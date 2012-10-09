package com.basic.dao.impl;

import org.basic.comp.base.TextField;
import org.basic.dao.abst.DaoAbstract;

import com.basic.db.JenisPekerjaan;
import com.basic.lang.LJenisPekerjaan;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;



public class JenisPekerjaanDao extends DaoAbstract {

	public JenisPekerjaanDao() {
		super(JenisPekerjaan.TABLE);
	}

	
	public String getCode(ODocument o){
		String tmp=o.field(JenisPekerjaan.CODE);
		if (tmp==null) {
			tmp="";
		}
		return tmp;
	}
	
	public String getNama(ODocument o){
		String tmp=o.field(JenisPekerjaan.NAMA);
		if (tmp==null) {
			tmp="";
		}
		return tmp;
	}
	
	public boolean vCode(ODatabaseDocumentTx db, TextField code){
		if (!(code.getText().trim().equals("") || code.getText().trim().equalsIgnoreCase("Auto"))) {
			long tmp=getCountByColumn(db, JenisPekerjaan.CODE, code.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, LJenisPekerjaan.CODE);
				return false;
			}
		}
		return true;
	}
	
	public boolean vCode(ODatabaseDocumentTx db, TextField code, ODocument model){
		if (!code.getText().equalsIgnoreCase((String) model.field(JenisPekerjaan.CODE))) {
			long tmp=getCountByColumn(db, JenisPekerjaan.CODE, code.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, LJenisPekerjaan.CODE);
				return false;
			}
			if (code.getText().trim().equals("")) {
				App.showErrorFieldEmpty(db, LJenisPekerjaan.CODE);
				return false;
			}
		}
		return true;
	}
	
	public boolean vNama(ODatabaseDocumentTx db, TextField nama){
		if (!validate(db, nama, LJenisPekerjaan.NAMA)) {
			return false;
		}
			long tmp=getCountByColumn(db, LJenisPekerjaan.NAMA, nama.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, LJenisPekerjaan.NAMA);
				return false;
			}
		return true;
	}
	
	public boolean vNama(ODatabaseDocumentTx db, TextField nama, ODocument model){
		if (!validate(db, nama, LJenisPekerjaan.NAMA)) {
			return false;
		}
		if (!nama.getText().equalsIgnoreCase((String) model.field(LJenisPekerjaan.NAMA))) {
			long tmp=App.getUsrDao().getCountByColumn(db, LJenisPekerjaan.NAMA, nama.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, LJenisPekerjaan.NAMA);
				return false;
			}
			if (nama.getText().trim().equals("")) {
				App.showErrorFieldEmpty(db, LJenisPekerjaan.NAMA);
				return false;
			}
		}
		return true;
	}

	public JenisPekerjaanDao setCode(ODocument o, String code){
		o.field(JenisPekerjaan.CODE, code);
		return this;
	}
	
	public JenisPekerjaanDao setCode(ODocument o, TextField code){
		setCode(o, code.getText());
		return this;
	}
	
	public JenisPekerjaanDao setNama(ODocument o, String nama){
		o.field(JenisPekerjaan.NAMA, nama);
		return this;
	}
	
	public JenisPekerjaanDao setNama(ODocument o, TextField nama){
		setNama(o, nama.getText());
		return this;
	}
	
	
	
	
	
	public void createFirst(ODatabaseDocumentTx db){
		ODocument o=new ODocument(getClassName());
		setNama(o, "Administrator");
		save(db, o);
		
		o=new ODocument(getClassName());
		setNama(o, "Admin");
		save(db, o);
		
		o=new ODocument(getClassName());
		setNama(o, "Kasir");
		save(db, o);
		
		o=new ODocument(getClassName());
		setNama(o, "Seles");
		save(db, o);
		
		o=new ODocument(getClassName());
		setNama(o, "Bagian Umum");
		save(db, o);
		
	}
	
	
	@Override
	public String getNameFielsToString() {
		return JenisPekerjaan.NAMA;
	}
}
