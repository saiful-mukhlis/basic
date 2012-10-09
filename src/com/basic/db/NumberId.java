package com.basic.db;

import com.basic.annotation.db.Type;
import com.orientechnologies.orient.core.metadata.schema.OType;

/**
 * Adalah table sequence yang di gunakan untuk kebutuhan
 * auto increment
 * @author toyib
 *
 */
public class NumberId {
	public static final String TABLE="NumberId";
	
	public static final String NAMA_TABLE="namaTable";
	@Type(t = OType.LONG)
	public static final String START="start";
	@Type(t = OType.LONG)
	public static final String END="end";
	@Type(t = OType.INTEGER)
	public static final String INCREMENT="increment";
	@Type(t = OType.LONG)
	public static final String NOW="now";
	
	public static final String PREFIX="prefix";
	public static final String FORMAT="format";
	@Type(t = OType.BOOLEAN)
	public static final String USE_FORMAT="useFormat";

}
