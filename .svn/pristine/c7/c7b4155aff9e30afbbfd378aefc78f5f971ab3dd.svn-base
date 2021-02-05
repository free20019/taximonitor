package com.tw.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.tw.dao.videoDAO;
import com.tw.entity.Company;
import com.tw.entity.User;
import com.tw.entity.Vehicle;

public  class VideoAction implements Action{
	private String xlsfilename;
	private String fanhuei;
	private String action;
	
	private String keyword;
	private String cars;
	private String yh;
	private List<Company>list=new ArrayList<Company>();
	private List<Vehicle>vehicle=new ArrayList<Vehicle>();
	private videoDAO v=new videoDAO();
	//查询通用视频终端的公司
	public String findmdtvideo(){
		HttpSession session= ServletActionContext.getRequest().getSession();
		String username=(String)session.getAttribute("um");
		list=v.findmdtvideo(username);
//		System.out.println(username+"   @@@");
		vehicle=v.findvhicvideo(username);
		return SUCCESS;
	}
	public String video(){
			try{
				String logfile=ServletActionContext.getServletContext().getRealPath("/");
				logfile=logfile+"video\\";
				File file= new File(logfile+"video.rar"); 
				this.xlsfilename=""+"video.rar";
				//this.xlsfilepath="//evaluation//"+xlsfilename;
				file.createNewFile(); //建立要输出的文件 
				OutputStream os = new FileOutputStream(file);//使用文件缓冲 
				WritableWorkbook wwb = Workbook.createWorkbook(os);//取EXCEL操作实例 
                wwb.write();//写入文件 
				wwb.close();
				os.close();
				this.fanhuei="文件下载成功";
				this.action="filedownload.action?inputPath="+this.xlsfilename;
			}catch(Exception e){
				this.fanhuei="文件下载失败!";
			}
		
		return SUCCESS;
	}
	
	public String hkyh(){
		User user=v.getUser(this.yh);
		ActionContext.getContext().getSession().put("data", user.getDate_view_type());
		return SUCCESS;
	}
	
	
	public String queryvediovhic(){
		HttpSession session= ServletActionContext.getRequest().getSession();
		String comps=(String)session.getAttribute("data");
		
		JSONArray jsonArray = v.findcamera(this.keyword,comps);
		JSONObject hksp = new JSONObject(); 
		hksp.put("id", "hksp");
		hksp.put("pId", "");
		hksp.put("name", "海康视频");
		hksp.put("isParent",true);
		hksp.put("open",true);
		jsonArray.add(hksp);
		cars = jsonArray.toString();
		return SUCCESS;  
	}
	
//	public String queryvediovhic(){
//		HttpSession session= ServletActionContext.getRequest().getSession();
//		String comps=(String)session.getAttribute("data");
//		
//		List<Vehicle> ts = v.findcamera(this.keyword,comps);
////		List<Company> comp = v.findcomp(comps);
//		JSONArray jsonArray = new JSONArray();
//		for(int i=0;i<ts.size();i++){
//			JSONObject one = new JSONObject(); 
//			one.put("id", ts.get(i).getId()); 
//			one.put("open", "close");
//			one.put("name", ts.get(i).getVehi_no());
//			String lx= "hksp";
//			one.put("pId", "hksp");
//			if(ts.get(i).getOnoffstate().equals("0")){
//				one.put("icon", "../img/video/off.png");
//			}else if(ts.get(i).getOnoffstate().equals("1")){
//				one.put("icon", "../img/video/on.png");
//				JSONObject td1 = new JSONObject(); 
//				td1.put("id", "td1");
//				td1.put("pId", ts.get(i).getId());
//				td1.put("name", "通道1");
//				td1.put("icon", "../img/video/mdt.png");
//				td1.put("camera", ts.get(i).getMdtno());
//				td1.put("lx", lx);
//				td1.put("td", "1");
//				td1.put("vehinum",ts.get(i).getVehi_no());
//				JSONObject td2 = new JSONObject(); 
//				td2.put("id", "td2");
//				td2.put("pId", ts.get(i).getId());
//				td2.put("name", "通道2");
//				td2.put("icon", "../img/video/mdt.png");
//				td2.put("camera", ts.get(i).getMdtno());
//				td2.put("lx", lx);
//				td2.put("td", "2");
//				td2.put("vehinum",ts.get(i).getVehi_no());
//				JSONObject td3 = new JSONObject(); 
//				td3.put("id", "td3");
//				td3.put("pId", ts.get(i).getId());
//				td3.put("name", "通道3");
//				td3.put("icon", "../img/video/mdt.png");
//				td3.put("camera", ts.get(i).getMdtno());
//				td3.put("lx", lx);
//				td3.put("td", "3");
//				td3.put("vehinum",ts.get(i).getVehi_no());
//				JSONObject td4 = new JSONObject(); 
//				td4.put("id", "td4");
//				td4.put("pId", ts.get(i).getId());
//				td4.put("name", "通道4");
//				td4.put("icon", "../img/video/mdt.png");
//				td4.put("camera", ts.get(i).getMdtno());
//				td4.put("lx", lx);
//				td4.put("td", "4");
//				td4.put("vehinum",ts.get(i).getVehi_no());
//				jsonArray.add(td1);
//				jsonArray.add(td2);
//				jsonArray.add(td3);
//				jsonArray.add(td4);
//			}
//			jsonArray.add(one);
//		}
////		JSONObject tysp = new JSONObject(); 
////		tysp.put("id", "tysp");
////		tysp.put("pId", "");
////		tysp.put("name", "通用视频");
////		tysp.put("isParent",true);
//		JSONObject hksp = new JSONObject(); 
//		hksp.put("id", "hksp");
//		hksp.put("pId", "");
//		hksp.put("name", "海康视频");
//		hksp.put("isParent",true);
//		hksp.put("open",true);
//		jsonArray.add(hksp);
////		jsonArray.add(tysp);
//		
////		for(int i=0;i<comp.size();i++){
////			JSONObject one = new JSONObject(); 
////			one.put("id", comp.get(i).getId()); 
////			one.put("pId", "");
////			one.put("open", "close");
////			one.put("name", comp.get(i).getName());
////			one.put("parent", true);
////			jsonArray.add(one);
////		}
//		cars = jsonArray.toString();
//		return SUCCESS;  
//	}
	
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
	public List<Company> getList() {
		return list;
	}
	public void setList(List<Company> list) {
		this.list = list;
	}
	public List<Vehicle> getVehicle() {
		return vehicle;
	}
	public void setVehicle(List<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCars() {
		return cars;
	}
	public void setCars(String cars) {
		this.cars = cars;
	}
	public String getYh() {
		return yh;
	}
	public void setYh(String yh) {
		this.yh = yh;
	}
	
}
