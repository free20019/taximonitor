package com.tw.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tw.dao.DataExcelDao;
import com.tw.dao.GroupManageDao;
import com.tw.dao.RegularOfflineDao;
import com.tw.entity.Condition;
import com.tw.entity.GroupManage;
import com.tw.entity.OperatingData;
import com.tw.entity.RegularOffline;
import com.tw.util.ExcelExport;
/**
 * 
 * @author sven.zhang
 * @category excel文件下载
 *
 */
public class DataExcelAction extends ActionSupport  {

	private static final long serialVersionUID = 112456782344587L;
	private String filename;
	private InputStream  inputPath;
	private DataExcelDao dao = new DataExcelDao();
	private SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
	private GroupManageDao pao = new GroupManageDao();
	
	private RegularOfflineDao rdao = new RegularOfflineDao();
	private ByteArrayOutputStream out = new ByteArrayOutputStream();
	private String msg;
	//按照车辆查询导出excel
	public String excelVehicle(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		Condition condition = (Condition) session.getAttribute("condition");
		String data=(String) ActionContext.getContext().getSession().get("data");
		String realname=(String) ActionContext.getContext().getSession().get("realname");
		if( condition != null){
			List<OperatingData> list = dao.getVehicle(condition,data,realname);
			msg =ExcelExport.exportVehicle(list, out);
		}
		byte[] ba = out.toByteArray();  
	    inputPath = new ByteArrayInputStream(ba); 	    
	    filename= "车辆运营数据统计"+df.format(new Date())+".xls";	    
		filename=translate(filename);
		if(msg !=""){
			filename=msg;
		}
	
	    return SUCCESS;
	}
	//营运交易查询excel
	public String excelBus(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		Condition condition = (Condition) session.getAttribute("condition1");
		String data=(String) ActionContext.getContext().getSession().get("data");
		String realname=(String) ActionContext.getContext().getSession().get("realname");
		if( condition != null){
			System.out.println(222);
			List<OperatingData> list = dao.getBusiness(condition,data,realname);
			msg = ExcelExport.exportSelect(list, out);
		}
		byte[] ba = out.toByteArray();  
		inputPath = new ByteArrayInputStream(ba); 
		filename= "营运交易查询"+df.format(new Date())+".xls";
		filename=translate(filename);
		 if(msg !=""){
				filename=msg;
			}
		return SUCCESS;
	}

		//常规下线情况
	public String regular(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		Condition condition = (Condition) session.getAttribute("conditionRegular");					
			List<RegularOffline> list = rdao.getRegularByTime(condition);
			msg = ExcelExport.exportRegular(list, out);
		byte[] ba = out.toByteArray();  
		inputPath = new ByteArrayInputStream(ba); 
		filename= "常规下线情况"+df.format(new Date())+".xls";
		filename=translate(filename);
		 if(msg !=""){
				filename=msg;
			}
		return SUCCESS;
	}
	//车辆管理查询excel
	public String excelGroups(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		GroupManage gm = (GroupManage) session.getAttribute("gm");
		
			List<GroupManage> list = pao.findInfo(gm);
			msg = ExcelExport.exportGs(list, out);
			byte[] ba = out.toByteArray();  
			inputPath = new ByteArrayInputStream(ba); 
			filename= "车辆管理"+df.format(new Date())+".xls";
			filename=translate(filename);
			if(msg !=""){
				filename=msg;
			}
		
		return SUCCESS;
	}
	public InputStream getInputPath() {
		return inputPath;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public void setInputPath(InputStream inputPath) {
		this.inputPath = inputPath;
	}
	/**
	 * 返回文件名编码设置
	 */
	public String translate(String name){
		try {
			name = new String(name.getBytes("utf-8"),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return name;
	}
}
