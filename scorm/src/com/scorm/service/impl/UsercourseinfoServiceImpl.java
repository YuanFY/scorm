package com.scorm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.scorm.dao.CourseregDAO;
import com.scorm.dao.ScoinfoDAO;
import com.scorm.dao.UsercourseinfoDAO;
import com.scorm.service.UsercourseinfoService;
import com.scorm.vo.Classstatisticinfo;
import com.scorm.vo.Coursereg;
import com.scorm.vo.Studyrecordinfo;
import com.scorm.vo.Usercourseinfo;
import com.scorm.vo.UsercourseinfoId;
/**
 * 学员学习信息服务层
 * @author BFS Team 
 *
 */
@Service(value="usercourseinfoService")
public class UsercourseinfoServiceImpl implements UsercourseinfoService{

	@Resource(name="usercourseinfoDAO")
	private UsercourseinfoDAO usercourseinfoDAO;
	
	@Resource(name="scoinfoDAO")
	private ScoinfoDAO scoinfoDAO;

	@Resource(name="courseregDAO")
	private CourseregDAO courseregDAO;

	
	@Override
	public void saveUsercourseinfo(Usercourseinfo usercourseinfo) {
		this.usercourseinfoDAO.saveUsercourseinfo(usercourseinfo);
	}

	@Override
	public void deleteUsercourseinfo(Usercourseinfo usercourseinfo) {
		this.usercourseinfoDAO.deleteUsercourseinfo(usercourseinfo);
	}

	@Override
	public void updateUsercourseinfo(Usercourseinfo usercourseinfo) {
		this.usercourseinfoDAO.updateUsercourseinfo(usercourseinfo);
	}

	@Override
	public List<Usercourseinfo> returnUsercourseinfo(Integer userId, Integer courseId, Integer scoId) {
		// TODO Auto-generated method stub
		return this.usercourseinfoDAO.returnUsercourseinfo(userId, courseId, scoId);
	}

	@Override
	public List<Usercourseinfo> returnAllUsercourseinfo(Integer userId) {
		// TODO Auto-generated method stub
		return this.usercourseinfoDAO.returnAllUsercourseinfo(userId);
	}
	
