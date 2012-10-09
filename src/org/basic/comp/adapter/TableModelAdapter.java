package org.basic.comp.adapter;


import java.util.List;

import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;
import org.basic.dao.adapter.DaoInterface;


import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class TableModelAdapter extends AbstractTableModel implements TableModelInterface {

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addModel(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editModel(ODocument model, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTableParent(TableInterfaces table) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TableInterfaces getTableParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSearching() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reload(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPaging(PagingAdapter paging) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadDataModel(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadJumlahData(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initDao() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initNamaKolom(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getModels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModels(List model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DaoInterface getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getNamaKolom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SplitButton getItemSearch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TextFieldSearch getFieldSearch() {
		// TODO Auto-generated method stub
		return null;
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


	}
