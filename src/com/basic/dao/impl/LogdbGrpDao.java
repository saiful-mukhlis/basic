package com.basic.dao.impl;

import java.util.Date;

import org.basic.dao.abst.DaoAbstract;

import com.basic.db.LogdbGrp;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;


public class LogdbGrpDao extends DaoAbstract {
	
	public LogdbGrpDao() {
		super(LogdbGrp.TABLE);
		// TODO Auto-generated constructor stub
	}
	
	public static final int ADD=0;
	public static final int EDIT=1;
	public static final int DEL=2;

	public void save(ODocument usr, int a, String oldObj, String newObj){
		ODocument o=new ODocument(getClassName());
		o.field(LogdbGrp.DATE_TIME, new Date(), OType.DATETIME);
		o.field(LogdbGrp.USR, usr, OType.LINK);
		o.field(LogdbGrp.ACTION, a, OType.INTEGER);
		o.field(LogdbGrp.OLD_OBJ, oldObj);
		o.field(LogdbGrp.NEW_OBJ, newObj);
		o.save();
	}
}