	@Override
	public List<Usercourseinfo> returnUsercourseinfo1(Integer userId,
			Integer courseId) {
		
		return this.usercourseinfoDAO.returnUsercourseinfo1(userId, courseId);
	}
	@Override
	public List<Classstatisticinfo> getCourseStatistic() {
		 List<Usercourseinfo> list =  usercourseinfoDAO.allinfo();
		 List<Classstatisticinfo> dm = new ArrayList<Classstatisticinfo>(); 
		 if(list.size()==0)
		 {
			 return null;
		 }
		 else{
		 for(Usercourseinfo ls:list){
			 	Classstatisticinfo info = new Classstatisticinfo();
				UsercourseinfoId y = ls.getId();
				String courseName= y.getCourseinfo().getCourseName();
				int courseId = y.getCourseinfo().getCourseId();
				String scoName = this.scoinfoDAO.findByScoId(y.getScoId()).get(0).getScoName();
				int scoScore;
				if(ls.getScoScore().equals("")){
					scoScore = 0;
				}else{
					scoScore = ls.getScoScore();
				}
				String studyTime = ls.getStudyTime();
				if(dm.size()<=0){
					info.setCourseName(courseName);
					info.setScoName(scoName);
					info.setNumber(1);
					info.setScoScore(scoScore);
					info.setStudyTime(studyTime);
					info.setCourseId(courseId);
					dm.add(info);
				}else{
					int i;
					for(i=0;i<dm.size();i++){
					//	System.out.println(y);
						if(courseName.equals(dm.get(i).getCourseName())){
							dm.get(i).setScoScore((dm.get(i).getScoScore()+scoScore)/2);
							dm.get(i).setNumber(dm.get(i).getNumber()+1);
							dm.get(i).getScoScore();
							
							String s1[]=dm.get(i).getStudyTime().split(":");
							String s2[]=studyTime.split(":");
							int s3[] = new int[3];
							for(int k=0;k<s1.length;k++)
							{
								s3[k]=0;
							}
							for(int k=s1.length-1,o=0;k>=0;k--,o++)
							{
								if(k>=s2.length){
									s3[o]=s3[o]+Integer.parseInt(s1[k]);
								}else{
									s3[o]=s3[o]+Integer.parseInt(s1[k])+Integer.parseInt(s2[k]);
								}
							//	System.out.println(s3[o]);
								if(k!=0){
									if(s3[o]>60){
										s3[o]=s3[o]-60;
										s3[o+1]++;
									}
								}
							}
							
							String time = s3[0]+":"+s3[1]+":"+s3[2];
							dm.get(i).setStudyTime(time);
							break;
						}
					}
					if(i==dm.size()){
						if(dm.size()==1){
							if(dm.get(0).getCourseName().equals(courseName)){
							//System.out.println(y);}
							}
							else{
								info.setCourseName(courseName);
								info.setScoName(scoName);
								info.setScoScore(scoScore);
								info.setNumber(1);
								info.setStudyTime(studyTime);
								info.setCourseId(courseId);
								dm.add(info);
							}
						}
						else{
							info.setCourseName(courseName);
							info.setScoName(scoName);
							info.setScoScore(scoScore);
							info.setNumber(1);
							info.setStudyTime(studyTime);
							info.setCourseId(courseId);
							dm.add(info);
						}
					}
				}
			}
			return dm;
		 }
	}
	public List<Classstatisticinfo> getSco(String course){
		List<Usercourseinfo> list =  usercourseinfoDAO.allinfo();
		 List<Classstatisticinfo> dm = new ArrayList<Classstatisticinfo>(); 
		 if(list.size()==0)
		 {
			 return null;
		 }
		 else{
		 for(Usercourseinfo ls:list){
			 	Classstatisticinfo info = new Classstatisticinfo();
				UsercourseinfoId y = ls.getId();
				String courseName= y.getCourseinfo().getCourseName();
				String scoName = this.scoinfoDAO.findByScoId(y.getScoId()).get(0).getScoName();
				String studyTime = ls.getStudyTime();
				int courseId = y.getCourseinfo().getCourseId();
				int scoScore;
				if(ls.getScoScore().equals("")){
					scoScore = 0;
				}else{
					scoScore = ls.getScoScore();
				}
					if(courseName.equals(course))
					{ 
						if(dm.size()<=0){
							info.setCourseName(courseName);
							info.setScoName(scoName);
							info.setNumber(1);
							info.setScoScore(scoScore);
							info.setStudyTime(studyTime);
							info.setCourseId(courseId);
							dm.add(info);
						}
						else{
						int i;
						for(i=0;i<dm.size();i++){
							if(courseName.equals(dm.get(i).getCourseName())){
								dm.get(i).setNumber(dm.get(i).getNumber()+1);
								dm.get(i).setScoScore((dm.get(i).getScoScore()+scoScore)/2);
								String s1[]=dm.get(i).getStudyTime().split(":");
								String s2[]=studyTime.split(":");
								int s3[] = new int[3];
								for(int k=0;k<s1.length;k++)
								{
									s3[k]=0;
								}
								for(int k=s1.length-1,o=0;k>=0;k--,o++)
								{
									if(k>=s2.length){
										s3[o]=s3[o]+Integer.parseInt(s1[k]);
									}else{
									s3[o]=s3[o]+Integer.parseInt(s1[k])+Integer.parseInt(s2[k]);
									}
								//	System.out.println(s3[o]);
									if(k!=0){
										if(s3[o]>60){
											s3[o]=s3[o]-60;
											s3[o+1]++;
										}
									}
								}
								
								String time = s3[0]+":"+s3[1]+":"+s3[2];
								dm.get(i).setStudyTime(time);
								break;
							}
						}
						if(i==dm.size()){
						//System.out.println("比较"+"y="+y+" 之前的"+dm.get(i).getDataYear());
							if(dm.size()==1){
								if(dm.get(0).getScoName().equals(scoName)){
									//System.out.println(y);}
								}
							else{
								info.setCourseName(courseName);
								info.setScoName(scoName);
								info.setNumber(1);
								info.setScoScore(scoScore);
								info.setStudyTime(studyTime);
								info.setCourseId(courseId);
								dm.add(info);
							}
						}
						else{
							info.setCourseName(courseName);
							info.setScoName(scoName);
							info.setNumber(1);
							info.setScoScore(scoScore);
							info.setStudyTime(studyTime);
							info.setCourseId(courseId);
							dm.add(info);
						}
					}
				}
			}
		 }
		 return dm;
	}
	}

