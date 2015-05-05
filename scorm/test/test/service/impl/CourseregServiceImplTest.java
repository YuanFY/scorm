package test.service.impl;

import java.sql.Timestamp;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.scorm.service.CourseregService;
import com.scorm.vo.Courseinfo;
import com.scorm.vo.Coursereg;
import com.scorm.vo.CourseregId;
import com.scorm.vo.Userinfo;
/**
 * 课程注册信息测试
 * @author BFS Team 
 *
 */
public class CourseregServiceImplTest {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	CourseregService service = (CourseregService) context.getBean("courseregService");
	
	Userinfo u = new Userinfo();
	Courseinfo c1 = new Courseinfo();
	Timestamp registerTimestamp = new Timestamp(System.currentTimeMillis());
	
	
	
	@Test
	public void testSaveCoursereg(){
		u.setUserId(1);
		c1.setCourseId(1);
		CourseregId c2 = new CourseregId(u,c1);
//		Coursereg c = new Coursereg(c2,registerTimestamp);		
//		service.saveCoursereg(c);
	}
	
	@Test
	public void testDeleteCoursereg(){
		u.setUserId(1);
		c1.setCourseId(1);
		CourseregId c2 = new CourseregId(u,c1);
//		Coursereg c = new Coursereg(c2,registerTimestamp);		
//		service.deleteCoursereg(c);
		
	}
	
	@Test
	public void testFindByUserId(){
		System.out.println(service.findByUserId(1).size());
	}
	
	@Test 
	public void testFindByCourseId(){
		System.out.println(service.findByCourseId(1));
	}
}
