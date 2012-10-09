package org.basic.comp.listener;

import javax.swing.JPanel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public interface WidgetInterface {
	public void build(ODatabaseDocumentTx db);
	public void load(ODocument model);
	public JPanel getPanel();
}
