package com.scorm.action;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.scorm.utils.MD5id;
import com.scorm.vo.Userinfo;
import com.scorm.service.UserinfoService;

/**
 * 用户action层
 * @author BFS Team 
 *
 */



@SuppressWarnings("serial")
@Scope(value="prototype")
@Component(value="userinfoAction")
public class UserinfoAction extends ActionSupport{
	@Resource(name="userinfoService")
	private UserinfoService userinfoService;
	
	private String userName;
	private String userPwd;
	private String userSex;
	private String userEmail;
	private String result;
	private String code;
	private boolean flag;
	private boolean validateFlag ;
	
	/**
	 * 登录验证
	 * @return
	 */
	public String loginValidate(){
		System.out.println("进入UserinfoAction中的loginValidate方法，传过来的参数有：");
		System.out.println("userName="+userName+"  userPwd="+userPwd);
		System.out.println(userinfoService);
		
		flag = userinfoService.findByUserNameAndUserName(userName, userPwd);
		if(flag){
			validateFlag = true;
			Timestamp loginTime = new Timestamp(System.currentTimeMillis());
			List<Userinfo> list = userinfoService.findByUserName(userName);
			if(list!=null){
				Userinfo u = list.get(0);
				u.setLoginTime(loginTime);
				userinfoService.saveUserinfo(u);
				this.setSessionUser(u);
			}
			return "success";
		}
		return "fail";
	}

	/**
	 * 制作验证码
	 */
	private static int width = 120;
	private static int height = 30;
	public String createImage(){
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		//不要缓存   解决回车后就刷新
		response.setHeader("Expires", "-1");
		response.setHeader("Cache-Control", "no-cache"); 
		response.addHeader("Pragma", "no-cache"); 
		
		//创建一个内存图像
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
		
		//先得到画笔
		 Graphics g = image.getGraphics();
		 //之前可以设置背景色先画边框
		 g.setColor(Color.BLUE);
		 g.drawRect(0, 0, width, height);
		 
		 //设置图片背景色
		 g.setColor(Color.GRAY);
		 g.fillRect(1, 1, width-2, height-2);
		 
		 //画干扰线
		 g.setColor(Color.gray);
		 Random r = new Random();
		 for(int i=0;i<15;i++){
			 g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
			 
		 }
		// 2.4画验证数字4个数字
		g.setColor(Color.white);
		g.setFont(new Font("宋体", Font.BOLD, 20));
		int x = 5;int y=20;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			int c = r.nextInt(9);
			sb.append(c);
			if(i%2==0)
				y = 20;
			else
				y = 15;
			g.drawString(c + "", x, y);
			x += 30;
		}
		String code = sb.toString();//3214
		//加密后再放入 MD5
		
		//加入到session中
		HttpSession session = request.getSession();
		session.setAttribute("code", MD5id.encode(code));
		
		// 3、输出response的输出流中ImageIO
		OutputStream out = null;
		try {
			response.flushBuffer();
			out = response.getOutputStream();
			ImageIO.write(image, "jpeg", out);
			
		} catch (Exception e) {
			//e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 验证验证码
	 * @return
	 */
	public String validateCode(){
		System.out.println("进入userinfoAction中的validateCode方法，传过来的参数有：");
		System.out.println("code="+code);
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			this.code = MD5id.encode(this.code);
			HttpSession session = request.getSession();
			String cCode = (String)session.getAttribute("code");
			System.out.println("code="+code+"\ncCode="+cCode);

			boolean flag = false;
			if(cCode.equals(this.code)){
				flag = true;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("flag", flag);
			JSONObject jsonObject = JSONObject.fromObject(map);
			result = jsonObject.toString();
			System.out.println("result="+result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	/**
	 * 验证用户名是否存在
	 * @return
	 */
	public String validateUserName(){
		System.out.println("进入userinfoAction中的validateUserName方法，传过来的参数有：");
		System.out.println("userName="+userName);
		
		List<Userinfo> list = userinfoService.findByUserName(userName);
		boolean flag = true;
		if(list.size()>0)
			flag = false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", flag);
		JSONObject jsonObject = JSONObject.fromObject(map);
		result = jsonObject.toString();
		System.out.println("result="+result);
		
		return "success";
	}
	/**
	 * 用户注册  添加用户
	 * @return
	 */
	public String registerUser(){
		List<Userinfo> list = userinfoService.findByUserName(userName);
		if(list.size()>0)
			return "index";
		validateFlag = false;
		flag = true;
		System.out.println("----进入userinfoAction中的registerUser方法，传过来的参数有：");
		if("0".equals(userSex))
			userSex = "男";
		else
			userSex = "女";
		System.out.println("userName="+userName+"  userPwd="+userPwd+" userSex="+userSex+"  userEmail="+userEmail);
		
		Userinfo userinfo = new Userinfo(userName,userPwd,userSex,userEmail,"",0,0,new Timestamp(System.currentTimeMillis()));
		userinfoService.saveUserinfo(userinfo);
		
		return "index";
	}
	
	public String logout(){
		this.logoutSessionUser();
		return null;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public boolean isValidateFlag() {
		return validateFlag;
	}
	public void setValidateFlag(boolean validateFlag) {
		this.validateFlag = validateFlag;
	}
	
}
