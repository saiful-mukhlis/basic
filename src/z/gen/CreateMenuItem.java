package z.gen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.JMenuItem;

import com.basic.annotation.db.Type;
import com.basic.db.Usr;
import com.basic.lang.LUsr;
import com.basic.lang.LWindow;
import com.global.App;
import com.google.common.base.CaseFormat;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class CreateMenuItem {

	private ODocument o;
	private String nama;
	
	public CreateMenuItem(Class klas) {
		super();
		StringBuilder s1=new StringBuilder();
		
		

		
		
		Field[]  x=klas.getDeclaredFields();
		for (Field f : x) {
			
			if (f.getName().equalsIgnoreCase("TABLE")) {
				continue;
			}
			
			String konstatnta=f.getName();
			
			s1.append("item=new JMenuItem(LWindow.KET_SEARCH+LUsr."+konstatnta+");");
			s1.append("\n");
			s1.append("item.addActionListener(new ActionListener() {");
			s1.append("\n");
			s1.append("@Override");
			s1.append("\n");
			s1.append("public void actionPerformed(ActionEvent arg0) {");
			s1.append("\n");
			s1.append("itemSearch.setText(LWindow.KET_SEARCH+LUsr."+konstatnta+");");
			s1.append("\n");
			s1.append("colSearch=LUsr."+konstatnta+";");
			s1.append("\n");
			s1.append("}");
			s1.append("\n");
			s1.append("});");
			s1.append("\n");
			s1.append("menuItemSearch.add(item);");
			s1.append("\n");
			
			
		}
		
		System.out.println(s1.toString());
	}

	public String getNama() {
		return App.getUsrDao().getNama(o);
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	
	

}
