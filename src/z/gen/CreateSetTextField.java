package z.gen;

import java.lang.reflect.Field;

import org.basic.comp.base.TextField;

import com.basic.annotation.db.Type;
import com.basic.db.Usr;
import com.google.common.base.CaseFormat;

public class CreateSetTextField {

	private String nama;
	
	public CreateSetTextField(Class klas) {
		super();
		StringBuilder s=new StringBuilder();
		
		
		Field[]  x=klas.getDeclaredFields();
		for (Field f : x) {
			
			String konstatnta=f.getName();
			String lc=CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, konstatnta);
			String uc=CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, konstatnta);
			
			
			
			s.append("\n");
			//public static final String DATE_TIME="";
			s.append(lc+".setText(\"\");");
			
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
