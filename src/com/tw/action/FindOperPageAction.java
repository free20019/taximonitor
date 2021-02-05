package com.tw.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.tw.dao.OperatingDataDao;
import com.tw.entity.Condition;
import com.tw.entity.OperatingData;
import com.tw.util.PageHelper;

public class FindOperPageAction implements Action{
	private static final long serialVersionUID = -2729160815301078521L;
	private List<OperatingData> list;
	private PageHelper page;
	private OperatingDataDao dao = new OperatingDataDao();
	// 按照车辆查询分页
	public String vehicle() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Condition bu = (Condition) session.getAttribute("condition");
		String data=(String) ActionContext.getContext().getSession().get("data");
		String realname=(String) ActionContext.getContext().getSession().get("realname");
		if (bu != null) {
			list =dao.getAll(bu, page,data,realname);	
		}
		return SUCCESS;
	}
	//营运交易查询
	public String business(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		Condition bu =  (Condition) session.getAttribute("condition4");
		String data=(String) ActionContext.getContext().getSession().get("data");
		String realname=(String) ActionContext.getContext().getSession().get("realname");
		if (bu != null) {
			list = dao.getBusiness(bu, page,data,realname);
		}
		return SUCCESS;
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
	public OperatingDataDao getDao() {
		return dao;
	}
	public void setDao(OperatingDataDao dao) {
		this.dao = dao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
