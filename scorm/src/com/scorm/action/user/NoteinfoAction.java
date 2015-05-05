package com.scorm.action.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.scorm.action.ActionSupport;
import com.scorm.service.NoteinfoService;
import com.scorm.vo.Noteinfo;
import com.scorm.vo.bean.NoteBean;
/**
 * 对课堂上的笔记进行操作
 * @author YuanFY
 * @Description:TODO
 * @version V1.0
 */
@SuppressWarnings("serial")
public class NoteinfoAction extends ActionSupport{
	private Integer noteId;
	private Integer userId;
	private Integer courseId;
	private String noteTitle;
	private String noteContent;
	private Timestamp noteTime;
	
	private String result;

	@Resource(name="noteinfoService")
	private NoteinfoService noteinfoService;
	
	/**
	 * 保存笔记信息
	 * @return
	 */
	public String save(){
		Noteinfo note = new Noteinfo();
		note.setCourseId(courseId);
		note.setNoteContent(noteContent);
		note.setNoteTime(new Timestamp(System.currentTimeMillis()));
		note.setUserId(getSessionUser().getUserId());
		note.setNoteTitle("0");
		noteinfoService.save(note);
		try {
			List<Noteinfo> info = noteinfoService.findSql("from Noteinfo order by noteId desc");
			List<NoteBean> beanList = new ArrayList<NoteBean>();
			for(int i=0; i<info.size(); i++){
				NoteBean bean = new NoteBean();
				bean.setNoteContent(info.get(i).getNoteContent());
				bean.setNoteTime(info.get(i).getNoteTime());
				bean.setUserName(getSessionUser().getUserName());
				bean.setNoteTitle(info.get(i).getNoteTitle());
				bean.setNoteId(info.get(0).getNoteId());
				beanList.add(bean);
			}
			this.setResult(json(beanList));
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	/**
	 * 更新笔记
	 * @return
	 */
	public String update(){
		System.out.println("-----noteId="+noteId);
		if(noteId!=0){
			Noteinfo noteinfo = noteinfoService.findSql("from Noteinfo where noteId="+noteId).get(0);
			noteinfoService.delete(noteinfo);
			noteinfo.setNoteContent(noteContent);
			noteinfo.setNoteTime(new Timestamp(System.currentTimeMillis()));
			noteinfoService.save(noteinfo);
		}
		try {
			List<Noteinfo> info = noteinfoService.findSql("from Noteinfo order by noteId desc");
			List<NoteBean> beanList = new ArrayList<NoteBean>();
			for(int i=0; i<info.size(); i++){
				NoteBean bean = new NoteBean();
				bean.setNoteContent(info.get(0).getNoteContent());
				bean.setNoteTime(info.get(0).getNoteTime());
				bean.setUserName(getSessionUser().getUserName());
				beanList.add(bean);
			}
			this.setResult(json(beanList));
			
			 List<Noteinfo> shareList = noteinfoService.findSql("from Noteinfo where noteTitle = '1' order by noteId desc");
             ServletActionContext.getRequest().setAttribute("shareList",shareList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	/**
	 * 标记分享
	 * @return
	 */
	public String  updateShare(){
		System.out.println("-----noteId="+noteId);
		if(noteId!=0){
			Noteinfo noteinfo = noteinfoService.findSql("from Noteinfo where noteId="+noteId).get(0);
			if(noteinfo.getNoteTitle()==null || "0".equals(noteinfo.getNoteTitle())){
				noteinfo.setNoteTitle("1");
			}else{
				noteinfo.setNoteTitle("0");
			}
			noteinfoService.update(noteinfo);
		}
		
		List<Noteinfo> shareList = noteinfoService.findSql("from Noteinfo where noteTitle = '1' order by noteId desc");
        ServletActionContext.getRequest().setAttribute("shareList",shareList);
        return "success";
	}
	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public Timestamp getNoteTime() {
		return noteTime;
	}

	public void setNoteTime(Timestamp noteTime) {
		this.noteTime = noteTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
	
}
