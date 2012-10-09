package org.basic.comp.base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.plaf.basic.BasicMenuBarUI;

import org.basic.comp.adapter.MenuAdapter;
import org.basic.comp.adapter.WindowInterfaces;
import org.basic.comp.listener.MasterInterface;

import com.basic.comp.impl.master.GrpM;
import com.basic.comp.impl.master.JenisPekerjaanM;
import com.basic.comp.impl.master.UsrM;
import com.basic.icon.IconBase;
import com.basic.lang.L;
import com.basic.lang.LMenu;
import com.basic.lang.LWindow;
import com.global.App;
import com.jgoodies.looks.BorderStyle;
import com.jgoodies.looks.HeaderStyle;
import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class Menu extends MenuAdapter {

	@Override
	public void changeHakAkses() {
		usr.setEnabled(UsrM.VIEW);
		grp.setEnabled(GrpM.VIEW);
		jenisPekerjaan.setEnabled(JenisPekerjaanM.VIEW);
		jenisToko.setEnabled(JenisPekerjaanM.VIEW);
		statusPelanggan.setEnabled(JenisPekerjaanM.VIEW);
		
		setFalseAll();
		if (com.global.DataUser.getUsr()!=null) {
			login.setText(L.logout);
			login.setIcon(IconBase.LOGOUT);
		}else{
			login.setText(L.login);
			login.setIcon(IconBase.LOGIN);
		}
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		init();
		changeHakAkses();
	}

	@Override
	public JMenuBar getMenu() {
		// TODO Auto-generated method stub
		return menu;
	}

	@Override
	public void setWindow(WindowInterfaces window) {
		// TODO Auto-generated method stub
		this.window=window;
	}

	private JMenuBar menu;

	protected JMenu file;
	protected JMenuItem login;
	protected JMenuItem close;
	protected JMenuItem print;
	protected JMenuItem exit;
	protected JMenuItem reload;
	

	protected JMenu editMenu;
	protected JMenuItem add;
	protected JMenuItem edit;
	protected JMenuItem del;
	protected JMenuItem view;
	
	

	protected JMenu master;
	protected JMenuItem grp;
	protected JMenuItem usr;
	protected JMenuItem pelanggan;
	protected JMenuItem paket;
	protected JMenuItem jenisPekerjaan;
	protected JMenuItem jenisToko;
	protected JMenuItem statusPelanggan;

	protected JMenu input;
	
	

	protected JMenu laporan;
	
	
	

	protected JMenu setting;

	protected JMenu help;
	protected JMenuItem registrasi;
	protected JMenuItem about;
	

	private WindowInterfaces window;
	
	

	public void init() {
		menu = new JMenuBar();
		menu.setUI ( new BasicMenuBarUI ()
	    {
	        public void paint ( Graphics g, JComponent c )
	        {
	            g.setColor ( Color.WHITE );
	            g.fillRect ( 0, 0, c.getWidth (), c.getHeight () );
	        }
	    } );
		menu.putClientProperty (Options.HEADER_STYLE_KEY, HeaderStyle.BOTH);
		menu.putClientProperty (Options.HEADER_STYLE_KEY, HeaderStyle.SINGLE);
		menu.putClientProperty(Options.NO_ICONS_KEY, Boolean.TRUE);
		menu.putClientProperty(PlasticLookAndFeel.BORDER_STYLE_KEY, BorderStyle.SEPARATOR);
		
		file = new JMenu();
		login = new JMenuItem();
		close = new JMenuItem();
		print = new JMenuItem();
		exit = new JMenuItem();
		
		file.add(login);
		file.add(close);
		file.add(print);
		file.addSeparator();
		file.add(exit);

		editMenu = new JMenu();
		add = new JMenuItem();
		edit = new JMenuItem();
		del = new JMenuItem();
		view = new JMenuItem();
		reload=new JMenuItem();
		
		editMenu.add(add);
		editMenu.add(edit);
		editMenu.add(del);
		editMenu.add(view);
		editMenu.add(reload);

		master = new JMenu();
		grp=new JMenuItem();
		usr=new JMenuItem();
		pelanggan=new JMenuItem();
		paket=new JMenuItem();
		jenisPekerjaan=new JMenuItem();
		jenisToko=new JMenuItem();
		statusPelanggan=new JMenuItem();

		master.add(grp);
		master.add(usr);
		master.add(pelanggan);
		master.add(paket);
		master.add(jenisPekerjaan);
		master.add(jenisToko);
		master.add(statusPelanggan);
		
		input = new JMenu();
		

		laporan = new JMenu();
		

		setting = new JMenu();
		

		help = new JMenu();
		registrasi = new JMenuItem();
		about = new JMenuItem();
		
		help.add(registrasi);
		help.add(about);
		
		menu.add(file);
		menu.add(editMenu);
		menu.add(master);
		menu.add(input);
		menu.add(laporan);
		menu.add(setting);
		menu.add(help);

		initMenu(file, L.file);
		initMenu(login, L.login, KeyEvent.VK_L, KeyEvent.VK_L, ActionEvent.CTRL_MASK, IconBase.LOGIN);
		initMenu(close, L.close, KeyEvent.VK_C, KeyEvent.VK_W, ActionEvent.CTRL_MASK, IconBase.CLOSE );
		initMenu(print, L.print, KeyEvent.VK_P, KeyEvent.VK_P, ActionEvent.CTRL_MASK, IconBase.PRINT);
		initMenu(exit, L.exit, KeyEvent.VK_E, KeyEvent.VK_X,
				ActionEvent.CTRL_MASK, IconBase.EXIT);
		initMenu(editMenu, L.edit);
		initMenu(add, L.add, KeyEvent.VK_T, KeyEvent.VK_N, ActionEvent.CTRL_MASK, IconBase.ADD);
		initMenu(edit, L.edit, KeyEvent.VK_E, KeyEvent.VK_E, ActionEvent.CTRL_MASK, IconBase.EDIT);
		initMenu(del, L.del, KeyEvent.VK_H, KeyEvent.VK_DELETE, 0, IconBase.DEL);
		initMenu(view, L.view, KeyEvent.VK_L, KeyEvent.VK_W, ActionEvent.CTRL_MASK, IconBase.VIEW);
		initMenu(reload, L.reload, KeyEvent.VK_R, KeyEvent.VK_F5, 0, IconBase.RELOAD);
		
		initMenu(master, LWindow.master);
		initMenu(grp, LMenu.GRP, GrpM.ICON_16 );
		initMenu(usr, LMenu.USR, UsrM.ICON_16);
		initMenu(jenisPekerjaan, LMenu.JENIS_PEKERJAAN, JenisPekerjaanM.ICON_16);
		
		initMenu(input, LWindow.input);
		
		initMenu(laporan, LWindow.lap);
		
		
		initMenu(setting, LWindow.setting);
		initMenu(help, LWindow.help);
		initMenu(registrasi, LWindow.registrasi, IconBase.REGISTRASI);
		initMenu(about, LWindow.about, IconBase.ABAOUT);
		
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.login();
			}
		});
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionClose();
			}
		});
		
		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionPrint();
			}
		});
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionExit();
			}
		});
		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionAdd();
			}
		});
		
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionEdit();
			}
		});
		
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionDel();
			}
		});
		
		view.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionView();
			}
		});
		
		reload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionReload();
			}
		});
		
		grp.addActionListener(window.getKomponentMaps().get(Window.HAK_AKSES).getAdd());
		usr.addActionListener(window.getKomponentMaps().get(Window.PEGAWAI).getAdd());
		jenisPekerjaan.addActionListener(window.getKomponentMaps().get(Window.JENIS_PEKERJAAN).getAdd());
		
