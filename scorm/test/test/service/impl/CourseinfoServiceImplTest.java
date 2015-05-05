package test.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.scorm.service.CourseinfoService;
import com.scorm.vo.Courseinfo;

/**
 * CourseinfoServiceImpl 测试
 * @author BFS Team 
 *
 */
public class CourseinfoServiceImplTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	Timestamp loginTime = new Timestamp(System.currentTimeMillis());
	CourseinfoService service = (CourseinfoService)context.getBean("courseinfoService");
	Courseinfo courseinfo = new Courseinfo( );
	List<Courseinfo> list =  new ArrayList<Courseinfo>();
	@Test
	public void testSaveCourseinfo(){
		service.saveCourseinfo(courseinfo);
	}
	
	@Test
	public void testDeleteCourseinfo(){
		list = service.findByCourseId(1);
		courseinfo = list.get(0);
		service.deleteCourseinfo(courseinfo);
	}
	
	@Test
	public void testFindByCourseId(){
		list = service.findByCourseId(1);
		System.out.println(list.size());
		System.out.println(list.get(0).getCourseName());
	}
	
	@Test
	public void testFindAllCourseinfo(){
		list = service.findAllCourseinfo();
		System.out.println(list.size());
	}

	@Test
	public void testFindByCourseType(){
		list = service.findByCourseType("c");
		System.out.println(list.size());
	}
	@Test
	public void testFindByCourseName(){
		list = service.findByCourseName("IT");
		System.out.println(list.size());
	}
}
