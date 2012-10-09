package com.basic.dao.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import org.basic.dao.abst.DaoAbstract;

import com.basic.db.Grp;
import com.basic.db.Logdb;
import com.basic.db.LogdbGrp;
import com.basic.db.Usr;
import com.basic.lang.LGrp;
import com.global.App;
import com.global.DataUser;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.tx.OTransaction.TXTYPE;

public class GrpDao extends DaoAbstract {

	public GrpDao() {
		super(Grp.TABLE);
	}

	public GrpDao setCode(ODocument o, String code) {
		o.field(Grp.CODE, code);
		return this;
	}

	public GrpDao setCode(ODocument o, TextField code) {
		setCode(o, code.getText());
		return this;
	}

	public String getCode(ODocument o) {
		String tmp = o.field(Grp.CODE);
		if (tmp == null) {
			tmp = "";
		}
		return tmp;
	}

	public boolean vCode(ODatabaseDocumentTx db, TextField code) {
		if (!(code.getText().trim().equals("") || code.getText().trim()
				.equalsIgnoreCase("Auto"))) {
			long tmp = getCountByColumn(db, Grp.CODE, code.getText());
			if (tmp > 0) {
				App.showErrorDataSudahAda(db, LGrp.CODE);
				return false;
			}
		}
		return true;
	}

	public boolean vCode(ODatabaseDocumentTx db, TextField code, ODocument model) {
		if (!code.getText().equalsIgnoreCase((String) model.field(Grp.CODE))) {
			long tmp = getCountByColumn(db, Grp.CODE, code.getText());
			if (tmp > 0) {
				App.showErrorDataSudahAda(db, LGrp.CODE);
				return false;
			}
			if (code.getText().trim().equals("")) {
				App.showErrorFieldEmpty(db, LGrp.CODE);
				return false;
			}
		}
		return true;
	}

	public GrpDao setName(ODocument o, String name) {
		o.field(Grp.NAME, name);
		return this;
	}

	public GrpDao setName(ODocument o, TextField name) {
		setName(o, name.getText());
		return this;
	}

	public String getName(ODocument o) {
		String tmp = o.field(Grp.NAME);
		if (tmp == null) {
			tmp = "";
		}
		return tmp;
	}

	public boolean vName(ODatabaseDocumentTx db, TextField name) {
		if (!validate(db, name, LGrp.NAME)) {
			return false;
		}
		long tmp = App.getGrpDao().getCountByColumn(db, Grp.NAME,
				name.getText());
		if (tmp > 0) {
			App.showErrorDataSudahAda(db, LGrp.NAME);
			return false;
		}
		return true;
	}

	public boolean vName(ODatabaseDocumentTx db, TextField name, ODocument model) {
		if (!validate(db, name, LGrp.NAME)) {
			return false;
		}
		if (!name.getText().equalsIgnoreCase((String) model.field(Grp.NAME))) {
			long tmp = App.getUsrDao().getCountByColumn(db, Grp.NAME,
					name.getText());
			if (tmp > 0) {
				App.showErrorDataSudahAda(db, LGrp.NAME);
				return false;
			}
			if (name.getText().trim().equals("")) {
				App.showErrorFieldEmpty(db, LGrp.NAME);
				return false;
			}
		}
		return true;
	}

	public GrpDao setNote(ODocument o, String note) {
		o.field(Grp.NOTE, note);
		return this;
	}

	public GrpDao setNote(ODocument o, TextArea note) {
		setNote(o, note.getText());
		return this;
	}

	public String getNote(ODocument o) {
		String tmp = o.field(Grp.NOTE);
		if (tmp == null) {
			tmp = "";
		}
		return tmp;
	}

	public boolean vNote(ODatabaseDocumentTx db, TextField note) {
		if (!validate(db, note, LGrp.NOTE)) {
			return false;
		}
		return true;
	}

