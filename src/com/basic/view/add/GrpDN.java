package com.basic.view.add;



import java.util.Date;

import org.basic.comp.abst.DialogDefault;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;

import com.basic.comp.impl.master.GrpM;
import com.basic.dao.impl.GrpDao;
import com.basic.lang.LGrp;
import com.global.App;
import com.global.DataUser;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;



public class GrpDN extends DialogDefault {
	protected TextField code;
	protected TextField name;
	protected TextArea note;
	protected ScrollPane snote;
	
	
	
	@Override
	public void actionReset() {
		code.setText("Auto");
		name.setText("");
		note.setText("");
		requestDefaultFocus();
	}

	
	
	@Override
	public void requestDefaultFocus() {
		name.requestFocus();
	}



	public void initComponent(ODatabaseDocumentTx db) {
		code = new TextField();
		name = new TextField();
		note = new TextArea();
		snote = new ScrollPane(note);
		
	}
	
	
	
	
	@Override
	public void setFocusEnter() {
		setFocusEnter(code, name);
		setFocusEnter(name, note);
		setFocusEnter(note, save);
		setFocusEnter(save, code);
	}


	@Override
	public void init(ODatabaseDocumentTx db) {
		icon=GrpM.ICON_48;
		super.init(db);
		labelTitle.setText("Tambah Hak Akses");
		labelNote.setText("Isi data dengan benar");
		initComponent(db);
		buildForm(db);
	}

	public void buildForm(ODatabaseDocumentTx db) {
		initComponent(db);
		
		StringBuilder col=new StringBuilder();
		StringBuilder row=new StringBuilder();
		col.append("30px,");
		col.append("r:p,10px,f:400px:g,");
		col.append("30px,");
		
		row.append("15dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("f:40dlu:g,15dlu,");
		
		FormLayout l = new FormLayout(col.toString(),row.toString());

		//l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
		b.append(LGrp.CODE, code, 2, 2, 1);
		b.append(LGrp.NAME, name, 2, 4, 1);
		
		b.append(LGrp.NOTE, snote, 2, 6, 1);
		
		
		panelForm=b.getPanel();

	}


	@Override
	public void save(ODatabaseDocumentTx db) {
		GrpDao d=App.getGrpDao();
		model=new ODocument(d.getClassName());
		d.setCode(model, code);
		d.setName(model, name);
		d.setNote(model, note);
		d.setCreateAt(model, new Date());
		d.setCreateBy(model, DataUser.getUsr());
		d.save(db, model);
	}

	@Override
	public boolean validate(ODatabaseDocumentTx db) {
		GrpDao d=App.getGrpDao();
		if (!d.vCode(db, code)) {
			return false;
		}
		if (!d.vName(db, name)) {
			return false;
		}
		
		return true;
	}
	
}
