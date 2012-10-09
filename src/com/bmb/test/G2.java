
package com.bmb.test;



import org.basic.comp.base.TextField;

import com.basic.db.Grp;
import com.basic.db.Usr;
import com.basic.lang.LPelanggan;
import com.bmb.app.dao.PelangganDao;
import com.bmb.app.db.Pelanggan;
import com.bmb.app.db.Pp;
import com.global.App;
import com.google.common.base.CaseFormat;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class G2 {
	public static void g() {
		App.info("\n");
//		App.info("Generate C");
		
		int S=0;
		int C=1;
		int D=2;
		int T=3;
		int STATUS=4;
		int I=5;
		
		String model="Grp";
//		String [] field={Paket.NAMA,Paket.KET,Paket.HARGA,Paket.UMUR,Paket.MAX_DOWNLINE,Paket.TGL_START,Paket.TGL_END,Paket.STATUS,Paket.TOTAL_PELANGGAN, Paket.TOTAL_PENDAPATAN, Paket.TARGET_PASAR};
//		int [] type={TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD};
		
//		String [] field={Cp.CODE, Cp.NAMA, Cp.JENIS_KELAMIN, Cp.KET, Cp.NO_HP1, Cp.NO_HP2, Cp.NO_TELP, Cp.PIN_BB, Cp.ALAMAT};
//		int [] type={TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD,TEXT_FIELD};
		
//		String [] field={Pelanggan.CODE, Pelanggan.NAMA_TOKO, Pelanggan.NAMA_PEMILIK, Pelanggan.JENIS_TOKO, Pelanggan.ALAMAT, Pelanggan.KOTA, Pelanggan.NO_TELP, Pelanggan.NO_FAX, Pelanggan.NO_HP1, Pelanggan.NO_HP2, Pelanggan.PIN_BB1, Pelanggan.PIN_BB2, Pelanggan.STATUS};
//		int [] type=    {C             ,S                    ,S                       ,STATUS,              S,                S,             S,                  S,                  S,               S,                 S,                S,                STATUS};
		
		
//		String [] field={Pp.CODE,Pp.TGL_DAFTAR,Pp.TGL_EXP,Pp.STATUS};
//		int [] type=    {C      ,T           ,T           ,I,       }; 
		
//		String [] field={Usr.CODE, Usr.USERNAME, Usr.PASSWORD, Usr.GROUP,Usr.NAMA, Usr.ALAMAT, Usr.KOTA, Usr.NO_IDENTITAS, Usr.JENIS_IDENTITAS, Usr.KOTA_LAHIR, Usr.TGL_LAHIR, Usr.JENIS_KELAMIN, Usr.NO_TELP, Usr.NO_HP1, Usr.NO_HP2, Usr.PIN_BB, Usr.TGL_MASUK, Usr.GAJI, Usr.JENIS_PEKERJAAN, Usr.PENDIDIKAN_TERAKHIR, Usr.STATUS};
//		int [] type=    {C      ,S              ,S           ,STATUS    ,S       ,S          ,S         ,S               ,S                   ,S               ,T            ,I                 ,S           ,S          ,S          ,S          ,T             ,D        , STATUS             ,S                       , STATUS       }; 
		
		
		String [] field={Grp.CODE, Grp.NAME, Grp.NOTE, Grp.KEY};
		int [] type=    {C      ,S         ,S        ,        S};
		
		StringBuilder s=new StringBuilder();
		
		
		
		for (int i = 0; i < type.length; i++) {
			if (type[i]==C) {
				code(s, model, field, i);
			}else if (type[i]==S) {
				str(s, model, field, i);
			}else if (type[i]==D) {
				doub(s, model, field, i);
			}else if (type[i]==T) {
				tgl(s, model, field, i);
			}else if (type[i]==STATUS) {
				status(s, model, field, i);
			}else if (type[i]==I) {
				intg(s, model, field, i);
			}
		}
		
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
	
	
	public static void intg(StringBuilder s,String model , String[] field, int i){
		s.append("public "+model+"Dao set"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODocument o, int "+field[i]+") {\n");
		s.append("		o.field("+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+", "+field[i]+", OType.INTEGER);\n");
		s.append("		return this;\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	public "+model+"Dao set"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODocument o, TextField "+field[i]+") {\n");
		s.append("		int tmp=0;\n");
		s.append("		try {\n");
		s.append("			tmp=Integer.parseInt("+field[i]+".getText());\n");
		s.append("		} catch (Exception e) {\n");
		s.append("		}\n");
		s.append("		set"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(o, tmp);\n");
		s.append("		return this;\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	public int get"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODocument o) {\n");
		s.append("		Integer tmp = o.field("+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+");\n");
		s.append("		if (tmp == null) {\n");
		s.append("			tmp = 0;\n");
		s.append("		}\n");
		s.append("		return tmp;\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	public boolean v"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODatabaseDocumentTx db, TextField "+field[i]+") {\n");
		s.append("		if (!validate(db, "+field[i]+", L"+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+")) {\n");
		s.append("			return false;\n");
		s.append("		}\n");
		s.append("		try {\n");
		s.append("			Integer tmp=Integer.parseInt("+field[i]+".getText());\n");
		s.append("		} catch (Exception e) {\n");
		s.append("			App.showErrorNotValid(db, L"+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+");\n");
		s.append("			"+field[i]+".setText(\"0\");\n");
		s.append("			"+field[i]+".requestFocus();\n");
		s.append("			return false;\n");
		s.append("		}\n");
		s.append("		return true;\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	public boolean v"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODatabaseDocumentTx db, TextField "+field[i]+", ODocument model) {\n");
		s.append("		return v"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(db, "+field[i]+");\n");
		s.append("	}\n");

	}
	
	public static void doub(StringBuilder s,String model , String[] field, int i){
		s.append("public "+model+"Dao set"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODocument o, double "+field[i]+") {\n");
		s.append("		o.field("+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+", "+field[i]+", OType.DOUBLE);\n");
		s.append("		return this;\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	public "+model+"Dao set"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODocument o, TextField "+field[i]+") {\n");
		s.append("		double tmp = 0;\n");
		s.append("		try {\n");
		s.append("			tmp = Double.parseDouble("+field[i]+".getText());\n");
		s.append("		} catch (Exception e) {\n");
		s.append("		}\n");
		s.append("		set"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(o, tmp);\n");
		s.append("		return this;\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	public double get"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODocument o) {\n");
		s.append("		Double tmp = o.field("+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+");\n");
		s.append("		if (tmp == null) {\n");
		s.append("			tmp = 0.0;\n");
		s.append("		}\n");
		s.append("		return tmp;\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	public boolean v"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODatabaseDocumentTx db, TextField "+field[i]+") {\n");
		s.append("		if (!validate(db, "+field[i]+", L"+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+")) {\n");
		s.append("			return false;\n");
		s.append("		}\n");
		s.append("		try {\n");
		s.append("			Double tmp = Double.parseDouble("+field[i]+".getText());\n");
		s.append("		} catch (Exception e) {\n");
		s.append("			App.showErrorNotValid(db, L"+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+");\n");
		s.append("			"+field[i]+".setText(\"0\");\n");
		s.append("			"+field[i]+".requestFocus();\n");
		s.append("			return false;\n");
		s.append("		}\n");
		s.append("		return true;\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	public boolean v"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODatabaseDocumentTx db, TextField "+field[i]+", ODocument model) {\n");
		s.append("		return v"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(db, "+field[i]+");\n");
		s.append("	}\n");
	}
	
	public static void status(StringBuilder s,String model , String[] field, int i){
		s.append("public "+model+"Dao set"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODocument o, int "+field[i]+") {\n");
		s.append("		o.field("+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+", "+field[i]+", OType.INTEGER);\n");
		s.append("		return this;\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	public "+model+"Dao set"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODocument o, TextField "+field[i]+") {\n");
		s.append("		Integer tmp=0;\n");
		s.append("		try {\n");
		s.append("			tmp=Integer.parseInt("+field[i]+".getText());\n");
		s.append("		} catch (Exception e) {\n");
		s.append("		}\n");
		s.append("		set"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(o, tmp);\n");
		s.append("		return this;\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	public String get"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODocument o) {\n");
		s.append("		Integer tmp = o.field("+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+");\n");
		s.append("		if (tmp == null) {\n");
		s.append("			return \"\";\n");
		s.append("		}\n");
		s.append("		String [] tmp2=get"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"Data();\n");
		s.append("		return tmp2[tmp];\n");
		s.append("	}\n");
		s.append("	\n");
		s.append("	public int get"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"Index(ODocument o) {\n");
		s.append("		Integer tmp = o.field("+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+");\n");
		s.append("		if (tmp == null) {\n");
		s.append("			return 0;\n");
		s.append("		}\n");
		s.append("		return tmp;\n");
		s.append("	}\n");
		s.append("	\n");
		s.append("	public String [] get"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"Data(){\n");
		s.append("		String a1=App.getT(\"Pilih "+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+" "+model+"\");\n");
		s.append("		String ta=App.getT(\"Tidak Aktif\");\n");
		s.append("		String a=App.getT(\"Aktif\");\n");
		s.append("		String [] string"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"={a1,ta,a};\n");
		s.append("		return string"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+";\n");
		s.append("	}\n");
	}
	
	public static void tgl(StringBuilder s,String model , String[] field, int i){
		s.append("public "+model+"Dao set"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODocument o, Date "+field[i]+") {\n");
		s.append("		o.field("+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+", "+field[i]+", OType.DATE);\n");
		s.append("		return this;\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	public "+model+"Dao set"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODocument o, DatePicker "+field[i]+") {\n");
		s.append("		set"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(o, "+field[i]+".getDate());\n");
		s.append("		return this;\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	public String get"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODocument o) {\n");
		s.append("		Date tmp = o.field("+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+");\n");
		s.append("		if (tmp == null) {\n");
		s.append("			return \"\";\n");
		s.append("		}\n");
		s.append("		return App.dateFormat.format(tmp);\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	public "+model+"Dao setTglEnd(ODocument o, Date tglEnd) {\n");
		s.append("		o.field("+model+".TGL_END, tglEnd, OType.DATE);\n");
		s.append("		return this;\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	public "+model+"Dao setTglEnd(ODocument o, DatePicker tglEnd) {\n");
		s.append("		setTglEnd(o, tglEnd.getDate());\n");
		s.append("		return this;\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	public String getTglEnd(ODocument o) {\n");
		s.append("		Date tmp = o.field("+model+".TGL_END);\n");
		s.append("		if (tmp == null) {\n");
		s.append("			return \"\";\n");
		s.append("		}\n");
		s.append("		return App.dateFormat.format(tmp);\n");
		s.append("	}\n");
	}
	
	public static void str(StringBuilder s,String model , String[] field, int i){
		s.append("public "+model+"Dao set"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODocument o, String "+field[i]+") {\n");
		s.append("		o.field("+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+", "+field[i]+");\n");
		s.append("		return this;\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	public "+model+"Dao set"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODocument o, TextField "+field[i]+") {\n");
		s.append("		set"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(o, "+field[i]+".getText());\n");
		s.append("		return this;\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	public String get"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODocument o) {\n");
		s.append("		String tmp = o.field("+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+");\n");
		s.append("		if (tmp == null) {\n");
		s.append("			tmp = \"\";\n");
		s.append("		}\n");
		s.append("		return tmp;\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	public boolean v"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODatabaseDocumentTx db, TextField "+field[i]+") {\n");
		s.append("		if (!validate(db, "+field[i]+", L"+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+")) {\n");
		s.append("			return false;\n");
		s.append("		}\n");
		s.append("		return true;\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	public boolean v"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODatabaseDocumentTx db, TextField "+field[i]+", ODocument model) {\n");
		s.append("		if (!validate(db, "+field[i]+", L"+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+")) {\n");
		s.append("			return false;\n");
		s.append("		}\n");
		s.append("		return true;\n");
		s.append("	}\n");
	}
	
	public static void code(StringBuilder s,String model , String[] field, int i){
		s.append("public "+model+"Dao set"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODocument o, String "+field[i]+"){\n");
		s.append("	o.field("+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+", "+field[i]+");\n");
		s.append("	return this;\n");
		s.append("}\n");
		
		s.append("public "+model+"Dao set"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODocument o, TextField "+field[i]+"){\n");
		s.append("	set"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(o, "+field[i]+".getText());\n");
		s.append("	return this;\n");
		s.append("}\n");
		
		s.append("public String get"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODocument o){\n");
		s.append("	String tmp=o.field("+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+");\n");
		s.append("	if (tmp==null) {\n");
		s.append("		tmp=\"\";\n");
		s.append("	}\n");
		s.append("	return tmp;\n");
		s.append("}\n");
		
		s.append("public boolean v"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODatabaseDocumentTx db, TextField "+field[i]+"){\n");
		s.append("	if (!("+field[i]+".getText().trim().equals(\"\") || "+field[i]+".getText().trim().equalsIgnoreCase(\"Auto\"))) {\n");
		s.append("		long tmp=getCountByColumn(db, "+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+", "+field[i]+".getText());\n");
		s.append("		if (tmp>0) {\n");
		s.append("			App.showErrorDataSudahAda(db, L"+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+");\n");
		s.append("			return false;\n");
		s.append("		}\n");
		s.append("	}\n");
		s.append("	return true;\n");
				s.append("}\n");
		
		s.append("public boolean v"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(ODatabaseDocumentTx db, TextField "+field[i]+", ODocument model){\n");
		s.append("	if (!"+field[i]+".getText().equalsIgnoreCase((String) model.field("+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+"))) {\n");
		s.append("		long tmp=getCountByColumn(db, "+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+", "+field[i]+".getText());\n");
		s.append("		if (tmp>0) {\n");
		s.append("			App.showErrorDataSudahAda(db, L"+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+");\n");
		s.append("			return false;\n");
		s.append("		}\n");
		s.append("		if ("+field[i]+".getText().trim().equals(\"\")) {\n");
		s.append("			App.showErrorFieldEmpty(db, L"+model+"."+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+");\n");
		s.append("			return false;\n");
		s.append("		}\n");
		s.append("	}\n");
		s.append("	return true;\n");
		s.append("}\n");
	}

}