	@Override
	public List<Studyrecordinfo> getStudyRecord(int id) {
		List<Usercourseinfo> usercourseinfo = usercourseinfoDAO.findUsercourseinfoById(id);
	//	System.out.println(usercourseinfo.size());
		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();
		dm = getRecord(usercourseinfo,id);
		return dm;
	}

	@Override
	public List<Studyrecordinfo> getStudyRecordByCourseType(String courseType,
			int userId) {
		List<Usercourseinfo> usercourseinfo = usercourseinfoDAO.findUsercourseinfoByCourseType(userId, courseType);
	//	System.out.println(courseType);
		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();
		dm = getRecord(usercourseinfo,userId);
		return dm;
	}

	@Override
	public List<Studyrecordinfo> getStudyRecordByCourseName(String courseName,
			int userId) {
		List<Usercourseinfo> usercourseinfo = usercourseinfoDAO.findUsercourseinfoByCourseName(userId, courseName);
		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();
		//List<Studyrecordinfo> end = new ArrayList<Studyrecordinfo>();
		dm = getRecord(usercourseinfo,userId);
		return dm;
	}

	@Override
	public List<Studyrecordinfo> getStudyRecordByAll(String courseName,
			String courseType, int userId) {
		List<Usercourseinfo> usercourseinfo = usercourseinfoDAO.findUsercourseinfoByAll(userId, courseName, courseType);
		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();
		dm = getRecord(usercourseinfo,userId);
		return dm;
	}
	public List<Studyrecordinfo> getRecord1(List<Coursereg> courseList,int id){
		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();  
		for(Coursereg ls:courseList){
			int y,m,d,flag=0;
			Studyrecordinfo study = new Studyrecordinfo();
			int courseId = ls.getId().getCourseinfo().getCourseId();
			List<Coursereg> coursereg = courseregDAO.findById(id, courseId);
			if(coursereg.size()>0){
				String Time = coursereg.get(0).getRegisterTime().toString().substring(0, 10);
				String courseName = ls.getId().getCourseinfo().getCourseName();
				String studyTime = ls.getRegisterTime().toString();
				int examRecord = coursereg.get(0).getExamScore();
				if(dm.size()<=0){
					study.setCourseName(courseName);
					study.setCourseType(ls.getId().getCourseinfo().getCourseType());
					String s1[]=Time.split("-");
					int s3[] = new int[3];
					for(int k=0;k<s1.length;k++)
					{
						s3[k]=0;
					}
					for(int k=0,o=0;k<s1.length;k++,o++)
					{
						s3[o]=s3[o]+Integer.parseInt(s1[k]);
					}
					study.setRegisterTime(Time);
				/*
				 * 判断是否过期
				 */
					Calendar cal=Calendar.getInstance();    
					y=cal.get(Calendar.YEAR);    
					m=cal.get(Calendar.MONTH);    
					d=cal.get(Calendar.DATE); 
					if(y>(s3[0]+10)){
						flag=1;
					}else if(y==(s3[0]+10)){
						if(m>s3[1]){
						flag=1;
					}else if(m==s3[1]){
						if(d>=s3[2]){
							flag=1;
						}
					}
				}
				String time;
				if(s3[1]<10){
					time = (s3[0]+10)+"-0"+s3[1]+"-"+s3[2];
				}else{
					time = (s3[0]+10)+"-"+s3[1]+"-"+s3[2];
				}
				study.setCourseId(courseId);
				study.setEndTime(time);
				study.setIsEnd(flag);
				study.setStudyTime(studyTime);
				study.setLastTime(ls.getRegisterTime().toString());
				study.setExamRecord(examRecord);
				dm.add(study);
			}
			else{
				int i;
				for(i=0;i<dm.size();i++){
					if(courseName.equals(dm.get(i).getCourseName())){
						dm.get(i).setStudyTime(dm.get(i).getStudyTime()+studyTime);
						
						dm.get(i).setExamRecord((examRecord+dm.get(i).getExamRecord())/2);
					//	dm.add(study);
						break;
					}
				}
				if(i==dm.size()){
					if(dm.size()==1){
						if(dm.get(0).getCourseName().equals(courseName)){
						//System.out.println(y);}
						}
						else{
							study.setCourseName(courseName);
							study.setCourseType(ls.getId().getCourseinfo().getCourseType());
							String s1[]=Time.split("-");
							int s3[] = new int[3];
							for(int k=0;k<s1.length;k++)
							{
								s3[k]=0;
							}
							for(int k=0,o=0;k<s1.length;k++,o++)
							{
								s3[o]=s3[o]+Integer.parseInt(s1[k]);
							}
							study.setRegisterTime(Time);

							/*
							 * 判断是否过期
							 */
							Calendar cal=Calendar.getInstance();    
							y=cal.get(Calendar.YEAR);    
							m=cal.get(Calendar.MONTH);    
							d=cal.get(Calendar.DATE); 
							if(y>(s3[0]+10)){
								flag=1;
							}else if(y==(s3[0]+10)){
								if(m>s3[1]){
									flag=1;
								}else if(m==s3[1]){
									if(d>=s3[2]){
										flag=1;
									}
								}
							}
							String time;
							if(s3[1]<10){
								time = (s3[0]+10)+"-0"+s3[1]+"-"+s3[2];
							}else{
								time = (s3[0]+10)+"-"+s3[1]+"-"+s3[2];
							}
							
							study.setEndTime(time);
							study.setStudyTime(studyTime); 
							study.setExamRecord(examRecord);
							dm.add(study);
						}
					}
					else{
						study.setCourseName(courseName);
						study.setCourseType(ls.getId().getCourseinfo().getCourseType());
						String s1[]=Time.split("-");
						int s3[] = new int[3];
						for(int k=0;k<s1.length;k++)
						{
							s3[k]=0;
						}
						for(int k=0,o=0;k<s1.length;k++,o++)
						{
							s3[o]=s3[o]+Integer.parseInt(s1[k]);
						}
						study.setRegisterTime(Time);
						String time = (s3[0]+10)+"-"+s3[1]+"-"+s3[2];
						/*
						 * 判断是否过期
						 */
						Calendar cal=Calendar.getInstance();    
						y=cal.get(Calendar.YEAR);    
						m=cal.get(Calendar.MONTH);    
						d=cal.get(Calendar.DATE); 
						if(y>(s3[0]+10)){
							flag=1;
						}else if(y==(s3[0]+10)){
							if(m>s3[1]){
								flag=1;
							}else if(m==s3[1]){
								if(d>=s3[2]){
									flag=1;
								}
							}
						}
						study.setEndTime(time);
						study.setStudyTime(studyTime);
						study.setExamRecord(examRecord);
						dm.add(study);
					}
				}
		}
		}
		}
		if(dm.size()<=0){
			dm=null;
		}
		return dm;
	}
	
