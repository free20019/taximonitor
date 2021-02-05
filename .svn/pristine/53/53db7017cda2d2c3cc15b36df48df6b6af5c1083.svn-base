package com.tw.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.tw.dao.userDao;
import com.tw.entity.User;
import com.tw.entity.Vhic;
import com.tw.entity.station;

public class LoginAction implements Action{
	private String username;
	private String password;
	private String message;
	private userDao userDao = new userDao();
	private String stime;
	private String etime;
	private String compname;
	private String xlsfilename;
	private String fanhuei;
	private String action;
	private List<User>list=new ArrayList<User>();
	private List<User>listexcle=new ArrayList<User>();
	public String execute() throws Exception {
		return null;
	}
	public String login(){
		User user=userDao.getUser(username, password);
		if(user.getPassword()==null){
			return ERROR;
		}else{
		HttpSession s=ServletActionContext.getRequest().getSession();
		s.setAttribute("date", user.getDate_view_type());
		s.setAttribute("um", user.getUsername());
//		System.out.println("### "+s.getAttribute("um"));
		ActionContext.getContext().getSession().put("id", user.getId());
		ActionContext.getContext().getSession().put("username", user.getUsername());
		ActionContext.getContext().getSession().put("job", user.getJob_number());
		ActionContext.getContext().getSession().put("realname", user.getReal_name());
		ActionContext.getContext().getSession().put("data", user.getDate_view_type());
		ActionContext.getContext().getSession().put("parent", user.getParent());
		ActionContext.getContext().getSession().put("ymqx", user.getYmqx());
		ActionContext.getContext().getSession().put("glqx", user.getGlqx());
			return SUCCESS;
		}
	}
	public String findloginhistory(){
		list=userDao.findloginhistory(stime,etime,compname);
		return SUCCESS;
	}
	public String findloginhistoryexcle(){
		try {
			listexcle=userDao.findloginhistory(stime,etime,compname);
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

				WritableSheet ws = wwb.createSheet("平台使用情况",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"账号"); 
            	ws.addCell(label);
            	label = new Label(1,0,"公司"); 
            	ws.addCell(label);
            	label = new Label(2,0,"登录时间"); 
            	ws.addCell(label);
                	for (int i = 0; i < listexcle.size(); i++) {
                    	label = new Label(0,i+1,listexcle.get(i).getUsername()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,listexcle.get(i).getReal_name()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,listexcle.get(i).getYmqx()); 
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public userDao getUserDao() {
		return userDao;
	}
	public void setUserDao(userDao userDao) {
		this.userDao = userDao;
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
	public List<User> getList() {
		return list;
	}
	public void setList(List<User> list) {
		this.list = list;
	}
	public List<User> getListexcle() {
		return listexcle;
	}
	public void setListexcle(List<User> listexcle) {
		this.listexcle = listexcle;
	}
	
}
