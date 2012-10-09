package com.basic.view.add;



import java.util.Date;

import org.basic.comp.abst.DialogDefault;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ComboBox;
import org.basic.comp.base.DatePicker;
import org.basic.comp.base.PasswordField;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import org.basic.comp.base.TextFieldAmount;
import org.basic.comp.model.ODocumentToString;

import com.basic.comp.impl.master.GrpM;
import com.basic.comp.impl.master.UsrM;
import com.basic.dao.impl.GrpDao;
import com.basic.dao.impl.UsrDao;
import com.basic.db.Usr;
import com.basic.lang.LGrp;
import com.basic.lang.LUsr;
import com.global.App;
import com.global.DataUser;
import com.global.util.UtilAccount;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;



public class UsrDN extends DialogDefault {
	protected TextField code;
	protected TextField username;
	protected TextField nama;
	protected TextArea alamat;
	protected ScrollPane salamat;
	protected TextField kota;
	protected TextField noIdentitas;
	protected TextField jenisIdentitas;
	protected TextField kotaLahir;
	protected TextField noTelp;
	protected TextField noHp1;
	protected TextField noHp2;
	protected TextField pinBb;
	protected TextFieldAmount gaji;
	protected TextField pendidikanTerakhir;
	protected PasswordField password;
	protected PasswordField ulang;
	protected DatePicker tglLahir;
	protected DatePicker tglMasuk;
	protected ComboBox jenisKelamin;
	protected ComboBox jenisPekerjaan;
	protected ComboBox status;
	protected ComboBox grp;
	
	protected ODocumentToString [] jenisPekerjaanModel;
	protected ODocumentToString [] grpModel;
	
	
	
	@Override
	public void actionReset() {
		code.setText("Auto");
		username.setText("");
		grp.setSelectedIndex(0);
		nama.setText("");
		alamat.setText("");
		
		kota.setText("");
		noIdentitas.setText("");
		jenisIdentitas.setText("");
		kotaLahir.setText("");
		tglLahir.setDate(new Date());
		jenisKelamin.setSelectedIndex(0);
		noTelp.setText("");
		noHp1.setText("");
		noHp2.setText("");
		pinBb.setText("");
		tglMasuk.setDate(new Date());
		gaji.setText("");
		jenisPekerjaan.setSelectedIndex(0);
		pendidikanTerakhir.setText("");
		status.setSelectedIndex(0);
		requestDefaultFocus();
	}

	
	
	@Override
	public void requestDefaultFocus() {
		username.requestFocus();
	}



	public void initComponent(ODatabaseDocumentTx db) {
		code=new TextField();
		username=new TextField();
		nama=new TextField();
		alamat=new TextArea();
		salamat=new ScrollPane(alamat);
		
		kota=new TextField();
		noIdentitas=new TextField();
		jenisIdentitas=new TextField();
		kotaLahir=new TextField();
		noTelp=new TextField();
		noHp1=new TextField();
		noHp2=new TextField();
		pinBb=new TextField();
		gaji=new TextFieldAmount();
		pendidikanTerakhir=new TextField();
		
		password=new PasswordField();
		ulang=new PasswordField();
		tglLahir=new DatePicker();
		tglMasuk=new DatePicker();

		jenisPekerjaanModel=App.getUsrDao().getJenisPekerjaanData(db);
		jenisKelamin=new ComboBox(App.getUsrDao().getJenisKelaminData());
		status=new ComboBox(App.getUsrDao().getStatusData());
		
		grpModel=App.getUsrDao().getGrpData(db);
		grp=new ComboBox(grpModel);
		jenisPekerjaan=new ComboBox(jenisPekerjaanModel);
		
	}
	
	
	
	
	@Override
	public void setFocusEnter() {
		setFocusEnter(code, username);
		setFocusEnter(username, password);
		setFocusEnter(password, ulang);
		setFocusEnter(ulang, grp);
//		setFocusEnter(status, grp);
		setFocusEnter(grp, status);
		setFocusEnter(status, nama);

		setFocusEnter(nama, jenisIdentitas);
		setFocusEnter(jenisIdentitas, noIdentitas);
		setFocusEnter(noIdentitas, kotaLahir);
		setFocusEnter(kotaLahir, tglLahir.getEditor());
		setFocusEnter(tglLahir.getEditor(), jenisKelamin);
		setFocusEnter(jenisKelamin, alamat);
		setFocusEnter(alamat, kota);
		setFocusEnter(kota, noTelp);
		setFocusEnter(noTelp, pinBb);
		setFocusEnter(pinBb, noHp1);
		setFocusEnter(noHp1, noHp2);
		setFocusEnter(noHp2, pendidikanTerakhir);
		setFocusEnter(pendidikanTerakhir, tglMasuk.getEditor());

		setFocusEnter(tglMasuk.getEditor(), gaji);
		setFocusEnter(gaji, jenisPekerjaan);
		setFocusEnter(jenisPekerjaan, save);
		
//		setFocusEnter(save, reset);
		setFocusEnter(reset, code);
	}


