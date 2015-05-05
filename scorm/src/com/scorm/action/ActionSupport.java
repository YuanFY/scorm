package com.scorm.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.scorm.utils.UserCommon;
import com.scorm.vo.Userinfo;

/**
 * 重写ActionSupport
 * 		封装一些json方面的方法
 * @author YuanFY
 *
 */

@SuppressWarnings("serial")
@Scope("prototype")
@Service(value="userActionSupport")
public abstract class ActionSupport extends com.opensymphony.xwork2.ActionSupport{
	/**
	 * 封装boolean类型
	 * @param object  boolean类型
	 * @return  返回json格式的字符串
	 * @throws Exception
	 */
	
	public String json(boolean object) throws Exception{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("flag", object);
		return jsonObject.toString();
	}
	/**
	 * 封装自定义对象类型
	 * @param object 自定义对象类型
	 * @return  返回json格式的字符串
	 * @throws Exception
	 */
	public String json(Object object) throws Exception{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);//处理循环解析bug
		jsonConfig.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
		JSONObject jsonObject =JSONObject.fromObject(object, jsonConfig);
		return jsonObject.toString();
	}
	/**
	 * 封装map对象
	 * @param map	Map对象
	 * @return  返回json格式的字符串
	 * @throws Exception
	 */
	public String json(Map<?,?> map) throws Exception{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
		JSONObject jsonObject =JSONObject.fromObject(map, jsonConfig);
		return jsonObject.toString();
	}
	/**
	 * 封装list对象
	 * @param list	List对象
	 * @return 返回json格式的字符串
	 * @throws Exception
	 */
	
	public String json(List<?> list) throws Exception{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
		JSONArray json =JSONArray.fromObject(list.toArray(), jsonConfig);
		return json.toString();
	}
	
	public static Userinfo getSessionUser(){
		return (Userinfo)ServletActionContext.getRequest().getSession().getAttribute(UserCommon.USERINFO);
	}
	public void setSessionUser(Userinfo userinfo){
		ServletActionContext.getRequest().getSession().setAttribute(UserCommon.USERINFO, userinfo);
	}
	
	public void logoutSessionUser( ){
		ServletActionContext.getRequest().getSession().removeAttribute(UserCommon.USERINFO);
	}
	
}
