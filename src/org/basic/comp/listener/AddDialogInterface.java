package org.basic.comp.listener;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.basic.comp.adapter.TableInterfaces;
import org.basic.dao.adapter.DaoInterface;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface AddDialogInterface extends WidgetInterface {
	public void actionReset();

	public void actionCancel();

	public void actionSave();
	
	public void save(ODatabaseDocumentTx db);

	public void requestDefaultFocus();

	public void setMaster(MasterInterface master);

	public MasterInterface getMaster();

	public void beforeSave();

	public void afterSave();

	public void setTable(TableInterfaces table);

	public void addSync(WiddgetSyncInterface widgetSync);

	public boolean validate(ODatabaseDocumentTx db);

	public Icon getIcon();

	public void setIcon(Icon icon);

	public JLabel getLabelTitle();

	public void setLabelTitle(JLabel label);

	public JScrollPane getPane();

	public DaoInterface getDao();

	public void setDao(DaoInterface dao);

	public JPanel getPanelForm();

	public void setPanelForm(JPanel panelForm);

	public void setFocusEnter();

	public void buildPanel();

	public void buildButton(ODatabaseDocumentTx db);

	public void setLayoutButton();

	public void setLayoutTitle();
}
