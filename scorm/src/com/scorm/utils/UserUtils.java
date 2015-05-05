package com.scorm.utils;

import java.util.ArrayList;
import java.util.Date; 
import java.util.List; 

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.scorm.action.ActionSupport;
import com.scorm.service.CourseinfoService;
import com.scorm.service.CourseregService;
import com.scorm.service.ScoinfoService;
import com.scorm.service.UsercourseinfoService;
import com.scorm.service.UserinfoService;
import com.scorm.vo.Courseinfo;
import com.scorm.vo.Coursereg;
import com.scorm.vo.Scoinfo;
import com.scorm.vo.UserFinish;
import com.scorm.vo.Usercourseinfo;
import com.scorm.vo.Userinfo;

@SuppressWarnings("serial")
public class UserUtils extends ActionSupport{
	private static ApplicationContext context = null;
	private static UsercourseinfoService usercourseinfoService;
	private static UserinfoService userinfoService;
	private static CourseregService courseregService;
	private static ScoinfoService scoinfoService;
	private static CourseinfoService courseinfoService;
	
	static{
		context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		usercourseinfoService = (UsercourseinfoService)context.getBean("usercourseinfoService");
		userinfoService = (UserinfoService)context.getBean("userinfoService");
		courseregService = (CourseregService)context.getBean("courseregService");
		scoinfoService = (ScoinfoService)context.getBean("scoinfoService");
		courseinfoService = (CourseinfoService)context.getBean("courseinfoService");
	}
	
	/**
	 * 根据协同过滤推荐算法
	 * 		如果是新手
	 * 			根据性别，查看该性别学的最好的课程
	 * 			最热门的
	 * 		如果是已经学习过的人
	 * 			根据学习课程的类型以及考试成绩  进行进一步的推荐
	 * 			根据学习最多的
	 * @return
	 */
	public static List<Courseinfo> recommend(){
		List<Courseinfo> recommentList = new ArrayList<Courseinfo>();
		Userinfo userinfo = getSessionUser();
		/*
		 * 判断是不是新手
		 */
		List<Coursereg> list = courseregService.findByUserId(userinfo.getUserId());
		if(list!=null && list.size()>0){
			/*
			 * 根据学习课程的类型以及考试成绩  进行进一步的推荐
			 */
			List<Courseinfo> clist = courseinfoService.findSql("from Courseinfo order by registerNumber desc");
			for(Coursereg c:list){
				if(c.getExamScore()<80){
					//根据类型找
					String courseType = courseinfoService.findByCourseId(c.getId().getCourseinfo().getCourseId()).get(0).getCourseType();
					List<Courseinfo> rlist = courseinfoService.findSql("from Courseinfo where courseType='"+courseType+"' order by registerNumber desc");
					for(Courseinfo c1:rlist ){
						if(recommentList.size()<5){
							recommentList.add(c1);
						}else{
							break;
						}
					} 
				}
			}
			System.out.println("1长度："+recommentList.size());
			for(Courseinfo c2:clist ){
				if(recommentList.size()<10){
					boolean flag = true;
					for(Courseinfo c3 : recommentList){
						if(c3.getCourseId().equals(c2.getCourseId())){
							flag = false;
							break;
						}
					}
					if(flag){
						recommentList.add(c2);
					}
				}else{
					break;
				}
			}
			System.out.println("2长度："+recommentList.size());
		}else{
			//新手
			List<Courseinfo> rlist = courseinfoService.findSql("select a.courseId,a.courseName, a.courseType ,a.courseContent, a.registerNumber,a.uploadTime,a.coursePicture from Courseinfo a, Coursereg b , Userinfo c " +
					"where a.courseId=b.courseId and b.userId=c.userId and c.userSex='"+userinfo.getUserSex()+"'  group by b.courseId order by count(b.courseId)   desc");
			List<Courseinfo> clist = courseinfoService.findSql("select * from Courseinfo order by registerNumber desc");
			for(Courseinfo c:rlist ){
				if(recommentList.size()<5){
					recommentList.add(c);
				}else{
					break;
				}
			}
			System.out.println("长度："+recommentList.size());
			for(Courseinfo c:clist ){
				if(recommentList.size()<10){
					boolean flag = true;
					for(Courseinfo c3 : recommentList){
						if(c3.getCourseId().equals(c .getCourseId())){
							flag = false;
							break;
						}
					}
					if(flag){
						recommentList.add(c );
					}
				}else{
					break;
				}
			}
			System.out.println("2长度："+recommentList.size());
		}
		System.out.println("长度："+recommentList.size());
		return recommentList;
	} 
	public String getStudyTime(){
		Userinfo userinfo = getSessionUser();
		if(userinfo == null)
			return null;
		List<Usercourseinfo> list = usercourseinfoService.returnAllUsercourseinfo(userinfo.getUserId());
		String st[] = new String[2];
		for(int i=0; i<2; i++){
			st[i] = "0";
		}
		for(Usercourseinfo u:list){
			String t = u.getStudyTime();
			String s[] = t.split(":");
			for(int i=0; i<s.length; i++){
				int tmp = Integer.parseInt(s[i]);
				tmp += Integer.parseInt(st[i]);
				st[i] = tmp+"";
			}
		}
		String studyTime = "0";
		if(st[0]!="0")
			studyTime = st[0]+":"+st[1];
		return studyTime;
	}
	public String getLoginTime(){
		Userinfo userinfo =  getSessionUser();
		if(userinfo == null)
			return null;
		List<Userinfo> userList = userinfoService.findByUserId(userinfo.getUserId());
		
		if(userList!=null&&userList.size()>0){
			String loginTime = userList.get(0).getLoginTime().toString();
			loginTime = loginTime.substring(0,10);
			return loginTime;
		}
		return "0";
	}
	public int getExamScore(){
		Userinfo userinfo =  getSessionUser();
		if(userinfo == null)
			return 0;
		List<Coursereg> clist = courseregService.findByUserId(userinfo.getUserId());
		
		int examScore = 0;
		for(Coursereg c:clist){
			examScore += c.getExamScore();
		}
		if(clist!=null&&clist.size()>0)
			examScore /= clist.size();
		return examScore;
	}
	
