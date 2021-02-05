package com.tw.action;



import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.tw.dao.ShiZaiDao;
import com.tw.entity.LOADONLINE;
import com.tw.entity.Vehicle;
import com.tw.util.Test;

public class swcz implements Action{
	private String qd_stime;
	private String qd_etime;
	private String zj_stime;
	private String zj_etime;
	private String zd_stime;
	private String zd_etime;
	private String qd_jwd;
	private String zd_jwd;
	private String zj_jwd;
	private String xlsfilename;
	private String fanhuei;
	private String action;
	private Test t = new Test();
	private List<List<Vehicle>> list = new ArrayList<List<Vehicle>>();
	public String findswcz(){
		list = t.findswcz(qd_stime, qd_etime, zj_stime, zj_etime, 
				zd_stime, zd_etime, qd_jwd, zj_jwd, zd_jwd);
		return SUCCESS;
	}
	public String findswczexcle(){
		list = t.findswcz(qd_stime, qd_etime, zj_stime, zj_etime, 
				zd_stime, zd_etime, qd_jwd, zj_jwd, zd_jwd);
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

				WritableSheet ws = wwb.createSheet("车辆",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"车号"); 
            	ws.addCell(label);
            	label = new Label(1,0,"经纬度"); 
            	ws.addCell(label);
            	label = new Label(2,0,"时间"); 
            	ws.addCell(label);
            	for (int i = 0; i < list.get(2).size(); i++) {
                	label = new Label(0,i+1,list.get(2).get(i).getVehi_no()); 
                	ws.addCell(label);
                	label = new Label(1,i+1,list.get(2).get(i).getPx().substring(0, list.get(2).get(i).getPx().length()-1));
                	ws.addCell(label);
                	label = new Label(2,i+1,list.get(2).get(i).getStime().substring(0, list.get(2).get(i).getStime().length()-1)); 
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
	public String getQd_stime() {
		return qd_stime;
	}
	public void setQd_stime(String qd_stime) {
		this.qd_stime = qd_stime;
	}
	public String getQd_etime() {
		return qd_etime;
	}
	public void setQd_etime(String qd_etime) {
		this.qd_etime = qd_etime;
	}
	public String getZj_stime() {
		return zj_stime;
	}
	public void setZj_stime(String zj_stime) {
		this.zj_stime = zj_stime;
	}
	public String getZj_etime() {
		return zj_etime;
	}
	public void setZj_etime(String zj_etime) {
		this.zj_etime = zj_etime;
	}
	public String getZd_stime() {
		return zd_stime;
	}
	public void setZd_stime(String zd_stime) {
		this.zd_stime = zd_stime;
	}
	public String getZd_etime() {
		return zd_etime;
	}
	public void setZd_etime(String zd_etime) {
		this.zd_etime = zd_etime;
	}
	public String getQd_jwd() {
		return qd_jwd;
	}
	public void setQd_jwd(String qd_jwd) {
		this.qd_jwd = qd_jwd;
	}
	public String getZd_jwd() {
		return zd_jwd;
	}
	public void setZd_jwd(String zd_jwd) {
		this.zd_jwd = zd_jwd;
	}
	public String getZj_jwd() {
		return zj_jwd;
	}
	public void setZj_jwd(String zj_jwd) {
		this.zj_jwd = zj_jwd;
	}
	public Test getT() {
		return t;
	}
	public void setT(Test t) {
		this.t = t;
	}

	public List<List<Vehicle>> getList() {
		return list;
	}

	public void setList(List<List<Vehicle>> list) {
		this.list = list;
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
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
