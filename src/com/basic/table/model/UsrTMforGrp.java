package com.basic.table.model;

import java.util.List;

import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class UsrTMforGrp extends UsrTM {

	public UsrTMforGrp(ODatabaseDocumentTx db) {
		super(db);
	}



//	@Override
//	public void addObjModel(ODocument model) {
//		// belum di isi
//		fireTableDataChanged();
//	}
	
	

	@Override
	public void initDao() {
		dao = App.getUsrDao();
	}

	@Override
	public void loadList(List list) {
		model=list;
	}

	protected List<ODocument> model2;
	protected List<ODocument> model3;

	@Override
	public void loadDataModel(ODatabaseDocumentTx db) {
		// tidak ada load data, data dri inputan bind
	}


	@Override
	public void loadJumlahData(ODatabaseDocumentTx db) {
		if (paging != null) {
			paging.getPanelPaging().setVisible(false);
		}
		
	}

	@Override
	public List getModel2() {
		return model2;
	}

	public List<ODocument> getModel3() {
		return model3;
	}



	public void setModel2(List model2) {
		this.model2 = model2;
	}



	public void setModel3(List model3) {
		this.model3 = model3;
	}

	

}
