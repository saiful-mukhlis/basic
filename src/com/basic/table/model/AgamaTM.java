package com.basic.table.model;

import org.basic.comp.abst.TableModelDefault;

import com.basic.db.Agama;
import com.basic.lang.LAgama;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class AgamaTM  extends TableModelDefault{

	public AgamaTM(ODatabaseDocumentTx db) {
		super(db);
	}
	protected final int NO = 0;
	protected final int CODE = 1;
	protected final int NAMA = 2;
	

	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[3];
		namaKolom[NO]=LAgama.NO;
		namaKolom[CODE]=LAgama.CODE;
		namaKolom[NAMA]=LAgama.NAMA;
		
	}

	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ODocument m=model.get(rowIndex);
		if (columnIndex == NO) {
			return getNo(rowIndex);
		} else if (columnIndex == CODE) {
			return m.field(Agama.CODE);
		}
		 else if (columnIndex == NAMA) {
				return m.field(Agama.NAMA);
			}
		else {
			return null;
		}
	}

	@Override
	public void initDao() {
		dao=App.getAgamaDao();
	}
	
	

}
