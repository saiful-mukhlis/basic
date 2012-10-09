package com.basic.comp.impl.master;




import javax.swing.Icon;

import org.basic.comp.abst.MasterDefault;

import com.basic.print.UsrPrintAll;
import com.basic.print.model.UsrPM;
import com.basic.table.UsrT;
import com.basic.view.add.UsrDN;
import com.basic.view.add.UsrN;
import com.basic.view.detail.UsrV;
import com.basic.view.edit.UsrDE;
import com.basic.view.edit.UsrE;
import com.basic.view.search.UsrS;
import com.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class UsrM extends MasterDefault {

	public static String TITLE_ADD=App.getT("Tambah Data Pegawai");
	public static String TITLE_EDIT=App.getT("Edit Data Pegawai");
	public static String TITLE_VIEW=App.getT("Data Pegawai");
	
	public static String TITLE=App.getT("Master Data Pegawai");
	public static String TITLE_MENU=App.getT("Pegawai");
	public static String TITLE_TOOLBAR=TITLE_MENU;
	public static String URL_ICON_16="/image/pegawai-16.png";
	public static String URL_ICON_32="/image/pegawai-32.png";
	public static String URL_ICON_48="/image/pegawai-48.png";
	public static Icon ICON_16=App.getIcon(URL_ICON_16);
	public static Icon ICON_32=App.getIcon(URL_ICON_32);
	public static Icon ICON_48=App.getIcon(URL_ICON_48);
	public static boolean ADD=false;
	public static boolean EDIT=false;
	public static boolean DEL=false;
	public static boolean VIEW=false;
	
	public UsrM() {
		super();
		lebar=0.35;
		title=TITLE_MENU;
		detailWidget=new UsrV();
		detailWidget.setMasterEfectWidget(this);
		table = new UsrT();
		printer=new UsrPrintAll(panel);
	}

	@Override
	public void setEditWidget() {
		editWidget=new UsrDE();
		
	}




	@Override
	public void setAddWidget() {
		addWidget=new UsrDN();
	}




	@Override
	public void setTableWidget() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void setDetailWidget() {
		// TODO Auto-generated method stub
		
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