	public List<Studyrecordinfo> getRecord(List<Usercourseinfo> usercourseinfo,int id){
		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();  
		for(Usercourseinfo ls:usercourseinfo){
			int y,m,d,flag=0;
			Studyrecordinfo study = new Studyrecordinfo();
			int courseId = ls.getId().getCourseinfo().getCourseId();
			List<Coursereg> coursereg = courseregDAO.findById(id, courseId);
			if(coursereg.size()>0){
				String Time = coursereg.get(0).getRegisterTime().toString().substring(0, 10);
				String courseName = ls.getId().getCourseinfo().getCourseName();
				String studyTime = ls.getStudyTime();
				int examRecord = coursereg.get(0).getExamScore();
				if(dm.size()<=0){
					study.setCourseName(courseName);
					study.setCourseType(ls.getId().getCourseinfo().getCourseType());
					String s1[]=Time.split("-");
					int s3[] = new int[3];
					for(int k=0;k<s1.length;k++)
					{
						s3[k]=0;
					}
					for(int k=0,o=0;k<s1.length;k++,o++)
					{
						s3[o]=s3[o]+Integer.parseInt(s1[k]);
					}
					study.setRegisterTime(Time);
				/*
				 * 判断是否过期
				 */
					Calendar cal=Calendar.getInstance();    
					y=cal.get(Calendar.YEAR);    
					m=cal.get(Calendar.MONTH);    
					d=cal.get(Calendar.DATE); 
					if(y>(s3[0]+10)){
						flag=1;
					}else if(y==(s3[0]+10)){
						if(m>s3[1]){
						flag=1;
					}else if(m==s3[1]){
						if(d>=s3[2]){
							flag=1;
						}
					}
				}
				String time;
				if(s3[1]<10){
					time = (s3[0]+10)+"-0"+s3[1]+"-"+s3[2];
				}else{
					time = (s3[0]+10)+"-"+s3[1]+"-"+s3[2];
				}
				study.setCourseId(courseId);
				study.setEndTime(time);
				study.setIsEnd(flag);
				study.setStudyTime(studyTime);
				study.setLastTime(ls.getViewTime().toString());
				study.setStudyStatus(ls.getIsFinish());
				study.setExamRecord(examRecord);
				dm.add(study);
			}
			else{
				int i;
				for(i=0;i<dm.size();i++){
					if(courseName.equals(dm.get(i).getCourseName())){
						dm.get(i).setStudyTime(dm.get(i).getStudyTime()+studyTime);
						if(ls.getIsFinish()==0){
							dm.get(i).setStudyStatus(ls.getIsFinish());
						}
						dm.get(i).setExamRecord((examRecord+dm.get(i).getExamRecord())/2);
					//	dm.add(study);
						break;
					}
				}
				if(i==dm.size()){
					if(dm.size()==1){
						if(dm.get(0).getCourseName().equals(courseName)){
						//System.out.println(y);}
						}
						else{
							study.setCourseName(courseName);
							study.setCourseType(ls.getId().getCourseinfo().getCourseType());
							String s1[]=Time.split("-");
							int s3[] = new int[3];
							for(int k=0;k<s1.length;k++)
							{
								s3[k]=0;
							}
							for(int k=0,o=0;k<s1.length;k++,o++)
							{
								s3[o]=s3[o]+Integer.parseInt(s1[k]);
							}
							study.setRegisterTime(Time);

							/*
							 * 判断是否过期
							 */
							Calendar cal=Calendar.getInstance();    
							y=cal.get(Calendar.YEAR);    
							m=cal.get(Calendar.MONTH);    
							d=cal.get(Calendar.DATE); 
							if(y>(s3[0]+10)){
								flag=1;
							}else if(y==(s3[0]+10)){
								if(m>s3[1]){
									flag=1;
								}else if(m==s3[1]){
									if(d>=s3[2]){
										flag=1;
									}
								}
							}
							String time;
							if(s3[1]<10){
								time = (s3[0]+10)+"-0"+s3[1]+"-"+s3[2];
							}else{
								time = (s3[0]+10)+"-"+s3[1]+"-"+s3[2];
							}
							
							study.setEndTime(time);
							study.setStudyTime(studyTime);
							study.setLastTime(ls.getViewTime().toString());
							study.setStudyStatus(ls.getIsFinish());
							study.setExamRecord(examRecord);
							dm.add(study);
						}
					}
					else{
						study.setCourseName(courseName);
						study.setCourseType(ls.getId().getCourseinfo().getCourseType());
						String s1[]=Time.split("-");
						int s3[] = new int[3];
						for(int k=0;k<s1.length;k++)
						{
							s3[k]=0;
						}
						for(int k=0,o=0;k<s1.length;k++,o++)
						{
							s3[o]=s3[o]+Integer.parseInt(s1[k]);
						}
						study.setRegisterTime(Time);
						String time = (s3[0]+10)+"-"+s3[1]+"-"+s3[2];
						/*
						 * 判断是否过期
						 */
						Calendar cal=Calendar.getInstance();    
						y=cal.get(Calendar.YEAR);    
						m=cal.get(Calendar.MONTH);    
						d=cal.get(Calendar.DATE); 
						if(y>(s3[0]+10)){
							flag=1;
						}else if(y==(s3[0]+10)){
							if(m>s3[1]){
								flag=1;
							}else if(m==s3[1]){
								if(d>=s3[2]){
									flag=1;
								}
							}
						}
						study.setEndTime(time);
						study.setStudyTime(studyTime);
						study.setLastTime(ls.getViewTime().toString());
						study.setStudyStatus(ls.getIsFinish());
						study.setExamRecord(examRecord);
						dm.add(study);
					}
				}
		}
		}
		}
		if(dm.size()<=0){
			dm=null;
		}
		return dm;
	}
	@Override
	public List<Studyrecordinfo> getStudyCourse(int id) {
		 
		//List<Usercourseinfo> usercourseinfo = usercourseinfoDAO.findUsercourseinfoById(id);
	//	System.out.println(usercourseinfo.size());
		
//		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();
//		List<Studyrecordinfo> end = new ArrayList<Studyrecordinfo>();
//		if(usercourseinfo.size()>0){
//			dm = getRecord(usercourseinfo,id);
//			for(Studyrecordinfo ls:dm){
//				if(ls.getIsEnd()==0&&ls.getStudyStatus()==0){
//					end.add(ls);
//				}
//			}
//		}else{
//			end = null;
//		}
		List<Coursereg> usercourseinfo = courseregDAO.findByUserId(id);
		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();
		List<Studyrecordinfo> end = new ArrayList<Studyrecordinfo>();
		if(usercourseinfo.size()>0){
			dm = getRecord1(usercourseinfo,id);
			for(Studyrecordinfo ls:dm){
				if(ls.getIsEnd()==0&&ls.getStudyStatus()==0){
					end.add(ls);
				}
			}
		}else{
			end = null;
		}
		return end;
	}
	@Override
	public List<Studyrecordinfo> getendCourse(int id) {
//		List<Usercourseinfo> usercourseinfo = usercourseinfoDAO.findUsercourseinfoById(id);
//	//	System.out.println(usercourseinfo.size());
//		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();
//		List<Studyrecordinfo> end = new ArrayList<Studyrecordinfo>();
//		if(usercourseinfo.size()>0){
//			dm = getRecord(usercourseinfo,id);
//			for(Studyrecordinfo ls:dm){
//				if(ls.getStudyStatus()==1){
//					end.add(ls);
//				}
//			}
//		}else{
//			end = null;
//		}
		List<Coursereg> usercourseinfo = courseregDAO.findByUserId(id);
		System.out.println("usercourseinfo="+usercourseinfo.size());
		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();
		List<Studyrecordinfo> end = new ArrayList<Studyrecordinfo>();
		if(usercourseinfo.size()>0){
			dm = getRecord1(usercourseinfo,id);
			for(Studyrecordinfo ls:dm){
				if(ls.getExamRecord()>60){
					end.add(ls);
				}
			}
		}else{
			end = null;
		}
		return end;
	}

