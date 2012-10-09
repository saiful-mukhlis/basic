package org.basic.comp.adapter;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.basic.comp.listener.AddDialogInterface;
import org.basic.comp.listener.MasterInterface;
import org.basic.comp.listener.WiddgetSyncInterface;
import org.basic.dao.adapter.DaoInterface;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;


public class AddDialogAdapter implements AddDialogInterface{

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
	public void actionCancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionSave() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestDefaultFocus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMaster(MasterInterface master) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MasterInterface getMaster() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void beforeSave() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterSave() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTable(TableInterfaces table) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSync(WiddgetSyncInterface widgetSync) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validate(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Icon getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIcon(Icon icon) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JLabel getLabelTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLabelTitle(JLabel label) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JScrollPane getPane() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaoInterface getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDao(DaoInterface dao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel getPanelForm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPanelForm(JPanel panelForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFocusEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildPanel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildButton(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLayoutButton() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLayoutTitle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}
	
}