	@Override
	public void init(ODatabaseDocumentTx db) {
		icon=UsrM.ICON_48;
		super.init(db);
		labelTitle.setText("Tambah Pegawai");
		labelNote.setText("Isi data dengan benar");
		initComponent(db);
		buildForm(db);
	}

	public void buildForm(ODatabaseDocumentTx db) {
		initComponent(db);
		

		

	
		
		StringBuilder col=new StringBuilder();
		StringBuilder row=new StringBuilder();
		col.append("30px,");
		col.append("r:p,10px,f:100px:g,");
		col.append("30px,");
		col.append("r:p,10px,f:100px:g,");
		col.append("30px,");
		col.append("r:p,10px,f:100px:g,");
		col.append("30px,");
		col.append("r:p,10px,f:100px:g,");
		col.append("30px,");
		
		row.append("15dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		//row.append("f:40dlu:g,15dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		
		FormLayout l = new FormLayout(col.toString(),row.toString());

		//l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
		b.append(LGrp.CODE, code, 2, 2, 1);
		b.append( LUsr.USERNAME, username, 2, 4, 5);
		
		b.append( LUsr.PASSWORD, password, 2, 6, 5);
		b.append( LUsr.ULANG, ulang, 2, 8, 5);
		
		b.append( LUsr.GRP, grp, 2, 10, 3);
		b.append( LUsr.STATUS, status, 2, 12, 3);

		b.append( LUsr.TGL_MASUK, tglMasuk, 2, 16, 1);
		b.append( LUsr.GAJI, gaji, 2, 18, 5);
		b.append( LUsr.JENIS_PEKERJAAN, jenisPekerjaan, 2, 20, 3);
		
		
		
		
		b.append( LUsr.NAMA, nama, 10, 2, 5);
		b.append( LUsr.JENIS_IDENTITAS, jenisIdentitas, 10, 4, 1);
		b.append( LUsr.NO_IDENTITAS, noIdentitas, 14, 4, 1);
		b.append( LUsr.KOTA_LAHIR, kotaLahir, 10, 6, 1);
		b.append( LUsr.TGL_LAHIR, tglLahir , 14, 6, 1);
		b.append( LUsr.JENIS_KELAMIN, jenisKelamin, 10, 8, 3);
		b.append( LUsr.ALAMAT, salamat, 10, 10, 5, 5);
		b.append( LUsr.KOTA, kota, 10, 16, 5);
		b.append( LUsr.NO_TELP, noTelp, 10, 18, 1);
		b.append( LUsr.PIN_BB, pinBb, 14, 18, 1);
		b.append( LUsr.NO_HP1, noHp1, 10, 20, 1);
		b.append( LUsr.NO_HP2, noHp2, 14, 20, 1);
		b.append( LUsr.PENDIDIKAN_TERAKHIR, pendidikanTerakhir, 10, 22, 5);
		
		
		panelForm=b.getPanel();

	}


	@Override
	public void save(ODatabaseDocumentTx db) {
		UsrDao d=App.getUsrDao();
		model=new ODocument(d.getClassName());
		d.setCode(model, code).
		setUsername(model, username).
		setGrp(model, ((ODocumentToString)grp.getSelectedItem()).getO()).
		setNama(model, nama).
		setAlamat(model, alamat).
		setKota(model, kota).
		setNoIdentitas(model, noIdentitas).
		setJenisIdentitas(model, jenisIdentitas).
		setKotaLahir(model, kotaLahir).
		setTglLahir(model, tglLahir.getDate()).
		setJenisKelamin(model, jenisKelamin.getSelectedIndex()).
		setNoTelp(model, noTelp).
		setNoHp1(model, noHp1).
		setNoHp2(model, noHp2).
		setPinBb(model, pinBb).
		setTglMasuk(model, tglMasuk.getDate()).
		setGaji(model, gaji).
		setJenisPekerjaan(model, ((ODocumentToString)grp.getSelectedItem()).getO()).
		setPendidikanTerakhir(model, pendidikanTerakhir).
		setStatus(model, status.getSelectedIndex());
		UtilAccount u=new UtilAccount();
		String p;
		try {
			p = u.md5(new String(password.getPassword()));
			d.setPassword(model,p );
		} catch (Exception e) {
			App.printErr(e);
		}
		
		d.setCreateAt(model, new Date());
		d.setCreateBy(model, DataUser.getUsr());
		d.save(db, model);
	}

	@Override
	public boolean validate(ODatabaseDocumentTx db) {
		
		UsrDao d=App.getUsrDao();
		
		if (d.vCode(db, code) && d.vUsername(db, username) && d.vPassword(db, password, ulang) && d.vNama(db, nama)) {
			if (grp.getSelectedIndex()==0) {
				App.showErrorFieldEmpty(db, LUsr.GRP);
				grp.requestFocus();
				return false;
			}
			
			
			if (status.getSelectedIndex()==0) {
				App.showErrorFieldEmpty(db, LUsr.STATUS);
				status.requestFocus();
				return false;
			}
			
			return true;
		}
		
		return false;
		
		
	}
	
}
