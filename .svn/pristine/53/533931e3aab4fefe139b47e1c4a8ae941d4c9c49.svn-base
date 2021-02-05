package com.tw.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.tw.dao.DataExcelDao;
import com.tw.dao.OperatingDataDao;
import com.tw.entity.Condition;
import com.tw.entity.OperatingData;
import com.tw.util.PageHelper;

public class findoperaction implements Action{
	private static final long serialVersionUID = 71442418916410272L;
	private Condition con;
	private List<OperatingData> list ;
	private PageHelper page;
	private List<Integer>numList =new ArrayList<Integer>();
	private OperatingDataDao dao =new OperatingDataDao();
	private DataExcelDao pao = new DataExcelDao();
	//根据车号获取统计数据
	public String vehicle(){
		getUtil();	
		HttpSession session = ServletActionContext.getRequest().getSession();
		String data=(String) ActionContext.getContext().getSession().get("data");
		String realname=(String) ActionContext.getContext().getSession().get("realname");
		session.setAttribute("condition", con);
		Integer t= dao.geData(con,data,realname);
		page.setPageCount(t);
		list =dao.getAll(con, page,data,realname);	
		return SUCCESS;
	}
	//营运交易查询
	public String business(){	
		getUtil();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String data=(String) ActionContext.getContext().getSession().get("data");
		String realname=(String) ActionContext.getContext().getSession().get("realname");
		session.setAttribute("condition1", con);
		Integer t= dao.getNumbyBusiness(con,data,realname);
		page.setPageCount(t);
		list = dao.getBusiness(con, page,data,realname);
		return SUCCESS;
	}
	//过滤前端传来的条件
	public void getUtil(){
		if("--选择分公司--".equals(con.getBranch()))
			con.setBranch(null);		
		if("--选择公司--".equals(con.getCompany()))
			con.setCompany(null);
		if("0".equals(con.getCardNo()))
			con.setCardNo(null);
		if("0".equals(con.getCertNo())||con.getCertNo() =="")
			con.setCertNo(null);
		if("0".equals(con.getGroup()))
			con.setGroup(null);
	}
	public String execute() throws Exception {
		return null;
	}
	public Condition getCon() {
		return con;
	}
	public void setCon(Condition con) {
		this.con = con;
	}
	public List<OperatingData> getList() {
		return list;
	}
	public void setList(List<OperatingData> list) {
		this.list = list;
	}
	public PageHelper getPage() {
		return page;
	}
	public void setPage(PageHelper page) {
		this.page = page;
	}
	public List<Integer> getNumList() {
		return numList;
	}
	public void setNumList(List<Integer> numList) {
		this.numList = numList;
	}
	public OperatingDataDao getDao() {
		return dao;
	}
	public void setDao(OperatingDataDao dao) {
		this.dao = dao;
	}
	public DataExcelDao getPao() {
		return pao;
	}
	public void setPao(DataExcelDao pao) {
		this.pao = pao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
