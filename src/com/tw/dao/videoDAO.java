package com.tw.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tw.entity.Company;
import com.tw.entity.User;
import com.tw.entity.Vehicle;
import com.tw.util.DataBese;

public class videoDAO {
	//查询有通用视频的公司
	public List<Company>findmdtvideo(String username){
		List<Company>list=new ArrayList<Company>();
		String sql="select distinct ba_name,ba_id from HZGPS_TAXI.VW_VEHICLE@TAXILINK t where (mdt_sub_type ='通用移动4G' or mdt_sub_type ='通用联通3G')";
		if (username!=null&&username.equals("shiws")) {
			sql+=" and (ba_name = '杭州外事旅游汽车集团有限公司'  or ba_name = '浙江中侨汽车出租有限公司'  or ba_name ='杭州蓝联出租汽车服务管理有限公司')";
		}else {
			sql+=" and (ba_name like '%杭州大众出租汽车股份有限公司%'  or ba_name like '%杭州客旅汽车出租公司%' or ba_name like '%杭州之江汽车出租有限公司%' or ba_name like '%杭州中润客运有限公司%'  or ba_name like '%杭州大川旅游汽车出租有限公司%'  or ba_name like '%杭州华旅客运有限公司%'  or ba_name like '%杭州安润客运有限公司%' or ba_name like '%杭州和谐出租汽车服务管理有限公司%')";
		}
//		System.out.println(sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Company c=new Company();
				c.setName(rs.getString("ba_name"));
				c.setBaid(rs.getString("ba_id"));
				list.add(c);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//查询通用视频的车辆
	public List<Vehicle>findvhicvideo(String username){
		List<Vehicle>list=new ArrayList<Vehicle>();
		String sql="select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v,hz_taxi.TB_TAXI_STATUS_new t where" +
				"  t.mdt_no=v.sim_num and (mdt_sub_type ='通用移动4G' or mdt_sub_type ='通用联通3G') ";
		if (username!=null&&username.equals("shiws")) {
			sql+=" and (ba_name = '杭州外事旅游汽车集团有限公司'  or ba_name = '浙江中侨汽车出租有限公司'  or ba_name ='杭州蓝联出租汽车服务管理有限公司')";
		}else {
			sql+=" and (ba_name like '%杭州大众出租汽车股份有限公司%'  or ba_name like '%杭州客旅汽车出租公司%' or ba_name like '%杭州之江汽车出租有限公司%' or ba_name like '%杭州中润客运有限公司%'  or ba_name like '%杭州大川旅游汽车出租有限公司%'  or ba_name like '%杭州华旅客运有限公司%'  or ba_name like '%杭州安润客运有限公司%' or ba_name like '%杭州和谐出租汽车服务管理有限公司%')";
		}
		sql+=" order by vehi_no";
//		System.out.println(sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vehicle v=new Vehicle();
				v.setBa_id(rs.getString("ba_id"));
				v.setVehi_id(rs.getString("vehi_id"));
				v.setVehi_no(rs.getString("vehi_no"));
				if (rs.getString("stime")!=null) {
					long time=rs.getTimestamp("stime").getTime();
					Date date=new Date();
					long time1=date.getTime();
					if (time1-time<=1800000) {
						v.setStatus("0");
					}
				}else {
					v.setStatus("1");
				}
				list.add(v);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//查找车辆-终端
	public JSONArray findcamera(String keyword,String compid) {
		String sql = "select * from HZGPS_TAXI.VW_VEHI_MDT@taxilink v where v.vehi_no like '%"+keyword+"%'";
		String [] ids = compid.split(",");
		String comps = " and (";
		for(int i=0;i<ids.length;i++){
			if(i==ids.length-1){
				comps +=" v.comp_name='"+ids[i]+"') order by v.vehi_no";
			}else{
				comps +="v.comp_name='"+ids[i]+"'or ";
			}
		}
		sql+=comps;
//		System.out.println(sql);
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		Connection con=DataBese.getConnectionOfOracle();
		JSONArray jsonArray = new JSONArray();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int count=0;
			while(rs.next()){
				count++;
				JSONObject one = new JSONObject(); 
				one.put("id", count+""); 
				one.put("open", "close");
				one.put("name", rs.getString("VEHI_NO"));
				one.put("pId", "hksp");
				one.put("lx", "hksp");
				one.put("camera", rs.getString("MDT_NO"));
				if(rs.getString("DB_TIME")!=null){
					Date otime = sdf.parse(rs.getString("DB_TIME"));
					Date ntime = new Date();
					long diff = ntime.getTime() - otime.getTime();
					long mins = diff / (1000 * 60);
					if(mins>10){
						one.put("icon", "../img/video/off.png");
					}else{
						one.put("icon", "../img/video/on.png");
						JSONObject td1 = new JSONObject(); 
						td1.put("id", "td1");
						td1.put("pId", count+"");
						td1.put("name", "通道1");
						td1.put("icon", "../img/video/mdt.png");
						td1.put("camera", rs.getString("MDT_NO"));
						td1.put("lx", "hksp");
						td1.put("td", "1");
						td1.put("vehinum",rs.getString("VEHI_NO"));
						JSONObject td2 = new JSONObject(); 
						td2.put("id", "td2");
						td2.put("pId", count+"");
						td2.put("name", "通道2");
						td2.put("icon", "../img/video/mdt.png");
						td2.put("camera", rs.getString("MDT_NO"));
						td2.put("lx", "hksp");
						td2.put("td", "2");
						td2.put("vehinum",rs.getString("VEHI_NO"));
						JSONObject td3 = new JSONObject(); 
						td3.put("id", "td3");
						td3.put("pId", count+"");
						td3.put("name", "通道3");
						td3.put("icon", "../img/video/mdt.png");
						td3.put("camera", rs.getString("MDT_NO"));
						td3.put("lx", "hksp");
						td3.put("td", "3");
						td3.put("vehinum",rs.getString("VEHI_NO"));
						JSONObject td4 = new JSONObject(); 
						td4.put("id", "td4");
						td4.put("pId", count+"");
						td4.put("name", "通道4");
						td4.put("icon", "../img/video/mdt.png");
						td4.put("camera", rs.getString("MDT_NO"));
						td4.put("lx", "hksp");
						td4.put("td", "4");
						td4.put("vehinum",rs.getString("VEHI_NO"));
						jsonArray.add(td1);
						jsonArray.add(td2);
						jsonArray.add(td3);
						jsonArray.add(td4);
					}
				}else{
					one.put("icon", "../img/video/off.png");
				}
				jsonArray.add(one);
			}
		} catch (Exception e) {
			e.printStackTrace();
	}finally {
		try {
			if (pstmt != null)
			pstmt.close();
			if (rs != null)
				rs.close();
			if (con != null)
				con.commit();
				con.close(); 
			} catch (Exception fe)
			{
			System.out.println("[Exception] - " + fe.toString());
			}
	}
		return jsonArray;
	}
//	public List<Vehicle> findcamera(String keyword,String compid) {
//		List<Vehicle> results = new ArrayList<Vehicle>();
//		String sql = "select * from HZGPS_TAXI.VW_VEHI_MDT@taxilink v where v.vehi_no like '%"+keyword+"%'";
//		String [] ids = compid.split(",");
//		String comps = " and (";
//		for(int i=0;i<ids.length;i++){
//			if(i==ids.length-1){
//				comps +=" v.comp_name='"+ids[i]+"') order by v.vehi_no";
//			}else{
//				comps +="v.comp_name='"+ids[i]+"'or ";
//			}
//		}
//		sql+=comps;
////		System.out.println(sql);
//		PreparedStatement pstmt =null;
//		ResultSet rs =null;
//		Connection con=DataBese.getConnectionOfOracle();
//		try {
//			pstmt = con.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			int count=0;
//			JSONArray jsonArray = new JSONArray();
//			while(rs.next()){
//				JSONObject one = new JSONObject(); 
//				one.put("id", count+""); 
//				one.put("open", "close");
//				one.put("name", rs.getString("VEHI_NO"));
//				one.put("pId", "hksp");
//				
//				count++;
//				Vehicle vehi = new Vehicle();
//				vehi.setId(count+"");
////				vehi.setTcnum(rs.getString("TC_NUM"));
////				vehi.setTcid(rs.getString("TC_ID"));
//				vehi.setComp_id(rs.getString("COMP_ID"));
//				vehi.setComp_name(rs.getString("COMP_NAME"));
//				vehi.setBa_id(rs.getString("BA_ID"));
//				vehi.setBa_name(rs.getString("BA_NAME"));
//				vehi.setVehi_no(rs.getString("VEHI_NO"));
//				vehi.setMdtno(rs.getString("MDT_NO"));
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				if(rs.getString("DB_TIME")!=null){
//					vehi.setStime(rs.getString("DB_TIME").substring(0, 19));
//					Date otime = sdf.parse(rs.getString("DB_TIME"));
//					Date ntime = new Date();
//					long diff = ntime.getTime() - otime.getTime();
//					long mins = diff / (1000 * 60);
//					if(mins>10){
//						one.put("icon", "../img/video/off.png");
//					}else{
//						one.put("icon", "../img/video/on.png");
//						vehi.setOnoffstate("1");
//					}
//				}else{
//					one.put("icon", "../img/video/off.png");
//					vehi.setOnoffstate("0");
//				}
//				if(rs.getString("SPEED")!=null){
//					
//					vehi.setSpeed((new  BigDecimal(rs.getString("SPEED")).setScale(2,BigDecimal.ROUND_HALF_UP))+"");
//				}else{
//					vehi.setSpeed("");
//				}
//				
//				results.add(vehi);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//	}finally {
//		try {
//			if (pstmt != null)
//			pstmt.close();
//			if (rs != null)
//				rs.close();
//			if (con != null)
//				con.commit();
//				con.close(); 
//			} catch (Exception fe)
//			{
//			System.out.println("[Exception] - " + fe.toString());
//			}
//	}
//		return results;
//	}
	public List<Company> findcomp(String compid) {
		String sql ="select * from HZGPS_TAXI.TB_COMPANY@taxilink t where 1=1 ";
		String [] ids = compid.split(",");
		String comps = " and (";
		for(int i=0;i<ids.length;i++){
			if(i==ids.length-1){
				comps +=" t.comp_name='"+ids[i]+"')";
			}else{
				comps +="t.comp_name='"+ids[i]+"'or ";
			}
		}
		sql = sql+comps;
		List<Company> cs = new ArrayList<Company>();
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		Connection con=DataBese.getConnectionOfOracle();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Company c = new Company();
				c.setId(rs.getString("comp_id"));
				c.setName(rs.getString("comp_name"));
				cs.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null)
				pstmt.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.commit();
					con.close(); 
				} catch (Exception fe)
				{
				System.out.println("[Exception] - " + fe.toString());
				}
		}
		return cs;
	}
	public User getUser(String yh) {
		User u=new User();
		String sql="select * from tb_user u,tb_taxi_station s where u.station_id = s.id and "
				+ " u.id='"+yh+"'";
		PreparedStatement ps;
//		System.out.println(sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			ps = con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				u.setId(rs.getString("id"));
				u.setUsername(rs.getString("user_name")==null?"&nbsp;":rs.getString("user_name"));
				u.setPassword(rs.getString("password")==null?"&nbsp;":rs.getString("password"));
				u.setReal_name(rs.getString("real_name"));
				u.setDate_view_type(rs.getString("date_view_type"));
				u.setParent(rs.getString("parent"));
				u.setYmqx(rs.getString("station_juri"));
				u.setGlqx(rs.getString("station_admin"));
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
}
