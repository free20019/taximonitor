package com.tw.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class insertMdt_no {
	public static void main(String[] args) {
		List<String> a =fingmdt_no();
		for (int i = 0; i < a.size(); i++) {
			String sql = "insert into hz_taxi.TB_TAXI_STATUS_new t (mdt_no) "
					+ "values ('"+a.get(i)+"')";
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps;
			try {
				ps = con.prepareStatement(sql);
				int rs = ps.executeUpdate();
				ps.close();
				con.commit();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static List<String> fingmdt_no(){
		List<String> list =new ArrayList<String>();
		String sql="select sim_num from(select * from (select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v where (comp_name = '杭州萧山汽车出租有限公司' or comp_name = '杭州市萧山公共交通有限公司' or comp_name = '萧山大众出租有限公司' or comp_name = '杭州萧山客运旅游有限公司' or comp_name = '浙江通信产业服务有限公司' or comp_name = '杭州萧山发展汽车有限公司' or comp_name = '杭州国运汽车出租有限公司' or comp_name = '萧山出租' or comp_name = '杭州萧山交投出租汽车服务有限公司' or comp_name = '杭州萧山长运出租车有限公司' or comp_name = '萧山安达汽车出租有限公司' or comp_name = '萧山万兴旅游汽车有限公司')) v left join hz_taxi.TB_TAXI_STATUS_new t on t.mdt_no = v.sim_num  order by vehi_no) where speed is  null";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				list.add(rs.getString("SIM_NUM"));
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
