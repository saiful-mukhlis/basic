package org.basic.comp.adapter;

import javax.swing.JMenuBar;

import org.basic.comp.listener.MasterInterface;
import org.basic.comp.listener.WidgetInterface;


import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface MenuInterface extends WidgetInterface{
	public JMenuBar getMenu();
	public void setWindow(WindowInterfaces window);
	public WindowInterfaces getWindow();
	public void setMasterSelected(MasterInterface master);
}
