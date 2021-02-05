package com.tw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tw.entity.Vehicle;
import com.tw.entity.Vhic;
import com.tw.entity.Wx;
import com.tw.util.DataBese;
import com.tw.util.VhicComparator;


public class FindDao {
	private Date d=new Date();
	private SimpleDateFormat sdformat =new SimpleDateFormat("yyyy");
	private String time=sdformat.format(d);
	//查询无营运数据车辆
	public List<Vhic>findvehino(String stime,String etime,String name,String areaid,String compid){
		List<Vhic>list=new ArrayList<Vhic>();
		String datt = stime.substring(0, 4);
		String [] ids = name.split(",");
		String sql="";
		String comps = "";
		for (int i = 0; i < ids.length; i++) {
			comps += "'"+ids[i]+"',";
		}
		comps = comps.substring(0, comps.length()-1);
			sql="select vehi_no,comp_name,own_name,own_tel,vehi_sim from" +
			" HZGPS_TAXI.VW_VEHICLE@TAXILINK t where  comp_name in ("+comps+") " ;
				if (!areaid.equals("0")&&areaid.length()>1) {
					sql+="and ba_name ='"+areaid+"'";
					if (!compid.equals("0")) {
						sql+="and comp_name='"+compid+"'";
					}
				}
			sql+=" minus " +
			" select vehi_no,comp_name,own_name,own_tel,vehi_sim from (select * from" +
			" (select distinct (vhic) c from HZGPS_CITIZEN.TB_CITIZEN_"+datt+"@TAXILINK45 t" +
			" where xiache>=to_date('"+stime+"','yyyy-MM-dd hh24:mi:ss') and" +
			" xiache<=to_date('"+etime+"','yyyy-MM-dd hh24:mi:ss')) a," +
			" HZGPS_TAXI.VW_VEHICLE@TAXILINK t where '浙'||a.c=t.vehi_no " ;
			if (!areaid.equals("0")&&areaid.length()>1) {
				sql+="and ba_name ='"+areaid+"'";
				if (!compid.equals("0")) {
					sql+="and comp_name='"+compid+"'";
				}
			}
			sql+=")";
//		System.out.println(areaid);
		System.out.println(sql);
		Connection con;
		PreparedStatement ps;
		ResultSet rs;
		try {
			con=DataBese.getConnectionOfOracle();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setComp_id(rs.getString("comp_name")==null?"&nbsp;":rs.getString("comp_name"));
				v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
				v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
				v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp;":rs.getString("vehi_no"));
				v.setVehi_sim(rs.getString("vehi_sim")==null?"&nbsp;":rs.getString("vehi_sim"));
				list.add(v);
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//监控点车辆明细查询
	public int findmingxi(String quyu,String time,String speed,String data,int j){
		String i=j+"";
		int cs=0;
		String stime=null;
		String etime=null;
		if (i.equals("0")) {
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Date d=new Date();
			String a=dft.format(d);
			try {
			Date b=dft.parse(a);
			Calendar date = Calendar.getInstance();
			date.setTime(b);
			date.set(Calendar.DATE, date.get(Calendar.DATE) -1);
			Date endDate;
			String zuotian=null;
				endDate = dft.parse(dft.format(date.getTime()));
			zuotian=dft.format(endDate);
			stime=zuotian+" 23:58:00";
			etime=time+" 00:02:00";
			}catch (Exception e) {
			}
		}else if (i.equals("1")) {
			stime=time+" 00:28:00";
			etime=time+" 00:32:00";
		}else if (i.equals("2")) {
			stime=time+" 00:58:00";
			etime=time+" 01:02:00";
		}else if (i.equals("3")) {
			stime=time+" 01:28:00";
			etime=time+" 01:32:00";
		}else if (i.equals("4")) {
			stime=time+" 01:58:00";
			etime=time+" 02:02:00";
		}
		else if (i.equals("5")) {
			stime=time+" 02:28:00";
			etime=time+" 02:32:00";
		}else if (i.equals("6")) {
			stime=time+" 02:58:00";
			etime=time+" 03:02:00";
		}else if (i.equals("7")) {
			stime=time+" 03:28:00";
			etime=time+" 03:32:00";
		}else if (i.equals("8")) {
			stime=time+" 03:58:00";
			etime=time+" 04:02:00";
		}else if (i.equals("9")) {
			stime=time+" 04:28:00";
			etime=time+" 04:32:00";
		}else if (i.equals("10")) {
			stime=time+" 04:58:00";
			etime=time+" 05:02:00";
		}else if (i.equals("11")) {
			stime=time+" 05:28:00";
			etime=time+" 05:32:00";
		}else if (i.equals("12")) {
			stime=time+" 05:58:00";
			etime=time+" 06:02:00";
		}else if (i.equals("13")) {
			stime=time+" 06:28:00";
			etime=time+" 06:32:00";
		}else if (i.equals("14")) {
			stime=time+" 06:58:00";
			etime=time+" 07:02:00";
		}else if (i.equals("15")) {
			stime=time+" 07:28:00";
			etime=time+" 07:32:00";
		}else if (i.equals("16")) {
			stime=time+" 07:58:00";
			etime=time+" 08:02:00";
		}else if (i.equals("17")) {
			stime=time+" 08:28:00";
			etime=time+" 08:32:00";
		}else if (i.equals("18")) {
			stime=time+" 08:58:00";
			etime=time+" 09:02:00";
		}else if (i.equals("19")) {
			stime=time+" 09:28:00";
			etime=time+" 09:32:00";
		}else if (i.equals("20")) {
			stime=time+" 09:58:00";
			etime=time+" 10:02:00";
		}else if (i.equals("21")) {
			stime=time+" 10:28:00";
			etime=time+" 10:32:00";
		}else if (i.equals("22")) {
			stime=time+" 10:58:00";
			etime=time+" 11:02:00";
		}else if (i.equals("23")) {
			stime=time+" 11:28:00";
			etime=time+" 11:32:00";
		}else if (i.equals("24")) {
			stime=time+" 11:58:00";
			etime=time+" 12:02:00";
		}else if (i.equals("25")) {
			stime=time+" 12:28:00";
			etime=time+" 12:32:00";
		}else if (i.equals("26")) {
			stime=time+" 12:58:00";
			etime=time+" 13:02:00";
		}else if (i.equals("27")) {
			stime=time+" 13:28:00";
			etime=time+" 13:32:00";
		}else if (i.equals("28")) {
			stime=time+" 13:58:00";
			etime=time+" 14:02:00";
		}else if (i.equals("29")) {
			stime=time+" 14:28:00";
			etime=time+" 14:32:00";
		}else if (i.equals("30")) {
			stime=time+" 14:58:00";
			etime=time+" 15:02:00";
		}else if (i.equals("31")) {
			stime=time+" 15:28:00";
			etime=time+" 15:32:00";
		}else if (i.equals("32")) {
			stime=time+" 15:58:00";
			etime=time+" 16:02:00";
		}else if (i.equals("33")) {
			stime=time+" 16:28:00";
			etime=time+" 16:32:00";
		}else if (i.equals("34")) {
			stime=time+" 16:58:00";
			etime=time+" 17:02:00";
		}else if (i.equals("35")) {
			stime=time+" 17:28:00";
			etime=time+" 17:32:00";
		}else if (i.equals("36")) {
			stime=time+" 17:58:00";
			etime=time+" 18:02:00";
		}else if (i.equals("37")) {
			stime=time+" 18:28:00";
			etime=time+" 18:32:00";
		}else if (i.equals("38")) {
			stime=time+" 18:58:00";
			etime=time+" 19:02:00";
		}else if (i.equals("39")) {
			stime=time+" 19:28:00";
			etime=time+" 19:32:00";
		}else if (i.equals("40")) {
			stime=time+" 19:58:00";
			etime=time+" 20:02:00";
		}else if (i.equals("41")) {
			stime=time+" 20:28:00";
			etime=time+" 20:32:00";
		}else if (i.equals("42")) {
			stime=time+" 20:58:00";
			etime=time+" 21:02:00";
		}else if (i.equals("43")) {
			stime=time+" 21:28:00";
			etime=time+" 21:32:00";
		}else if (i.equals("44")) {
			stime=time+" 21:58:00";
			etime=time+" 22:02:00";
		}else if (i.equals("45")) {
			stime=time+" 22:28:00";
			etime=time+" 22:32:00";
		}else if (i.equals("46")) {
			stime=time+" 22:58:00";
			etime=time+" 23:02:00";
		}else if (i.equals("47")) {
			stime=time+" 23:28:00";
			etime=time+" 23:32:00";
		}
		String sql="select count(*) c from hz_taxi.TB_TAXI_AREA_INFO_RECORD t,HZGPS_TAXI.VW_VEHICLE@TAXILINK v" +
				"  where t.vehi_no=v.vehi_no and record_time>=" +
				"to_date('"+stime+"','yyyy-MM-dd HH24:mi:ss') and" +
				" record_time<=to_date('"+etime+"','yyyy-MM-dd HH24:mi:ss') ";
		if (quyu.equals("所有区域")) {
			sql+=" and area_name !='非监控区域'  ";
		}else {
			sql+=" and  area_name ='"+quyu+"'";
		}
		if (speed!=null&&speed.length()>0) {
			sql+=" and speed >"+speed;
		}
		String [] ids = data.split(",");
		String comps = "";
		if (ids.length==1&&!ids[0].equals("所有公司")) {
			comps +="and ( v.ba_name='"+ids[0]+"')";
		}else if (ids.length==2&&!ids[0].equals("所有公司")&&ids[1].equals("所有分公司")) {
			comps +="and ( v.ba_name='"+ids[0]+"')";
		}else if (ids.length==2&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")) {
			comps +="and ( v.ba_name='"+ids[0]+"' and v.comp_name='"+ids[1]+"')";
		}else if (ids.length==3&&!ids[0].equals("所有公司")&&ids[1].equals("所有分公司")) {
			comps +="and ( v.ba_name='"+ids[0]+"')";
		}else if (ids.length==3&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")&&ids[2].equals("所有车辆")) {
			comps +="and ( v.ba_name='"+ids[0]+"' and v.comp_name='"+ids[1]+"')";
		}else if (ids.length==3&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")&&!ids[2].equals("所有车辆")) {
			comps +="and ( v.ba_name='"+ids[0]+"' and v.comp_name='"+ids[1]+"' and v.vehi_no='"+ids[2]+"')";
		}
		sql+=comps;
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				cs=rs.getInt("c");
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cs;
	}
	//查询车辆具体信息
	public List<Vhic>findspecinfo(String quyu,String time,String speed,String i,String data){
		String stime=null;
		String etime=null;
		if (i.equals("0")) {
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Date d=new Date();
			String a=dft.format(d);
			try {
			Date b=dft.parse(a);
			Calendar date = Calendar.getInstance();
			date.setTime(b);
			date.set(Calendar.DATE, date.get(Calendar.DATE) -1);
			Date endDate;
			String zuotian=null;
				endDate = dft.parse(dft.format(date.getTime()));
			zuotian=dft.format(endDate);
			stime=zuotian+" 23:58:00";
			etime=time+" 00:02:00";
			}catch (Exception e) {
			}
		}else if (i.equals("1")) {
			stime=time+" 00:28:00";
			etime=time+" 00:32:00";
		}else if (i.equals("2")) {
			stime=time+" 00:58:00";
			etime=time+" 01:02:00";
		}else if (i.equals("3")) {
			stime=time+" 01:28:00";
			etime=time+" 01:32:00";
		}else if (i.equals("4")) {
			stime=time+" 01:58:00";
			etime=time+" 02:02:00";
		}
		else if (i.equals("5")) {
			stime=time+" 02:28:00";
			etime=time+" 02:32:00";
		}else if (i.equals("6")) {
			stime=time+" 02:58:00";
			etime=time+" 03:02:00";
		}else if (i.equals("7")) {
			stime=time+" 03:28:00";
			etime=time+" 03:32:00";
		}else if (i.equals("8")) {
			stime=time+" 03:58:00";
			etime=time+" 04:02:00";
		}else if (i.equals("9")) {
			stime=time+" 04:28:00";
			etime=time+" 04:32:00";
		}else if (i.equals("10")) {
			stime=time+" 04:58:00";
			etime=time+" 05:02:00";
		}else if (i.equals("11")) {
			stime=time+" 05:28:00";
			etime=time+" 05:32:00";
		}else if (i.equals("12")) {
			stime=time+" 05:58:00";
			etime=time+" 06:02:00";
		}else if (i.equals("13")) {
			stime=time+" 06:28:00";
			etime=time+" 06:32:00";
		}else if (i.equals("14")) {
			stime=time+" 06:58:00";
			etime=time+" 07:02:00";
		}else if (i.equals("15")) {
			stime=time+" 07:28:00";
			etime=time+" 07:32:00";
		}else if (i.equals("16")) {
			stime=time+" 07:58:00";
			etime=time+" 08:02:00";
		}else if (i.equals("17")) {
			stime=time+" 08:28:00";
			etime=time+" 08:32:00";
		}else if (i.equals("18")) {
			stime=time+" 08:58:00";
			etime=time+" 09:02:00";
		}else if (i.equals("19")) {
			stime=time+" 09:28:00";
			etime=time+" 09:32:00";
		}else if (i.equals("20")) {
			stime=time+" 09:58:00";
			etime=time+" 10:02:00";
		}else if (i.equals("21")) {
			stime=time+" 10:28:00";
			etime=time+" 10:32:00";
		}else if (i.equals("22")) {
			stime=time+" 10:58:00";
			etime=time+" 11:02:00";
		}else if (i.equals("23")) {
			stime=time+" 11:28:00";
			etime=time+" 11:32:00";
		}else if (i.equals("24")) {
			stime=time+" 11:58:00";
			etime=time+" 12:02:00";
		}else if (i.equals("25")) {
			stime=time+" 12:28:00";
			etime=time+" 12:32:00";
		}else if (i.equals("26")) {
			stime=time+" 12:58:00";
			etime=time+" 13:02:00";
		}else if (i.equals("27")) {
			stime=time+" 13:28:00";
			etime=time+" 13:32:00";
		}else if (i.equals("28")) {
			stime=time+" 13:58:00";
			etime=time+" 14:02:00";
		}else if (i.equals("29")) {
			stime=time+" 14:28:00";
			etime=time+" 14:32:00";
		}else if (i.equals("30")) {
			stime=time+" 14:58:00";
			etime=time+" 15:02:00";
		}else if (i.equals("31")) {
			stime=time+" 15:28:00";
			etime=time+" 15:32:00";
		}else if (i.equals("32")) {
			stime=time+" 15:58:00";
			etime=time+" 16:02:00";
		}else if (i.equals("33")) {
			stime=time+" 16:28:00";
			etime=time+" 16:32:00";
		}else if (i.equals("34")) {
			stime=time+" 16:58:00";
			etime=time+" 17:02:00";
		}else if (i.equals("35")) {
			stime=time+" 17:28:00";
			etime=time+" 17:32:00";
		}else if (i.equals("36")) {
			stime=time+" 17:58:00";
			etime=time+" 18:02:00";
		}else if (i.equals("37")) {
			stime=time+" 18:28:00";
			etime=time+" 18:32:00";
		}else if (i.equals("38")) {
			stime=time+" 18:58:00";
			etime=time+" 19:02:00";
		}else if (i.equals("39")) {
			stime=time+" 19:28:00";
			etime=time+" 19:32:00";
		}else if (i.equals("40")) {
			stime=time+" 19:58:00";
			etime=time+" 20:02:00";
		}else if (i.equals("41")) {
			stime=time+" 20:28:00";
			etime=time+" 20:32:00";
		}else if (i.equals("42")) {
			stime=time+" 20:58:00";
			etime=time+" 21:02:00";
		}else if (i.equals("43")) {
			stime=time+" 21:28:00";
			etime=time+" 21:32:00";
		}else if (i.equals("44")) {
			stime=time+" 21:58:00";
			etime=time+" 22:02:00";
		}else if (i.equals("45")) {
			stime=time+" 22:28:00";
			etime=time+" 22:32:00";
		}else if (i.equals("46")) {
			stime=time+" 22:58:00";
			etime=time+" 23:02:00";
		}else if (i.equals("47")) {
			stime=time+" 23:28:00";
			etime=time+" 23:32:00";
		}
		List<Vhic>list=new ArrayList<Vhic>();
		String sql="select * from hz_taxi.TB_TAXI_AREA_INFO_RECORD t, HZGPS_TAXI.VW_VEHICLE@TAXILINK v" +
				" where t.vehi_no=v.vehi_no " +
				" and record_time>=to_date('"+stime+"','yyyy-MM-dd HH24:mi:ss')" +
				" and record_time<=to_date('"+etime+"','yyyy-MM-dd HH24:mi:ss')";
		
		if (quyu.equals("所有区域")) {
			sql+=" and area_name <>'非监控区域'  ";
		}else {
			sql+=" and  area_name ='"+quyu+"'";
		}
		if (speed.length()>0) {
			sql+=" and t.speed>"+speed;
		}
		String [] ids = data.split(",");
		String comps = "";
		if (ids.length==1&&!ids[0].equals("所有公司")) {
			comps +="and ( v.ba_name='"+ids[0]+"')";
		}else if (ids.length==2&&!ids[0].equals("所有公司")&&ids[1].equals("所有分公司")) {
			comps +="and ( v.ba_name='"+ids[0]+"')";
		}else if (ids.length==2&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")) {
			comps +="and ( v.ba_name='"+ids[0]+"' and v.comp_name='"+ids[1]+"')";
		}else if (ids.length==3&&!ids[0].equals("所有公司")&&ids[1].equals("所有分公司")) {
			comps +="and ( v.ba_name='"+ids[0]+"')";
		}else if (ids.length==3&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")&&ids[2].equals("所有车辆")) {
			comps +="and ( v.ba_name='"+ids[0]+"' and v.comp_name='"+ids[1]+"')";
		}else if (ids.length==3&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")&&!ids[2].equals("所有车辆")) {
			comps +="and ( v.ba_name='"+ids[0]+"' and v.comp_name='"+ids[1]+"' and v.vehi_no='"+ids[2]+"')";
		}
		sql+=comps;
		sql+=" order by v.comp_name,v.vehi_no";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setComp_id(rs.getString("comp_name")==null?"&nbsp;":rs.getString("comp_name"));
				v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
				v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
				v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp;":rs.getString("vehi_no"));
				v.setVehi_sim(rs.getString("vehi_sim")==null?"&nbsp;":rs.getString("vehi_sim"));
				list.add(v);
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//查询区域车辆具体信息
	public List<Vhic>findvhicinfo(String time,String id){
		String day=time.substring(0, 10)+" "+time.subSequence(10, 18);
		List<Vhic>list=new ArrayList<Vhic>();
		String sql=" select *  from hz_taxi.TB_TAXI_STATUS_new t,hz_taxi.TB_TAXI_AREA a, HZGPS_TAXI.VW_VEHICLE@TAXILINK v" +
				" where t.vehi_no=v.vehi_no and (t.LATI<=a.LATITUDE_MAX  and t.LATI>=a.LATITUDE_MIN)" +
				" and (t.LONGI <=a.LONGITUDE_MAX and t.LONGI>=LONGITUDE_MIN)" +
				" and a.AREA_ID='"+id+"' and t.db_time>=to_date('"+day+"','yyyy-mm-dd hh24:mi:ss')-1/24/60*10 ";
		sql+=" order by v.comp_name,v.vehi_no";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setComp_id(rs.getString("comp_name")==null?"&nbsp;":rs.getString("comp_name"));
				v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
				v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
				v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp;":rs.getString("vehi_no"));
				v.setVehi_sim(rs.getString("vehi_sim")==null?"&nbsp;":rs.getString("vehi_sim"));
				list.add(v);
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//未上线车辆查询
	public List<Vhic>findnoline(String stime,String etime,String areaid,String compid,String name){
		long ts = getDaySub(stime, etime)+1;
		List<Vhic>list=new ArrayList<Vhic>();
		String [] ids = null;
		if(name!=""){
			ids = name.split(",");
		}
		String sql="";
		String comps = "";
		for (int i = 0; i < ids.length; i++) {
			comps += "'"+ids[i]+"',";
		}
		comps = comps.substring(0, comps.length()-1);
			sql="select t.* from (select v.vehi_no,v.comp_name,v.own_name,v.own_tel,v.vehi_sim,max(ONLINE_TIME) ONLINE_TIME,count(1) c  from HZGPS_TAXI.VW_VEHICLE@TAXILINK v ,TB_TAXI_NOT_ONLINE@taxilink69 ol where 1=1 " ;
			if (areaid.equals("0")||areaid==null) {
				sql+=" and ba_name in ("+comps+")"; 
			}else {
				sql+=" and ba_name ='"+areaid+"'";
			}
			sql+=" and ol.vehi_no=v.vehi_no "
					+ " and db_time>=to_date('"+stime+"','yyyy-MM-dd') and  db_time<=to_date('"+etime+"','yyyy-MM-dd')"
					+ " group by  v.vehi_no, v.comp_name, v.own_name, v.own_tel, v.vehi_sim having count(1)>="+ts
					+ " ) t  order by t.ONLINE_TIME desc";
			System.out.println("未上线"+sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setComp_id(rs.getString("comp_name")==null?"&nbsp;":rs.getString("comp_name"));
				v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
				v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
				v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp;":rs.getString("vehi_no"));
				v.setVehi_sim(rs.getString("vehi_sim")==null?"&nbsp;":rs.getString("vehi_sim"));
				if(rs.getString("online_time")==null){
					v.setTime("&nbsp;");
				}else{					
					v.setTime(rs.getString("online_time").substring(0, 19)==null?"&nbsp;":rs.getString("online_time").substring(0, 19));
				}
				list.add(v);
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//上线无营运车辆查询
		public List<Vhic>findonlineoffbus(String stime,String etime,String areaid,String compid,String name) {
			List<Vhic>list=new ArrayList<Vhic>();
	        String riqi = stime.substring(2, 4)+stime.substring(5, 7);
			String [] ids = name.split(",");
			String sql="";
			String comps = "";
			for (int i = 0; i < ids.length; i++) {
				comps += "'"+ids[i]+"',";
			}
			comps = comps.substring(0, comps.length()-1);
				sql="select v.vehi_no,v.comp_name,v.own_name,v.own_tel,v.vehi_sim,v.ba_name, max(ol.db_time) online_time from TB_TAXI_ONLINE_"+riqi+"@taxilink69 ol,VW_VEHICLE@taxilink69 v where ol.db_time>=to_date('"+stime+"','yyyy-MM-dd') and ol.db_time<=to_date('"+etime+"','yyyy-MM-dd') "
							+ " and v.vehi_no in (select distinct '浙'||vhic vhic from TB_JJQ_STATIC@taxilink69 where "
//							+ "\"Y/N\" ='N'  and shangche>=to_date('"+stime+" 00:00:00','yyyy-MM-dd HH24:mi:ss') and shangche<=to_date('"+etime+" 23:59:59','yyyy-MM-dd HH24:mi:ss') and"
								+ " '浙'||vhic NOT in (select distinct '浙'||vhic from TB_JJQ_STATIC@taxilink69  where \"Y/N\" ='Y'"
									+ " and shangche>=to_date('"+stime+" 00:00:00','yyyy-MM-dd HH24:mi:ss') and shangche<=to_date('"+etime+" 23:59:59','yyyy-MM-dd HH24:mi:ss')"
								+ "))"
							+ " and v.vehi_no = ol.vehi_no";
				if (areaid.equals("0")||areaid==null) {
					sql+=" and v.ba_name in ("+comps+")"; 
				}else {
					sql+=" and v.ba_name ='"+areaid+"'";
				}
				sql += "group by v.vehi_no,v.comp_name,v.own_name,v.own_tel,v.vehi_sim,v.ba_name";
				System.out.println("上线无营运"+sql);
			try {
				Connection con=DataBese.getConnectionOfOracle();
				PreparedStatement ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					Vhic v=new Vhic();
					v.setComp_id(rs.getString("comp_name")==null?"&nbsp;":rs.getString("comp_name"));
					v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
					v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
					v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp;":rs.getString("vehi_no"));
					v.setVehi_sim(rs.getString("vehi_sim")==null?"&nbsp;":rs.getString("vehi_sim"));
					v.setTime(rs.getString("online_time").substring(0, 10)==null?"&nbsp;":rs.getString("online_time").substring(0, 10));
					list.add(v);
				}
				rs.close();
				ps.close();
				con.commit();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		//未上线营运车辆查询
		public List<Vhic>findnolinebus(String stime,String etime,String areaid,String compid,String name) {
			long ts = getDaySub(stime, etime)+1;
			List<Vhic>list=new ArrayList<Vhic>();
			String [] ids = name.split(",");
			String sql="";
			String comps = "";
			for (int i = 0; i < ids.length; i++) {
				comps += "'"+ids[i]+"',";
			}
			comps = comps.substring(0, comps.length()-1);
				sql="select t.* from  (select a.*,v.*  from"
					+ " (select vehi_no,count(1) c from TB_TAXI_NOT_ONLINE@taxilink69 where db_time>=to_date('"+stime+"','yyyy-MM-dd') and db_time<=to_date('"+etime+"','yyyy-MM-dd') and vehi_no is not null "
							+ " and vehi_no in  (select distinct '浙'||vhic from TB_JJQ_STATIC@taxilink69  where \"Y/N\" ='Y'"
									+ " and shangche>=to_date('"+stime+" 00:00:00','yyyy-MM-dd HH24:mi:ss') and shangche<=to_date('"+etime+" 23:59:59','yyyy-MM-dd HH24:mi:ss')"
								+ ") group by vehi_no having count(1)>="+ts
					+ " ) a";
				sql +=" left join (select vehi_no vehi_num,comp_name,own_name,own_tel,vehi_sim,ba_name from VW_VEHICLE@taxilink69 ) v on v.vehi_num=a.vehi_no) t  where 1=1" ;
				if (areaid.equals("0")||areaid==null) {
					sql+=" and t.ba_name in ("+comps+")"; 
				}else {
					sql+=" and t.ba_name ='"+areaid+"'";
				}
				System.out.println("未上线营运"+sql);
			try {
				Connection con=DataBese.getConnectionOfOracle();
				PreparedStatement ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					Vhic v=new Vhic();
					v.setComp_id(rs.getString("comp_name")==null?"&nbsp;":rs.getString("comp_name"));
					v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
					v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
					v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp;":rs.getString("vehi_no"));
					v.setVehi_sim(rs.getString("vehi_sim")==null?"&nbsp;":rs.getString("vehi_sim"));
					list.add(v);
				}
				rs.close();
				ps.close();
				con.commit();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		//无上线无营运车辆查询
		public List<Vhic>findnolineoffbus(String stime,String etime,String areaid,String compid,String name) {
			long ts = getDaySub(stime, etime)+1;
			List<Vhic>list=new ArrayList<Vhic>();
			String [] ids = name.split(",");
			String sql="";
			String comps = "";
			for (int i = 0; i < ids.length; i++) {
				comps += "'"+ids[i]+"',";
			}
			comps = comps.substring(0, comps.length()-1);
				sql="select v.vehi_no,v.vehi_num,v.comp_name,v.own_name,v.own_tel,v.vehi_sim,v.ba_name from VW_VEHICLE@taxilink69 v where v.vehi_no in (select vehi_no from TB_TAXI_NOT_ONLINE@taxilink69 where db_time>=to_date('"+stime+"','yyyy-MM-dd') and db_time<=to_date('"+etime+"','yyyy-MM-dd') and vehi_no is not null"
						+ " group by vehi_no having count(1)>="+ts
							+ " ) and vehi_no not in (select distinct '浙'||vhic from TB_JJQ_STATIC@taxilink69  where \"Y/N\" ='Y'"
									+ " and shangche>=to_date('"+stime+" 00:00:00','yyyy-MM-dd HH24:mi:ss') and shangche<=to_date('"+etime+" 23:59:59','yyyy-MM-dd HH24:mi:ss')"
								+ ")";
				if (areaid.equals("0")||areaid==null) {
					sql+=" and v.ba_name in ("+comps+")"; 
				}else {
					sql+=" and v.ba_name ='"+areaid+"'";
				}
				System.out.println("无上线无营运"+sql);
			try {
				Connection con=DataBese.getConnectionOfOracle();
				PreparedStatement ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					Vhic v=new Vhic();
					v.setComp_id(rs.getString("comp_name")==null?"&nbsp;":rs.getString("comp_name"));
					v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
					v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
					v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp;":rs.getString("vehi_no"));
					v.setVehi_sim(rs.getString("vehi_sim")==null?"&nbsp;":rs.getString("vehi_sim"));
					list.add(v);
				}
				rs.close();
				ps.close();
				con.commit();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		//无空车变化查询
		public List<Vhic>findnoempty(String stime,String etime,String areaid,String compid,String name) {
			List<Vhic>list=new ArrayList<Vhic>();
			String [] ids = name.split(",");
			String comps = "";
			for (int i = 0; i < ids.length; i++) {
				comps += "'"+ids[i]+"',";
			}
			
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date dBegin = null,dEnd = null;
			try {
				dBegin = sdf.parse(stime);
				dEnd = sdf.parse(etime);  
			} catch (ParseException e) {
				e.printStackTrace();
			}  
		      
	        List<Date> lDate = findDates(dBegin, dEnd);  
	        String riqi = "";
	        for (Date date : lDate) { 
	    	    riqi += sdf.format(date)+",";
	        }  
	        String[] a = riqi.substring(0, riqi.length()-1).split(",");
//			String sql = "select x.*,v.comp_name,v.mt_name,v.mdt_sub_type from(select vehi_no,";
//			for (int i = 0; i < a.length; i++) {
//				sql += " (select state from TB_TJ@taxilink69 A where xztime=to_date('"+a[i]+"','yyyy-mm-dd') and A.vehi_no=TB_TJ.vehi_no)as m"+(i+1)+",";
//			}
//			sql = sql.substring(0, sql.length()-1);
//			sql += " from TB_TJ@taxilink69 group by vehi_no) x,vw_vehicle@taxilink69 v where x.vehi_no = v.VEHI_NO and (";
//			for (int i = 0; i < a.length; i++) {
//				if(i==0){
//					sql += " nvl(m1,'0' ) != '0'";
//				}else{
//					sql += " or nvl(m"+(i+1)+",'0' ) != '0'";
//				}
//			}
//			sql += ")";
//			
//			comps = comps.substring(0, comps.length()-1);				
//			if (areaid.equals("0")||areaid==null) {
//				sql+=" and v.ba_name in ("+comps+")"; 
//			}else {
//				sql+=" and v.ba_name ='"+areaid+"'";
//			}
			
			String sql="select ";
			for (int i = 0; i < a.length; i++) {
				sql += " m"+(i+1)+".state as m"+(i+1)+",";
			}
			sql += " a.* from (select distinct x.vehi_no, v.comp_name, v.mt_name, v.mdt_sub_type from TB_TJ@taxilink69 x ,vw_vehicle@taxilink69 v "
					+ " where x.xztime >= to_date('"+stime+"', 'yyyy-mm-dd') and x.xztime <= to_date('"+etime+"', 'yyyy-mm-dd') and state is not null and x.vehi_no = v.VEHI_NO";
			comps = comps.substring(0, comps.length()-1);				
			if (areaid.equals("0")||areaid==null) {
				sql+=" and v.ba_name in ("+comps+")"; 
			}else {
				sql+=" and v.ba_name ='"+areaid+"'";
			}
			sql +=")a ";
			for (int i = 0; i < a.length; i++) {
				sql +=" left join  (select state,vehi_no from TB_TJ@taxilink69 where xztime=to_date('"+a[i]+"','yyyy-mm-dd'))m"+(i+1)+" on  a.vehi_no = m"+(i+1)+".vehi_no";
			}
			System.out.println("无空车变化"+sql);
			try {
				Connection con=DataBese.getConnectionOfOracle();
				PreparedStatement ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					Vhic v=new Vhic();
					v.setComp_id(rs.getString("comp_name")==null?"&nbsp;":rs.getString("comp_name"));
					v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp;":rs.getString("vehi_no"));
					v.setA(a.length);
					String b = "",c = "";
					for (int i = 0; i < a.length; i++) {
						c = "m"+(i+1);
						b += rs.getString(c)+";";
					}
					v.setBa_id(b);
					list.add(v);
				}
				rs.close();
				ps.close();
				con.commit();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
		private List<Date> findDates(Date beginDate, Date endDate) {
			List<Date> lDate = new ArrayList<Date>();
			Calendar cal = Calendar.getInstance();
			// 使用给定的 Date 设置此 Calendar 的时间
			cal.setTime(beginDate);
			if (cal.getTime().before(endDate) || cal.getTime().equals(endDate)) {
				lDate.add(beginDate);// 把开始时间加入集合
				boolean bContinue = true;
				while (bContinue) {
				// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
				cal.add(Calendar.DAY_OF_MONTH, 1);
				// 测试此日期是否在指定日期之后
				if (endDate.after(cal.getTime())) {
					lDate.add(cal.getTime());
				} else {
					break;
					
				}
			}
				if ( !beginDate.equals(endDate)) {					
					lDate.add(endDate);// 把结束时间加入集合
				}
			}
			return lDate;
		}
	//时间段车辆数量分析
//	public List<Vhic>findtimevhic(String stime,String etime,String quyu,String compname){
//		List<Vhic>list=new ArrayList<Vhic>();
//		String sql="select distinct(t.vehi_no),vehi_sim,own_name,own_tel,area_name,v.ba_name from TB_TAXI_AREA_INFO_RECORD t" +
//				",HZGPS_TAXI.VW_VEHICLE@TAXILINK v where t.vehi_no=v.vehi_no and " +
//				" t.source_db_time>to_date('"+stime+"','yyyy-mm-dd hh24:mi:ss')" +
//				" and t.source_db_time<to_date('"+etime+"','yyyy-mm-dd hh24:mi:ss') ";
//		System.out.println(sql);
//		if (compname!=null&&compname.length()>0&&!compname.equals("0")) {
//			sql+=" and v.ba_name ='"+compname+"'";
//		}
//		if (!quyu.equals("0")) {
//			sql+=" and area_name='"+quyu+"'";
//		}else {
//			sql+="and area_name!='非监控区域'";
//		}
//		sql+=" order by ba_name";
//		Connection con=null;
//		PreparedStatement ps=null;
//		ResultSet rs=null;
//		try {
//			con=DataBese.getConnectionOfOracle();
//			ps=con.prepareStatement(sql);
//			rs=ps.executeQuery();
//			int a=0;
//			while(rs.next()){
//				Vhic v=new Vhic();
//				v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp;":rs.getString("vehi_no"));
//				a=findtimevhicno(stime, etime, quyu, rs.getString("vehi_no"));
//				v.setTime(a+"");
//				v.setVehi_sim(rs.getString("vehi_sim")==null?"&nbsp;":rs.getString("vehi_sim"));
//				v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
//				v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
//				v.setBa_name(rs.getString("area_name"));
//				v.setComp_name(rs.getString("ba_name"));
//				list.add(v);
//			}
//			rs.close();
//			ps.close();
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date d1=null;
//		Date d2=null;
//		try {
//			d1 = sd.parse(stime);
//			d2 = sd.parse(etime);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//        //毫秒ms
//        long diff = d2.getTime() - d1.getTime();
//
//        long diffMinutes = diff / (60 * 1000) % 60;
//        long diffHours = diff / (60 * 60 * 1000) % 24;
//        long diffDays = diff / (24 * 60 * 60 * 1000);
//        if (diffDays!=0) {
//			diffMinutes=diffDays*24*60+diffMinutes;
//			if (diffHours!=0) {
//	 			diffMinutes=diffHours*60+diffMinutes;
//	 		}
//		}
//        if (diffHours!=0) {
//			diffMinutes=diffHours*60+diffMinutes;
//		}
//        long a=diffMinutes/10;
//        if (a<10) {
//			a=1;
//		}
//		List<Vhic>l=new ArrayList<Vhic>();
//		for (int i = 0; i < list.size(); i++) {
//			Vhic v1=new Vhic();
//			if (Integer.parseInt(list.get(i).getTime())>=a) {
//				v1.setVehi_no(list.get(i).getVehi_no());
//				v1.setTime(list.get(i).getTime());
//				v1.setVehi_sim(list.get(i).getVehi_sim());
//				v1.setOwn_name(list.get(i).getOwn_name());
//				v1.setOwn_tel(list.get(i).getOwn_tel());
//				v1.setBa_name(list.get(i).getBa_name());
//				v1.setComp_name(list.get(i).getComp_name());
//				l.add(v1);
//			}
//		}
//		return l;
//	}
//	//时段车辆数量查询
//	public int findtimevhicno(String stime,String etime,String quyu,String vehi){
//		int count=0;
//		String sql="select count(*) c from TB_TAXI_AREA_INFO_RECORD t,HZGPS_TAXI.VW_VEHICLE@TAXILINK v " +
//				" where t.vehi_no=v.vehi_no and  t.vehi_no='"+vehi+"' and" +
//				" t.source_db_time>to_date('"+stime+"','yyyy-mm-dd hh24:mi:ss') and" +
//				" t.source_db_time<to_date('"+etime+"','yyyy-mm-dd hh24:mi:ss') ";
//		if (quyu!=null&&quyu.length()>0&&!quyu.equals("0")) {
//			sql+=" and area_name='"+quyu+"'";
//		}
//		System.out.println(sql);
//		Connection con=null;
//		PreparedStatement ps=null;
//		ResultSet rs=null;
//		try {
//			con=DataBese.getConnectionOfOracle();
//			ps=con.prepareStatement(sql);
//			rs=ps.executeQuery();
//			while(rs.next()){
//				count=rs.getInt("c");
//			}
//			rs.close();
//			ps.close();
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return count;
//	}
	
	
	//时间段车辆数量分析
	public List<Vhic>findtimevhic(String stime,String etime,String quyu,String compname){
		List<Vhic>list=new ArrayList<Vhic>();
		String sql="select * from(select v.vehi_no,v.vehi_sim,v.own_name,v.own_tel,ve.area_name,v.ba_name" +
				"  from (select distinct(t.vehi_no),area_name from hz_taxi.TB_TAXI_AREA_INFO_RECORD t," +
				"HZGPS_TAXI.VW_VEHICLE@TAXILINK v where t.vehi_no=v.vehi_no and " +
				" t.source_db_time>to_date('"+stime+"','yyyy-mm-dd hh24:mi:ss') and" +
				"  t.source_db_time<to_date('"+etime+"','yyyy-mm-dd hh24:mi:ss')";
				if (compname!=null&&compname.length()>0&&!compname.equals("0")) {
					sql+=" and v.ba_name ='"+compname+"'";
				}
				if (!quyu.equals("0")) {
					sql+=" and area_name='"+quyu+"'";
				}else {
					sql+="and area_name!='非监控区域'";
				}
				sql+=") ve, HZGPS_TAXI.VW_VEHICLE@TAXILINK v where ve.vehi_no=v.vehi_no ) a, " +
				" (select count(t.vehi_no) v,t.vehi_no from hz_taxi.TB_TAXI_AREA_INFO_RECORD t,HZGPS_TAXI.VW_VEHICLE@TAXILINK v " +
				" where t.vehi_no=v.vehi_no and t.source_db_time>to_date('"+stime+"','yyyy-mm-dd hh24:mi:ss') " +
				"and t.source_db_time<to_date('"+etime+"','yyyy-mm-dd hh24:mi:ss')  " ;
				if (compname!=null&&compname.length()>0&&!compname.equals("0")) {
					sql+=" and v.ba_name ='"+compname+"'";
				}
				if (!quyu.equals("0")) {
					sql+=" and area_name='"+quyu+"'";
				}else {
					sql+="and area_name!='非监控区域'";
				}
				sql+=" group by t.vehi_no) b where a.vehi_no=b.vehi_no ";
				
				sql+=" order by ba_name";
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DataBese.getConnectionOfOracle();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			int a=0;
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d1=null;
			Date d2=null;
			try {
				d1 = sd.parse(stime);
				d2 = sd.parse(etime);
			} catch (ParseException e) {
				e.printStackTrace();
			}

	        //毫秒ms
	        long diff = d2.getTime() - d1.getTime();

	        long diffMinutes = diff / (60 * 1000) % 60;
	        long diffHours = diff / (60 * 60 * 1000) % 24;
	        long diffDays = diff / (24 * 60 * 60 * 1000);
	        if (diffDays!=0) {
				diffMinutes=diffDays*24*60+diffMinutes;
				if (diffHours!=0) {
		 			diffMinutes=diffHours*60+diffMinutes;
		 		}
			}
	        if (diffHours!=0) {
				diffMinutes=diffHours*60+diffMinutes;
			}
	        long a1=diffMinutes/10;
	        if (a1<10) {
				a1=1;
			}
			while(rs.next()){
				Vhic v=new Vhic();
				if(rs.getLong("v")>=a1){
				v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp;":rs.getString("vehi_no"));
				v.setVehi_sim(rs.getString("vehi_sim")==null?"&nbsp;":rs.getString("vehi_sim"));
				v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
				v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
				v.setBa_name(rs.getString("area_name"));
				v.setComp_name(rs.getString("ba_name"));
				list.add(v);
				}
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//公司出租汽车营运率分析
	public List<Vhic>findtaxifenxi(String time){
		List<Vhic>list=new ArrayList<Vhic>();
		String sql="select *  from HZGPS_TAXI.TB_BUSI_AREA@TAXILINK a, hz_taxi.TB_TAXI_CHUCHE_INFO_RECORD t" +
				" where a.ba_id=t.ba_id and db_time=to_Date('"+time+"','yyyy-MM-dd')";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			List<Vhic>list1=findareacls(time);
			int a=0;
			while(rs.next()){
				Vhic v=new Vhic();
				if (!rs.getString("ba_name").equals("测试")) {
					for (int i = 0; i < list1.size(); i++) {
						if (rs.getString("ba_id").equals(list1.get(i).getBa_id())) {
							a++;
						}
					}
					v.setA(a);
					v.setBa_name(rs.getString("ba_name"));
					v.setAverage_run_times(rs.getString("average_run_times"));
					v.setBusy_taxi_chuche_rate(rs.getString("busy_taxi_chuche_rate"));
					v.setTaxi_chuche_rate(rs.getString("taxi_chuche_rate"));
					v.setRun_times(rs.getString("run_times"));
					a=0;
					list.add(v);
				}
			}
			VhicComparator comp=new VhicComparator();
			//调用排序方法
			Collections.sort(list,comp);
			 
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//查询车辆
	public List<Vhic> findcl3(String vehi_no){
		List<Vhic> list = new ArrayList<Vhic>();
		String sql = "select vehi_no from HZGPS_TAXI.VW_VEHICLE@TAXILINK where vehi_no like '%"+vehi_no+"%' order by vehi_no";
		System.out.println(sql);
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Vhic v = new Vhic();
				v.setVehi_id(rs.getString("vehi_no"));
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
	//多屏监控
	public List<Vehicle> finddpjk(String quyu){
		List<Vehicle>list=new ArrayList<Vehicle>();
		String sql="select *  from HZ_TAXI.TB_TAXI_STATUS_NEW t,HZGPS_TAXI.VW_VEHICLE@TAXILINK v where t.mdt_no=v.sim_num"
				+ " and vehi_no = '"+quyu+"'";
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
				v.setVehi_sim(rs.getString("vehi_sim")==null?"&nbsp":rs.getString("vehi_sim"));
				v.setSpeed(rs.getString("speed")==null?"&nbsp":rs.getString("speed"));
				v.setHeading(rs.getString("heading")==null?"&nbsp":rs.getString("heading"));
				if (rs.getString("stime")!=null) {
					v.setDateTime(rs.getString("stime").substring(0, 19));
					long time=rs.getTimestamp("stime").getTime();
					Date date=new Date();
					long time1=date.getTime();
					if (time1-time<=1800000) {
						v.setOnoffstate("0");
					}
				}else {
					v.setOnoffstate("1");
				}
				v.setState(rs.getString("status")==null?"&nbsp":rs.getString("status"));
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
	//查询公司车辆数
	public List<Vhic> findareacls(String time){
		List<Vhic>list=new ArrayList<Vhic>();
		String sql="select *  from HZGPS_TAXI.TB_BUSI_AREA@TAXILINK a,HZGPS_TAXI.VW_VEHICLE@TAXILINK v where a.ba_id=v.BA_id";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setBa_name(rs.getString("Ba_name"));
				v.setBa_id(rs.getString("ba_id"));
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
	
    public static long getDaySub(String beginDateStr,String endDateStr)
    {
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate;
        Date endDate;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1); //得到前一天
        Date date = null;
        try {
            date = format.parse(format.format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            beginDate = format.parse(beginDateStr);
            endDate= format.parse(endDateStr);
            if(endDate.getTime()>date.getTime()){
                day=(date.getTime()-beginDate.getTime())/(24*60*60*1000);
            }else{
                day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }
	public List<Vehicle> findwxtj(String stime, String etime, String compid,
			String compname, String quyu, String areaid, String name) {
		List<Vehicle>list=new ArrayList<Vehicle>();
		String sql = "select * from(select t.vehi_no,count(1) c from TB_REPAIR_RECORD@Db113 t,tb_user_wx@Db113 u,TB_REPAIR_TYPE@Db113 r"
				+ ",vw_vehicle@Db113 v where"
				+ " t.user_id=u.user_id and t.rt_id=r.rt_id and (t.vehi_id = v.vehi_id or t.vehi_no = v.VEHI_NO)"
				+ " and rr_time >=to_date('"+stime+"','yyyy-mm-dd hh24:mi:ss')"
				+ " and rr_time <=to_date('"+etime+"','yyyy-mm-dd hh24:mi:ss')";
				String [] ids = name.split(",");
				String comps = "";
				for (int i = 0; i < ids.length; i++) {
					comps += "'"+ids[i]+"',";
				}
				comps = comps.substring(0, comps.length()-1);
				sql += " and comp_name in ("+comps+")";
				if(compid!=null&&!compid.equals("null")&&compid.length()>0&&!compid.equals("0")){
					sql += " and user_name = '"+compid+"'";
				}
				if(compname!=null&&!compname.equals("null")&&compname.length()>0&&!compid.equals("0")){
					sql += " and rt_type = '"+compname+"'";
				}
				if(quyu!=null&&!quyu.equals("null")&&quyu.length()>0&&!compid.equals("0")){
					sql += " and a.vehi_no = '"+quyu+"'";
				}
				if(areaid!=null&&!areaid.equals("null")&&areaid.length()>0&&!compid.equals("0")){
					sql += " and comp_name = '"+areaid+"'";
				}
				sql += " group by t.vehi_no) a left join vw_vehicle@Db113 v on a.vehi_no = v.vehi_no";
				System.out.println(sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vehicle v=new Vehicle();
				v.setBa_name(rs.getString("comp_name"));
				v.setVehi_no(rs.getString("vehi_no"));
				v.setMt_name(rs.getString("mt_name"));
				v.setMdtno(rs.getString("mdt_sub_type"));
				v.setAddress(rs.getString("c"));
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
	public List<Map<String, Object>> findclwh(String vhic,String name){
		String sql = "select COMP_NAME,OWN_NAME,OWN_TEL,MT_NAME,MDT_SUB_TYPE,VSIM_NUM,VEHI_SIM,MDT_NO,VEHI_DATE,NOTE from VW_VEHICLE@Db113 where vehi_no = '"+vhic+"'";
		String [] ids = name.split(",");
		String comps = "";
		for (int i = 0; i < ids.length; i++) {
			comps += "'"+ids[i]+"',";
		}
		comps = comps.substring(0, comps.length()-1);
		sql += " and comp_name in ("+comps+")";
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Map<String, String> map = new HashMap<String, String>();
				map.put("COMP_NAME", rs.getString("COMP_NAME"));
				map.put("OWN_NAME", rs.getString("OWN_NAME"));
				map.put("OWN_TEL", rs.getString("OWN_TEL"));
				map.put("MT_NAME", rs.getString("MT_NAME"));
				map.put("MDT_SUB_TYPE", rs.getString("MDT_SUB_TYPE"));
				map.put("VSIM_NUM", rs.getString("VSIM_NUM"));
				map.put("VEHI_SIM", rs.getString("VEHI_SIM"));
				map.put("MDT_NO", rs.getString("MDT_NO"));
				map.put("VEHI_DATE", rs.getString("VEHI_DATE"));
				map.put("NOTE", rs.getString("NOTE"));
				list.add(map);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Map<String, Object>> l = new ArrayList<Map<String,Object>>();
		Map<String, Object> m = new HashMap<String, Object>();
		List<Map<String, String>> list1 = findwxxx(vhic,name);
		m.put("info", list);
		m.put("wxxx", list1);
		l.add(m);
		return l;
	}
	public List<Map<String, String>> findwxxx(String vhic,String name){
		String wxxx = "select a.*,rm.RM_MALFUNCTION RM_MALFUNCTION1,rt.rt_type,ra.ra_addr from(select r.*"
				+ ",v.VEHI_NO vehi_no1 from TB_REPAIR_RECORD@Db113 r,"
				+ " vw_vehicle@Db113 v where (r.vehi_id = v.vehi_id or r.vehi_no = v.VEHI_NO)"
				+ " and (r.vehi_no = '"+vhic+"' or v.VEHI_NO = '"+vhic+"')";
//		if(!power_name.equals("管理员")){
//			wxxx += " and u.user_name = '"+username+"'";
//		}
		String [] ids = name.split(",");
		String comps = "";
		for (int i = 0; i < ids.length; i++) {
			comps += "'"+ids[i]+"',";
		}
		comps = comps.substring(0, comps.length()-1);
		wxxx += " and comp_name in ("+comps+")";
		wxxx += " ) a left join TB_REPAIR_MALFUNCTION@Db113 rm on a.rm_id=rm.rm_id "
				+ " left join TB_REPAIR_TYPE@Db113 rt on a.rt_id=rt.rt_id left join TB_REPAIR_ADDR@Db113 ra on a.ra_id=ra.ra_id"
				+ " order by rr_time desc";
		System.out.println(wxxx);
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(wxxx);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Map<String, String> map = new HashMap<String, String>();
				map.put("VEHI_NO", rs.getString("VEHI_NO"));
				map.put("VEHI_NAME", rs.getString("VEHI_NAME"));
				map.put("VEHI_PHONE", rs.getString("VEHI_PHONE"));
				map.put("RT_TYPE", rs.getString("RT_TYPE"));
				map.put("RT_ID", rs.getString("RT_ID"));
				map.put("RR_COST", rs.getString("RR_COST"));
				map.put("RR_STATE", rs.getString("RR_STATE"));
				map.put("USER_ID", rs.getString("USER_ID"));
				map.put("RA_ADDR", rs.getString("RA_ADDR"));
				map.put("RC_CONTENT", rs.getString("RC_CONTENT"));
				map.put("RR_TIME", rs.getString("RR_TIME"));
				map.put("RR_TIME_END", rs.getString("RR_TIME_END"));
				map.put("RM_MALFUNCTION1", rs.getString("RM_MALFUNCTION1"));
				map.put("RM_MALFUNCTION", rs.getString("RM_MALFUNCTION"));
//				map.put("RC_MODE", rs.getString("RC_MODE")); 
				map.put("RR_AUDITIME", rs.getString("RR_AUDITIME"));
				map.put("RR_ID", rs.getString("RR_ID"));
				map.put("RM_ID", rs.getString("RM_ID"));
				map.put("RA_ID", rs.getString("RA_ID"));
				map.put("TCSS", rs.getString("TCSS"));
				list.add(map);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Wx> findwxinfolist(String table){
		String sql = "";
		if(table.equals("TB_REPAIR_ADDR")){
			sql = "select ra_id id,ra_addr name from TB_REPAIR_ADDR@Db113";
		}else if(table.equals("TB_REPAIR_MALFUNCTION")){
			sql = "select rm_id id,rm_malfunction name from TB_REPAIR_MALFUNCTION@Db113";
		}else if(table.equals("TB_REPAIR_TYPE")){
			sql = "select rt_id id,rt_type name from TB_REPAIR_TYPE@Db113";
		}else if(table.equals("TB_REPAIR_CONTENT")){
			sql = "select rc_id id,rc_content name from TB_REPAIR_CONTENT@Db113";
		}
		List<Wx> list = new ArrayList<Wx>();
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Wx w = new Wx();
				w.setId(rs.getString("ID"));
				w.setName(rs.getString("NAME"));
				list.add(w);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public int addwxgl(String cphm,String lxr,String lxdh,String gzxx,String gzms,String wxlx,String dqgz,String wxfy,String wxry,String wxdd,String wxnr,String sxsj,String wxsj,String myd) {
		String rr_id = findMaxId();
		int count = 0;
		String sql = "insert into TB_REPAIR_RECORD@Db113 (rr_id,vehi_no,rt_id,rr_cost,ra_id,rc_content,rr_time,"
						+ "rr_time_end,rm_id,rm_malfunction,tcss,user_id,vehi_name,vehi_phone) values "
						+ "('"+rr_id+"','"+cphm+"','"+wxlx+"','"+wxfy+"',"
						+ "'"+wxdd+"','"+wxnr+"',to_date('"+sxsj+"','yyyy-mm-dd hh24:mi:ss'),"
						+ "to_date('"+wxsj+"','yyyy-mm-dd hh24:mi:ss'),'"+gzxx+"','"+gzms+"','"
						+myd+"','"+wxry+"'"
						+ ",'"+lxr+"','"+lxdh+"')";
		System.out.println(sql);
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			count=0;
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	public String findMaxId(){
		int id = 1;
		String sql = "select max(to_number(RR_ID)) RR_ID from TB_REPAIR_RECORD@Db113";
		System.out.println(sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				id = rs.getInt("RR_ID")+1;
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id+"";
	}
	public int editwxgl(String cphm, String lxr, String lxdh, String gzxx,
			String gzms, String wxlx, String dqgz, String wxfy, String wxry,
			String wxdd, String wxnr, String sxsj, String wxsj, String myd, String id) {
		int count = 0;
		String sql = "update TB_REPAIR_RECORD@Db113 set vehi_no='"+cphm+"',rt_id='"+wxlx+"'"
				+ "	,rr_cost='"+wxfy+"',ra_id='"+wxdd+"',rc_content='"+wxnr+"',rr_time=to_date('"+wxsj+"','yyyy-mm-dd hh24:mi:ss'),"
						+ "rr_time_end=to_date('"+wxsj+"','yyyy-mm-dd hh24:mi:ss'),rm_id='"+gzxx+"',rm_malfunction='"+gzms+"',"
								+ "tcss='"+myd+"',user_id='"+wxry+"',vehi_name='"+lxr+"',vehi_phone='"+lxdh+"'"
						+ " where rr_id = '"+id+"'";
		System.out.println(sql);
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			count=0;
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	public int delwxgl(String id) {
		int count = 0;
		String sql = "delete from TB_REPAIR_RECORD@Db113 "
						+ " where rr_id = '"+id+"'";
		System.out.println(sql);
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			count=0;
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
}
