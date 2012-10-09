package org.basic.comp.adapter;

import java.util.List;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

import org.basic.comp.listener.WidgetInterface;


import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class MenuAdapter implements MenuInterface, HakAksesListener, WidgetInterface{

	@Override
	public void build(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JMenuBar getMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWindow(WindowInterfaces window) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load(ODocument model, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load2(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load3(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load4(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load5(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadList(List list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadList2(List list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadList3(List list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadList4(List list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadList5(List list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modelWidgetAdd2(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modelWidgetAdd3(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modelWidgetAdd4(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modelWidgetAdd5(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listWidgetAdd(List list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listWidgetAdd2(List list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listWidgetAdd3(List list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listWidgetAdd4(List list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listWidgetAdd5(List list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modelWidgetSearch(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeHakAkses() {
		// TODO Auto-generated method stub
		
	}
}
