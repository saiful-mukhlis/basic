package org.basic.comp.abst;

import java.util.ArrayList;
import java.util.List;


import org.basic.comp.adapter.PagingAdapter;
import org.basic.comp.adapter.TableInterfaces;
import org.basic.comp.adapter.TableModelAdapter;
import org.basic.dao.adapter.DaoInterface;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class TableModelDefault extends TableModelAdapter {

	protected String[] namaKolom;
	protected List<ODocument> model=new ArrayList<ODocument>();

	protected PagingAdapter paging;
	protected TableInterfaces table;
	protected DaoInterface dao;

	public TableModelDefault(ODatabaseDocumentTx db) {
		super();
		initDao();
		initNamaKolom(db);
	}

	public int getColumnCount() {
		return namaKolom.length;
	}
	


	public int getRowCount() {
		return model.size();
	}

	public String getColumnName(int col) {
		return namaKolom[col];
	}

	/* 
	 *  tanpa q tes sya + loadJumlahData
	 */
	public void reload(ODatabaseDocumentTx db) {
		loadJumlahData(db);
		loadDataModel(db);
		fireTableDataChanged();
		if (getTable()!=null) {
			getTable().selected();
		}
	}

	public void load(ODatabaseDocumentTx db) {
		loadJumlahData(db);
		loadDataModel(db);
		if (paging != null) {
			paging.loadFirst(db);
		}
	}

	public void setPaging(PagingAdapter paging) {
		this.paging = paging;
	}

	public List getModels() {
		return model;
	}

	public TableInterfaces getTable() {
		return table;
	}

	public void setTableParent(TableInterfaces table) {
		this.table = table;
	}

	public DaoInterface getDao() {
		return dao;
	}
	
	@Override
	public void loadDataModel(ODatabaseDocumentTx db) {
		if (paging != null) {
			int tmp=(paging.getCurentHalaman()-1)
					* paging.getJumlahPerHalaman();
			if (tmp<0) {
				tmp=0;
			}
			model = getDao().getAll(db, tmp
					,
					paging.getJumlahPerHalaman());
		} else {
			model = (List<ODocument>) getDao().getAll(db);
		}
	}

	@Override
	public void loadJumlahData(ODatabaseDocumentTx db) {
		if (paging != null) {
			paging.setJumlahData(dao.getCount(db));
			if (paging.getJumlahData() > paging.getJumlahPerHalaman()) {
				paging.getPanelPaging().setVisible(true);
			} else {
				paging.getPanelPaging().setVisible(false);
			}
		}
		
	}
	
	public void loadDataModelSearch(ODatabaseDocumentTx db, ODocument o) {
		if (paging != null) {
			int tmp=(paging.getCurentHalaman()-1)
					* paging.getJumlahPerHalaman();
			if (tmp<0) {
				tmp=0;
			}
			model = getDao().getAllSearch(db, o, tmp
					,
					paging.getJumlahPerHalaman());
			
		} else {
			model = (List<ODocument>) getDao().getAllSearch(db,o);
		}
	}

	public void loadJumlahDataSearch(ODatabaseDocumentTx db, ODocument o) {
		if (paging != null) {
			paging.setJumlahData(dao.getCountSearch(db,o));
			if (paging.getJumlahData() > paging.getJumlahPerHalaman()) {
				paging.getPanelPaging().setVisible(true);
			} else {
				paging.getPanelPaging().setVisible(false);
			}
		}
		
	}
	
	
	
	
	
	
	public void loadDataModelSearch(ODatabaseDocumentTx db, String col, String value) {
		if (paging != null) {
			int tmp=(paging.getCurentHalaman()-1)
					* paging.getJumlahPerHalaman();
			if (tmp<0) {
				tmp=0;
			}
			model = getDao().getAllByColumn(db, col, value, tmp
					,
					paging.getJumlahPerHalaman());
			
		} else {
			model = (List<ODocument>) getDao().getAllByColumn(db, col, value);
		}
	}

	public void loadJumlahDataSearch(ODatabaseDocumentTx db,  String col, String value) {
		if (paging != null) {
			paging.setJumlahData(dao.getCountByColumn(db,col, value));
			if (paging.getJumlahData() > paging.getJumlahPerHalaman()) {
				paging.getPanelPaging().setVisible(true);
			} else {
				paging.getPanelPaging().setVisible(false);
			}
		}
		
	}
	
	public void loadDataModelSearchLike(ODatabaseDocumentTx db, String col, String value) {
		if (paging != null) {
			int tmp=(paging.getCurentHalaman()-1)
					* paging.getJumlahPerHalaman();
			if (tmp<0) {
				tmp=0;
			}
			model = getDao().getAllByColumnLike(db, col, value+"%", tmp
					,
					paging.getJumlahPerHalaman());
			
		} else {
			model = (List<ODocument>) getDao().getAllByColumnLike(db, col, value+"%");
		}
	}

	public void loadJumlahDataSearchLike(ODatabaseDocumentTx db,  String col, String value) {
		if (paging != null) {
			paging.setJumlahData(dao.getCountByColumnLike(db,col, value+"%"));
			if (paging.getJumlahData() > paging.getJumlahPerHalaman()) {
				paging.getPanelPaging().setVisible(true);
			} else {
				paging.getPanelPaging().setVisible(false);
			}
		}
		
	}
	
	
	
	
	

	@Override
	public void addModel(ODocument model) {
		if (model!=null && model.field("@class").equals(dao.getClassName())) {
			this.model.add(model);
			fireTableDataChanged();
		}
		
	}

	

	@Override
	public void editModel(ODocument model, int index) {
		if (model!=null && model.field("@class").equals(dao.getClassName())) {
			this.model.set(index, model);
			fireTableDataChanged();
		}
	}

	public String[] getNamaKolom() {
		return namaKolom;
	}

	public void setNamaKolom(String[] namaKolom) {
		this.namaKolom = namaKolom;
	}
	
	public int getNo(int rowIndex){
		int no = rowIndex + 1;
		if (paging != null) {
				no += ((paging.getCurentHalaman()-1) * paging.getJumlahPerHalaman());
		}
		return no;
	}
	

	
	
//	
//	@Override
//	public void setDefaultLebar(JTable table) {
////		if (table!=null) {
////			TableColumnModel t=table.getColumnModel();
////			t.getColumn(NO).setPreferredWidth(27);
////			t.getColumn(CODE).setPreferredWidth(27);
////			t.getColumn(NAME).setPreferredWidth(100);
////			t.getColumn(NAME_PEMILIK).setPreferredWidth(100);
//////			t.getColumn(TELP).setPreferredWidth(27);
//////			t.getColumn(ADDRESS).setPreferredWidth(27);
////			}
//		
//	}
	
}
