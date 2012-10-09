package com.basic.table.model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.basic.comp.abst.TableModelDefault;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;

import com.basic.dao.impl.GrpDao;
import com.basic.db.Grp;
import com.basic.db.JenisPekerjaan;
import com.basic.db.Usr;
import com.basic.lang.LGrp;
import com.basic.lang.LGrp;
import com.basic.lang.LWindow;
import com.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class GrpTM  extends TableModelDefault{

	public GrpTM(ODatabaseDocumentTx db) {
		super(db);
	}

	protected final int NO = 0;
	protected final int CODE = 1;
	protected final int NAMA = 2;
	protected final int NOTE = 3;
	protected final int CREATE_BY = 4;
	protected final int CREATE_AT = 5;
	protected final int UPDATE_BY = 6;
	protected final int UPDATE_AT = 7;
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[8];
		namaKolom[NO]=LGrp.NO;
		namaKolom[CODE]=LGrp.CODE;
		namaKolom[NAMA]=LGrp.NAME;
		namaKolom[NOTE]=LGrp.NOTE;
		namaKolom[CREATE_BY]=LGrp.CREATE_BY;
		namaKolom[CREATE_AT]=LGrp.CREATE_AT;
		namaKolom[UPDATE_BY]=LGrp.UPDATE_BY;
		namaKolom[UPDATE_AT]=LGrp.UPDATE_AT;
	}
	public void load(ODatabaseDocumentTx db) {
		loadJumlahData(db);
		loadDataModel(db);
		super.load(db);
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ODocument m=model.get(rowIndex);
		GrpDao d=App.getGrpDao();
		if (columnIndex == NO) {
			return getNo(rowIndex);
		} else if (columnIndex == CODE) {
			return d.getCode(m);
		} else if (columnIndex == NAMA) {
			return d.getName(m);
		} else if (columnIndex == NOTE) {
			return d.getNote(m);
		} else if (columnIndex == CREATE_BY) {
			return d.getCreateBy(m);
		} else if (columnIndex == CREATE_AT) {
			return d.getCreateAt(m);
		} else if (columnIndex == UPDATE_BY) {
			return d.getUpdateBy(m);
		} else if (columnIndex == UPDATE_AT) {
			return d.getUpdateAt(m);
		} else {
			return null;
		}
	}
	








	@Override
	public void initDao() {
		dao=App.getGrpDao();
	}
	
	
	/**
	 * model=grp
	 * model2=createby
	 * model3=updateby
	 * list=usrs
	 */
	
	
//	@Override
//	public void loadDataModel(ODatabaseDocumentTx db) {
//		super.loadDataModel(db);
//		model2 = new ArrayList<ODocument>();
//		model3 = new ArrayList<ODocument>();
//		list = new ArrayList<>();
//		for (ODocument oDocument : model) {
//			ODocument tmp2 = oDocument.field(Grp.CREATE_BY);
//			if (tmp2!=null) {
//				model2.add(tmp2);
//			}else{
//				model2.add(App.getGrpDao().getCreateBy2(oDocument));
//			}
//			
//
//			ODocument tmp3 = oDocument.field(Grp.UPDATE_BY);
//			if (tmp3 != null) {
//				tmp3.field(Usr.NAMA);
//				model3.add(tmp3);
//			} else {
//				model3.add(App.getGrpDao().getUpdateBy2(oDocument));
//			}
//			
//			List<ODocument> tmp4 = oDocument.field(Grp.USRS);
//			if (tmp4 != null) {
//				list.add(tmp4);
//			} else {
//				List<ODocument> tmp5=new ArrayList<>();
//				list.add(tmp5);
//			}
//
//		}
//	}
	
	
	
	
	
	
	
	
	
	
	public void actionSearchOneField(String col, String value){
		ODatabaseDocumentTx db = App.getDbd();
		ODatabaseRecordThreadLocal.INSTANCE.set(db);
		loadJumlahDataSearchLike(db, col, value);
		loadDataModelSearchLike(db, col, value);
		db.close();
		fireTableDataChanged();
		if (getTable() != null) {
			getTable().selected();
		}
	}

	protected TextFieldSearch fieldSearch;
	protected SplitButton itemSearch;
	protected String colSearch = Grp.NAME;
	protected JPopupMenu menuItemSearch;

	public TextFieldSearch getFieldSearch() {
		if (fieldSearch == null) {
			fieldSearch = new TextFieldSearch();
			fieldSearch = new TextFieldSearch();
			fieldSearch.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String tmp=fieldSearch.getText();
					if (!tmp.trim().equalsIgnoreCase("")) {
						actionSearchOneField(colSearch, tmp);
					}else{
						ODatabaseDocumentTx db = App.getDbd();
						ODatabaseRecordThreadLocal.INSTANCE.set(db);
						reload(db);
						db.close();
					}
				}
			});
		}
		return fieldSearch;
	}

	public SplitButton getItemSearch() {
		if (itemSearch == null) {
			itemSearch = new SplitButton(LWindow.KET_SEARCH + LGrp.NAME);
			itemSearch.setBackground(Color.WHITE);

			menuItemSearch = new JPopupMenu();

			JMenuItem item = new JMenuItem(LWindow.KET_SEARCH + LGrp.CODE);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LGrp.CODE);
					colSearch = Grp.CODE;
				}
			});
			menuItemSearch.add(item);
			
			item = new JMenuItem(LWindow.KET_SEARCH + LGrp.NAME);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LGrp.NAME);
					colSearch = Grp.NAME;
				}
			});
			menuItemSearch.add(item);
			
			itemSearch.setDropDownMenu(menuItemSearch);
			itemSearch.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String tmp=fieldSearch.getText();
					if (!tmp.trim().equalsIgnoreCase("")) {
						actionSearchOneField(colSearch, tmp);
					}else{
						ODatabaseDocumentTx db = App.getDbd();
						ODatabaseRecordThreadLocal.INSTANCE.set(db);
						reload(db);
						db.close();
					}
					
				}
			});

		}
		return itemSearch;
	}
}
