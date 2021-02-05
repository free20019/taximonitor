package com.tw.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.tw.entity.LOADONLINE;
import com.tw.util.DataBese;

public class SSlichengDao {
	private Date d=new Date();
	private SimpleDateFormat sdformat =new SimpleDateFormat("yyyy");
	private String time=sdformat.format(d);
	public double findmeiyou(int i){
		int count=0;
		String stime="",etime="";
		if (i==0) {
			 Calendar calendar = Calendar.getInstance();
		     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 6);
		     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     stime= df.format(calendar.getTime()).substring(0, 14)+"00:00";
	        Calendar calendar1 = Calendar.getInstance();
	        calendar1.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY) - 5);
	        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        etime= df1.format(calendar1.getTime()).substring(0, 14)+"00:00";
		}else if (i==1) {
			Calendar calendar = Calendar.getInstance();
		     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 5);
		     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     stime= df.format(calendar.getTime()).substring(0, 14)+"00:00";
	        Calendar calendar1 = Calendar.getInstance();
	        calendar1.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY) - 4);
	        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        etime= df1.format(calendar1.getTime()).substring(0, 14)+"00:00";
		}else if (i==2) {
			Calendar calendar = Calendar.getInstance();
		     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 4);
		     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     stime= df.format(calendar.getTime()).substring(0, 14)+"00:00";
	        Calendar calendar1 = Calendar.getInstance();
	        calendar1.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY) - 3);
	        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        etime= df1.format(calendar1.getTime()).substring(0, 14)+"00:00";
		}else if (i==3) {
			Calendar calendar = Calendar.getInstance();
		     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 3);
		     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     stime= df.format(calendar.getTime()).substring(0, 14)+"00:00";
	        Calendar calendar1 = Calendar.getInstance();
	        calendar1.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY) - 2);
	        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        etime= df1.format(calendar1.getTime()).substring(0, 14)+"00:00";
		}else if (i==4) {
			Calendar calendar = Calendar.getInstance();
		     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 2);
		     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     stime= df.format(calendar.getTime()).substring(0, 14)+"00:00";
	        Calendar calendar1 = Calendar.getInstance();
	        calendar1.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY) - 1);
	        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        etime= df1.format(calendar1.getTime()).substring(0, 14)+"00:00";
		}else if (i==5) {
			Calendar calendar = Calendar.getInstance();
		     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     stime= df.format(calendar.getTime()).substring(0, 14)+"00:00";
		     Date d=new Date();
	        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        etime= df1.format(d).substring(0, 14)+"00:00";
		}
		String sql="select sum(jicheng) jicheng from HZGPS_CITIZEN.TB_CITIZEN_"+time+"@TAXILINK45 t" +
				" where shangche>=to_date('"+stime+"','yyyy-MM-dd hh24:mi:ss')" +
				" and  shangche<to_date('"+etime+"','yyyy-MM-dd hh24:mi:ss')";
		double totalpage=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection con=null;
		int a=vhicno(i, stime, etime);
		try {
			con=DataBese.getConnectionOfOracle();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			if (rs.next()) {
				totalpage=rs.getDouble("jicheng")/a;
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalpage;
	}
	public int vhicno(int i,String stime,String etime){
		int count=0;
		String sql="select count(distinct(vhic)) c from HZGPS_CITIZEN.TB_CITIZEN_"+time+"@TAXILINK45 t" +
				" where shangche>=to_date('"+stime+"','yyyy-MM-dd hh24:mi:ss')" +
				" and  shangche<to_date('"+etime+"','yyyy-MM-dd hh24:mi:ss')";
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			if (rs.next()) {
				count=rs.getInt("c");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public List<LOADONLINE>findaverage(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(time));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        calendar.add(Calendar.DATE, -7);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date    sTime = calendar.getTime();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date    eTime = calendar.getTime();
        String st = sdf.format(sTime) + " 00:00:00";
        String et = sdf.format(eTime) + " 23:59:59";
    
		List<LOADONLINE>list=new ArrayList<LOADONLINE>();
		String sql="select * from hz_taxi.TB_TAXI_RUN_INFO_RECORD_test t where db_time>=to_date('"+st+"','yyyy-MM-dd HH24:mi:ss')" +
				" and db_time<=to_date('"+et+"','yyyy-MM-dd HH24:mi:ss')";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				LOADONLINE l=new LOADONLINE();
				l.setDb_time(rs.getString("db_time"));
				l.setActual_load_mileage(rs.getString("actual_load_mileage"));
				list.add(l);
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOADONLINE zhuanhuan=zhuanhuan(list);
		List<LOADONLINE>list1=new ArrayList<LOADONLINE>();
		list1.add(zhuanhuan);
		return list1;
	}
	public LOADONLINE zhuanhuan(List<LOADONLINE> list){
		LOADONLINE l=new LOADONLINE();
		List<String>yingyun1=new ArrayList<String>();
		List<String>yingyun2=new ArrayList<String>();
		List<String>yingyun3=new ArrayList<String>();
		List<String>yingyun4=new ArrayList<String>();
		List<String>yingyun5=new ArrayList<String>();
		List<String>yingyun6=new ArrayList<String>();
		List<String>yingyun7=new ArrayList<String>();
		List<String>yingyun8=new ArrayList<String>();
		List<String>yingyun9=new ArrayList<String>();
		List<String>yingyun10=new ArrayList<String>();
		List<String>yingyun11=new ArrayList<String>();
		List<String>yingyun12=new ArrayList<String>();
		List<String>yingyun13=new ArrayList<String>();
		List<String>yingyun14=new ArrayList<String>();
		List<String>yingyun15=new ArrayList<String>();
		List<String>yingyun16=new ArrayList<String>();
		List<String>yingyun17=new ArrayList<String>();
		List<String>yingyun18=new ArrayList<String>();
		List<String>yingyun19=new ArrayList<String>();
		List<String>yingyun20=new ArrayList<String>();
		List<String>yingyun21=new ArrayList<String>();
		List<String>yingyun22=new ArrayList<String>();
		List<String>yingyun23=new ArrayList<String>();
		List<String>yingyun24=new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getDb_time().length()==10||list.get(i).getDb_time().substring(11,16).equals("00:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun1.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("01:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun2.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("02:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun3.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("03:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun4.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("04:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun5.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("05:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun6.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("06:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun7.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("07:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun8.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("08:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun9.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("09:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun10.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("10:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun11.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("11:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun12.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("12:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun13.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("13:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun14.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("14:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun15.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("15:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun16.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("16:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun17.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("17:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun18.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("18:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun19.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("19:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun20.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("20:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun21.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("21:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun22.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("22:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun23.add(list.get(i).getActual_load_mileage());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("23:00")) {
				if (list.get(i).getActual_load_mileage()!=null&&list.get(i).getActual_load_mileage().length()>0&&list.get(i).getActual_load_mileage()!="0") {
					yingyun24.add(list.get(i).getActual_load_mileage());
				}
			}
		}
		List<String >pingjunyingyun=new ArrayList<String>();
		double b=0.0;
		b=0;
		if (yingyun1.size()>0) {
			for (int j = 0; j < yingyun1.size(); j++) {
					b+=Double.parseDouble(yingyun1.get(j));
			}
			BigDecimal bg = new BigDecimal(b/yingyun1.size());
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(0, f1+"");
		}else {
			pingjunyingyun.add(0, 0+"");
		}
		b=0.0;
		if (yingyun2.size()>0) {
			
			for (int j = 0; j < yingyun2.size(); j++) {
					b+=Double.parseDouble(yingyun2.get(j));
			}
			BigDecimal bg = new BigDecimal(b/yingyun2.size());
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(1, f1+"");
		}else {
			pingjunyingyun.add(1, 0+"");
		}
		b=0.0;
		if (yingyun3.size()>0) {
			
			for (int j = 0; j < yingyun3.size(); j++) {
					b+=Double.parseDouble(yingyun3.get(j));
			}
			BigDecimal bg = new BigDecimal(b/yingyun3.size());
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(2, f1+"");
		}else {
			pingjunyingyun.add(2, 0+"");
		}
		b=0.0;
		if (yingyun4.size()>0) {
			
			for (int j = 0; j < yingyun4.size(); j++) {
					b+=Double.parseDouble(yingyun4.get(j));
			}
			BigDecimal bg = new BigDecimal(b/yingyun4.size());
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(3, f1+"");
		}else {
			pingjunyingyun.add(3, 0+"");
		}
		b=0.0;
		if (yingyun5.size()>0) {
			for (int j = 0; j < yingyun5.size(); j++) {
					b+=Double.parseDouble(yingyun5.get(j));
			}
			BigDecimal bg = new BigDecimal(b/yingyun5.size());
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(4, f1+"");
		}else {
			pingjunyingyun.add(4, 0+"");
		}
		b=0.0;
		if (yingyun6.size()>0) {
			
			for (int j = 0; j < yingyun6.size(); j++) {
					b+=Double.parseDouble(yingyun6.get(j));
			}
			BigDecimal bg = new BigDecimal(b/yingyun6.size());
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(5, f1+"");
		}else{
			pingjunyingyun.add(5, 0+"");
		}
			
		b=0.0;
		if (yingyun7.size()>0) {
			
			for (int j = 0; j < yingyun7.size(); j++) {
					b+=Double.parseDouble(yingyun7.get(j));
			}
			BigDecimal bg = new BigDecimal(b/yingyun7.size());
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(6, f1+"");
		}else {
			pingjunyingyun.add(6, 0+"");
		}
		b=0.0;
		if (yingyun8.size()>0) {
			
			for (int j = 0; j < yingyun8.size(); j++) {
					b+=Double.parseDouble(yingyun8.get(j));
			}
			BigDecimal bg = new BigDecimal(b/yingyun8.size());
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(7, f1+"");
		}else {
			pingjunyingyun.add(7, 0+"");
		}
		b=0.0;
		if (yingyun9.size()>0) {
			
			for (int j = 0; j < yingyun9.size(); j++) {
					b+=Double.parseDouble(yingyun9.get(j));
			}
			BigDecimal bg = new BigDecimal(b/yingyun9.size());
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(8, f1+"");
		}else {
			pingjunyingyun.add(8, 0+"");
		}
		b=0.0;
		if (yingyun10.size()>0) {
			
			for (int j = 0; j < yingyun10.size(); j++) {
					b+=Double.parseDouble(yingyun10.get(j));
			}
			BigDecimal bg = new BigDecimal(b/yingyun10.size());
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(9, f1+"");
		}else {
			pingjunyingyun.add(9,0+"");
		}
		b=0.0;
		if (yingyun11.size()>0) {
			
			for (int j = 0; j < yingyun11.size(); j++) {
					b+=Double.parseDouble(yingyun11.get(j));
			}
			BigDecimal bg = new BigDecimal(b/yingyun11.size());
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(10, f1+"");
		}else {
			pingjunyingyun.add(10, 0+"");

		}
		b=0.0;
		if (yingyun12.size()>0) {
			
			for (int j = 0; j < yingyun12.size(); j++) {
					b+=Double.parseDouble(yingyun12.get(j));
			}
			BigDecimal bg = new BigDecimal(b/yingyun12.size());
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(11, f1+"");
		}else {
			pingjunyingyun.add(11, 0+"");
		}
		b=0.0;
		if (yingyun13.size()>0) {
			for (int j = 0; j < yingyun13.size(); j++) {
					b+=Double.parseDouble(yingyun13.get(j));
			}
			BigDecimal bg = new BigDecimal(b/yingyun13.size());
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(12, f1+"");
			
		}else{
			pingjunyingyun.add(12, 0+"");
		}
		b=0.0;
		if (yingyun14.size()>0) {
			
			for (int j = 0; j < yingyun14.size(); j++) {
					b+=Double.parseDouble(yingyun14.get(j));
			}
			BigDecimal bg = new BigDecimal(b/yingyun14.size());
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(13, f1+"");
		}else {
			pingjunyingyun.add(13, 0+"");
		}
		b=0.0;
		if (yingyun15.size()>0) {
			
			for (int j = 0; j < yingyun15.size(); j++) {
					b+=Double.parseDouble(yingyun15.get(j));
			}
			BigDecimal bg = new BigDecimal(b/yingyun15.size());
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(14, f1+"");
		}else {
			pingjunyingyun.add(14, 0+"");
		}
		b=0.0;
		if (yingyun16.size()>0) {
			for (int j = 0; j < yingyun16.size(); j++) {
					b+=Double.parseDouble(yingyun16.get(j));
			}
			BigDecimal bg = new BigDecimal(b/yingyun16.size());
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(15, f1+"");
		}else {
			pingjunyingyun.add(15, 0+"");
		}
		b=0.0;
		if (yingyun17.size()>0) {
		for (int j = 0; j < yingyun17.size(); j++) {
				b+=Double.parseDouble(yingyun17.get(j));
		}
		BigDecimal bg = new BigDecimal(b/yingyun17.size());
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		pingjunyingyun.add(16, f1+"");
		}else {
			pingjunyingyun.add(16, 0+"");
		}
		b=0.0;
		if (yingyun18.size()>0) {
		for (int j = 0; j < yingyun18.size(); j++) {
				b+=Double.parseDouble(yingyun18.get(j));
		}
		BigDecimal bg = new BigDecimal(b/yingyun18.size());
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		pingjunyingyun.add(17, f1+"");
		}else {
			pingjunyingyun.add(17, 0+"");
		}
		b=0.0;
		if (yingyun19.size()>0) {
		for (int j = 0; j < yingyun19.size(); j++) {
				b+=Double.parseDouble(yingyun19.get(j));
		}
		BigDecimal bg = new BigDecimal(b/yingyun19.size());
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		pingjunyingyun.add(18, f1+"");
		}else {
			pingjunyingyun.add(18, 0+"");
		}
		b=0.0;
		if (yingyun20.size()>0) {
		for (int j = 0; j < yingyun20.size(); j++) {
				b+=Double.parseDouble(yingyun20.get(j));
		}
		BigDecimal bg = new BigDecimal(b/yingyun20.size());
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		pingjunyingyun.add(19, f1+"");
		}else {
			pingjunyingyun.add(19, 0+"");
		}
		b=0.0;
		if (yingyun21.size()>0) {
		for (int j = 0; j < yingyun21.size(); j++) {
				b+=Double.parseDouble(yingyun21.get(j));
		}
		BigDecimal bg = new BigDecimal(b/yingyun21.size());
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		pingjunyingyun.add(20, f1+"");
		}else {
			pingjunyingyun.add(20, 0+"");
		}
		b=0.0;
		if (yingyun22.size()>0) {
		for (int j = 0; j < yingyun22.size(); j++) {
				b+=Double.parseDouble(yingyun22.get(j));
		}
		BigDecimal bg = new BigDecimal(b/yingyun22.size());
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		pingjunyingyun.add(21, f1+"");
		}else {
			pingjunyingyun.add(21, 0+"");
		}
		b=0.0;
		if (yingyun23.size()>0) {
		for (int j = 0; j < yingyun23.size(); j++) {
				b+=Double.parseDouble(yingyun23.get(j));
		}
		BigDecimal bg = new BigDecimal(b/yingyun23.size());
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		pingjunyingyun.add(22, f1+"");
		}else {
			pingjunyingyun.add(22, 0+"");
		}
		b=0.0;
		if (yingyun24.size()>0) {
		for (int j = 0; j < yingyun24.size(); j++) {
				b+=Double.parseDouble(yingyun24.get(j));
		}
		BigDecimal bg = new BigDecimal(b/yingyun24.size());
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		pingjunyingyun.add(23, f1+"");
	}else {
		pingjunyingyun.add(23, 0+"");
	}
		l.setPjyingyun(pingjunyingyun);
		return l;
	}
}