	public boolean vNote(ODatabaseDocumentTx db, TextField note, ODocument model) {
		if (!validate(db, note, LGrp.NOTE)) {
			return false;
		}
		return true;
	}

	public GrpDao setKey(ODocument o, String key) {
		o.field(Grp.KEY, key);
		return this;
	}

	public GrpDao setKey(ODocument o, TextField key) {
		setKey(o, key.getText());
		return this;
	}

	public String getKey(ODocument o) {
		String tmp = o.field(Grp.KEY);
		if (tmp == null) {
			tmp = "";
		}
		return tmp;
	}

	public GrpDao setUsrs(ODocument o, HashSet<ODocument> users) {
		if (users == null) {
			users = new HashSet<>();
		}
		o.field(Grp.USRS, users, OType.LINKSET);
		return this;
	}

	public GrpDao setCreateBy(ODocument o, ODocument usr) {
		if (usr != null) {
			o.field(Grp.CREATE_BY, usr, OType.LINK);
		}
		return this;
	}
	
	public String getCreateAt(ODocument o){
		Date tmp=o.field(Grp.CREATE_AT);
		if (tmp==null) {
			return "";
		}
		return App.dateTimeFormat.format(tmp);
	}
	
	public String getUpdateAt(ODocument o){
		Date tmp=o.field(Grp.UPDATE_AT);
		if (tmp==null) {
			return "";
		}
		return App.dateTimeFormat.format(tmp);
	}
	
	public ODocument getCreateBy(ODocument o){
		if (o.isLazyLoad()) {
			return o.field(Grp.CREATE_BY);
		}else{
			ORecordId id=o.field(Grp.CREATE_BY);
			if (id!=null) {
				ODatabaseDocumentTx db = App.getDbd();
				ODatabaseRecordThreadLocal.INSTANCE.set(db);
				ODocument tmp= getOneByRid(db, id.toString());
				db.close();
				return tmp;
			}
			return null;
		}
	}
	
	public String createByToString(ODocument o){
		ODocument tmp=getCreateBy(o);
		if (tmp==null) {
			return "";
		}
		return App.getUsrDao().getNama(tmp);
	}
	
	
	
	public ODocument getUpdateBy(ODocument o){
		if (o.isLazyLoad()) {
			return o.field(Grp.UPDATE_BY);
		}else{
			ORecordId id=o.field(Grp.UPDATE_BY);
			if (id!=null) {
				ODatabaseDocumentTx db = App.getDbd();
				ODatabaseRecordThreadLocal.INSTANCE.set(db);
				ODocument tmp= getOneByRid(db, id.toString());
				db.close();
				return tmp;
			}
			return null;
		}
	}
	
	public String updateByToString(ODocument o){
		ODocument tmp=getUpdateBy(o);
		if (tmp==null) {
			return "";
		}
		return App.getUsrDao().getNama(tmp);
	}
	
	
	public ODocument getCreateBy2(ODocument o){
		String json=o.field(Grp.CREATE_BY2);
		if (json==null || json.equalsIgnoreCase("")) {
			return null;
		}
		ODocument tmp=new ODocument(getClassName());
		tmp.fromJSON(json);
		return tmp;
	}
	
	public ODocument getUpdateBy2(ODocument o){
		String json=o.field(Grp.UPDATE_BY2);
		if (json==null || json.equalsIgnoreCase("")) {
			return null;
		}
		ODocument tmp=new ODocument(getClassName());
		tmp.fromJSON(json);
		return tmp;
	}

	public GrpDao setCreateAt(ODocument o, Date createAt) {
		if (createAt != null) {
			o.field(Grp.CREATE_AT, createAt, OType.DATETIME);
		}
		return this;
	}

	public GrpDao setUpdateBy(ODocument o, ODocument usr) {
		if (usr != null) {
			o.field(Grp.UPDATE_BY, usr, OType.LINK);
		}
		return this;
	}

	public GrpDao setUpdateAt(ODocument o, Date updateAt) {
		if (updateAt != null) {
			o.field(Grp.UPDATE_AT, updateAt, OType.DATETIME);
		}
		return this;
	}