	@Override
	public List<Studyrecordinfo> getlastCourse(int id) {
		//List<Usercourseinfo> usercourseinfo = usercourseinfoDAO.findUsercourseinfoById(id);
		List<Coursereg> usercourseinfo = courseregDAO.findByUserId(id);
		// TODO Auto-generated method stub
		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();
		List<Studyrecordinfo> end = new ArrayList<Studyrecordinfo>();
		if(usercourseinfo.size()>0){
			dm = getRecord1(usercourseinfo,id);
			for(Studyrecordinfo ls:dm){
				 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
 		         
				if(ls.getEndTime().compareTo(df.format(new Date()))<0){
					end.add(ls);
				}
			}
		}else{
			end = null;
		}
		return end;
	}

	@Override
	public List<Studyrecordinfo> getStudyCourseByCourseType(String courseType,
			int userId) {
		List<Usercourseinfo> usercourseinfo = usercourseinfoDAO.findUsercourseinfoByCourseType(userId, courseType);
		//	System.out.println(usercourseinfo.size());
		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();
		List<Studyrecordinfo> end = new ArrayList<Studyrecordinfo>();
		if(usercourseinfo.size()>0){
			dm = getRecord(usercourseinfo,userId);
			for(Studyrecordinfo ls:dm){
				if(ls.getIsEnd()==0&&ls.getStudyStatus()==0){
					end.add(ls);
				}
			}
		}else{
			end = null;
		}
		return end;
	}

