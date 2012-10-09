package com.basic.view.add;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;

import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ComboBox;
import org.basic.comp.base.DatePicker;
import org.basic.comp.base.PasswordField;
import org.basic.comp.listener.WidgetInterface;
import org.basic.comp.model.ODocumentToString;

import com.basic.comp.impl.master.UsrM;
import com.basic.dao.impl.UsrDao;
import com.basic.db.Logdb;
import com.basic.db.Usr;
import com.basic.icon.IconBase;
import com.basic.lang.L;
import com.basic.lang.LUsr;
import com.basic.view.def.UsrC;
import com.global.App;
import com.global.DataUser;
import com.global.util.UtilAccount;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class UsrN extends UsrC {
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
	
	
	public void initComponent(ODatabaseDocumentTx db){
		super.initComponent(db);
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
		
		save=new JButton(L.save);
		reset=new JButton(L.reset);
		
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				actionSave();
			}
		});
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				actionReset();
			}
		});
				
	}
	
	
	
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		initComponent(db);
		title = UsrM.TITLE_ADD;
		icon = IconBase.ADD;
		
		buildLabel(db);
		buildForm(db);
		buildPanel();
		
		setFocusEnter();
		actionReset();
	}
	


	public void buildForm(ODatabaseDocumentTx db) {
		
		Double tmp = App.getW()*lebar;
		layout = new FormLayout(
				//"r:p,   	10px,   	f:max("+tmp.intValue()+"px;p):g,  10px,   	f:max(80px;p),  10px,   	f:max(80px;p),     	10px," +
						"r:p,3dlu,	f:max("+tmp.intValue()+"px;p):g,3dlu,	f:max("+tmp.intValue()+"px;p):g,3dlu,	10dlu,	r:p,3dlu,	f:max("+tmp.intValue()+"px;p):g,3dlu,	r:p,3dlu,	f:max("+tmp.intValue()+"px;p):g,3dlu,",
				

				"p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,  p,3dlu,   f:20dlu,3dlu,  p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,  p,3dlu,  p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,  p,3dlu,  p,3dlu ,10dlu");

		 layout.setColumnGroups(new int[][] { { 3,5,10,14 } });
		builder = new FormBuilder(layout, true);

		builder.append( LUsr.CODE, code, 1, 1, 1);
		builder.append( LUsr.USERNAME, username, 1, 3, 3);
		
		builder.append( LUsr.PASSWORD, password, 1, 5, 3);
		builder.append( LUsr.ULANG, ulang, 1, 7, 3);
		
		builder.append( LUsr.GRP, grp, 1, 9, 1);
		builder.append( LUsr.STATUS, status, 1, 11, 1);

		builder.append( LUsr.TGL_MASUK, tglMasuk, 1, 15, 1);
		builder.append( LUsr.GAJI, gaji, 1, 17, 3);
		builder.append( LUsr.JENIS_PEKERJAAN, jenisPekerjaan, 1, 19, 1);
		
		
		
		
		builder.append( LUsr.NAMA, nama, 8, 1, 5);
		builder.append( LUsr.JENIS_IDENTITAS, jenisIdentitas, 8, 3, 1);
		builder.append( LUsr.NO_IDENTITAS, noIdentitas, 12, 3, 1);
		builder.append( LUsr.KOTA_LAHIR, kotaLahir, 8, 5, 1);
		builder.append( LUsr.TGL_LAHIR, tglLahir , 12, 5, 1);
		builder.append( LUsr.JENIS_KELAMIN, jenisKelamin, 8, 7, 1);
		builder.append( LUsr.ALAMAT, salamat, 8, 9, 5, 5);
		builder.append( LUsr.KOTA, kota, 8, 15, 5);
		builder.append( LUsr.NO_TELP, noTelp, 8, 17, 1);
		builder.append( LUsr.PIN_BB, pinBb, 12, 17, 1);
		builder.append( LUsr.NO_HP1, noHp1, 8, 19, 1);
		builder.append( LUsr.NO_HP2, noHp2, 12, 19, 1);
		builder.append( LUsr.PENDIDIKAN_TERAKHIR, pendidikanTerakhir, 8, 21, 5);
//		
//		
//		Double tmp = App.getW()*lebar;
//		layout = new FormLayout(
//				"r:p,   	10px,   	f:max("+tmp.intValue()+"px;p):g,  10px,   	f:max(80px;p),  10px,   	f:max(80px;p),     	10px,",
//
//				"p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,  p,3dlu,  p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,  p,3dlu,  p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,  p,3dlu,  p,3dlu ,p ,10dlu");
//
//		 layout.setColumnGroups(new int[][] { { 5, 7 } });
//		builder = new FormBuilder(layout, true);
//
//		builder.append( LUsr.CODE, code, 1, 1, 5);
//		builder.append( LUsr.USERNAME, username, 1, 3, 5);
//		
//		builder.append( LUsr.PASSWORD, password, 1, 5, 5);
//		builder.append( LUsr.ULANG, ulang, 1, 7, 5);
//		
//		builder.append( LUsr.GROUP, grp, 1, 9, 5);
//		builder.append( LUsr.NAMA, nama, 1, 11, 5);
//		builder.append( LUsr.ALAMAT, salamat, 1, 13, 5);
//		builder.append( LUsr.KOTA, kota, 1, 15, 5);
//		builder.append( LUsr.NO_IDENTITAS, noIdentitas, 1, 17, 5);
//		builder.append( LUsr.JENIS_IDENTITAS, jenisIdentitas, 1, 19, 5);
//		builder.append( LUsr.KOTA_LAHIR, kotaLahir, 1, 21, 5);
//		builder.append( LUsr.TGL_LAHIR, tglLahir , 1, 23, 5);
//		builder.append( LUsr.JENIS_KELAMIN, jenisKelamin, 1, 25, 5);
//		builder.append( LUsr.NO_TELP, noTelp, 1, 27, 5);
//		builder.append( LUsr.NO_HP1, noHp1, 1, 29, 5);
//		builder.append( LUsr.NO_HP2, noHp2, 1, 31, 5);
//		builder.append( LUsr.PIN_BB, pinBb, 1, 33, 5);
//		builder.append( LUsr.TGL_MASUK, tglMasuk, 1, 35, 5);
//		builder.append( LUsr.GAJI, gaji, 1, 37, 5);
//		builder.append( LUsr.JENIS_PEKERJAAN, jenisPekerjaan, 1, 39, 5);
//		builder.append( LUsr.PENDIDIKAN_TERAKHIR, pendidikanTerakhir, 1, 41, 5);
//		builder.append( LUsr.STATUS, status, 1, 43, 5);
		
		FormLayout layout2 = new FormLayout(
				"p:g,  10px:g,   	f:max(80px;p),  10px,   	f:max(80px;p),     	10px,",

				"p,3dlu,     p,3dlu");

		 layout2.setColumnGroups(new int[][] { { 3, 5 } });
		 FormBuilder builder2 = new FormBuilder(layout2, true);
		
		buildButton(db);
		
		builder2.append( save, 3, 1);
		builder2.append( reset, 5, 1);
		//builder2.getPanel().setBackground(Color.WHITE);
		builder.append( builder2.getPanel(), 1, 23, 14, 1);

	}
	
	public void setFocusEnter(){
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
	public void buildButton(ODatabaseDocumentTx db) {
		super.buildButton(db);
		
	}
	
	
	
	
	
	
	
	public void afterSave(ODocument tmp){
		for (WidgetInterface w: widgets) {
			w.addModel(tmp);
		}
		for (WidgetInterface w: widgets) {
			w.addModel(((ODocumentToString) grp.getSelectedItem()).getO());
		}
		for (WidgetInterface w: widgets) {
			w.addModel(((ODocumentToString) jenisPekerjaan.getSelectedItem()).getO());
		}
		actionReset();
	}
	

	public void actionReset(){
		resetContentComponent();
		code.setText("Auto");
		jenisKelamin.setSelectedIndex(0);
		jenisPekerjaan.setSelectedIndex(0);
		status.setSelectedIndex(1);
		grp.setSelectedItem(0);
		requestDefaultFocus();
		
	}

	

	
	
	/**
	 *  harus ada di new
	 * @return
	 */
	public ODocument createDataBaru() {
		
		ODocument logdb=new ODocument(Logdb.TABLE);
		logdb.field(Logdb.CREATE_BY, DataUser.getUsr().field(Usr.NAMA) );
		logdb.field(Logdb.CREATE_AT, new Date(), OType.DATE);
		
		ODocument o=new ODocument(Usr.TABLE);
		UsrDao d=App.getUsrDao();
		d.setCode(o, code).
		setUsername(o, username).
		setGrp(o, ((ODocumentToString)grp.getSelectedItem()).getO()).
		setNama(o, nama).
		setAlamat(o, alamat).
		setKota(o, kota).
		setNoIdentitas(o, noIdentitas).
		setJenisIdentitas(o, jenisIdentitas).
		setKotaLahir(o, kotaLahir).
		setTglLahir(o, tglLahir.getDate()).
		setJenisKelamin(o, jenisKelamin.getSelectedIndex()).
		setNoTelp(o, noTelp).
		setNoHp1(o, noHp1).
		setNoHp2(o, noHp2).
		setPinBb(o, pinBb).
		setTglMasuk(o, tglMasuk.getDate()).
		setGaji(o, gaji).
		setJenisPekerjaan(o, ((ODocumentToString)grp.getSelectedItem()).getO()).
		setPendidikanTerakhir(o, pendidikanTerakhir).
		setStatus(o, status.getSelectedIndex());
		UtilAccount u=new UtilAccount();
		String p;
		try {
			p = u.md5(new String(password.getPassword()));
			d.setPassword(o,p );
		} catch (Exception e) {
			App.printErr(e);
		}
		
		d.setLogdb(o, logdb);
		
		return o;
	}
	public boolean validate(ODatabaseDocumentTx db){
		
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
	public void save(ODatabaseDocumentTx db, ODocument model) {
		App.getUsrDao().save(db, model);
	}

	public void afterOk() {
		nama.requestFocus();
	}

	
}
