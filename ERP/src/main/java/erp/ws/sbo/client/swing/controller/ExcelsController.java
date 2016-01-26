package erp.ws.sbo.client.swing.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import erp.ws.sbo.client.swing.tablemodel.DocLineModel;
import erp.ws.sbo.client.swing.util.general.ComboBoxItem;
import erp.ws.sbo.client.swing.util.general.JMyTable;
import erp.ws.sbo.client.swing.view.Utility.ExcelView;
import erp.ws.sbo.utils.DbUtils;



import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelsController  implements ActionListener {

	private   Object[][] ob,ob1;
	private erp.ws.sbo.client.swing.view.Utility.ExcelView v;
	private String hql;
	private String[] cols;
	@SuppressWarnings("rawtypes")
	private DbUtils dbu1=new DbUtils();
    public ExcelsController(ExcelView v)
    {
	   this.setV(v);
    }
 
    /**
     * 导出数据为XLS格式
     * @param fileName        文件的名称，可以设为绝对路径，也可以设为相对路径
     * @param content        数据的内容
     */
    public  void exportExcel(String fileName, Object[][] content,String tablename) {
        WritableWorkbook wwb;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fileName);
            wwb = Workbook.createWorkbook(fos);
            WritableSheet ws = wwb.createSheet(tablename, 10);        // 创建一个工作表
            //设置单元格的文字格式
            WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
                    UnderlineStyle.NO_UNDERLINE,Colour.BLUE);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE); 
            wcf.setAlignment(Alignment.CENTRE); 
           // ws.setRowView(0, 500);
            //JOptionPane.showMessageDialog(v, content.length);
            //    填充数据的内容          
            for (int i = 0; i < content.length; i++){
                for(int j=0;j<content[i].length;j++)
                {
                    ws.addCell(new Label(j, i, content[i][j]==null?"":content[i][j].toString(), wcf));                   
                }
             
                if(i == 0)
                    wcf = new WritableCellFormat();
            }

            wwb.write();
            wwb.close();
        } catch (IOException e){
        } catch (RowsExceededException e){
        } catch (WriteException e){}
    }

    /**
     * 从Excel文件里读取数据保存到Vector里
     * @param fileName        Excel文件的名称
     * @return                Vector对象,里面包含从Excel文件里获取到的数据
     */
    public  Object[][] importExcel(String fileName,String tablename){
    	
        try {        	
            Workbook book = Workbook.getWorkbook(new File(fileName));            
            Sheet sheet = book.getSheet(0);
            if(!tablename.equals("")&&!tablename.equals(book.getSheet(0).getName()))
            {
               JOptionPane.showMessageDialog(v,book.getSheet(0).getName()+"选择表和excel中数据表不一致，不允许导入");
               return null;
            }         
            // 获得第一个工作表对象 
            int rows = sheet.getRows();
            int cols=sheet.getColumns();
            ob=new Object[rows][cols];
            for(int i = 1; i < rows; i++) {
            	 Cell [] cell = sheet.getRow(i);           	 
                 if(cell.length == 0)
                     continue;
            	for(int j=0;j<cols;j++)
            	{
            		ob[i-1][j]=sheet.getCell(j, i).getContents();
            	}             
            }

            book.close();
        }catch(Exception e) {} 
        return ob;
    }

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Handle open button action.
        if (e.getSource()==v.getBtn_selexcelfile()) {
        	  if(!((ComboBoxItem)v.getComboBox().getSelectedItem()).getValue().toString().equals("USR_Customer"))
              {
              	JOptionPane.showMessageDialog(v, "目前只允许导入客户资料");
              	return;
              }
        	v.getJt_selexcelfile().setText("");
            int returnVal = v.getFc().showOpenDialog(v);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = v.getFc().getSelectedFile();
                //This is where a real application would open the file.
                v.getJt_selexcelfile().setText(file.getPath());
            } else {
            	v.getJt_selexcelfile().setText("Open command cancelled by user");
            }
            v.getJt_selexcelfile().setCaretPosition(v.getJt_selexcelfile().getDocument().getLength());
            //JOptionPane.showMessageDialog(null,importExcel(v.getJt_selexcelfile().getText())); 
          
            v.getOd().setDataSet(importExcel(v.getJt_selexcelfile().getText(),((ComboBoxItem)v.getComboBox().getSelectedItem()).getValue().toString()));
            v.getOd().fireTableDataChanged();
        }
        else if(e.getSource()==v.getBtn_export()) {
        	v.getJt_selexcelfile().setText("");
            int returnVal = v.getFc().showSaveDialog(v);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = v.getFc().getSelectedFile();
                //This is where a real application would open the file.
                v.getJt_selexcelfile().setText(file.getPath()+".xls");
            } else {
            	v.getJt_selexcelfile().setText("save command cancelled by user");
            }
            v.getJt_selexcelfile().setCaretPosition(v.getJt_selexcelfile().getDocument().getLength());
             cols=dbu1.getColumnNames(((ComboBoxItem)v.getComboBox().getSelectedItem()).getValue().toString());
            
            hql="select * from "+((ComboBoxItem)v.getComboBox().getSelectedItem()).getValue().toString();
            ob1=new Object[dbu1.executeSQLQuery(hql).length+1][cols.length];
           
            for(int i=0;i<cols.length;i++)
            {
            	ob1[0][i]=cols[i];
            }
            ob=dbu1.executeSQLQuery(hql);
            for(int i=0;i<ob1.length-1;i++)
            {
        	  for(int j=0;j<ob1[0].length;j++)
              {
        	    ob1[i+1][j]=ob[i][j];
              }
            }
            exportExcel( v.getJt_selexcelfile().getText(), ob1,((ComboBoxItem)v.getComboBox().getSelectedItem()).getValue().toString());
                        
        }
        else if(e.getSource()==v.getComboBox()){
        	 hql="select * from "+((ComboBoxItem)v.getComboBox().getSelectedItem()).getValue().toString();
				
     	     v.setOd(new DocLineModel(v.getDbu1(),((ComboBoxItem)v.getComboBox().getSelectedItem()).getValue().toString(),hql));		   	   
     				
     		 v.setTable(new JMyTable(v.getOd()));
     		 v.getTable().setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
     		 v.getScp().removeAll();
        	 v.getPanel_1().remove(v.getScp());
     		 v.setScp(new JScrollPane());
     		 v.getScp().setViewportView(v.getTable());
     		
        	 v.getPanel_1().setLayout(v.getSl_panel_1());
        	 v.getSl_panel_1().putConstraint(SpringLayout.NORTH, v.getScp(), 0, SpringLayout.NORTH, v.getPanel_1());
        	 v.getSl_panel_1().putConstraint(SpringLayout.WEST, v.getScp(), 0, SpringLayout.WEST, v.getPanel_1());
        	 v.getSl_panel_1().putConstraint(SpringLayout.SOUTH, v.getScp(), 0, SpringLayout.SOUTH, v.getPanel_1());
             v.getSl_panel_1().putConstraint(SpringLayout.EAST, v.getScp(), 0, SpringLayout.EAST, v.getPanel_1());
        	
             v.getPanel_1().add(v.getScp());
      		 
        }
        else if(e.getSource()==v.getBtn_importdb()){
        	
        	if(JOptionPane.showConfirmDialog(v, "导入前建议先将数据导出excel作为备份,是否继续导入,导入将更新现有数据库数据,确实要将表格的数据导入到现有数据库吗")==0){
        	   JOptionPane.showMessageDialog(v, "导入");
        	}
        }
        else{}

	}

	public ExcelView getV() {
		return v;
	}

	public void setV(ExcelView v) {
		this.v = v;
	}

    
}
