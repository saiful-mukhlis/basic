package com.basic.db;

import com.basic.annotation.db.Type;
import com.orientechnologies.orient.core.metadata.schema.OType;

public class Logdb {
	@Type(t = OType.DATETIME)
	public static final String DATE_TIME="dateTime";
	@Type(t = OType.LINK)
	public static final String USR="usr";
	public static final String USR_OLD="usrOld";
	@Type(t = OType.INTEGER)
	public static final String ACTION="action";
	public static final String OLD_OBJ="oldObj";
	public static final String NEW_OBJ="newObj";
	


}
