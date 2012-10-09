package org.basic.comp.abst;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import org.basic.comp.adapter.DetailWidgetInterface;
import org.basic.comp.adapter.HakAksesListener;
import org.basic.comp.adapter.TableInterfaces;
import org.basic.comp.adapter.ToolbarSmallAdapter;
import org.basic.comp.listener.MasterInterface;
import org.basic.comp.listener.WidgetInterface;

import com.basic.lang.L;
import com.basic.lang.LMasterPegawai;
import com.global.App;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public abstract class MasterFAbstract implements MasterInterface, HakAksesListener{

	protected JPanel panel;
	protected TableInterfaces table;
	
	protected DetailWidgetInterface form;
	
	public DetailWidgetInterface getForm() {
		return form;
	}

	public DetailWidgetInterface getEditForm() {
		return null;
	}

	public DetailWidgetInterface getViewForm() {
		return null;
	}

//	protected JPanel cardPanel;
//	protected CardLayout cardLayout;

	protected JSplitPane splitPane;

//	protected JButton showTable;
//	protected JButton showForm;


	protected JPanel aksi;

	protected JToolBar toolBar;
	
	protected List<ToolbarSmallAdapter> changeStateActions=new ArrayList<>();
	
	public void init(ODatabaseDocumentTx db){
		panel=new JPanel();
		panel.setBackground(Color.WHITE);
		initTable(db);
		initLabelTitle(db);
		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
//		cardLayout = new CardLayout();
//		cardPanel = new JPanel(cardLayout);
		

//		showTable = new JButton(App.getIcon(db, "icon 1w 16"));
//		showForm = new JButton(App.getIcon(db, "icon 2l 16"));
		
//		showTable.setBackground(Color.WHITE);
//		showForm.setBackground(Color.WHITE);
		
	}
	
	public void initTable(ODatabaseDocumentTx db) {
		
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		init(db);
		buildBody(db);
		setLayout();
	}
	
	public void setLayout() {
		panel.setLayout(new BorderLayout());
//		cardPanel.add(form.getPanel(), "tambah");
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				 form.getPanel(), table.getPanel());
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerSize(1);
		tampilkanDefault();
		
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(toolBar, BorderLayout.NORTH);
	}

	public void tampilkanDefault() {
	}

	@Override
	public void load(ODocument model) {
	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void modelWidgetChange(ODocument model) {
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
	}

	@Override
	public void changeHakAkses() {
	}

	public TableInterfaces getTable() {
		return table;
	}
	
	public void aksiReload() {
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		table.reload(db);
		db.close();
		
	}
	
	public void aksiDelete(){
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		table.aksiDelete(db);
		db.close();
	}

//	public void tampilkanForm() {
//		cardPanel.setVisible(true);
//		splitPane.setDividerLocation(getDevide());
//	}
//
//	public void aksiTambah() {
//		tampilkanForm();
//		cardLayout.show(cardPanel, "tambah");
//		form.aksiReset();
//	}

//	public void aksiLihat() {
//		tampilkanForm();
//		cardLayout.show(cardPanel, "lihat");
//	}

//	public void aksiEdit() {
//		tampilkanForm();
//		cardLayout.show(cardPanel, "edit");
//	}
	
	public void buildAksi(ODatabaseDocumentTx db){
		FormLayout layout = new FormLayout(
				" 4dlu,  	f:p,  4dlu,   p:g,  4dlu,   	p,  4dlu,   	p,  4dlu,   	p:g,  4dlu,   	"
						+ "p,  2dlu,  p,  2dlu,p,  2dlu,p,  2dlu,p,   4dlu,",

				"p,3dlu");

		toolBar = new JToolBar();
		toolBar.setLayout(layout);
		toolBar.setBackground(Color.WHITE);
		CellConstraints cc = new CellConstraints();
		toolBar.add(label, cc.xy(2, 1));
//		toolBar.add(showTable, cc.xy(6, 1));
//		toolBar.add(showForm, cc.xy(8, 1));
//		toolBar.add(reload, cc.xy(12, 1));
//		toolBar.add(lihat, cc.xy(14, 1));
//		toolBar.add(tambah, cc.xy(16, 1));
//		toolBar.add(edit, cc.xy(18, 1));
//		toolBar.add(hapus, cc.xy(20, 1));
	}
	protected JLabel label;
	public void initLabelTitle(ODatabaseDocumentTx db){
		label = new JLabel(App.getIcon(L.iconPegawai16));
		label.setText(LMasterPegawai.title);
	}
	
	public void buildAksiListener(){
//		showTable.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent actionevent) {
//				form.getPanel().setVisible(false);
//
//			}
//		});
//		showForm.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent actionevent) {
//				tampilkanDefault();
//
//			}
//		});

	}

	public void buildBody(ODatabaseDocumentTx db){
		initBody(db);
		
		form.build(db);
		
		buildAksi(db);
		buildAksiListener();
		setBinding();
	}
	
	public void initBody(ODatabaseDocumentTx db) {
	}

	public void setBinding(){
		table.addWidget(this);

		form.addWidget(table);
	}

	@Override
	public void actionAdd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionEdit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionReload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionDel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPrint() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAdd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isView() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEdit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPrint() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrlIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ToolbarSmallAdapter> getChangeStateActions() {
		return changeStateActions;
	}

	public void setChangeStateActions(List<ToolbarSmallAdapter> changeStateActions) {
		this.changeStateActions = changeStateActions;
	}
	
	
	

}
