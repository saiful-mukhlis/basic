package com.basic.view.edit;


import org.basic.comp.listener.WidgetInterface;

import com.basic.comp.impl.master.GrpM;
import com.basic.dao.impl.GrpDao;
import com.basic.db.Grp;
import com.basic.lang.LGrp;
import com.basic.view.def.GrpC;
import com.global.App;
import com.global.DataUser;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class GrpE extends GrpC  {
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = GrpM.TITLE_EDIT;
		icon = GrpM.ICON_16;
		
		buildLabel(db);
		buildForm(db);
		buildPanel();
		
		setFocusEnter();
		actionReset();
	}
	
	
	protected ODocument model;

	public void load(ODocument model) {
		if (model==null) {
			resetContentComponent();
		}else
		if (modelIsTrue(model)) {
			this.model=model;
			setContentComponent(model);
		}
	}

	@Override
	public void buildForm(ODatabaseDocumentTx db) {
		super.buildForm(db);
		buildButton(db);
	}



	@Override
	public void buildButton(ODatabaseDocumentTx db) {
		super.buildButton(db);
		builder.append( save, 7, 7);
	}
	
	
	public void setFocusEnter() {
		setFocusEnter(code, name);
		setFocusEnter(name, note);
		setFocusEnter(note, save);
		setFocusEnter(save, code);
	}
	
	
	public boolean validate(ODatabaseDocumentTx db){
		if (DataUser.getUsr()!=null) {
			GrpDao d=App.getGrpDao();
			if (!d.vCode(db, code, model)) {
				return false;
			}
			if (!d.vName(db, name, model)) {
				return false;
			}
		}else{
			return false;
		}
		
		return true;
	}
	public void actionSave() {

		ODatabaseDocumentTx db=App.getDbd();
		ODatabaseRecordThreadLocal. INSTANCE.set(db);
		if (validate(db)) {
			
			ODocument o=model;
			GrpDao d=App.getGrpDao();
			d.setCode(o, code);
			d.setName(o, name);
			d.setNote(o, note);
			
			try {
				o.save();
				for (WidgetInterface w: widgets) {
					w.modelWidgetChange(o);
					this.model=o;
				}
				App.showSaveOk();
				
			} catch (Exception e) {
				
			}finally{
				db.close();
			}
			
		}
	
		
	}


	
}
