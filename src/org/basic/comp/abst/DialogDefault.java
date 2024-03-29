package org.basic.comp.abst;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.text.JTextComponent;

import org.basic.comp.adapter.AddDialogAdapter;
import org.basic.comp.adapter.EfectWidget;
import org.basic.comp.adapter.TableInterfaces;
import org.basic.comp.base.GradientPanel;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.listener.WidgetInterface;
import org.basic.dao.adapter.DaoInterface;

import com.basic.lang.L;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public  class DialogDefault extends AddDialogAdapter {

	protected double lebar;
	protected Icon icon;
	protected JLabel labelTitle;
	protected JLabel labelNote;
	protected JPanel panel;
	protected JPanel panelForm;
	protected GradientPanel panelTitle;
	protected JPanel panelButton;
	protected ScrollPane pane;

	protected EfectWidget master;
	protected List<WidgetInterface> widgets = new ArrayList<WidgetInterface>();

	protected DaoInterface dao;

	protected JButton save;
	protected JButton reset;
	protected JButton cancel;
	
	protected ODocument model;
	
	protected TableInterfaces table;

	public void init(ODatabaseDocumentTx db) {
		save = new JButton(L.save);
		reset = new JButton(L.reset);
		cancel = new JButton(L.cancel);
		labelTitle = new JLabel();
		labelNote = new JLabel();
		buildLabelTitle();
		buildButton(db);

	}

	public void buildLabelTitle() {
		Font f = labelTitle.getFont();
		Font f2 = new Font(f.getName(), Font.BOLD, f.getSize());
		labelTitle.setFont(f2);
		setLayoutTitle();
	}

	public void buildButton(ODatabaseDocumentTx db) {

		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				actionSave();
			}
		});
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				actionReset();
			}
		});
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				actionCancel();
			}
		});

		setLayoutButton();
	}

	public void setLayoutButton() {
		StringBuilder c = new StringBuilder();
		c.append("30px,");
		c.append("d:g,10px,");
		c.append("f:80px,10px,");// 4
		c.append("f:80px,10px,");// 6
		c.append("f:80px,30px,");// 7

		StringBuilder r = new StringBuilder();
		r.append("5dlu,");
		r.append("p,");
		r.append("15dlu,");

		FormLayout l = new FormLayout(c.toString(), r.toString());
		FormBuilder b = new FormBuilder(l);

		b.append(save, 4, 2);
		b.append(reset, 6, 2);
		b.append(cancel, 8, 2);
		panelButton = b.getPanel();
	}

	public void setLayoutTitle() {
		panelTitle = new GradientPanel(new BorderLayout());
		panelTitle.setBackground(new Color(240, 255, 255));
		panelTitle.setForeground(Color.WHITE);
		StringBuilder c = new StringBuilder();
		c.append("30px,");
		c.append("20px,");
		c.append("f:0px:g,10px,");

		StringBuilder r = new StringBuilder();
		r.append("10dlu,");
		r.append("p,1dlu,");
		r.append("p,4dlu,");

		FormLayout l = new FormLayout(c.toString(), r.toString());
		FormBuilder b = new FormBuilder(l);

		b.append(labelTitle, 2, 2, 2, 1);
		b.append(labelNote, 3, 4);

		if (icon != null) {
			StringBuilder c2 = new StringBuilder();
			c2.append("1px,");
			c2.append("f:0px:g,");
			c2.append("p,30px,");

			StringBuilder r2 = new StringBuilder();
			r2.append("5dlu,");
			r2.append("p,4dlu,");
			// r2.append("c:0px:g,");

			FormLayout l2 = new FormLayout(c2.toString(), r2.toString());
			FormBuilder b2 = new FormBuilder(l2);

			JLabel labelIcon = new JLabel(icon);
			b2.append(labelIcon, 3, 2);

			StringBuilder c3 = new StringBuilder();
			c3.append("1px,");
			c3.append("f:0px:g,");
			c3.append("p,");
			c3.append("1px,");

			StringBuilder r3 = new StringBuilder();
			r3.append("1dlu,");
			r3.append("p,");
			r3.append("1dlu,");

			FormLayout l3 = new FormLayout(c3.toString(), r3.toString());
			FormBuilder b3 = new FormBuilder(l3);

			b3.append(b.getPanel(), 2, 2);
			b3.append(b2.getPanel(), 3, 2);
			b.getPanel().setOpaque(false);
			b2.getPanel().setOpaque(false);
			b3.getPanel().setOpaque(false);
			panelTitle.add(b3.getPanel(), BorderLayout.CENTER);

		} else {
			b.getPanel().setOpaque(false);
			panelTitle.add(b.getPanel(), BorderLayout.CENTER);
		}
		panelTitle.add(new JSeparator(), BorderLayout.SOUTH);
		// panelTitle.setBorder(App.borderBlackBottom5555);
	}

	/**
	 * untuk menjalankan ini harus sudah build label dan build form
	 */
	public void buildPanel() {
		panel = new JPanel(new BorderLayout());
		pane = new ScrollPane(panelForm);
		pane.setBorder(null);
		panel.add(pane, BorderLayout.CENTER);
		panel.add(panelTitle, BorderLayout.NORTH);
		panel.add(panelButton, BorderLayout.SOUTH);
	}

	public void setFocusEnter(JTextComponent sebelum,
			final JTextComponent sesudah) {
		sebelum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_ENTER == e.getKeyCode()) {
					sesudah.requestFocus();
					sesudah.selectAll();
				}
			}
		});
	}

	public void setFocusEnter(JTextComponent sebelum, final JComponent sesudah) {
		sebelum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_ENTER == e.getKeyCode()) {
					sesudah.requestFocus();
				}
			}
		});
	}

	public void setFocusEnter(JComponent sebelum, final JComponent sesudah) {
		sebelum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_ENTER == e.getKeyCode()) {
					sesudah.requestFocus();
				}
			}
		});
	}

	public void addWidget(WidgetInterface e) {
		widgets.add(e);
	}

	public JFrame getFrame(Object o) {
		if (o instanceof JFrame) {
			return ((JFrame) o);
		} else {
			if (o instanceof Component) {
				return getFrame(((Component) o).getParent());
			} else {
				return null;
			}
		}
	}

	public void clearText(JTextComponent field) {
		field.setText("");
	}

	public boolean modelIsTrue(ODocument model) {
		return model.field("@class").equals(dao.getClassName());
	}

	public void setBackground(JComponent field) {
		field.setBackground(App.whiteSmoke);
	}

	public void actionSave() {
		ODatabaseDocumentTx db = App.getDbd();
		ODatabaseRecordThreadLocal.INSTANCE.set(db);
		if (beforeSave(db)) {
			try {
				save(db);
				afterSave();
			} catch (Exception e) {
				App.printErr(e);
			} finally {
				db.close();
			}

		}
	}


	public void afterSave(){
		table.addModel(model);
		dispose(panel);
	}
	

	public boolean beforeSave(ODatabaseDocumentTx db) {
		return validate(db);
	}



	// default
	public double getLebar() {
		return lebar;
	}

	public void setLebar(double lebar) {
		this.lebar = lebar;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}


	public JLabel getLabelTitle() {
		return labelTitle;
	}

	public void setLabelTitle(JLabel label) {
		this.labelTitle = label;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JScrollPane getPane() {
		return pane;
	}

	public DaoInterface getDao() {
		return dao;
	}

	public void setDao(DaoInterface dao) {
		this.dao = dao;
	}


	public JPanel getPanelForm() {
		return panelForm;
	}

	public void setPanelForm(JPanel panelForm) {
		this.panelForm = panelForm;
	}


	public void setMasterEfectWidget(EfectWidget master) {
		this.master = master;
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		init(db);
		buildPanel();
		setFocusEnter();
	}


	public void dispose(Object o) {
		if (o instanceof Window) {
			((Window) o).dispose();
		} else {
			if (o instanceof Component) {
				dispose(((Component) o).getParent());
			}
		}
	}

	public void actionCancel() {
		dispose(panel);
	}

	public ODocument getModel() {
		return model;
	}

	public void setModel(ODocument model) {
		this.model = model;
	}

	@Override
	public void setTable(TableInterfaces table) {
		this.table=table;
	}


	
}