	@Override
	public List<Studyrecordinfo> getStudyCourseByCourseName(String courseName,
			int userId) {
	//	List<Usercourseinfo> usercourseinfo = usercourseinfoDAO.findUsercourseinfoByCourseType(userId, courseType);
		List<Usercourseinfo> usercourseinfo = usercourseinfoDAO.findUsercourseinfoByCourseName(userId, courseName);
		//	System.out.println(usercourseinfo.size());
		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();
		List<Studyrecordinfo> end = new ArrayList<Studyrecordinfo>();
		if(usercourseinfo.size()>0){
			dm = getRecord(usercourseinfo,userId);
			for(Studyrecordinfo ls:dm){
				if(ls.getIsEnd()==0&&ls.getStudyStatus()==0){
					end.add(ls);
				}
			}
		}else{
			end = null;
		}
		return end;
	}

	@Override
	public List<Studyrecordinfo> getStudyCourseByAll(String courseName,
			String courseType, int userId) {
		List<Usercourseinfo> usercourseinfo = usercourseinfoDAO.findUsercourseinfoByAll(userId, courseName,courseType);
		//	System.out.println(usercourseinfo.size());
		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();
		List<Studyrecordinfo> end = new ArrayList<Studyrecordinfo>();
		if(usercourseinfo.size()>0){
			dm = getRecord(usercourseinfo,userId);
			for(Studyrecordinfo ls:dm){
				if(ls.getIsEnd()==0&&ls.getStudyStatus()==0){
					end.add(ls);
				}
			}
		}else{
			end = null;
		}
		return end;
	}

