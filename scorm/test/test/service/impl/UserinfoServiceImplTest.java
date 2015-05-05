package test.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.scorm.service.UserinfoService;
import com.scorm.vo.Userinfo;

/**
 * 
 * @author BFS Team 
 *
 */
public class UserinfoServiceImplTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	Timestamp loginTime = new Timestamp(System.currentTimeMillis());
	UserinfoService service = (UserinfoService)context.getBean("userinfoService");
	Userinfo userinfo = new Userinfo( );
	List<Userinfo> list =  new ArrayList<Userinfo>();
	
	@Test
	public void testSaveUserinfo(){
		service.saveUserinfo(userinfo);
	}
	
	@Test
	public void testDeleteUserinfo(){
		list = service.findByUserId(1);
		userinfo = list.get(0);
		service.deleteUserinfo(userinfo);
	}
	
	@Test
	public void testFindByUserId(){
		list = service.findByUserId(1);
		System.out.println(list.size());
		System.out.println(list.get(0).getUserName());
	}
	
	@Test
	public void testFindAllUserinfo(){
		list = service.findAllUserinfo();
		System.out.println(list.size());
	}
	
	@Test
	public void testFindByUserName(){
		list = service.findByUserName("test");
		System.out.println(list.size());
	}
}
