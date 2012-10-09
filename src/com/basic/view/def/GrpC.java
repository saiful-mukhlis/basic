package com.basic.view.def;

import java.util.List;

import javax.swing.JSplitPane;

import org.basic.comp.abst.DetailAbstract;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import org.basic.comp.base.TreeHakAkses;

import com.basic.dao.impl.GrpDao;
import com.basic.db.Grp;
import com.basic.lang.LGrp;
import com.basic.table.UsrTForGrp;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class GrpC extends DetailAbstract {

	protected TextField code;
	protected TextField name;
	protected TextArea note;
	protected ScrollPane snote;
	
	protected TextField key;
	protected TextField createBy;
	protected TextField updateBy;
	protected TextField createAt;
	protected TextField updateAt;
	protected TextField createBy2;
	protected TextField updateBy2;
	
	
	protected TreeHakAkses tree;
	protected UsrTForGrp usrt;
	protected JSplitPane splitPane;

	public void init(ODatabaseDocumentTx db) {
		lebar = 0.002;
		dao = App.getGrpDao();
		tree=new TreeHakAkses();
		usrt=new UsrTForGrp();
		
		initComponent(db);
	}

	public void resetContentComponent() {
		code.setText("");
		name.setText("");
		note.setText("");
		key.setText("");
		createBy.setText("");
		updateBy.setText("");
		createAt.setText("");
		updateAt.setText("");
		createBy2.setText("");
		updateBy2.setText("");
	}

	public void setColorView() {
		code.setBackground(App.whiteSmoke);
		name.setBackground(App.whiteSmoke);
		note.setBackground(App.whiteSmoke);
		snote.setBackground(App.whiteSmoke);
		
		key.setBackground(App.whiteSmoke);
		createBy.setBackground(App.whiteSmoke);
		updateBy.setBackground(App.whiteSmoke);
		createAt.setBackground(App.whiteSmoke);
		updateAt.setBackground(App.whiteSmoke);
		createBy2.setBackground(App.whiteSmoke);
		updateBy2.setBackground(App.whiteSmoke);
	}

	public void setEditable(boolean isEdit) {
		code.setEditable(false);
		name.setEditable(false);
		note.setEditable(false);
	}

//	public void setContentComponent(ODocument model) {
//		if (modelIsTrue(model)) {
//			GrpDao d = App.getGrpDao();
//			code.setText(d.getCode(model));
//			name.setText(d.getName(model));
//			note.setText(d.getNote(model));
//			createAt.setText(d.getCreateAt(model));
//			updateAt.setText(d.getUpdateAt(model));
//		}
//	}
	
	

	@Override
	public void load(ODocument model) {
		if (model==null) {
			code.setText("");
			name.setText("");
			note.setText("");
			createBy.setText("");
			createAt.setText("");
			updateBy.setText("");
			updateAt.setText("");
		}else if (model.field("@class").equals(Grp.TABLE)) {
			GrpDao d = App.getGrpDao();
			code.setText(d.getCode(model));
			name.setText(d.getName(model));
			note.setText(d.getNote(model));
			createBy.setText(d.createByToString(model));
			createAt.setText(d.getCreateAt(model));
			updateBy.setText(d.updateByToString(model));
			updateAt.setText(d.getUpdateAt(model));
		}
		tree.load(model);
	}



	public void initComponent(ODatabaseDocumentTx db) {
		code = new TextField();
		name = new TextField();
		note = new TextArea();
		snote = new ScrollPane(note);
		
		key = new TextField();
		createBy = new TextField();
		updateBy = new TextField();
		createAt = new TextField();
		updateAt = new TextField();
		createBy2 = new TextField();
		updateBy2 = new TextField();
	}

	public void buildForm(ODatabaseDocumentTx db) {

		//Double tmp = App.getW() * lebar;// 0.51
		
		
		
		StringBuilder col=new StringBuilder();
		StringBuilder row=new StringBuilder();
		col.append("10px,");
		col.append("r:p,10px,f:0px:g,");
		col.append("20px,");
		col.append("r:p,10px,f:0px:g,");
		col.append("10px,");
		
		row.append("3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		
		FormLayout l = new FormLayout(col.toString(),row.toString());

		l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
		b.append(LGrp.CODE, code, 2, 2, 1);
		b.append(LGrp.NAME, name, 2, 4, 1);
		
		b.append(LGrp.NOTE, snote, 2, 6, 1, 3);
		
		b.append(LGrp.CREATE_BY, createBy, 6, 2, 1);
		b.append(LGrp.CREATE_AT, createAt, 6, 4, 1);
		b.append(LGrp.UPDATE_BY, updateBy, 6, 6, 1);
		b.append(LGrp.UPDATE_AT, updateAt, 6, 8, 1);
		
		
		StringBuilder c=new StringBuilder();
		StringBuilder r=new StringBuilder();
		c.append("10px,f:0px:g,10px,");
		
		r.append("3dlu,");
		r.append("p,3dlu,");
		r.append("p,3dlu,");
		layout=new FormLayout(c.toString(), r.toString());
		builder=new FormBuilder(layout);
		
		tree.build(db);
		usrt.build(db);
		tree.getPanel().setBorder(App.borderBlack);
		usrt.getPanel().setBorder(App.borderBlack);
		splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tree.getPanel(), usrt.getPanel());
		splitPane.setDividerSize(20);
		Double tmp=App.getW()*0.25;
		splitPane.setDividerLocation(tmp.intValue());
//		splitPane.setOneTouchExpandable(false);
		splitPane.setOpaque(false);
		splitPane.setBorder(null);
//		splitPane.setBackground(Color.red);
		builder.append(b.getPanel(), 2, 2);
		builder.append(splitPane, 2, 4);

	}

	@Override
	public void requestDefaultFocus() {
		name.requestFocus();
	}

}
