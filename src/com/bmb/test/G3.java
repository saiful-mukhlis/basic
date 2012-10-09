package com.bmb.test;



import com.bmb.app.db.Cp;
import com.bmb.app.db.Paket;
import com.bmb.app.db.Pelanggan;
import com.global.App;
import com.google.common.base.CaseFormat;

public class G3 {
	public static void g() {
		App.info("\n");
		
		
		String model="Paket";
//		String [] field={Paket.NAMA,Paket.KET,Paket.HARGA,Paket.UMUR,Paket.MAX_DOWNLINE,Paket.TGL_START,Paket.TGL_END,Paket.STATUS,Paket.TOTAL_PELANGGAN, Paket.TOTAL_PENDAPATAN, Paket.TARGET_PASAR};
		
		
//		String [] field={Pelanggan.CODE, Pelanggan.NAMA_TOKO, Pelanggan.NAMA_PEMILIK, Pelanggan.JENIS_TOKO, Pelanggan.ALAMAT, Pelanggan.KOTA, Pelanggan.NO_TELP, Pelanggan.NO_FAX, Pelanggan.NO_HP1, Pelanggan.NO_HP2, Pelanggan.PIN_BB1, Pelanggan.PIN_BB2, Pelanggan.STATUS};
		
		String [] field={Paket.CODE, Paket.NAMA, Paket.KET, Paket.HARGA, Paket.UMUR, Paket.MAX_DOWNLINE, Paket.TGL_START, Paket.TGL_END, Paket.STATUS, Paket.TOTAL_PELANGGAN, Paket.TOTAL_PENDAPATAN, Paket.TARGET_PASAR};
		
		StringBuilder s=new StringBuilder();
		
	
		
		for (int i = 0; i < field.length; i++) {
			s.append("else if (columnIndex == "+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field[i])+") {\n");
			s.append("	return App.get"+model+"Dao().get"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(m);\n");
			s.append("} \n");
				
				s.append("\n");
		}
		
		for (int i = 0; i < field.length; i++) {
			s.append("d.set"+CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field[i])+"(o, "+field[i]+"); \n");
		}
		
		
		
		s.append("\n");
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
