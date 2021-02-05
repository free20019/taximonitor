package com.tw.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;











import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tw.entity.Groups;
import com.tw.entity.Vehicle;
import com.tw.entity.area1;
import com.tw.util.DataBese;

public class CarDao {
	public static void main(String[] args) {
		System.out.println(getplace("116.397428", "39.90923"));
	}
	public static List<Vehicle> aa(){
		String sql = "select * from hz_taxi.aa";
		List<Vehicle>list=new ArrayList<Vehicle>();
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vehicle v=new Vehicle();
				v.setAddress(rs.getString("aa"));
				v.setId(rs.getString("id"));
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
	//经纬度转换为地址
	public static String getplace(String lati, String longti){
		//String path="http://gc.ditu.aliyun.com/regeocoding?l="+lati+","+longti+"&type=100";  
		String path="http://192.168.0.105:9090/taximonitor/addresss.action?lati="+lati+"&longi="+longti; 
		String place="";
        //参数直接加载url后面
		try {
			URL url= new URL(path);
		
        HttpURLConnection conn=(HttpURLConnection) url.openConnection();  
        conn.setRequestMethod("GET");  
        conn.setConnectTimeout(5000);  
        StringBuffer buffer = new StringBuffer();  
        if(conn.getResponseCode()==200){                //200表示请求成功  
            InputStream is=conn.getInputStream();    //以输入流的形式返回  
            InputStreamReader inputStreamReader = new InputStreamReader(is, "utf-8");  
            //将输入流转换成字符串  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
      	  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            is.close();  
            is = null;  
            conn.disconnect();  
//            JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
            place = buffer.toString();
        }  
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return place;
	}
	
	//查询车辆在地图上显示
	public List<Vehicle>findvhic(String vehi_no,String vehi_nolist,String vhic,String name){
		List<Vehicle>list=new ArrayList<Vehicle>();
		String sql="";
		String [] ids = name.split(",");
		String comps = "";
		for (int i = 0; i < ids.length; i++) {
			comps += "'"+ids[i]+"',";
		}
		comps = comps.substring(0, comps.length()-1);
		sql="select * from(select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v where  comp_name in("+comps+")";
		if (vehi_no!=null&&vehi_no.length()>0) {
			sql+=" and v.vehi_no = '"+vehi_no+"'";
		}
		String vhiclist="";
		if (vehi_nolist!=null&&vehi_nolist.length()>0) {
			String[] lists=vehi_nolist.split(",");
			vhiclist+=" and (";
			for (int i = 0; i < lists.length; i++) {
				if (i==lists.length-1) {
					vhiclist+=" v.vehi_no='"+lists[i]+"')";
				}else {
					vhiclist+=" v.vehi_no='"+lists[i]+"' or ";
				}
			}
		}
		sql+=vhiclist;
		String vhic1="";
		if (vhic!=null&&vhic.length()>0) {
			String[] lists=vhic.split(";");
			vhic1+=" and (";
			for (int i = 0; i < lists.length; i++) {
				if (i==lists.length-1) {
					vhic1+=" v.vehi_no='"+lists[i]+"')";
				}else {
					vhic1+=" v.vehi_no='"+lists[i]+"' or ";
				}
			}
		}
		sql+=vhic1;
		sql+=") v left join hzgps_taxi.TB_MDT_STATUS@taxilink69 t on t.mdt_no = v.mdt_no ";
		System.out.println("bj="+sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vehicle v=new Vehicle();
				v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp":rs.getString("vehi_no"));
				v.setComp_name(rs.getString("comp_name")==null?"&nbsp":rs.getString("comp_name"));
				v.setColor(rs.getString("vc_name")==null?"&nbsp":rs.getString("vc_name"));
				v.setPx(rs.getString("px")==null?"0":rs.getString("px"));
				v.setPy(rs.getString("py")==null?"0":rs.getString("py"));
				v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
				v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
				v.setSim_num(rs.getString("vehi_sim")==null?"&nbsp":rs.getString("vehi_sim"));
				if (vehi_nolist!=null&&vehi_nolist.length()>0) {						
					v.setBa_id(rs.getString("alarmstatus")==null?"&nbsp":rs.getString("alarmstatus"));
//					if (rs.getString("alt_time")!=null) {
//						v.setDateTime(rs.getString("alt_time").substring(0, 19));
//					}
				}
				if (rs.getString("stime")!=null) {
					v.setStime(rs.getString("stime").substring(0, 19));
					long time=rs.getTimestamp("stime").getTime();
					Date date=new Date();
					long time1=date.getTime();
					if (time1-time<=1800000) {
						v.setStatus("0");
					}
				}else {
					v.setStatus("1");
				}
				if (rs.getDouble("speed")>0) {
					v.setGspstatus("1");
				}else {
					v.setGspstatus("0");
				}
				v.setCartype(rs.getString("vt_name")==null?"&nbsp":rs.getString("vt_name"));
				v.setAddress(getplace(rs.getString("px"), rs.getString("py")));
				v.setDistance(0);
				v.setSpeed(rs.getString("speed")==null?"&nbsp":rs.getString("speed"));
				v.setStime(rs.getString("stime")==null?"&nbsp":rs.getString("stime").substring(0, 19));
				v.setMt_name(rs.getString("mt_name")==null?"&nbsp":rs.getString("mt_name"));
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
	//查询车辆不输出地址，增加查询速度
	public List<Vehicle>findvhic1(String vehi_no,String vehi_nolist,String vhic,String name){
		List<Vehicle>list=new ArrayList<Vehicle>();
		String [] ids = name.split(",");
		String comps = "";
		for (int i = 0; i < ids.length; i++) {
			comps += "'"+ids[i]+"',";
		}
		comps = comps.substring(0, comps.length()-1);
		String sql="";
			sql="select * from(select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v where 1=1 ";
			if (vehi_no!=null&&vehi_no.length()>0) {
				sql+=" and (v.vehi_no like '%"+vehi_no+"%' or v.MDT_SUB_TYPE like '%"+vehi_no+"%' "
						+ " or v.MDT_NO like '%"+vehi_no+"%' or v.SIM_NUM like '%"+vehi_no+"%')";
			}
			String vhiclist="";
			if (vehi_nolist!=null&&vehi_nolist.length()>0) {
				String[] lists=vehi_nolist.split(",");
				vhiclist+=" and (";
				for (int i = 0; i < lists.length; i++) {
					if (i==lists.length-1) {
						vhiclist+=" v.vehi_no='"+lists[i]+"')";
					}else {
						vhiclist+=" v.vehi_no='"+lists[i]+"' or ";
					}
				}
			}
			sql+=vhiclist;
			sql+=" and comp_name in ("+comps+")";
			String vhic1="";
			if (vhic!=null&&vhic.length()>0) {
				String[] lists=vhic.split(";");
				vhic1+=" and (";
				for (int i = 0; i < lists.length; i++) {
					if (i==lists.length-1) {
						vhic1+=" v.vehi_no='"+lists[i]+"')";
					}else {
						vhic1+=" v.vehi_no='"+lists[i]+"' or ";
					}
				}
			}
			sql+=vhic1;
			sql+=") v left join hzgps_taxi.TB_MDT_STATUS@taxilink69 t on t.mdt_no = v.mdt_no  order by vehi_no";
			System.out.println(sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				/**
				 * 当前时间-stime<30分钟上线车辆
				 * speed>0为运动状态车辆
				 * speed=0为不运动状态
				 * 在下面设置为status=0为上线=1为下线
				 * 再上线的基础上gpsstatus=1为运动状态
				 * =0为静止状态
				 * */
				Vehicle v=new Vehicle();
				v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp":rs.getString("vehi_no"));
				v.setComp_name(rs.getString("comp_name")==null?"&nbsp":rs.getString("comp_name"));
				v.setColor(rs.getString("vc_name")==null?"&nbsp":rs.getString("vc_name"));
				v.setPx(rs.getString("px")==null?"0":rs.getString("px"));
				v.setPy(rs.getString("py")==null?"0":rs.getString("py"));
				v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
				v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
				v.setSim_num(rs.getString("vehi_sim")==null?"&nbsp":rs.getString("vehi_sim"));
				v.setCartype(rs.getString("vt_name")==null?"&nbsp":rs.getString("vt_name"));
//				v.setAddress(getplace(rs.getString("px"), rs.getString("py")));
				v.setDistance(0);
				v.setSpeed(rs.getString("speed")==null?"&nbsp":rs.getString("vehi_no"));
				if (rs.getString("stime")!=null) {
					v.setStime(rs.getString("stime").substring(0, 19));
					long time=rs.getTimestamp("stime").getTime();
					Date date=new Date();
					long time1=date.getTime();
					if (time1-time<=1800000) {
						v.setStatus("0");
					}
				}else {
					v.setStatus("1");
				}
				if (rs.getDouble("speed")>0) {
					v.setGspstatus("1");
				}else {
					v.setGspstatus("0");
				}
				v.setMt_name(rs.getString("mt_name"));
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
//查询时间段内车辆记录，用在地图显示路径
	public List<Vehicle>findhistroy(String vhic,String stime,String etime){
		String time=stime.substring(2, 4)+stime.substring(5, 7);
		List<Vehicle>list=new ArrayList<Vehicle>();
		String sql="select * from HZGPS_TAXI.TB_GPS_"+time+"@taxilink69 t where vehicle_num='"+vhic
		+"' and speed_time >=to_date('"+stime+"','yyyy-mm-dd hh24:mi:ss') and " +
				" speed_time <to_date('"+etime+"','yyyy-mm-dd hh24:mi:ss') order by speed_time ";
		System.out.println(sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vehicle v=new Vehicle();
				v.setVehi_no(rs.getString("vehicle_num"));
				v.setStime(rs.getString("speed_time").substring(0, 19));
				if (rs.getString("state").equals("1")) {
					v.setStatus("重车");
				}else {
					v.setStatus("空车");
				}
				v.setSpeed(rs.getString("speed"));
				v.setDistance(rs.getInt("direction"));
				v.setPx(rs.getString("px"));
				v.setPy(rs.getString("py"));
				v.setAddress(getplace(rs.getString("px"), rs.getString("py")));
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
	
	//查询车辆组
	public List<Groups> findcargroup(String userid){
		List<Groups>list=new ArrayList<Groups>();
		String sql="select * from TB_TAXI_VEHICLE_GROUPNAME t where user_id='"+userid+"'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Groups g=new Groups();
				g.setGroupname(rs.getString("group_name"));
				g.setId(rs.getString("group_id"));
				list.add(g);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//根据id查询车辆组
	public Groups findcargroupid(String id){
		Groups g=new Groups();
		String sql="select * from TB_TAXI_VEHICLE_GROUPNAME where group_id='"+id+"'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				g.setGroupname(rs.getString("group_name"));
				g.setId(rs.getString("group_id"));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return g;
	}
	//增加车辆组
	public int addcargroup(String id,String name,String userid){
		int count=0;
		String sql="insert into TB_TAXI_VEHICLE_GROUPNAME (group_id,group_name,user_id)" +
				"values ('"+id+"','"+name+"','"+userid+"')";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				count=0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		return count;
	}
	//修改车辆组
	public int editcargroup(String name,String id){
		int count=0;
		String sql="update TB_TAXI_VEHICLE_GROUPNAME set group_name='"+name+"' where group_id='"+id+"'";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				count=0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//删除车辆组
	public int delcargroup(String id){
		int count=0;
		String sql="delete from TB_TAXI_VEHICLE_GROUPNAME where group_id='"+id+"'";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				count=0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//查询车辆组下车辆
	public List<Vehicle> findcargroupvhic(String id){
		List<Vehicle>list=new ArrayList<Vehicle>();
		String sql="select * from (select  distinct(group_vhic),group_id from TB_TAXI_VEHICLE_GROUP" +
				" where group_id='"+id+"') t ,HZGPS_TAXI.VW_VEHICLE@TAXILINK" +
				" v where t.group_vhic=v.vehi_no";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vehicle v=new Vehicle();
				v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp;":rs.getString("vehi_no"));
				v.setColor(rs.getString("vc_name")==null?"&nbsp;":rs.getString("vc_name"));
				v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
				v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
				v.setSim_num(rs.getString("vehi_sim")==null?"&nbsp;":rs.getString("vehi_sim"));
				v.setCartype(rs.getString("vt_name")==null?"&nbsp;":rs.getString("vt_name"));
				v.setMt_name(rs.getString("mt_name")==null?"&nbsp;":rs.getString("mt_name"));
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
	//根据id查询车辆组
	public List<Groups> findcargroupidvhic(String id){
		List<Groups>list=new ArrayList<Groups>();
		
		String sql="select * from TB_TAXI_VEHICLE_GROUP where group_id='"+id+"'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Groups g=new Groups();
				g.setGroupvhic(rs.getString("group_vhic"));
				g.setId(rs.getString("group_id"));
				list.add(g);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//增加车辆组
	public int addcargroupvhic(String id,String vhic){
		int count=0;
		String sql="insert into TB_TAXI_VEHICLE_GROUP (group_id,group_vhic)" +
				"values ('"+id+"','"+vhic+"')";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				count=0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		return count;
	}
	//修改车辆组
	public int editcargroupvhic(String vhic,String id,String groupid){
		int count=0;
		String sql="update TB_TAXI_VEHICLE_GROUP set group_vhic='"+vhic+"' where group_id='"+groupid+"' and id='"+id+"'";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				count=0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//删除车辆组
	public int delcargroupvhic(String id){
		int count=0;
		String sql="delete from TB_TAXI_VEHICLE_GROUP where group_id='"+id+"'";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				count=0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//查询车辆组及组内车辆
	public List<Groups>findgroupnum(String id){
		List<Groups>list=new ArrayList<Groups>();
		String sql="select g.group_name,g.group_id,(select count(*) from" +
				" TB_TAXI_VEHICLE_GROUP v where v.group_id=g.group_id) n" +
				" from TB_TAXI_VEHICLE_GROUPNAME g where g.user_id='"+id+"'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Groups g=new Groups();
				g.setGroupname(rs.getString("group_name"));
				g.setGroupvhic(rs.getString("n"));
				g.setId(rs.getString("group_id"));
				list.add(g);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//查询车辆组下车辆的详细信息
	public List<Vehicle>findgroupinfo(String id){
		List<Vehicle>list=new ArrayList<Vehicle>();
		String sql="select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v" +
				",hz_taxi.TB_TAXI_STATUS_new t,(select * from TB_TAXI_VEHICLE_GROUP" +
				" where group_id='"+id+"') g where  t.mdt_no=v.sim_num and" +
				" v.vehi_no=g.group_vhic";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vehicle v=new Vehicle();
				v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp":rs.getString("vehi_no"));
				v.setComp_name(rs.getString("comp_name")==null?"&nbsp":rs.getString("comp_name"));
				v.setColor(rs.getString("vc_name")==null?"&nbsp":rs.getString("vc_name"));
				v.setPx(rs.getString("px")==null?"0":rs.getString("px"));
				v.setPy(rs.getString("py")==null?"0":rs.getString("py"));
				v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
				v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
				v.setSim_num(rs.getString("vehi_sim")==null?"&nbsp":rs.getString("vehi_sim"));
				v.setCartype(rs.getString("vt_name")==null?"&nbsp":rs.getString("vt_name"));
				v.setAddress(getplace(rs.getString("px"), rs.getString("py")));
				v.setDistance(0);
				v.setSpeed(rs.getString("speed")==null?"&nbsp":rs.getString("speed"));
				if (rs.getString("stime")!=null) {
					v.setStime(rs.getString("stime").substring(0, 19));
					long time=rs.getTimestamp("stime").getTime();
					Date date=new Date();
					long time1=date.getTime();
					if (time1-time<=1800000) {
						v.setStatus("0");
					}
				}else {
					v.setStatus("1");
				}
				if (rs.getDouble("speed")>0) {
					v.setGspstatus("1");
				}else {
					v.setGspstatus("0");
				}
				v.setMt_name(rs.getString("mt_name"));
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
	//查询车辆在线下线载客空驶总数
	public List<Integer> findnum(String name){
		List<Integer>list=new ArrayList<Integer>();
		String [] ids = name.split(",");
		String sql="";
		String comps = "";
		for (int i = 0; i < ids.length; i++) {
			comps +="'"+ids[i]+"',";
		}
		comps = comps.substring(0, comps.length()-1);
		sql="select * from(select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v where comp_name in ("+comps+") ) v left join hzgps_taxi.TB_MDT_STATUS@taxilink69 t on t.mdt_no = v.mdt_no ";
		System.out.println("查询车辆在线下线载客空驶总数"+sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			int zx=0,bzx=0,zk=0,ks=0,sy=0;
			while(rs.next()){
				sy++;
				if (rs.getString("stime")!=null) {
					long time=rs.getTimestamp("stime").getTime();
					Date date=new Date();
					long time1=date.getTime();
					if (time1-time<=1800000) {
						zx++;
						if(rs.getString("state").equals("0")){
							zk++;
						}else {
							ks++;
						}
					}else {
						bzx++;
					}
				}else {
					bzx++;
				}
			}
			list.add(sy);
			list.add(zx);
			list.add(bzx);
			list.add(zk);
			list.add(ks);
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//查询区域
	public List<area1>findarea(String id,String userid){
		List<area1>list=new ArrayList<area1>();
		String sql="select * from TB_UPDOWNAREA where user_id='"+userid+"' ";
		if (id!=null&&id.length()>0) {
			sql+=" and id='"+id+"'";
		}
//		System.out.println(sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				area1 a=new area1();
				a.setId(rs.getString("id")==null?"&nbsp;":rs.getString("id"));
				a.setAlarmnum(rs.getString("alarmnum")==null?"&nbsp;":rs.getString("alarmnum"));
				a.setArea_coordinates(rs.getString("area_coordinates")==null?"&nbsp;":rs.getString("area_coordinates"));
				a.setArae_describe(rs.getString("area_describe")==null?"&nbsp;":rs.getString("area_describe"));
				a.setArea_name(rs.getString("area_name")==null?"&nbsp;":rs.getString("area_name"));
				a.setArea_size(rs.getString("area_size")==null?"&nbsp;":rs.getString("area_size"));
				a.setOrderid(rs.getString("orderid")==null?"&nbsp;":rs.getString("orderid"));
				a.setUser_id(rs.getString("user_id")==null?"&nbsp;":rs.getString("user_id"));
				list.add(a);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//添加区域
	public int addarea(String area_name,String area_describe,String area_coordinates,String area_size,String id){
		int count=0;
		String sql="insert into TB_UPDOWNAREA(area_name,area_describe,area_coordinates,area_size,user_id)" +
				"values ('"+area_name+"','"+area_describe+"','"+area_coordinates+"','"+area_size+"','"+id+"')";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				count=0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//修改区域
	public int editarea(String id,String area_name,String area_describe){
		int count=0;
		String sql="update TB_UPDOWNAREA set area_name='"+area_name+"'," +
				"area_describe='"+area_describe+"' where id='"+id+"'";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				count=0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//删除区域
	public int delarea(String id){
		int count=0;
		String sql="delete from TB_UPDOWNAREA where id='"+id+"'";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				count=0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//查询电子围栏
	public List<area1>findfence(String id,String userid){
		List<area1>list=new ArrayList<area1>();
		String sql="select t.id,area_name,area_describe,area_pxpy,area_size," +
				"t.group_id,group_name,(select count(*) from TB_TAXI_VEHICLE_GROUP" +
				" where group_id=t.group_id) num  from TB_FENCE_AREA t," +
				"tb_taxi_vehicle_groupname g where t.group_id=g.group_id  and t.user_id='"+userid+"' ";
		if (id!=null&&id.length()>0) {
			sql+=" and t.id='"+id+"'";
		}
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				area1 a=new area1();
				a.setId(rs.getString("id")==null?"&nbsp;":rs.getString("id"));
				a.setUser_id(rs.getString("group_name"));
				a.setOrderid(rs.getString("group_id"));
				a.setAlarmnum(rs.getString("num"));
				a.setArea_coordinates(rs.getString("area_pxpy")==null?"&nbsp;":rs.getString("area_pxpy"));
				a.setArae_describe(rs.getString("area_describe")==null?"&nbsp;":rs.getString("area_describe"));
				a.setArea_name(rs.getString("area_name")==null?"&nbsp;":rs.getString("area_name"));
				a.setArea_size(rs.getString("area_size")==null?"&nbsp;":rs.getString("area_size"));
				list.add(a);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//添加区域
	public int addfence(String area_name,String area_describe,String area_coordinates,String area_size,String group_id,String id){
		int count=0;
		String sql="insert into TB_FENCE_AREA(area_name,area_describe,area_pxpy,area_size,group_id,user_id)" +
				"values ('"+area_name+"','"+area_describe+"','"+area_coordinates+"','"+area_size+"','"+group_id+"','"+id+"')";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				count=0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//修改区域
	public int editfence(String id,String area_name,String area_describe,String group_id){
		int count=0;
		String sql="update TB_FENCE_AREA set area_name='"+area_name+"'," +
				"area_describe='"+area_describe+"',group_id='"+group_id+"' where id='"+id+"'";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				count=0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//删除区域
	public int delfence(String id){
		int count=0;
		String sql="delete from TB_FENCE_AREA where id='"+id+"'";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				count=0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//查询电子围栏所管理的车辆信息
	public List<Vehicle>findfencevhicinfo(String id){
		List<Vehicle>list=new ArrayList<Vehicle>();
		String sql="select v.vehi_no,v.vc_name,v.vehi_sim,t.speed,t.stime,t.px,t.py," +
				"v.mt_name,v.vt_name,t.status,t.gps_status from" +
				" (select * from TB_FENCE_AREA t,tb_taxi_vehicle_group g where" +
				" t.group_id=g.group_id  and t.id='"+id+"') a,HZGPS_TAXI.VW_VEHICLE@TAXILINK v" +
				",hz_taxi.TB_TAXI_STATUS_new t where a.group_vhic=v.vehi_no and" +
				" t.mdt_no=v.sim_num";
//		System.out.println(sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vehicle v=new Vehicle();
				v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp":rs.getString("vehi_no"));
//				v.setComp_name(rs.getString("comp_name")==null?"&nbsp":rs.getString("comp_name"));
				v.setColor(rs.getString("vc_name")==null?"&nbsp":rs.getString("vc_name"));
				v.setPx(rs.getString("px")==null?"0":rs.getString("px"));
				v.setPy(rs.getString("py")==null?"0":rs.getString("py"));
//				v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
//				v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
				v.setSim_num(rs.getString("vehi_sim")==null?"&nbsp":rs.getString("vehi_sim"));
				v.setCartype(rs.getString("vt_name")==null?"&nbsp":rs.getString("vt_name"));
				v.setAddress(getplace(rs.getString("px"), rs.getString("py")));
				v.setDistance(0);
				v.setSpeed(rs.getString("speed")==null?"&nbsp":rs.getString("vehi_no"));
				if (rs.getString("stime")!=null) {
					v.setStime(rs.getString("stime").substring(0, 19));
					long time=rs.getTimestamp("stime").getTime();
					Date date=new Date();
					long time1=date.getTime();
					if (time1-time<=1800000) {
						v.setStatus("0");
					}
				}else {
					v.setStatus("1");
				}
				if (rs.getDouble("speed")>0) {
					v.setGspstatus("1");
				}else {
					v.setGspstatus("0");
				}
//				v.setMt_name(rs.getString("mt_name"));
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
	
	//通过终端编号往终端发送短信
	public String client(String mdt_no, String note) {
		int a = (int)(1+Math.random()*(100000-1+1));
		try {
				Socket socket = new Socket("192.168.0.105", 7706);
				System.out.println("连接成功tessmiaa");
				OutputStream out = socket.getOutputStream();
				String nr = mdt_no+"|"+a+"|"+note+"|4";
				System.out.println(nr);
				byte[] bt = nr.getBytes("GB2312");
				out.write(bt);
				socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static int ttt(String stime,String etime){
		int d = 0;
		String sql = "select sum(sc) c from (select jicheng11,xs,sc"
				+ " from( select  TO_NUMBER(decode(decode(lower("
				+ "nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),"
				+ "  upper(nvl(replace(translate(jicheng, '$%', ' '),' ',''),"
				+ "0)),1,0),1,nvl(replace(  translate(jicheng, '$%', ' '),'"
				+ " ',''),0),0))/10  *(1/round((xiache-shangche)*24,2))"
				+ " jicheng11,round((xiache-shangche)*24,2) *"
				+ "(1/round((xiache-shangche)*24,2)) xs,"
				+ "round((xiache-shangche)*24,2) sc from "
				+ "HZGPS_CITIZEN.TB_CITIZEN_2017@TAXILINK45 t where"
				+ " shangche >= to_date('"+stime+"','yyyy-mm-dd')"
				+ "  and shangche < to_date('"+etime+"','yyyy-mm-dd')"
				+ " and round((xiache-shangche)*24,2)>0) where jicheng11<=12)";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				d= rs.getInt("c");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}
	public static String ttt1(){

		//String path="http://gc.ditu.aliyun.com/regeocoding?l="+lati+","+longti+"&type=100";  
		String path="http://localhost:8080/qzsp/verifi/PriseVerification?sundata=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"; 
		String place="";
        //参数直接加载url后面
		try {
			URL url= new URL(path);
		
        HttpURLConnection conn=(HttpURLConnection) url.openConnection();  
        conn.setRequestMethod("GET");  
        conn.setConnectTimeout(5000);  
        StringBuffer buffer = new StringBuffer();  
        if(conn.getResponseCode()==200){                //200表示请求成功  
            InputStream is=conn.getInputStream();    //以输入流的形式返回  
            InputStreamReader inputStreamReader = new InputStreamReader(is, "utf-8");  
            //将输入流转换成字符串  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
      	  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            is.close();  
            is = null;  
            conn.disconnect();  
            place = buffer.toString();  
            System.out.println(place);
        }  
		} catch (Exception e) {
			
		}  
		return place;
	
	}
	public static String test(){

		PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL("http://localhost:8080/qzsp/verifi/PriseVerification");
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print("sundata=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");
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        System.out.println(result);
		return result;
	
	}
	public static String ck(){

		PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL("http://api.boktour.com/tour/Booking");
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print("UserName=yuancheng&&PassWord=666666&&ListId=3542551&&Amount01=1&&Amount02=1&&CustomerName=江豆");
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        System.out.println(result);
		return result;
	
	}
}
