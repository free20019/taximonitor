package com.tw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tw.entity.Condition;
import com.tw.entity.OperatingData;
import com.tw.util.DataBese;

/**
 * 
 * @author sven.zhang
 * @since 2015.4.13
 *	excel文件下载数据查询
 *
 */
public class DataExcelDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private DecimalFormat df =new DecimalFormat("0.00");
	private Date d=new Date();
	private SimpleDateFormat sdformat =new SimpleDateFormat("yyyy");
	private String time=sdformat.format(d);
	//按车辆营运交易查询
	public List<OperatingData> getVehicle(Condition condition,String data,String realname){
//		String datt = condition.getStartTime().substring(0, 4);
//		String table_name="";
//		if(realname.indexOf("余杭")>=0||realname.indexOf("临平")>=0||realname.indexOf("临安")>=0){
//			table_name = "JIJIAQI.TB_CITIZEN_"+datt;
//		}else{
//			table_name = "HZGPS_CITIZEN.TB_CITIZEN_"+datt+"@TAXILINK45";
//		}
		List<OperatingData> list = new ArrayList<OperatingData>();
		String[] d = data.split(",");
		String comp = "";
		for (int i = 0; i < d.length; i++) {
			comp += "'"+d[i]+"',";
		}
		comp=comp.substring(0, comp.length()-1);
//		StringBuffer sql = new StringBuffer("select * from(select p.*,rownum rn from(select t1.*,v1.*from ");
//		sql.append("(select vhic,");
//		sql.append("sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jine, '$%', ' '),' ',''),0))," +
//				"upper(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(" +
//				"translate(jine, '$%', ' '),' ',''),0),0))) st,count(vhic) ct,sum(TO_NUMBER(" +
//				"decode(decode(lower(nvl(replace(translate(substr(denghou,-2,2), '$%', ' '),' ',''),0))" +
//				",upper(nvl(replace(translate(substr(denghou,-2,2), '$%', ' '),' ',''),0)),1,0),1," +
//				"nvl(replace(translate(substr(denghou,-2,2), '$%', ' '),' ',''),0),0))+TO_NUMBER" +
//				"(decode(decode(lower(nvl(replace(translate(substr(denghou,-4,2), '$%', ' '),' ',''),0))" +
//				",upper(nvl(replace(translate(substr(denghou,-4,2), '$%', ' '),' ',''),0)),1,0),1," +
//				"nvl(replace(translate(substr(denghou,-4,2), '$%', ' '),' ',''),0),0))*60+ " +
//				"TO_NUMBER(decode(decode(lower(nvl(replace(translate(substr(denghou,-6,2), " +
//				"'$%', ' '),' ',''),0)),upper(nvl(replace(translate(substr(denghou,-6,2), '$%', ' ')" +
//				",' ',''),0)),1,0),1,nvl(replace(translate(substr(denghou,-6,2), '$%', ' '),' ',''),0),0)" +
//				")*3600) waitTime, sum(TO_NUMBER(decode(decode(lower(nvl(replace" +
//				"(translate(jicheng, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jicheng, '$%', ' ')" +
//				",' ',''),0)),1,0),1,nvl(replace(translate(jicheng, '$%', ' '),' ',''),0),0))) distance, " +
//				"sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(kongshi, '$%', ' ')" +
//				",' ',''),0)),upper(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),1,0),1," +
//				"nvl(replace(translate(kongshi, '$%', ' '),' ',''),0),0))) empty1, " +
//				"sum((xiache-shangche)*60*24*60)time1");
//		sql.append(" from  "+table_name+" t  where shangche>=to_date(");
//		sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
//		sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss')group by vhic)t1,");
//		sql.append("(select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v  where   1=1 ");
//		sql.append(" and comp_name in ("+comp+")");
//		if (condition.getCompany() != null)
//			sql.append(" and ba_name='" + condition.getCompany() + "' ");
//		if (condition.getBranch() != null)
//			sql.append(" and comp_name='" + condition.getBranch() + "' ");
//		if (condition.getCardNo() != null&&condition.getCardNo().length()>0)
//			sql.append(" and vehi_no='" + condition.getCardNo() + "'");		
//		sql.append(")v1 where '浙'||t1.vhic =v1.vehi_no order by ba_name asc,comp_name asc,vehi_no asc )p ");
//		sql.append(")  " );
		StringBuffer sql = new StringBuffer();
		String st = condition.getStartTime().replaceAll("-", "");
		String et = condition.getEndTime().replaceAll("-", "");
		String tab = "jjq.JJQ_TJ_"+st.substring(0, 6)+"_DAY@taxilink89";
		sql.append("select t1.*,rownum rn from (select cphm vehi_no,zgs ba_name,fgs comp_name,sum(tjcs) ct,sum(jine) * 100 st"
				+ ",sum(szlc) * 10 distance,sum(kslc)*10 empty1,sum(yssc) * 60 time1,sum(dhsj) * 60 waitTime"
				+ " from "+tab+" where type = '5'");
		sql.append(" and day >='"+st+"'");
		sql.append(" and day <='"+et+"'");
		sql.append(" and fgs in ("+comp+")");
		if (condition.getCompany() != null)
			sql.append(" and zgs='" + condition.getCompany() + "' ");
		if (condition.getBranch() != null)
			sql.append(" and fgs='" + condition.getBranch() + "' ");
		if (condition.getCardNo() != null&&condition.getCardNo().length()>0)
			sql.append(" and '浙'||cphm='" + condition.getCardNo() + "'");	
		sql.append("group by cphm, zgs, fgs order by comp_name,cphm) t1 ");
		System.out.println(sql);
		con = DataBese.getConnectionOfOracle();
		try {
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			int hour = 0,minuts = 0,miao = 0;
			while (rs.next()) {
				OperatingData b = new OperatingData();	
				float distance = rs.getFloat("distance")/10;
				float empty = rs.getFloat("empty1")/10;
				b.setCompany(rs.getString("ba_name"));
				b.setBranch(rs.getString("comp_name"));				
				b.setNumber(rs.getInt("rn"));
				b.setVhic(rs.getString("vehi_no"));
				b.setMoney(rs.getFloat("st") / 100);
				b.setTimes(rs.getInt("ct"));
				b.setTotalDis(df.format(distance+empty));
				b.setYpercent(df.format(distance/(empty+distance)*100)+"%");
				b.setDistance(rs.getFloat("distance")/10);
				b.setTimeOut(rs.getInt("time1"));
				b.setEmpty(rs.getFloat("empty1")/10);
//				if(realname.indexOf("余杭")>=0||realname.indexOf("临平")>=0||realname.indexOf("临安")>=0){
//					hour = Integer.parseInt(rs.getString("waitTime").substring(0, 2));
//					minuts = Integer.parseInt(rs.getString("waitTime").substring(2, 4));
//					miao = Integer.parseInt(rs.getString("waitTime").substring(4, 6));
//					b.setWaitTime(hour*3600+minuts*60+miao);
//				}else{
					b.setWaitTime(rs.getInt("waitTime"));
//				}
				list.add(b);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;
	}
	
		// 按照资格证号统计查询
		public List<OperatingData> getDataByCetNo(Condition condition) {
			List<OperatingData> list = new ArrayList<OperatingData>();
			String datt = condition.getStartTime().substring(0, 4);
			StringBuffer sql = new StringBuffer("select p.*, rownum rn from(select t1.*,v1.* from ");
			sql.append("(select yingyun,sum(jine) st,count(yingyun) ct,sum(substr(denghou,-2,2)+substr(denghou,-4,2)*60+substr(denghou,-6,2)*3600) waitTime,sum(jicheng) distance,sum(kongshi)empty1, sum((xiache-shangche)*60*24*60)time1");
			sql.append(" from  HZGPS_CITIZEN.TB_CITIZEN_"+datt+"@TAXILINK45 t  where shangche>=to_date(");
			sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
			sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss')");
			if(condition.getCertNo() != null)
				sql.append(" and yingyun='" + condition.getCertNo() + "'");
			sql.append(" group by yingyun)t1,");
			sql.append(" (select vhic ,max( distinct yingyun)yy from  HZGPS_CITIZEN.TB_CITIZEN_"+datt+"@TAXILINK45 t where shangche>=to_date(");
			sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
			sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss')group by vhic)p,");
			sql.append("(select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v  where  1=1 ");	
			if (condition.getCompany() != null)
				sql.append(" and ba_name='" + condition.getCompany() + "' ");
			if (condition.getBranch() != null)
				sql.append(" and comp_name='" + condition.getBranch() + "' ");	
			sql.append(")v1 where '浙'||p.vhic =v1.vehi_no and p.yy = t1.yingyun order by ba_name asc,comp_name asc,yingyun asc )p");
			con = DataBese.getConnectionOfOracle();
			try {
				ps = con.prepareStatement(sql.toString());
				rs = ps.executeQuery();
				int hour = 0,minuts = 0,miao = 0;
				while (rs.next()) {
					OperatingData b = new OperatingData();	
					float distance = rs.getFloat("distance")/10;
					float empty = rs.getFloat("empty1")/10;
					b.setCompany(rs.getString("ba_name"));
					b.setBranch(rs.getString("comp_name"));				
					b.setNumber(rs.getInt("rn"));
					b.setCertNo(rs.getString("yingyun"));
					b.setMoney(rs.getFloat("st") / 100);
					b.setTimes(rs.getInt("ct"));				
					b.setDistance(rs.getFloat("distance")/10);
					b.setTotalDis(df.format(distance+empty));
					b.setYpercent(df.format(distance/(empty+distance)*100)+"%");
					b.setTimeOut(rs.getInt("time1"));
					b.setEmpty(rs.getFloat("empty1")/10);
					hour = Integer.parseInt(rs.getString("waitTime").substring(0, 2));
					minuts = Integer.parseInt(rs.getString("waitTime").substring(2, 4));
					miao = Integer.parseInt(rs.getString("waitTime").substring(4, 6));
					b.setWaitTime(hour*3600+minuts*60+miao);
					list.add(b);
				}
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

			return list;
		}
		//按照公司查询数据统计
				public List<OperatingData> getDataByCompany(Condition condition) {
					String datt = condition.getStartTime().substring(0, 4);
					List<OperatingData> list = new ArrayList<OperatingData>();
					StringBuffer sql = new StringBuffer("select p1.*, rownum rn from(select p.*,s.ba_name ,f.total from(");
					sql.append("select sum(st) st,sum(ct) ct,count(vhic)qt,sum(waitTime) waitTime,sum(distance) distance,sum(empty1)empty1, sum(time1)time1,comp_name from( ");
					sql.append("select t1.*,v1.* from ");			
					sql.append("(select vhic,sum(jine) st,count(vhic) ct,sum(substr(denghou,-2,2)+substr(denghou,-4,2)*60+substr(denghou,-6,2)*3600) waitTime,sum(jicheng) distance,sum(kongshi)empty1, sum((xiache-shangche)*60*24*60)time1");			
					sql.append(" from  HZGPS_CITIZEN.TB_CITIZEN_"+datt+"@TAXILINK45 t  where shangche>=to_date(");
					sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
					sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss')group by vhic)t1,");
					sql.append("(select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v  where  1=1 ");	
					if (condition.getCompany() != null)				
						sql.append(" and comp_name='" + condition.getCompany() + "' ");
					if (condition.getBranch() != null)
						sql.append(" and comp_name='" + condition.getBranch() + "' ");					
					sql.append(")v1 where '浙'||t1.vhic =v1.vehi_no )group by comp_name )p,");
					sql.append("(select comp_name,max(distinct ba_name)ba_name from HZGPS_TAXI.TB_COMPANY@TAXILINK t ,HZGPS_TAXI.TB_BUSI_AREA@TAXILINK v where v.ba_id =t.ba_id group by comp_name )s,");
					sql.append("(select count(vehi_no)total,comp_name from HZGPS_TAXI.VW_VEHICLE@TAXILINK v group by comp_name)f");
					sql.append(" where p.comp_name =s.comp_name and f.comp_name=s.comp_name )p1");
					con = DataBese.getConnectionOfOracle();
					try {
						ps = con.prepareStatement(sql.toString());
						rs = ps.executeQuery();
						int hour = 0,minuts = 0,miao = 0;
						while (rs.next()) {
							OperatingData b = new OperatingData();
							float total = (rs.getFloat("distance")+rs.getFloat("empty1"))/10;
							float per = rs.getFloat("distance")/total*10;
							b.setDriving(rs.getInt("qt"));
							b.setTotal(rs.getInt("total"));
							b.setCompany(rs.getString("ba_name"));
							b.setBranch(rs.getString("comp_name"));
							b.setNumber(rs.getInt("rn"));
							b.setMoney(rs.getFloat("st") / 100);
							b.setTimes(rs.getInt("ct"));
							b.setTotalDis(total+"");
							b.setYpercent(df.format(per)+"%");
							b.setDistance(rs.getFloat("distance")/10);
							b.setTimeOut(rs.getInt("time1"));
							b.setEmpty(rs.getFloat("empty1")/10);
							hour = Integer.parseInt(rs.getString("waitTime").substring(0, 2));
							minuts = Integer.parseInt(rs.getString("waitTime").substring(2, 4));
							miao = Integer.parseInt(rs.getString("waitTime").substring(4, 6));
							b.setWaitTime(hour*3600+minuts*60+miao);
							list.add(b);
						}
						rs.close();
						ps.close();
						con.close();
					} catch (SQLException e) {

						e.printStackTrace();
					}
					return list;
				}
				//查询群组数据
				public List<OperatingData> getDataByGroup(Condition condition){
					String datt = condition.getStartTime().substring(0, 4);
					List<OperatingData> list = new ArrayList<OperatingData>();
					StringBuffer sql = new StringBuffer("select g.*,rownum rn from(select p.*,f.total from(");
					sql.append("select sum(st) st,sum(ct) ct,count(vhic)qt,sum(waitTime) waitTime,sum(distance) distance,sum(empty1)empty1, sum(time1)time1,group_name from( ");
					sql.append("select t1.*,v1.* from ");			
					sql.append("(select vhic,sum(jine) st,count(vhic) ct,sum(substr(denghou,-2,2)+substr(denghou,-4,2)*60+substr(denghou,-6,2)*3600) waitTime,sum(jicheng) distance,sum(kongshi)empty1, sum((xiache-shangche)*60*24*60)time1");			
					sql.append(" from  HZGPS_CITIZEN.TB_CITIZEN_"+datt+"@TAXILINK45 t  where shangche>=to_date(");
					sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
					sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss')group by vhic)t1,");
					sql.append("(select tb.group_vhic,ts.* from tb_taxi_vehicle_groupname ts,tb_taxi_vehicle_group tb  where  tb.group_id = ts.group_id  ");	
					if (condition.getGroup() != null)				
						sql.append(" and ts.group_id='" + condition.getGroup() + "' ");
					sql.append(")v1 where '浙'||t1.vhic =v1.group_vhic)group by group_name)p, ");
					sql.append("(select count(group_vhic)total ,group_name from tb_taxi_vehicle_group tb ,tb_taxi_vehicle_groupname tn where tb.group_id=tn.group_id group by group_name )f where f.group_name = p.group_name");
					sql.append(" order by p.group_name)g");
					con = DataBese.getConnectionOfOracle();
					try {
						ps = con.prepareStatement(sql.toString());
						rs = ps.executeQuery();
						int hour = 0,minuts = 0,miao = 0;
						while (rs.next()) {
							OperatingData b = new OperatingData();
							float total = (rs.getFloat("distance")+rs.getFloat("empty1"))/10;
							float per = rs.getFloat("distance")/total*10;
							b.setDriving(rs.getInt("qt"));
							b.setTotal(rs.getInt("total"));
							b.setVpercent(df.format(100*rs.getInt("qt")/rs.getInt("total"))+"%");
							b.setGroup(rs.getString("group_name"));
							b.setNumber(rs.getInt("rn"));
							b.setMoney(rs.getFloat("st") / 100);
							b.setTimes(rs.getInt("ct"));
							b.setTotalDis(total+"");
							b.setYpercent(df.format(per)+"%");
							b.setDistance(rs.getFloat("distance")/10);
							b.setTimeOut(rs.getInt("time1"));
							b.setEmpty(rs.getFloat("empty1")/10);
							hour = Integer.parseInt(rs.getString("waitTime").substring(0, 2));
							minuts = Integer.parseInt(rs.getString("waitTime").substring(2, 4));
							miao = Integer.parseInt(rs.getString("waitTime").substring(4, 6));
							b.setWaitTime(hour*3600+minuts*60+miao);
							list.add(b);
						}
						rs.close();
						ps.close();
						con.close();
					} catch (SQLException e) {

						e.printStackTrace();
					}

						return list;
					
				}
				//查询时段交易记录
				public List<OperatingData> getBusiness(Condition condition,String data,String realname){
					String table_name="";
					String datt = condition.getStartTime().substring(0, 4);
					String dd = condition.getStartTime().substring(0,4)+condition.getStartTime().substring(5,7);
					if(realname.indexOf("余杭")>=0||realname.indexOf("临平")>=0||realname.indexOf("临安")>=0){
						table_name = "JIJIAQI.TB_CITIZEN_"+datt;
					}else{
						table_name = "jjq.JJQ"+dd+"_1@taxilink89";
					}
					String[] d = data.split(",");
					String comp = "";
					for (int i = 0; i < d.length; i++) {
						comp += "'"+d[i]+"',";
					}
					comp=comp.substring(0, comp.length()-1);
					List<OperatingData> list = new ArrayList<OperatingData>();
					StringBuffer sql = new StringBuffer("select * from(select p.* ,rownum rn from(select t1.*,v1.*from");
					sql.append("(select * from  "+table_name+" t  where shangche>=to_date(");
					sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
					sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss')");
					if(realname.indexOf("余杭")>=0||realname.indexOf("临平")>=0||realname.indexOf("临安")>=0){
					}else{
						sql.append(" and flag = '1000000000'");
					}
					sql.append(")t1,(select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v  where  1=1  ");
						if (condition.getCompany() != null){
							sql.append(" and comp_name='" + condition.getCompany() + "' ");
						}else {
							sql.append(" and comp_name in ("+comp+")");
						}
					if (condition.getBranch() != null){
						sql.append(" and comp_name='" + condition.getBranch() + "' ");
					}
					if (condition.getCardNo() != null&&condition.getCardNo().length()>0){
						sql.append(" and vehi_no='" + condition.getCardNo() + "'");		
					}
					sql.append(")v1 where '浙'||t1.vhic =v1.vehi_no )p )");
					System.out.println(sql);
					con = DataBese.getConnectionOfOracle();
					try {
						ps = con.prepareStatement(sql.toString());
						rs = ps.executeQuery();
						int hour = 0,minuts = 0,miao = 0;
						while (rs.next()) {
							OperatingData b = new OperatingData();								
							b.setNumber(rs.getInt("rn"));
							b.setVhic(rs.getString("vehi_no"));
							b.setCertNo(rs.getString("yingyun"));
							b.setUpTaxi(rs.getString("shangche").substring(0, rs.getString("shangche").length()-2));
							b.setDownTaxi(rs.getString("xiache").substring(0, rs.getString("xiache").length()-2));
							b.setMoney(rs.getFloat("jine")/100);									
							b.setDistance(rs.getFloat("jicheng")/10);
							b.setEmpty(rs.getFloat("kongshi")/10);
							hour = Integer.parseInt(rs.getString("denghou").substring(0, 2));
							minuts = Integer.parseInt(rs.getString("denghou").substring(2, 4));
							miao = Integer.parseInt(rs.getString("denghou").substring(4, 6));
							b.setWaitTime(hour*3600+minuts*60+miao);
							b.setType(rs.getString("jiaoyitype"));
							b.setTime(rs.getString("zhongxin").substring(0, rs.getString("zhongxin").length()-2));
							list.add(b);
						}
						rs.close();
						ps.close();
						con.close();
					} catch (SQLException e) {

						e.printStackTrace();
					}

					
					return list;
				}
				//群组交易查询
				public List<OperatingData> getGroupsel(Condition condition){
					List<OperatingData> list = new ArrayList<OperatingData>();
					String datt = condition.getStartTime().substring(0, 4);
					StringBuffer sql = new StringBuffer("");
					sql.append("select g.*,rownum rn from(select t1.*,v.* from(select * from  HZGPS_CITIZEN.TB_CITIZEN_"+datt+"@TAXILINK45 t  where shangche>=to_date(");
					sql.append("'"+condition.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
					sql.append("'"+condition.getEndTime()+"','yyyy-MM-dd hh24:mi:ss'))t1,");
					sql.append("(select * from tb_taxi_vehicle_group tb where 1= 1");
					if(condition.getGroup() != null)
						sql.append(" and group_id='"+condition.getGroup()+"'");
					if(condition.getCardNo() != null)
						sql.append("and group_vhic='"+condition.getCardNo()+"'");
					sql.append(")v where '浙'||t1.vhic = v.group_vhic order by shangche asc ,vhic asc)g ");
//					System.out.println(sql);
					con = DataBese.getConnectionOfOracle();
					try {
						ps = con.prepareStatement(sql.toString());
						rs = ps.executeQuery();
						int hour = 0,minuts = 0,miao = 0;
						while (rs.next()) {
							OperatingData b = new OperatingData();								
							b.setNumber(rs.getInt("rn"));
							b.setVhic(rs.getString("group_vhic"));
							b.setCertNo(rs.getString("yingyun"));
							b.setUpTaxi(rs.getString("shangche").substring(0, rs.getString("shangche").length()-2));
							b.setDownTaxi(rs.getString("xiache").substring(0, rs.getString("xiache").length()-2));
							b.setMoney(rs.getFloat("jine") / 100);				
							b.setDistance(rs.getFloat("jicheng")/10);
							b.setEmpty(rs.getFloat("kongshi")/10);
							hour = Integer.parseInt(rs.getString("waitTime").substring(0, 2));
							minuts = Integer.parseInt(rs.getString("waitTime").substring(2, 4));
							miao = Integer.parseInt(rs.getString("waitTime").substring(4, 6));
							b.setWaitTime(hour*3600+minuts*60+miao);
							b.setType(rs.getString("jiaoyitype"));
							list.add(b);
						}
						rs.close();
						ps.close();
						con.close();
					} catch (SQLException e) {

						e.printStackTrace();
					}

					
					return list;
				}
}
