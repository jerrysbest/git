package erp.ws.sbo.client.swing.util.general;


import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import erp.ws.sbo.utils.MdbHibernateUtils;

public class AutoCompleter implements  KeyListener,ItemListener,FocusListener{
	private JComboBox owner = null;
	private JTextField editor = null;

	private ComboBoxModel model = null;
	private String hql=null;
	private String hql1=null;
	private int length;

	public AutoCompleter(JComboBox comboBox) {
	
		owner = comboBox;
	    editor = (JTextField) comboBox.getEditor().getEditorComponent();	   
	    editor.addKeyListener(this);
	    editor.addFocusListener(this);
	    model = comboBox.getModel();
	    owner.addItemListener(this);	        
		  
}
	public AutoCompleter(String hql,String hql1,int i,JComboBox comboBox) {
		
		this.hql=hql;
		this.hql1=hql1;
		this.length=i;
		owner = comboBox;
	    editor = (JTextField) comboBox.getEditor().getEditorComponent();	   
	    editor.addKeyListener(this);
	    editor.addFocusListener(this);
	    model = comboBox.getModel();
	    owner.addItemListener(this);	        
		  
}
	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
	
		  
	}

	public void keyReleased(KeyEvent e) {
		//long result=(d.parse(nowtime).getTime()-d.parse(testtime).getTime())/1000;		 
		char ch = e.getKeyChar();
	    if (ch == KeyEvent.CHAR_UNDEFINED 
	    		||Character.isISOControl(ch) 
	    		||ch == KeyEvent.VK_DELETE
	        ) {
	      return;
	    }
		
	    int caretPosition = editor.getCaretPosition();
	    String str = editor.getText();
	   // JOptionPane.showMessageDialog(null,str);
		
	    if (str.length() <length) {
	      return;
	    }
	   // model = comboBox.getModel();	   
		Session session = MdbHibernateUtils.getSession();
	      Transaction t = session.beginTransaction();{
	       try {
	           //** 生成主键对象 *//    
	    	 /*  "select p.cardCode+','+p.cardName from Ocrd as p " +
				" where p.cardCode like '%"+str+"%' or p.cardName like '%"+str+"%' and p.cardType='C'"*/
			Query q=session.createSQLQuery(hql);	
			q.setString("str1", "%"+str+"%");
			q.setString("str2", "%"+str+"%");
			q.setFirstResult(0);
			q.setMaxResults(100);
		    @SuppressWarnings("rawtypes")
			List list=q.list();
	        t.commit();
	       
	        Iterator<?> it=list.iterator();
	        owner.removeAllItems();
	           while(it.hasNext()){	              
	          owner.addItem(it.next().toString());	         
	              }
	         } catch (HibernateException e1) {
	           e1.printStackTrace();
	           t.rollback();
	         } finally {
	       	  MdbHibernateUtils.closeSession(session);
	         } 
	    //   JOptionPane.showMessageDialog(null,str);
	       
	    autoComplete(str, caretPosition);
	      }
     
	}
	
	protected void autoComplete(String strf, int caretPosition) {
	    Object[] opts;
	    opts = getMatchingOptions(strf);
	  /* if (owner != null) {
	      model = new DefaultComboBoxModel(opts);
	      owner.setModel(model);
	    }*/
	    if (opts.length > 0) {
	      if(caretPosition>editor.getText().length()) return;
	     // editor.setCaretPosition(caretPosition);
	      //editor.setText(editor.getText().trim().substring(0,caretPosition));
	      if (owner != null) {
	        try {	        	
	          owner.showPopup();        
	          if(model.getSize()==1){
	        	  
	        	  editor.setText(model.getElementAt(0).toString().split(",")[0]);
	        	  
	          }
	          else{
	          editor.setText(strf);	     
	        
	          }
	        }
	        catch (Exception ex) {
	          ex.printStackTrace();
	        }
	      }
	    }
	}
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		protected Object[] getMatchingOptions(String str) {
	        List v = new Vector();
	        List v1 = new Vector();

	        for (int k = 0; k < model.getSize(); k++) {
	          Object itemObj = model.getElementAt(k);
	          if (itemObj != null) {
	            String item = itemObj.toString().toLowerCase();
	            if (item.contains(str.toLowerCase())) {
	              v.add(model.getElementAt(k));
	            }
	            else {
	              v1.add(model.getElementAt(k));
	            }
	          }
	          else {
	            v1.add(model.getElementAt(k));
	          }
	        }
	        for (int i = 0; i < v1.size(); i++) {
	          v.add(v1.get(i));
	        }
	        if (v.isEmpty()) {
	          v.add(str);
	        }
	        return v.toArray();
	    }

	    public void itemStateChanged(ItemEvent event) {
	    	
	        if (event.getStateChange() == ItemEvent.SELECTED) {
	          int caretPosition = editor.getCaretPosition();
	          if (caretPosition != -1) {
	            try {
	              editor.moveCaretPosition(caretPosition);
	              editor.setText(editor.getText().split(",")[0]);
	            }
	            catch (IllegalArgumentException ex) {
	              ex.printStackTrace();
	            }
	          }
	        }
	    }

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}
		@SuppressWarnings("rawtypes")
		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			//JOptionPane.showMessageDialog(null,editor.getText());
			
			  String parm=((JTextField)e.getSource()).getText();			
		        if(parm.length()==0)
		        {		        	
		        	return;
		        }
			 editor.setText(editor.getText().split(",")[0]);
			// JOptionPane.showMessageDialog(null,editor.getText());
			 Session session = MdbHibernateUtils.getSession();
		      Transaction t = session.beginTransaction();{
		       try {
		           //** 生成主键对象 *//        
				/*Query q=session.createQuery("select p.cardCode+','+p.cardName from Ocrd as p " +
						" where p.cardCode='"+editor.getText()+"' and p.cardType='C'");	  */	
				Query q=session.createSQLQuery(hql1);	
				q.setString("str1",editor.getText());				
				q.setFirstResult(0);
				q.setMaxResults(100);
			    List list=q.list();
		        t.commit();
		      
		       if(list.size()==0)
		       {
		    	   JOptionPane.showMessageDialog(null,"请求的数据不存在");
		    	   editor.setText("");
		    	   editor.requestFocus(); 
		       }
		        
		       } catch (HibernateException e1) {
		           e1.printStackTrace();
		           t.rollback();
		       } finally {
		       	  MdbHibernateUtils.closeSession(session);
		       } 
		}
	
}
}
