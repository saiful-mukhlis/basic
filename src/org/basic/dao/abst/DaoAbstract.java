package org.basic.dao.abst;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.basic.comp.model.ODocumentToString;
import org.basic.dao.adapter.DaoAdapter;
import org.basic.dao.adapter.DaoInterface;

import com.basic.db.NumberId;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.db.record.ORecordLazyList;
import com.orientechnologies.orient.core.iterator.ORecordIteratorClass;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.core.tx.OTransaction.TXTYPE;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class DaoAbstract extends DaoAdapter {
	public String className;

	public DaoAbstract(String table) {
		super();
		this.setClassName(table);
	}

	public ODocument save(ODatabaseDocumentTx db, ODocument model){
		String tmp=model.field("code");
		if (tmp==null || tmp.trim().equalsIgnoreCase("AUTO") || tmp.trim().equalsIgnoreCase("")) {
			ODocument num=App.getNumberIdDao().getLangByNamaTable(db, getClassName());
			if (num==null) {
				num=App.getNumberIdDao().createContentDefaultModel(db, getClassName(), getFormatCode(), isUseFormatCode());
			}
			long now=num.field(NumberId.NOW);
			boolean useFormat=num.field(NumberId.USE_FORMAT);
			String format=num.field(NumberId.FORMAT);
			if (format==null) {
				format="";
			}
			boolean ulang=true;
			String nowWithFormat="";
			while (ulang) {
				nowWithFormat=now+"";
				if (useFormat) {
					int panjangTanpaFormat=nowWithFormat.length();
					int panjangFormat=format.length();
					if (panjangTanpaFormat<panjangFormat) {
						nowWithFormat=format+nowWithFormat;
						int panjangSetelahFormat=nowWithFormat.length();
						nowWithFormat=nowWithFormat.substring(panjangSetelahFormat-panjangFormat, panjangSetelahFormat);
					}
				}
				ODocument od=getOne(db, "code", nowWithFormat);
				if (od!=null) {
					now++;
				}else{
					ulang=false;
				}
			}
			model.field("code", nowWithFormat);
			model.save();
			int aut=num.field("increment");
			now=now+aut;
			num.field("now", now , OType.LONG);
			num.save();
		}else{
			model.save();
		}
		return model;
	}

	public ORecordIteratorClass<ODocument> getAllLazy(ODatabaseDocumentTx db) {
		return db.browseClass(getClassName());
	}

	public List<ODocument> getAll(ODatabaseDocumentTx db) {
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(getClassName());
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1"))
				.execute();
		return result;
	}

	public List<ODocument> getAllAsc(ODatabaseDocumentTx db, String by) {
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" order by ");
		sql.append(by);
		sql.append(" asc ");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1"))
				.execute();
		return result;
	}

	public List<ODocument> getAll(ODatabaseDocumentTx db, int start, int end) {
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" skip " + start + " limit " + end);
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1"))
				.execute();
		return result;
	}

	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom,
			String value, int start, int end) {
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kolom);
		sql.append(" = ? ");
		sql.append(" skip " + start + " limit " + end);
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1"))
				.execute(value);
		return result;
	}

	@Override
	public List<ODocument> getAllByColumnLike(ODatabaseDocumentTx db,
			String col, String value) {
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(col);
		sql.append(".toLowerCase()");
		sql.append(" = ? ");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1"))
				.execute(value.toLowerCase());
		return result;
	}

	public List<ODocument> getAllByColumnLike(ODatabaseDocumentTx db,
			String kolom, String value, int start, int end) {
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kolom);
		sql.append(".toLowerCase()");
		sql.append(" like ? ");
		sql.append(" skip " + start + " limit " + end);
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1"))
				.execute(value.toLowerCase());
		return result;
	}

	public List<ODocument> getAllByColumnLike(ODatabaseDocumentTx db,
			String kolom, String value, String kolom2, String value2,
			int start, int end) {
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kolom);
		sql.append(".toLowerCase()");
		sql.append(" like ? or ");
		sql.append(kolom2);
		sql.append(".toLowerCase()");
		sql.append(" like ? ");
		sql.append(" skip " + start + " limit " + end);
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1"))
				.execute(value.toLowerCase(), value2.toLowerCase());
		return result;
	}

	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom,
			Object value, int start, int end) {
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kolom);
		sql.append(" = ? ");
		sql.append(" skip " + start + " limit " + end);
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1"))
				.execute(value);
		return result;
	}

	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom,
			String value) {
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kolom);
		sql.append(" = ? ");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1"))
				.execute(value);
		return result;
	}

	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom,
			Object value) {
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kolom);
		sql.append(" = ? ");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1"))
				.execute(value);
		return result;
	}

	public ODocument getOneByRid(ODatabaseDocumentTx db, String rid) {
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(rid);
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query).execute();
		if (result.size() == 0) {
			return null;
		} else {
			return (ODocument) result.get(0);
		}
	}

	public ODocument getOne(ODatabaseDocumentTx db, String kolom, String value) {
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kolom);
		sql.append(" = ? limit 1");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query).execute(value);
		if (result.size() == 0) {
			return null;
		} else {
			return (ODocument) result.get(0);
		}
	}

	public ODocument getOne(ODatabaseDocumentTx db, String kolom, Object value) {
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kolom);
		sql.append(" = ? limit 1");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query).execute(value);
		if (result.size() == 0) {
			return null;
		} else {
			return (ODocument) result.get(0);
		}
	}

	public List<ODocument> getAllLazy(ODatabaseDocumentTx db, int start, int end) {
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" skip " + start + " limit " + end);
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query).execute();
		return result;
	}

	// public T getOne(OObjectDatabaseTx db, String kolom, Object value, String
	// kolom2, Object vallue2, String operator) {
	// StringBuilder sql=new StringBuilder("select * from ");
	// sql.append(className);
	// sql.append(" where ");
	// sql.append(kolom);
	// sql.append(" = ? ");
	// sql.append(operator);
	// sql.append(" ");
	// sql.append(kolom2);
	// sql.append(" = ? limit 1");
	// OSQLSynchQuery<T> query = new OSQLSynchQuery<T>(sql.toString());
	// List<T> result = db.command(query).execute(value, vallue2);
	// if (result.size() == 0) {
	// return null;
	// } else {
	// return (T) result.get(0);
	// }
	// }

	public ODocument getOne(ODatabaseDocumentTx db, String kolom, Object value,
			String kolom2, Object value2, String operator) {
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kolom);
		sql.append(" = ? ");
		sql.append(operator);
		sql.append(" ");
		sql.append(kolom2);
		sql.append(" = ? limit 1");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query).execute(value, value2);
		if (result.size() == 0) {
			return null;
		} else {
			return (ODocument) result.get(0);
		}
	}

	// public T getOne(OObjectDatabaseTx db, String kolom, Object value, String
	// kolom2, Object vallue2) {
	// return getOne(db, kolom, value, kolom2, vallue2, "and");
	// }

	public ODocument getOne(ODatabaseDocumentTx db, String kolom, Object value,
			String kolom2, Object vallue2) {
		return getOne(db, kolom, value, kolom2, vallue2, "and");
	}

	public long getCount(ODatabaseDocumentTx db) {
		return db.countClass(getClassName());
	}

	public long getCount(ODatabaseDocumentTx db, String sql, String as) {
		List<ODocument> result = db.query(new OSQLSynchQuery<ODocument>(sql));
		try {
			long count = Long.parseLong(result.get(0).field(as).toString());
			return count;
		} catch (Exception e) {
			App.printErr(e);
			return 0;
		}
	}

	@Override
	public long getCountByColumnLike(ODatabaseDocumentTx db, String col,
			String value) {
		StringBuilder sql = new StringBuilder("select count(*) as x from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(col);
		sql.append(".toLowerCase()");
		sql.append(" like ?");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query).execute(value.toLowerCase());
		try {
			long count = Long.parseLong(result.get(0).field("x").toString());
			return count;
		} catch (Exception e) {
			App.printErr(e);
			return 0;
		}
	}

	public long getCountByColumn(ODatabaseDocumentTx db, String colom,
			String value) {
		StringBuilder sql = new StringBuilder("select count(*) as x from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(colom);
		sql.append(" = ?");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query).execute(value);
		try {
			long count = Long.parseLong(result.get(0).field("x").toString());
			return count;
		} catch (Exception e) {
			App.printErr(e);
			return 0;
		}
	}

	public long getCountByColumn(ODatabaseDocumentTx db, String colom,
			Object value) {
		StringBuilder sql = new StringBuilder("select count(*) as x from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(colom);
		sql.append(" = ?");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query).execute(value);
		try {
			long count = Long.parseLong(result.get(0).field("x").toString());
			return count;
		} catch (Exception e) {
			App.printErr(e);
			return 0;
		}
	}

	public long getCountByColumn(ODatabaseDocumentTx db, String colom,
			String value, String colom2, String value2, String operator) {
		StringBuilder sql = new StringBuilder("select count(*) as x from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(colom);
		sql.append(" = ?");
		sql.append(" ");
		sql.append(operator);
		sql.append(" ");
		sql.append(colom);
		sql.append(" = ?");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query).execute(value, value2);
		try {
			long count = Long.parseLong(result.get(0).field("x").toString());
			return count;
		} catch (Exception e) {
			App.printErr(e);
			return 0;
		}
	}

	public void printAll(ODatabaseDocumentTx db) {
		for (ODocument obj : db.browseClass(getClassName())) {
			App.info(obj.toJSON());
		}
	}

	public long truncetClass(ODatabaseDocumentTx db) {
		return db.command(new OCommandSQL("TRUNCATE CLASS " + getClassName()))
				.execute();
	}

	/**
	 * contoh dari sql TRUNCATE RECORD [20:0, 20:1, 20:2], TRUNCATE RECORD 20:3
	 * 
	 * @param db
	 * @param rid
	 * @return
	 */
	public int truncetRecord(ODatabaseDocumentTx db, String rid) {
		return db.command(new OCommandSQL("TRUNCATE CLASS " + rid)).execute();
	}

	public long deleteAll(ODatabaseDocumentTx db) {
		return truncetClass(db);
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * menghapus data yang kosong
	 * 
	 * @param db
	 * @param namaColom
	 * @return
	 */
	public int delNull(ODatabaseDocumentTx db) {
		StringBuilder sql = new StringBuilder("delete from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append("x");
		sql.append(" = ? ");
		OCommandSQL query = new OCommandSQL(sql.toString());
		int tmp = db.command(query).execute(1);
		return tmp;
	}

	public int delByColoumn(ODatabaseDocumentTx db, String namaColom,
			Object value) {
		StringBuilder sql = new StringBuilder("delete from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(namaColom);
		sql.append(" = ? ");
		OCommandSQL query = new OCommandSQL(sql.toString());
		return db.command(query).execute(value);
	}

	/**
	 * sql delete dengan operator = untuk parameter colom harus lowerCase
	 * 
	 * @param db
	 * @param colom
	 * @param value
	 * @return int (jumlah data yang di manipulasi/ di delete)
	 */
	public int delByColoumn(ODatabaseDocumentTx db, String colom, String value) {
		StringBuilder sql = new StringBuilder("delete from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(colom);
		sql.append(" = ?");
		OCommandSQL query = new OCommandSQL(sql.toString());
		return db.command(query).execute(value);
	}

	public ODocument delete(ODatabaseDocumentTx db, ODocument o) {
		try {
			db.begin(TXTYPE.OPTIMISTIC);
			if (beforeDelete(db, o)) {
				o.delete();
			}
			db.commit();
		} catch (Exception e) {
			db.rollback();
		}
		afterDelete(db, o);
		return o;
	}

	public void afterDelete(ODatabaseDocumentTx db, ODocument o) {

	}

	public boolean beforeDelete(ODatabaseDocumentTx db, ODocument o) {
		return true;
	}

	public ODocument setNull(ODatabaseDocumentTx db, ODocument o) {
		o.field("x", 1, OType.INTEGER);
		o.save();
		return o;
	}

	public String getNameFielsToString() {
		return null;
	}

	// public List<T> getResult(OObjectDatabaseTx db, OSQLSynchQuery<T> query) {
	// return db.query(query);
	// }
	//
	// public List<T> getAll(OObjectDatabaseTx db) {
	// StringBuilder sql=new StringBuilder("select * from ");
	// sql.append(className);
	// OSQLSynchQuery<T> query = new OSQLSynchQuery<T>(sql.toString());
	// return getResult(db, query);
	// }

	// public T getOne(OObjectDatabaseTx db, String kolom, String value) {
	// StringBuilder sql=new StringBuilder("select * from ");
	// sql.append(className);
	// sql.append(" where ");
	// sql.append(kolom);
	// sql.append(" = ? limit 1");
	// OSQLSynchQuery<T> query = new OSQLSynchQuery<T>(sql.toString());
	// List<T> result = db.command(query).execute(value);
	// if (result.size() == 0) {
	// return null;
	// } else {
	// return (T) result.get(0);
	// }
	// }

	public boolean validate(ODatabaseDocumentTx db, JTextField field,
			String namaField) {
		if (field.getText().trim().equalsIgnoreCase("")) {
			App.showErrorFieldEmpty(db, namaField);
			field.requestFocus();
			return false;
		}
		return true;
	}

	public boolean validate(ODatabaseDocumentTx db, JComboBox box,
			String namaField) {
		try {
			if (box.getSelectedItem() == null
					|| ((ODocumentToString) box.getSelectedItem()).getO() == null) {
				App.showErrorFieldEmpty(db, namaField);
				box.requestFocus();
				return false;
			}
		} catch (Exception e) {
			App.printErr(e);
		}
		return true;
	}

	public boolean validate(ODatabaseDocumentTx db, JPasswordField field,
			JPasswordField field2, String namaField) {
		if (field.getPassword().length > 0) {
			if (field.getPassword().length == field2.getPassword().length
					&& Arrays.equals(field.getPassword(), field2.getPassword())) {

			} else {
				App.showErrorPasswordTidakSamadenganKonfirmasi();
				field2.requestFocus();
				return false;
			}
		} else {
			App.showErrorFieldEmpty(db, namaField);
			field.requestFocus();
			return false;
		}
		return true;
	}

	public boolean validate(ODatabaseDocumentTx db, DaoInterface dao,
			JTextField field, String namaField, String uniqueField) {
		long tmp = dao.getCountByColumn(db, uniqueField, field.getText());
		if (tmp > 0) {
			App.showErrorDataSudahAda(db, namaField);
			field.requestFocus();
			return false;
		}
		return true;
	}

	public boolean validate(ODatabaseDocumentTx db, JTextArea field,
			String namaField) {
		if (field.getText().trim().equalsIgnoreCase("")) {
			App.showErrorFieldEmpty(db, namaField);
			return false;
		}
		return true;
	}

	@Override
	public List<ODocument> getLinkList(ODatabaseDocumentTx db, String linklist) {
		StringBuilder sql = new StringBuilder("select from ?");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query).execute(linklist);
		return result;
	}

	@Override
	public HashSet<ODocument> getLinkSet(ODatabaseDocumentTx db,
			ORecordLazyList linkset) {
		StringBuilder sql = new StringBuilder("select from ");
		sql.append(linkset);
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(
				sql.toString());
		List<ODocument> result = db.command(query).execute();
		return new HashSet<>(result);
	}

}
