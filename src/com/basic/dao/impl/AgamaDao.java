package com.basic.dao.impl;

import org.basic.comp.base.TextField;
import org.basic.dao.abst.DaoAbstract;

import com.basic.db.Agama;
import com.basic.lang.LAgama;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;



public class AgamaDao extends DaoAbstract {

	public AgamaDao() {
		super(Agama.TABLE);
	}
	
	public String getCode(ODocument o){
		String tmp=o.field(Agama.CODE);
		if (tmp==null) {
			tmp="";
		}
		return tmp;
	}
	
	public String getNama(ODocument o){
		String tmp=o.field(Agama.NAMA);
		if (tmp==null) {
			tmp="";
		}
		return tmp;
	}
	
	public boolean vCode(ODatabaseDocumentTx db, TextField code){
		if (!(code.getText().trim().equals("") || code.getText().trim().equalsIgnoreCase("Auto"))) {
			long tmp=getCountByColumn(db, Agama.CODE, code.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, LAgama.CODE);
				return false;
			}
		}
		return true;
	}
	
	public boolean vCode(ODatabaseDocumentTx db, TextField code, ODocument model){
		if (!code.getText().equalsIgnoreCase((String) model.field(Agama.CODE))) {
			long tmp=getCountByColumn(db, Agama.CODE, code.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, LAgama.CODE);
				return false;
			}
			if (code.getText().trim().equals("")) {
				App.showErrorFieldEmpty(db, LAgama.CODE);
				return false;
			}
		}
		return true;
	}
	
	public boolean vNama(ODatabaseDocumentTx db, TextField nama){
		if (!validate(db, nama, LAgama.NAMA)) {
			return false;
		}
		return true;
	}
	
	public boolean vNama(ODatabaseDocumentTx db, TextField nama, ODocument model){
		if (!validate(db, nama, LAgama.NAMA)) {
			return false;
		}
		return true;
	}

	public AgamaDao setCode(ODocument o, String code){
		o.field(Agama.CODE, code);
		return this;
	}
	
	public AgamaDao setCode(ODocument o, TextField code){
		setCode(o, code.getText());
		return this;
	}
	
	public AgamaDao setNama(ODocument o, String nama){
		o.field(Agama.NAMA, nama);
		return this;
	}
	
	public AgamaDao setNama(ODocument o, TextField nama){
		setNama(o, nama.getText());
		return this;
	}
	
	public void createFirst(ODatabaseDocumentTx db){
		ODocument o=new ODocument(getClassName());
		setNama(o, "Islam");
		save(db, o);
		
		o=new ODocument(getClassName());
		setNama(o, "Kristen");
		save(db, o);
		
		o=new ODocument(getClassName());
		setNama(o, "Katolik");
		save(db, o);
		
		o=new ODocument(getClassName());
		setNama(o, "Buddha");
		save(db, o);
		
		o=new ODocument(getClassName());
		setNama(o, "Hindu");
		save(db, o);
		
		o=new ODocument(getClassName());
		setNama(o, "Khonghucu");
		save(db, o);
		
		o=new ODocument(getClassName());
		setNama(o, "Lain");
		save(db, o);
	}
	
}
