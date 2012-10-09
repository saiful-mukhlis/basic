package z.gen;

import java.lang.reflect.Field;

import com.basic.annotation.db.Type;
import com.basic.db.Usr;
import com.global.App;
import com.google.common.base.CaseFormat;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class CreateFieldOd {

	private ODocument o;
	private String nama;
	
	public CreateFieldOd(Class klas) {
		super();
		StringBuilder s0=new StringBuilder();
		StringBuilder s=new StringBuilder();
		StringBuilder s1=new StringBuilder();
		
		s0.append("\n");
		s0.append("private ODocument o;");
		s0.append("\n");
		s0.append("public ODocument getO() {");
		s0.append("\n");
		s0.append("return o;");
		s0.append("\n");
		s0.append("}");
		s0.append("\n");
		s0.append("public void setO(ODocument o) {");
		s0.append("\n");
		s0.append("this.o = o;");
		s0.append("\n");
		s0.append("}");
		
		
		Field[]  x=klas.getDeclaredFields();
		for (Field f : x) {
			
			if (f.getName().equalsIgnoreCase("TABLE")) {
				continue;
			}
			
			String type="String";
			String konstatnta=f.getName();
			String lc=CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, konstatnta);
			String uc=CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, konstatnta);
			
			
			s1.append("\n");
			s1.append("public "+type+" get"+uc+"() {");
			s1.append("\n");
			
			Type c=f.getAnnotation(Type.class);
			if (c!=null) {
				s1.append("return App.get"+klas.getSimpleName()+"Dao()."+lc+"ToString(o);");
			}else{
				s1.append("return App.get"+klas.getSimpleName()+"Dao().get"+uc+"(o);");
			}
			
			s1.append("\n");
			s1.append("}");
			
			s1.append("\n");
			s1.append("public void set"+uc+"(String "+lc+") {");
			s1.append("\n");
			s1.append("this.nama = "+lc+";");
			s1.append("\n");
			s1.append("}");
			
			s.append("\n");
			s.append("private ");
			
			s.append(type);
			s.append(" ");
			s.append(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, f.getName()));
			s.append(";");
			
			
		}
		
		System.out.println(s0.toString());
		System.out.println(s.toString());
		System.out.println(s1.toString());
	}

	public String getNama() {
		return App.getUsrDao().getNama(o);
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	
	

}
