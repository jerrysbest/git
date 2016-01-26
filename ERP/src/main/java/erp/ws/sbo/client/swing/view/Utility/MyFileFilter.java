package erp.ws.sbo.client.swing.view.Utility;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class MyFileFilter extends FileFilter{
	public boolean accept(File f) {
		   String tmp=f.getName().toLowerCase();
		  //显示文件夹
		         if(f.isDirectory()){
		             return true; 
		   }
      //循环过滤文件过滤
		        if(tmp.endsWith(".xls")||f.isDirectory()){
		           return true;
		        }
		         return false;
		}
		public String getDescription() {
		  return "excel(*.xls)";
		 }

}
