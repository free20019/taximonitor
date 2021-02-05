package com.tw.action;

import java.io.*;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
public class DownLoad extends ActionSupport {

 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String    inputPath;
 private String    contentType;
 private String filename;
 public String getContentType() {
  return contentType;
 }
 public void setContentType(String contentType) {
  this.contentType = contentType;
 }
 public String getFilename() {
  return filename;
 }
 public void setFilename(String filename) {
  this.filename = filename;
 }
 public String getInputPath() {
  return inputPath;
 }
 public void setInputPath(String inputPath) {
  this.inputPath = inputPath;
 }
 public InputStream getInputStream() throws Exception
      {
         return ServletActionContext.getServletContext().getResourceAsStream(inputPath);
   }
 @Override
 public String execute() throws Exception {
  
  filename=inputPath; //保存文件时的名称
  inputPath="/count/"+inputPath;//要下载的文件路径和名称
     contentType="application/excel";//保存文件的类型
     return SUCCESS;

 }
 
 public String video() throws Exception {
  
  filename=inputPath; //保存文件时的名称
  inputPath="/video/"+inputPath;//要下载的文件路径和名称
     contentType="application/excel";//保存文件的类型
     return SUCCESS;

 }
}

