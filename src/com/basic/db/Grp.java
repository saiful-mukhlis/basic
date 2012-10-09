package com.basic.db;

import com.basic.annotation.db.Type;
import com.orientechnologies.orient.core.metadata.schema.OType;

public class Grp {
	public static final String TABLE="Grp";
	public static final String CODE="code";
	public static final String NAME="name";
	public static final String NOTE="note";
	public static final String KEY="key";
	@Type(t = OType.LINKSET)
	public static final String USRS="usrs";
	
	@Type(t = OType.LINK)
	public static final String CREATE_BY="createdBy";
	@Type(t = OType.LINK)
	public static final String UPDATE_BY="updatedBy";
	@Type(t = OType.DATETIME)
	public static final String CREATE_AT="createdAt";
	@Type(t = OType.DATETIME)
	public static final String UPDATE_AT="updatedAt";
	
	public static final String CREATE_BY2="createdBy2";
	public static final String UPDATE_BY2="updatedBy2";

}
