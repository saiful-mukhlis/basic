package com.basic.table;

import org.basic.comp.abst.TableDefault;
import org.basic.comp.listener.WidgetInterface;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.table.TableColumnExt;

import com.basic.table.model.JenisPekerjaanTM;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class JenisPekerjaanT extends TableDefault{


	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new JenisPekerjaanTM(db);
		tableModel.setTableParent(this);
		
	}

	@Override
	public void load(ODocument model) {
	}


//	@Override
//	public void modelWidgetAdd(ODocument model) {
//			tableModel.addObjModel(model);
//			selected();
//		
//	}
	public void selectedNotValid(){
		if (widgets.size() != 0) {
			for (WidgetInterface view : widgets) {
				view.modelWidgetChange(null);
			}
		}
	}

	public void selectedValid(int i) {
		if (widgets.size() != 0) {
			for (WidgetInterface view : widgets) {
				int indtmp=table.convertRowIndexToModel(i);
				view.modelWidgetChange((ODocument) tableModel.getModels().get(indtmp));
			}
		}
	}

	private int indexBerubah=-1;
	@Override
	public void modelWidgetChange(ODocument model) {
		if (model!=null && model.field("@class").equals(App.getJenisPekerjaanDao().getClassName())) {
			indexBerubah=this.tableModel.getModels().indexOf(model);
		}

		tableModel.fireTableDataChanged();
	}
	
	

	public void setSimple(){
		if (getTable() instanceof JXTable) {
			JXTable t=(JXTable) getTable();
			String [] x=tableModel.getNamaKolom();
			for (String string : x) {
				TableColumnExt tcx=t.getColumnExt(string);
				if (tcx!=null) {
					tcx.setVisible(false);
				}
			}
			
			TableColumnExt tcx=t.getColumnExt(x[0]);
			if (tcx!=null) {
				tcx.setVisible(true);
			}
			
//			tcx=t.getColumnExt(x[1]);
//			if (tcx!=null) {
//				tcx.setVisible(true);
//			}
			
			tcx=t.getColumnExt(x[2]);
			if (tcx!=null) {
				tcx.setVisible(true);
			}
			
			
			
			
			
		}
	}
	public void setShowAll(){
		if (getTable() instanceof JXTable) {
			JXTable t=(JXTable) getTable();
			String [] x=tableModel.getNamaKolom();
			for (String string : x) {
				TableColumnExt tcx=t.getColumnExt(string);
				if (tcx!=null) {
					tcx.setVisible(true);
				}
			}
			
		}
	}

}
