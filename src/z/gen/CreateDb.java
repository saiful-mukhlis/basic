package z.gen;

import java.lang.reflect.Field;

import com.basic.annotation.db.Type;
import com.basic.db.Usr;
import com.google.common.base.CaseFormat;

public class CreateDb {

	private String nama;
	
	public CreateDb(Class klas) {
		super();
		StringBuilder s=new StringBuilder();
		
		
		Field[]  x=klas.getDeclaredFields();
		for (Field f : x) {
			
			String konstatnta=f.getName();
			String lc=CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, konstatnta);
			String uc=CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, konstatnta);
			
			
			
			s.append("\n");
			//public static final String DATE_TIME="";
			s.append("public static final String "+konstatnta+"=\""+lc+"\";");
			
		}
		
		System.out.println(s.toString());
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
	
	

}