	/**
	 * 得到某个人的完成课程
	 * @return
	 */
	public int getIsFinish(){
		Userinfo userinfo =  getSessionUser();
		List<Coursereg> list = courseregService.findSql("from Coursereg where userId = "+userinfo.getUserId());
		int isFinish = 0;
		for(Coursereg c : list){
			if(c.getExamScore()>60){
				isFinish ++;
			}
		}
		return isFinish;
	}
	public int getExamCourse(){
		Userinfo userinfo = getSessionUser();
		if(userinfo == null)
			return 0;
		List<Coursereg> clist = courseregService.findByUserId(userinfo.getUserId());
		
		int examCourse = 0;
		//根据userId 看注册了几门课程
		for(Coursereg c:clist){
			
			int courseId = c.getId().getCourseinfo().getCourseId();
			//根据courseId 查找该课程下有多少课件
			ScoinfoService scoinfoService = (ScoinfoService)context.getBean("scoinfoService");
			List<Scoinfo> scoList = scoinfoService.findByCourseId(courseId);
			boolean flag = false;
			for(Scoinfo sco:scoList){
				if(sco.getIsTest()==1){
					flag = true;
					break;
				}
			}
			if(flag)
				examCourse++;
		}
		return examCourse;
	}
	public int getTaskNum(){
		Userinfo userinfo = getSessionUser();
		if(userinfo == null)
			return 0;
		List<Coursereg> clist = courseregService.findByUserId(userinfo.getUserId());
		
		return clist.size();
	}
	
	public List<UserFinish> MaxFinish(){
		Userinfo userinfo = getSessionUser();
		if(userinfo == null)
			return null;
		List<UserFinish> t = new ArrayList<UserFinish>();
		
		List<Userinfo> allUser = userinfoService.findAllUserinfo();
		for(Userinfo u:allUser){
			UserFinish uf = new UserFinish();
			uf.setUserName(u.getUserName());
			List<Coursereg> tmpList = courseregService.findByUserId(u.getUserId());
			int isFinish = 0;
			for(Coursereg c:tmpList){
				
				int courseId = c.getId().getCourseinfo().getCourseId();
				//根据courseId 查找该课程下有多少课件
				
				List<Scoinfo> scoList = scoinfoService.findByCourseId(courseId);
				boolean flag = true;
				for(Scoinfo sco:scoList){
					//根据userId courseId scoId  查看该课件是否完成 
					List<Usercourseinfo> uclist = usercourseinfoService.returnUsercourseinfo(userinfo.getUserId(), courseId, sco.getScoId());
					if(uclist==null||uclist.size()==0){
						flag = false;
						break;
					}
					for(Usercourseinfo u1:uclist){
						if(u1.getIsFinish()==0){
							flag = false;
							break;
						}
					}
					if(!flag)
						break;
				}
				if(flag&&scoList.size()>0)
					isFinish ++;
			}
			uf.setIsFinish(isFinish);
			t.add(uf);
		}
		return t;
	}
	
