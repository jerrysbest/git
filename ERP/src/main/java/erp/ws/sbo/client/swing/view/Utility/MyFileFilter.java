package erp.ws.sbo.client.swing.view.Utility;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class MyFileFilter extends FileFilter{
	public boolean accept(File f) {
		   String tmp=f.getName().toLowerCase();
		  //��ʾ�ļ���
		         if(f.isDirectory()){
		             return true; 
		   }
      //ѭ�������ļ�����
		        if(tmp.endsWith(".xls")||f.isDirectory()){
		           return true;
		        }
		         return false;
		}
		public String getDescription() {
		  return "excel(*.xls)";
		 }

}
