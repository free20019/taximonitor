package com.tw.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.tw.dao.FindDao;
import com.tw.dao.LoadOnlineDAO;
import com.tw.entity.Area;
import com.tw.entity.LOADONLINE;
import com.tw.entity.Vehicle;
import com.tw.entity.Vhic;
import com.tw.entity.Wx;
import com.tw.util.VhicComparator;


public class FindAction implements Action{
	private String time;
	private String quyu;
	private String day;
	private String speed;
	private String i;
	private String id;
	private String xlsfilename;
	private String fanhuei;
	private String action;
	private String stime;
	private String etime;
	private String compname;
	private String compid;
	private String areaid;
	private List<Vehicle>vehicle=new ArrayList<Vehicle>();
	private List<Vhic>list=new ArrayList<Vhic>();
	private List<String>list1=new ArrayList<String>();
	private List<Vhic>list2=new ArrayList<Vhic>();
	private List<Vhic>list3=new ArrayList<Vhic>();
	private List<Vhic>list4=new ArrayList<Vhic>();
	private List<Vhic>list5=new ArrayList<Vhic>();
	private List<Vhic>list6=new ArrayList<Vhic>();
	private List<Vhic>list7=new ArrayList<Vhic>();
	private List<Vhic>list8=new ArrayList<Vhic>();
	private List<Vhic>listexcle=new ArrayList<Vhic>();
	private List<Map<String, Object>> l = new ArrayList<Map<String,Object>>();
	private String vhic;
	private List<Wx>wx=new ArrayList<Wx>();
	private Vhic v = new Vhic();
	FindDao f=new FindDao();
	private String cphm;
	private String lxr;
	private String lxdh;
	private String gzxx;
	private String gzms;
	private String wxlx;
	private String dqgz;
	private String wxfy;
	private String wxry;
	private String wxdd;
	private String wxnr;
	private String sxsj;
	private String wxsj;
	private String myd;
	public String findoffline(){
		String name=(String) ActionContext.getContext().getSession().get("data");
		list=f.findvehino(stime,etime,name,areaid,compid);
		return SUCCESS;
	}
	public String findbreakdown(){
		int a=0;
		for (int i = 0; i < 48; i++) {
			String data=(String) ActionContext.getContext().getSession().get("data");
			a=f.findmingxi(quyu, day, speed, data, i);
			list1.add(i,a+"")  ;
		}
		return SUCCESS;
	}
	public String findspecinfo(){
		String data=(String) ActionContext.getContext().getSession().get("data");
		list2=f.findspecinfo(quyu, time, speed, i,data);
		return SUCCESS;
	}
	public String findareavhic(){
		list3=f.findvhicinfo(time, id);
		return SUCCESS;
	}
	public String findnoline(){
		String name=(String) ActionContext.getContext().getSession().get("data");
		list4=f.findnoline(stime,etime,areaid,compid,name);
		return SUCCESS;
	}
	public String findonlineoffbus(){
		String name=(String) ActionContext.getContext().getSession().get("data");
		list5=f.findonlineoffbus(stime,etime,areaid,compid,name);
		return SUCCESS;
	}
	public String findnolinebus() {
		String name=(String) ActionContext.getContext().getSession().get("data");
		list6=f.findnolinebus(stime,etime,areaid,compid,name);
		return SUCCESS;
	}
	public String findnolineoffbus(){
		String name=(String) ActionContext.getContext().getSession().get("data");
		list7=f.findnolineoffbus(stime,etime,areaid,compid,name);
		return SUCCESS;
	}
	public String findnoempty(){
		String name=(String) ActionContext.getContext().getSession().get("data");
		list8=f.findnoempty(stime,etime,areaid,compid,name);
		return SUCCESS;
	}
	public String execute() throws Exception {
		HttpSession session= ServletActionContext.getRequest().getSession();
		session.setAttribute("vhic", vhic);
		return SUCCESS;
	}
	//时间段车辆数量分析
	public String findtimevehi(){
		list2=f.findtimevhic(stime, etime, quyu, compname);
		return SUCCESS;
	}
	//查询所有公司
	public String findareaname(){
		list2=f.findtaxifenxi(time);
		return SUCCESS;
	}
	//输入3位以上车号查询车辆
	public String findcl3(){
		list2 = f.findcl3(quyu);
		return SUCCESS;
	}
	public String finddpjk(){
		vehicle = f.finddpjk(quyu);
		return SUCCESS;
	}
	/*维修统计*/
	public String findwxtj(){
		String name=(String) ActionContext.getContext().getSession().get("data");
		vehicle = f.findwxtj(stime,etime,compid,compname,quyu,areaid,name);
		return SUCCESS;
	}
	/*维修管理*/
	public String findwxgl(){
		String name=(String) ActionContext.getContext().getSession().get("data");
		l = f.findclwh(quyu,name);
		return SUCCESS;
	}
	/*维修管理下拉框*/
	public String findwxinfolist(){
		wx = f.findwxinfolist(quyu);
		return SUCCESS;
	}
	/*维修管理增加*/
	public String addwxgl(){
		int a = f.addwxgl(cphm,lxr,lxdh,gzxx,gzms,wxlx,dqgz,wxfy,wxry,wxdd,wxnr,sxsj,wxsj,myd);
		if (a!=0) {
			compname="添加成功";
		}else {
			compname="添加失败";
		}
		return SUCCESS;
	}
	public String editwxgl(){
		int a = f.editwxgl(cphm,lxr,lxdh,gzxx,gzms,wxlx,dqgz,wxfy,wxry,wxdd,wxnr,sxsj,wxsj,myd,id);
		if (a!=0) {
			compname="修改成功";
		}else {
			compname="修改失败";
		}
		return SUCCESS;
	}
	public String delwxgl(){
		int a = f.delwxgl(id);
		if (a!=0) {
			compname="删除成功";
		}else {
			compname="删除失败";
		}
		return SUCCESS;
	}
	public String findmingxiexcle(){
		try {
				String data=(String) ActionContext.getContext().getSession().get("data");
				list=f.findspecinfo(quyu, time, speed, i,data);
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

				WritableSheet ws = wwb.createSheet("未营运车辆查询",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"公司"); 
            	ws.addCell(label);
            	label = new Label(1,0,"车号"); 
            	ws.addCell(label);
            	label = new Label(2,0,"SIM卡号"); 
            	ws.addCell(label);
            	label = new Label(3,0,"司机姓名"); 
            	ws.addCell(label);
            	label = new Label(4,0,"司机电话"); 
            	ws.addCell(label);
                	for (int i = 0; i < list.size(); i++) {
                    	label = new Label(0,i+1,list.get(i).getComp_id()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,list.get(i).getVehi_no()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,list.get(i).getVehi_sim()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,list.get(i).getOwn_name()); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,list.get(i).getOwn_tel()); 
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
	
	public String findofflineexcle(){
		try {
			String name=(String) ActionContext.getContext().getSession().get("data");
			list=f.findvehino(stime,etime,name,areaid,compid);
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

				WritableSheet ws = wwb.createSheet("为营运车辆查询",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"公司"); 
            	ws.addCell(label);
            	label = new Label(1,0,"车号"); 
            	ws.addCell(label);
            	label = new Label(2,0,"SIM卡号"); 
            	ws.addCell(label);
            	label = new Label(3,0,"司机姓名"); 
            	ws.addCell(label);
            	label = new Label(4,0,"司机电话"); 
            	ws.addCell(label);
                	for (int i = 0; i < list.size(); i++) {
                    	label = new Label(0,i+1,list.get(i).getComp_id()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,list.get(i).getVehi_no()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,list.get(i).getVehi_sim()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,list.get(i).getOwn_name()); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,list.get(i).getOwn_tel()); 
                    	ws.addCell(label);
					}
                wwb.write();//写入文件 
				wwb.close();
				os.close();
				this.fanhuei="成功导成Excel!";
				this.action="filedownload.action?inputPath="+this.xlsfilename;
//				System.out.println(action+"   "+xlsfilename+"   "+file);
			}catch(Exception e){
				this.fanhuei="导出失败!";
			}
		
		return SUCCESS;
	}
	public String findonlineoffbusexcle(){
		try {
			String data=(String) ActionContext.getContext().getSession().get("data");
			listexcle=f.findonlineoffbus(stime,etime,areaid,compid,data);
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

				WritableSheet ws = wwb.createSheet("上线无营运车辆查询",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"公司"); 
            	ws.addCell(label);
            	label = new Label(1,0,"车号"); 
            	ws.addCell(label);
            	label = new Label(2,0,"最后上线时间"); 
            	ws.addCell(label);
            	label = new Label(3,0,"SIM卡号"); 
            	ws.addCell(label);
            	label = new Label(4,0,"司机姓名"); 
            	ws.addCell(label);
            	label = new Label(5,0,"司机电话"); 
            	ws.addCell(label);
                	for (int i = 0; i < listexcle.size(); i++) {
                    	label = new Label(0,i+1,listexcle.get(i).getComp_id()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,listexcle.get(i).getVehi_no()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,listexcle.get(i).getTime()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,listexcle.get(i).getVehi_sim()); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,listexcle.get(i).getOwn_name()); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,listexcle.get(i).getOwn_tel()); 
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
	
	public String findnolineexcle(){
		try {
			String data=(String) ActionContext.getContext().getSession().get("data");
			listexcle=f.findnoline(stime,etime,areaid,compid,data);
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

				WritableSheet ws = wwb.createSheet("为营运车辆查询",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"公司"); 
            	ws.addCell(label);
            	label = new Label(1,0,"车号"); 
            	ws.addCell(label);
            	label = new Label(2,0,"汇报时间"); 
            	ws.addCell(label);
            	label = new Label(3,0,"SIM卡号"); 
            	ws.addCell(label);
            	label = new Label(4,0,"司机姓名"); 
            	ws.addCell(label);
            	label = new Label(5,0,"司机电话"); 
            	ws.addCell(label);
                	for (int i = 0; i < listexcle.size(); i++) {
                    	label = new Label(0,i+1,listexcle.get(i).getComp_id()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,listexcle.get(i).getVehi_no()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,listexcle.get(i).getTime()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,listexcle.get(i).getVehi_sim()); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,listexcle.get(i).getOwn_name()); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,listexcle.get(i).getOwn_tel()); 
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
	public String findtimevehiexcle(){
		try {
				String data=(String) ActionContext.getContext().getSession().get("data");
				list2=f.findtimevhic(stime, etime, quyu, compname);
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

				WritableSheet ws = wwb.createSheet("为营运车辆查询",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"公司"); 
            	ws.addCell(label);
            	label = new Label(1,0,"区域"); 
            	ws.addCell(label);
            	label = new Label(2,0,"车号"); 
            	ws.addCell(label);
            	label = new Label(3,0,"SIM卡号"); 
            	ws.addCell(label);
            	label = new Label(4,0,"司机姓名"); 
            	ws.addCell(label);
            	label = new Label(5,0,"司机电话"); 
            	ws.addCell(label);
                	for (int i = 0; i < list2.size(); i++) {
                    	label = new Label(0,i+1,list2.get(i).getComp_name()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,list2.get(i).getBa_name()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,list2.get(i).getVehi_no()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,list2.get(i).getVehi_sim()); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,list2.get(i).getOwn_name()); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,list2.get(i).getOwn_tel()); 
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
	public String findnolinebusexcle(){
		try {
			String data=(String) ActionContext.getContext().getSession().get("data");
			listexcle=f.findnolinebus(stime,etime,areaid,compid,data);
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

				WritableSheet ws = wwb.createSheet("未上线营运车辆查询",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"公司"); 
            	ws.addCell(label);
            	label = new Label(1,0,"车号"); 
            	ws.addCell(label);
            	label = new Label(2,0,"SIM卡号"); 
            	ws.addCell(label);
            	label = new Label(3,0,"司机姓名"); 
            	ws.addCell(label);
            	label = new Label(4,0,"司机电话"); 
            	ws.addCell(label);
                	for (int i = 0; i < listexcle.size(); i++) {
                    	label = new Label(0,i+1,listexcle.get(i).getComp_id()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,listexcle.get(i).getVehi_no()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,listexcle.get(i).getVehi_sim()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,listexcle.get(i).getOwn_name()); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,listexcle.get(i).getOwn_tel()); 
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
	public String findnolineoffbusexcle(){
		try {
			String data=(String) ActionContext.getContext().getSession().get("data");
			listexcle=f.findnolineoffbus(stime,etime,areaid,compid,data);
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

				WritableSheet ws = wwb.createSheet("上线无营运车辆查询",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"公司"); 
            	ws.addCell(label);
            	label = new Label(1,0,"车号"); 	
            	ws.addCell(label);
            	label = new Label(2,0,"SIM卡号"); 
            	ws.addCell(label);
            	label = new Label(3,0,"司机姓名"); 
            	ws.addCell(label);
            	label = new Label(4,0,"司机电话"); 
            	ws.addCell(label);
                	for (int i = 0; i < listexcle.size(); i++) {
                    	label = new Label(0,i+1,listexcle.get(i).getComp_id()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,listexcle.get(i).getVehi_no()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,listexcle.get(i).getVehi_sim()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,listexcle.get(i).getOwn_name()); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,listexcle.get(i).getOwn_tel()); 
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
	public String findnoemptyexcle(){
		try {
			String data=(String) ActionContext.getContext().getSession().get("data");
			listexcle=f.findnoempty(stime,etime,areaid,compid,data);		
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date dBegin = null,dEnd = null;
				try {
					dBegin = sdf.parse(stime);
					dEnd = sdf.parse(etime);  
				} catch (ParseException e) {
					e.printStackTrace();
				}  	      
				List<String> lDate = findDates(dBegin, dEnd);  
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

				WritableSheet ws = wwb.createSheet("无空车变化车辆查询",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"公司"); 
            	ws.addCell(label);
            	label = new Label(1,0,"车号"); 
            	ws.addCell(label);
            	for(int z=0;z<lDate.size();z++){
            		label = new Label(z+2,0,lDate.get(z)); 
                	ws.addCell(label);
            	}
                	for (int i = 0; i < listexcle.size(); i++) {
                    	label = new Label(0,i+1,listexcle.get(i).getComp_id()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,listexcle.get(i).getVehi_no()); 
                    	ws.addCell(label);
                    	String[] arr = listexcle.get(i).getBa_id().split(";");
                    	for(int j=0;j<arr.length;j++){
                    		if(arr[j].equals("null")){
                    			label = new Label(j+2,i+1,""); 
                    		}else{                    			
                    			label = new Label(j+2,i+1,arr[j]); 
                    		}
                        	ws.addCell(label);
                    	}
                    	
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
	
	private List<String> findDates(Date beginDate, Date endDate) {
		List<String> lDate = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		if (cal.getTime().before(endDate) || cal.getTime().equals(endDate)) {
			lDate.add(sdf.format(beginDate));// 把开始时间加入集合
			boolean bContinue = true;
			while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime())) {
				lDate.add(sdf.format(cal.getTime()));
			} else {
				break;
				
			}
		}
			if ( !beginDate.equals(endDate)) {					
				lDate.add(sdf.format(endDate));// 把结束时间加入集合
			}
		}
		return lDate;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public List<Vehicle> getVehicle() {
		return vehicle;
	}
	public void setVehicle(List<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}
	public List<Vhic> getList() {
		return list;
	}
	public void setList(List<Vhic> list) {
		this.list = list;
	}
	public List<String> getList1() {
		return list1;
	}
	public void setList1(List<String> list1) {
		this.list1 = list1;
	}
	public FindDao getF() {
		return f;
	}
	public void setF(FindDao f) {
		this.f = f;
	}
	public String getQuyu() {
		return quyu;
	}
	public void setQuyu(String quyu) {
		this.quyu = quyu;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public List<Vhic> getList2() {
		return list2;
	}
	public void setList2(List<Vhic> list2) {
		this.list2 = list2;
	}
	public String getI() {
		return i;
	}
	public void setI(String i) {
		this.i = i;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Vhic> getList3() {
		return list3;
	}
	public void setList3(List<Vhic> list3) {
		this.list3 = list3;
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
	public List<Vhic> getListexcle() {
		return listexcle;
	}
	public void setListexcle(List<Vhic> listexcle) {
		this.listexcle = listexcle;
	}
	public List<Vhic> getList4() {
		return list4;
	}
	public void setList4(List<Vhic> list4) {
		this.list4 = list4;
	}
	public List<Vhic> getList5() {
		return list5;
	}
	public void setList5(List<Vhic> list5) {
		this.list5 = list5;
	}
	public List<Vhic> getList6() {
		return list6;
	}
	public void setList6(List<Vhic> list6) {
		this.list6 = list6;
	}
	
	public List<Vhic> getList7() {
		return list7;
	}
	public void setList7(List<Vhic> list7) {
		this.list7 = list7;
	}
	
	public List<Vhic> getList8() {
		return list8;
	}
	public void setList8(List<Vhic> list8) {
		this.list8 = list8;
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
	public String getCompname() {
		return compname;
	}
	public void setCompname(String compname) {
		this.compname = compname;
	}
	public String getCompid() {
		return compid;
	}
	public void setCompid(String compid) {
		this.compid = compid;
	}
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public String getVhic() {
		return vhic;
	}
	public void setVhic(String vhic) {
		this.vhic = vhic;
	}
	public List<Map<String, Object>> getL() {
		return l;
	}
	public void setL(List<Map<String, Object>> l) {
		this.l = l;
	}
	public List<Wx> getWx() {
		return wx;
	}
	public void setWx(List<Wx> wx) {
		this.wx = wx;
	}
	public Vhic getV() {
		return v;
	}
	public void setV(Vhic v) {
		this.v = v;
	}
	public String getCphm() {
		return cphm;
	}
	public void setCphm(String cphm) {
		this.cphm = cphm;
	}
	public String getLxr() {
		return lxr;
	}
	public void setLxr(String lxr) {
		this.lxr = lxr;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getGzxx() {
		return gzxx;
	}
	public void setGzxx(String gzxx) {
		this.gzxx = gzxx;
	}
	public String getGzms() {
		return gzms;
	}
	public void setGzms(String gzms) {
		this.gzms = gzms;
	}
	public String getWxlx() {
		return wxlx;
	}
	public void setWxlx(String wxlx) {
		this.wxlx = wxlx;
	}
	public String getDqgz() {
		return dqgz;
	}
	public void setDqgz(String dqgz) {
		this.dqgz = dqgz;
	}
	public String getWxfy() {
		return wxfy;
	}
	public void setWxfy(String wxfy) {
		this.wxfy = wxfy;
	}
	public String getWxry() {
		return wxry;
	}
	public void setWxry(String wxry) {
		this.wxry = wxry;
	}
	public String getWxdd() {
		return wxdd;
	}
	public void setWxdd(String wxdd) {
		this.wxdd = wxdd;
	}
	public String getWxnr() {
		return wxnr;
	}
	public void setWxnr(String wxnr) {
		this.wxnr = wxnr;
	}
	public String getSxsj() {
		return sxsj;
	}
	public void setSxsj(String sxsj) {
		this.sxsj = sxsj;
	}
	public String getWxsj() {
		return wxsj;
	}
	public void setWxsj(String wxsj) {
		this.wxsj = wxsj;
	}
	public String getMyd() {
		return myd;
	}
	public void setMyd(String myd) {
		this.myd = myd;
	}
	
}
