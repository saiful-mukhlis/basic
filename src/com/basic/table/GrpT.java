package com.basic.table;

import javax.swing.JOptionPane;

import org.basic.comp.abst.TableDefault;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.table.TableColumnExt;

import com.basic.table.model.GrpTM;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class GrpT extends TableDefault{


	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new GrpTM(db);
		tableModel.setTableParent(this);
		
	}



	public void aksiDelete(ODatabaseDocumentTx db) {
		if (getTable() != null) {
			int i = getTable().getSelectedRow();
			if (i != -1) {
				if (JOptionPane.showConfirmDialog(null,
						"Apakah Anda yakin akan menghapus? \nMenghapus Hak Akses akan menghapus Pegawai yang memililki Hak Akses ini.",
						"Konfirmasi Delete",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
					int itmp=table.convertRowIndexToModel(i);
					ODocument tmp=(ODocument) tableModel.getModels().get(itmp	);
					try {
//						tmp.delete();
						tableModel.getDao().delete(db, tmp);
						tableModel.getModels().remove(itmp);
						tableModel.fireTableDataChanged();
						selected();
					} catch (Exception e) {
						App.printErr(e);
					}
				}
			} 
		}

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
			
			TableColumnExt tcx=t.getColumnExt(x[1]);
			if (tcx!=null) {
				tcx.setVisible(true);
			}
			
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