	@Override
	public List<Studyrecordinfo> getendCourseByCourseType(String courseType,
			int userId) {
		List<Usercourseinfo> usercourseinfo = usercourseinfoDAO.findUsercourseinfoByCourseType(userId, courseType);
		//	System.out.println(usercourseinfo.size());
		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();
		List<Studyrecordinfo> end = new ArrayList<Studyrecordinfo>();
		if(usercourseinfo.size()>0){
			dm = getRecord(usercourseinfo,userId);
			for(Studyrecordinfo ls:dm){
				if(ls.getStudyStatus()==1&&ls.getExamRecord()>=60){
					end.add(ls);
				}
			}
		}else{
			end = null;
		}
		return end;
	}

	@Override
	public List<Studyrecordinfo> getendCourseByCourseName(String courseName,
			int userId) {
		List<Usercourseinfo> usercourseinfo = usercourseinfoDAO.findUsercourseinfoByCourseType(userId, courseName);
		//	System.out.println(usercourseinfo.size());
		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();
		List<Studyrecordinfo> end = new ArrayList<Studyrecordinfo>();
		if(usercourseinfo.size()>0){
			dm = getRecord(usercourseinfo,userId);
			for(Studyrecordinfo ls:dm){
				if(ls.getStudyStatus()==1&&ls.getExamRecord()>=60){
					end.add(ls);
				}
			}
		}else{
			end = null;
		}
		return end;
	}

