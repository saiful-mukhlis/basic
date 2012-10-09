package com.bmb.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Column;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.Painter;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import org.basic.comp.base.Window;

import z.gen.CreateDb;
import z.gen.CreateField;
import z.gen.CreateFieldOd;
import z.gen.CreateMenuItem;
import z.gen.CreateNewTextField;
import z.gen.CreateSetTextField;
import z.gen.CreateSetTextFieldBG;
import z.gen.CreateSetTextFieldWithModel;
import z.gen.CreateTextField;

import com.basic.annotation.db.Type;
import com.basic.db.Grp;
import com.basic.db.Logdb;
import com.basic.db.Usr;
import com.basic.view.screen.SplashScreen;
import com.global.App;
import com.jgoodies.looks.FontPolicies;
import com.jgoodies.looks.FontPolicy;
import com.jgoodies.looks.FontSet;
import com.jgoodies.looks.FontSets;
import com.jgoodies.looks.Fonts;
import com.jgoodies.looks.Options;
import com.jgoodies.looks.windows.WindowsLookAndFeel;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.db.record.ORecordLazyList;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class TestW {
	public static void main(String[] args) {

		m1();


	}
	
	
	public static void m3(){
		new CreateSetTextFieldWithModel(Grp.class);
	}
	
	public static void m2(){
		ODatabaseDocumentTx db = App.getDbd();
		ODatabaseRecordThreadLocal.INSTANCE.set(db);
		List<ODocument> tmps=App.getGrpDao().getAll(db);
		for (ODocument o : tmps) {
			o.setLazyLoad(false);
			if (o.isLazyLoad()) {
				System.out.println("lazy");
			}
			System.out.println(o);
			String ox=o.field(Grp.CREATE_BY);
//			System.out.println(ox);
			List<ODocument> o1=o.field(Grp.USRS);
			if (o1!=null) {
				for (ODocument o2 : o1) {
					System.out.println(o2.toJSON());
				}
			}
		}
		for (ODocument o : tmps) {
			List<ODocument> o1=o.field(Grp.USRS);
			if (o1!=null) {
				for (ODocument o2 : o1) {
					System.out.println(o2.toJSON());
				}
			}
		}
		
		db.close();
		
		ODatabaseDocumentTx db2 = App.getDbd();
		ODatabaseRecordThreadLocal.INSTANCE.set(db2);
		for (ODocument o : tmps) {
			List<ODocument> o1=o.field(Grp.USRS);
			if (o1!=null) {
				for (ODocument o2 : o1) {
					System.out.println(o2.toJSON());
				}
			}
		}
		db2.close();
	}
	
	public static void m1(){
		try {
			UIManager.setLookAndFeel("com.jgoodies.looks.windows.WindowsLookAndFeel");

			UIManager.put("TaskPaneContainer.useGradient", Boolean.FALSE);
			UIManager.put("TaskPaneContainer.background", Color.WHITE);

			UIManager.put("TaskPane.titleForeground", Color.WHITE);
			UIManager.put("TaskPane.titleOver", Color.LIGHT_GRAY.darker());
			UIManager.put("TaskPane.background", Color.WHITE);

			UIManager.put("TaskPane.titleBackgroundGradientStart", Color.BLACK);
			UIManager.put("TaskPane.titleBackgroundGradientEnd", Color.BLACK);
			
			UIManager.put("SplitPaneDivider.border", App.borderWhite);
			UIManager.put("SplitPaneDivider.draggingColor", App.aqua);
			
			
			UIDefaults ui = UIManager.getLookAndFeelDefaults();
			
//			ui.put("PopupMenu.background", Color.WHITE);
//			ui.put("PopupMenu.opaque", true);
//			ui.put("PopupMenu.contentMargins", null);
//			ui.put("RadioButtonMenuItem.background", Color.WHITE);
//			ui.put("RadioButtonMenuItem.opaque", true);
//			ui.put("CheckBoxMenuItem.background", Color.WHITE);
//			ui.put("CheckBoxMenuItem.opaque", true);
//			ui.put("MenuItem.background", Color.WHITE);
//			ui.put("MenuItem.opaque", true);
			
			ui.put("PopupMenuSeparator.background", Color.RED);
//			ui.put("PopupMenuSeparator.disabled", Color.RED);
//			ui.put("PopupMenuSeparator.opaque", false);
//			
//			ui.put("PopupMenuSeparator[Enabled].backgroundPainter", null);
			
//			ui.put("control", Color.RED);
//			ui.put("Separator.background", Color.RED);
//			ui.put("Separator.opaque", false);
//			ui.put("Separator[Enabled].backgroundPainter", null);
//			
//			ui.put("PopupMenuSeparator.contentMargins", null);
//			ui.put("Separator.contentMargins", null);
			
			
			ui.put("Menu.background", Color.WHITE);
			
			ui.put("OptionPane.background", Color.WHITE);
//			ui.put("MenuBar:Menu[Selected].backgroundPainter", Color.WHITE);
//			ui.put("MenuBar[Enabled].backgroundPainter", Color.WHITE);
//			ui.put("MenuBar[Enabled].borderPainter", Color.WHITE);
			ui.put("MenuBar.opaque", true);
			
			ui.put("Panel.background", Color.WHITE);
			ui.put("RootPane.background", Color.WHITE);
			ui.put("DesktopPane.background", Color.WHITE);
			
			ui.put("Menu.opaque", true);
			ui.put("Menu.foreground", Color.BLACK); 

			
			ui.put("TabbedPane.background", Color.WHITE);
//			ui.put( "ScrollBar.thumb", Color.blue ); 
			
//			UIManager.put("ScrollBar.background", Color.BLACK);
//			UIManager.put("ScrollBar:ScrollBarTrack[Disabled].backgroundPainter", null);
//			UIManager.put("scrollbar", Color.BLACK);
			
			UIManager.put("jgoodies.popupDropShadowEnabled", Boolean.TRUE);
//			UIManager.put(
//				      com.jgoodies.looks.Options.DEFAULT_ICON_SIZE_KEY, 
//				      new Dimension(18, 18));
			//Options.setDefaultIconSize(new Dimension(18, 18));
			Options.setSelectOnFocusGainEnabled(true);
			
			System.setProperty("Windows.controlFont", "Segoe UI-plain-15");
			
			Font controlFont = Fonts.WINDOWS_VISTA_96DPI_NORMAL;
			FontSet fontSet = FontSets.createDefaultFontSet(controlFont);
			FontPolicy fontPolicy = FontPolicies.createFixedPolicy(fontSet);
			WindowsLookAndFeel.setFontPolicy(fontPolicy);
			WindowsLookAndFeel.setFontPolicy(FontPolicies.getLooks1xWindowsPolicy());

		} catch (Exception e) {
			App.printErr(e);
		}
		
		
		
		try {

			SplashScreen splash = new SplashScreen();
			splash.setVisible(true);
			splash.getBar().setValue(30);
			Window w = new Window();
			splash.getBar().setValue(40);
			w.setProgressBar(splash.getBar());
			ODatabaseDocumentTx db = App.getDbd();
			ODatabaseRecordThreadLocal.INSTANCE.set(db);
			splash.getBar().setValue(70);
			w.build(db);



			db.close();
			splash.setVisible(false);
			splash.dispose();

		} catch (Exception e) {
			App.showErrSementara(e.getMessage());
			App.printErr(e);
			System.exit(0);
		}
	}

	
}
