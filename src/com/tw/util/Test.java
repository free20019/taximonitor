package com.tw.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.GpsServicesDelegate;
import com.tw.entity.Vehicle;


public class Test {

	private static  String WS_URL = "http://192.168.0.102:9086/EWS/GpsServicesPort?WSDL";
//	public static void main(String[] args) throws Exception {
//		
//		URL url = new URL(WS_URL);
//        QName qname = new QName("http://service.com/", "GpsServicesService");
//        Service service = Service.create(url, qname);
//        GpsServicesDelegate gpsService = service.getPort(GpsServicesDelegate.class);
//        Map<String, Object> req_ctx = ((BindingProvider)gpsService).getRequestContext();
//
//        List qy = new ArrayList();
//        Calendar calendar = Calendar.getInstance() ,calendar2 = Calendar.getInstance();
//        calendar.set(2016, 4, 31, 18, 30, 22);
//		calendar2.set(2016, 4,31, 18, 50, 22);
//		
//        qy.add("120.400654,30.13079;120.387349,30.24193;120.540643,30.238519;120.556092,30.1299;");
//        
//        qy.add("120.300654,30.10079;120.387349,30.24193;120.540643,30.238519;120.556092,30.1299;");
//        
////        qy.add("120.350654,30.15079;120.387349,30.24193;120.540643,30.238519;120.556092,30.1299");
//        String  result = gpsService.swcz(qy, calendar.getTimeInMillis(), calendar2.getTimeInMillis());
////        System.out.println("查询结果:\n"+result);
//        JSONObject json = JSONObject.fromObject(result);
//        List<String> list = new ArrayList<String>();
//        for (int i = 0; i < json.size(); i++) {
//        	list.add(json.getString("result"+i));
//		}
//        if(list.size()==2){
//        	JSONObject json1;
//        	JSONObject json2;
//        	 json1=JSONObject.fromObject(list.get(0));
//        	 json2=JSONObject.fromObject(list.get(1));
//        	 Set s1=json1.keySet();
//        	 Set s2=json2.keySet();
//        	 s1.retainAll(s2);
//        	 Iterator it = s1.iterator();  
//	    	 String a = "";
//	    	 while(it.hasNext()) {  
//	    		 a=(String) it.next();
//	    		 System.out.println(a+","+json1.get(a));
//	    	 }
//        }else{
//        	JSONObject json1;
//        	JSONObject json2;
//        	JSONObject json3;
//        	json1=JSONObject.fromObject(list.get(0));
//       	 	json2=JSONObject.fromObject(list.get(1));
//       	 	json3=JSONObject.fromObject(list.get(2));
//	       	 Set s1=json1.keySet();
//	    	 Set s2=json2.keySet();
//	    	 Set s3=json3.keySet();
//	    	 s1.retainAll(s2);
//	    	 s1.retainAll(s3);
//	    	 Iterator it = s1.iterator();  
//	    	 String a = "";
//	    	 while(it.hasNext()) {  
//	    		 a=(String) it.next();
//	    	 }  
//        }
//        
//	}
	public List<List<Vehicle>> findswcz(String qd_stime,String qd_etime,
			String zj_stime,String zj_etime,String zd_stime,String zd_etime,
			String qd_jwd,String zj_jwd,String zd_jwd){
		List<List<Vehicle>> zzjg = new ArrayList<List<Vehicle>>();
		List<String> list = new ArrayList<String>();
		String s1 = "",s2 = "",s3 = "";
		List<Vehicle> listqd = new ArrayList<Vehicle>();
		List<Vehicle> listzd = new ArrayList<Vehicle>();
		if(qd_jwd!=null&&qd_jwd.length()>0){
			s1=swczff(qd_stime,qd_etime,qd_jwd,"1");
			JSONObject json = JSONObject.fromObject(s1);
	        list.add(json.getString("result0"));
	        JSONObject json1 =  JSONObject.fromObject(json.getString("result0"));
	        Set setqd = json1.keySet();
	        Iterator it = setqd.iterator();
	    	 String a = "";
	    	 while(it.hasNext()) {
	    		 a=(String) it.next();
	    		 Vehicle v = new Vehicle();
	    		 v.setVehi_no(a);
	    		 v.setStime(json1.get(a).toString().split("\\[")[0]);
	    		 v.setPx(json1.get(a).toString().split("\\[")[1]);
	    		 listqd.add(v);
	    	 }  
		}
		
		System.out.println(1);
		for(int i=0;i<listqd.size();i++){
			System.out.println(listqd.get(i).getVehi_no()+" ####");
		}
		if(zd_jwd!=null&&zd_jwd.length()>0){
			s2=swczff(zd_stime,zd_etime,zd_jwd,"2");
			JSONObject json = JSONObject.fromObject(s2);
	        list.add(json.getString("result0"));
	        JSONObject json1 =  JSONObject.fromObject(json.getString("result0"));
	        Set setzd = json1.keySet();
	        Iterator it = setzd.iterator();
	    	 String a = "";
	    	 while(it.hasNext()) {  
	    		 a=(String) it.next();
	    		 Vehicle v = new Vehicle();
	    		 v.setVehi_no(a);
	    		 v.setStime(json1.get(a).toString().split("\\[")[0]);
	    		 v.setPx(json1.get(a).toString().split("\\[")[1]);
	    		 listzd.add(v);
	    	 }  
		}
		if(zj_jwd!=null&&zj_jwd.length()>0){
			s3=swczff(zj_stime,zj_etime,zj_jwd,"3");
			JSONObject json = JSONObject.fromObject(s3);
	        list.add(json.getString("result0"));
		}
		List<Vehicle> listjg = new ArrayList<Vehicle>();
		if(list.size()==2){
        	JSONObject json11=JSONObject.fromObject(list.get(0));
        	JSONObject json2;
        	 json2=JSONObject.fromObject(list.get(1));
        	 Set s11=json11.keySet();
        	 Set s22=json2.keySet();
        	 Set s33 = new HashSet(s11);
        	 Set s44 = new HashSet(s22);
        	 s33.retainAll(s44);
        	 Iterator it = s33.iterator();
	    	 String a = "";
	    	 while(it.hasNext()) {  
	    		 a=(String) it.next();
	    		 Vehicle v = new Vehicle();
	    		 v.setVehi_no(a);
	    		 System.out.println(a+"  #####");
	    		 v.setStime(json11.get(a).toString().split("\\[")[0]);
	    		 v.setPx(json11.get(a).toString().split("\\[")[1]);
	    		 listjg.add(v);
	    	 }  
        }else{
        	JSONObject json1;
        	JSONObject json2;
        	JSONObject json3;
        	json1=JSONObject.fromObject(list.get(0));
       	 	json2=JSONObject.fromObject(list.get(1));
       	 	json3=JSONObject.fromObject(list.get(2));
	       	 Set s11=json1.keySet();
	    	 Set s22=json2.keySet();
	    	 Set s33=json3.keySet();
	    	 Set s111 = new HashSet(s11);
        	 Set s222 = new HashSet(s22);
        	 Set s333 = new HashSet(s33);
	    	 s111.retainAll(s222);
	    	 s111.retainAll(s333);
	    	 Iterator it = s111.iterator();  
	    	 String a = "";
	    	 while(it.hasNext()) {  
	    		 a=(String) it.next();
	    		 Vehicle v = new Vehicle();
	    		 v.setVehi_no(a);
	    		 v.setStime(json1.get(a).toString().split("\\[")[0]);
	    		 v.setPx(json1.get(a).toString().split("\\[")[1]);
	    		 listjg.add(v);
	    	 }  
        }
		zzjg.add(listqd);
		zzjg.add(listzd);
		zzjg.add(listjg);
		System.out.println(zzjg.get(0).size()+"  @@@");
		return zzjg;
	}
	
