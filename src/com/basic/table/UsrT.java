package com.basic.table;

import java.awt.Font;

import javax.swing.UIManager;

import org.basic.comp.abst.TableDefault;
import org.basic.comp.listener.WidgetInterface;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.table.TableColumnExt;

import com.basic.table.model.UsrTM;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class UsrT extends TableDefault{


	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new UsrTM(db);
		tableModel.setTableParent(this);
		
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
			
			tcx=t.getColumnExt(x[1]);
			if (tcx!=null) {
				tcx.setVisible(true);
			}
			
			tcx=t.getColumnExt(x[2]);
			if (tcx!=null) {
				tcx.setVisible(true);
			}
			
			tcx=t.getColumnExt(x[3]);
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