//		showToolbar.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				window.showToolbar();
//				if (showToolbar.getText().equals(LWindow.showToolbar)) {
//					showToolbar.setText(LWindow.hideToolbar);
//				}else{
//					showToolbar.setText(LWindow.showToolbar);
//				}
//			}
//		});
		
		registrasi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				window.actionReg();
			}
		});
		
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				window.actionAbout();
			}
		});
		
	}

	private void initMenu(JMenu m, String nama, int key) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
	}

	private void initMenu(JMenu m, String nama) {
		m.setText(App.getT(nama));
	}

	private void initMenu(JMenuItem m, String nama) {
		m.setText(App.getT(nama));
	}

	private void initMenu(JMenuItem m, String nama, int key, int ke, int ae) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
		m.setAccelerator(KeyStroke.getKeyStroke(ke, ae));
	}

	private void initMenu(JMenuItem m, String nama, int key) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
	}

	private void initMenu(JMenuItem m, String nama, String urlIcon16) {
		m.setText(App.getT(nama));
		m.setIcon(App.getIcon(urlIcon16));
	}

	private void initMenu(JMenuItem m, String nama, int key, int ke, int ae,
			String urlIcon16) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
		m.setAccelerator(KeyStroke.getKeyStroke(ke, ae));
		m.setIcon(App.getIcon(urlIcon16));
	}

	private void initMenu(JMenuItem m, String nama, int key, String urlIcon16) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
		m.setIcon(App.getIcon(urlIcon16));
	}
	
	private void initMenu(JMenuItem m, String nama, Icon icon16) {
		m.setText(App.getT(nama));
		m.setIcon(icon16);
	}

	private void initMenu(JMenuItem m, String nama, int key, int ke, int ae,
			Icon icon16) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
		m.setAccelerator(KeyStroke.getKeyStroke(ke, ae));
		m.setIcon(icon16);
	}

	private void initMenu(JMenuItem m, String nama, int key, Icon icon16) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
		m.setIcon(icon16);
	}


	public void setFalseAll() {
		add.setEnabled(false);
		edit.setEnabled(false);
		view.setEnabled(false);
		del.setEnabled(false);
		print.setEnabled(false);
	}

	public void setStateByHakAkses() {
		if (window.getKomponentSeledcted()!=null) {
			MasterInterface master=window.getKomponentSeledcted().getWidgetTop();
			add.setEnabled(master.isAdd());
			edit.setEnabled(master.isEdit());
			view.setEnabled(master.isView());
			del.setEnabled(master.isDel());
			print.setEnabled(master.isPrint());
		}else{
			setFalseAll();
		}
	}

}
