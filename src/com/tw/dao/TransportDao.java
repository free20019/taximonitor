package com.tw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tw.entity.Complaint;
import com.tw.entity.Illegal;
import com.tw.entity.ServiceQuality;
import com.tw.entity.Vhic;
import com.tw.util.DataBese;
import com.tw.util.PageHelper;

public class TransportDao {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public Integer findillegalcount(String compname) {
		String sql = "select count(*) COUNT from (select distinct b.AUTO_NUM from (select ti.*,gv.AREA_NAME from tb_global_vehicle@TAXILINK69 gv,TB_TAXI_ILLEGAL_INFO_OUT@TAXILINK69 ti where REPLACE(ti.AUTO_NUM,'.','')=gv.plate_number and gv.industry=090 and gv.business_scope_code = 1400  AND gv.STATUS_NAME='营运' AND ti.AUTO_NUM LIKE '浙A%') b where 1=1 ";
		if(compname!=null&&!compname.isEmpty()&&!compname.equals("null")&&compname.length()>0){
			sql+=" and b.COM_NAME = '"+compname+"'";
		}
		sql+=")";
		con = DataBese.getConnectionOfOracle();
		int t=0;
		try {
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				t=rs.getInt("COUNT");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return t;
	}

	public List<Illegal> findillegal(String compname, PageHelper page) {
		List<Illegal>list=new ArrayList<Illegal>();
		String sql = "select tt.* from (select t.*,b.COM_NAME,b.IC_SCORE ,rownum as rn from (select "
				+ "b.AUTO_NUM,count(b.AUTO_NUM) as count1 from (select ti.*,gv.AREA_NAME from tb_global_vehicle@TAXILINK69 gv,TB_TAXI_ILLEGAL_INFO_OUT@TAXILINK69 ti where REPLACE(ti.AUTO_NUM,'.','')=gv.plate_number and gv.industry=090 and gv.business_scope_code = 1400  AND gv.STATUS_NAME='营运' AND ti.AUTO_NUM LIKE '浙A%') b where 1=1 ";
		if(compname!=null&&!compname.isEmpty()&&!compname.equals("null")&&compname.length()>0){
			sql+=" and b.COM_NAME = '"+compname+"'";
		}
		sql +="  group by b.AUTO_NUM";
		sql += " ) t left join ( select b.id,b.AUTO_NUM,b.COM_NAME ,b.IC_SCORE from (select ti.*,gv.AREA_NAME, row_number() over (partition by ti.AUTO_NUM  order by ti.id desc) as rnum from tb_global_vehicle@TAXILINK69 gv,TB_TAXI_ILLEGAL_INFO_OUT@TAXILINK69 ti where REPLACE(ti.AUTO_NUM,'.','')=gv.plate_number and gv.industry=090 and gv.business_scope_code = 1400  AND gv.STATUS_NAME='营运' AND ti.AUTO_NUM LIKE '浙A%') b where rnum = 1"
				+ " ) b on  b.AUTO_NUM=t.AUTO_NUM where rownum <= "+(page.getCurrentPage()*page.getPageSize())+") tt where tt.rn > "+((page.getCurrentPage()-1)*page.getPageSize());
		
	
		System.out.println(sql);
		Connection con;
		PreparedStatement ps;
		ResultSet rs;
		try {
			con=DataBese.getConnectionOfOracle();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Illegal v=new Illegal();
				v.setAuto_num(rs.getString("AUTO_NUM")==null?"":rs.getString("AUTO_NUM").toString().replace(".",""));
				v.setCom_name(rs.getString("COM_NAME")==null?"":rs.getString("COM_NAME"));
				v.setCount(rs.getString("COUNT1")==null?"":rs.getString("COUNT1"));
				v.setIc_score(rs.getString("IC_SCORE")==null?"":rs.getString("IC_SCORE"));
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

	public List<Illegal> findillegalexcle(String compname) {
		List<Illegal>list=new ArrayList<Illegal>();
		String sql = "select t.*,b.COM_NAME,b.IC_SCORE ,rownum as rn from (select "
				+ "b.AUTO_NUM,count(b.AUTO_NUM) as count1 from (select ti.*,gv.AREA_NAME from tb_global_vehicle@TAXILINK69 gv,TB_TAXI_ILLEGAL_INFO_OUT@TAXILINK69 ti where REPLACE(ti.AUTO_NUM,'.','')=gv.plate_number and gv.industry=090 and gv.business_scope_code = 1400  AND gv.STATUS_NAME='营运' AND ti.AUTO_NUM LIKE '浙A%') b where 1=1 ";
		if(compname!=null&&!compname.isEmpty()&&!compname.equals("null")&&compname.length()>0){
			sql+=" and b.COM_NAME = '"+compname+"'";
		}
		sql +="  group by b.AUTO_NUM";
		sql += " ) t left join ( select b.id,b.AUTO_NUM,b.COM_NAME ,b.IC_SCORE from (select ti.*,gv.AREA_NAME, row_number() over (partition by ti.AUTO_NUM  order by ti.id desc) as rnum from tb_global_vehicle@TAXILINK69 gv,TB_TAXI_ILLEGAL_INFO_OUT@TAXILINK69 ti where REPLACE(ti.AUTO_NUM,'.','')=gv.plate_number and gv.industry=090 and gv.business_scope_code = 1400  AND gv.STATUS_NAME='营运' AND ti.AUTO_NUM LIKE '浙A%') b where rnum = 1"
				+ " ) b on  b.AUTO_NUM=t.AUTO_NUM ";
		
	
		System.out.println(sql);
		Connection con;
		PreparedStatement ps;
		ResultSet rs;
		try {
			con=DataBese.getConnectionOfOracle();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Illegal v=new Illegal();
				v.setAuto_num(rs.getString("AUTO_NUM")==null?"":rs.getString("AUTO_NUM").toString().replace(".",""));
				v.setCom_name(rs.getString("COM_NAME")==null?"":rs.getString("COM_NAME"));
				v.setCount(rs.getString("COUNT1")==null?"":rs.getString("COUNT1"));
				v.setIc_score(rs.getString("IC_SCORE")==null?"":rs.getString("IC_SCORE"));
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
	
	public int findcomplaintcount(String stime, String etime, String type) {
		String sql = "select count(*) COUNT from global_complaint_12328@TAXILINK69 where 1=1 ";
		if(type != null&&!type.isEmpty()&&type.length()>0&&!type.equals("")){
            sql += "and BUSINESS_ITEMTYPE_NAME like '%"+type+"%'";
        }
        if(stime != null&&!stime.isEmpty()&&stime.length()>0&&!stime.equals("")){
            sql += "and ACCEPT_TIME >= to_date('"+stime+"','yyyy-mm-dd hh24:mi:ss')";
        }
        if(etime != null&&!etime.isEmpty()&&etime.length()>0&&!etime.equals("")){
            sql += "and ACCEPT_TIME <= to_date('"+etime+"','yyyy-mm-dd hh24:mi:ss')";
        }
		con = DataBese.getConnectionOfOracle();
		int t=0;
		try {
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				t=rs.getInt("COUNT");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return t;
	}

	public List<Complaint> findcomplaint(String stime, String etime, String type, PageHelper page2) {
		List<Complaint>list=new ArrayList<Complaint>();
		String sql = "select tt.* from (select c.*,rownum as rn from global_complaint_12328@TAXILINK69 c where 1=1 ";
		if(type != null&&!type.isEmpty()&&type.length()>0&&!type.equals("")){
            sql += "and BUSINESS_ITEMTYPE_NAME like '%"+type+"%'";
        }
        if(stime != null&&!stime.isEmpty()&&stime.length()>0&&!stime.equals("")){
            sql += "and ACCEPT_TIME >= to_date('"+stime+" 00:00:00','yyyy-mm-dd hh24:mi:ss')";
        }
        if(etime != null&&!etime.isEmpty()&&etime.length()>0&&!etime.equals("")){
            sql += "and ACCEPT_TIME <= to_date('"+etime+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
        }
        sql +=" and rownum <= "+(page2.getCurrentPage()*page2.getPageSize())+" order by ACCEPT_TIME desc) tt where tt.rn > "+((page2.getCurrentPage()-1)*page2.getPageSize());
		System.out.println(sql);
		Connection con;
		PreparedStatement ps;
		ResultSet rs;
		try {
			con=DataBese.getConnectionOfOracle();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Complaint v=new Complaint();
				v.setCALL_NAME(rs.getString("CALL_NAME")==null?"":rs.getString("CALL_NAME"));
				v.setBUSINESS_ITEMTYPE_NAME(rs.getString("BUSINESS_ITEMTYPE_NAME")==null?"":rs.getString("BUSINESS_ITEMTYPE_NAME").replace("运管_客运_出租客运_", ""));
				v.setCALLER_ID(rs.getString("CALLER_ID")==null?"":rs.getString("CALLER_ID"));
				v.setVEHICLE_PLATE_NUMBER(rs.getString("VEHICLE_PLATE_NUMBER")==null?"":rs.getString("VEHICLE_PLATE_NUMBER"));
				v.setBUSINESS_STATUS_NAME(rs.getString("BUSINESS_STATUS_NAME")==null?"":rs.getString("BUSINESS_STATUS_NAME"));
				v.setARCHIVE_TIME(rs.getString("ARCHIVE_TIME")==null?"":rs.getString("ARCHIVE_TIME").substring(0, 19));
				v.setACCEPT_TIME(rs.getString("ACCEPT_TIME")==null?"":rs.getString("ACCEPT_TIME").substring(0, 19));
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

	public List<Complaint> findcomplaintexcle(String stime, String etime,
			String type) {
		List<Complaint>list=new ArrayList<Complaint>();
		String sql = "select c.* from global_complaint_12328@TAXILINK69 c where 1=1 ";
		if(type != null&&!type.isEmpty()&&type.length()>0&&!type.equals("")){
            sql += "and BUSINESS_ITEMTYPE_NAME like '%"+type+"%'";
        }
        if(stime != null&&!stime.isEmpty()&&stime.length()>0&&!stime.equals("")){
            sql += "and ACCEPT_TIME >= to_date('"+stime+" 00:00:00','yyyy-mm-dd hh24:mi:ss')";
        }
        if(etime != null&&!etime.isEmpty()&&etime.length()>0&&!etime.equals("")){
            sql += "and ACCEPT_TIME <= to_date('"+etime+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
        }
        sql +=" order by ACCEPT_TIME desc";
		System.out.println(sql);
		Connection con;
		PreparedStatement ps;
		ResultSet rs;
		try {
			con=DataBese.getConnectionOfOracle();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Complaint v=new Complaint();
				v.setCALL_NAME(rs.getString("CALL_NAME")==null?"":rs.getString("CALL_NAME"));
				v.setBUSINESS_ITEMTYPE_NAME(rs.getString("BUSINESS_ITEMTYPE_NAME")==null?"":rs.getString("BUSINESS_ITEMTYPE_NAME").replace("运管_客运_出租客运_", ""));
				v.setCALLER_ID(rs.getString("CALLER_ID")==null?"":rs.getString("CALLER_ID"));
				v.setVEHICLE_PLATE_NUMBER(rs.getString("VEHICLE_PLATE_NUMBER")==null?"":rs.getString("VEHICLE_PLATE_NUMBER"));
				v.setBUSINESS_STATUS_NAME(rs.getString("BUSINESS_STATUS_NAME")==null?"":rs.getString("BUSINESS_STATUS_NAME"));
				v.setARCHIVE_TIME(rs.getString("ARCHIVE_TIME")==null?"":rs.getString("ARCHIVE_TIME").substring(0, 19));
				v.setACCEPT_TIME(rs.getString("ACCEPT_TIME")==null?"":rs.getString("ACCEPT_TIME").substring(0, 19));
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

	public List<ServiceQuality> findservicequality(String year,
			String compname, String level) {
		List<ServiceQuality>list=new ArrayList<ServiceQuality>();
		String sql = "select * from SERVICEEVALUATION@TAXILINK69 where 1 = 1 ";
        if(compname!=null&&!compname.isEmpty()&&compname.length()>0&&!compname.equals("")){
            sql += " and YHMC = '"+compname+"'";
        }
        if(level!=null&&!level.isEmpty()&&level.length()>0&&!level.equals("")){
            sql += " and XYDJ = '"+level+"'";
        }
        if(year!=null&&!year.isEmpty()&&year.length()>0&&!year.equals("")){
            sql += " and SJ = "+year+"";
        }
        sql +=" order by sj desc,yhmc";
        System.out.println(sql);
		Connection con;
		PreparedStatement ps;
		ResultSet rs;
		try {
			con=DataBese.getConnectionOfOracle();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				ServiceQuality v=new ServiceQuality();
				v.setYHMC(rs.getString("YHMC")==null?"":rs.getString("YHMC"));
				v.setXYDJ(rs.getString("XYDJ")==null?"":rs.getString("XYDJ"));
				v.setSJ(rs.getString("SJ")==null?"":rs.getString("SJ"));
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


}
