package com.basic.db;


import com.basic.annotation.db.Type;
import com.orientechnologies.orient.core.metadata.schema.OType;

public class Usr {
	public static final String TABLE = "Usr";
	public static final String CODE = "code";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	@Type(t = OType.LINK)
	public static final String GRP = "grp";

	public static final String NAMA = "nama";
	public static final String ALAMAT = "alamat";
	public static final String KOTA = "kota";
	public static final String NO_IDENTITAS = "noIdentitas";
	public static final String JENIS_IDENTITAS = "jenisIdentitas";
	public static final String KOTA_LAHIR = "kotaLahir";
	@Type(t = OType.DATE)
	public static final String TGL_LAHIR = "tglLahir";
	@Type(t = OType.INTEGER)
	public static final String JENIS_KELAMIN = "jenisKelamin";
	public static final String NO_TELP = "noTelp";
	public static final String NO_HP1 = "noHp1";
	public static final String NO_HP2 = "noHp2";
	public static final String PIN_BB = "pinBb";

	@Type(t = OType.DATE)
	public static final String TGL_MASUK = "tglMasuk";
	@Type(t = OType.DECIMAL)
	public static final String GAJI = "gaji";
	@Type(t = OType.LINK)
	public static final String JENIS_PEKERJAAN = "jenisPekerjaan";
	public static final String PENDIDIKAN_TERAKHIR = "pendidikanTerakhir";
	@Type(t = OType.INTEGER)
	public static final String STATUS = "status";
	
	
	
	@Type(t = OType.LINK)
	public static final String CREATE_BY="createdBy";
	@Type(t = OType.LINK)
	public static final String UPDATE_BY="updatedBy";
	@Type(t = OType.DATETIME)
	public static final String CREATE_AT="createdAt";
	@Type(t = OType.DATETIME)
	public static final String UPDATE_AT="updatedAt";

}
