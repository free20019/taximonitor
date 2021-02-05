package com.tw.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.tw.dao.CarDao;
import com.tw.entity.Groups;
import com.tw.entity.Vehicle;
import com.tw.entity.area1;

public class CarAction implements Action{
	private CarDao c=new CarDao();
	private List<Vehicle>list=new ArrayList<Vehicle>();
	private List<Groups>group=new ArrayList<Groups>();
	private Groups g=new Groups();
	private String vehi_no;
	private String mdt_type;
	private String  vehi_nolist;
	private String stime;
	private String etime;
	private String id;
	private String info;
	private String vhic;
	private String name;
	private List<Integer>num=new ArrayList<Integer>();
	private List<area1>area=new ArrayList<area1>();
	private String area_name;
	private String area_describe;
	private String area_coordinates;
	private String area_size;
	private String group_id;
	private String lati;
	private String longi;
	private String address;
	
	private String xlsfilename;
	private String fanhuei;
	private String action;
	public String addresss(){
		address = c.getplace(lati, longi);
		System.out.println(address);
		//注意：加上这句就必须设置响应的编码格式，否则会出现乱码  
        HttpServletResponse response = ServletActionContext.getResponse();  
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
  
        String dbusername = "Charles";  
         out.println(address);//返回的字符串数据  
        return null;  
	}
	//查询车辆在地图上显示
	public String findvhic(){
		String name=(String) ActionContext.getContext().getSession().get("data");
		list=c.findvhic(vehi_no, vehi_nolist,vhic,name);
		return SUCCESS;
	}
	//查询车辆不包含地址
	public String findvhic1(){
		String name=(String) ActionContext.getContext().getSession().get("data");
		list=c.findvhic1(vehi_no, vehi_nolist,vhic,name);
		return SUCCESS;
	}
	public String findvhichistory(){
		list=c.findhistroy(vehi_no, stime, etime);
		return SUCCESS;
	}
	//车辆组的增删改查
	public String findcargroup(){
		String userid=(String) ActionContext.getContext().getSession().get("id");
		group=c.findcargroup(userid);
		return SUCCESS;
	}
	public String findcargroupvhic(){
		list=c.findcargroupvhic(id);
		return SUCCESS;
	}
	public String delcargroup(){
		int a=c.delcargroup(id);
		if (a!=0) {
			c.delcargroupvhic(id);
			info="删除成功";
		}else {
			info="删除失败";
		}
		return SUCCESS;
	}
	public String addcargroup(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmss");
		Date d=new Date();
		String userid=(String) ActionContext.getContext().getSession().get("id");
		id=sdf.format(d);
		int a=c.addcargroup(id, name,userid);
		if (a!=0) {
			info="增加成功";
			String[] v=vhic.split(",");
			for (int i = 0; i < v.length; i++) {
				c.addcargroupvhic(id, v[i]);
			}
		}else {
			info="增加失败";
		}
		return SUCCESS;
	}
	public String findcargroupid(){
		g=c.findcargroupid(id);
		group=c.findcargroupidvhic(id);
		return SUCCESS;
	}
	public String editcargroup(){
		int a=c.delcargroup(id);
		if (a!=0) {
			c.delcargroupvhic(id);
			SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmss");
			Date d=new Date();
			id=sdf.format(d);
			String userid=(String) ActionContext.getContext().getSession().get("id");
			int b=c.addcargroup(id, name,userid);
			if (b!=0) {
				info="修改成功";
				String[] v=vhic.split(",");
				for (int i = 0; i < v.length; i++) {
					c.addcargroupvhic(id, v[i]);
				}
			}else {
				info="修改失败";
			}
		}else {
			info="修改失败";
		}
		return SUCCESS;
	}
	//查询车辆组及组内车辆数
	public String findgroupnum(){
		String userid=(String) ActionContext.getContext().getSession().get("id");
		group=c.findgroupnum(userid);
		return SUCCESS;
	}
	//查询车辆组车辆的详细信息
	public String findgroupinfo(){
		list=c.findgroupinfo(id);
		return SUCCESS;
	}
	//查询车辆在线下线载客空驶总数
	public String findnum(){
		String name=(String) ActionContext.getContext().getSession().get("data");
		num=c.findnum(name);
		return SUCCESS;
	}
	//查询区域
	public String findarea(){
		String userid=(String) ActionContext.getContext().getSession().get("id");
		area=c.findarea(id,userid);
		return SUCCESS;
	}
	//增加区域
	public String addarea(){
		int a=c.addarea(area_name, area_describe, area_coordinates, area_size,id);
		if (a!=0) {
			info="添加成功";
		}else {
			info="添加失败";
		}
		return SUCCESS;
	}
	//修改区域
	public String editarea(){
		int a=c.editarea(id, area_name, area_describe);
		if (a!=0) {
			info="修改成功";
		}else {
			info="修改失败";
		}
		return SUCCESS;
	}
	//删除区域
	public String delarea(){
		int a=c.delarea(id);
		if (a!=0) {
			info="删除成功";
		}else {
			info="删除失败";
		}
		return SUCCESS;
	}
	//查询区域和车辆
	public String findareavhic(){
		String name=(String) ActionContext.getContext().getSession().get("data");
		String userid=(String) ActionContext.getContext().getSession().get("id");
		list=c.findvhic1(vehi_no, vehi_nolist,vhic,name);
		area=c.findarea(id,userid);
		return SUCCESS;
	}
	//查询电子围栏
	public String findfence(){
		String userid=(String) ActionContext.getContext().getSession().get("id");
		area=c.findfence(id,userid);
		return SUCCESS;
	}
	//增加电子围栏
	public String addfence(){
		int a=c.addfence(area_name, area_describe, area_coordinates, area_size,group_id,id);
		if (a!=0) {
			info="添加成功";
		}else {
			info="添加失败";
		}
		return SUCCESS;
	}
	//修改电子围栏
	public String editfence(){
		int a=c.editfence(id, area_name, area_describe,group_id);
		if (a!=0) {
			info="修改成功";
		}else {
			info="修改失败";
		}
		return SUCCESS;
	}
	//删除电子围栏
	public String delfence(){
		int a=c.delfence(id);
		if (a!=0) {
			info="删除成功";
		}else {
			info="删除失败";
		}
		return SUCCESS;
	}
	//查询电子围栏所管理的车辆信息
	public String findfencevhicinfo(){
		list=c.findfencevhicinfo(id);
		return SUCCESS;
	}
	//向车辆终端发送信息
	public String send_message(){
		String a = c.client(vhic, info);
		return SUCCESS;
	}
	public List<area1> getArea() {
		return area;
	}
	public void setArea(List<area1> area) {
		this.area = area;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String groupId) {
		group_id = groupId;
	}
	public CarDao getC() {
		return c;
	}
	public void setC(CarDao c) {
		this.c = c;
	}
	public List<Vehicle> getList() {
		return list;
	}
	public void setList(List<Vehicle> list) {
		this.list = list;
	}
	
