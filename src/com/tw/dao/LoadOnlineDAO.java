package com.tw.dao;

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

public class LoadOnlineDAO {
	public String[][] findloadonline(String time,int i){
		String [][] a=new String [4][48];
		for (int j = 0; j < 4; j++) {
			for (int x = 0; x < a[0].length; x++) {
				a[j][x]="0";
			}
		}
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String jintian=null,zuotian=null,qiantian=null,shangzhou=null,shangyue=null,shangnian=null;
		try {
		Date beginDate = dft.parse(time);
		jintian=time;
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
		zuotian=dft.format(endDate);
		Calendar date7 = Calendar.getInstance();
		date7.setTime(beginDate);
		date7.set(Calendar.DATE, date7.get(Calendar.DATE) - 2);
		Date date8=dft.parse(dft.format(date7.getTime()));
		qiantian=dft.format(date8);
		Calendar date3 = Calendar.getInstance();
		date3.setTime(beginDate);
		date3.set(Calendar.DATE, date3.get(Calendar.DATE) - 7);
		Date date4=dft.parse(dft.format(date3.getTime()));
		shangzhou=dft.format(date4);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		int riqi=cal.get(Calendar.DAY_OF_MONTH);
	    Calendar date1 = Calendar.getInstance();
		date1.setTime(beginDate);
		date1.set(Calendar.DATE, date1.get(Calendar.DATE) - riqi);
		Date date2=dft.parse(dft.format(date1.getTime()));
		shangyue=dft.format(date2);
		Calendar date5 = Calendar.getInstance();
		date5.setTime(beginDate);
		date5.set(Calendar.DATE, date5.get(Calendar.DATE) - 365);
		Date date6=dft.parse(dft.format(date5.getTime()));
		shangnian=dft.format(date6);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String d=null;
		if (i==0) {
			d=shangnian;
		}else if (i==1) {
			d=shangyue;
		}else if (i==2) {
			d=shangzhou;
		}else if(i==3){
			d=qiantian;
		}else if (i==4) {
			d=zuotian;
		}else if (i==5) {
			d=jintian;
		}
		List<LOADONLINE>list=new ArrayList<LOADONLINE>();
		String sql="select * from hz_taxi.TB_TAXI_LOAD_ONLINE_RATE t where" +
				" db_time>=to_date('"+d+" 00:00:00','yyyy-MM-dd HH24:mi:ss') and" +
				" db_time<=to_date('"+d+" 23:59:59','yyyy-MM-dd HH24:mi:ss') order by db_time";
		try {
			String dbtime=null;
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				LOADONLINE l=new LOADONLINE();
				dbtime=rs.getString("db_time");
				if (dbtime.length()==10||dbtime.substring(11	, 16).equals("00:00")) {
					a[0][0]=rs.getString("load_rate");
					a[1][0]=rs.getString("area_load_rate");
					a[2][0]=rs.getString("online_rate");
					a[3][0]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("00:30")) {
					a[0][1]=rs.getString("load_rate");
					a[1][1]=rs.getString("area_load_rate");
					a[2][1]=rs.getString("online_rate");
					a[3][1]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("01:00")) {
					a[0][2]=rs.getString("load_rate");
					a[1][2]=rs.getString("area_load_rate");
					a[2][2]=rs.getString("online_rate");
					a[3][2]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("01:30")) {
					a[0][3]=rs.getString("load_rate");
					a[1][3]=rs.getString("area_load_rate");
					a[2][3]=rs.getString("online_rate");
					a[3][3]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("02:00")) {
					a[0][4]=rs.getString("load_rate");
					a[1][4]=rs.getString("area_load_rate");
					a[2][4]=rs.getString("online_rate");
					a[3][4]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("02:30")) {
					a[0][5]=rs.getString("load_rate");
					a[1][5]=rs.getString("area_load_rate");
					a[2][5]=rs.getString("online_rate");
					a[3][5]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("03:00")) {
					a[0][6]=rs.getString("load_rate");
					a[1][6]=rs.getString("area_load_rate");
					a[2][6]=rs.getString("online_rate");
					a[3][6]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("03:30")) {
					a[0][7]=rs.getString("load_rate");
					a[1][7]=rs.getString("area_load_rate");
					a[2][7]=rs.getString("online_rate");
					a[3][7]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("04:00")) {
					a[0][8]=rs.getString("load_rate");
					a[1][8]=rs.getString("area_load_rate");
					a[2][8]=rs.getString("online_rate");
					a[3][8]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("04:30")) {
					a[0][9]=rs.getString("load_rate");
					a[1][9]=rs.getString("area_load_rate");
					a[2][9]=rs.getString("online_rate");
					a[3][9]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("05:00")) {
					a[0][10]=rs.getString("load_rate");
					a[1][10]=rs.getString("area_load_rate");
					a[2][10]=rs.getString("online_rate");
					a[3][10]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("05:30")) {
					a[0][11]=rs.getString("load_rate");
					a[1][11]=rs.getString("area_load_rate");
					a[2][11]=rs.getString("online_rate");
					a[3][11]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("06:00")) {
					a[0][12]=rs.getString("load_rate");
					a[1][12]=rs.getString("area_load_rate");
					a[2][12]=rs.getString("online_rate");
					a[3][12]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("06:30")) {
					a[0][13]=rs.getString("load_rate");
					a[1][13]=rs.getString("area_load_rate");
					a[2][13]=rs.getString("online_rate");
					a[3][13]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("07:00")) {
					a[0][14]=rs.getString("load_rate");
					a[1][14]=rs.getString("area_load_rate");
					a[2][14]=rs.getString("online_rate");
					a[3][14]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("07:30")) {
					a[0][15]=rs.getString("load_rate");
					a[1][15]=rs.getString("area_load_rate");
					a[2][15]=rs.getString("online_rate");
					a[3][15]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("08:00")) {
					a[0][16]=rs.getString("load_rate");
					a[1][16]=rs.getString("area_load_rate");
					a[2][16]=rs.getString("online_rate");
					a[3][16]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("08:30")) {
					a[0][17]=rs.getString("load_rate");
					a[1][17]=rs.getString("area_load_rate");
					a[2][17]=rs.getString("online_rate");
					a[3][17]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("09:00")) {
					a[0][18]=rs.getString("load_rate");
					a[1][18]=rs.getString("area_load_rate");
					a[2][18]=rs.getString("online_rate");
					a[3][18]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("09:30")) {
					a[0][19]=rs.getString("load_rate");
					a[1][19]=rs.getString("area_load_rate");
					a[2][19]=rs.getString("online_rate");
					a[3][19]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("10:00")) {
					a[0][20]=rs.getString("load_rate");
					a[1][20]=rs.getString("area_load_rate");
					a[2][20]=rs.getString("online_rate");
					a[3][20]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("10:30")) {
					a[0][21]=rs.getString("load_rate");
					a[1][21]=rs.getString("area_load_rate");
					a[2][21]=rs.getString("online_rate");
					a[3][21]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("11:00")) {
					a[0][22]=rs.getString("load_rate");
					a[1][22]=rs.getString("area_load_rate");
					a[2][22]=rs.getString("online_rate");
					a[3][22]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("11:30")) {
					a[0][23]=rs.getString("load_rate");
					a[1][23]=rs.getString("area_load_rate");
					a[2][23]=rs.getString("online_rate");
					a[3][23]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("12:00")) {
					a[0][24]=rs.getString("load_rate");
					a[1][24]=rs.getString("area_load_rate");
					a[2][24]=rs.getString("online_rate");
					a[3][24]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("12:30")) {
					a[0][25]=rs.getString("load_rate");
					a[1][25]=rs.getString("area_load_rate");
					a[2][25]=rs.getString("online_rate");
					a[3][25]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("13:00")) {
					a[0][26]=rs.getString("load_rate");
					a[1][26]=rs.getString("area_load_rate");
					a[2][26]=rs.getString("online_rate");
					a[3][26]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("13:30")) {
					a[0][27]=rs.getString("load_rate");
					a[1][27]=rs.getString("area_load_rate");
					a[2][27]=rs.getString("online_rate");
					a[3][27]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("14:00")) {
					a[0][28]=rs.getString("load_rate");
					a[1][28]=rs.getString("area_load_rate");
					a[2][28]=rs.getString("online_rate");
					a[3][28]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("14:30")) {
					a[0][29]=rs.getString("load_rate");
					a[1][29]=rs.getString("area_load_rate");
					a[2][29]=rs.getString("online_rate");
					a[3][29]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("15:00")) {
					a[0][30]=rs.getString("load_rate");
					a[1][30]=rs.getString("area_load_rate");
					a[2][30]=rs.getString("online_rate");
					a[3][30]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("15:30")) {
					a[0][31]=rs.getString("load_rate");
					a[1][31]=rs.getString("area_load_rate");
					a[2][31]=rs.getString("online_rate");
					a[3][31]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("16:00")) {
					a[0][32]=rs.getString("load_rate");
					a[1][32]=rs.getString("area_load_rate");
					a[2][32]=rs.getString("online_rate");
					a[3][32]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("16:30")) {
					a[0][33]=rs.getString("load_rate");
					a[1][33]=rs.getString("area_load_rate");
					a[2][33]=rs.getString("online_rate");
					a[3][33]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("17:00")) {
					a[0][34]=rs.getString("load_rate");
					a[1][34]=rs.getString("area_load_rate");
					a[2][34]=rs.getString("online_rate");
					a[3][34]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("17:30")) {
					a[0][35]=rs.getString("load_rate");
					a[1][35]=rs.getString("area_load_rate");
					a[2][35]=rs.getString("online_rate");
					a[3][35]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("18:00")) {
					a[0][36]=rs.getString("load_rate");
					a[1][36]=rs.getString("area_load_rate");
					a[2][36]=rs.getString("online_rate");
					a[3][36]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("18:30")) {
					a[0][37]=rs.getString("load_rate");
					a[1][37]=rs.getString("area_load_rate");
					a[2][37]=rs.getString("online_rate");
					a[3][37]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("19:00")) {
					a[0][38]=rs.getString("load_rate");
					a[1][38]=rs.getString("area_load_rate");
					a[2][38]=rs.getString("online_rate");
					a[3][38]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("19:30")) {
					a[0][39]=rs.getString("load_rate");
					a[1][39]=rs.getString("area_load_rate");
					a[2][39]=rs.getString("online_rate");
					a[3][39]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("20:00")) {
					a[0][40]=rs.getString("load_rate");
					a[1][40]=rs.getString("area_load_rate");
					a[2][40]=rs.getString("online_rate");
					a[3][40]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("20:30")) {
					a[0][41]=rs.getString("load_rate");
					a[1][41]=rs.getString("area_load_rate");
					a[2][41]=rs.getString("online_rate");
					a[3][41]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("21:00")) {
					a[0][42]=rs.getString("load_rate");
					a[1][42]=rs.getString("area_load_rate");
					a[2][42]=rs.getString("online_rate");
					a[3][42]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("21:30")) {
					a[0][43]=rs.getString("load_rate");
					a[1][43]=rs.getString("area_load_rate");
					a[2][43]=rs.getString("online_rate");
					a[3][43]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("22:00")) {
					a[0][44]=rs.getString("load_rate");
					a[1][44]=rs.getString("area_load_rate");
					a[2][44]=rs.getString("online_rate");
					a[3][44]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("22:30")) {
					a[0][45]=rs.getString("load_rate");
					a[1][45]=rs.getString("area_load_rate");
					a[2][45]=rs.getString("online_rate");
					a[3][45]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("23:00")) {
					a[0][46]=rs.getString("load_rate");
					a[1][46]=rs.getString("area_load_rate");
					a[2][46]=rs.getString("online_rate");
					a[3][46]=rs.getString("area_online_rate");
				}else if (dbtime.substring(11	, 16).equals("23:30")) {
					a[0][47]=rs.getString("load_rate");
					a[1][47]=rs.getString("area_load_rate");
					a[2][47]=rs.getString("online_rate");
					a[3][47]=rs.getString("area_online_rate");
				}
//					if (dbtime.length()==10||dbtime.substring(11	, 16).equals("00:00")||dbtime.substring(11	, 16).equals("00:30")||dbtime.substring(11	, 16).equals("01:00")||dbtime.substring(11	, 16).equals("01:30")||dbtime.substring(11	, 16).equals("02:00")||dbtime.substring(11	, 16).equals("02:30")||dbtime.substring(11	, 16).equals("03:00")||dbtime.substring(11	, 16).equals("03:30")||dbtime.substring(11	, 16).equals("04:00")||dbtime.substring(11	, 16).equals("04:30")||dbtime.substring(11	, 16).equals("05:00")||dbtime.substring(11	, 16).equals("05:30")||dbtime.substring(11	, 16).equals("06:00")||dbtime.substring(11	, 16).equals("06:30")||dbtime.substring(11	, 16).equals("07:00")||dbtime.substring(11	, 16).equals("07:30")||dbtime.substring(11	, 16).equals("08:00")||dbtime.substring(11	, 16).equals("08:30")||dbtime.substring(11	, 16).equals("09:00")||dbtime.substring(11	, 16).equals("09:30")||dbtime.substring(11	, 16).equals("10:00")||dbtime.substring(11	, 16).equals("10:30")||dbtime.substring(11	, 16).equals("11:00")||dbtime.substring(11	, 16).equals("11:30")||dbtime.substring(11	, 16).equals("12:00")||dbtime.substring(11	, 16).equals("12:30")||dbtime.substring(11	, 16).equals("13:00")||dbtime.substring(11	, 16).equals("13:30")||dbtime.substring(11	, 16).equals("14:00")||dbtime.substring(11	, 16).equals("14:30")||dbtime.substring(11	, 16).equals("15:00")||dbtime.substring(11	, 16).equals("15:30")||dbtime.substring(11	, 16).equals("16:00")||dbtime.substring(11	, 16).equals("16:30")||dbtime.substring(11	, 16).equals("17:00")||dbtime.substring(11	, 16).equals("17:30")||dbtime.substring(11	, 16).equals("18:00")||dbtime.substring(11	, 16).equals("18:30")||dbtime.substring(11	, 16).equals("19:00")||dbtime.substring(11	, 16).equals("19:30")||dbtime.substring(11	, 16).equals("20:00")||dbtime.substring(11	, 16).equals("20:30")||dbtime.substring(11	, 16).equals("21:00")||dbtime.substring(11	, 16).equals("21:30")||dbtime.substring(11	, 16).equals("22:00")||dbtime.substring(11	, 16).equals("22:30")||dbtime.substring(11	, 16).equals("23:00")||dbtime.substring(11	, 16).equals("23:30")) {
//						l.setLoad_rate(rs.getString("load_rate"));
//						l.setOnline_rate(rs.getString("online_rate"));
//						l.setArea_load_rate(rs.getString("area_load_rate"));
//						l.setArea_online_rate(rs.getString("area_online_rate"));
//						list.add(l);
//						}
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	//平均值
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
		String sql="select * from hz_taxi.TB_TAXI_LOAD_ONLINE_RATE t where db_time>=to_date('"+st+"','yyyy-MM-dd HH24:mi:ss')" +
				" and db_time<=to_date('"+et+"','yyyy-MM-dd HH24:mi:ss')";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				LOADONLINE l=new LOADONLINE();
				l.setDb_time(rs.getString("db_time"));
				l.setLoad_rate(rs.getString("load_rate"));
				l.setOnline_rate(rs.getString("online_rate"));
				l.setArea_load_rate(rs.getString("area_load_rate"));
				l.setArea_online_rate(rs.getString("area_online_rate"));
				list.add(l);
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOADONLINE zhuanhuan=zhuanh(list);
		List<LOADONLINE>list1=new ArrayList<LOADONLINE>();
		list1.add(zhuanhuan);
		return list1;
	}
	public LOADONLINE zhuanh(List<LOADONLINE>list){
		List<LOADONLINE>list1=list;
		LOADONLINE l=new LOADONLINE();
		List<String>online1=new ArrayList<String>();
		List<String>online2=new ArrayList<String>();
		List<String>online3=new ArrayList<String>();
		List<String>online4=new ArrayList<String>();
		List<String>online5=new ArrayList<String>();
		List<String>online6=new ArrayList<String>();
		List<String>online7=new ArrayList<String>();
		List<String>online8=new ArrayList<String>();
		List<String>online9=new ArrayList<String>();
		List<String>online10=new ArrayList<String>();
		List<String>online11=new ArrayList<String>();
		List<String>online12=new ArrayList<String>();
		List<String>online13=new ArrayList<String>();
		List<String>online14=new ArrayList<String>();
		List<String>online15=new ArrayList<String>();
		List<String>online16=new ArrayList<String>();
		List<String>online17=new ArrayList<String>();
		List<String>online18=new ArrayList<String>();
		List<String>online19=new ArrayList<String>();
		List<String>online20=new ArrayList<String>();
		List<String>online21=new ArrayList<String>();
		List<String>online22=new ArrayList<String>();
		List<String>online23=new ArrayList<String>();
		List<String>online24=new ArrayList<String>();
		List<String>online25=new ArrayList<String>();
		List<String>online26=new ArrayList<String>();
		List<String>online27=new ArrayList<String>();
		List<String>online28=new ArrayList<String>();
		List<String>online29=new ArrayList<String>();
		List<String>online30=new ArrayList<String>();
		List<String>online31=new ArrayList<String>();
		List<String>online32=new ArrayList<String>();
		List<String>online33=new ArrayList<String>();
		List<String>online34=new ArrayList<String>();
		List<String>online35=new ArrayList<String>();
		List<String>online36=new ArrayList<String>();
		List<String>online37=new ArrayList<String>();
		List<String>online38=new ArrayList<String>();
		List<String>online39=new ArrayList<String>();
		List<String>online40=new ArrayList<String>();
		List<String>online41=new ArrayList<String>();
		List<String>online42=new ArrayList<String>();
		List<String>online43=new ArrayList<String>();
		List<String>online44=new ArrayList<String>();
		List<String>online45=new ArrayList<String>();
		List<String>online46=new ArrayList<String>();
		List<String>online47=new ArrayList<String>();
		List<String>online48=new ArrayList<String>();
		List<String>allonline1=new ArrayList<String>();
		List<String>allonline2=new ArrayList<String>();
		List<String>allonline3=new ArrayList<String>();
		List<String>allonline4=new ArrayList<String>();
		List<String>allonline5=new ArrayList<String>();
		List<String>allonline6=new ArrayList<String>();
		List<String>allonline7=new ArrayList<String>();
		List<String>allonline8=new ArrayList<String>();
		List<String>allonline9=new ArrayList<String>();
		List<String>allonline10=new ArrayList<String>();
		List<String>allonline11=new ArrayList<String>();
		List<String>allonline12=new ArrayList<String>();
		List<String>allonline13=new ArrayList<String>();
		List<String>allonline14=new ArrayList<String>();
		List<String>allonline15=new ArrayList<String>();
		List<String>allonline16=new ArrayList<String>();
		List<String>allonline17=new ArrayList<String>();
		List<String>allonline18=new ArrayList<String>();
		List<String>allonline19=new ArrayList<String>();
		List<String>allonline20=new ArrayList<String>();
		List<String>allonline21=new ArrayList<String>();
		List<String>allonline22=new ArrayList<String>();
		List<String>allonline23=new ArrayList<String>();
		List<String>allonline24=new ArrayList<String>();
		List<String>allonline25=new ArrayList<String>();
		List<String>allonline26=new ArrayList<String>();
		List<String>allonline27=new ArrayList<String>();
		List<String>allonline28=new ArrayList<String>();
		List<String>allonline29=new ArrayList<String>();
		List<String>allonline30=new ArrayList<String>();
		List<String>allonline31=new ArrayList<String>();
		List<String>allonline32=new ArrayList<String>();
		List<String>allonline33=new ArrayList<String>();
		List<String>allonline34=new ArrayList<String>();
		List<String>allonline35=new ArrayList<String>();
		List<String>allonline36=new ArrayList<String>();
		List<String>allonline37=new ArrayList<String>();
		List<String>allonline38=new ArrayList<String>();
		List<String>allonline39=new ArrayList<String>();
		List<String>allonline40=new ArrayList<String>();
		List<String>allonline41=new ArrayList<String>();
		List<String>allonline42=new ArrayList<String>();
		List<String>allonline43=new ArrayList<String>();
		List<String>allonline44=new ArrayList<String>();
		List<String>allonline45=new ArrayList<String>();
		List<String>allonline46=new ArrayList<String>();
		List<String>allonline47=new ArrayList<String>();
		List<String>allonline48=new ArrayList<String>();
		List<String>load1=new ArrayList<String>();
		List<String>load2=new ArrayList<String>();
		List<String>load3=new ArrayList<String>();
		List<String>load4=new ArrayList<String>();
		List<String>load5=new ArrayList<String>();
		List<String>load6=new ArrayList<String>();
		List<String>load7=new ArrayList<String>();
		List<String>load8=new ArrayList<String>();
		List<String>load9=new ArrayList<String>();
		List<String>load10=new ArrayList<String>();
		List<String>load11=new ArrayList<String>();
		List<String>load12=new ArrayList<String>();
		List<String>load13=new ArrayList<String>();
		List<String>load14=new ArrayList<String>();
		List<String>load15=new ArrayList<String>();
		List<String>load16=new ArrayList<String>();
		List<String>load17=new ArrayList<String>();
		List<String>load18=new ArrayList<String>();
		List<String>load19=new ArrayList<String>();
		List<String>load20=new ArrayList<String>();
		List<String>load21=new ArrayList<String>();
		List<String>load22=new ArrayList<String>();
		List<String>load23=new ArrayList<String>();
		List<String>load24=new ArrayList<String>();
		List<String>load25=new ArrayList<String>();
		List<String>load26=new ArrayList<String>();
		List<String>load27=new ArrayList<String>();
		List<String>load28=new ArrayList<String>();
		List<String>load29=new ArrayList<String>();
		List<String>load30=new ArrayList<String>();
		List<String>load31=new ArrayList<String>();
		List<String>load32=new ArrayList<String>();
		List<String>load33=new ArrayList<String>();
		List<String>load34=new ArrayList<String>();
		List<String>load35=new ArrayList<String>();
		List<String>load36=new ArrayList<String>();
		List<String>load37=new ArrayList<String>();
		List<String>load38=new ArrayList<String>();
		List<String>load39=new ArrayList<String>();
		List<String>load40=new ArrayList<String>();
		List<String>load41=new ArrayList<String>();
		List<String>load42=new ArrayList<String>();
		List<String>load43=new ArrayList<String>();
		List<String>load44=new ArrayList<String>();
		List<String>load45=new ArrayList<String>();
		List<String>load46=new ArrayList<String>();
		List<String>load47=new ArrayList<String>();
		List<String>load48=new ArrayList<String>();
		List<String>allload1=new ArrayList<String>();
		List<String>allload2=new ArrayList<String>();
		List<String>allload3=new ArrayList<String>();
		List<String>allload4=new ArrayList<String>();
		List<String>allload5=new ArrayList<String>();
		List<String>allload6=new ArrayList<String>();
		List<String>allload7=new ArrayList<String>();
		List<String>allload8=new ArrayList<String>();
		List<String>allload9=new ArrayList<String>();
		List<String>allload10=new ArrayList<String>();
		List<String>allload11=new ArrayList<String>();
		List<String>allload12=new ArrayList<String>();
		List<String>allload13=new ArrayList<String>();
		List<String>allload14=new ArrayList<String>();
		List<String>allload15=new ArrayList<String>();
		List<String>allload16=new ArrayList<String>();
		List<String>allload17=new ArrayList<String>();
		List<String>allload18=new ArrayList<String>();
		List<String>allload19=new ArrayList<String>();
		List<String>allload20=new ArrayList<String>();
		List<String>allload21=new ArrayList<String>();
		List<String>allload22=new ArrayList<String>();
		List<String>allload23=new ArrayList<String>();
		List<String>allload24=new ArrayList<String>();
		List<String>allload25=new ArrayList<String>();
		List<String>allload26=new ArrayList<String>();
		List<String>allload27=new ArrayList<String>();
		List<String>allload28=new ArrayList<String>();
		List<String>allload29=new ArrayList<String>();
		List<String>allload30=new ArrayList<String>();
		List<String>allload31=new ArrayList<String>();
		List<String>allload32=new ArrayList<String>();
		List<String>allload33=new ArrayList<String>();
		List<String>allload34=new ArrayList<String>();
		List<String>allload35=new ArrayList<String>();
		List<String>allload36=new ArrayList<String>();
		List<String>allload37=new ArrayList<String>();
		List<String>allload38=new ArrayList<String>();
		List<String>allload39=new ArrayList<String>();
		List<String>allload40=new ArrayList<String>();
		List<String>allload41=new ArrayList<String>();
		List<String>allload42=new ArrayList<String>();
		List<String>allload43=new ArrayList<String>();
		List<String>allload44=new ArrayList<String>();
		List<String>allload45=new ArrayList<String>();
		List<String>allload46=new ArrayList<String>();
		List<String>allload47=new ArrayList<String>();
		List<String>allload48=new ArrayList<String>();
		for (int i = 0; i < list1.size(); i++) {
			if (list.get(i).getDb_time().length()==10||list.get(i).getDb_time().substring(11,16).equals("00:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline1.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online1.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload1.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load1.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("00:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline2.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online2.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload2.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load2.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("01:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline3.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online3.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload3.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load3.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("01:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline4.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online4.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload4.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load4.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("02:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline5.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online5.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload5.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load5.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("02:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline6.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online6.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload6.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load6.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("03:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline7.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online7.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload7.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load7.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("03:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline8.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online8.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload8.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load8.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("04:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline9.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online9.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload9.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load9.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("04:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline10.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online10.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload10.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load10.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("05:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline11.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online11.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload11.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load11.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("05:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline12.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online12.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload12.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load12.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("06:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline13.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online13.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload13.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load13.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("06:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline14.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online14.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload14.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load14.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("07:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline15.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online15.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload15.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load15.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("07:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline16.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online16.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload16.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load16.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("08:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline17.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online17.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload17.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load17.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("08:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline18.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online18.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload18.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load18.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("09:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline19.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online19.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload19.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load19.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("09:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline20.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online20.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload20.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load20.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("10:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline21.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online21.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload21.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load21.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("10:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline22.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online22.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload22.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load22.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("11:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline23.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online23.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload23.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load23.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("11:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline24.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online24.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload24.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load24.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("12:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline25.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online25.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload25.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load25.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("12:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline26.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online26.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload26.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load26.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("13:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline27.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online27.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload27.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load27.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("13:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline28.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online28.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload28.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load28.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("14:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline29.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online29.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload29.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load29.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("14:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline30.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online30.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload30.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load30.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("15:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline31.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online31.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload31.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load31.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("15:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline32.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online32.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload32.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load32.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("16:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline33.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online33.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload33.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load33.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("16:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline34.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online34.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload34.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load34.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("17:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline35.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online35.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload35.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load35.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("17:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline36.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online36.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload36.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load36.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("18:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline37.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online37.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload37.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load37.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("18:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline38.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online38.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload38.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load38.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("19:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline39.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online39.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload39.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load39.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("19:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline40.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online40.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload40.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load40.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("20:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline41.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online41.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload41.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load41.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("20:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline42.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online42.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload42.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load42.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("21:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline43.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online43.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload43.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load43.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("21:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline44.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online44.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload44.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load44.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("22:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline45.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online45.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload45.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load45.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("22:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline46.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online46.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload46.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load46.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("23:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline47.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online47.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload47.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load47.add(list.get(i).getArea_load_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("23:30")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					allonline48.add(list.get(i).getOnline_rate());
				}
				if (list.get(i).getArea_online_rate()!=null&&list.get(i).getArea_online_rate().length()>0&&list.get(i).getArea_online_rate()!="0") {
					online48.add(list.get(i).getArea_online_rate());
				}
				if (list.get(i).getLoad_rate()!=null&&list.get(i).getLoad_rate().length()>0&&list.get(i).getLoad_rate()!="0") {
					allload48.add(list.get(i).getLoad_rate());
				}
				if (list.get(i).getArea_load_rate()!=null&&list.get(i).getArea_load_rate().length()>0&&list.get(i).getArea_load_rate()!="0") {
					load48.add(list.get(i).getArea_load_rate());
				}
			}
		}
		List<String>pingjunonline=new ArrayList<String>();
		List<String>pingjunallonline=new ArrayList<String>();
		List<String>pingjunload=new ArrayList<String>();
		List<String>pingjunallload=new ArrayList<String>();
		double b=0.0;
		b=0;
		if (online1.size()>0) {
			for (int j = 0; j < online1.size(); j++) {
				if(online1.get(j).length()==3){
					b+=Double.parseDouble(online1.get(j).substring(0, 2))/100;
				}
			}
			pingjunonline.add(0, (int)(b/online1.size()*100)+"");
		}else {
			pingjunonline.add(0, 0+"");
		}
		b=0.0;
		if (online2.size()>0) {
			
			for (int j = 0; j < online2.size(); j++) {
				if(online2.get(j).length()==3){
					b+=Double.parseDouble(online2.get(j).substring(0, 2))/100;
				}
			}
			pingjunonline.add(1, (int)(b/online2.size()*100)+"");
		}else {
			pingjunonline.add(1, 0+"");
		}
		b=0.0;
		if (online3.size()>0) {
			
			for (int j = 0; j < online3.size(); j++) {
				if(online3.get(j).length()==3){
					b+=Double.parseDouble(online3.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunonline.add(2, (int)(b/online3.size()*100)+"");
		}else {
			pingjunonline.add(2, 0+"");
		}
		b=0.0;
		if (online4.size()>0) {
			
			for (int j = 0; j < online4.size(); j++) {
				if(online4.get(j).length()==3){
					b+=Double.parseDouble(online4.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunonline.add(3, (int)(b/online4.size()*100)+"");
		}else {
			pingjunonline.add(3, 0+"");
		}
		b=0.0;
		if (online5.size()>0) {
			for (int j = 0; j < online5.size(); j++) {
				if(online5.get(j).length()==3){
					b+=Double.parseDouble(online5.get(j).substring(0, 2))/100;
				}
			}
			pingjunonline.add(4, (int)(b/online5.size()*100)+"");
		}else {
			pingjunonline.add(4, 0+"");
		}
		b=0.0;
		if (online6.size()>0) {
			
			for (int j = 0; j < online6.size(); j++) {
				if(online6.get(j).length()==3){
					b+=Double.parseDouble(online6.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunonline.add(5, (int)(b/online6.size()*100)+"");
		}else{
			pingjunonline.add(5, 0+"");
		}
			
		b=0.0;
		if (online7.size()>0) {
			
			for (int j = 0; j < online7.size(); j++) {
				if(online7.get(j).length()==3){
					b+=Double.parseDouble(online7.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunonline.add(6, (int)(b/online7.size()*100)+"");
		}else {
			pingjunonline.add(6, 0+"");
		}
		b=0.0;
		if (online8.size()>0) {
			
			for (int j = 0; j < online8.size(); j++) {
				if(online8.get(j).length()==3){
					b+=Double.parseDouble(online8.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunonline.add(7, (int)(b/online8.size()*100)+"");
		}else {
			pingjunonline.add(7, 0+"");
		}
		b=0.0;
		if (online9.size()>0) {
			
			for (int j = 0; j < online9.size(); j++) {
				if(online9.get(j).length()==3){
					b+=Double.parseDouble(online9.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunonline.add(8, (int)(b/online9.size()*100)+"");
		}else {
			pingjunonline.add(8, 0+"");
		}
		b=0.0;
		if (online10.size()>0) {
			
			for (int j = 0; j < online10.size(); j++) {
				if(online10.get(j).length()==3){
					b+=Double.parseDouble(online10.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunonline.add(9, (int)(b/online10.size()*100)+"");
		}else {
			pingjunonline.add(9,0+"");
		}
		b=0.0;
		if (online11.size()>0) {
			
			for (int j = 0; j < online11.size(); j++) {
				if(online11.get(j).length()==3){
					b+=Double.parseDouble(online11.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunonline.add(10, (int)(b/online11.size()*100)+"");
		}else {
			pingjunonline.add(10, 0+"");

		}
		b=0.0;
		if (online12.size()>0) {
			
			for (int j = 0; j < online12.size(); j++) {
				if(online12.get(j).length()==3){
					b+=Double.parseDouble(online12.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunonline.add(11, (int)(b/online12.size()*100)+"");
		}else {
			pingjunonline.add(11, 0+"");
		}
		b=0.0;
		if (online13.size()>0) {
			for (int j = 0; j < online13.size(); j++) {
				if(online13.get(j).length()==3){
					b+=Double.parseDouble(online13.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunonline.add(12, (int)(b/online13.size()*100)+"");
			
		}else{
			pingjunonline.add(12, 0+"");
		}
		b=0.0;
		if (online14.size()>0) {
			
			for (int j = 0; j < online14.size(); j++) {
				if(online14.get(j).length()==3){
					b+=Double.parseDouble(online14.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunonline.add(13, (int)(b/online14.size()*100)+"");
		}else {
			pingjunonline.add(13, 0+"");
		}
		b=0.0;
		if (online15.size()>0) {
			
			for (int j = 0; j < online15.size(); j++) {
				if(online15.get(j).length()==3){
					b+=Double.parseDouble(online15.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunonline.add(14, (int)(b/online15.size()*100)+"");
		}else {
			pingjunonline.add(14, 0+"");
		}
		b=0.0;
		if (online16.size()>0) {
			for (int j = 0; j < online16.size(); j++) {
				if(online16.get(j).length()==3){
					b+=Double.parseDouble(online16.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunonline.add(15, (int)(b/online16.size()*100)+"");
		}else {
			pingjunonline.add(15, 0+"");
		}
		b=0.0;
		if (online17.size()>0) {
		for (int j = 0; j < online17.size(); j++) {
			if(online17.get(j).length()==3){
				b+=Double.parseDouble(online17.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(16, (int)(b/online17.size()*100)+"");
		}else {
			pingjunonline.add(16, 0+"");
		}
		b=0.0;
		if (online18.size()>0) {
		for (int j = 0; j < online18.size(); j++) {
			if(online18.get(j).length()==3){
				b+=Double.parseDouble(online18.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(17, (int)(b/online18.size()*100)+"");
		}else {
			pingjunonline.add(17, 0+"");
		}
		b=0.0;
		if (online19.size()>0) {
		for (int j = 0; j < online19.size(); j++) {
			if(online19.get(j).length()==3){
				b+=Double.parseDouble(online19.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(18, (int)(b/online19.size()*100)+"");
		}else {
			pingjunonline.add(18, 0+"");
		}
		b=0.0;
		if (online20.size()>0) {
		for (int j = 0; j < online20.size(); j++) {
			if(online20.get(j).length()==3){
				b+=Double.parseDouble(online20.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(19, (int)(b/online20.size()*100)+"");
		}else {
			pingjunonline.add(19, 0+"");
		}
		b=0.0;
		if (online21.size()>0) {
		for (int j = 0; j < online21.size(); j++) {
			if(online21.get(j).length()==3){
				b+=Double.parseDouble(online21.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(20, (int)(b/online21.size()*100)+"");
		}else {
			pingjunonline.add(20, 0+"");
		}
		b=0.0;
		if (online22.size()>0) {
		for (int j = 0; j < online22.size(); j++) {
			if(online22.get(j).length()==3){
				b+=Double.parseDouble(online22.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(21, (int)(b/online22.size()*100)+"");
		}else {
			pingjunonline.add(21, 0+"");
		}
		b=0.0;
		if (online23.size()>0) {
		for (int j = 0; j < online23.size(); j++) {
			if(online23.get(j).length()==3){
				b+=Double.parseDouble(online23.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(22, (int)(b/online23.size()*100)+"");
		}else {
			pingjunonline.add(22, 0+"");
		}
		b=0.0;
		if (online24.size()>0) {
		for (int j = 0; j < online24.size(); j++) {
			if(online24.get(j).length()==3){
				b+=Double.parseDouble(online24.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(23, (int)(b/online24.size()*100)+"");
	}else {
		pingjunonline.add(23, 0+"");
	}
		b=0.0;
		if (online25.size()>0) {
		for (int j = 0; j < online25.size(); j++) {
			if(online25.get(j).length()==3){
				b+=Double.parseDouble(online25.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(24, (int)(b/online25.size()*100)+"");
		}else {
			pingjunonline.add(24, 0+"");
		}
		b=0.0;
		if (online26.size()>0) {
		for (int j = 0; j < online26.size(); j++) {
			if(online26.get(j).length()==3){
				b+=Double.parseDouble(online26.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(25, (int)(b/online26.size()*100)+"");
	}else {
		pingjunonline.add(25, 0+"");
	}
		b=0.0;
		if (online27.size()>0) {
		for (int j = 0; j < online27.size(); j++) {
			if(online27.get(j).length()==3){
				b+=Double.parseDouble(online27.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(26, (int)(b/online27.size()*100)+"");
		}else {
			pingjunonline.add(26, 0+"");
		}
		b=0.0;
		if (online28.size()>0) {
		for (int j = 0; j < online28.size(); j++) {
			if(online28.get(j).length()==3){
				b+=Double.parseDouble(online28.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(27, (int)(b/online28.size()*100)+"");
	}else {
		pingjunonline.add(27, 0+"");
	}
		b=0.0;
		if (online29.size()>0) {
		for (int j = 0; j < online29.size(); j++) {
			if(online29.get(j).length()==3){
				b+=Double.parseDouble(online29.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(28, (int)(b/online29.size()*100)+"");
		}else {
			pingjunonline.add(28, 0+"");
		}
		b=0.0;
		if (online30.size()>0) {
		for (int j = 0; j < online30.size(); j++) {
			if(online30.get(j).length()==3){
				b+=Double.parseDouble(online30.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(29, (int)(b/online30.size()*100)+"");
	}else {
		pingjunonline.add(29, 0+"");
	}
		b=0.0;
		if (online31.size()>0) {
		for (int j = 0; j < online31.size(); j++) {
			if(online31.get(j).length()==3){
				b+=Double.parseDouble(online31.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(30, (int)(b/online31.size()*100)+"");
		}else {
			pingjunonline.add(30, 0+"");
		}
		b=0.0;
		if (online32.size()>0) {
		for (int j = 0; j < online32.size(); j++) {
			if(online32.get(j).length()==3){
				b+=Double.parseDouble(online32.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(31, (int)(b/online32.size()*100)+"");
	}else {
		pingjunonline.add(31, 0+"");
	}
		b=0.0;
		if (online33.size()>0) {
		for (int j = 0; j < online33.size(); j++) {
			if(online33.get(j).length()==3){
				b+=Double.parseDouble(online33.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(32, (int)(b/online33.size()*100)+"");
		}else {
			pingjunonline.add(32, 0+"");
		}
		b=0.0;
		if (online34.size()>0) {
		for (int j = 0; j < online34.size(); j++) {
			if(online34.get(j).length()==3){
				b+=Double.parseDouble(online34.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(33, (int)(b/online34.size()*100)+"");
	}else {
		pingjunonline.add(33, 0+"");
	}
		b=0.0;
		if (online35.size()>0) {
		for (int j = 0; j < online35.size(); j++) {
			if(online35.get(j).length()==3){
				b+=Double.parseDouble(online35.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(34, (int)(b/online35.size()*100)+"");
		}else {
			pingjunonline.add(34, 0+"");
		}
		b=0.0;
		if (online36.size()>0) {
		for (int j = 0; j < online36.size(); j++) {
			if(online36.get(j).length()==3){
				b+=Double.parseDouble(online36.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(35, (int)(b/online36.size()*100)+"");
	}else {
		pingjunonline.add(35, 0+"");
	}
		b=0.0;
		if (online37.size()>0) {
		for (int j = 0; j < online37.size(); j++) {
			if(online37.get(j).length()==3){
				b+=Double.parseDouble(online37.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(36, (int)(b/online37.size()*100)+"");
		}else {
			pingjunonline.add(36, 0+"");
		}
		b=0.0;
		if (online38.size()>0) {
		for (int j = 0; j < online38.size(); j++) {
			if(online38.get(j).length()==3){
				b+=Double.parseDouble(online38.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(37, (int)(b/online38.size()*100)+"");
	}else {
		pingjunonline.add(37, 0+"");
	}
		b=0.0;
		if (online39.size()>0) {
		for (int j = 0; j < online39.size(); j++) {
			if(online39.get(j).length()==3){
				b+=Double.parseDouble(online39.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(38, (int)(b/online39.size()*100)+"");
		}else {
			pingjunonline.add(38, 0+"");
		}
		b=0.0;
		if (online40.size()>0) {
		for (int j = 0; j < online40.size(); j++) {
			if(online40.get(j).length()==3){
				b+=Double.parseDouble(online40.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(39, (int)(b/online40.size()*100)+"");
	}else {
		pingjunonline.add(39, 0+"");
	}
		b=0.0;
		if (online41.size()>0) {
		for (int j = 0; j < online41.size(); j++) {
			if(online41.get(j).length()==3){
				b+=Double.parseDouble(online41.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(40, (int)(b/online41.size()*100)+"");
		}else {
			pingjunonline.add(40, 0+"");
		}
		b=0.0;
		if (online42.size()>0) {
		for (int j = 0; j < online42.size(); j++) {
			if(online42.get(j).length()==3){
				b+=Double.parseDouble(online42.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(41, (int)(b/online42.size()*100)+"");
	}else {
		pingjunonline.add(41, 0+"");
	}
		b=0.0;
		if (online43.size()>0) {
		for (int j = 0; j < online43.size(); j++) {
			if(online43.get(j).length()==3){
				b+=Double.parseDouble(online43.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(42, (int)(b/online43.size()*100)+"");
		}else {
			pingjunonline.add(42, 0+"");
		}
		b=0.0;
		if (online44.size()>0) {
		for (int j = 0; j < online44.size(); j++) {
			if(online44.get(j).length()==3){
				b+=Double.parseDouble(online44.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(43, (int)(b/online44.size()*100)+"");
	}else {
		pingjunonline.add(43, 0+"");
	}
		b=0.0;
		if (online45.size()>0) {
		for (int j = 0; j < online45.size(); j++) {
			if(online45.get(j).length()==3){
				b+=Double.parseDouble(online45.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(44, (int)(b/online45.size()*100)+"");
		}else {
			pingjunonline.add(44, 0+"");
		}
		b=0.0;
		if (online46.size()>0) {
		for (int j = 0; j < online46.size(); j++) {
			if(online46.get(j).length()==3){
				b+=Double.parseDouble(online46.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(45, (int)(b/online46.size()*100)+"");
	}else {
		pingjunonline.add(45, 0+"");
	}
		b=0.0;
		if (online47.size()>0) {
		for (int j = 0; j < online47.size(); j++) {
			if(online47.get(j).length()==3){
				b+=Double.parseDouble(online47.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunonline.add(46, (int)(b/online47.size()*100)+"");
		}else {
			pingjunonline.add(46, 0+"");
		}
		b=0.0;
		if (online48.size()>0) {
			for (int j = 0; j < online48.size(); j++) {
				if(online48.get(j).length()==3){
					b+=Double.parseDouble(online48.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunonline.add(47, (int)(b/online48.size()*100)+"");
		}else {
			pingjunonline.add(47, 0+"");
		}
		
		b=0;
		if (allonline1.size()>0) {
			for (int j = 0; j < allonline1.size(); j++) {
				if(allonline1.get(j).length()==3){
					b+=Double.parseDouble(allonline1.get(j).substring(0, 2))/100;
				}
			}
			pingjunallonline.add(0, (int)(b/allonline1.size()*100)+"");
		}else {
			pingjunallonline.add(0, 0+"");
		}
		b=0.0;
		if (allonline2.size()>0) {
			
			for (int j = 0; j < allonline2.size(); j++) {
				if(allonline2.get(j).length()==3){
					b+=Double.parseDouble(allonline2.get(j).substring(0, 2))/100;
				}
			}
			pingjunallonline.add(1, (int)(b/allonline2.size()*100)+"");
		}else {
			pingjunallonline.add(1, 0+"");
		}
		b=0.0;
		if (allonline3.size()>0) {
			
			for (int j = 0; j < allonline3.size(); j++) {
				if(allonline3.get(j).length()==3){
					b+=Double.parseDouble(allonline3.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallonline.add(2, (int)(b/allonline3.size()*100)+"");
		}else {
			pingjunallonline.add(2, 0+"");
		}
		b=0.0;
		if (allonline4.size()>0) {
			
			for (int j = 0; j < allonline4.size(); j++) {
				if(allonline4.get(j).length()==3){
					b+=Double.parseDouble(allonline4.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallonline.add(3, (int)(b/allonline4.size()*100)+"");
		}else {
			pingjunallonline.add(3, 0+"");
		}
		b=0.0;
		if (allonline5.size()>0) {
			for (int j = 0; j < allonline5.size(); j++) {
				if(allonline5.get(j).length()==3){
					b+=Double.parseDouble(allonline5.get(j).substring(0, 2))/100;
				}
			}
			pingjunallonline.add(4, (int)(b/allonline5.size()*100)+"");
		}else {
			pingjunallonline.add(4, 0+"");
		}
		b=0.0;
		if (allonline6.size()>0) {
			
			for (int j = 0; j < allonline6.size(); j++) {
				if(allonline6.get(j).length()==3){
					b+=Double.parseDouble(allonline6.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallonline.add(5, (int)(b/allonline6.size()*100)+"");
		}else{
			pingjunallonline.add(5, 0+"");
		}
			
		b=0.0;
		if (allonline7.size()>0) {
			
			for (int j = 0; j < allonline7.size(); j++) {
				if(allonline7.get(j).length()==3){
					b+=Double.parseDouble(allonline7.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallonline.add(6, (int)(b/allonline7.size()*100)+"");
		}else {
			pingjunallonline.add(6, 0+"");
		}
		b=0.0;
		if (allonline8.size()>0) {
			
			for (int j = 0; j < allonline8.size(); j++) {
				if(allonline8.get(j).length()==3){
					b+=Double.parseDouble(allonline8.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallonline.add(7, (int)(b/allonline8.size()*100)+"");
		}else {
			pingjunallonline.add(7, 0+"");
		}
		b=0.0;
		if (allonline9.size()>0) {
			
			for (int j = 0; j < allonline9.size(); j++) {
				if(allonline9.get(j).length()==3){
					b+=Double.parseDouble(allonline9.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallonline.add(8, (int)(b/allonline9.size()*100)+"");
		}else {
			pingjunallonline.add(8, 0+"");
		}
		b=0.0;
		if (allonline10.size()>0) {
			
			for (int j = 0; j < allonline10.size(); j++) {
				if(allonline10.get(j).length()==3){
					b+=Double.parseDouble(allonline10.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallonline.add(9, (int)(b/allonline10.size()*100)+"");
		}else {
			pingjunallonline.add(9,0+"");
		}
		b=0.0;
		if (allonline11.size()>0) {
			
			for (int j = 0; j < allonline11.size(); j++) {
				if(allonline11.get(j).length()==3){
					b+=Double.parseDouble(allonline11.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallonline.add(10, (int)(b/allonline11.size()*100)+"");
		}else {
			pingjunallonline.add(10, 0+"");

		}
		b=0.0;
		if (allonline12.size()>0) {
			
			for (int j = 0; j < allonline12.size(); j++) {
				if(allonline12.get(j).length()==3){
					b+=Double.parseDouble(allonline12.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallonline.add(11, (int)(b/allonline12.size()*100)+"");
		}else {
			pingjunallonline.add(11, 0+"");
		}
		b=0.0;
		if (allonline13.size()>0) {
			for (int j = 0; j < allonline13.size(); j++) {
				if(allonline13.get(j).length()==3){
					b+=Double.parseDouble(allonline13.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallonline.add(12, (int)(b/allonline13.size()*100)+"");
			
		}else{
			pingjunallonline.add(12, 0+"");
		}
		b=0.0;
		if (allonline14.size()>0) {
			
			for (int j = 0; j < allonline14.size(); j++) {
				if(allonline14.get(j).length()==3){
					b+=Double.parseDouble(allonline14.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallonline.add(13, (int)(b/allonline14.size()*100)+"");
		}else {
			pingjunallonline.add(13, 0+"");
		}
		b=0.0;
		if (allonline15.size()>0) {
			
			for (int j = 0; j < allonline15.size(); j++) {
				if(allonline15.get(j).length()==3){
					b+=Double.parseDouble(allonline15.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallonline.add(14, (int)(b/allonline15.size()*100)+"");
		}else {
			pingjunallonline.add(14, 0+"");
		}
		b=0.0;
		if (allonline16.size()>0) {
			for (int j = 0; j < allonline16.size(); j++) {
				if(allonline16.get(j).length()==3){
					b+=Double.parseDouble(allonline16.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallonline.add(15, (int)(b/allonline16.size()*100)+"");
		}else {
			pingjunallonline.add(15, 0+"");
		}
		b=0.0;
		if (allonline17.size()>0) {
		for (int j = 0; j < allonline17.size(); j++) {
			if(allonline17.get(j).length()==3){
				b+=Double.parseDouble(allonline17.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(16, (int)(b/allonline17.size()*100)+"");
		}else {
			pingjunallonline.add(16, 0+"");
		}
		b=0.0;
		if (allonline18.size()>0) {
		for (int j = 0; j < allonline18.size(); j++) {
			if(allonline18.get(j).length()==3){
				b+=Double.parseDouble(allonline18.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(17, (int)(b/allonline18.size()*100)+"");
		}else {
			pingjunallonline.add(17, 0+"");
		}
		b=0.0;
		if (allonline19.size()>0) {
		for (int j = 0; j < allonline19.size(); j++) {
			if(allonline19.get(j).length()==3){
				b+=Double.parseDouble(allonline19.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(18, (int)(b/allonline19.size()*100)+"");
		}else {
			pingjunallonline.add(18, 0+"");
		}
		b=0.0;
		if (allonline20.size()>0) {
		for (int j = 0; j < allonline20.size(); j++) {
			if(allonline20.get(j).length()==3){
				b+=Double.parseDouble(allonline20.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(19, (int)(b/allonline20.size()*100)+"");
		}else {
			pingjunallonline.add(19, 0+"");
		}
		b=0.0;
		if (allonline21.size()>0) {
		for (int j = 0; j < allonline21.size(); j++) {
			if(allonline21.get(j).length()==3){
				b+=Double.parseDouble(allonline21.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(20, (int)(b/allonline21.size()*100)+"");
		}else {
			pingjunallonline.add(20, 0+"");
		}
		b=0.0;
		if (allonline22.size()>0) {
		for (int j = 0; j < allonline22.size(); j++) {
			if(allonline22.get(j).length()==3){
				b+=Double.parseDouble(allonline22.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(21, (int)(b/allonline22.size()*100)+"");
		}else {
			pingjunallonline.add(21, 0+"");
		}
		b=0.0;
		if (allonline23.size()>0) {
		for (int j = 0; j < allonline23.size(); j++) {
			if(allonline23.get(j).length()==3){
				b+=Double.parseDouble(allonline23.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(22, (int)(b/allonline23.size()*100)+"");
		}else {
			pingjunallonline.add(22, 0+"");
		}
		b=0.0;
		if (allonline24.size()>0) {
		for (int j = 0; j < allonline24.size(); j++) {
			if(allonline24.get(j).length()==3){
				b+=Double.parseDouble(allonline24.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(23, (int)(b/allonline24.size()*100)+"");
	}else {
		pingjunallonline.add(23, 0+"");
	}
		b=0.0;
		if (allonline25.size()>0) {
		for (int j = 0; j < allonline25.size(); j++) {
			if(allonline25.get(j).length()==3){
				b+=Double.parseDouble(allonline25.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(24, (int)(b/allonline25.size()*100)+"");
		}else {
			pingjunallonline.add(24, 0+"");
		}
		b=0.0;
		if (allonline26.size()>0) {
		for (int j = 0; j < allonline26.size(); j++) {
			if(allonline26.get(j).length()==3){
				b+=Double.parseDouble(allonline26.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(25, (int)(b/allonline26.size()*100)+"");
	}else {
		pingjunallonline.add(25, 0+"");
	}
		b=0.0;
		if (allonline27.size()>0) {
		for (int j = 0; j < allonline27.size(); j++) {
			if(allonline27.get(j).length()==3){
				b+=Double.parseDouble(allonline27.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(26, (int)(b/allonline27.size()*100)+"");
		}else {
			pingjunallonline.add(26, 0+"");
		}
		b=0.0;
		if (allonline28.size()>0) {
		for (int j = 0; j < allonline28.size(); j++) {
			if(allonline28.get(j).length()==3){
				b+=Double.parseDouble(allonline28.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(27, (int)(b/allonline28.size()*100)+"");
	}else {
		pingjunallonline.add(27, 0+"");
	}
		b=0.0;
		if (allonline29.size()>0) {
		for (int j = 0; j < allonline29.size(); j++) {
			if(allonline29.get(j).length()==3){
				b+=Double.parseDouble(allonline29.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(28, (int)(b/allonline29.size()*100)+"");
		}else {
			pingjunallonline.add(28, 0+"");
		}
		b=0.0;
		if (allonline30.size()>0) {
		for (int j = 0; j < allonline30.size(); j++) {
			if(allonline30.get(j).length()==3){
				b+=Double.parseDouble(allonline30.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(29, (int)(b/allonline30.size()*100)+"");
	}else {
		pingjunallonline.add(29, 0+"");
	}
		b=0.0;
		if (allonline31.size()>0) {
		for (int j = 0; j < allonline31.size(); j++) {
			if(allonline31.get(j).length()==3){
				b+=Double.parseDouble(allonline31.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(30, (int)(b/allonline31.size()*100)+"");
		}else {
			pingjunallonline.add(30, 0+"");
		}
		b=0.0;
		if (allonline32.size()>0) {
		for (int j = 0; j < allonline32.size(); j++) {
			if(allonline32.get(j).length()==3){
				b+=Double.parseDouble(allonline32.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(31, (int)(b/allonline32.size()*100)+"");
	}else {
		pingjunallonline.add(31, 0+"");
	}
		b=0.0;
		if (allonline33.size()>0) {
		for (int j = 0; j < allonline33.size(); j++) {
			if(allonline33.get(j).length()==3){
				b+=Double.parseDouble(allonline33.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(32, (int)(b/allonline33.size()*100)+"");
		}else {
			pingjunallonline.add(32, 0+"");
		}
		b=0.0;
		if (allonline34.size()>0) {
		for (int j = 0; j < allonline34.size(); j++) {
			if(allonline34.get(j).length()==3){
				b+=Double.parseDouble(allonline34.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(33, (int)(b/allonline34.size()*100)+"");
	}else {
		pingjunallonline.add(33, 0+"");
	}
		b=0.0;
		if (allonline35.size()>0) {
		for (int j = 0; j < allonline35.size(); j++) {
			if(allonline35.get(j).length()==3){
				b+=Double.parseDouble(allonline35.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(34, (int)(b/allonline35.size()*100)+"");
		}else {
			pingjunallonline.add(34, 0+"");
		}
		b=0.0;
		if (allonline36.size()>0) {
		for (int j = 0; j < allonline36.size(); j++) {
			if(allonline36.get(j).length()==3){
				b+=Double.parseDouble(allonline36.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(35, (int)(b/allonline36.size()*100)+"");
	}else {
		pingjunallonline.add(35, 0+"");
	}
		b=0.0;
		if (allonline37.size()>0) {
		for (int j = 0; j < allonline37.size(); j++) {
			if(allonline37.get(j).length()==3){
				b+=Double.parseDouble(allonline37.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(36, (int)(b/allonline37.size()*100)+"");
		}else {
			pingjunallonline.add(36, 0+"");
		}
		b=0.0;
		if (allonline38.size()>0) {
		for (int j = 0; j < allonline38.size(); j++) {
			if(allonline38.get(j).length()==3){
				b+=Double.parseDouble(allonline38.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(37, (int)(b/allonline38.size()*100)+"");
	}else {
		pingjunallonline.add(37, 0+"");
	}
		b=0.0;
		if (allonline39.size()>0) {
		for (int j = 0; j < allonline39.size(); j++) {
			if(allonline39.get(j).length()==3){
				b+=Double.parseDouble(allonline39.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(38, (int)(b/allonline39.size()*100)+"");
		}else {
			pingjunallonline.add(38, 0+"");
		}
		b=0.0;
		if (allonline40.size()>0) {
		for (int j = 0; j < allonline40.size(); j++) {
			if(allonline40.get(j).length()==3){
				b+=Double.parseDouble(allonline40.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(39, (int)(b/allonline40.size()*100)+"");
	}else {
		pingjunallonline.add(39, 0+"");
	}
		b=0.0;
		if (allonline41.size()>0) {
		for (int j = 0; j < allonline41.size(); j++) {
			if(allonline41.get(j).length()==3){
				b+=Double.parseDouble(allonline41.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(40, (int)(b/allonline41.size()*100)+"");
		}else {
			pingjunallonline.add(40, 0+"");
		}
		b=0.0;
		if (allonline42.size()>0) {
		for (int j = 0; j < allonline42.size(); j++) {
			if(allonline42.get(j).length()==3){
				b+=Double.parseDouble(allonline42.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(41, (int)(b/allonline42.size()*100)+"");
	}else {
		pingjunallonline.add(41, 0+"");
	}
		b=0.0;
		if (allonline43.size()>0) {
		for (int j = 0; j < allonline43.size(); j++) {
			if(allonline43.get(j).length()==3){
				b+=Double.parseDouble(allonline43.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(42, (int)(b/allonline43.size()*100)+"");
		}else {
			pingjunallonline.add(42, 0+"");
		}
		b=0.0;
		if (allonline44.size()>0) {
		for (int j = 0; j < allonline44.size(); j++) {
			if(allonline44.get(j).length()==3){
				b+=Double.parseDouble(allonline44.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(43, (int)(b/allonline44.size()*100)+"");
	}else {
		pingjunallonline.add(43, 0+"");
	}
		b=0.0;
		if (allonline45.size()>0) {
		for (int j = 0; j < allonline45.size(); j++) {
			if(allonline45.get(j).length()==3){
				b+=Double.parseDouble(allonline45.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(44, (int)(b/allonline45.size()*100)+"");
		}else {
			pingjunallonline.add(44, 0+"");
		}
		b=0.0;
		if (allonline46.size()>0) {
		for (int j = 0; j < allonline46.size(); j++) {
			if(allonline46.get(j).length()==3){
				b+=Double.parseDouble(allonline46.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(45, (int)(b/allonline46.size()*100)+"");
	}else {
		pingjunallonline.add(45, 0+"");
	}
		b=0.0;
		if (allonline47.size()>0) {
		for (int j = 0; j < allonline47.size(); j++) {
			if(allonline47.get(j).length()==3){
				b+=Double.parseDouble(allonline47.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallonline.add(46, (int)(b/allonline47.size()*100)+"");
		}else {
			pingjunallonline.add(46, 0+"");
		}
		b=0.0;
		if (allonline48.size()>0) {
			for (int j = 0; j < allonline48.size(); j++) {
				if(allonline48.get(j).length()==3){
					b+=Double.parseDouble(allonline48.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallonline.add(47, (int)(b/allonline48.size()*100)+"");
		}else {
			pingjunallonline.add(47, 0+"");
		}
		
		b=0;
		if (load1.size()>0) {
			for (int j = 0; j < load1.size(); j++) {
				if(load1.get(j).length()==3){
					b+=Double.parseDouble(load1.get(j).substring(0, 2))/100;
				}
			}
			pingjunload.add(0, (int)(b/load1.size()*100)+"");
		}else {
			pingjunload.add(0, 0+"");
		}
		b=0.0;
		if (load2.size()>0) {
			
			for (int j = 0; j < load2.size(); j++) {
				if(load2.get(j).length()==3){
					b+=Double.parseDouble(load2.get(j).substring(0, 2))/100;
				}
			}
			pingjunload.add(1, (int)(b/load2.size()*100)+"");
		}else {
			pingjunload.add(1, 0+"");
		}
		b=0.0;
		if (load3.size()>0) {
			
			for (int j = 0; j < load3.size(); j++) {
				if(load3.get(j).length()==3){
					b+=Double.parseDouble(load3.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunload.add(2, (int)(b/load3.size()*100)+"");
		}else {
			pingjunload.add(2, 0+"");
		}
		b=0.0;
		if (load4.size()>0) {
			
			for (int j = 0; j < load4.size(); j++) {
				if(load4.get(j).length()==3){
					b+=Double.parseDouble(load4.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunload.add(3, (int)(b/load4.size()*100)+"");
		}else {
			pingjunload.add(3, 0+"");
		}
		b=0.0;
		if (load5.size()>0) {
			for (int j = 0; j < load5.size(); j++) {
				if(load5.get(j).length()==3){
					b+=Double.parseDouble(load5.get(j).substring(0, 2))/100;
				}
			}
			pingjunload.add(4, (int)(b/load5.size()*100)+"");
		}else {
			pingjunload.add(4, 0+"");
		}
		b=0.0;
		if (load6.size()>0) {
			
			for (int j = 0; j < load6.size(); j++) {
				if(load6.get(j).length()==3){
					b+=Double.parseDouble(load6.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunload.add(5, (int)(b/load6.size()*100)+"");
		}else{
			pingjunload.add(5, 0+"");
		}
			
		b=0.0;
		if (load7.size()>0) {
			
			for (int j = 0; j < load7.size(); j++) {
				if(load7.get(j).length()==3){
					b+=Double.parseDouble(load7.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunload.add(6, (int)(b/load7.size()*100)+"");
		}else {
			pingjunload.add(6, 0+"");
		}
		b=0.0;
		if (load8.size()>0) {
			
			for (int j = 0; j < load8.size(); j++) {
				if(load8.get(j).length()==3){
					b+=Double.parseDouble(load8.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunload.add(7, (int)(b/load8.size()*100)+"");
		}else {
			pingjunload.add(7, 0+"");
		}
		b=0.0;
		if (load9.size()>0) {
			
			for (int j = 0; j < load9.size(); j++) {
				if(load9.get(j).length()==3){
					b+=Double.parseDouble(load9.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunload.add(8, (int)(b/load9.size()*100)+"");
		}else {
			pingjunload.add(8, 0+"");
		}
		b=0.0;
		if (load10.size()>0) {
			
			for (int j = 0; j < load10.size(); j++) {
				if(load10.get(j).length()==3){
					b+=Double.parseDouble(load10.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunload.add(9, (int)(b/load10.size()*100)+"");
		}else {
			pingjunload.add(9,0+"");
		}
		b=0.0;
		if (load11.size()>0) {
			
			for (int j = 0; j < load11.size(); j++) {
				if(load11.get(j).length()==3){
					b+=Double.parseDouble(load11.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunload.add(10, (int)(b/load11.size()*100)+"");
		}else {
			pingjunload.add(10, 0+"");

		}
		b=0.0;
		if (load12.size()>0) {
			
			for (int j = 0; j < load12.size(); j++) {
				if(load12.get(j).length()==3){
					b+=Double.parseDouble(load12.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunload.add(11, (int)(b/load12.size()*100)+"");
		}else {
			pingjunload.add(11, 0+"");
		}
		b=0.0;
		if (load13.size()>0) {
			for (int j = 0; j < load13.size(); j++) {
				if(load13.get(j).length()==3){
					b+=Double.parseDouble(load13.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunload.add(12, (int)(b/load13.size()*100)+"");
			
		}else{
			pingjunload.add(12, 0+"");
		}
		b=0.0;
		if (load14.size()>0) {
			
			for (int j = 0; j < load14.size(); j++) {
				if(load14.get(j).length()==3){
					b+=Double.parseDouble(load14.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunload.add(13, (int)(b/load14.size()*100)+"");
		}else {
			pingjunload.add(13, 0+"");
		}
		b=0.0;
		if (load15.size()>0) {
			
			for (int j = 0; j < load15.size(); j++) {
				if(load15.get(j).length()==3){
					b+=Double.parseDouble(load15.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunload.add(14, (int)(b/load15.size()*100)+"");
		}else {
			pingjunload.add(14, 0+"");
		}
		b=0.0;
		if (load16.size()>0) {
			for (int j = 0; j < load16.size(); j++) {
				if(load16.get(j).length()==3){
					b+=Double.parseDouble(load16.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunload.add(15, (int)(b/load16.size()*100)+"");
		}else {
			pingjunload.add(15, 0+"");
		}
		b=0.0;
		if (load17.size()>0) {
		for (int j = 0; j < load17.size(); j++) {
			if(load17.get(j).length()==3){
				b+=Double.parseDouble(load17.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(16, (int)(b/load17.size()*100)+"");
		}else {
			pingjunload.add(16, 0+"");
		}
		b=0.0;
		if (load18.size()>0) {
		for (int j = 0; j < load18.size(); j++) {
			if(load18.get(j).length()==3){
				b+=Double.parseDouble(load18.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(17, (int)(b/load18.size()*100)+"");
		}else {
			pingjunload.add(17, 0+"");
		}
		b=0.0;
		if (load19.size()>0) {
		for (int j = 0; j < load19.size(); j++) {
			if(load19.get(j).length()==3){
				b+=Double.parseDouble(load19.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(18, (int)(b/load19.size()*100)+"");
		}else {
			pingjunload.add(18, 0+"");
		}
		b=0.0;
		if (load20.size()>0) {
		for (int j = 0; j < load20.size(); j++) {
			if(load20.get(j).length()==3){
				b+=Double.parseDouble(load20.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(19, (int)(b/load20.size()*100)+"");
		}else {
			pingjunload.add(19, 0+"");
		}
		b=0.0;
		if (load21.size()>0) {
		for (int j = 0; j < load21.size(); j++) {
			if(load21.get(j).length()==3){
				b+=Double.parseDouble(load21.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(20, (int)(b/load21.size()*100)+"");
		}else {
			pingjunload.add(20, 0+"");
		}
		b=0.0;
		if (load22.size()>0) {
		for (int j = 0; j < load22.size(); j++) {
			if(load22.get(j).length()==3){
				b+=Double.parseDouble(load22.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(21, (int)(b/load22.size()*100)+"");
		}else {
			pingjunload.add(21, 0+"");
		}
		b=0.0;
		if (load23.size()>0) {
		for (int j = 0; j < load23.size(); j++) {
			if(load23.get(j).length()==3){
				b+=Double.parseDouble(load23.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(22, (int)(b/load23.size()*100)+"");
		}else {
			pingjunload.add(22, 0+"");
		}
		b=0.0;
		if (load24.size()>0) {
		for (int j = 0; j < load24.size(); j++) {
			if(load24.get(j).length()==3){
				b+=Double.parseDouble(load24.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(23, (int)(b/load24.size()*100)+"");
	}else {
		pingjunload.add(23, 0+"");
	}
		b=0.0;
		if (load25.size()>0) {
		for (int j = 0; j < load25.size(); j++) {
			if(load25.get(j).length()==3){
				b+=Double.parseDouble(load25.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(24, (int)(b/load25.size()*100)+"");
		}else {
			pingjunload.add(24, 0+"");
		}
		b=0.0;
		if (load26.size()>0) {
		for (int j = 0; j < load26.size(); j++) {
			if(load26.get(j).length()==3){
				b+=Double.parseDouble(load26.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(25, (int)(b/load26.size()*100)+"");
	}else {
		pingjunload.add(25, 0+"");
	}
		b=0.0;
		if (load27.size()>0) {
		for (int j = 0; j < load27.size(); j++) {
			if(load27.get(j).length()==3){
				b+=Double.parseDouble(load27.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(26, (int)(b/load27.size()*100)+"");
		}else {
			pingjunload.add(26, 0+"");
		}
		b=0.0;
		if (load28.size()>0) {
		for (int j = 0; j < load28.size(); j++) {
			if(load28.get(j).length()==3){
				b+=Double.parseDouble(load28.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(27, (int)(b/load28.size()*100)+"");
	}else {
		pingjunload.add(27, 0+"");
	}
		b=0.0;
		if (load29.size()>0) {
		for (int j = 0; j < load29.size(); j++) {
			if(load29.get(j).length()==3){
				b+=Double.parseDouble(load29.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(28, (int)(b/load29.size()*100)+"");
		}else {
			pingjunload.add(28, 0+"");
		}
		b=0.0;
		if (load30.size()>0) {
		for (int j = 0; j < load30.size(); j++) {
			if(load30.get(j).length()==3){
				b+=Double.parseDouble(load30.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(29, (int)(b/load30.size()*100)+"");
	}else {
		pingjunload.add(29, 0+"");
	}
		b=0.0;
		if (load31.size()>0) {
		for (int j = 0; j < load31.size(); j++) {
			if(load31.get(j).length()==3){
				b+=Double.parseDouble(load31.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(30, (int)(b/load31.size()*100)+"");
		}else {
			pingjunload.add(30, 0+"");
		}
		b=0.0;
		if (load32.size()>0) {
		for (int j = 0; j < load32.size(); j++) {
			if(load32.get(j).length()==3){
				b+=Double.parseDouble(load32.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(31, (int)(b/load32.size()*100)+"");
	}else {
		pingjunload.add(31, 0+"");
	}
		b=0.0;
		if (load33.size()>0) {
		for (int j = 0; j < load33.size(); j++) {
			if(load33.get(j).length()==3){
				b+=Double.parseDouble(load33.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(32, (int)(b/load33.size()*100)+"");
		}else {
			pingjunload.add(32, 0+"");
		}
		b=0.0;
		if (load34.size()>0) {
		for (int j = 0; j < load34.size(); j++) {
			if(load34.get(j).length()==3){
				b+=Double.parseDouble(load34.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(33, (int)(b/load34.size()*100)+"");
	}else {
		pingjunload.add(33, 0+"");
	}
		b=0.0;
		if (load35.size()>0) {
		for (int j = 0; j < load35.size(); j++) {
			if(load35.get(j).length()==3){
				b+=Double.parseDouble(load35.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(34, (int)(b/load35.size()*100)+"");
		}else {
			pingjunload.add(34, 0+"");
		}
		b=0.0;
		if (load36.size()>0) {
		for (int j = 0; j < load36.size(); j++) {
			if(load36.get(j).length()==3){
				b+=Double.parseDouble(load36.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(35, (int)(b/load36.size()*100)+"");
	}else {
		pingjunload.add(35, 0+"");
	}
		b=0.0;
		if (load37.size()>0) {
		for (int j = 0; j < load37.size(); j++) {
			if(load37.get(j).length()==3){
				b+=Double.parseDouble(load37.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(36, (int)(b/load37.size()*100)+"");
		}else {
			pingjunload.add(36, 0+"");
		}
		b=0.0;
		if (load38.size()>0) {
		for (int j = 0; j < load38.size(); j++) {
			if(load38.get(j).length()==3){
				b+=Double.parseDouble(load38.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(37, (int)(b/load38.size()*100)+"");
	}else {
		pingjunload.add(37, 0+"");
	}
		b=0.0;
		if (load39.size()>0) {
		for (int j = 0; j < load39.size(); j++) {
			if(load39.get(j).length()==3){
				b+=Double.parseDouble(load39.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(38, (int)(b/load39.size()*100)+"");
		}else {
			pingjunload.add(38, 0+"");
		}
		b=0.0;
		if (load40.size()>0) {
		for (int j = 0; j < load40.size(); j++) {
			if(load40.get(j).length()==3){
				b+=Double.parseDouble(load40.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(39, (int)(b/load40.size()*100)+"");
	}else {
		pingjunload.add(39, 0+"");
	}
		b=0.0;
		if (load41.size()>0) {
		for (int j = 0; j < load41.size(); j++) {
			if(load41.get(j).length()==3){
				b+=Double.parseDouble(load41.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(40, (int)(b/load41.size()*100)+"");
		}else {
			pingjunload.add(40, 0+"");
		}
		b=0.0;
		if (load42.size()>0) {
		for (int j = 0; j < load42.size(); j++) {
			if(load42.get(j).length()==3){
				b+=Double.parseDouble(load42.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(41, (int)(b/load42.size()*100)+"");
	}else {
		pingjunload.add(41, 0+"");
	}
		b=0.0;
		if (load43.size()>0) {
		for (int j = 0; j < load43.size(); j++) {
			if(load43.get(j).length()==3){
				b+=Double.parseDouble(load43.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(42, (int)(b/load43.size()*100)+"");
		}else {
			pingjunload.add(42, 0+"");
		}
		b=0.0;
		if (load44.size()>0) {
		for (int j = 0; j < load44.size(); j++) {
			if(load44.get(j).length()==3){
				b+=Double.parseDouble(load44.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(43, (int)(b/load44.size()*100)+"");
	}else {
		pingjunload.add(43, 0+"");
	}
		b=0.0;
		if (load45.size()>0) {
		for (int j = 0; j < load45.size(); j++) {
			if(load45.get(j).length()==3){
				b+=Double.parseDouble(load45.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(44, (int)(b/load45.size()*100)+"");
		}else {
			pingjunload.add(44, 0+"");
		}
		b=0.0;
		if (load46.size()>0) {
		for (int j = 0; j < load46.size(); j++) {
			if(load46.get(j).length()==3){
				b+=Double.parseDouble(load46.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(45, (int)(b/load46.size()*100)+"");
	}else {
		pingjunload.add(45, 0+"");
	}
		b=0.0;
		if (load47.size()>0) {
		for (int j = 0; j < load47.size(); j++) {
			if(load47.get(j).length()==3){
				b+=Double.parseDouble(load47.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunload.add(46, (int)(b/load47.size()*100)+"");
		}else {
			pingjunload.add(46, 0+"");
		}
		b=0.0;
		if (load48.size()>0) {
			for (int j = 0; j < load48.size(); j++) {
				if(load48.get(j).length()==3){
					b+=Double.parseDouble(load48.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunload.add(47, (int)(b/load48.size()*100)+"");
		}else {
			pingjunload.add(47, 0+"");
		}
		
		b=0;
		if (allload1.size()>0) {
			for (int j = 0; j < allload1.size(); j++) {
				if(allload1.get(j).length()==3){
					b+=Double.parseDouble(allload1.get(j).substring(0, 2))/100;
				}
			}
			pingjunallload.add(0, (int)(b/allload1.size()*100)+"");
		}else {
			pingjunallload.add(0, 0+"");
		}
		b=0.0;
		if (allload2.size()>0) {
			
			for (int j = 0; j < allload2.size(); j++) {
				if(allload2.get(j).length()==3){
					b+=Double.parseDouble(allload2.get(j).substring(0, 2))/100;
				}
			}
			pingjunallload.add(1, (int)(b/allload2.size()*100)+"");
		}else {
			pingjunallload.add(1, 0+"");
		}
		b=0.0;
		if (allload3.size()>0) {
			
			for (int j = 0; j < allload3.size(); j++) {
				if(allload3.get(j).length()==3){
					b+=Double.parseDouble(allload3.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallload.add(2, (int)(b/allload3.size()*100)+"");
		}else {
			pingjunallload.add(2, 0+"");
		}
		b=0.0;
		if (allload4.size()>0) {
			
			for (int j = 0; j < allload4.size(); j++) {
				if(allload4.get(j).length()==3){
					b+=Double.parseDouble(allload4.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallload.add(3, (int)(b/allload4.size()*100)+"");
		}else {
			pingjunallload.add(3, 0+"");
		}
		b=0.0;
		if (allload5.size()>0) {
			for (int j = 0; j < allload5.size(); j++) {
				if(allload5.get(j).length()==3){
					b+=Double.parseDouble(allload5.get(j).substring(0, 2))/100;
				}
			}
			pingjunallload.add(4, (int)(b/allload5.size()*100)+"");
		}else {
			pingjunallload.add(4, 0+"");
		}
		b=0.0;
		if (allload6.size()>0) {
			
			for (int j = 0; j < allload6.size(); j++) {
				if(allload6.get(j).length()==3){
					b+=Double.parseDouble(allload6.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallload.add(5, (int)(b/allload6.size()*100)+"");
		}else{
			pingjunallload.add(5, 0+"");
		}
			
		b=0.0;
		if (allload7.size()>0) {
			
			for (int j = 0; j < allload7.size(); j++) {
				if(allload7.get(j).length()==3){
					b+=Double.parseDouble(allload7.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallload.add(6, (int)(b/allload7.size()*100)+"");
		}else {
			pingjunallload.add(6, 0+"");
		}
		b=0.0;
		if (allload8.size()>0) {
			
			for (int j = 0; j < allload8.size(); j++) {
				if(allload8.get(j).length()==3){
					b+=Double.parseDouble(allload8.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallload.add(7, (int)(b/allload8.size()*100)+"");
		}else {
			pingjunallload.add(7, 0+"");
		}
		b=0.0;
		if (allload9.size()>0) {
			
			for (int j = 0; j < allload9.size(); j++) {
				if(allload9.get(j).length()==3){
					b+=Double.parseDouble(allload9.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallload.add(8, (int)(b/allload9.size()*100)+"");
		}else {
			pingjunallload.add(8, 0+"");
		}
		b=0.0;
		if (allload10.size()>0) {
			
			for (int j = 0; j < allload10.size(); j++) {
				if(allload10.get(j).length()==3){
					b+=Double.parseDouble(allload10.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallload.add(9, (int)(b/allload10.size()*100)+"");
		}else {
			pingjunallload.add(9,0+"");
		}
		b=0.0;
		if (allload11.size()>0) {
			
			for (int j = 0; j < allload11.size(); j++) {
				if(allload11.get(j).length()==3){
					b+=Double.parseDouble(allload11.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallload.add(10, (int)(b/allload11.size()*100)+"");
		}else {
			pingjunallload.add(10, 0+"");

		}
		b=0.0;
		if (allload12.size()>0) {
			
			for (int j = 0; j < allload12.size(); j++) {
				if(allload12.get(j).length()==3){
					b+=Double.parseDouble(allload12.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallload.add(11, (int)(b/allload12.size()*100)+"");
		}else {
			pingjunallload.add(11, 0+"");
		}
		b=0.0;
		if (allload13.size()>0) {
			for (int j = 0; j < allload13.size(); j++) {
				if(allload13.get(j).length()==3){
					b+=Double.parseDouble(allload13.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallload.add(12, (int)(b/allload13.size()*100)+"");
			
		}else{
			pingjunallload.add(12, 0+"");
		}
		b=0.0;
		if (allload14.size()>0) {
			
			for (int j = 0; j < allload14.size(); j++) {
				if(allload14.get(j).length()==3){
					b+=Double.parseDouble(allload14.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallload.add(13, (int)(b/allload14.size()*100)+"");
		}else {
			pingjunallload.add(13, 0+"");
		}
		b=0.0;
		if (allload15.size()>0) {
			
			for (int j = 0; j < allload15.size(); j++) {
				if(allload15.get(j).length()==3){
					b+=Double.parseDouble(allload15.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallload.add(14, (int)(b/allload15.size()*100)+"");
		}else {
			pingjunallload.add(14, 0+"");
		}
		b=0.0;
		if (allload16.size()>0) {
			for (int j = 0; j < allload16.size(); j++) {
				if(allload16.get(j).length()==3){
					b+=Double.parseDouble(allload16.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallload.add(15, (int)(b/allload16.size()*100)+"");
		}else {
			pingjunallload.add(15, 0+"");
		}
		b=0.0;
		if (allload17.size()>0) {
		for (int j = 0; j < allload17.size(); j++) {
			if(allload17.get(j).length()==3){
				b+=Double.parseDouble(allload17.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(16, (int)(b/allload17.size()*100)+"");
		}else {
			pingjunallload.add(16, 0+"");
		}
		b=0.0;
		if (allload18.size()>0) {
		for (int j = 0; j < allload18.size(); j++) {
			if(allload18.get(j).length()==3){
				b+=Double.parseDouble(allload18.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(17, (int)(b/allload18.size()*100)+"");
		}else {
			pingjunallload.add(17, 0+"");
		}
		b=0.0;
		if (allload19.size()>0) {
		for (int j = 0; j < allload19.size(); j++) {
			if(allload19.get(j).length()==3){
				b+=Double.parseDouble(allload19.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(18, (int)(b/allload19.size()*100)+"");
		}else {
			pingjunallload.add(18, 0+"");
		}
		b=0.0;
		if (allload20.size()>0) {
		for (int j = 0; j < allload20.size(); j++) {
			if(allload20.get(j).length()==3){
				b+=Double.parseDouble(allload20.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(19, (int)(b/allload20.size()*100)+"");
		}else {
			pingjunallload.add(19, 0+"");
		}
		b=0.0;
		if (allload21.size()>0) {
		for (int j = 0; j < allload21.size(); j++) {
			if(allload21.get(j).length()==3){
				b+=Double.parseDouble(allload21.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(20, (int)(b/allload21.size()*100)+"");
		}else {
			pingjunallload.add(20, 0+"");
		}
		b=0.0;
		if (allload22.size()>0) {
		for (int j = 0; j < allload22.size(); j++) {
			if(allload22.get(j).length()==3){
				b+=Double.parseDouble(allload22.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(21, (int)(b/allload22.size()*100)+"");
		}else {
			pingjunallload.add(21, 0+"");
		}
		b=0.0;
		if (allload23.size()>0) {
		for (int j = 0; j < allload23.size(); j++) {
			if(allload23.get(j).length()==3){
				b+=Double.parseDouble(allload23.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(22, (int)(b/allload23.size()*100)+"");
		}else {
			pingjunallload.add(22, 0+"");
		}
		b=0.0;
		if (allload24.size()>0) {
		for (int j = 0; j < allload24.size(); j++) {
			if(allload24.get(j).length()==3){
				b+=Double.parseDouble(allload24.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(23, (int)(b/allload24.size()*100)+"");
	}else {
		pingjunallload.add(23, 0+"");
	}
		b=0.0;
		if (allload25.size()>0) {
		for (int j = 0; j < allload25.size(); j++) {
			if(allload25.get(j).length()==3){
				b+=Double.parseDouble(allload25.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(24, (int)(b/allload25.size()*100)+"");
		}else {
			pingjunallload.add(24, 0+"");
		}
		b=0.0;
		if (allload26.size()>0) {
		for (int j = 0; j < allload26.size(); j++) {
			if(allload26.get(j).length()==3){
				b+=Double.parseDouble(allload26.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(25, (int)(b/allload26.size()*100)+"");
	}else {
		pingjunallload.add(25, 0+"");
	}
		b=0.0;
		if (allload27.size()>0) {
		for (int j = 0; j < allload27.size(); j++) {
			if(allload27.get(j).length()==3){
				b+=Double.parseDouble(allload27.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(26, (int)(b/allload27.size()*100)+"");
		}else {
			pingjunallload.add(26, 0+"");
		}
		b=0.0;
		if (allload28.size()>0) {
		for (int j = 0; j < allload28.size(); j++) {
			if(allload28.get(j).length()==3){
				b+=Double.parseDouble(allload28.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(27, (int)(b/allload28.size()*100)+"");
	}else {
		pingjunallload.add(27, 0+"");
	}
		b=0.0;
		if (allload29.size()>0) {
		for (int j = 0; j < allload29.size(); j++) {
			if(allload29.get(j).length()==3){
				b+=Double.parseDouble(allload29.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(28, (int)(b/allload29.size()*100)+"");
		}else {
			pingjunallload.add(28, 0+"");
		}
		b=0.0;
		if (allload30.size()>0) {
		for (int j = 0; j < allload30.size(); j++) {
			if(allload30.get(j).length()==3){
				b+=Double.parseDouble(allload30.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(29, (int)(b/allload30.size()*100)+"");
	}else {
		pingjunallload.add(29, 0+"");
	}
		b=0.0;
		if (allload31.size()>0) {
		for (int j = 0; j < allload31.size(); j++) {
			if(allload31.get(j).length()==3){
				b+=Double.parseDouble(allload31.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(30, (int)(b/allload31.size()*100)+"");
		}else {
			pingjunallload.add(30, 0+"");
		}
		b=0.0;
		if (allload32.size()>0) {
		for (int j = 0; j < allload32.size(); j++) {
			if(allload32.get(j).length()==3){
				b+=Double.parseDouble(allload32.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(31, (int)(b/allload32.size()*100)+"");
	}else {
		pingjunallload.add(31, 0+"");
	}
		b=0.0;
		if (allload33.size()>0) {
		for (int j = 0; j < allload33.size(); j++) {
			if(allload33.get(j).length()==3){
				b+=Double.parseDouble(allload33.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(32, (int)(b/allload33.size()*100)+"");
		}else {
			pingjunallload.add(32, 0+"");
		}
		b=0.0;
		if (allload34.size()>0) {
		for (int j = 0; j < allload34.size(); j++) {
			if(allload34.get(j).length()==3){
				b+=Double.parseDouble(allload34.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(33, (int)(b/allload34.size()*100)+"");
	}else {
		pingjunallload.add(33, 0+"");
	}
		b=0.0;
		if (allload35.size()>0) {
		for (int j = 0; j < allload35.size(); j++) {
			if(allload35.get(j).length()==3){
				b+=Double.parseDouble(allload35.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(34, (int)(b/allload35.size()*100)+"");
		}else {
			pingjunallload.add(34, 0+"");
		}
		b=0.0;
		if (allload36.size()>0) {
		for (int j = 0; j < allload36.size(); j++) {
			if(allload36.get(j).length()==3){
				b+=Double.parseDouble(allload36.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(35, (int)(b/allload36.size()*100)+"");
	}else {
		pingjunallload.add(35, 0+"");
	}
		b=0.0;
		if (allload37.size()>0) {
		for (int j = 0; j < allload37.size(); j++) {
			if(allload37.get(j).length()==3){
				b+=Double.parseDouble(allload37.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(36, (int)(b/allload37.size()*100)+"");
		}else {
			pingjunallload.add(36, 0+"");
		}
		b=0.0;
		if (allload38.size()>0) {
		for (int j = 0; j < allload38.size(); j++) {
			if(allload38.get(j).length()==3){
				b+=Double.parseDouble(allload38.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(37, (int)(b/allload38.size()*100)+"");
	}else {
		pingjunallload.add(37, 0+"");
	}
		b=0.0;
		if (allload39.size()>0) {
		for (int j = 0; j < allload39.size(); j++) {
			if(allload39.get(j).length()==3){
				b+=Double.parseDouble(allload39.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(38, (int)(b/allload39.size()*100)+"");
		}else {
			pingjunallload.add(38, 0+"");
		}
		b=0.0;
		if (allload40.size()>0) {
		for (int j = 0; j < allload40.size(); j++) {
			if(allload40.get(j).length()==3){
				b+=Double.parseDouble(allload40.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(39, (int)(b/allload40.size()*100)+"");
	}else {
		pingjunallload.add(39, 0+"");
	}
		b=0.0;
		if (allload41.size()>0) {
		for (int j = 0; j < allload41.size(); j++) {
			if(allload41.get(j).length()==3){
				b+=Double.parseDouble(allload41.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(40, (int)(b/allload41.size()*100)+"");
		}else {
			pingjunallload.add(40, 0+"");
		}
		b=0.0;
		if (allload42.size()>0) {
		for (int j = 0; j < allload42.size(); j++) {
			if(allload42.get(j).length()==3){
				b+=Double.parseDouble(allload42.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(41, (int)(b/allload42.size()*100)+"");
	}else {
		pingjunallload.add(41, 0+"");
	}
		b=0.0;
		if (allload43.size()>0) {
		for (int j = 0; j < allload43.size(); j++) {
			if(allload43.get(j).length()==3){
				b+=Double.parseDouble(allload43.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(42, (int)(b/allload43.size()*100)+"");
		}else {
			pingjunallload.add(42, 0+"");
		}
		b=0.0;
		if (allload44.size()>0) {
		for (int j = 0; j < allload44.size(); j++) {
			if(allload44.get(j).length()==3){
				b+=Double.parseDouble(allload44.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(43, (int)(b/allload44.size()*100)+"");
	}else {
		pingjunallload.add(43, 0+"");
	}
		b=0.0;
		if (allload45.size()>0) {
		for (int j = 0; j < allload45.size(); j++) {
			if(allload45.get(j).length()==3){
				b+=Double.parseDouble(allload45.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(44, (int)(b/allload45.size()*100)+"");
		}else {
			pingjunallload.add(44, 0+"");
		}
		b=0.0;
		if (allload46.size()>0) {
		for (int j = 0; j < allload46.size(); j++) {
			if(allload46.get(j).length()==3){
				b+=Double.parseDouble(allload46.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(45, (int)(b/allload46.size()*100)+"");
	}else {
		pingjunallload.add(45, 0+"");
	}
		b=0.0;
		if (allload47.size()>0) {
		for (int j = 0; j < allload47.size(); j++) {
			if(allload47.get(j).length()==3){
				b+=Double.parseDouble(allload47.get(j).substring(0, 2))/100;
			}
			
		}
		pingjunallload.add(46, (int)(b/allload47.size()*100)+"");
		}else {
			pingjunallload.add(46, 0+"");
		}
		b=0.0;
		if (allload48.size()>0) {
			for (int j = 0; j < allload48.size(); j++) {
				if(allload48.get(j).length()==3){
					b+=Double.parseDouble(allload48.get(j).substring(0, 2))/100;
				}
				
			}
			pingjunallload.add(47, (int)(b/allload48.size()*100)+"");
		}else {
			pingjunallload.add(47, 0+"");
		}
		l.setPjonline(pingjunonline);
		l.setPjallonline(pingjunallonline);
		l.setPjload(pingjunload);
		l.setPjallload(pingjunallload);
		return l;
	}
}
