package com.tw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tw.entity.User;
import com.tw.entity.Vhic;
import com.tw.entity.station;
import com.tw.util.DataBese;


public class userDao {
	public User getUser(String username, String password) {
		User u=new User();
		String sql="select * from tb_user u,tb_taxi_station s where u.station_id = s.id and "
				+ " user_name='"+username+"' and password='"+password+"'";
		PreparedStatement ps;
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
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
				String insert="insert into tb_login_history (USER_ID,LOGIN_TIME,LOGING_WAY,ACCOUNT_SORT) values ('"+rs.getString("id")+"',to_date('"+date+"','yyyy-mm-dd hh24:mi:ss'),'1','0')";
				System.out.println("login insert="+insert);		
				Connection con2=null;
				try {
					con2=DataBese.getConnectionOfOracle();
					Statement st=con2.createStatement();
					st.executeUpdate(insert);
					st.close();
					con2.commit();
					con2.close();
				} catch (SQLException e) {
					e.printStackTrace();
					try {
						con2.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
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
	//查询无营运数据车辆
		public List<User>findloginhistory(String stime,String etime,String compname){
			List<User>list=new ArrayList<User>();
			String sql = "select u.*,to_char(h.login_time,'yyyy-MM-dd HH24:mi:ss') login_time from TB_USER u,tb_login_history h "
					+ " where u.id=h.user_id and LOGING_WAY='1' and ACCOUNT_SORT='0'";
			if(stime!=null&&!stime.equals("null")&&stime.length()>0){
				sql += " and h.login_time >=to_date('"+stime+" 00:00:00','yyyy-mm-dd hh24:mi:ss')";
			}
			if(etime!=null&&!etime.equals("null")&&etime.length()>0){
				sql += " and h.login_time <=to_date('"+etime+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
			}
			if(compname!=null&&!compname.equals("null")&&!compname.equals("0")&&compname.length()>0&&!compname.equals("所有公司")){
				sql += " and u.real_name = '"+compname+"'";
			}
			sql +="order by h.login_time desc";
			System.out.println(sql);
			Connection con;
			PreparedStatement ps;
			ResultSet rs;
			try {
				con=DataBese.getConnectionOfOracle();
				ps = con.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()){
					User u=new User();
					u.setUsername(rs.getString("user_name")==null?"&nbsp;":rs.getString("user_name"));
					u.setPassword(rs.getString("password")==null?"&nbsp;":rs.getString("password"));
					u.setReal_name(rs.getString("real_name"));
					u.setDate_view_type(rs.getString("date_view_type"));
					u.setParent(rs.getString("parent"));
					u.setYmqx(rs.getString("login_time"));
					list.add(u);
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
