package com.basic.table.model;

import org.basic.comp.abst.TableModelDefault;

import com.basic.db.JenisPekerjaan;
import com.basic.lang.LJenisPekerjaan;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class JenisPekerjaanTM  extends TableModelDefault{

	public JenisPekerjaanTM(ODatabaseDocumentTx db) {
		super(db);
	}
	protected final int NO = 0;
	protected final int CODE = 1;
	protected final int NAMA = 2;
	

	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[3];
		namaKolom[NO]=LJenisPekerjaan.NO;
		namaKolom[CODE]=LJenisPekerjaan.CODE;
		namaKolom[NAMA]=LJenisPekerjaan.NAMA;
		
	}

	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ODocument m=model.get(rowIndex);
		if (columnIndex == NO) {
			return getNo(rowIndex);
		} else if (columnIndex == CODE) {
			return m.field(JenisPekerjaan.CODE);
		}
		 else if (columnIndex == NAMA) {
				return m.field(JenisPekerjaan.NAMA);
			}
		else {
			return null;
		}
	}

	@Override
	public void initDao() {
		dao=App.getJenisPekerjaanDao();
	}
	
	

}