	@Override
	public List<Studyrecordinfo> getendCourseByAll(String courseName,
			String courseType, int userId) {
		List<Usercourseinfo> usercourseinfo = usercourseinfoDAO.findUsercourseinfoByAll(userId, courseName,courseType);
		//	System.out.println(usercourseinfo.size());
		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();
		List<Studyrecordinfo> end = new ArrayList<Studyrecordinfo>();
		if(usercourseinfo.size()>0){
			dm = getRecord(usercourseinfo,userId);
			for(Studyrecordinfo ls:dm){
				if(ls.getStudyStatus()==1&&ls.getExamRecord()>=60){
					end.add(ls);
				}
			}
		}else{
			end = null;
		}
		return end;
	}

	@Override
	public List<Studyrecordinfo> getlastCourseByCourseType(String courseType,
			int userId) {
		List<Usercourseinfo> usercourseinfo = usercourseinfoDAO.findUsercourseinfoByCourseType(userId, courseType);
		//	System.out.println(usercourseinfo.size());
		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();
		List<Studyrecordinfo> end = new ArrayList<Studyrecordinfo>();
		if(usercourseinfo.size()>0){
			dm = getRecord(usercourseinfo,userId);
			for(Studyrecordinfo ls:dm){
				if(ls.getIsEnd()==1){
					end.add(ls);
				}
			}
		}else{
			end = null;
		}
		return end;
	}

	@Override
	public List<Studyrecordinfo> getlastCourseByCourseName(String courseName,
			int userId) {
		List<Usercourseinfo> usercourseinfo = usercourseinfoDAO.findUsercourseinfoByCourseType(userId, courseName);
		//	System.out.println(usercourseinfo.size());
		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();
		List<Studyrecordinfo> end = new ArrayList<Studyrecordinfo>();
		if(usercourseinfo.size()>0){
			dm = getRecord(usercourseinfo,userId);
			for(Studyrecordinfo ls:dm){
				if(ls.getIsEnd()==1){
					end.add(ls);
				}
			}
		}else{
			end = null;
		}
		return end;
	}

	@Override
	public List<Studyrecordinfo> getlastCourseByAll(String courseName,
			String courseType, int userId) {
		List<Usercourseinfo> usercourseinfo = usercourseinfoDAO.findUsercourseinfoByAll(userId, courseName,courseType);
		//	System.out.println(usercourseinfo.size());
		List<Studyrecordinfo> dm = new ArrayList<Studyrecordinfo>();
		List<Studyrecordinfo> end = new ArrayList<Studyrecordinfo>();
		if(usercourseinfo.size()>0){
			dm = getRecord(usercourseinfo,userId);
			for(Studyrecordinfo ls:dm){
				if(ls.getIsEnd()==1){
					end.add(ls);
				}
			}
		}else{
			end = null;
		}
		return end;
	}

	@Override
	public List<Usercourseinfo> getCourseStudy(int userId, int courseId) {

		return this.usercourseinfoDAO.getCourseStudy(userId, courseId);
	}

	@Override
	public List<Usercourseinfo> getByUserCourseScoId(int userId, int courseId,
			int scoId) {
		return this.usercourseinfoDAO.findByUserCourseScoId(userId, courseId, scoId);
	}

	@Override
	public List<Usercourseinfo> findByScoId(Integer scoId) {
		// TODO Auto-generated method stub
		return this.usercourseinfoDAO.findByScoId(scoId);
	}
}
