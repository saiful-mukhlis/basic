package org.basic.comp.abst;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.basic.comp.adapter.DetailWidgetInterface;
import org.basic.comp.adapter.MasterAdapter;
import org.basic.comp.adapter.TableInterfaces;
import org.basic.comp.adapter.ToolbarSmallAdapter;
import org.basic.comp.base.SplitPane;
import org.basic.comp.base.ToolbarSmallAdapter;
import org.basic.comp.base.ToolbarSmallRLTEDP;

import com.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public  class MasterTable extends MasterAdapter{


	protected TableInterfaces table;
	
	protected DetailWidgetInterface form;
	protected DetailWidgetInterface editForm;


	protected JPanel panelAction;

	protected ToolbarSmallAdapter toolBar;
//	protected double lebar;
	protected String title;
	
//	public int getDevide() {
//		Double tmp = App.getW()*lebar;
//		return tmp.intValue();
//	}
	
	
	public void init(ODatabaseDocumentTx db){
		panel=new JPanel();
		table.setMasterEfectWidget(this);
		table.build(db);
		
		
		toolBar = new ToolbarSmallRLTEDP(this);
		changeStateActions.add(toolBar);
		
	}
	
	@Override
	public void build(ODatabaseDocumentTx db) {
		init(db);
		buildBody(db);
		setLayout();
//		setAksiTampilan();
	}
	
	public void setLayout() {
		panel.setLayout(new BorderLayout());
		panel.add(table.getPanel(), BorderLayout.CENTER);
		panel.add(toolBar, BorderLayout.NORTH);
		
		
	}




	@Override
	public void changeHakAkses() {
		toolBar.setStateByHakAkses();
	}

	public TableInterfaces getTable() {
		return table;
	}
	
	public void actionReload() {
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		table.reload(db);
		db.close();
		
	}
	
	public void actionDel(){
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		table.aksiDelete(db);
		db.close();
	}

	

	public void buildBody(ODatabaseDocumentTx db){
		initBody(db);
		setBinding();
	}
	
	
	public void setBinding(){
		table.addWidget(this);
	}

	public DetailWidgetInterface getForm() {
		return form;
	}

	public void setForm(DetailWidgetInterface form) {
		this.form = form;
	}

	public DetailWidgetInterface getEditForm() {
		return editForm;
	}

	public void setEditForm(DetailWidgetInterface editForm) {
		this.editForm = editForm;
	}




	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	
	@Override
	public void modelWidgetChange(ODocument model) {
		// tampilan default
		actionView();
		//ToolbarSmallRLTED toolBar=(ToolbarSmallRLTED) this.toolBar;
		if (model==null) {
			for (ToolbarSmallAdapter toolbarSmallAdapter : getChangeStateActions()) {
				toolbarSmallAdapter.setFalseAll();
			}
		}else{
			for (ToolbarSmallAdapter toolbarSmallAdapter : getChangeStateActions()) {
				toolbarSmallAdapter.setStateByHakAkses();
			}
		}

	}

	
	
	
	public void actionEdit() {
//		if (editForm==null) {
//			setEditForm();
//			ODatabaseDocumentTx db = App.getDbd();
//		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
//			editForm.build(db);
//			db.close();
//			table.addWidgetChange(getEditForm());
//			editForm.addWidgetModel(table);
//			editForm.addWidgetModel(this);
//			table.selected();
//		}
//		perspectiveForm();
//		splitPane.setRightComponent(editForm.getPanel());
//		editForm.requestDefaultFocus();
	}
	
	public void actionAdd() {
//		if (form==null) {
//			setForm();
//			ODatabaseDocumentTx db = App.getDbd();
//		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
//		    form.setMaster(this);
//			form.build(db);
//			form.addWidgetModel(table);
//			db.close();
//		}
//		splitPane.setRightComponent(form.getPanel());
//		perspectiveForm();
//		getForm().actionReset();
//		form.requestDefaultFocus();
	}
//	public void perspectiveDefault() {
//		perspectiveDefault=true;
//		table.getPanel().setVisible(true);
//		table.setSimple();
//
//		viewForm.getPanel().setVisible(true);
//		if (editForm!=null) {
//			editForm.getPanel().setVisible(true);
//		}
//		if (form!=null) {
//			form.getPanel().setVisible(true);
//		}
//		splitPane.setDividerLocation(getDevide());
//	}
//	public void perspective1() {
//		perspectiveDefault=false;
//		table.getPanel().setVisible(true);
//		table.setShowAll();
//		splitPane.setDividerLocation(1.0);
//		viewForm.getPanel().setVisible(false);
//		if (editForm!=null) {
//			editForm.getPanel().setVisible(false);
//		}
//		if (form!=null) {
//			form.getPanel().setVisible(false);
//		}
//	}
//	public void perspective2() {
//		perspectiveDefault=false;
//		table.getPanel().setVisible(false);
//		viewForm.getPanel().setVisible(true);
//		if (editForm!=null) {
//			editForm.getPanel().setVisible(true);
//		}
//		if (form!=null) {
//			form.getPanel().setVisible(true);
//		}
//		splitPane.setDividerLocation(0.0);
//	}
	


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

//	public String getUrlIcon() {
//		return urlIcon;
//	}
//
//	public void setUrlIcon(String urlIcon) {
//		this.urlIcon = urlIcon;
//	}

	public boolean isPerspectiveDefault() {
		return perspectiveDefault;
	}


	public void setPerspectiveDefault(boolean perspectiveDefault) {
		this.perspectiveDefault = perspectiveDefault;
	}
	
	
	
	
//	@Override
//	public void actionPrint() {}
//	public void perspective3() {}
//	public void perspective4() {}
	public void initBody(ODatabaseDocumentTx db) {}
	public void setEditForm() {}
	public void setForm() {}


//	@Override
//	public boolean isPrint() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//
//	public WindowAdapter getWindow() {
//		return window;
//	}
//
//
//	public void setWindow(WindowAdapter window) {
//		this.window = window;
//	}
//
//
//	public List<ToolbarSmallAdapter> getChangeStateActions() {
//		return changeStateActions;
//	}
//
//
//	public void setChangeStateActions(List<ToolbarSmallAdapter> changeStateActions) {
//		this.changeStateActions = changeStateActions;
//	}
	
	
}