	public String getVehi_no() {
		return vehi_no;
	}

	public void setVehi_no(String vehiNo) {
		vehi_no = vehiNo;
	}

	public String getMdt_type() {
		return mdt_type;
	}
	public void setMdt_type(String mdt_type) {
		this.mdt_type = mdt_type;
	}
	public String getVehi_nolist() {
		return vehi_nolist;
	}
	public void setVehi_nolist(String vehiNolist) {
		vehi_nolist = vehiNolist;
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
	public String execute() throws Exception {
		return SUCCESS;
	}
	public List<Groups> getGroup() {
		return group;
	}
	public void setGroup(List<Groups> group) {
		this.group = group;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getVhic() {
		return vhic;
	}
	public void setVhic(String vhic) {
		this.vhic = vhic;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Groups getG() {
		return g;
	}
	public void setG(Groups g) {
		this.g = g;
	}
	public List<Integer> getNum() {
		return num;
	}
	public void setNum(List<Integer> num) {
		this.num = num;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String areaName) {
		area_name = areaName;
	}
	public String getArea_describe() {
		return area_describe;
	}
	public void setArea_describe(String areaDescribe) {
		area_describe = areaDescribe;
	}
	public String getArea_coordinates() {
		return area_coordinates;
	}
	public void setArea_coordinates(String areaCoordinates) {
		area_coordinates = areaCoordinates;
	}
	public String getArea_size() {
		return area_size;
	}
	public void setArea_size(String areaSize) {
		area_size = areaSize;
	}
	public String getLati() {
		return lati;
	}
	public void setLati(String lati) {
		this.lati = lati;
	}
	public String getLongi() {
		return longi;
	}
	public void setLongi(String longi) {
		this.longi = longi;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String exporthistory(){
		try {
			list=c.findhistroy(vehi_no,stime,etime);
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

				WritableSheet ws = wwb.createSheet(list.get(0).getVehi_no()+"历史轨迹",0);//创建Excel工作表 指定名称和位置 
				Label label ;
				label = new Label(0,0,"序号"); 
            	ws.addCell(label);
            	label = new Label(1,0,"上报时间"); 
            	ws.addCell(label);
            	label = new Label(2,0,"车辆状态"); 
            	ws.addCell(label);
            	label = new Label(3,0,"经度"); 
            	ws.addCell(label);
            	label = new Label(4,0,"纬度"); 
            	ws.addCell(label);
            	label = new Label(5,0,"方向"); 
            	ws.addCell(label);
            	label = new Label(6,0,"GPS速度(km/h)"); 
            	ws.addCell(label);
            	label = new Label(7,0,"里程(km)"); 
            	ws.addCell(label);
            	label = new Label(8,0,"位置描述"); 
            	ws.addCell(label);
            	double lc=0;
                	for (int ii1 = 0; ii1 < list.size(); ii1++) {
                		label = new Label(0,ii1+1,ii1+1+""); 
                    	ws.addCell(label);
                    	label = new Label(1,ii1+1,list.get(ii1).getStime()); 
                    	ws.addCell(label);
                    	label = new Label(2,ii1+1,list.get(ii1).getStatus()); 
                    	ws.addCell(label);
                    	label = new Label(3,ii1+1,list.get(ii1).getPx()); 
                    	ws.addCell(label);
                    	label = new Label(4,ii1+1,list.get(ii1).getPy()); 
                    	ws.addCell(label);
                    	label = new Label(5,ii1+1,dlwz(list.get(ii1).getDistance())); 
                    	ws.addCell(label);
                    	label = new Label(6,ii1+1,list.get(ii1).getSpeed()); 
                    	ws.addCell(label);
                    	if(ii1==0){
                    		label = new Label(7,ii1+1,0+""); 
                    	}else{	
                    		lc +=GetDistance(list.get(ii1).getPx(),list.get(ii1).getPy(),list.get(ii1-1).getPx(),list.get(ii1-1).getPy());
                    		BigDecimal bg = new BigDecimal(lc);
                    		double lc1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    		label = new Label(7,ii1+1,lc1+""); 
                    	}
                    	ws.addCell(label);
                    	label = new Label(8,ii1+1,list.get(ii1).getAddress()); 
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
	public String dlwz(int obj){
		if(obj==0||obj==360){
			return "正北";
		}else if(obj==90){
			return "正东";
		}else if(obj==180){
			return "正南";
		}else if(obj==270){
			return "正西";
		}else if(obj>0&&obj<90){
			return "东北";
		}else if(obj>90&&obj<180){
			return "东南";
		}else if(obj>180&&obj<270){
			return "西南";
		}else if(obj>270&&obj<360){
			return "西北";
		}
		return null;
		
	}
	private static double rad(String lat1){
		return (Double.parseDouble(lat1))* Math.PI / 180.00;
	}
	public static double GetDistance(String latitude1, String longitude1, String latitude2, String longitude2){	
		 double Lat1 = rad(latitude1); // 纬度
		 double Lat2 = rad(latitude2); 
		 double a = Lat1 - Lat2;//两点纬度之差
		 double b = rad(longitude1) - rad(longitude2); //经度之差
		 double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(b / 2), 2)));//计算两点距离的公式
		 s = s * 6378137.0;//弧长乘地球半径（半径为米）
		 s = Math.round(s * 10000d) / 10000d/1000;//精确距离的数值
		 return s;

	}

}
