package com.basic.db;

import com.basic.annotation.db.Type;
import com.orientechnologies.orient.core.metadata.schema.OType;

public class Bos {
	public static final String TABLE="Bos";
	public static final String ID="id";
	public static final String NAME="name";
	@Type(t = OType.INTEGER)
	public static final String JML="jml";

}
