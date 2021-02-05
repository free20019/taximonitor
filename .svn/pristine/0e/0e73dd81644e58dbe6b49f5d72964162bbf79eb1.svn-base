package com.tw.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.tw.dao.ApplyDao;
import com.tw.dao.TransportDao;
import com.tw.entity.Complaint;
import com.tw.entity.Illegal;
import com.tw.entity.OperatingData;
import com.tw.entity.ServiceQuality;
import com.tw.util.PageHelper;

public class TransportAction implements Action{
	
	private String stime;
	private String etime;
	private String compname;
	private String type;	
	private String year;	
	private String level;	
	private String xlsfilename;
	private String fanhuei;
	private String action;
	private PageHelper page1 = new PageHelper();
	private List<Illegal> list1;
	private List<Illegal> listexcel1;
	private PageHelper page2 = new PageHelper();
	private List<Complaint> list2;
	private List<Complaint> listexcel2;
	private List<ServiceQuality> list3;
	TransportDao f=new TransportDao();
	
	//违章数据查询
	public String findillegal(){
		int count=f.findillegalcount(compname);
		page1.setPageCount(count);
		list1=f.findillegal(compname,page1);
		return SUCCESS;
	}
	//违章数据导出
	public String findillegalexcle(){
		try {
			listexcel1=f.findillegalexcle(compname);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try{
			Date date = new Date();
			String logfile=ServletActionContext.getServletContext().getRealPath("/");
			String dateStr2 = new Timestamp(date.getTime()).toString();
			String MV_VERSION="";
			MV_VERSION=MV_VERSION+dateStr2.substring(0,4);
			MV_VERSION=MV_VERSION+dateStr2.substring(5,7);
			MV_VERSION=MV_VERSION+dateStr2.substring(8,10);
			MV_VERSION=MV_VERSION+dateStr2.substring(11,13);
			MV_VERSION=MV_VERSION+dateStr2.substring(14,16);
			MV_VERSION=MV_VERSION+dateStr2.substring(17,19);
			logfile=logfile+"count\\";
			File file1 = new File(logfile);//创建文件夹
			if (!file1.exists()) {
				file1.mkdir();
				//System.err.println(file + " 文件夹已创建！");
			}
			DelAllFile d=new DelAllFile();
			d.delAllFile(logfile);
			File file= new File(logfile+""+MV_VERSION+".xls"); 
			this.xlsfilename=""+MV_VERSION+".xls";
			//this.xlsfilepath="//evaluation//"+xlsfilename;
			file.createNewFile(); //建立要输出的文件 

			OutputStream os = new FileOutputStream(file);//使用文件缓冲 

			WritableWorkbook wwb = Workbook.createWorkbook(os);//取EXCEL操作实例 
			WritableSheet ws = wwb.createSheet("违章查询",0);//创建Excel工作表 指定名称和位置 
			Label label ;
			String a[] = {"车牌号","公司名称","违章次数","IC卡分值"};//导出列明
			for(int i=0;i<a.length;i++){
				label = new Label(i,0,a[i]); 
            	ws.addCell(label);
			}
        	for (int i = 0; i < listexcel1.size(); i++) {
            	label = new Label(0,i+1,listexcel1.get(i).getAuto_num()); 
            	ws.addCell(label);
            	label = new Label(1,i+1,listexcel1.get(i).getCom_name()); 
            	ws.addCell(label);
            	label = new Label(2,i+1,listexcel1.get(i).getCount()); 
            	ws.addCell(label);                  	
            	label = new Label(3,i+1,listexcel1.get(i).getIc_score()); 
            	ws.addCell(label);
			}
            wwb.write();//写入文件 
			wwb.close();
			os.close();
			this.fanhuei="成功导成Excel!";
			this.action="filedownload.action?inputPath="+this.xlsfilename;
		}catch(Exception e){
			this.fanhuei="导出失败!";
		}
		
		return SUCCESS;
	}
	
	//投诉查询
	public String findcomplaint(){
	    int count=f.findcomplaintcount(stime,etime,type);
		page2.setPageCount(count);
		list2=f.findcomplaint(stime,etime,type,page2);
		return SUCCESS;
	}
	//投诉数据导出
	public String findcomplaintexcle(){
		try {
			listexcel2=f.findcomplaintexcle(stime,etime,type);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try{
			Date date = new Date();
			String logfile=ServletActionContext.getServletContext().getRealPath("/");
			String dateStr2 = new Timestamp(date.getTime()).toString();
			String MV_VERSION="";
			MV_VERSION=MV_VERSION+dateStr2.substring(0,4);
			MV_VERSION=MV_VERSION+dateStr2.substring(5,7);
			MV_VERSION=MV_VERSION+dateStr2.substring(8,10);
			MV_VERSION=MV_VERSION+dateStr2.substring(11,13);
			MV_VERSION=MV_VERSION+dateStr2.substring(14,16);
			MV_VERSION=MV_VERSION+dateStr2.substring(17,19);
			logfile=logfile+"count\\";
			File file1 = new File(logfile);//创建文件夹
			if (!file1.exists()) {
				file1.mkdir();
				//System.err.println(file + " 文件夹已创建！");
			}
			DelAllFile d=new DelAllFile();
			d.delAllFile(logfile);
			File file= new File(logfile+""+MV_VERSION+".xls"); 
			this.xlsfilename=""+MV_VERSION+".xls";
			//this.xlsfilepath="//evaluation//"+xlsfilename;
			file.createNewFile(); //建立要输出的文件 

			OutputStream os = new FileOutputStream(file);//使用文件缓冲 

			WritableWorkbook wwb = Workbook.createWorkbook(os);//取EXCEL操作实例 
			WritableSheet ws = wwb.createSheet("投诉查询",0);//创建Excel工作表 指定名称和位置 
			Label label ;
			String a[] = {"投诉人","投诉类型","联系方式","投诉车辆","处理详情","投诉时间","处理时间"};//导出列明
			for(int i=0;i<a.length;i++){
				label = new Label(i,0,a[i]); 
            	ws.addCell(label);
			}
        	for (int i = 0; i < listexcel2.size(); i++) {
            	label = new Label(0,i+1,listexcel2.get(i).getCALL_NAME()); 
            	ws.addCell(label);
            	label = new Label(1,i+1,listexcel2.get(i).getBUSINESS_ITEMTYPE_NAME()); 
            	ws.addCell(label);
            	label = new Label(2,i+1,listexcel2.get(i).getCALLER_ID()); 
            	ws.addCell(label);                  	
            	label = new Label(3,i+1,listexcel2.get(i).getVEHICLE_PLATE_NUMBER()); 
            	ws.addCell(label);
            	label = new Label(4,i+1,listexcel2.get(i).getBUSINESS_STATUS_NAME()); 
            	ws.addCell(label);
            	label = new Label(5,i+1,listexcel2.get(i).getARCHIVE_TIME()); 
            	ws.addCell(label);
            	label = new Label(6,i+1,listexcel2.get(i).getACCEPT_TIME()); 
            	ws.addCell(label);
			}
            wwb.write();//写入文件 
			wwb.close();
			os.close();
			this.fanhuei="成功导成Excel!";
			this.action="filedownload.action?inputPath="+this.xlsfilename;
		}catch(Exception e){
			this.fanhuei="导出失败!";
		}
		
		return SUCCESS;
	}
	
	//服务质量查询
	public String findservicequality(){
		list3=f.findservicequality(year,compname,level);
		return SUCCESS;
	}
	//服务质量导出
	public String findservicequalityexcle(){
		try {
			list3=f.findservicequality(year,compname,level);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try{
			Date date = new Date();
			String logfile=ServletActionContext.getServletContext().getRealPath("/");
			String dateStr2 = new Timestamp(date.getTime()).toString();
			String MV_VERSION="";
			MV_VERSION=MV_VERSION+dateStr2.substring(0,4);
			MV_VERSION=MV_VERSION+dateStr2.substring(5,7);
			MV_VERSION=MV_VERSION+dateStr2.substring(8,10);
			MV_VERSION=MV_VERSION+dateStr2.substring(11,13);
			MV_VERSION=MV_VERSION+dateStr2.substring(14,16);
			MV_VERSION=MV_VERSION+dateStr2.substring(17,19);
			logfile=logfile+"count\\";
			File file1 = new File(logfile);//创建文件夹
			if (!file1.exists()) {
				file1.mkdir();
				//System.err.println(file + " 文件夹已创建！");
			}
			DelAllFile d=new DelAllFile();
			d.delAllFile(logfile);
			File file= new File(logfile+""+MV_VERSION+".xls"); 
			this.xlsfilename=""+MV_VERSION+".xls";
			//this.xlsfilepath="//evaluation//"+xlsfilename;
			file.createNewFile(); //建立要输出的文件 

			OutputStream os = new FileOutputStream(file);//使用文件缓冲 

			WritableWorkbook wwb = Workbook.createWorkbook(os);//取EXCEL操作实例 
			WritableSheet ws = wwb.createSheet("服务质量查询",0);//创建Excel工作表 指定名称和位置 
			Label label ;
			String a[] = {"公司名称","年度","信誉等级"};
			for(int i=0;i<a.length;i++){
				label = new Label(i,0,a[i]); 
				ws.addCell(label);
			}
			for (int i = 0; i < list3.size(); i++) {
	          	label = new Label(0,i+1,list3.get(i).getYHMC()); 
	          	ws.addCell(label);
	          	label = new Label(1,i+1,list3.get(i).getSJ()); 
	          	ws.addCell(label);
	          	label = new Label(2,i+1,list3.get(i).getXYDJ()); 
	          	ws.addCell(label);                  	
			}
          wwb.write();//写入文件 
			wwb.close();
			os.close();
			this.fanhuei="成功导成Excel!";
			this.action="filedownload.action?inputPath="+this.xlsfilename;
		}catch(Exception e){
			this.fanhuei="导出失败!";
		}
		
		return SUCCESS;
	}
	
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCompname() {
		return compname;
	}
	public void setCompname(String compname) {
		this.compname = compname;
	}

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getXlsfilename() {
		return xlsfilename;
	}

	public void setXlsfilename(String xlsfilename) {
		this.xlsfilename = xlsfilename;
	}

	public String getFanhuei() {
		return fanhuei;
	}

	public void setFanhuei(String fanhuei) {
		this.fanhuei = fanhuei;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public PageHelper getPage1() {
		return page1;
	}

	public void setPage1(PageHelper page1) {
		this.page1 = page1;
	}

	public List<Illegal> getList1() {
		return list1;
	}
	public void setList1(List<Illegal> list1) {
		this.list1 = list1;
	}

	public List<Illegal> getListexcel1() {
		return listexcel1;
	}

	public void setListexcel1(List<Illegal> listexcel1) {
		this.listexcel1 = listexcel1;
	}


	public PageHelper getPage2() {
		return page2;
	}

	public void setPage2(PageHelper page2) {
		this.page2 = page2;
	}

	public List<Complaint> getList2() {
		return list2;
	}

	public void setList2(List<Complaint> list2) {
		this.list2 = list2;
	}

	public List<Complaint> getListexcel2() {
		return listexcel2;
	}

	public void setListexcel2(List<Complaint> listexcel2) {
		this.listexcel2 = listexcel2;
	}
	
	public List<ServiceQuality> getList3() {
		return list3;
	}
	public void setList3(List<ServiceQuality> list3) {
		this.list3 = list3;
	}
	public TransportDao getF() {
		return f;
	}

	public void setF(TransportDao f) {
		this.f = f;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

}
