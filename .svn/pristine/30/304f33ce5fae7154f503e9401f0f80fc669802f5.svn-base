package com.tw.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opensymphony.xwork2.Action;

public class Cliente1 implements Action{

	private Socket socket;
	private String phone;
	private int type;
	private static String businessdescrip;
	public void Cliente() {
		try {
			socket = new Socket("192.168.0.227", 17600);
			 System.out.println("连接成功tessmiaa");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String execute(){
		 Cliente();
		 start();
		return SUCCESS;
	}
	/**
	 * 读取数据
	 *
	 */
	private class ServerHandler implements Runnable {
		public void run() {
			try {
				InputStream in = socket.getInputStream();
				byte[] ttt = new byte[50];
				in.read(ttt);
				ByteBuffer buffer=ByteBuffer .allocate(8020);
				buffer.put(ttt);
				buffer.flip();
				for (;;) {
					if (buffer.remaining() >= 14) {
						int pos = buffer.position();
						short a =buffer.getShort();
//						System.out.println("消息头"+a);
						byte cmd = buffer.get();
//						System.out.println("命令号"+cmd);
						int len = buffer.getShort();
//						 System.out.println("长度"+len);
						int p1 = buffer.position();
						int sum = 0;
						char s = 0;
						//String aa = new String();
						StringBuilder sb = new StringBuilder();
						while (true) {

							if ((s = (char) buffer.get()) != '\0') {
								// System.out.println(s);
								sb = sb.append(s);
								sum++;
								buffer.position(p1 + sum);
							} else {
								buffer.position(p1 + sum);
								String pintaiid=sb.toString();
//								 System.out.println("终端编号"+sb.toString());
								buffer.get();
								break;
							}
						}
						buffer.getShort();// 序列号
						  buffer.getShort();// 请求编号
						if (buffer.remaining() >= (len - 5 - sum)) {//数据内容解析
							int local = buffer.position();
							byte[] bits = new byte[len - 8 - sum];
							ByteBuffer b=buffer.get(bits);
							String content = new String(bits, "GBK");
							 int p11 = buffer.position();
							int sum1= 0;
							char s1 = 0;
							//String aa = new String();
							StringBuilder sb1 = new StringBuilder();
							while (true) {
								if ((s1=  (char)buffer.get()) != 0) {
									// System.out.println(s);
									sb1 = sb1.append(s1);
									sum1++;
									buffer.position(p11 + sum1);
								} else {
									buffer.position(p11 + sum1);
									String hehe= sb.toString();
//									 System.out.println("业务ID"+hehe);
									buffer.get();
									break;
								}
							}
		                 byte aaaa=buffer.get();
//		               System.out.println("hehe"+aaaa);
//							System.out.println(content+"晕了晕了晕了晕了晕了晕了晕了晕了晕了晕了晕了晕了晕了晕了晕了晕了");
//		                    System.out.println("能到这里吗");
						} else {
							buffer.position(pos);
						}
						buffer.get();
						short w=buffer.getShort();
//						System.out.println("包尾"+w);
					} else {
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void start() {
//		 Cliente();
		SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmss");
		Date d=new Date();
		String time=sdf.format(d);
//		System.out.println(type+"  "+time+" "+phone);
		try {
			ServerHandler handler = new ServerHandler();
			Thread t = new Thread(handler);
			t.setDaemon(true);
			t.start();
			OutputStream out = socket.getOutputStream();
//			InputStreamReader isr = new InputStreamReader(in, "utf-8");
//			isr.read();
			System.out.println(businessdescrip);
				out.write(getSendorder("108888888888", (byte)2, time, businessdescrip, phone));
//				out.write(getSendorder("108888888888", (byte)2, "22", "hehe", "666666666"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param businessid 终端编号
	 * @param j 约车类型
	 * @param time 用车时间
	 * @param businessdescrip 业务描述
	 * @param phone 手机号码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public byte[] getSendorder(String businessid,byte j,String time,String businessdescrip,String phone)//简明信息下发打包
	throws UnsupportedEncodingException {
int rowNumber=0;

String pintai="108888888888";
byte[] zhonduanidbyte=pintai.getBytes("GBK");
byte[] businessidbyte=businessid.getBytes("GBK");
byte[] timebyte=time.getBytes("GBK");
byte[] phonebyte=phone.getBytes("GBK");
byte[] businessdescripbyte=businessdescrip.getBytes("GBK");
int length= 2+zhonduanidbyte.length+1+2+2+1+businessidbyte.length+1+timebyte.length+1+phonebyte.length+1+businessdescripbyte.length
+1;

ByteBuffer buffer = ByteBuffer.allocate(8192);
//int length = pintai.length()+1+businessid.length()+1+time.length()+1+businessdescrip.length()+1+1+7+phone.length()+1;
buffer.clear();
byte[] a = { (byte) 0XFF, (byte) 0X61 };

byte[] b = { (byte) 0XFF, (byte) 0X5A };
buffer.put(a);
buffer.put((byte) 0x01);
buffer.putShort((short) (length));//长度
buffer.put(pintai.getBytes()).put((byte) 0);//平台编号
buffer.putShort((short) (++rowNumber));//序列号
buffer.putShort((short)5);//请求编号
buffer.put(businessid.getBytes("GBK")).put((byte) 0);//业务ID
buffer.put(j);//业务类型
buffer.put(time.getBytes("GBK")).put((byte) 0);//要车时间
buffer.put(phone.getBytes("GBK")).put((byte) 0);//电话号码
buffer.put(businessdescrip.getBytes("GBK")).put((byte) 0);//业务描述
// buffer.put((byte) 1);
buffer.put(Globing.getsum(buffer, 2, buffer.position() - 2));
buffer.put(b);

// buffer.putChar(b);
int count = buffer.position();
// System.out.println(count);
byte[] command = new byte[count];
buffer.position(0);
for (int i = 0; i < count; i++) {

	command[i] = buffer.get();
}
buffer.clear();
//System.out.println(Arrays.toString(command));
return command;
}
public byte[] getorder()//详细信息结果返回
	throws UnsupportedEncodingException {
ByteBuffer buffer = ByteBuffer.allocate(8192);
String pintai="123";
String businid="5266666";
int rowNumber=0;
int length = pintai.length()+1+businid.length()+1+1+7;
// buffer.position(0);
buffer.clear();
byte[] a = { (byte) 0XFF, (byte) 0X61 };

byte[] b = { (byte) 0XFF, (byte) 0X5A };
// byte c='0';
buffer.put(a);
// buffer.putChar(a);
buffer.put((byte) 0x11);
buffer.putShort((short) (length));//长度
buffer.put(pintai.getBytes()).put((byte) 0);//平台编号
buffer.putShort((short) (++rowNumber));
buffer.putShort((short) 6);//请求编号
buffer.put(businid.getBytes("GBK")).put((byte) 0);//业务编号

buffer.put((byte)0);//状态
// buffer.put((byte) 1);
buffer.put(Globing.getsum(buffer, 2, buffer.position() - 2));
buffer.put(b);

// buffer.putChar(b);
int count = buffer.position();
// System.out.println(count);
byte[] command = new byte[count];
buffer.position(0);
for (int i = 0; i < count; i++) {

	command[i] = buffer.get();
}
buffer.clear();
//System.out.println(Arrays.toString(command));
return command;
}
public static void main(String[] args) {
//Cliente1 client = new Cliente1();
//client.start(type, businessdescrip, businessdescrip);
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public String getBusinessdescrip() {
	return businessdescrip;
}
public void setBusinessdescrip(String businessdescrip) {
	this.businessdescrip = businessdescrip;
}


}
