package com.basic.view.add;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import org.basic.comp.abst.FormBuilder;
import org.basic.comp.listener.WidgetInterface;

import com.basic.comp.impl.master.JenisPekerjaanM;
import com.basic.dao.impl.JenisPekerjaanDao;
import com.basic.db.JenisPekerjaan;
import com.basic.icon.IconBase;
import com.basic.lang.L;
import com.basic.lang.LJenisPekerjaan;
import com.basic.view.def.JenisPekerjaanC;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class JenisPekerjaanN extends JenisPekerjaanC  {

	public void initComponent(ODatabaseDocumentTx db) {
		super.initComponent(db);

		save = new JButton(L.save);
		reset = new JButton(L.reset);

		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				actionSave();
			}
		});
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				actionReset();
			}
		});

	}

	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		initComponent(db);
		title = JenisPekerjaanM.TITLE_ADD;
		icon = IconBase.ADD;

		buildLabel(db);
		buildForm(db);
		buildPanel();

		setFocusEnter();
		actionReset();
	}

	public void buildForm(ODatabaseDocumentTx db) {
		Double tmp = App.getW() * lebar;
		layout = new FormLayout(
				"r:p,   	10px,   	f:max("
						+ tmp.intValue()
						+ "px;p):g,  10px,   	f:max(80px;p),  10px,   	f:max(80px;p),     	10px,",

				"p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,  p,3dlu,  p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,  p,3dlu,  p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,  p,3dlu,  p,3dlu ,10dlu");

		layout.setColumnGroups(new int[][] { { 5, 7 } });
		builder = new FormBuilder(layout, true);
		builder.append(LJenisPekerjaan.CODE, code, 1, 1, 5);
		builder.append(LJenisPekerjaan.NAMA, nama, 1, 3, 5);

		buildButton(db);

	}

	public void setFocusEnter() {
		setFocusEnter(code, nama);
		setFocusEnter(nama, save);

		// setFocusEnter(save, reset);
		setFocusEnter(save, code);
	}

	@Override
	public void buildButton(ODatabaseDocumentTx db) {
		super.buildButton(db);
		builder.append(save, 5, 5);
		builder.append(reset, 7, 5);
	}

	public void afterSave(ODocument tmp) {
		for (WidgetInterface w : widgets) {
			w.addModel(tmp);
		}
		actionReset();
	}

	public void actionReset() {
		resetContentComponent();
		code.setText("Auto");
		requestDefaultFocus();

	}

	/**
	 * harus ada di new
	 * 
	 * @return
	 */
	public ODocument createDataBaru() {

		ODocument o = new ODocument(JenisPekerjaan.TABLE);
		JenisPekerjaanDao d = App.getJenisPekerjaanDao();
		d.setCode(o, code);
		d.setNama(o, nama);

		return o;
	}

	public boolean validate(ODatabaseDocumentTx db) {
		JenisPekerjaanDao d = App.getJenisPekerjaanDao();
		if (!d.vCode(db, code)) {
			return false;
		}
		if (!d.vNama(db, nama)) {
			return false;
		}
		return true;
	}

	public void save(ODatabaseDocumentTx db, ODocument model) {
		App.getJenisPekerjaanDao().save(db, model);
	}

	public void afterOk() {
		nama.requestFocus();
	}

}