	// public ODocument factoryModel(String code, String name, String note) {
	// ODocument doc = factoryModel(name, code, note, (String) DataUser
	// .getUsr().field(Usr.USERNAME), new Date());
	// doc.field(Grp.CODE, code);
	// doc.field(Grp.NAME, name);
	// doc.field(Grp.NOTE, note);
	// return doc;
	// }

	// public ODocument factoryModel(String name, String code, String note,
	// String createdBy, Date createdAt) {
	// ODocument doc = new ODocument(getClassName());
	// doc.field(Grp.NAME, name);
	// doc.field(Grp.CODE, code);
	// doc.field(Grp.NOTE, note);
	// ODocument logdb = new ODocument(Logdb.TABLE);
	// logdb.field(Logdb.CREATE_BY, createdBy);
	// logdb.field(Logdb.CREATE_AT, createdAt, OType.DATE);
	// doc.field(Grp.LOGDB, logdb, OType.EMBEDDED);
	// return doc;
	//
	// }

	public void factoryModelFirst(ODatabaseDocumentTx db) {
		if (getCount(db) == 0) {

			ODocument usr = App.getUsrDao().getOne(db, Usr.USERNAME, "admin");
			if (usr == null) {
				App.getUsrDao().factoryModelFirst(db);
				usr = App.getUsrDao().getOne(db, Usr.USERNAME, "admin");
			}

			ODocument admin = new ODocument(getClassName());
			setName(admin, "Admin");
			setNote(admin, "Hak Akses untuk Super User");
			StringBuffer tmp = new StringBuffer();
			for (int i = 1; i < 50; i++) {
				tmp.append("x" + i + "x");
			}
			admin.field(Grp.KEY, tmp.toString());
			setCreateBy(admin, usr);
			setCreateAt(admin, new Date());

			HashSet<ODocument> usrs = new HashSet<>();
			usrs.add(usr);
			setUsrs(admin, usrs);

			ODocument pegawai = new ODocument(getClassName());
			setName(pegawai, "Pegawai");
			setNote(pegawai, "Hak Akses untuk Pegawai");
			setCreateBy(pegawai, usr);
			setCreateAt(pegawai, new Date());

			try {
				db.begin(TXTYPE.OPTIMISTIC);

				save(db, admin);
				// memberi group pada admin
				App.getUsrDao().setGrp(usr, admin);
				usr.save();

				save(db, pegawai);
				db.commit();

			} catch (Exception e) {
				db.rollback();
			}

		}
	}

	public long modelIsExist(ODatabaseDocumentTx db, String name, String code) {
		return getCountByColumn(db, "name", name, "code", code, "or");
	}

	public boolean beforeDelete(ODatabaseDocumentTx db, ODocument o) {
		App.getUsrDao().delByColoumn(db, Usr.GRP, o.getIdentity());
		return true;
	}

	@Override
	public String getNameFielsToString() {
		return Grp.NAME;
	}

	@Override
	public ODocument update(ODatabaseDocumentTx db, ODocument model,
			String jsonOld) {
		
		try{
			  db.begin(TXTYPE.OPTIMISTIC);
			  App.getLogdbGrpDao().save(DataUser.getUsr(), LogdbGrpDao.EDIT, jsonOld, model.toJSON());
			  model.save();
			  db.commit();
			}catch( Exception e ){
			  db.rollback();
			}

		
		return model;
	}

	@Override
	public ODocument save(ODatabaseDocumentTx db, ODocument model) {
		try{
			  db.begin(TXTYPE.OPTIMISTIC);
			  App.getLogdbGrpDao().save(DataUser.getUsr(), LogdbGrpDao.ADD, "", model.toJSON());
			  super.save(db, model);
			  db.commit();
			}catch( Exception e ){
			  db.rollback();
			}

		
		return model;
	}
	
	
	
	

}
