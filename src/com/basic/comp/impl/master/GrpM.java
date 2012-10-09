package com.basic.comp.impl.master;



import javax.swing.Icon;

import org.basic.comp.abst.MasterDefault;
import org.basic.comp.abst.TableDefault;

import com.basic.table.GrpT;
import com.basic.view.add.GrpDN;
import com.basic.view.detail.GrpV;
import com.basic.view.edit.GrpDE;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class GrpM extends MasterDefault {
	
	public static String TITLE_ADD=App.getT("Tambah Data Hak Akses");
	public static String TITLE_EDIT=App.getT("Edit Data Hak Akses");
	public static String TITLE_VIEW=App.getT("Data Hak Akses");
	
	public static String TITLE=App.getT("Master Hak Akses");
	public static String TITLE_MENU=App.getT("Hak Akses");
	public static String TITLE_TOOLBAR=TITLE_MENU;
	public static String URL_ICON_16="/image/hakakses-16.png";
	public static String URL_ICON_32="/image/hakakses-32.png";
	public static String URL_ICON_48="/image/hakakses-48.png";
	public static Icon ICON_16=App.getIcon(URL_ICON_16);
	public static Icon ICON_32=App.getIcon(URL_ICON_32);
	public static Icon ICON_48=App.getIcon(URL_ICON_48);
	public static boolean ADD=false;
	public static boolean EDIT=false;
	public static boolean DEL=false;
	public static boolean VIEW=false;

	public GrpM() {
		super();
		lebar=0.2;
		title=TITLE_MENU;
	}

	@Override
	public void setDetailWidget() {
		detailWidget=new GrpV();
	}

	@Override
	public void setEditWidget() {
		editWidget=new GrpDE();
		
	}

	@Override
	public void setAddWidget() {
		addWidget=new GrpDN();
	}

	@Override
	public void setTableWidget() {
		table =new GrpT();
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
	public String getTitleToolBar() {
		return TITLE.toUpperCase();
	}



}
