package com.basic.annotation.db;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.orientechnologies.orient.core.metadata.schema.OType;

@Retention(RetentionPolicy.RUNTIME) 
public @interface Type {
public  OType t();
}
