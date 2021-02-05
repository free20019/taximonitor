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
import com.opensymphony.xwork2.ActionContext;
import com.tw.dao.FormDao;
import com.tw.entity.Form;
import com.tw.entity.Vehicle;

public class FormAction implements Action{
	private String time;
	private String ba_name;
	private List<Form>list=new ArrayList<Form>();
	private List<Vehicle>list2=new ArrayList<Vehicle>();
	private FormDao f=new FormDao();
	private String xlsfilename;
	private String fanhuei;
	private String action;
	public String finddayform(){
		String data=(String) ActionContext.getContext().getSession().get("data");
		list=f.finddayfrom(time, ba_name,data);
		return SUCCESS;
	}
	public String findmonthform(){
		String data=(String) ActionContext.getContext().getSession().get("data");
		list=f.findmonthform(time, ba_name,data);
		return SUCCESS;
	}
	public String findalarmstatus(){
		String data=(String) ActionContext.getContext().getSession().get("data");
		list2=f.findalarmstatus(data);
		return SUCCESS;
	}
	public String finddayformexcle(){
		try {
			String data=(String) ActionContext.getContext().getSession().get("data");
			list=f.finddayfrom(time, ba_name,data);
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

				WritableSheet ws = wwb.createSheet("日报表",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"日期"); 
            	ws.addCell(label);
            	label = new Label(1,0,"总车辆数"); 
            	ws.addCell(label);
            	label = new Label(2,0,"营运车辆数"); 
            	ws.addCell(label);
            	label = new Label(3,0,"出车率"); 
            	ws.addCell(label);
            	label = new Label(4,0,"平均周转次数"); 
            	ws.addCell(label);
            	label = new Label(5,0,"平均营收金额"); 
            	ws.addCell(label);
            	label = new Label(6,0,"平均实载率"); 
            	ws.addCell(label);
            	label = new Label(7,0,"平均重车时间(分)"); 
            	ws.addCell(label);
            	label = new Label(8,0,"平均等候时间(分)"); 
            	ws.addCell(label);
            	label = new Label(9,0,"平均实载里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(10,0,"平均空驶里程(公里)"); 
            	ws.addCell(label);
                	for (int i = 0; i < list.size(); i++) {
                    	label = new Label(0,i+1,list.get(i).getReport_time()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,list.get(i).getRepore_vhic()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,list.get(i).getRepore_vhicno()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,((Integer.parseInt(list.get(i).getRepore_vhicno())/Integer.parseInt(list.get(i).getRepore_vhic())*100)+"%")); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,list.get(i).getRepore_no()); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,list.get(i).getRepore_amount_revenue()); 
                    	ws.addCell(label);
                    	label = new Label(6,i+1,list.get(i).getRepore_actual_loading()); 
                    	ws.addCell(label);
                    	label = new Label(7,i+1,list.get(i).getReproe_revenue_time()); 
                    	ws.addCell(label);
                    	label = new Label(8,i+1,list.get(i).getRepore_wait_time()); 
                    	ws.addCell(label);
                    	label = new Label(9,i+1,list.get(i).getRepore_actual_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(10,i+1,list.get(i).getRepore_empty_mileage()); 
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
	public String findmonthformexcle(){
		try {
			String data=(String) ActionContext.getContext().getSession().get("data");
			list=f.findmonthform(time, ba_name,data);
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

				WritableSheet ws = wwb.createSheet("日报表",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"日期"); 
            	ws.addCell(label);
            	label = new Label(1,0,"总车辆数"); 
            	ws.addCell(label);
            	label = new Label(2,0,"参与营运车辆数"); 
            	ws.addCell(label);
            	label = new Label(3,0,"出车率"); 
            	ws.addCell(label);
            	label = new Label(4,0,"周转总次数"); 
            	ws.addCell(label);
            	label = new Label(5,0,"平均周转次数"); 
            	ws.addCell(label);
            	label = new Label(6,0,"平均营收金额"); 
            	ws.addCell(label);
            	label = new Label(7,0,"平均实载率"); 
            	ws.addCell(label);
            	label = new Label(8,0,"平均出车时间(时)"); 
            	ws.addCell(label);
            	label = new Label(9,0,"平均重车时间(时)"); 
            	ws.addCell(label);
            	label = new Label(10,0,"平均等候时间(时)"); 
            	ws.addCell(label);
            	label = new Label(11,0,"平均总里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(12,0,"平均实载里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(13,0,"平均空驶里程(公里)"); 
            	ws.addCell(label);
                	for (int i = 0; i < list.size(); i++) {
                    	label = new Label(0,i+1,list.get(i).getReport_time()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,list.get(i).getRepore_vhicno()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,list.get(i).getRepore_vhic()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,((Integer.parseInt(list.get(i).getRepore_vhic())/Integer.parseInt(list.get(i).getRepore_vhicno())*100)+"%")); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,list.get(i).getRepore_no()); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,list.get(i).getRepore_turnover());
                    	ws.addCell(label);
                    	label = new Label(6,i+1,list.get(i).getRepore_amount_revenue());
                    	ws.addCell(label);
                    	label = new Label(7,i+1,list.get(i).getRepore_actual_loading()); 
                    	ws.addCell(label);
                    	label = new Label(8,i+1,list.get(i).getRepore_car_time()); 
                    	ws.addCell(label);
                    	label = new Label(9,i+1,list.get(i).getReproe_revenue_time()); 
                    	ws.addCell(label);
                    	label = new Label(10,i+1,list.get(i).getRepore_wait_time()); 
                    	ws.addCell(label);
                    	label = new Label(11,i+1,list.get(i).getRepore_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(12,i+1,list.get(i).getRepore_actual_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(13,i+1,list.get(i).getRepore_empty_mileage()); 
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
	public String getBa_name() {
		return ba_name;
	}

	public void setBa_name(String baName) {
		ba_name = baName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<Form> getList() {
		return list;
	}

	public void setList(List<Form> list) {
		this.list = list;
	}

	public FormDao getF() {
		return f;
	}

	public void setF(FormDao f) {
		this.f = f;
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
	public List<Vehicle> getList2() {
		return list2;
	}
	public void setList2(List<Vehicle> list2) {
		this.list2 = list2;
	}
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
