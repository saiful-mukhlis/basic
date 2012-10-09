package com.basic.print;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.Page;
import org.basic.comp.listener.LoadingAdapter;

import com.basic.comp.impl.DialogLoading;
import com.basic.comp.impl.master.UsrM;
import com.basic.db.Grp;
import com.basic.db.JenisPekerjaan;
import com.basic.db.Usr;
import com.basic.lang.LUsr;
import com.basic.print.defaults.PrintDefault;
import com.basic.print.model.UsrPM;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;


public class UsrPrintAll extends PrintDefault {
	protected List<UsrPM> pm=new ArrayList<>();
	public UsrPrintAll(JPanel panel) {
		super(panel);
	}
	@Override
	public void init(ODatabaseDocumentTx db) {
		reportChild = new DynamicReportBuilder();
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
		// reportChild.setPageSizeAndOrientation
	}
	@Override
	public void buildColumn() {
		reportChild.addColumn(buildColumn("no",LUsr.NO , 8));
		reportChild.addColumn(buildColumn(Usr.NAMA, LUsr.NAMA, 100));
		reportChild.addColumn(buildColumn(Usr.ALAMAT, LUsr.ALAMAT, 100));
		reportChild.addColumn(buildColumn(Usr.KOTA, LUsr.KOTA, 100));
		reportChild.addColumn(buildColumn(Usr.NO_TELP, LUsr.NO_TELP, 100));
		reportChild.addColumn(buildColumn(Usr.NO_HP1, LUsr.NO_HP1, 100));
	}
	@Override
	public void buildReport(ODatabaseDocumentTx db) {
		super.buildReport(db);
		
		
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
	}
	@Override
	public void run(final ODatabaseDocumentTx db) {
		buildReport(db);
		DialogLoading dl=new DialogLoading(db,getWindow(panel),new LoadingAdapter() {

			@Override
			public void runTask() {
				List<DynamicReport> childReport=new ArrayList<DynamicReport>();
				List<List> datas=new ArrayList<List>();
				buildReport(db);
				childReport.add(reportChild.build());
				datas.add(pm);

				ReportBese report=new ReportBese();
				report.setDatas(datas);
				report.setChildReport(childReport);
				report.runReport(db);
				
			}
		
		});
		dl.setVisible(true);
	}
	
	public void runPrint(final ODatabaseDocumentTx db) {
		buildReport(db);
		List<DynamicReport> childReport=new ArrayList<DynamicReport>();
		List<List> datas=new ArrayList<List>();
		buildReport(db);
		childReport.add(reportChild.build());
		datas.add(pm);

		ReportBese report=new ReportBese();
		report.setDatas(datas);
		report.setChildReport(childReport);
		report.runPrint(db);
	}
	
	public void runPdf(final ODatabaseDocumentTx db) {
		buildReport(db);
		List<DynamicReport> childReport=new ArrayList<DynamicReport>();
		List<List> datas=new ArrayList<List>();
		buildReport(db);
		childReport.add(reportChild.build());
		datas.add(pm);

		ReportBese report=new ReportBese();
		report.setDatas(datas);
		report.setChildReport(childReport);
		report.runPdf(db, panel, UsrM.TITLE);
	}
	
	public void runWord(final ODatabaseDocumentTx db) {
		buildReport(db);
		List<DynamicReport> childReport=new ArrayList<DynamicReport>();
		List<List> datas=new ArrayList<List>();
		buildReport(db);
		childReport.add(reportChild.build());
		datas.add(pm);

		ReportBese report=new ReportBese();
		report.setDatas(datas);
		report.setChildReport(childReport);
		report.runWord(db, panel, UsrM.TITLE);
	}
	
	public void runExcel(final ODatabaseDocumentTx db) {
		buildReport(db);
		List<DynamicReport> childReport=new ArrayList<DynamicReport>();
		List<List> datas=new ArrayList<List>();
		buildReport(db);
		childReport.add(reportChild.build());
		datas.add(pm);

		ReportBese report=new ReportBese();
		report.setDatas(datas);
		report.setChildReport(childReport);
		report.runExcel(db, panel, UsrM.TITLE);
	}
	
	
}
