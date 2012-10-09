package com.basic.dao.impl;


import org.basic.dao.abst.DaoAbstract;

import com.basic.db.NumberId;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;

public class NumberIdDao extends DaoAbstract {

	public NumberIdDao() {
		super(NumberId.TABLE);
	}
	

	public ODocument getLangByNamaTable(ODatabaseDocumentTx db, String value){
		return getOne(db, NumberId.NAMA_TABLE, value);
	}

	public ODocument createContentDefaultModel(ODatabaseDocumentTx db, String nameTable, String prefix, String format, boolean useFormat) {
		if (getCountByNameTable(db, nameTable)==0) {
			ODocument tmp=factoryLang(nameTable, 1, 0, 1, 1, prefix, format, useFormat);
					tmp.save();
					return tmp;
		}else{
			return null;
		}
	}
	public ODocument createContentDefaultModel(ODatabaseDocumentTx db, String nameTable, String format, boolean useFormat) {
		if (getCountByNameTable(db, nameTable)==0) {
			ODocument tmp=factoryLang(nameTable, 1, 0, 1, 1, "", format, useFormat);
			tmp.save();		
			return tmp;
		}else{
			return null;
		}
	}


	public ODocument factoryLang(String nameTable, long start,
			long end, int increment, long now, String prefix,
			String format, boolean useFormat ) {
		ODocument doc = new ODocument(getClassName());
		doc.field(NumberId.NAMA_TABLE, nameTable);
		doc.field(NumberId.START, start, OType.LONG);
		doc.field(NumberId.END, end, OType.LONG);
		doc.field(NumberId.INCREMENT, increment, OType.INTEGER);
		doc.field(NumberId.NOW, now, OType.LONG);
		doc.field(NumberId.PREFIX, prefix);
		doc.field(NumberId.FORMAT, format);
		doc.field(NumberId.USE_FORMAT, useFormat, OType.BOOLEAN);
		return doc;
	}
	
	public int updateWithDocumentByCode(ODatabaseDocumentTx db, String code, String name, boolean codeIsChange){
		StringBuilder sql=new StringBuilder("update ");
		sql.append(getClassName());
		if (codeIsChange) {
			sql.append(" set code = ? ,");
		}
		sql.append(" set name = ?");
		sql.append(" where code = ? ");
		App.info(sql.toString());
		OCommandSQL query = new OCommandSQL(sql.toString());
		if (codeIsChange) {
			return db.command(query).execute(code, name, code);
		}else{
			return db.command(query).execute(name, code);
		}
	}
	
	public long getCountByNameTable(ODatabaseDocumentTx db, String nameTable){
		return getCountByColumn(db, NumberId.NAMA_TABLE, nameTable);
	}

}
