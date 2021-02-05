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

import com.opensymphony.xwork2.ActionContext;
import com.tw.entity.Form;
import com.tw.entity.Vehicle;
import com.tw.util.DataBese;

public class FormDao {
	//查询日报表
	public List<Form> finddayfrom(String time,String ba_name,String data){
		String[] a = data.split(",");
		String comp = "";
		for (int i = 0; i < a.length; i++) {
			comp += "'"+a[i]+"',";
		}
		comp = comp.substring(0, comp.length()-1);
		List<Form>list=new ArrayList<Form>();
		String sql="";
		if (ba_name.equals("0")) {
			int gss = comp.split(",").length;
				sql="select db_time,sum(run_taxis) run_taxis,round(sum(run_times)/"+gss+",2) run_times, " +
				"round(sum(run_profit)/"+gss+",2) run_profit ,round(sum(rtrim(actual_load_rate,'%'))/"+gss+",2) actual_load_rate," +
				"round(sum(run_time)/"+gss+",2) run_time, round(sum(waitting_time)/"+gss+",2) waitting_time," +
				"round(sum(actual_load_mileage)/"+gss+",2) actual_load_mileage," +
				"round(sum(no_load_mileage)/"+gss+",2) no_load_mileage " +
				"from (select trunc(db_time,'hh') db_time,run_taxis,run_times,run_profit ,actual_load_rate," +
				"run_time,waitting_time,actual_load_mileage,no_load_mileage from" +
				" TB_TAXI_DAY@Taxilink69 t where " +
				"db_time>=to_date('"+time+" 00:00:00','yyyy-mm-dd hh24:mi:ss') and " +
				" db_time<=to_date('"+time+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
				sql+=" and ba_name in ("+comp+")";
				sql+=") where 1=1 group by db_time  order by db_time desc";
		}else {
			sql="select * from TB_TAXI_DAY@Taxilink69 where " +
			"db_time>=to_date('"+time+" 00:00:00','yyyy-mm-dd hh24:mi:ss') and " +
			" db_time<=to_date('"+time+" 23:59:59','yyyy-mm-dd hh24:mi:ss') and " +
					"ba_name='"+ba_name+"' order by db_time desc";
		}
		System.out.println(sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			String no1 = "";
			if (ba_name.equals("0")) {
				no1=findcompno(comp)+"";
			}else {
				no1=findcompno("'"+ba_name+"'")+"";
			}
			while(rs.next()){
				Form f=new Form();
				f.setReport_time(rs.getString("db_time").substring(0,19));//时间
				f.setRepore_actual_loading(rs.getString("actual_load_rate"));//平均实载率
				f.setRepore_wait_time(rs.getString("waitting_time"));//载客等候时间
				f.setRepore_empty_mileage(rs.getString("no_load_mileage"));//空驶里程
				f.setRepore_actual_mileage(rs.getString("actual_load_mileage"));//载客里程
				f.setRepore_no(rs.getString("run_times"));//周转次数
				f.setRepore_amount_revenue(rs.getString("run_profit"));//营收金额
				f.setReproe_revenue_time(rs.getString("run_time"));//重车时间
				f.setRepore_vhicno(rs.getString("run_taxis"));
				f.setRepore_vhic(no1);
				list.add(f);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static void main(String[] args) {
		String a = "'杭州大众出租汽车股份有限公司','杭州客旅汽车出租公司','杭州之江汽车出租有限公司','杭州中润客运有限公司','杭州大川旅游汽车出租有限公司','杭州华旅客运有限公司','杭州和谐出租汽车服务管理有限公司'";
		System.out.println(a.split(",").length);
	}
	//月报表
	public List<Form>findmonthform(String time,String name,String data){
		String[] a = data.split(",");
		String comp = "";
		for (int i = 0; i < a.length; i++) {
			comp += "'"+a[i]+"',";
		}
		comp = comp.substring(0, comp.length()-1);
		time=time.substring(0, 4)+time.substring(5, 7);
		List<Form>list=new ArrayList<Form>();
		String sql="";
		if (name.equals("0")) {
			int gss = comp.split(",").length;
			System.out.println(gss);
				sql="select report_time,sum(repore_vhic) repore_vhic," +
				"sum(repore_no) repore_no,sum(repore_vhicno) repore_vhicno," +
				"round(sum(REPORE_TURNOVER)/"+gss+",0) REPORE_TURNOVER," +
				"round(sum(rtrim(REPORE_RADE,'%'))/"+gss+",2) REPORE_RADE," +
				"round(sum(REPORE_AMOUNT_REVENUE)/"+gss+",0) REPORE_AMOUNT_REVENUE," +
				"round(sum(rtrim(REPORE_ACTUAL_LOADING,'%'))/"+gss+",2) REPORE_ACTUAL_LOADING," +
				"round(sum(REPORE_CAR_TIME)/"+gss+",2) REPORE_CAR_TIME," +
				"round(sum(REPROE_REVENUE_TIME)/"+gss+",2) REPROE_REVENUE_TIME," +
				"round(sum(REPORE_WAIT_TIME)/"+gss+",2) REPORE_WAIT_TIME," +
				"round(sum(REPORE_MILEAGE)/"+gss+",2) REPORE_MILEAGE," +
				"round(sum(REPORE_ACTUAL_MILEAGE)/"+gss+",2) REPORE_ACTUAL_MILEAGE," +
				"round(sum(REPORE_EMPTY_MILEAGE)/"+gss+",2) REPORE_EMPTY_MILEAGE" +
				" from TB_TAXI_MONTHLY@Taxilink69 t where 1=1 " ;
				sql+=" and ba_anme in ("+comp+")";
				sql+=" and  report_time like '%"+time+"%'  group by report_time order by report_time desc";
		}else {
			sql="select * from TB_TAXI_MONTHLY@Taxilink69 t where" +
					" report_time like '%"+time+"%' and ba_anme='"+name+"' order by report_time desc";
		}
		System.out.println(sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Form f=new Form();
				f.setRepore_actual_loading(rs.getString("repore_actual_loading"));//平均实载率
				f.setRepore_actual_mileage(rs.getString("repore_actual_mileage"));//平均实载里程
				f.setRepore_amount_revenue(rs.getString("repore_amount_revenue"));//平均营收金额
				f.setRepore_empty_mileage(rs.getString("repore_empty_mileage"));//平均空驶里程
				f.setRepore_mileage(rs.getString("repore_mileage"));//平均总里程
				f.setRepore_no(rs.getString("repore_no"));//营运总次数
				f.setRepore_rade(rs.getString("repore_rade"));//平均上路率
				f.setRepore_turnover(rs.getString("repore_turnover"));//平均周转次数
				f.setRepore_vhic(rs.getString("repore_vhic"));//参与营运车辆
				f.setRepore_wait_time(rs.getString("repore_wait_time"));//平均等候时间
				f.setReport_time(rs.getString("report_time"));//时间
				f.setReproe_revenue_time(rs.getString("reproe_revenue_time"));//平均重车时间
				f.setRepore_car_time(rs.getString("repore_car_time"));//平均出车时间
				f.setRepore_vhicno(rs.getString("repore_vhicno"));//车辆总数
//				if (username.equals("shiws")) {
//					if (name.equals("0")) {
//						f.setRepore_vhicno("625");
//					}else {
//						f.setRepore_vhicno(findcompno(name)+"");
//					}
//				}else {
//					if (name.equals("0")) {
//						f.setRepore_vhicno("1722");
//					}else {
//						f.setRepore_vhicno(findcompno(name)+"");
//					}
//				}
				list.add(f);
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
	public int findcompno(String ba_name){
		int count=0;
		String sql="select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK where comp_name in ("+ba_name+")";
//		System.out.println(sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				count++;
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	//查询紧急报警车辆
	public List<Vehicle> findalarmstatus(String data){
		String[] a = data.split(",");
		String comp = "";
		for (int i = 0; i < a.length; i++) {
			comp += "'"+a[i]+"',";
		}
		comp = comp.substring(0, comp.length()-1);
		Date d = new Date();
		long b = d.getTime()-1000*60*5;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date(b));
		String sql = "select a.vehicle_num,t.px,t.py,t.status,v.comp_name,v.vehi_sim,v.own_name,v.own_tel"
				+ " from(select vehicle_num,max(speed_time) from HZGPS_TAXI.TB_GPS_1703@TAXILINK t"
				+ " where (substr(alarmstatus, -1) = '1' or substr(alarmstatus, -1) = '3' or"
				+ " substr(alarmstatus, -1) = '5' or substr(alarmstatus, -1) = '7') and"
				+ " speed_time > to_date('"+time+"', 'yyyy-mm-dd hh24:mi:ss')"
				+ " group by vehicle_num)a,TB_TAXI_STATUS_NEW t,hzgps_taxi.vw_vehicle@taxilink v"
				+ " where t.mdt_no = v.sim_num and a.vehicle_num =v.vehi_no and v.comp_name in ("+comp+")";
		List<Vehicle> list =  new ArrayList<Vehicle>();
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Vehicle v = new Vehicle();
				v.setComp_name(rs.getString("comp_name"));
				v.setVehi_no(rs.getString("vehicle_num"));
				v.setPx(rs.getString("px"));
				v.setPy(rs.getString("py"));
				v.setStatus(rs.getString("status"));
				v.setSim_num(rs.getString("vehi_sim"));
				v.setOwn_name(rs.getString("own_name"));
				v.setOwn_tel(rs.getString("own_tel"));
				v.setAddress(new CarDao().getplace(rs.getString("px"), rs.getString("py")));
				list.add(v);
			}
		} catch (Exception e) {
		}
		return list;
	}
}
