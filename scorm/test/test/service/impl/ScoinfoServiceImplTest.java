package test.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.scorm.service.CourseinfoService;
import com.scorm.service.ScoinfoService;
import com.scorm.service.impl.CourseinfoServiceImpl;
import com.scorm.vo.Courseinfo;
import com.scorm.vo.Scoinfo;

/**
 * ScoinfoServiceImplTest 测试
 * @author BFS Team 
 *
 */
@Component(value="scoinfoServiceImplTest")
public class ScoinfoServiceImplTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	Timestamp loginTime = new Timestamp(System.currentTimeMillis());
	ScoinfoService service = (ScoinfoService)context.getBean("scoinfoService");
	CourseinfoService service1 = (CourseinfoService)context.getBean("courseinfoService");
	Courseinfo courseinfo =service1.findByCourseId(1).get(0);
	Scoinfo scoinfo = new Scoinfo();
	
	CourseinfoService c = new CourseinfoServiceImpl();
	
	@Test
	public void testSave(){
		//service.saveScoinfo(scoinfo);
		c.findCourseName("IT");
	}
	
	@Test
	public void deleteScoinfo() {
		scoinfo.setScoId(1);
		service.deleteScoinfo(scoinfo);
	}
	@Test
	public void updateScoinfo() {
		scoinfo.setScoName("java");
		scoinfo.setScoId(1);
		service.updateScoinfo(scoinfo);
	}

	@Test
	public void findAllScoinfo() {
		System.out.println(service.findAllScoinfo().size());
	}
	@Test
	public void findByScoName() {
		System.out.println(service.findByScoName("c").size());
	}

	@Test
	public void findByScoId() {
		System.out.println(service.findByScoId(1).size());
	}
	@Test
	public void findByCourseId() {
		System.out.println(service.findByCourseId(1).size());
	}
}
