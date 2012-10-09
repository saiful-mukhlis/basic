package com.basic.dao.impl;


import java.util.Random;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;

import org.basic.dao.abst.DaoAbstract;

import com.basic.db.Bos;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class BosDao extends DaoAbstract {

	public BosDao() {
		super(Bos.TABLE);
	}

	public ODocument factoryModel(int id, String name, int jml) {
		ODocument o = new ODocument(getClassName());
		setId(o, id);
		setName(o, name);
		setJml(o, jml);
		return o;
	}

	public ODocument factoryModelUpdate(ODocument o,int id, String name, int jml) {
		setId(o, id);
		setName(o, name);
		setJml(o, jml);
		return o;
	}

	public void factoryFirst(ODatabaseDocumentTx db){
			ODocument o=factoryModel(1,"jmllog", 20000);
			o.save();
			
			o=factoryModel(2,creatRendom(), 0);
			o.save();
	}
	
	public String getName(ODocument o) {
		return o.field(Bos.NAME);
	}

	public ODocument setName(ODocument o,String name) {
		o.field(Bos.NAME, name);
		return o;
	}

	public int getJml(ODocument o) {
		return o.field(Bos.JML);
	}

	public ODocument setJml(ODocument o, int jml) {
		o.field(Bos.JML, jml, OType.INTEGER);
		return o;
	}

	public int getId(ODocument o) {
		return o.field(Bos.ID);
	}

	public ODocument setId(ODocument o, int id) {
		o.field(Bos.ID, id, OType.INTEGER);
		return o;
	}
	
	public boolean check(ODatabaseDocumentTx db){
		ODocument o=getOne(db, Bos.ID, 2);
		if (o==null) {
			factoryFirst(db);
			//baru pertama jadi belum reg
			return false;
		}
		int jumlahStatus=getJml(o);
		String kodenya=getName(o);
		if (jumlahStatus==1) {
			//sudah registrasi
			Preferences userPref = Preferences.userRoot();
			String x=userPref.get("ortptnk", "x");
			if (!x.equalsIgnoreCase(kodenya)) {
				if (jumlahStatus==1) {
					setName(o, creatRendom());
					setJml(o, 0);
					o.save();
				}
				//di hack
				return false;
			}
			return true;
		}
		return false;
	}
	
	public String creatRendom(){
		Random r = new Random();

		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZOIUYTR";
		StringBuilder tmp = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			tmp.append(alphabet.charAt(r.nextInt(alphabet.length())));
		}
		return tmp.toString();
	}
	
	public void minus(ODatabaseDocumentTx db){
		ODocument o=getOne(db, Bos.ID, 2);
		if (o!=null) {
//			String namaKode=getName(o);
			int jmlStatus=getJml(o);
			if (jmlStatus==0) {
				//belum reg
				ODocument o1=getOne(db, Bos.ID, 1);
				if (o1!=null) {
//					String namaKode1=getName(o1);
					int jmlStatus1=getJml(o1);
					if (jmlStatus1<0) {
						JOptionPane.showMessageDialog(null, "Masa Trial Sudah Habis");
						System.exit(0);
					}
					jmlStatus1--;
					setJml(o1, jmlStatus1);
					o1.save();
				}else{
					factoryFirst(db);
					db.close();
					System.exit(0);
				}
			}else{
				//System.exit(1);
			}
		}else{
			factoryFirst(db);
			db.close();
			System.exit(0);
		}
	}
}
