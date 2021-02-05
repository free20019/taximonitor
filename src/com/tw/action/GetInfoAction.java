package com.tw.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.tw.dao.ConditionDao;
import com.tw.entity.Condition;
import com.tw.entity.Groups;

public class GetInfoAction implements Action{
	private Condition con = new Condition();
	private List<String >baList;
	private List<String>compList = new ArrayList<String>() ;
	private List<String>certList;
	private List<String>cardList;
	private List<Groups>groupList;
	private List<Groups>gcardList;
	private List sList;
	private Groups g = new Groups();
	private ConditionDao dao = new ConditionDao();
	//获取公司
	public String company(){		
		HttpSession session = ServletActionContext.getRequest().getSession();
		String permission = (String) session.getAttribute("data");
		String[] a = permission.split(",");
		for (int i = 0; i < a.length; i++) {
			compList.add(a[i]);
		}
//		String username=(String) ActionContext.getContext().getSession().get("username");
//		String perm[]=new String[3];
//		if (username.equals("shiws")) {
//			compList.add("杭州外事旅游汽车集团有限公司");
//			compList.add("浙江中侨汽车出租有限公司");
//			compList.add("杭州蓝联出租汽车服务管理有限公司");
//		}else if (username.equals("shiws")) {
//			compList.add("杭州萧山汽车出租有限公司");
//			compList.add("杭州市萧山公共交通有限公司");
//			compList.add("萧山大众出租有限公司");
//			compList.add("杭州萧山客运旅游有限公司");
//			compList.add("浙江通信产业服务有限公司");
//			compList.add("杭州萧山发展汽车有限公司");
//			compList.add("杭州国运汽车出租有限公司");
//			compList.add("萧山出租");
//			compList.add("杭州萧山交投出租汽车服务有限公司");
//			compList.add("杭州萧山长运出租车有限公司");
//			compList.add("萧山安达汽车出租有限公司");
//			compList.add("萧山万兴旅游汽车有限公司");
//		}else {
//			if( permission !=null){
//				perm = permission.split(",");
//				if("所有公司".equals(perm[0])){
//					compList =dao.getCompany();
//				}else{
//					String comp = perm[0];
//					compList.add(comp);
//				}
//			}
//		}
		session.setAttribute("compList", compList);
		return SUCCESS;
	}
	//查分公司
	public String branch(){		
		baList = dao.getBranch(con.getCompany());
		return SUCCESS;
	}
	//查车号
	public String cardNo(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String permission = (String) session.getAttribute("date");
		List<String> list = (List<String>) session.getAttribute("compList");
		//permission != null && 
		if(list !=null){
			if( "--选择分公司--".equals(con.getBranch()) )
				con.setBranch(null);
			if( "--选择公司--".equals(con.getCompany()) )
				con.setCompany(null);
			if( list.size() == 1)	
				con.setCompany(list.get(0));
			cardList = dao.getCardNo(con);	
		}
		return SUCCESS;
	}
	
	public Condition getCon() {
		return con;
	}



	public void setCon(Condition con) {
		this.con = con;
	}



	public List<String> getBaList() {
		return baList;
	}



	public void setBaList(List<String> baList) {
		this.baList = baList;
	}



	public List<String> getCompList() {
		return compList;
	}



	public void setCompList(List<String> compList) {
		this.compList = compList;
	}



	public List<String> getCertList() {
		return certList;
	}



	public void setCertList(List<String> certList) {
		this.certList = certList;
	}



	public List<String> getCardList() {
		return cardList;
	}



	public void setCardList(List<String> cardList) {
		this.cardList = cardList;
	}



	public List<Groups> getGroupList() {
		return groupList;
	}



	public void setGroupList(List<Groups> groupList) {
		this.groupList = groupList;
	}



	public List<Groups> getGcardList() {
		return gcardList;
	}



	public void setGcardList(List<Groups> gcardList) {
		this.gcardList = gcardList;
	}



	public List getsList() {
		return sList;
	}



	public void setsList(List sList) {
		this.sList = sList;
	}



	public Groups getG() {
		return g;
	}



	public void setG(Groups g) {
		this.g = g;
	}



	public ConditionDao getDao() {
		return dao;
	}



	public void setDao(ConditionDao dao) {
		this.dao = dao;
	}



	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
