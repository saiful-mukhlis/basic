package com.basic.comp.impl.master;




import javax.swing.Icon;

import org.basic.comp.abst.MasterDefault;

import com.basic.table.JenisPekerjaanT;
import com.basic.view.add.JenisPekerjaanN;
import com.basic.view.detail.JenisPekerjaanV;
import com.basic.view.edit.JenisPekerjaanE;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class JenisPekerjaanM extends MasterDefault {

	public static String TITLE_ADD=App.getT("Tambah Data Jenis Pekerjaan");
	public static String TITLE_EDIT=App.getT("Edit Data Jenis Pekerjaan");
	public static String TITLE_VIEW=App.getT("Data Jenis Pekerjaan");
	
	public static String TITLE=App.getT("Master Data Jenis Pekerjaan");
	public static String TITLE_MENU=App.getT("Jenis Pekerjaan");
	public static String TITLE_TOOLBAR=TITLE_MENU;
	public static String URL_ICON_16="/image/work-16.png";
	public static String URL_ICON_32="/image/work-32.png";
	public static Icon ICON_16=App.getIcon(URL_ICON_16);
	public static Icon ICON_32=App.getIcon(URL_ICON_32);
	public static boolean ADD=false;
	public static boolean EDIT=false;
	public static boolean DEL=false;
	public static boolean VIEW=false;
	
	public JenisPekerjaanM() {
		super();
		lebar=0.35;
		title=TITLE_MENU;
		detailWidget=new JenisPekerjaanV();
		detailWidget.setMasterEfectWidget(this);
		table = new JenisPekerjaanT();
		
	}

	@Override
	public void setEditWidget() {
		editWidget=new JenisPekerjaanE();
		editWidget.setMasterEfectWidget(this);
	}

	@Override
	public void setAddWidget() {
		addWidget=new JenisPekerjaanN();
		addWidget.setMasterEfectWidget(this);
	}


	public boolean isAdd() {
		return ADD;
	}
	
	public boolean isDel() {
		return DEL;
	}

	public boolean isView() {
		return VIEW;
	}

	public boolean isEdit() {
		return EDIT;
	}

	@Override
	public Icon getIcon16() {
		return ICON_16;
	}

	@Override
	public Icon getIcon32() {
		return ICON_32;
	}

	@Override
	public void initPrefernce(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSearchForm() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getTitleToolBar() {
		return TITLE.toUpperCase();
	}

}
