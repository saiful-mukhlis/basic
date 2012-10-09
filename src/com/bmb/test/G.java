package com.bmb.test;



import com.global.App;
import com.google.common.base.CaseFormat;

public class G {
	public static void g() {
		App.info("\n");
//		App.info("Generate C");
		
		int TEXT_FIELD=0;
		
		String model="Paket";
//		String [] field={Paket.CODE,Paket.NAMA,Paket.KET,Paket.HARGA,Paket.UMUR,Paket.MAX_DOWNLINE,Paket.TGL_START,Paket.TGL_END,Paket.STATUS,Paket.TOTAL_PELANGGAN, Paket.TOTAL_PENDAPATAN, Paket.TARGET_PASAR};
//		int [] type={TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD};
		
//		String [] field={Cp.CODE, Cp.NAMA, Cp.JENIS_KELAMIN, Cp.KET, Cp.NO_HP1, Cp.NO_HP2, Cp.NO_TELP, Cp.PIN_BB, Cp.ALAMAT};
//		int [] type={TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD};
		
//		String [] field={Pelanggan.CODE, Pelanggan.NAMA_TOKO, Pelanggan.NAMA_PEMILIK, Pelanggan.JENIS_TOKO, Pelanggan.ALAMAT, Pelanggan.KOTA, Pelanggan.NO_TELP, Pelanggan.NO_FAX, Pelanggan.NO_HP1, Pelanggan.NO_HP2, Pelanggan.PIN_BB1, Pelanggan.PIN_BB2, Pelanggan.STATUS};
//		int [] type={TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD};
		
		
		StringBuilder s=new StringBuilder();
		
		s.append("package com.bmb.app.impl.view.basic;");
		s.append("\n");
		s.append("import org.bmb.app.base.abstrak.ComponetFormAbstract;");
		s.append("\n");
		s.append("import org.bmb.app.base.builder.FormBuilder;");
		s.append("\n");
		s.append("import org.bmb.app.base.komponen.TextField;");
		s.append("\n");
		s.append("import com.bmb.app.global.App;");
		s.append("\n");
		s.append("import com.bmb.app.lang.L"+model+";");
		s.append("\n");
		s.append("import com.bmb.app.dao."+model+"Dao;");
		s.append("\n");
		s.append("import com.jgoodies.forms.layout.FormLayout;");
		s.append("\n");
		s.append("import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;");
		s.append("\n");
		s.append("import com.orientechnologies.orient.core.record.impl.ODocument;");
		s.append("\n");
		s.append("\n");
		s.append("public class "+model+"C extends ComponetFormAbstract{");
		s.append("\n");
		s.append("\n");
		
		for (int i = 0; i < type.length; i++) {
			if (type[i]==TEXT_FIELD) {
				s.append("protected TextField "+field[i]+";");
				s.append("\n");
			}
		}
		

		
		s.append("\n");
		s.append("\n");
		
		
		
		
		
		s.append("public void initComponent(ODatabaseDocumentTx db){");
		s.append("\n");
		
		for (int i = 0; i < type.length; i++) {
			if (type[i]==TEXT_FIELD) {
				s.append(field[i]+"=new TextField();");
				s.append("\n");
			}
		}
		
		s.append("}");
		s.append("\n");
		
		s.append("\n");
		s.append("\n");
		
		
		s.append("public void resetContentComponent(){");
		s.append("\n");
		
		for (int i = 0; i < type.length; i++) {
			if (type[i]==TEXT_FIELD) {
				s.append(field[i]+".setText(\"\");");
				s.append("\n");
			}
		}
		
		s.append("}");
		s.append("\n");
		
		
		s.append("\n");
		s.append("\n");
		
		
		s.append("public void setColorView(){");
		s.append("\n");
		
		for (int i = 0; i < type.length; i++) {
			if (type[i]==TEXT_FIELD) {
				s.append(field[i]+".setBackground(App.whiteSmoke);");
				s.append("\n");
			}
		}
		
		s.append("}");
		s.append("\n");
		
		s.append("\n");
		s.append("\n");
		
		
		s.append("public void setEditable(boolean isEdit){");
		s.append("\n");
		
		for (int i = 0; i < type.length; i++) {
			if (type[i]==TEXT_FIELD) {
				s.append(field[i]+".setEditable(isEdit);");
				s.append("\n");
			}
		}
		
		s.append("}");
		s.append("\n");
		
		
		s.append("\n");
		s.append("\n");
		
		
		s.append("public void setContentComponent(ODocument o){");
		s.append("\n");
		
		s.append("if (o!=null && modelIsTrue(o)){");
		s.append("\n");
		s.append(model+"Dao d=App.get"+model+"Dao();");
		s.append("\n");
		
		for (int i = 0; i < type.length; i++) {
			if (type[i]==TEXT_FIELD) {
				s.append(field[i]+".setText(d.get"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(o));");
				s.append("\n");
			}
		}
		
		s.append("}");
		s.append("\n");
		
		s.append("}");
		s.append("\n");
		
		
		
		
		s.append("\n");
		s.append("\n");
		
		
		s.append("public void init(ODatabaseDocumentTx db){");
		s.append("\n");
		
		s.append("lebar=0.37;");
		s.append("\n");
		s.append("dao=App.get"+model+"Dao();");
		s.append("\n");
		s.append("initComponent(db);");
		s.append("\n");

		
		s.append("}");
		s.append("\n");
		
		
		
		
		
		s.append("\n");
		s.append("\n");
		
		
		s.append("public void buildForm(ODatabaseDocumentTx db) {");
		s.append("\n");
		
		s.append("Double tmp = App.getW()*lebar;");
		s.append("\n");
		s.append("layout = new FormLayout(");
		s.append("\n");
		s.append("\"r:p,   	10px,   	f:max(\"+tmp.intValue()+\"px;p):g,  10px \",");
		s.append("\n");
		
		s.append("\"");
		
		for (int i = 0; i < type.length; i++) {
			if (type[i]==TEXT_FIELD) {
				s.append("p,3dlu,  ");
			}
		}
		
		s.append("");
		s.append("10dlu\");");
		s.append("\n");
		
		s.append("\n");
		
		
		s.append("layout.setColumnGroups(new int[][] { { 5, 7 } });");
		s.append("\n");
		s.append("builder = new FormBuilder(layout, true);");
		s.append("\n");
		
		for (int i = 0; i < type.length; i++) {
			if (type[i]==TEXT_FIELD) {
				s.append("builder.append( L"+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+", "+field[i]+", 1, "+(i*2+1)+", 5);");
				s.append("\n");
			}
		}
		s.append("");
		s.append("\n");

		
		s.append("}");
		s.append("\n");
		
		
		
		
		
		s.append("public void requestDefaultFocus(){");
		s.append("\n");
		

		
		s.append(field[0]+".requestFocus();\n");

		
		s.append("}");
		s.append("\n");
		
		s.append("}");
		s.append("\n");
		
		
		s.append("");
		s.append("\n");
		s.append("");
		s.append("\n");
		s.append("");
		s.append("\n");
		s.append("");
		s.append("\n");
		s.append("");
		s.append("\n");
		s.append("");
		s.append("\n");
		s.append("");
		s.append("\n");
		s.append("");
		s.append("\n");
		s.append("");
		s.append("\n");
		s.append("");
		s.append("\n");
		s.append("");
		s.append("\n");
		
		App.info(s.toString());
		
	}

}
