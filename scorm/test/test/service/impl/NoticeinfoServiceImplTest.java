package test.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.scorm.service.NoticeinfoService;
import com.scorm.vo.Noticeinfo;

/**
 * NoticeinfoServiceImplTest
 * @author BFS Team 
 *
 */
public class NoticeinfoServiceImplTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	Timestamp loginTime = new Timestamp(System.currentTimeMillis());
	NoticeinfoService service = (NoticeinfoService)context.getBean("noticeinfoService");
	Noticeinfo noticeinfo = new Noticeinfo("c", "c语言使用指数上升", loginTime);
	List<Noticeinfo> list =  new ArrayList<Noticeinfo>();
	
	@Test
	public void testSaveNoticeinfo(){
		service.saveNoticeinfo(noticeinfo);
	}
	
	@Test
	public void testDeleteNoticeinfo(){
		list = service.findByNoticeId(1);
		noticeinfo = list.get(0);
		service.deleteNoticeinfo(noticeinfo);
	}
	
	@Test
	public void testFindByNoticeId(){
		list = service.findByNoticeId(1);
		System.out.println(list.size());
		System.out.println(list.get(0).getNoticeContent());
	}
	
	@Test
	public void testFindAllUserinfo(){
		list = service.findAllNoticeinfo();
		System.out.println(list.size());
	}
	
	@Test
	public void testFindByNoticeTitle(){
		list = service.findByNoticeTitle("c");
		System.out.println(list.size());
	}
	
	@Test
	public void testFindByNoticeContent(){
		list = service.findByNoticeContent("c语言");
		System.out.println(list.size());
	}
}
