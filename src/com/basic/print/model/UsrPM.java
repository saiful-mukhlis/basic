package com.basic.print.model;

import com.global.App;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class UsrPM {
	private ODocument o;
	private ODocument o2;
	private ODocument o3;

	public ODocument getO() {
		return o;
	}

	public void setO(ODocument o) {
		this.o = o;
	}

	
	
	public ODocument getO2() {
		return o2;
	}

	public void setO2(ODocument o2) {
		this.o2 = o2;
	}

	


	public ODocument getO3() {
		return o3;
	}

	public void setO3(ODocument o3) {
		this.o3 = o3;
	}



	private String no;
	private String code;
	private String username;
	private String password;
	private String grp;
	private String nama;
	private String alamat;
	private String kota;
	private String noIdentitas;
	private String jenisIdentitas;
	private String kotaLahir;
	private String tglLahir;
	private String jenisKelamin;
	private String noTelp;
	private String noHp1;
	private String noHp2;
	private String pinBb;
	private String tglMasuk;
	private String gaji;
	private String jenisPekerjaan;
	private String pendidikanTerakhir;
	private String status;
	
	

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCode() {
		return App.getUsrDao().getCode(o);
	}

	public void setCode(String code) {
		this.nama = code;
	}

	public String getUsername() {
		return App.getUsrDao().getUsername(o);
	}

	public void setUsername(String username) {
		this.nama = username;
	}

	public String getPassword() {
		return App.getUsrDao().getPassword(o);
	}

	public void setPassword(String password) {
		this.nama = password;
	}

	public String getGrp() {
		if (o2==null) {
			return "";
		}
		return App.getUsrDao().grpToString(o2);
	}

	public void setGrp(String grp) {
		this.nama = grp;
	}

	public String getNama() {
		return App.getUsrDao().getNama(o);
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAlamat() {
		return App.getUsrDao().getAlamat(o);
	}

	public void setAlamat(String alamat) {
		this.nama = alamat;
	}

	public String getKota() {
		return App.getUsrDao().getKota(o);
	}

	public void setKota(String kota) {
		this.nama = kota;
	}

	public String getNoIdentitas() {
		return App.getUsrDao().getNoIdentitas(o);
	}

	public void setNoIdentitas(String noIdentitas) {
		this.nama = noIdentitas;
	}

	public String getJenisIdentitas() {
		return App.getUsrDao().getJenisIdentitas(o);
	}

	public void setJenisIdentitas(String jenisIdentitas) {
		this.nama = jenisIdentitas;
	}

	public String getKotaLahir() {
		return App.getUsrDao().getKotaLahir(o);
	}

	public void setKotaLahir(String kotaLahir) {
		this.nama = kotaLahir;
	}

	public String getTglLahir() {
		return App.getUsrDao().tglLahirToString(o);
	}

	public void setTglLahir(String tglLahir) {
		this.nama = tglLahir;
	}

	public String getJenisKelamin() {
		return App.getUsrDao().jenisKelaminToString(o);
	}

	public void setJenisKelamin(String jenisKelamin) {
		this.nama = jenisKelamin;
	}

	public String getNoTelp() {
		return App.getUsrDao().getNoTelp(o);
	}

	public void setNoTelp(String noTelp) {
		this.nama = noTelp;
	}

	public String getNoHp1() {
		return App.getUsrDao().getNoHp1(o);
	}

	public void setNoHp1(String noHp1) {
		this.nama = noHp1;
	}

	public String getNoHp2() {
		return App.getUsrDao().getNoHp2(o);
	}

	public void setNoHp2(String noHp2) {
		this.nama = noHp2;
	}

	public String getPinBb() {
		return App.getUsrDao().getPinBb(o);
	}

	public void setPinBb(String pinBb) {
		this.nama = pinBb;
	}


	public String getTglMasuk() {
		return App.getUsrDao().tglMasukToString(o);
	}

	public void setTglMasuk(String tglMasuk) {
		this.nama = tglMasuk;
	}

	public String getGaji() {
		return App.getUsrDao().gajiToString(o);
	}

	public void setGaji(String gaji) {
		this.nama = gaji;
	}

	public String getJenisPekerjaan() {
		if (o3==null) {
			return "";
		}
		return App.getUsrDao().jenisPekerjaanToString(o3);
	}

	public void setJenisPekerjaan(String jenisPekerjaan) {
		this.nama = jenisPekerjaan;
	}

	public String getPendidikanTerakhir() {
		return App.getUsrDao().getPendidikanTerakhir(o);
	}

	public void setPendidikanTerakhir(String pendidikanTerakhir) {
		this.nama = pendidikanTerakhir;
	}

	public String getStatus() {
		return App.getUsrDao().statusToString(o);
	}

	public void setStatus(String status) {
		this.nama = status;
	}
}
