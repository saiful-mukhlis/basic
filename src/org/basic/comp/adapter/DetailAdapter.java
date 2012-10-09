package org.basic.comp.adapter;

import java.util.List;

import javax.swing.JPanel;

import org.basic.comp.listener.WidgetInterface;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;


public class DetailAdapter implements DetailWidgetInterface{

	@Override
	public void build(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionReset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMasterEfectWidget(EfectWidget master) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTypeEfectWidget(int typeEfectWidget) {
		// TODO Auto-generated method stub
		
	}



}
