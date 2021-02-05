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
import java.util.List;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.tw.entity.*;
import com.tw.util.DataBese;
import com.tw.util.VhicComparator;
import oracle.sql.NUMBER;


public class ApplyDao {
	private Date d=new Date();
	private SimpleDateFormat sdformat =new SimpleDateFormat("yyyy");
	private String time=sdformat.format(d);
	//查询车辆转入申请
	public List<Vhic>findvehicleapplication(String stime,String etime,String vehicle_no,String type){
		List<Vhic>list=new ArrayList<Vhic>();
		
		String sql="select a.*,v.REGISTER_DATE from tb_vehicle_application@TAXILINK69 a"
				+ " left join tb_vehicle@TAXILINK69 v on v.vehi_no=a.vehicle_no where 1=1";
		if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0){
			sql+=" and a.application_time >=to_date('"+stime+" 00:00:00','yyyy-MM-dd HH24:mi:ss')";
		}
		if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0){
			sql+=" and a.application_time <=to_date('"+etime+" 23:59:59','yyyy-MM-dd HH24:mi:ss')";
		}
		if(vehicle_no!=null&&!vehicle_no.isEmpty()&&!vehicle_no.equals("null")&&vehicle_no.length()>0){
			sql+=" and a.vehicle_no ='"+vehicle_no+"'";
		}
		if(type!=null&&!type.isEmpty()&&!type.equals("null")&&type.length()>0){
			sql+=" and a.JOIN_TURNOUT ='"+type+"'";
		}
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
				v.setComp_id(rs.getString("ID")==null?"":rs.getString("ID"));
				v.setVehi_no(rs.getString("VEHICLE_NO")==null?"":rs.getString("VEHICLE_NO"));
				v.setComp_name(rs.getString("OLD_COMPANY")==null?"":rs.getString("OLD_COMPANY"));
				v.setBa_name(rs.getString("NEW_COMPANY")==null?"":rs.getString("NEW_COMPANY"));
				v.setTime(rs.getString("JOIN_TIME")==null?"":rs.getString("JOIN_TIME").substring(0, 19));
				v.setOwn_name(rs.getString("APPLICATION_TIME")==null?"":rs.getString("APPLICATION_TIME").substring(0, 19));
				v.setVehi_sim(rs.getString("AUDIT_STATUS")==null?"":(rs.getString("AUDIT_STATUS").equals("2")?"未审核":(rs.getString("AUDIT_STATUS").equals("0")?"审核通过":"审核不通过")));
				v.setOwn_tel(rs.getString("AUDIT_TIME")==null?"":rs.getString("AUDIT_TIME").substring(0, 19));
				v.setRun_times(rs.getString("REASON")==null?"":rs.getString("REASON"));
				v.setOwner_id(rs.getString("REGISTER_DATE")==null?"":rs.getString("REGISTER_DATE"));
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
	//增加车辆转入申请
	public String addvehicleapplication(String vehicle_no, String compname, String time, String type){
		List<Vhic>list=new ArrayList<Vhic>();
		String[] a=vehicle_no.split("\\(");
		String old_company=a[1].replace(")", "");
		String msg="";
		String cx="select vehicle_no from tb_vehicle_application@TAXILINK69  where vehicle_no='"+a[0]+"' and audit_status ='2' and JOIN_TURNOUT ='"+type+"'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(cx);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setVehi_no(rs.getString("vehicle_no"));
				list.add(v);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(list.size()>0){
			msg="该车辆已申请";
			return msg;
		}else{
			int count=0;
			String sql="insert into tb_vehicle_application@TAXILINK69 (VEHICLE_NO,OLD_COMPANY,NEW_COMPANY,APPLICATION_TIME,AUDIT_STATUS,JOIN_TIME,JOIN_TURNOUT)" +
					"values ('"+a[0]+"','"+old_company+"','"+compname+"',sysdate,'2',to_date('"+time+"','yyyy-MM-dd HH24:mi:ss'),'"+type+"')";
			try {
				Connection con=DataBese.getConnectionOfOracle();
				Statement st=con.createStatement();
				count=st.executeUpdate(sql);
				st.close();
				con.commit();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count>0){
				msg="添加成功";
			}else{
				msg="添加失败";
			}
		}
		return msg;
	}
	//修改车辆转入申请
	public String editvehicleapplication(String vehicle_no, String compname, String time, String type, String id){
		List<Vhic>list=new ArrayList<Vhic>();
		String[] a=vehicle_no.split("\\(");
		String old_company=a[1].replace(")", "");
		String msg="";
		String cx="select vehicle_no from tb_vehicle_application@TAXILINK69  where vehicle_no='"+a[0]+"' and audit_status ='2' and JOIN_TURNOUT ='"+type+"' and id !='"+id+"'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(cx);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setVehi_no(rs.getString("vehicle_no"));
				list.add(v);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(list.size()>0){
			msg="该车辆已申请";
			return msg;
		}else{
			int count=0;
			String sql="update tb_vehicle_application@TAXILINK69 set VEHICLE_NO='"+a[0]+"', OLD_COMPANY='"+old_company+"',"
					+ " NEW_COMPANY='"+compname+"',APPLICATION_TIME=sysdate, AUDIT_STATUS='2',JOIN_TIME=to_date('"+time+"','yyyy-MM-dd HH24:mi:ss'), JOIN_TURNOUT='"+type+"' where id='"+id+"'";
			try {
				Connection con=DataBese.getConnectionOfOracle();
				Statement st=con.createStatement();
				count=st.executeUpdate(sql);
				st.close();
				con.commit();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count>0){
				msg="修改成功";
			}else{
				msg="修改失败";
			}
		}
		return msg;
	}
	//删除车辆转入申请
	public String deletevehicleapplication(String id){
		int count=0;
		String msg="";
		String sql="delete from tb_vehicle_application@TAXILINK69 where id='"+id+"'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(count>0){
			msg="删除成功";
		}else{
			msg="删除失败";
		}
		return msg;
	}
	//查询车辆报停申请
	public List<Vhic>findvehiclestop(String stime,String etime,String vehicle_no){
		List<Vhic>list=new ArrayList<Vhic>();
		
		String sql="select a.*,v.REGISTER_DATE from tb_vehicle_stop@TAXILINK69 a"
				+ " left join tb_vehicle@TAXILINK69 v on v.vehi_no=a.vehicle_no where 1=1";
		if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0){
			sql+=" and a.stop_time >=to_date('"+stime+" 00:00:00','yyyy-MM-dd HH24:mi:ss')";
		}
		if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0){
			sql+=" and a.stop_time <=to_date('"+etime+" 23:59:59','yyyy-MM-dd HH24:mi:ss')";
		}
		if(vehicle_no!=null&&!vehicle_no.isEmpty()&&!vehicle_no.equals("null")&&vehicle_no.length()>0){
			sql+=" and a.vehicle_no ='"+vehicle_no+"'";
		}
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
				v.setComp_id(rs.getString("ID")==null?"":rs.getString("ID"));
				v.setComp_name(rs.getString("COMPANY_NAME")==null?"":rs.getString("COMPANY_NAME"));
				v.setVehi_no(rs.getString("VEHICLE_NO")==null?"":rs.getString("VEHICLE_NO"));
				v.setOwn_name(rs.getString("STOP_TIME")==null?"":rs.getString("STOP_TIME").substring(0, 19));
				v.setBa_name(rs.getString("STOP_REASON")==null?"":rs.getString("STOP_REASON"));
				v.setTime(rs.getString("UP_TIME")==null?"":rs.getString("UP_TIME").substring(0, 19));
				v.setVehi_sim(rs.getString("AUDIT_STATUS")==null?"":(rs.getString("AUDIT_STATUS").equals("2")?"未审核":(rs.getString("AUDIT_STATUS").equals("0")?"审核通过":"审核不通过")));
				v.setOwn_tel(rs.getString("AUDIT_TIME")==null?"":rs.getString("AUDIT_TIME").substring(0, 19));
				v.setRun_times(rs.getString("AUDIT_REASON")==null?"":rs.getString("AUDIT_REASON"));
				v.setOwner_id(rs.getString("REGISTER_DATE")==null?"":rs.getString("REGISTER_DATE"));
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
	//增加车辆报停申请
	public String addvehiclestop(String vehicle_no, String reason, String time){
		List<Vhic>list=new ArrayList<Vhic>();
		String[] a=vehicle_no.split("\\(");
		String company=a[1].replace(")", "");
		String msg="";
		String cx="select vehicle_no from tb_vehicle_stop@TAXILINK69  where vehicle_no='"+a[0]+"' and audit_status ='2'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(cx);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setVehi_no(rs.getString("vehicle_no"));
				list.add(v);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(list.size()>0){
			msg="该车辆已申请";
			return msg;
		}else{
			int count=0;
			String sql="insert into tb_vehicle_stop@TAXILINK69 (VEHICLE_NO,COMPANY_NAME,STOP_REASON,STOP_TIME,UP_TIME,AUDIT_STATUS)" +
					"values ('"+a[0]+"','"+company+"','"+reason+"',to_date('"+time+"','yyyy-MM-dd HH24:mi:ss'),sysdate,'2')";
			try {
				Connection con=DataBese.getConnectionOfOracle();
				Statement st=con.createStatement();
				count=st.executeUpdate(sql);
				st.close();
				con.commit();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count>0){
				msg="添加成功";
			}else{
				msg="添加失败";
			}
		}
		return msg;
	}
	//修改车辆报停申请
	public String editvehiclestop(String vehicle_no, String reason, String time, String id){
		List<Vhic>list=new ArrayList<Vhic>();
		String[] a=vehicle_no.split("\\(");
		String company=a[1].replace(")", "");
		String msg="";
		String cx="select vehicle_no from tb_vehicle_stop@TAXILINK69  where vehicle_no='"+a[0]+"' and audit_status ='2' and id !='"+id+"'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(cx);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setVehi_no(rs.getString("vehicle_no"));
				list.add(v);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(list.size()>0){
			msg="该车辆已申请";
			return msg;
		}else{
			int count=0;
			String sql="update tb_vehicle_stop@TAXILINK69 set VEHICLE_NO='"+a[0]+"', COMPANY_NAME='"+company+"',"
					+ " STOP_REASON='"+reason+"',STOP_TIME=to_date('"+time+"','yyyy-MM-dd HH24:mi:ss'),UP_TIME=sysdate, AUDIT_STATUS='2' where id='"+id+"'";
			try {
				Connection con=DataBese.getConnectionOfOracle();
				Statement st=con.createStatement();
				count=st.executeUpdate(sql);
				st.close();
				con.commit();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count>0){
				msg="修改成功";
			}else{
				msg="修改失败";
			}
		}
		return msg;
	}
	//删除车辆报停申请
	public String deletevehiclestop(String id){
		int count=0;
		String msg="";
		String sql="delete from tb_vehicle_stop@TAXILINK69 where id='"+id+"'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(count>0){
			msg="删除成功";
		}else{
			msg="删除失败";
		}
		return msg;
	}
	//查询车牌号变更申请
	public List<Vhic>findvehiclechange(String stime,String etime,String vehicle_no, String new_vehicle){
		List<Vhic>list=new ArrayList<Vhic>();
		
		String sql="select a.*,v.REGISTER_DATE from tb_vehicle_change@TAXILINK69 a"
				+ " left join tb_vehicle@TAXILINK69 v on v.vehi_no=a.OLD_VEHICLE_NO where 1=1";
		if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0){
			sql+=" and a.APPLY_TIME >=to_date('"+stime+" 00:00:00','yyyy-MM-dd HH24:mi:ss')";
		}
		if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0){
			sql+=" and a.APPLY_TIME <=to_date('"+etime+" 23:59:59','yyyy-MM-dd HH24:mi:ss')";
		}
		if(vehicle_no!=null&&!vehicle_no.isEmpty()&&!vehicle_no.equals("null")&&vehicle_no.length()>0){
			sql+=" and a.OLD_VEHICLE_NO ='"+vehicle_no+"'";
		}
		if(new_vehicle!=null&&!new_vehicle.isEmpty()&&!new_vehicle.equals("null")&&new_vehicle.length()>0){
			sql+=" and a.NEW_VEHICLE_NO ='"+new_vehicle+"'";
		}
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
				v.setComp_id(rs.getString("ID")==null?"":rs.getString("ID"));
				v.setVehi_no(rs.getString("OLD_VEHICLE_NO")==null?"":rs.getString("OLD_VEHICLE_NO"));
				v.setComp_name(rs.getString("NEW_VEHICLE_NO")==null?"":rs.getString("NEW_VEHICLE_NO"));
				v.setOwn_name(rs.getString("APPLY_TIME")==null?"":rs.getString("APPLY_TIME").substring(0, 19));
				v.setVehi_sim(rs.getString("AUDIT_STATUS")==null?"":(rs.getString("AUDIT_STATUS").equals("2")?"未审核":(rs.getString("AUDIT_STATUS").equals("0")?"审核通过":"审核不通过")));
				v.setOwn_tel(rs.getString("AUDIT_TIME")==null?"":rs.getString("AUDIT_TIME").substring(0, 19));
				v.setRun_times(rs.getString("AUDIT_REASON")==null?"":rs.getString("AUDIT_REASON"));
				v.setOwner_id(rs.getString("REGISTER_DATE")==null?"":rs.getString("REGISTER_DATE"));
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
	//增加车牌号变更申请
	public String addvehiclechange(String vehicle_no, String new_vehicle){
		List<Vhic>list=new ArrayList<Vhic>();
		String msg="";
		String cx="select old_vehicle_no from tb_vehicle_change@TAXILINK69  where old_vehicle_no='"+vehicle_no+"' and audit_status ='2'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(cx);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setVehi_no(rs.getString("old_vehicle_no"));
				list.add(v);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(list.size()>0){
			msg="该车辆已申请";
			return msg;
		}else{
			int count=0;
			String sql="insert into tb_vehicle_change@TAXILINK69 (OLD_VEHICLE_NO,NEW_VEHICLE_NO,APPLY_TIME,AUDIT_STATUS)" +
					"values ('"+vehicle_no+"','"+new_vehicle+"',sysdate,'2')";
			try {
				Connection con=DataBese.getConnectionOfOracle();
				Statement st=con.createStatement();
				count=st.executeUpdate(sql);
				st.close();
				con.commit();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count>0){
				msg="添加成功";
			}else{
				msg="添加失败";
			}
		}
		return msg;
	}
	//修改车牌号变更申请
	public String editvehiclechange(String vehicle_no, String new_vehicle, String id){
		List<Vhic>list=new ArrayList<Vhic>();
		String msg="";
		String cx="select old_vehicle_no from tb_vehicle_change@TAXILINK69  where old_vehicle_no='"+vehicle_no+"' and audit_status ='2' and id!='"+id+"'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(cx);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setVehi_no(rs.getString("old_vehicle_no"));
				list.add(v);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(list.size()>0){
			msg="该车辆已申请";
			return msg;
		}else{
			int count=0;
			String sql="update tb_vehicle_change@TAXILINK69 set OLD_VEHICLE_NO='"+vehicle_no+"', NEW_VEHICLE_NO='"+new_vehicle+"',"
					+ " APPLY_TIME=sysdate, AUDIT_STATUS='2' where id='"+id+"'";
			try {
				Connection con=DataBese.getConnectionOfOracle();
				Statement st=con.createStatement();
				count=st.executeUpdate(sql);
				st.close();
				con.commit();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count>0){
				msg="修改成功";
			}else{
				msg="修改失败";
			}
		}
		return msg;
	}
	//删除车牌号变更申请
	public String deletevehiclechange(String id){
		int count=0;
		String msg="";
		String sql="delete from tb_vehicle_change@TAXILINK69 where id='"+id+"'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(count>0){
			msg="删除成功";
		}else{
			msg="删除失败";
		}
		return msg;
	}
	//查询新车牌号
	public List<Vhic>findnewvehicle(){
		List<Vhic>list=new ArrayList<Vhic>();
		
		String sql="select distinct NEW_VEHICLE_NO from tb_vehicle_change@TAXILINK69 where NEW_VEHICLE_NO is not null";
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
				v.setVehi_no(rs.getString("NEW_VEHICLE_NO")==null?"":rs.getString("NEW_VEHICLE_NO"));
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
	public List<RepairOrder> findrepairorder(String stime, String etime, String vehicle_no) {
		List<RepairOrder>list=new ArrayList<RepairOrder>();
		String sql = "select distinct al.*,v.COMP_NAME,g.AREA_NAME from tb_taxi_gzfx_examine@TAXILINK69 al" +
                " left join vw_vehicle@TAXILINK69 v on al.vehicle_no=v.vehi_no" +
                " left join  (select plate_number,area_name from tb_global_vehicle@TAXILINK69 where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g on al.vehicle_no=g.plate_number " +
                " where 1=1";
		if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0){
			sql+=" and al.fault_time >=to_date('"+stime+" 00:00:00','yyyy-MM-dd HH24:mi:ss')";
		}
		if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0){
			sql+=" and al.fault_time <=to_date('"+etime+" 23:59:59','yyyy-MM-dd HH24:mi:ss')";
		}
		if(vehicle_no!=null&&!vehicle_no.isEmpty()&&!vehicle_no.equals("null")&&vehicle_no.length()>0){
			sql+=" and al.vehicle_no ='"+vehicle_no+"'";
		}
		sql +=" order by al.fault_time desc";
		System.out.println(sql);
		Connection con;
		PreparedStatement ps;
		ResultSet rs;
		try {
			con=DataBese.getConnectionOfOracle();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				RepairOrder v=new RepairOrder();
				v.setComp_name(rs.getString("COMP_NAME")==null?"":rs.getString("COMP_NAME"));
				v.setVehicle_no(rs.getString("VEHICLE_NO")==null?"":rs.getString("VEHICLE_NO"));
				v.setArea_name(rs.getString("AREA_NAME")==null?"":rs.getString("AREA_NAME"));
				v.setFault_type(rs.getString("FAULT_TYPE")==null?"":rs.getString("FAULT_TYPE"));
				v.setFault_time(rs.getString("FAULT_TIME")==null?"":rs.getString("FAULT_TIME").substring(0, 19));
				v.setOnce_time(rs.getString("ONCE_TIME")==null?"":rs.getString("ONCE_TIME").substring(0, 19));
				v.setTwice_time(rs.getString("TWICE_TIME")==null?"":rs.getString("TWICE_TIME").substring(0, 19));
				v.setThird_time(rs.getString("THIRD_TIME")==null?"":rs.getString("THIRD_TIME").substring(0, 19));
				v.setRepair_time(rs.getString("REPAIR_TIME")==null?"":rs.getString("REPAIR_TIME").substring(0, 19));
				v.setFeedback_time(rs.getString("WD_FEEDBACK_TIME")==null?"":rs.getString("WD_FEEDBACK_TIME").substring(0, 19));
				v.setIs_repair(rs.getString("IS_REPAIR")==null?"":(rs.getString("IS_REPAIR").toString().equals("0")?"已维修":(rs.getString("IS_REPAIR").toString().equals("1")?"未维修":"")));
				v.setRepair_result(rs.getString("REPAIR_RESULT")==null?"":rs.getString("REPAIR_RESULT"));
				v.setDescription(rs.getString("DESCRIPTION")==null?"":rs.getString("DESCRIPTION"));
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


	//燃料类型
	public List<TbDataApplication> findfueltype() {
		List<TbDataApplication> list = new ArrayList<TbDataApplication>();
		String sql = "select distinct rllx from vw_vehicle@TAXILINK69 where rllx is not null order by rllx";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				TbDataApplication c = new TbDataApplication();
				c.setFuelType(rs.getString("rllx"));
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

	//终端类型
	public List<TbDataApplication> findterminaltype() {
		List<TbDataApplication> list = new ArrayList<TbDataApplication>();
		String sql = "select distinct mt_name from vw_vehicle@TAXILINK69 where mt_name is not null order by mt_name";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				TbDataApplication c = new TbDataApplication();
				c.setTerminalModel(rs.getString("mt_name"));
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

	//车辆类型
	public List<TbDataApplication> findvehicletype() {
		List<TbDataApplication> list = new ArrayList<TbDataApplication>();
		String sql = "select distinct vt_name from vw_vehicle@TAXILINK69 where vt_name is not null order by vt_name";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				TbDataApplication c = new TbDataApplication();
				c.setVehicleType(rs.getString("vt_name"));
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

	public List<TbDataApplication> finddataapplication(String stime, String etime, String vehicleNo, String companyName) {
		List<TbDataApplication>list=new ArrayList<TbDataApplication>();
		String sql = "select al.*, u.REAL_NAME from TB_DATA_APPLICATION@TAXILINK69 al " +
				"  left join tb_user@TAXILINK69 u on al.audit_person=u.user_id" +
				" where 1=1";
		if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0){
			sql+=" and al.application_date >=to_date('"+stime+" 00:00:00','yyyy-MM-dd HH24:mi:ss')";
		}
		if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0){
			sql+=" and al.application_date <=to_date('"+etime+" 23:59:59','yyyy-MM-dd HH24:mi:ss')";
		}
		if(vehicleNo!=null&&!vehicleNo.isEmpty()&&!vehicleNo.equals("null")&&vehicleNo.length()>0){
			sql+=" and al.vehicle_no ='"+vehicleNo+"'";
		}
		if(companyName!=null&&!companyName.isEmpty()&&!companyName.equals("null")&&companyName.length()>0){
			sql+=" and al.company_name ='"+companyName+"'";
		}
		sql +=" order by al.application_date desc";
		System.out.println(sql);
		Connection con;
		PreparedStatement ps;
		ResultSet rs;
		try {
			con=DataBese.getConnectionOfOracle();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				TbDataApplication v=new TbDataApplication();
				v.setId(rs.getString("ID")==null?"":rs.getString("ID"));
				v.setVehicleNo(rs.getString("VEHICLE_NO")==null?"":rs.getString("VEHICLE_NO"));
				v.setVehicleColor(rs.getString("VEHICLE_COLOR")==null?"":rs.getString("VEHICLE_COLOR"));
				v.setVehicleType(rs.getString("VEHICLE_TYPE")==null?"":rs.getString("VEHICLE_TYPE"));
				v.setFuelType(rs.getString("FUEL_TYPE")==null?"":rs.getString("FUEL_TYPE"));
				v.setTerminalType(rs.getString("TERMINAL_TYPE")==null?"":rs.getString("TERMINAL_TYPE"));
				v.setTerminalModel(rs.getString("TERMINAL_MODEL")==null?"":rs.getString("TERMINAL_MODEL"));
				v.setOwnerName(rs.getString("OWNER_NAME")==null?"":rs.getString("OWNER_NAME"));
				v.setOwnerPhone(rs.getString("OWNER_PHONE")==null?"":rs.getString("OWNER_PHONE"));
				v.setDayPerson(rs.getString("DAY_PERSON")==null?"":rs.getString("DAY_PERSON"));
				v.setNightPerson(rs.getString("NIGHT_PERSON")==null?"":rs.getString("NIGHT_PERSON"));
				v.setDayPhone(rs.getString("DAY_PHONE")==null?"":rs.getString("DAY_PHONE"));
				v.setNightPhone(rs.getString("NIGHT_PHONE")==null?"":rs.getString("NIGHT_PHONE"));
				v.setApplicationDate(rs.getString("APPLICATION_DATE")==null?"":rs.getString("APPLICATION_DATE").substring(0, 19));
				v.setAuditDate(rs.getString("AUDIT_DATE")==null?"":rs.getString("AUDIT_DATE").substring(0, 19));
				v.setAuditPerson(rs.getString("REAL_NAME")==null?"":rs.getString("REAL_NAME"));
				v.setAuditReason(rs.getString("AUDIT_REASON")==null?"":rs.getString("AUDIT_REASON"));
				v.setCompanyName(rs.getString("COMPANY_NAME")==null?"":rs.getString("COMPANY_NAME"));
				v.setAuditStatus(rs.getString("AUDIT_STATUS")==null?"":(rs.getString("AUDIT_STATUS").equals("2")?"未审核":(rs.getString("AUDIT_STATUS").equals("0")?"审核通过":"审核不通过")));
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

	public String adddataapplication(String vehicleNo, String vehicleColor, String vehicleType, String fuelType, String terminalType, String terminalModel, String ownerName, String ownerPhone, String dayPerson, String nightPerson, String dayPhone, String nightPhone, String applicationDate, String auditDate, String auditPerson, String auditReason, String companyName, String auditStatus) {
		List<TbDataApplication>list=new ArrayList<TbDataApplication>();
		String msg="";
		String cx="select vehicle_no from TB_DATA_APPLICATION@TAXILINK69  where vehicle_no='"+vehicleNo+"' and audit_status ='2'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(cx);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				TbDataApplication v=new TbDataApplication();
				v.setVehicleNo(rs.getString("vehicle_no"));
				list.add(v);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(list.size()>0){
			msg="该车辆已申请";
			return msg;
		}else{
			int count=0;
			String sql="insert into TB_DATA_APPLICATION@TAXILINK69" +
					" (VEHICLE_NO, VEHICLE_COLOR, VEHICLE_TYPE, FUEL_TYPE, TERMINAL_TYPE, TERMINAL_MODEL, OWNER_NAME" +
					", OWNER_PHONE, DAY_PERSON, NIGHT_PERSON, DAY_PHONE, NIGHT_PHONE, APPLICATION_DATE, COMPANY_NAME) " +
					"values ('"+vehicleNo+"','"+vehicleColor+"','"+vehicleType+"','"+fuelType+"','"+terminalType+"','"+terminalModel+"','"+ownerName+"'," +
					"'"+ownerPhone+"','"+dayPerson+"','"+nightPerson+"','"+dayPhone+"','"+nightPhone+"',sysdate,'"+companyName+"')";
			try {
				Connection con=DataBese.getConnectionOfOracle();
				Statement st=con.createStatement();
				count=st.executeUpdate(sql);
				st.close();
				con.commit();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count>0){
				msg="添加成功";
			}else{
				msg="添加失败";
			}
		}
		return msg;
	}

	public String editdataapplication(String vehicleNo, String vehicleColor, String vehicleType, String fuelType, String terminalType, String terminalModel, String ownerName, String ownerPhone, String dayPerson, String nightPerson, String dayPhone, String nightPhone, String applicationDate, String auditDate, String auditPerson, String auditReason, String companyName, String auditStatus, String id) {
		List<TbDataApplication>list=new ArrayList<TbDataApplication>();
		String msg="";
		String cx="select vehicle_no from TB_DATA_APPLICATION@TAXILINK69  where vehicle_no='"+vehicleNo+"' and audit_status =2 and id!='"+id+"'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(cx);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				TbDataApplication v=new TbDataApplication();
				v.setVehicleNo(rs.getString("vehicle_no"));
				list.add(v);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(list.size()>0){
			msg="该车辆已申请";
			return msg;
		}else{
			int count=0;
			String sql="update TB_DATA_APPLICATION@TAXILINK69 set VEHICLE_NO='"+vehicleNo+"', VEHICLE_COLOR='"+vehicleColor+"', VEHICLE_TYPE='"+vehicleType+"'" +
					" ,FUEL_TYPE='"+fuelType+"', TERMINAL_TYPE='"+terminalType+"', TERMINAL_MODEL='"+terminalModel+"', OWNER_NAME='"+ownerName+"'" +
					" ,OWNER_PHONE='"+ownerPhone+"', DAY_PERSON='"+dayPerson+"', NIGHT_PERSON='"+nightPerson+"', DAY_PHONE='"+dayPhone+"'" +
					" ,NIGHT_PHONE='"+nightPhone+"', APPLICATION_DATE= sysdate, COMPANY_NAME='"+companyName+"'" +
					", AUDIT_STATUS=2, AUDIT_PERSON='', AUDIT_REASON='', AUDIT_DATE=''" +
					" where id='"+id+"'";
			try {
				Connection con=DataBese.getConnectionOfOracle();
				Statement st=con.createStatement();
				count=st.executeUpdate(sql);
				st.close();
				con.commit();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count>0){
				msg="修改成功";
			}else{
				msg="修改失败";
			}
		}
		return msg;
	}

	public String deletedataapplication(String id) {
		int count=0;
		String msg="";
		String sql="delete from TB_DATA_APPLICATION@TAXILINK69 where id='"+id+"'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(count>0){
			msg="删除成功";
		}else{
			msg="删除失败";
		}
		return msg;
	}
}
