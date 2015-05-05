package test.service.impl;

import java.sql.Timestamp;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.scorm.service.UsercourseinfoService;
import com.scorm.service.UserinfoService;
import com.scorm.vo.Courseinfo;
import com.scorm.vo.Scoinfo;
import com.scorm.vo.Usercourseinfo;
import com.scorm.vo.UsercourseinfoId;
import com.scorm.vo.Userinfo;

/**
 * 学员学习信息测试
 * @author BFS Team 
 *
 */
public class UsercourseinfoServiceTest {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	UsercourseinfoService service = (UsercourseinfoService)context.getBean("usercourseinfoService");
	
	@Test
	public void testSave(){
		Userinfo u1 = new Userinfo();
		u1.setUserId(1);
		Courseinfo c1 = new Courseinfo();
		c1.setCourseId(1);
		Scoinfo s1 = new Scoinfo();
		s1.setScoId(1);
//		UsercourseinfoId uId = new UsercourseinfoId(u1,c1,s1);
//
//		Timestamp loginTime = new Timestamp(System.currentTimeMillis());
//		Usercourseinfo u = new Usercourseinfo(uId, "",null, null, loginTime, 0, "","","");
//		service.saveUsercourseinfo(u);
	}
	
	@Test
	public void delete(){
		Userinfo u1 = new Userinfo();
		u1.setUserId(1);
		Courseinfo c1 = new Courseinfo();
		c1.setCourseId(1);
		Scoinfo s1 = new Scoinfo();
		s1.setScoId(1);
//		UsercourseinfoId uId = new UsercourseinfoId(u1,c1,s1);
//
//		Timestamp loginTime = new Timestamp(System.currentTimeMillis());
//		Usercourseinfo u = new Usercourseinfo(uId, "",null, null, loginTime, 0, "","","");
//		service.deleteUsercourseinfo(u);
	}
	
	@Test
	public void testUpdate(){
		Userinfo u1 = new Userinfo();
		u1.setUserId(1);
		Courseinfo c1 = new Courseinfo();
		c1.setCourseId(1);
		Scoinfo s1 = new Scoinfo();
		s1.setScoId(1);
//		UsercourseinfoId uId = new UsercourseinfoId(u1,c1,s1);
//
//		Timestamp loginTime = new Timestamp(System.currentTimeMillis());
//		Usercourseinfo u = new Usercourseinfo(uId, "",null, null, loginTime, 0, "","","");
//		service.updateUsercourseinfo(u);
	}
	
	@Test
	public void testReturnUsercourseinfo(){
		Userinfo u1 = new Userinfo();
		u1.setUserId(1);
		Courseinfo c1 = new Courseinfo();
		c1.setCourseId(1);
		Scoinfo s1 = new Scoinfo();
		s1.setScoId(1);
//		UsercourseinfoId uId = new UsercourseinfoId(u1,c1,s1);
//
//		Timestamp loginTime = new Timestamp(System.currentTimeMillis());
//		Usercourseinfo u = new Usercourseinfo(uId, "",null, null, loginTime, 0, "","","");
//		System.out.println(service.returnUsercourseinfo(u).size());
	}
	@Test
	public void testReturnALLUsercourseinfo(){
		Userinfo u1 = new Userinfo();
		u1.setUserId(1);
		Courseinfo c1 = new Courseinfo();
		c1.setCourseId(1);
		Scoinfo s1 = new Scoinfo();
		s1.setScoId(1);
//		UsercourseinfoId uId = new UsercourseinfoId(u1,c1,s1);
//
//		Timestamp loginTime = new Timestamp(System.currentTimeMillis());
//		Usercourseinfo u = new Usercourseinfo(uId, "",null, null, loginTime, 0, "","","");
//		System.out.println(service.returnAllUsercourseinfo(u).size());
	}
}
