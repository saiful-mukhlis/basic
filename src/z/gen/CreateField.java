package z.gen;

import java.lang.reflect.Field;

import com.basic.annotation.db.Type;
import com.basic.db.Usr;
import com.google.common.base.CaseFormat;

public class CreateField {

	private String nama;
	
	public CreateField(Class klas) {
		super();
		StringBuilder s=new StringBuilder();
		
		StringBuilder s1=new StringBuilder();
		
		Field[]  x=klas.getDeclaredFields();
		for (Field f : x) {
			
			String type;
			String konstatnta=f.getName();
			String lc=CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, konstatnta);
			String uc=CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, konstatnta);
			
			
			Type c=f.getAnnotation(Type.class);
			if (c!=null) {
				type=c.t().getDefaultJavaType().getSimpleName();
			}else{
				type="String";
			}
			
			s.append("\n");
			s.append("private ");
			
			s.append(type);
			s.append(" ");
			s.append(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, f.getName()));
			s.append(";");
			
			s1.append("\n");
			s1.append("public "+type+" get"+uc+"() {");
			s1.append("\n");
			s1.append("return "+lc+";");
			s1.append("\n");
			s1.append("}");
			
			s1.append("\n");
			s1.append("public void set"+uc+"(String "+lc+") {");
			s1.append("\n");
			s1.append("this.nama = "+lc+";");
			s1.append("\n");
			s1.append("}");
		}
		
		System.out.println(s.toString());
		System.out.println(s1.toString());
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
	
	

}
