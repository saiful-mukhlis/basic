package com.basic.managedb;

import com.basic.db.Agama;
import com.basic.db.Bos;
import com.basic.db.Grp;
import com.basic.db.JenisPekerjaan;
import com.basic.db.Logdb;
import com.basic.db.LogdbGrp;
import com.basic.db.LogdbUsr;
import com.basic.db.NumberId;
import com.basic.db.Usr;
import com.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class StartDb {

	/**
	 * membuat schema,index dan relasi pada database di jalankan setelah
	 * database di buat, jadi hanya sekali saja
	 */
	public void createSchemaDb() {
		OObjectDatabaseTx db = App.getDb();

		OClass agama = db.getMetadata().getSchema().getClass(Agama.TABLE);
		OClass bos = db.getMetadata().getSchema().getClass(Bos.TABLE);
		OClass jenisPekerjaan = db.getMetadata().getSchema()
				.getClass(JenisPekerjaan.TABLE);
		OClass numberId = db.getMetadata().getSchema().getClass(NumberId.TABLE);

		OClass grp = db.getMetadata().getSchema().getClass(Grp.TABLE);
		OClass logdbUsr = db.getMetadata().getSchema().getClass(LogdbUsr.TABLE);
		OClass logdbGrp = db.getMetadata().getSchema().getClass(LogdbGrp.TABLE);
		OClass usr = db.getMetadata().getSchema().getClass(Usr.TABLE);

		// tabel Agama
		agama.createProperty(Agama.CODE, OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);
		agama.createProperty(Agama.NAMA, OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);

		// table Jenis Pekerjaan
		jenisPekerjaan.createProperty(JenisPekerjaan.CODE, OType.STRING)
				.createIndex(OClass.INDEX_TYPE.UNIQUE);
		jenisPekerjaan.createProperty(JenisPekerjaan.NAMA, OType.STRING)
				.createIndex(OClass.INDEX_TYPE.UNIQUE);
		// tabel NumberId
		numberId.createProperty(NumberId.NAMA_TABLE, OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);

		// table Grp
		grp.createProperty(Grp.CODE, OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);
		grp.createProperty(Grp.NAME, OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);
		grp.createProperty(Grp.USRS, OType.LINKLIST);
		grp.createProperty(Grp.CREATE_BY, OType.LINK, usr);
		grp.createProperty(Grp.UPDATE_BY, OType.LINK, usr);
		
		
		logdbUsr.createProperty(LogdbUsr.USR, OType.LINK, usr);
		
		logdbGrp.createProperty(LogdbGrp.USR, OType.LINK, usr);
		

		// tabel Usr
		usr.createProperty(Usr.USERNAME, OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);
		usr.createProperty(Usr.CODE, OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);
		usr.createProperty("grp", OType.LINK, grp);
		usr.createProperty(Usr.CREATE_BY, OType.LINK, usr);
		usr.createProperty(Usr.UPDATE_BY, OType.LINK, usr);

		db.getMetadata().getSchema().save();

		db.close();

		ODatabaseDocumentTx dbd = App.getDbd();
		ODatabaseRecordThreadLocal.INSTANCE.set(dbd);
		App.getUsrDao().factoryModelFirst(dbd);
		App.getGrpDao().factoryModelFirst(dbd);
		App.getJenisPekerjaanDao().createFirst(dbd);
		dbd.close();

	}

}
