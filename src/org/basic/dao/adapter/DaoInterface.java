package org.basic.dao.adapter;

import java.util.HashSet;
import java.util.List;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.db.record.ORecordLazyList;
import com.orientechnologies.orient.core.record.impl.ODocument;

public interface DaoInterface {
	public ODocument save(ODatabaseDocumentTx db, ODocument model);
	public ODocument update(ODatabaseDocumentTx db, ODocument model);
	public ODocument update(ODatabaseDocumentTx db, ODocument model, String jsonOld);
	public ODocument delete(ODatabaseDocumentTx db,ODocument o);
	public List<ODocument> getAll(ODatabaseDocumentTx db);
	public List<ODocument> getAll(ODatabaseDocumentTx db, int start, int end);
	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom, Object value);
	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom, String value);
	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom, Object value, int start, int end);
	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom, String value, int start, int end);
	public ODocument getOne(ODatabaseDocumentTx db, String kolom, String value);
	public ODocument getOne(ODatabaseDocumentTx db, String kolom, Object value, String kolom2, Object value2, String operator);
	public ODocument getOne(ODatabaseDocumentTx db, String kolom, Object value, String kolom2, Object vallue2);
	public long getCount(ODatabaseDocumentTx db);
	public long getCount(ODatabaseDocumentTx db, String sql, String as);
	public long getCountByColumn(ODatabaseDocumentTx db, String colom, String value);
	public long getCountByColumn(ODatabaseDocumentTx db, String colom, Object value);
	public long getCountByColumn(ODatabaseDocumentTx db, String colom, String value, String colom2, String value2, String operator);
	public void printAll(ODatabaseDocumentTx db);
	public int delByColoumn(ODatabaseDocumentTx db, String colom, String value);
	public long truncetClass(ODatabaseDocumentTx db);
	public int truncetRecord(ODatabaseDocumentTx db, String rid);
	public long deleteAll(ODatabaseDocumentTx db);
	public String getClassName();
	public String getNameFielsToString();
	
	
	
	public List<ODocument> getLinkList(ODatabaseDocumentTx db, String linklist);
	public HashSet<ODocument> getLinkSet(ODatabaseDocumentTx db, ORecordLazyList linkset);
	
	
	
	/**
	 * Untuk mendapatkan List ODocument dengan parameter ODocument 
	 * yang akan di ubah menjadi attribut pencarian
	 * @param db
	 * @param o
	 * @return
	 */
	public List<ODocument> getAllSearch(ODatabaseDocumentTx db, ODocument o);
	public long getCountSearch(ODatabaseDocumentTx db, ODocument o);
	public List<ODocument> getCountAllSearch(ODatabaseDocumentTx db, ODocument o);
	public List<ODocument> getAllSearch(ODatabaseDocumentTx db, ODocument o,
			int tmp, int jumlahPerHalaman);
	public List<ODocument> getAllByColumnLike(ODatabaseDocumentTx db,
			String col, String value, int tmp, int jumlahPerHalaman);
	public List<ODocument> getAllByColumnLike(ODatabaseDocumentTx db,
			String col, String value);
	public long getCountByColumnLike(ODatabaseDocumentTx db, String col,
			String value);
	public String getFormatCode();
	public boolean isUseFormatCode();

}