	@SuppressWarnings("deprecation")
	public List<UserFinish> getAllCourse(){
		Userinfo userinfo = getSessionUser();
		if(userinfo == null)
			return null;
		List<Coursereg> clist = courseregService.findByUserId(userinfo.getUserId());
		
		CourseinfoService courseinfoService = (CourseinfoService)context.getBean("courseinfoService");
		List<UserFinish> uflist = new ArrayList<UserFinish>();
		for(Coursereg c:clist){
			UserFinish uf = new UserFinish();
			String time = (c.getRegisterTime().getYear()-100+1)+"-"+c.getRegisterTime().getMonth()+"-"+c.getRegisterTime().getDate();
			uf.setTime(time);
			
			Date date = new Date();
			if(date.getYear()>c.getRegisterTime().getYear()+1&&date.getMonth()>c.getRegisterTime().getMonth()){
				uf.setFinish("已过期");
			}
			else{
				uf.setFinish("学习中");
			}
			
			Courseinfo c1 = courseinfoService.findByCourseId(c.getId().getCourseinfo().getCourseId()).get(0);
			uf.setCourseId(c1.getCourseId());
			uf.setCourseName(c1.getCourseName());
			uf.setCourseType(c1.getCourseType());
			uf.setPicture(c1.getCoursePicture());
			uflist.add(uf);
			
		}
		return uflist;
	}
	/**
	 * 得到考试课程
	 * @return
	 */
	public List<UserFinish> getTestCourse(){
		Userinfo userinfo = getSessionUser();
		if(userinfo == null)
			return null;
		List<UserFinish> t = new ArrayList<UserFinish>();
		
		List<Coursereg> clist = courseregService.findByUserId(userinfo.getUserId());
		
		//根据userId 看注册了几门课程
		for(Coursereg c:clist){
			UserFinish uf = new UserFinish();
			@SuppressWarnings("deprecation")
			String time = (c.getRegisterTime().getYear()-100+1)+"-"+c.getRegisterTime().getMonth()+"-"+c.getRegisterTime().getDate();
			uf.setTime(time);
			int courseId = c.getId().getCourseinfo().getCourseId();
			//根据courseId 查找该课程下有多少课件
			List<Scoinfo> scoList = scoinfoService.findByCourseId(courseId);
			boolean flag = false;
			for(Scoinfo sco:scoList){
				System.out.println(sco.getIsTest());
				if(sco.getIsTest()==1){
					flag = true;
					break;
				}
			}
			if(flag)
			{
				if(c.getExamScore()>0){
					uf.setFinish("已考试");
				}
				else{
					uf.setFinish("未考试");
				}System.out.println("----");
				Courseinfo c1 = courseinfoService.findByCourseId(c.getId().getCourseinfo().getCourseId()).get(0);
				uf.setCourseId(c1.getCourseId());
				uf.setCourseName(c1.getCourseName());
				uf.setCourseType(c1.getCourseType());
				uf.setPicture(c1.getCoursePicture());
				t.add(uf);
			}
		}
		return t;
	}
	
	/**
	 * 得到最热门课程
	 * @return
	 */
	public List<Courseinfo> getHotCourse(){
		List<Courseinfo> list = courseinfoService.findSql("from Courseinfo order by registerNumber desc");
		List<Courseinfo> t = new ArrayList<Courseinfo>();
		for(Courseinfo c : list){
			if(t.size()<10){
				t.add(c);
			}else{
				break;
			}
		}
		System.out.println("热门课程size："+t.size());
		return list;
	}
	/**
	 * 得到最新课程
	 * @return
	 */
	public List<Courseinfo> getNewCourse(){
		List<Courseinfo> list = courseinfoService.findSql("from Courseinfo order by uploadTime ");
		List<Courseinfo> t = new ArrayList<Courseinfo>();
		for(Courseinfo c : list){
			if(t.size()<10){
				t.add(c);
			}else{
				break;
			}
		}
		System.out.println("最新课程size："+t.size());
		return list;
	}
	/**
	 * 根据课程Id返回该课程下的所有课件
	 * @param courseName
	 * @return
	 */
	public List<Scoinfo>  getCourseInSco(int courseId){
		List<Scoinfo> list = scoinfoService.findByCourseId(courseId);
		return list;
	}
}
