package org.basic.comp.base;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.basic.comp.abst.WindowAbstract;
import org.basic.comp.adapter.HakAksesListener;
import org.noos.xing.mydoggy.Content;
import org.noos.xing.mydoggy.ContentManager;
import org.noos.xing.mydoggy.mydoggyset.action.AddContentAction;
import org.noos.xing.yasaf.plaf.action.ViewContextAction;

import com.basic.comp.impl.master.GrpM;
import com.basic.comp.impl.master.JenisPekerjaanM;
import com.basic.comp.impl.master.UsrM;
import com.basic.lang.L;
import com.basic.lang.LWindow;
import com.global.App;
import com.global.DataUser;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class Window extends WindowAbstract{
	
	private List<Komponent> komponents=new ArrayList<Komponent>();
	private List<HakAksesListener> cangeHakAkses = new ArrayList<HakAksesListener>();
	private HashMap<String, Komponent> komponentMaps=new HashMap<>();
	
	
	protected AddContentAction windowAca;
	protected ViewContextAction windowVca;
	
	protected Component welcomeComponent;
	
	
	public static String PEGAWAI=UsrM.class.getName();
	public static String HAK_AKSES=GrpM.class.getName();
	public static String JENIS_PEKERJAAN=JenisPekerjaanM.class.getName();
	
	@Override
	public void buildMaster(ODatabaseDocumentTx db) {
		
		Komponent pegawai=new Komponent(this, PEGAWAI, UsrM.TITLE, 'P', UsrM.ICON_16, UsrM.ICON_32);
		Komponent hakAkses=new Komponent(this, HAK_AKSES, LWindow.HAK_AKSES, 'H', GrpM.ICON_16, GrpM.ICON_32);
		Komponent jenisPekerjaan=new Komponent(this, JENIS_PEKERJAAN, LWindow.JENIS_PEKERJAAN, 'H', JenisPekerjaanM.ICON_16, JenisPekerjaanM.ICON_32);
		
		komponentMaps.put(PEGAWAI, pegawai);
		komponentMaps.put(HAK_AKSES, hakAkses);
		komponentMaps.put(JENIS_PEKERJAAN, jenisPekerjaan);
		
		komponents.add(pegawai);
		komponents.add(hakAkses);
		komponents.add(jenisPekerjaan);
		
		
		
		for (Komponent komponent : getKomponents()) {
			cangeHakAkses.add(komponent.getHakAksesListener());
		}
		
		
		JPanel panel = new ImagePanel(
				new FlowLayout(FlowLayout.CENTER, 50, 180));
		welcomeComponent = panel;

		
		windowAca = factoryContentAction(db, "Welcome", App.getIcon(L.iconApp16),
				welcomeComponent, (int) 'W');
		
	}


	@Override
	public void buildActions(ODatabaseDocumentTx db) {
		for (Komponent komponent : getKomponents()) {
			komponent.setView(db, komponent.getTypeMaster(), myDoggySetContext);
		}
		
		windowVca = new ViewContextAction(
				"Welcome", App.getIcon(L.iconApp16),
				myDoggySetContext, ImagePanel.class);
		
	}
	
	@Override
	public void initContext(ODatabaseDocumentTx db) {
		myDoggySetContext = new Context(this, toolWindowManager, frame);
	}
	

	@Override
	public void initToolbar(ODatabaseDocumentTx db) {
		setToolbar(new Toolbar());
		getToolbar().setWindow(this);
		getToolbar().build(db);
		frame.getContentPane().add(getToolbar().getPanel(), BorderLayout.NORTH);
		cangeHakAkses.add(toolbar);
		toolbar.getPanel().setVisible(false);
	}

	@Override
	public void initMenu(ODatabaseDocumentTx db) {
		menu=new Menu();
		menu.setWindow(this);
		menu.build(db);
		cangeHakAkses.add(menu);
		frame.setJMenuBar(menu.getMenu());
	}


	@Override
	public void showWelcome() {
		myDoggySetContext.put(ImagePanel.class, null);
		
	}

	@Override
	public Component getComponentWelcome() {
		return welcomeComponent;
	}

	@Override
	public void seta(ODatabaseDocumentTx db) {
		bosOne=new BosOne();
		bosOne.seta(db);
		
	}



	@Override
	public void load(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modelWidgetChange(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
		// TODO Auto-generated method stub
		
	}


	
	public AddContentAction getWelcomeAca() {
		return windowAca;
	}


	@Override
	public void showToolbar() {
		if (toolbar.getPanel().isVisible()) {
			toolbar.getPanel().setVisible(false);
		}else{
			toolbar.getPanel().setVisible(true);
		}
		
	}


	public List<Komponent> getKomponents() {
		return komponents;
	}


	public void setKomponents(List<Komponent> komponents) {
		this.komponents = komponents;
	}


	public void login() {
		
		if (DataUser.getUsr() == null) {
			LoginDialog form = new LoginDialog();
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			form.buildComponent(db);
			db.close();
			JDialog d = new JDialog(frame);
			d.setModalityType(ModalityType.APPLICATION_MODAL);
			d.getContentPane().add(form.getPanel());
			d.pack();
			setCenterDialog(d);
			d.setVisible(true);
		} else {
			//logout
			DataUser.setUsr(null);
			DataUser.setGrp(null);
			closeAllWindow();
			
		}
		DataUser.setAkses();
		for (HakAksesListener hakAksesListener : cangeHakAkses) {
			if (hakAksesListener!=null) {
				hakAksesListener.changeHakAkses();
			}
		}
	}
	
	public void closeAllWindow() {
		toolWindowManager.getContentManager().removeAllContents();

	}
	
	public void setCenterDialog(JDialog d) {
		d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - d
				.getPreferredSize().width) / 2, (Toolkit.getDefaultToolkit()
				.getScreenSize().height - d.getPreferredSize().height) / 2);

	}


	public HashMap<String, Komponent> getKomponentMaps() {
		return komponentMaps;
	}


	public void setKomponentMaps(HashMap<String, Komponent> komponentMaps) {
		this.komponentMaps = komponentMaps;
	}
	
	public Komponent getKomponentSeledcted(){
		return komponentMaps.get(idSelected);
	}
	
	public void actionClose(){
		ContentManager c=toolWindowManager.getContentManager();
		Content co=c.getSelectedContent();
		if (co!=null) {
			c.removeContent(co);
		}
	}


	@Override
	public void actionExit() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPrint() {
		// TODO Auto-generated method stub
		Komponent komponent=getKomponentSeledcted();
		if (komponent!=null) {
			komponent.getWidgetTop().actionPrint();
		}
	}


	@Override
	public void actionAdd() {
		// TODO Auto-generated method stub
		Komponent komponent=getKomponentSeledcted();
		if (komponent!=null) {
			komponent.getWidgetTop().actionAdd();
		}
	}


	@Override
	public void actionEdit() {
		// TODO Auto-generated method stub
		Komponent komponent=getKomponentSeledcted();
		if (komponent!=null) {
			komponent.getWidgetTop().actionEdit();
		}
	}


	@Override
	public void actionDel() {
		// TODO Auto-generated method stub
		Komponent komponent=getKomponentSeledcted();
		if (komponent!=null) {
			komponent.getWidgetTop().actionDel();
		}
	}


	@Override
	public void actionView() {
		// TODO Auto-generated method stub
		Komponent komponent=getKomponentSeledcted();
		if (komponent!=null) {
			komponent.getWidgetTop().actionView();
		}
	}
	
	@Override
	public void actionReload() {
		// TODO Auto-generated method stub
		Komponent komponent=getKomponentSeledcted();
		if (komponent!=null) {
			komponent.getWidgetTop().actionReload();
		}
	}
	
	public void actionReg(){
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		FormRegistrasi form=new FormRegistrasi();
		form.buildComponent(db);
		JDialog d = new JDialog(getWindow(getPanel()), ModalityType.APPLICATION_MODAL);
		d.setIconImage(App.getImage(L.iconApp16).getImage());
		d.getContentPane().add(form.getPanel());
		d.setFocusTraversalPolicy(form.getFocus());
		d.pack();
		setCenterDialog(d);
		d.setVisible(true);
		
		db.close();
	}

	public void actionAbout(){
		AboutDialog form=new AboutDialog();
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		form.buildComponent(db);
		JDialog d = new JDialog(getWindow(getPanel()), ModalityType.APPLICATION_MODAL);
		d.setIconImage(App.getImage(L.iconApp16).getImage());
		d.getContentPane().add(form.getPanel());
		d.setSize(480, 360);
		//d.pack();
		d.setResizable(false);
		setCenterDialog(d);
		d.setVisible(true);
	}
	
	public java.awt.Window getWindow(Object o){
		if (o instanceof Window) {
			return ((java.awt.Window) o);
		} else {
			if (o instanceof Component) {
				return  getWindow(((Component) o).getParent());
			}else{
				return null;
			}
		}
	}





	

}
