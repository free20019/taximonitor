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
import com.tw.dao.HalfMonthDao;
import com.tw.dao.LoadOnlineDAO;
import com.tw.entity.HalfMonth;
import com.tw.entity.LOADONLINE;

public class LoadOnlineAction implements Action{
	private List<LOADONLINE>list=new ArrayList<LOADONLINE>();
	private List<LOADONLINE>list1=new ArrayList<LOADONLINE>();
	private List<List<LOADONLINE>>list2=new ArrayList<List<LOADONLINE>>();
	private String stime;
	private String etime;
	private String xlsfilename;
	private String fanhuei;
	private String action;
	private String time;
	LOADONLINE loadonline =new LOADONLINE();
	private HalfMonthDao h=new HalfMonthDao();
	private HalfMonth halfMonth=new HalfMonth();
	private List<LOADONLINE>listexcle=new ArrayList<LOADONLINE>();
	private List<LOADONLINE>list1excle=new ArrayList<LOADONLINE>();
	private List<LOADONLINE>loadlistexcle=new ArrayList<LOADONLINE>();
	private List<LOADONLINE>loadlist1excle=new ArrayList<LOADONLINE>();
	LoadOnlineDAO l=new LoadOnlineDAO();
	public String findonline(){
		list1=l.findaverage(time);
		String[][] shuzu=null;
		List<LOADONLINE>list=new ArrayList<LOADONLINE>();
		List<String[][]>aa=new ArrayList<String[][]>();
		for (int i = 0; i < 6; i++) {
			shuzu =l.findloadonline(time,i);
			aa.add(i, shuzu);
		}
		loadonline.setOl(aa);
		halfMonth=h.findonlineloadmaxmin(time);
		return SUCCESS;
	}
	public String findloadexcle(){
		LoadOnlineDAO l=new LoadOnlineDAO();
				try {
					String[][] shuzu=null;
					List<LOADONLINE>list=new ArrayList<LOADONLINE>();
					List<String[][]>aa=new ArrayList<String[][]>();
					for (int i = 0; i < 6; i++) {
						shuzu =l.findloadonline(time,i);
						aa.add(i, shuzu);
					}
					loadonline.setOl(aa);
					loadlist1excle=l.findaverage(time);
					halfMonth=h.findonlineloadmaxmin(time);
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

						WritableSheet ws = wwb.createSheet("重车率分析",0);//创建Excel工作表 指定名称和位置 
						Label label ;
		            	label = new Label(0,0,"重车率"); 
		            	ws.addCell(label);
		            	label = new Label(1,0,"00:00"); 
		            	ws.addCell(label);
		            	label = new Label(2,0,"00:30"); 
		            	ws.addCell(label);
		            	label = new Label(3,0,"01:00"); 
		            	ws.addCell(label);
		            	label = new Label(4,0,"01:30"); 
		            	ws.addCell(label);
		            	label = new Label(5,0,"02:00"); 
		            	ws.addCell(label);
		            	label = new Label(6,0,"02:30"); 
		            	ws.addCell(label);
		            	label = new Label(7,0,"03:00"); 
		            	ws.addCell(label);
		            	label = new Label(8,0,"03:30"); 
		            	ws.addCell(label);
		            	label = new Label(9,0,"04:00"); 
		            	ws.addCell(label);
		            	label = new Label(10,0,"04:30"); 
		            	ws.addCell(label);
		            	label = new Label(11,0,"05:00"); 
		            	ws.addCell(label);
		            	label = new Label(12,0,"05:30"); 
		            	ws.addCell(label);
		            	label = new Label(13,0,"06:00"); 
		            	ws.addCell(label);
		            	label = new Label(14,0,"06:30"); 
		            	ws.addCell(label);
		            	label = new Label(15,0,"07:00"); 
		            	ws.addCell(label);
		            	label = new Label(16,0,"07:30"); 
		            	ws.addCell(label);
		            	label = new Label(17,0,"08:00"); 
		            	ws.addCell(label);
		            	label = new Label(18,0,"08:30"); 
		            	ws.addCell(label);
		            	label = new Label(19,0,"09:00"); 
		            	ws.addCell(label);
		            	label = new Label(20,0,"09:30"); 
		            	ws.addCell(label);
		            	label = new Label(21,0,"10:00"); 
		            	ws.addCell(label);
		            	label = new Label(22,0,"10:30"); 
		            	ws.addCell(label);
		            	label = new Label(23,0,"11:00"); 
		            	ws.addCell(label);
		            	label = new Label(24,0,"11:30"); 
		            	ws.addCell(label);
		            	label = new Label(25,0,"12:00"); 
		            	ws.addCell(label);
		            	label = new Label(26,0,"12:30"); 
		            	ws.addCell(label);
		            	label = new Label(27,0,"13:00"); 
		            	ws.addCell(label);
		            	label = new Label(28,0,"13:30"); 
		            	ws.addCell(label);
		            	label = new Label(29,0,"14:00"); 
		            	ws.addCell(label);
		            	label = new Label(30,0,"14:30"); 
		            	ws.addCell(label);
		            	label = new Label(31,0,"15:00"); 
		            	ws.addCell(label);
		            	label = new Label(32,0,"15:30"); 
		            	ws.addCell(label);
		            	label = new Label(33,0,"16:00"); 
		            	ws.addCell(label);
		            	label = new Label(34,0,"16:30"); 
		            	ws.addCell(label);
		            	label = new Label(35,0,"17:00"); 
		            	ws.addCell(label);
		            	label = new Label(36,0,"17:30"); 
		            	ws.addCell(label);
		            	label = new Label(37,0,"18:00"); 
		            	ws.addCell(label);
		            	label = new Label(38,0,"18:30"); 
		            	ws.addCell(label);
		            	label = new Label(39,0,"19:00"); 
		            	ws.addCell(label);
		            	label = new Label(40,0,"19:30"); 
		            	ws.addCell(label);
		            	label = new Label(41,0,"20:00"); 
		            	ws.addCell(label);
		            	label = new Label(42,0,"20:30"); 
		            	ws.addCell(label);
		            	label = new Label(43,0,"21:00"); 
		            	ws.addCell(label);
		            	label = new Label(44,0,"21:30"); 
		            	ws.addCell(label);
		            	label = new Label(45,0,"22:00"); 
		            	ws.addCell(label);
		            	label = new Label(46,0,"22:30"); 
		            	ws.addCell(label);
		            	label = new Label(47,0,"23:00"); 
		            	ws.addCell(label);
		            	label = new Label(48,0,"23:30"); 
		            	ws.addCell(label);
		            	label = new Label(0,1,"今天"); 
		            	ws.addCell(label);
		            	label = new Label(0,2,"昨天"); 
		            	ws.addCell(label);
		            	label = new Label(0,3,"前天"); 
		            	ws.addCell(label);
		            	label = new Label(0,4,"上周"); 
		            	ws.addCell(label);
		            	label = new Label(0,5,"上周平均"); 
		            	ws.addCell(label);
		            	label = new Label(0,6,"前半月最大"); 
		            	ws.addCell(label);
		            	label = new Label(0,7,"前半月最小"); 
		            	ws.addCell(label);
		            	label = new Label(0,8,"上月"); 
		            	ws.addCell(label);
		            	label = new Label(0,9,"上年"); 
		            	ws.addCell(label);
		            	String[][]shangnianj=loadonline.getOl().get(0);
		            	for (int i = 0; i < shangnianj[1].length; i++) {
		            		if (!shangnianj[1][i].equals("&nbsp;")) {
		            			label = new Label(i+1,9,shangnianj[1][i]); 
		            			ws.addCell(label);
							}
						}
		            	String[][]shangyuej=loadonline.getOl().get(1);
		            	for (int i = 0; i < shangyuej[1].length; i++) {
		            		if (!shangyuej[1][i].equals("&nbsp;")) {
		            			label = new Label(i+1,8,shangyuej[1][i]); 
		            			ws.addCell(label);
							}
						}
		            	String[] max=halfMonth.getArealoadmax();
		            	for (int i = 0; i < max.length; i++) {
							label=new Label(i+1,6,max[i]);
							ws.addCell(label);
						}
		            	String[] min=halfMonth.getArealoadmin();
		            	for (int i = 0; i < min.length; i++) {
							label=new Label(i+1,7,min[i]);
							ws.addCell(label);
						}
		            	String[][]shangzhouj=loadonline.getOl().get(2);
		            	for (int i = 0; i < shangzhouj[1].length; i++) {
		            		if (!shangzhouj[1][i].equals("&nbsp;")) {
		            			label = new Label(i+1,4,shangzhouj[1][i]); 
		            			ws.addCell(label);
							}
						}
		            	String[][]qiantianj=loadonline.getOl().get(3);
		            	for (int i = 0; i < qiantianj[1].length; i++) {
		            		if (!qiantianj[1][i].equals("&nbsp;")) {
		            			label = new Label(i+1,3,qiantianj[1][i]); 
		            			ws.addCell(label);
							}
						}
		            	String[][]zuotianj=loadonline.getOl().get(4);
		            	for (int i = 0; i < zuotianj[1].length; i++) {
		            		if (!zuotianj[1][i].equals("&nbsp;")) {
		            			label = new Label(i+1,2,zuotianj[1][i]); 
		            			ws.addCell(label);
							}
						}
		            	String[][]jintianj=loadonline.getOl().get(5);
		            	for (int i = 0; i < jintianj[1].length; i++) {
		            		if (!jintianj[1][i].equals("&nbsp;")) {
		            			label = new Label(i+1,1,jintianj[1][i]); 
		            			ws.addCell(label);
							}
						}
		                	List<String>pjload=loadlist1excle.get(0).getPjload();
		                	for (int i = 0; i < pjload.size(); i++) {
		                    	label = new Label(i+1,5,pjload.get(i)+"%"); 
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
	public String findallloadexcle(){
		LoadOnlineDAO l=new LoadOnlineDAO();
				try {
					String[][] shuzu=null;
					List<LOADONLINE>list=new ArrayList<LOADONLINE>();
					List<String[][]>aa=new ArrayList<String[][]>();
					for (int i = 0; i < 6; i++) {
						shuzu =l.findloadonline(time,i);
						aa.add(i, shuzu);
					}
					loadonline.setOl(aa);
					loadlist1excle=l.findaverage(time);
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

						WritableSheet ws = wwb.createSheet("重车率分析",0);//创建Excel工作表 指定名称和位置 
						Label label ;
		            	label = new Label(0,0,"重车率"); 
		            	ws.addCell(label);
		            	label = new Label(1,0,"00:00"); 
		            	ws.addCell(label);
		            	label = new Label(2,0,"00:30"); 
		            	ws.addCell(label);
		            	label = new Label(3,0,"01:00"); 
		            	ws.addCell(label);
		            	label = new Label(4,0,"01:30"); 
		            	ws.addCell(label);
		            	label = new Label(5,0,"02:00"); 
		            	ws.addCell(label);
		            	label = new Label(6,0,"02:30"); 
		            	ws.addCell(label);
		            	label = new Label(7,0,"03:00"); 
		            	ws.addCell(label);
		            	label = new Label(8,0,"03:30"); 
		            	ws.addCell(label);
		            	label = new Label(9,0,"04:00"); 
		            	ws.addCell(label);
		            	label = new Label(10,0,"04:30"); 
		            	ws.addCell(label);
		            	label = new Label(11,0,"05:00"); 
		            	ws.addCell(label);
		            	label = new Label(12,0,"05:30"); 
		            	ws.addCell(label);
		            	label = new Label(13,0,"06:00"); 
		            	ws.addCell(label);
		            	label = new Label(14,0,"06:30"); 
		            	ws.addCell(label);
		            	label = new Label(15,0,"07:00"); 
		            	ws.addCell(label);
		            	label = new Label(16,0,"07:30"); 
		            	ws.addCell(label);
		            	label = new Label(17,0,"08:00"); 
		            	ws.addCell(label);
		            	label = new Label(18,0,"08:30"); 
		            	ws.addCell(label);
		            	label = new Label(19,0,"09:00"); 
		            	ws.addCell(label);
		            	label = new Label(20,0,"09:30"); 
		            	ws.addCell(label);
		            	label = new Label(21,0,"10:00"); 
		            	ws.addCell(label);
		            	label = new Label(22,0,"10:30"); 
		            	ws.addCell(label);
		            	label = new Label(23,0,"11:00"); 
		            	ws.addCell(label);
		            	label = new Label(24,0,"11:30"); 
		            	ws.addCell(label);
		            	label = new Label(25,0,"12:00"); 
		            	ws.addCell(label);
		            	label = new Label(26,0,"12:30"); 
		            	ws.addCell(label);
		            	label = new Label(27,0,"13:00"); 
		            	ws.addCell(label);
		            	label = new Label(28,0,"13:30"); 
		            	ws.addCell(label);
		            	label = new Label(29,0,"14:00"); 
		            	ws.addCell(label);
		            	label = new Label(30,0,"14:30"); 
		            	ws.addCell(label);
		            	label = new Label(31,0,"15:00"); 
		            	ws.addCell(label);
		            	label = new Label(32,0,"15:30"); 
		            	ws.addCell(label);
		            	label = new Label(33,0,"16:00"); 
		            	ws.addCell(label);
		            	label = new Label(34,0,"16:30"); 
		            	ws.addCell(label);
		            	label = new Label(35,0,"17:00"); 
		            	ws.addCell(label);
		            	label = new Label(36,0,"17:30"); 
		            	ws.addCell(label);
		            	label = new Label(37,0,"18:00"); 
		            	ws.addCell(label);
		            	label = new Label(38,0,"18:30"); 
		            	ws.addCell(label);
		            	label = new Label(39,0,"19:00"); 
		            	ws.addCell(label);
		            	label = new Label(40,0,"19:30"); 
		            	ws.addCell(label);
		            	label = new Label(41,0,"20:00"); 
		            	ws.addCell(label);
		            	label = new Label(42,0,"20:30"); 
		            	ws.addCell(label);
		            	label = new Label(43,0,"21:00"); 
		            	ws.addCell(label);
		            	label = new Label(44,0,"21:30"); 
		            	ws.addCell(label);
		            	label = new Label(45,0,"22:00"); 
		            	ws.addCell(label);
		            	label = new Label(46,0,"22:30"); 
		            	ws.addCell(label);
		            	label = new Label(47,0,"23:00"); 
		            	ws.addCell(label);
		            	label = new Label(48,0,"23:30"); 
		            	ws.addCell(label);
		            	label = new Label(0,1,"今天"); 
		            	ws.addCell(label);
		            	label = new Label(0,2,"昨天"); 
		            	ws.addCell(label);
		            	label = new Label(0,3,"前天"); 
		            	ws.addCell(label);
		            	label = new Label(0,4,"上周"); 
		            	ws.addCell(label);
		            	label = new Label(0,5,"上周平均"); 
		            	ws.addCell(label);
		            	label = new Label(0,6,"上月"); 
		            	ws.addCell(label);
		            	label = new Label(0,7,"上年"); 
		            	ws.addCell(label);
		            	String[][]shangnianj=loadonline.getOl().get(0);
		            	for (int i = 0; i < shangnianj[0].length; i++) {
		            		if (!shangnianj[0][i].equals("&nbsp;")) {
		            			label = new Label(i+1,7,shangnianj[0][i]); 
		            			ws.addCell(label);
							}
						}
		            	String[][]shangyuej=loadonline.getOl().get(1);
		            	for (int i = 0; i < shangyuej[0].length; i++) {
		            		if (!shangyuej[0][i].equals("&nbsp;")) {
		            			label = new Label(i+1,6,shangyuej[0][i]); 
		            			ws.addCell(label);
							}
						}
		            	String[][]shangzhouj=loadonline.getOl().get(2);
		            	for (int i = 0; i < shangzhouj[0].length; i++) {
		            		if (!shangzhouj[0][i].equals("&nbsp;")) {
		            			label = new Label(i+1,4,shangzhouj[0][i]); 
		            			ws.addCell(label);
							}
						}
		            	String[][]qiantianj=loadonline.getOl().get(3);
		            	for (int i = 0; i < qiantianj[0].length; i++) {
		            		if (!qiantianj[0][i].equals("&nbsp;")) {
		            			label = new Label(i+1,3,qiantianj[0][i]); 
		            			ws.addCell(label);
							}
						}
		            	String[][]zuotianj=loadonline.getOl().get(4);
		            	for (int i = 0; i < zuotianj[0].length; i++) {
		            		if (!zuotianj[0][i].equals("&nbsp;")) {
		            			label = new Label(i+1,2,zuotianj[0][i]); 
		            			ws.addCell(label);
							}
						}
		            	String[][]jintianj=loadonline.getOl().get(5);
		            	for (int i = 0; i < jintianj[0].length; i++) {
		            		if (!jintianj[0][i].equals("&nbsp;")) {
		            			label = new Label(i+1,1,jintianj[0][i]); 
		            			ws.addCell(label);
							}
						}
		                	List<String>pjallload=loadlist1excle.get(0).getPjallload();
		                	for (int i = 0; i < pjallload.size(); i++) {
		                    	label = new Label(i+1,5,pjallload.get(i)); 
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
	public String findallonlineexcle(){
		LoadOnlineDAO l=new LoadOnlineDAO();
				try {
					String[][] shuzu=null;
					List<LOADONLINE>list=new ArrayList<LOADONLINE>();
					List<String[][]>aa=new ArrayList<String[][]>();
					for (int i = 0; i < 6; i++) {
						shuzu =l.findloadonline(time,i);
						aa.add(i, shuzu);
					}
					loadonline.setOl(aa);
					loadlist1excle=l.findaverage(time);
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

						WritableSheet ws = wwb.createSheet("上线率分析",0);//创建Excel工作表 指定名称和位置 
						Label label ;
		            	label = new Label(0,0,"上线率"); 
		            	ws.addCell(label);
		            	label = new Label(1,0,"00:00"); 
		            	ws.addCell(label);
		            	label = new Label(2,0,"00:30"); 
		            	ws.addCell(label);
		            	label = new Label(3,0,"01:00"); 
		            	ws.addCell(label);
		            	label = new Label(4,0,"01:30"); 
		            	ws.addCell(label);
		            	label = new Label(5,0,"02:00"); 
		            	ws.addCell(label);
		            	label = new Label(6,0,"02:30"); 
		            	ws.addCell(label);
		            	label = new Label(7,0,"03:00"); 
		            	ws.addCell(label);
		            	label = new Label(8,0,"03:30"); 
		            	ws.addCell(label);
		            	label = new Label(9,0,"04:00"); 
		            	ws.addCell(label);
		            	label = new Label(10,0,"04:30"); 
		            	ws.addCell(label);
		            	label = new Label(11,0,"05:00"); 
		            	ws.addCell(label);
		            	label = new Label(12,0,"05:30"); 
		            	ws.addCell(label);
		            	label = new Label(13,0,"06:00"); 
		            	ws.addCell(label);
		            	label = new Label(14,0,"06:30"); 
		            	ws.addCell(label);
		            	label = new Label(15,0,"07:00"); 
		            	ws.addCell(label);
		            	label = new Label(16,0,"07:30"); 
		            	ws.addCell(label);
		            	label = new Label(17,0,"08:00"); 
		            	ws.addCell(label);
		            	label = new Label(18,0,"08:30"); 
		            	ws.addCell(label);
		            	label = new Label(19,0,"09:00"); 
		            	ws.addCell(label);
		            	label = new Label(20,0,"09:30"); 
		            	ws.addCell(label);
		            	label = new Label(21,0,"10:00"); 
		            	ws.addCell(label);
		            	label = new Label(22,0,"10:30"); 
		            	ws.addCell(label);
		            	label = new Label(23,0,"11:00"); 
		            	ws.addCell(label);
		            	label = new Label(24,0,"11:30"); 
		            	ws.addCell(label);
		            	label = new Label(25,0,"12:00"); 
		            	ws.addCell(label);
		            	label = new Label(26,0,"12:30"); 
		            	ws.addCell(label);
		            	label = new Label(27,0,"13:00"); 
		            	ws.addCell(label);
		            	label = new Label(28,0,"13:30"); 
		            	ws.addCell(label);
		            	label = new Label(29,0,"14:00"); 
		            	ws.addCell(label);
		            	label = new Label(30,0,"14:30"); 
		            	ws.addCell(label);
		            	label = new Label(31,0,"15:00"); 
		            	ws.addCell(label);
		            	label = new Label(32,0,"15:30"); 
		            	ws.addCell(label);
		            	label = new Label(33,0,"16:00"); 
		            	ws.addCell(label);
		            	label = new Label(34,0,"16:30"); 
		            	ws.addCell(label);
		            	label = new Label(35,0,"17:00"); 
		            	ws.addCell(label);
		            	label = new Label(36,0,"17:30"); 
		            	ws.addCell(label);
		            	label = new Label(37,0,"18:00"); 
		            	ws.addCell(label);
		            	label = new Label(38,0,"18:30"); 
		            	ws.addCell(label);
		            	label = new Label(39,0,"19:00"); 
		            	ws.addCell(label);
		            	label = new Label(40,0,"19:30"); 
		            	ws.addCell(label);
		            	label = new Label(41,0,"20:00"); 
		            	ws.addCell(label);
		            	label = new Label(42,0,"20:30"); 
		            	ws.addCell(label);
		            	label = new Label(43,0,"21:00"); 
		            	ws.addCell(label);
		            	label = new Label(44,0,"21:30"); 
		            	ws.addCell(label);
		            	label = new Label(45,0,"22:00"); 
		            	ws.addCell(label);
		            	label = new Label(46,0,"22:30"); 
		            	ws.addCell(label);
		            	label = new Label(47,0,"23:00"); 
		            	ws.addCell(label);
		            	label = new Label(48,0,"23:30"); 
		            	ws.addCell(label);
		            	label = new Label(0,1,"今天"); 
		            	ws.addCell(label);
		            	label = new Label(0,2,"昨天"); 
		            	ws.addCell(label);
		            	label = new Label(0,3,"前天"); 
		            	ws.addCell(label);
		            	label = new Label(0,4,"上周"); 
		            	ws.addCell(label);
		            	label = new Label(0,5,"上周平均"); 
		            	ws.addCell(label);
		            	label = new Label(0,6,"上月"); 
		            	ws.addCell(label);
		            	label = new Label(0,7,"上年"); 
		            	ws.addCell(label);
		            	String[][]shangnianj=loadonline.getOl().get(0);
		            	for (int i = 0; i < shangnianj[2].length; i++) {
		            		if (!shangnianj[2][i].equals("&nbsp;")) {
		            			label = new Label(i+1,7,shangnianj[2][i]); 
		            			ws.addCell(label);
							}
						}
		            	String[][]shangyuej=loadonline.getOl().get(1);
		            	for (int i = 0; i < shangyuej[2].length; i++) {
		            		if (!shangyuej[2][i].equals("&nbsp;")) {
		            			label = new Label(i+1,6,shangyuej[2][i]); 
		            			ws.addCell(label);
							}
						}
		            	String[][]shangzhouj=loadonline.getOl().get(2);
		            	for (int i = 0; i < shangzhouj[2].length; i++) {
		            		if (!shangzhouj[2][i].equals("&nbsp;")) {
		            			label = new Label(i+1,4,shangzhouj[2][i]); 
		            			ws.addCell(label);
							}
						}
		            	String[][]qiantianj=loadonline.getOl().get(3);
		            	for (int i = 0; i < qiantianj[2].length; i++) {
		            		if (!qiantianj[2][i].equals("&nbsp;")) {
		            			label = new Label(i+1,3,qiantianj[2][i]); 
		            			ws.addCell(label);
							}
						}
		            	String[][]zuotianj=loadonline.getOl().get(4);
		            	for (int i = 0; i < zuotianj[2].length; i++) {
		            		if (!zuotianj[2][i].equals("&nbsp;")) {
		            			label = new Label(i+1,2,zuotianj[2][i]); 
		            			ws.addCell(label);
							}
						}
		            	String[][]jintianj=loadonline.getOl().get(5);
		            	for (int i = 0; i < jintianj[2].length; i++) {
		            		if (!jintianj[2][i].equals("&nbsp;")) {
		            			label = new Label(i+1,1,jintianj[2][i]); 
		            			ws.addCell(label);
							}
						}
		                	List<String>pjallonline=loadlist1excle.get(0).getPjallonline();
		                	for (int i = 0; i < pjallonline.size(); i++) {
		                    	label = new Label(i+1,5,pjallonline.get(i)); 
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
	public String findonlineexcle(){

LoadOnlineDAO l=new LoadOnlineDAO();
		try {
			String[][] shuzu=null;
			List<LOADONLINE>list=new ArrayList<LOADONLINE>();
			List<String[][]>aa=new ArrayList<String[][]>();
			for (int i = 0; i < 6; i++) {
				shuzu =l.findloadonline(time,i);
				aa.add(i, shuzu);
			}
			loadonline.setOl(aa);
			loadlist1excle=l.findaverage(time);
			halfMonth=h.findonlineloadmaxmin(time);
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

				WritableSheet ws = wwb.createSheet("上线率分析",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"上线率"); 
            	ws.addCell(label);
            	label = new Label(1,0,"00:00"); 
            	ws.addCell(label);
            	label = new Label(2,0,"00:30"); 
            	ws.addCell(label);
            	label = new Label(3,0,"01:00"); 
            	ws.addCell(label);
            	label = new Label(4,0,"01:30"); 
            	ws.addCell(label);
            	label = new Label(5,0,"02:00"); 
            	ws.addCell(label);
            	label = new Label(6,0,"02:30"); 
            	ws.addCell(label);
            	label = new Label(7,0,"03:00"); 
            	ws.addCell(label);
            	label = new Label(8,0,"03:30"); 
            	ws.addCell(label);
            	label = new Label(9,0,"04:00"); 
            	ws.addCell(label);
            	label = new Label(10,0,"04:30"); 
            	ws.addCell(label);
            	label = new Label(11,0,"05:00"); 
            	ws.addCell(label);
            	label = new Label(12,0,"05:30"); 
            	ws.addCell(label);
            	label = new Label(13,0,"06:00"); 
            	ws.addCell(label);
            	label = new Label(14,0,"06:30"); 
            	ws.addCell(label);
            	label = new Label(15,0,"07:00"); 
            	ws.addCell(label);
            	label = new Label(16,0,"07:30"); 
            	ws.addCell(label);
            	label = new Label(17,0,"08:00"); 
            	ws.addCell(label);
            	label = new Label(18,0,"08:30"); 
            	ws.addCell(label);
            	label = new Label(19,0,"09:00"); 
            	ws.addCell(label);
            	label = new Label(20,0,"09:30"); 
            	ws.addCell(label);
            	label = new Label(21,0,"10:00"); 
            	ws.addCell(label);
            	label = new Label(22,0,"10:30"); 
            	ws.addCell(label);
            	label = new Label(23,0,"11:00"); 
            	ws.addCell(label);
            	label = new Label(24,0,"11:30"); 
            	ws.addCell(label);
            	label = new Label(25,0,"12:00"); 
            	ws.addCell(label);
            	label = new Label(26,0,"12:30"); 
            	ws.addCell(label);
            	label = new Label(27,0,"13:00"); 
            	ws.addCell(label);
            	label = new Label(28,0,"13:30"); 
            	ws.addCell(label);
            	label = new Label(29,0,"14:00"); 
            	ws.addCell(label);
            	label = new Label(30,0,"14:30"); 
            	ws.addCell(label);
            	label = new Label(31,0,"15:00"); 
            	ws.addCell(label);
            	label = new Label(32,0,"15:30"); 
            	ws.addCell(label);
            	label = new Label(33,0,"16:00"); 
            	ws.addCell(label);
            	label = new Label(34,0,"16:30"); 
            	ws.addCell(label);
            	label = new Label(35,0,"17:00"); 
            	ws.addCell(label);
            	label = new Label(36,0,"17:30"); 
            	ws.addCell(label);
            	label = new Label(37,0,"18:00"); 
            	ws.addCell(label);
            	label = new Label(38,0,"18:30"); 
            	ws.addCell(label);
            	label = new Label(39,0,"19:00"); 
            	ws.addCell(label);
            	label = new Label(40,0,"19:30"); 
            	ws.addCell(label);
            	label = new Label(41,0,"20:00"); 
            	ws.addCell(label);
            	label = new Label(42,0,"20:30"); 
            	ws.addCell(label);
            	label = new Label(43,0,"21:00"); 
            	ws.addCell(label);
            	label = new Label(44,0,"21:30"); 
            	ws.addCell(label);
            	label = new Label(45,0,"22:00"); 
            	ws.addCell(label);
            	label = new Label(46,0,"22:30"); 
            	ws.addCell(label);
            	label = new Label(47,0,"23:00"); 
            	ws.addCell(label);
            	label = new Label(48,0,"23:30"); 
            	ws.addCell(label);
            	label = new Label(0,1,"今天"); 
            	ws.addCell(label);
            	label = new Label(0,2,"昨天"); 
            	ws.addCell(label);
            	label = new Label(0,3,"前天"); 
            	ws.addCell(label);
            	label = new Label(0,4,"上周"); 
            	ws.addCell(label);
            	label = new Label(0,5,"上周平均"); 
            	ws.addCell(label);
            	label = new Label(0,6,"前半月最大"); 
            	ws.addCell(label);
            	label = new Label(0,7,"前半月最小"); 
            	ws.addCell(label);
            	label = new Label(0,8,"上月"); 
            	ws.addCell(label);
            	label = new Label(0,9,"上年"); 
            	ws.addCell(label);
            	String[][]shangnianj=loadonline.getOl().get(0);
            	for (int i = 0; i < shangnianj[3].length; i++) {
            		if (!shangnianj[3][i].equals("&nbsp;")) {
            			label = new Label(i+1,9,shangnianj[3][i]); 
            			ws.addCell(label);
					}
				}
            	String[][]shangyuej=loadonline.getOl().get(1);
            	for (int i = 0; i < shangyuej[3].length; i++) {
            		if (!shangyuej[3][i].equals("&nbsp;")) {
            			label = new Label(i+1,8,shangyuej[3][i]); 
            			ws.addCell(label);
					}
				}
            	String[] max=halfMonth.getArealoadmax();
            	for (int i = 0; i < max.length; i++) {
					label=new Label(i+1,6,max[i]);
					ws.addCell(label);
				}
            	String[] min=halfMonth.getArealoadmin();
            	for (int i = 0; i < min.length; i++) {
					label=new Label(i+1,7,min[i]);
					ws.addCell(label);
				}
            	String[][]shangzhouj=loadonline.getOl().get(2);
            	for (int i = 0; i < shangzhouj[3].length; i++) {
            		if (!shangzhouj[3][i].equals("&nbsp;")) {
            			label = new Label(i+1,4,shangzhouj[3][i]); 
            			ws.addCell(label);
					}
				}
            	String[][]qiantianj=loadonline.getOl().get(3);
            	for (int i = 0; i < qiantianj[3].length; i++) {
            		if (!qiantianj[3][i].equals("&nbsp;")) {
            			label = new Label(i+1,3,qiantianj[3][i]); 
            			ws.addCell(label);
					}
				}
            	String[][]zuotianj=loadonline.getOl().get(4);
            	for (int i = 0; i < zuotianj[3].length; i++) {
            		if (!zuotianj[3][i].equals("&nbsp;")) {
            			label = new Label(i+1,2,zuotianj[3][i]); 
            			ws.addCell(label);
					}
				}
            	String[][]jintianj=loadonline.getOl().get(5);
            	for (int i = 0; i < jintianj[3].length; i++) {
            		if (!jintianj[3][i].equals("&nbsp;")) {
            			label = new Label(i+1,1,jintianj[3][i]); 
            			ws.addCell(label);
					}
				}
                	List<String>pjload=loadlist1excle.get(0).getPjonline();
                	for (int i = 0; i < pjload.size(); i++) {
                    	label = new Label(i+1,5,pjload.get(i)+"%"); 
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
	public String execute() throws Exception {
		return null;
	}

	public List<LOADONLINE> getList() {
		return list;
	}

	public void setList(List<LOADONLINE> list) {
		this.list = list;
	}
	public List<LOADONLINE> getList1() {
		return list1;
	}
	public void setList1(List<LOADONLINE> list1) {
		this.list1 = list1;
	}
	public LoadOnlineDAO getL() {
		return l;
	}
	public void setL(LoadOnlineDAO l) {
		this.l = l;
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
	public List<LOADONLINE> getListexcle() {
		return listexcle;
	}
	public void setListexcle(List<LOADONLINE> listexcle) {
		this.listexcle = listexcle;
	}
	public List<LOADONLINE> getList1excle() {
		return list1excle;
	}
	public void setList1excle(List<LOADONLINE> list1excle) {
		this.list1excle = list1excle;
	}
	public List<LOADONLINE> getLoadlistexcle() {
		return loadlistexcle;
	}
	public void setLoadlistexcle(List<LOADONLINE> loadlistexcle) {
		this.loadlistexcle = loadlistexcle;
	}
	public List<LOADONLINE> getLoadlist1excle() {
		return loadlist1excle;
	}
	public void setLoadlist1excle(List<LOADONLINE> loadlist1excle) {
		this.loadlist1excle = loadlist1excle;
	}
	public List<List<LOADONLINE>> getList2() {
		return list2;
	}
	public void setList2(List<List<LOADONLINE>> list2) {
		this.list2 = list2;
	}
	public LOADONLINE getLoadonline() {
		return loadonline;
	}
	public void setLoadonline(LOADONLINE loadonline) {
		this.loadonline = loadonline;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public HalfMonthDao getH() {
		return h;
	}
	public void setH(HalfMonthDao h) {
		this.h = h;
	}
	public HalfMonth getHalfMonth() {
		return halfMonth;
	}
	public void setHalfMonth(HalfMonth halfMonth) {
		this.halfMonth = halfMonth;
	}
}
