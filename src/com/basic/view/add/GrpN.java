package com.basic.view.add;


import java.util.Date;

import com.basic.comp.impl.master.GrpM;
import com.basic.dao.impl.GrpDao;
import com.basic.dao.impl.UsrDao;
import com.basic.db.Grp;
import com.basic.db.Logdb;
import com.basic.db.Usr;
import com.basic.lang.L;
import com.basic.lang.LGrp;
import com.basic.view.def.GrpC;
import com.global.App;
import com.global.DataUser;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class GrpN extends GrpC {
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = GrpM.TITLE_MENU;
		icon = GrpM.ICON_16;
		
		buildLabel(db);
		buildForm(db);
		buildPanel();
		
		setFocusEnter();
		actionReset();
	}
	
	public void afterOk() {
		name.requestFocus();
	}


	@Override
	public void buildForm(ODatabaseDocumentTx db) {
		super.buildForm(db);
		buildButton(db);
	}


	@Override
	public void buildButton(ODatabaseDocumentTx db) {
		super.buildButton(db);
		builder.append(save, 5, 7);
		builder.append(reset, 7, 7);
	}
	
	
	
	
	
	
	
	
	

	public void actionReset() {
		code.setText("AUTO");
		clearText(name);
		clearText(note);
	}
	public void setFocusEnter() {
		setFocusEnter(code, name);
		setFocusEnter(name, note);
		setFocusEnter(note, save);
		setFocusEnter(save, code);
	}
	
	
	/**
	 *  harus ada di new
	 * @return
	 */
	public ODocument createDataBaru() {
		if (DataUser.getUsr()!=null) {
			ODocument logdb=new ODocument(Logdb.TABLE);
			logdb.field(Logdb.CREATE_BY, DataUser.getUsr().field(Usr.NAMA) );
			logdb.field(Logdb.CREATE_AT, new Date(), OType.DATE);
			
			ODocument o=new ODocument(Grp.TABLE);
			GrpDao d=App.getGrpDao();
			d.setCode(o, code);
			d.setName(o, name);
			d.setNote(o, note);
			d.setLogdb(o, logdb);
			
			return o;
		}else{
			return null;
		}
		
	}
	
	
	public boolean validate(ODatabaseDocumentTx db){
		GrpDao d=App.getGrpDao();
		if (!d.vCode(db, code)) {
			return false;
		}
		if (!d.vName(db, name)) {
			return false;
		}
		
		return true;
	}
	public void save(ODatabaseDocumentTx db, ODocument model) {
		dao.save(db, model);
	}


	
}
