package org.basic.comp.adapter;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;

import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;
import org.basic.comp.listener.MasterInterface;
import org.basic.comp.listener.PanelBottomInterface;
import org.basic.comp.listener.WidgetInterface;


import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;



public class TableAdapter implements TableInterfaces{

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
	public void reload(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JTable getTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMaster(EfectWidget master) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTypeEfectWidget(int typeEfectWidget) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addWidget(WidgetInterface widget) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aksiDelete(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setShowAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSimple() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TableModelInterface getTableModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addModel(ODocument o) {
		getTableModel().addModel(o);
	}

	@Override
	public ODocument getModelSelected() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIndexRowSelected() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isSearching() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public WindowInterfaces getWindow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWindow(WindowInterfaces window) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MasterInterface getMaster() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMaster(MasterInterface master) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PanelBottomInterface getPanelBottom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getNamaKolom() {
		return getTableModel().getNamaKolom();
	}

	@Override
	public SplitButton getItemSearch() {
		return getTableModel().getItemSearch();
	}

	@Override
	public TextFieldSearch getFieldSearch() {
		return getTableModel().getFieldSearch();
	}

	@Override
	public void setBind() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ODocument getBind() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editModel(ODocument model, int index) {
		// TODO Auto-generated method stub
		
	}



	}