	public String swczff(String stime,String etime,String jwd,String obj){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long st = 0,et = 0;
		try {
			st = sdf.parse(stime).getTime();
			et = sdf.parse(etime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		URL url = null;
		try {
			url = new URL(WS_URL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        QName qname = new QName("http://service.com/", "GpsServicesService");
        Service service = Service.create(url, qname);
        GpsServicesDelegate gpsService = service.getPort(GpsServicesDelegate.class);
        Map<String, Object> req_ctx = ((BindingProvider)gpsService).getRequestContext();

        List qy = new ArrayList();
        Calendar calendar = Calendar.getInstance() ,calendar2 = Calendar.getInstance();
        calendar.set(2016, 4, 31, 18, 30, 22);
		calendar2.set(2016, 4,31, 18, 50, 22);
		
        qy.add(jwd);
        
//        qy.add("120.300654,30.10079;120.387349,30.24193;120.540643,30.238519;120.556092,30.1299;");
        
//        qy.add("120.350654,30.15079;120.387349,30.24193;120.540643,30.238519;120.556092,30.1299");
        String  result = gpsService.swcz3Day(qy, st, et);
        System.out.println(result);
//        String  result = gpsService.swcz(qy, calendar.getTimeInMillis(), calendar2.getTimeInMillis());
//        System.out.println("查询结果:\n"+result);
//        JSONObject json = JSONObject.fromObject(result);
//        if(obj.equals("1")){
//        	json1 = JSONObject.fromObject(result);
//        }
//        List<String> list = new ArrayList<String>();
//        list.add(json.getString("result0"));
//        JSONObject json1=JSONObject.fromObject(list.get(0));
        return result;
	}
public static void main(String[] args) {
	String a = "浙AT0943, 浙ATB599, 浙AT6711, 浙ATA569, 浙AT5835, 浙AT7834, 浙AT1678, 浙ATD970, 浙ATD086, 浙ATB698, 浙AT9954, 浙ATB149, 浙AT5733, 浙AT1675, 浙ATH358, 浙AT1968, 浙AT2926, 浙AT4891, 浙ATB589, 浙AT2823, 浙ATC322, 浙AT1285, 浙AUT818, 浙AT9967, 浙AT1689, 浙ALT902, 浙ATB416, 浙AT3184, 浙ATD688, 浙ATB699, 浙ATB169, 浙AT2421, 浙ATB421, 浙AT7982, 浙ATG218, 浙AT6454, 浙ATG883, 浙ATH366, 浙AT1583, 浙AT9671, 浙AT3818, 浙ACT287, 浙AT3814, 浙ATF609, 浙ATF607, 浙ATA079, 浙AT9977, 浙ATH234, 浙ATB130, 浙AT7021, 浙ATC205, 浙ATE777, 浙ATG877, 浙AT5997, 浙AT0171, 浙ATC308, 浙AT1160, 浙AT0028, 浙ATD192, 浙AT9980, 浙ATF338, 浙AT4466, 浙AQT202, 浙ATB966, 浙ATD092, 浙ATC203, 浙ATG376, 浙AT4094, 浙AT2817, 浙AT4725, 浙AT1938, 浙ATG868, 浙AT5036, 浙AT0203, 浙AT2960, 浙AT4755, 浙AT0205, 浙ATA095, 浙AT6682, 浙AT5902, 浙AT7544, 浙AT7682, 浙AT2091, 浙AT2868, 浙ATD577, 浙AT4160, 浙ATB054, 浙AT9557, 浙ATG855, 浙AT2577, 浙ATA878, 浙AT2964, 浙AT2475, 浙APT935, 浙ATB450, 浙ATF677, 浙AT8550, 浙AT5500, 浙AT0216, 浙ATG315, 浙ATA083, 浙ATF259, 浙AT8935, 浙AT9152, 浙ATA885, 浙AT4841, 浙AT6494, 浙ATG119, 浙AT2765, 浙AT8936, 浙ATF681, 浙ATF682, 浙AT4647, 浙AT4549, 浙AT0418, 浙AT6170, 浙ATF685, 浙AT2086, 浙AQT082, 浙AT0851, 浙AT2459, 浙ATA692, 浙AT2984, 浙AT1706, 浙AT3464, 浙AT8192, 浙AT2847, 浙AT3050, 浙AT5616, 浙ATA897, 浙ATB876, 浙ATB177, 浙ATJ528, 浙APT027, 浙ATG528, 浙AT8570, 浙AT8808, 浙AT1395, 浙AT4438, 浙AT4146, 浙AT1077, 浙APT530, 浙AT2235, 浙ATC026, 浙ATF176, 浙AT4822, 浙ATF278, 浙AT3454, 浙AT2588, 浙ATA166, 浙ATF571, 浙AT0533, 浙ATF666, 浙ATF669, 浙AT4298, 浙ATC341, 浙AT3035, 浙AT9642, 浙ATB798, 浙AT1107, 浙ATA907, 浙AT8763, 浙ATB507, 浙ATF156, 浙ATE178, 浙AT2255, 浙ATF296, 浙ATG553, 浙AT9023, 浙ATC352, 浙AT2183, 浙AT6388, 浙AT3500, 浙AT5265, 浙AT1372, 浙AT4517, 浙AT3783, 浙AT4284, 浙AQT181, 浙AT1478, 浙AT4510, 浙AT9837, 浙ATD329, 浙AQT755, 浙ATG681, 浙AT2613, 浙AT2079, 浙ATJ558, 浙AT9523, 浙APT957, 浙AT3017, 浙ATH797, 浙ATB625, 浙AUT885, 浙ATH795, 浙ATC765, 浙AQT688, 浙AT4273, 浙ATB266, 浙AT7376, 浙AT9667, 浙AT2756, 浙ATG277, 浙AT9143, 浙ATG572, 浙ATC076, 浙AT5523, 浙ATA397, 浙ATB768, 浙AT6915, 浙AT0600, 浙ATA779, 浙AT6361, 浙AT9659, 浙ATG168, 浙AT0373, 浙ATE189, 浙AT4676, 浙ATE807, 浙ATG567, 浙AT3220, 浙ATC068, 浙AT2265, 浙AT3324, 浙ATC175, 浙AT1622, 浙ATC680, 浙AT0676, 浙ATH636, 浙ATC238, 浙AT1734, 浙AT5152, 浙ATC685, 浙ATC881, 浙ATA525, 浙AT1448, 浙AT1246, 浙AT3736, 浙ATJ050, 浙APT079, 浙ATE872, 浙ATA719, 浙ALT271, 浙ATB236, 浙AT0390, 浙AUT399, 浙AT3289, 浙AT5670, 浙ATD755, 浙AT5676, 浙ATG389, 浙ALT570, 浙AT9878, 浙AT5570, 浙AT5977, 浙AT0069, 浙ATJ598, 浙AT8784, 浙ATD366, 浙AT5276, 浙APT315, 浙ATH345, 浙AT8981, 浙AT1121, 浙ATH551, 浙ATJ086, 浙AT3666, 浙ATB114, 浙ATC671, 浙AT6434, 浙ATA356, 浙AT5181, 浙ATH660, 浙ATH661, 浙ATC667, 浙ATE526, 浙ATJ868, 浙AT5759, 浙ATA203, 浙ATC233, 浙ATD875, 浙AT5386, 浙ATB109, 浙AT3668, 浙ATA015";
	String b = "浙AT0943, 浙AT4298, 浙ATB599, 浙AT6711, 浙AT5835, 浙ATB798, 浙AT1107, 浙AT1678, 浙AT8763, 浙ATB507, 浙ATD086, 浙ATB698, 浙AT9954, 浙ATB149, 浙ATE178, 浙AT2926, 浙AT9023, 浙AT2255, 浙ATF296, 浙ATB589, 浙AT2823, 浙ATC352, 浙AT2183, 浙AT6388, 浙AT3500, 浙ATC322, 浙AT7845, 浙AT4517, 浙ATB416, 浙AT4284, 浙AT3783, 浙ATB699, 浙AT2421, 浙ATB421, 浙AT4510, 浙AT7982, 浙ATG218, 浙ATG883, 浙AT1583, 浙ATG681, 浙AT2613, 浙AT2079, 浙ATJ558, 浙AT3017, 浙ATB625, 浙AUT885, 浙ATH795, 浙AQT688, 浙AT3814, 浙AT4273, 浙ATB266, 浙AT9667, 浙AT2756, 浙ATG277, 浙ATF607, 浙AT9977, 浙ATB130, 浙AT7021, 浙ATC205, 浙ATG572, 浙ATC076, 浙ATE777, 浙AT5523, 浙ATC308, 浙AT0171, 浙ATA397, 浙ATB768, 浙AT1160, 浙ATD192, 浙AT6915, 浙ATF338, 浙AT6361, 浙AT4466, 浙AQT202, 浙ATB966, 浙ATD092, 浙ATG168, 浙ATG376, 浙AT4094, 浙AT2817, 浙AT0166, 浙AT4725, 浙ATJ138, 浙ATE807, 浙ATG567, 浙AT1938, 浙AT2265, 浙ATC175, 浙AT1622, 浙AT0676, 浙ATH636, 浙AT0203, 浙AT4755, 浙AT5152, 浙ATA095, 浙AT6682, 浙ATC685, 浙AT5902, 浙AT7682, 浙AT2091, 浙AT2868, 浙ATD577, 浙ATB054, 浙ATC881, 浙AT9557, 浙ATA878, 浙AT9784, 浙AT2964, 浙AT2475, 浙AT1448, 浙APT935, 浙ATB450, 浙AT3736, 浙AT8550, 浙AT5500, 浙ATF196, 浙ATJ050, 浙AT0216, 浙AT1098, 浙ATG315, 浙ATA083, 浙ATF259, 浙AT8935, 浙AT9152, 浙ALT271, 浙AT0390, 浙AUT399, 浙AT3289, 浙ATA885, 浙AT6494, 浙AT4841, 浙AT8936, 浙AT2765, 浙ATD755, 浙AT5670, 浙ATF682, 浙AT4549, 浙AT0418, 浙AT2086, 浙ALT570, 浙AT6170, 浙AT5676, 浙ATF685, 浙AT0851, 浙AT5570, 浙AT2459, 浙ATA692, 浙AT2984, 浙ATJ598, 浙ATA600, 浙AT3464, 浙ATD366, 浙AT3463, 浙APT315, 浙AT8192, 浙AT3050, 浙ATA897, 浙ATB876, 浙AT5471, 浙ATB177, 浙ATJ528, 浙ATH551, 浙ATG528, 浙AT8570, 浙AT8808, 浙AT4146, 浙AT3666, 浙ATC026, 浙ATF176, 浙ATB114, 浙ATC671, 浙AT2335, 浙AT6434, 浙ATA356, 浙AT5181, 浙ATH660, 浙ATC667, 浙ATA166, 浙ATA169, 浙ATE526, 浙ATJ868, 浙AT0533, 浙AT5759, 浙ATA203, 浙ATF666, 浙ATF669, 浙AT5386, 浙ATB109, 浙AT3668";
	Set s11=new HashSet();
	Set s22=new HashSet();
	for (int i = 0; i < a.split(", ").length; i++) {
		s11.add(a.split(", ")[i]);
	}
	for (int i = 0; i < b.split(", ").length; i++) {
		s22.add(b.split(", ")[i]);
	}
	 System.out.println(s11);
	 System.out.println(s22);
	 s11.retainAll(s22);
	 System.out.println(s11);
}
}
