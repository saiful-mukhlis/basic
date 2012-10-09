package com.basic.print.interfaces;

import java.awt.Window;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

import com.basic.db.Grp;
import com.basic.db.JenisPekerjaan;
import com.basic.db.Usr;
import com.basic.print.model.UsrPM;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public interface PrintInterface {

	/**
	 * Contoh : 
	 * <pre>
	 * reportChild.setColspan(5, 1, "Jumlah");
	 * </pre>
	 * 5 = kolom awal <br/>
	 * 1 = jumlah kolom yang di span <br/>
	 * Jumlah = title header
	 */
	public void buildColSpan();

	/**
	 * Contoh :
	 * <pre>
	 * reportChild.addColumn(buildColumn("no", "No", 8));
	 * </pre>
	 * no = nama properti filed <br/>
	 * No = title headernya <br/>
	 * 8  = lebarnya
	 */
	public void buildColumn();

	public AbstractColumn buildColumn(String property, String title, int width);

	public AbstractColumn buildColumn(String property, String title, int width,
			Style style, Style styleHeader, Collection styleCond);

	/**
	 * Bila db tidak dibutuhkan null kan saja
	 *<pre>
	 * reportChild = new DynamicReportBuilder();
		// reportChild.setTitle(title);
		// reportChild.setSubtitle(subTitle);
		// reportChild.setHeaderHeight(tinggiHeader);
		// reportChild.setUseFullPageWidth(satuHalamanPenuh);
		reportChild.setColumnsPerPage(1);
		reportChild.setAllowDetailSplit(true);
		reportChild.setDefaultStyles(titleStyle, titleStyle, headerStyle,
				isiStyle);
		reportChild.setPageSizeAndOrientation(Page.Page_A4_Landscape());

		reportChild.setAllowDetailSplit(true);
		// reportChild.setPageSizeAndOrientation(Page.Page_A4_Portrait());
		// reportChild.setPageSizeAndOrientation(Page.Page_Legal_Portrait());
		 * </pre>
	 * @param db
	 */
	public void init(ODatabaseDocumentTx db);
	
	/**
	 * 
	 * <pre>
	 * 	
		initStyles();
		init(db);
		initReportChild();
		buildColumn();
		buildColSpan();
		// yang di atas sudah sebagai default
		pm=new ArrayList<>();
		List<ODocument> model=App.getUsrDao().getAll(db);
		int index=0;
		for (ODocument oDocument : model) {
			int no = index + 1;
			UsrPM u=new UsrPM();
			ODocument tmp2=oDocument.field(Usr.GRP);
			tmp2.field(Grp.NAME);
			u.setO2(tmp2);
			
			ODocument tmp3=oDocument.field(Usr.JENIS_PEKERJAAN);
			if (tmp3!=null) {
				tmp3.field(JenisPekerjaan.NAMA);
				u.setO3(tmp3);
			}else{
				u.setO3(null);
			}
			u.setO(oDocument);
			u.setNo(no+"");
			u.setO(oDocument);
			pm.add(u);
			
			index++;
		}
		
	 * </pre>
	 * @param db
	 */
	public void buildReport(ODatabaseDocumentTx db);
	
	/**
	 * <pre>
	 * Object[] possibilities = App.getBulan();
		final NamaBulan s = (NamaBulan)JOptionPane.showInputDialog(getFrame(panel),
		                    "Bulan : \n",
		                    "Pilih Bulan",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    possibilities,
		                    possibilities[0]);
		if (s!=null) {
			boolean salah=true;
			while (salah) {
				String inputValue = JOptionPane.showInputDialog(getFrame(panel), "Tahun :", "Inputkan Tahun", JOptionPane.PLAIN_MESSAGE); 
				if (inputValue!=null) {
					if (inputValue.length()==4) {
						try {
							final int th=Integer.parseInt(inputValue);
							salah=false;
							DialogLoading dl=new DialogLoading(db,getWindow(panel),new LoadingAdapter() {

								@Override
								public void runTask() {
									List<DynamicReport> childReport=new ArrayList<DynamicReport>();
									List<List> datas=new ArrayList<List>();
									buildReport(db, lajura, s, th);
									childReport.add(reportChild.build());
									datas.add(data);

									String t1="";
									String t2="";
									String t3="";
									ODocument f=App.getFormatDao().getOne(db, FormatDao.code, "kop");
									if (f!=null) {
										t1=f.field(FormatDao.kop1);
										t2=f.field(FormatDao.kop2);
										t3=f.field(FormatDao.kop3);
										tinggiHeader=50;
										satuHalamanPenuh=true;
									}
									
									AbstractJadwalReport report = new AbstractJadwalReport(t1, t2, t3, "");
									//report.setLandscape(false);
									report.setDatas(datas);
									report.setChildReport(childReport);
									runReport(db,report);
									
								}
							
							});
							dl.setVisible(true);
						} catch (Exception e) {
							// TODO: handle exception
							App.printErr(e);
						}
					}
				}else{
					salah=false;
				}
				
			}
		}
	 * </pre>
	 * 
	 * @param db
	 */
	public void run(final ODatabaseDocumentTx db);
	public void runPrint(final ODatabaseDocumentTx db);
	public void runPdf(final ODatabaseDocumentTx db);
	public void runWord(final ODatabaseDocumentTx db);
	public void runExcel(final ODatabaseDocumentTx db);


	/**
	 * pembentukan tabel di dalam report
	 */
	public void initReportChild();

	/**
	 * deklarasi semua style
	 */
	public void initStyles();

	public Window getWindow(Object o);

	public JFrame getFrame(Object o);

}
