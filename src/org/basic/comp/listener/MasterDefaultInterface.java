package org.basic.comp.listener;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;


public interface MasterDefaultInterface  {
	public void initPrefernce(ODatabaseDocumentTx db);
	public void setEditWidget();
	public void setAddWidget();
	public void setTableWidget();
	public void setDetailWidget();
}
