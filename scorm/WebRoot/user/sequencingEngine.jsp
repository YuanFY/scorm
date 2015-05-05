<%@page import="com.scorm.action.ActionSupport"%>
<%@page import="com.scorm.vo.Userinfo"%>
<%@page import="com.scorm.vo.UsercourseinfoId"%>
<%@page import="com.scorm.vo.Scoinfo"%>
<%@page import="com.scorm.vo.Usercourseinfo"%>
<%@page import="com.scorm.service.ScoinfoService"%>
<%@page import="com.scorm.service.UsercourseinfoService"%>
<%@page import="com.scorm.vo.Courseinfo"%>
<%@page import="com.scorm.service.CourseinfoService"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.scorm.utils.UserCommon"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@page import = "java.sql.*, java.util.*, org.adl.util.*" %>

<%
/*******************************************************************************
**
** Filename:  sequencingEngine.jsp
**
** File Description:   This file determines which item should be launched in
**                     the current course.  It responds to the following events
**                     Next - Launch the next sco or asset
**                     Previous - Launch the previous sco or asset
**                     Menu - Launch the selected item
**
** Author: ADL Technical Team
**
** Contract Number:
** Company Name: CTC
**
** Module/Package Name:
** Module/Package Description:
**
** Design Issues: This is a proprietary solution for a sequencing engine.  
**                This version will most likely be replaced when the SCORM
**                adopts the current draft sequencing specification.
**
** Implementation Issues:
** Known Problems:
** Side Effects:
**
** References: ADL SCORM
**
/*******************************************************************************
**
** Concurrent Technologies Corporation (CTC) grants you ("Licensee") a non-
** exclusive, royalty free, license to use, modify and redistribute this
** software in source and binary code form, provided that i) this copyright
** notice and license appear on all copies of the software; and ii) Licensee
** does not utilize the software in a manner which is disparaging to CTC.
**
** This software is provided "AS IS," without a warranty of any kind.  ALL
** EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
** IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR NON-
** INFRINGEMENT, ARE HEREBY EXCLUDED.  CTC AND ITS LICENSORS SHALL NOT BE LIABLE
** FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
** DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES.  IN NO EVENT WILL CTC  OR ITS
** LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
** INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
** CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
** OR INABILITY TO USE SOFTWARE, EVEN IF CTC HAS BEEN ADVISED OF THE POSSIBILITY
** OF SUCH DAMAGES.
**
*******************************************************************************/

//  Booleans for a completed course and request type
boolean courseComplete = true;
boolean wasAMenuRequest = false;
boolean wasANextRequest = false;
boolean wasAPrevRequest = false;
boolean wasFirstSession = false;
boolean empty_block = false;

//  The type of controls shown
String control = new String();
//  The next item that will be launched
String nextItemToLaunch = new String();
//  The type of button request if its a button request
String buttonType = new String();
//  whether the launched unit is a sco or an asset
String type = new String();
// is the item a block with no content
String item_type = new String();
//is the identifier column 
String identifier = new String();

int cId = Integer.parseInt(request.getParameter("cId"));

// The courseID is passed as a parameter on initial launch of a course
String courseID = (String)request.getParameter( "courseID" );
//  Get the requested sco if its a menu request
String requestedSCO = (String)request.getParameter( "scoID" );
//  Get the button that was pushed if its a button request
buttonType = (String)request.getParameter( "button" );



// Set boolean for the type of navigation request
if ( (! (requestedSCO == null)) && (! requestedSCO.equals("") ))
{
   wasAMenuRequest = true;
}
else if ( (! (buttonType == null) ) && ( buttonType.equals("next") ) )
{
   wasANextRequest = true;
}
else if ( (! (buttonType == null) ) && ( buttonType.equals("prev") ) )
{
   wasAPrevRequest = true;
}
else
{
   // First launch of the course in this session.
   wasFirstSession = true;
}
//  If the course has not been launched
if ( courseID != null )
{
   //  set the course ID
   session.setAttribute( "COURSEID", courseID );
}
else // Not the initial launch of course, use session data
{
   courseID = (String)session.getAttribute( "COURSEID" );
}

try
{
  
    
   
   //  Execute the course info database query
	//根据courseId查找courseinfo
	ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	CourseinfoService courseinfoService = (CourseinfoService)context.getBean("courseinfoService");
	UsercourseinfoService usercourseinfoService = (UsercourseinfoService)context.getBean("usercourseinfoService");
	ScoinfoService scoinfoService = (ScoinfoService)context.getBean("scoinfoService");
	ActionSupport actionSupport = (ActionSupport)context.getBean("userActionSupport");
	//List<Courseinfo> courseList = courseinfoService.findByCourseId(Integer.parseInt(courseID));
	Userinfo userinfo = actionSupport.getSessionUser();
	List<Scoinfo> scoList = scoinfoService.findByScoId(Integer.parseInt(courseID));
	
	List<Usercourseinfo> uclist1 = usercourseinfoService.returnUsercourseinfo(userinfo.getUserId(), Integer.parseInt(courseID), cId);
	if(uclist1==null||uclist1.size()==0){
		Userinfo u = new Userinfo();
		u.setUserId(userinfo.getUserId());
		Courseinfo c = new Courseinfo();
		c.setCourseId(cId);
		UsercourseinfoId ucId = new UsercourseinfoId(u, c , Integer.parseInt(courseID));
		
		Usercourseinfo uc = new Usercourseinfo(ucId,"",0,"",new Timestamp(System.currentTimeMillis()),0,"0", scoList.get(0).getLaunch(),"",0);
		
		usercourseinfoService.saveUsercourseinfo(uc);
	}
	
     //  Get the CONTROL column
     control = "choice";
     session.setAttribute( "control", control );
  
   
   //  Get the session exit flag to see if its a logout request
   String exitFlag = (String)session.getAttribute( "EXITFLAG" );
       
   if ( exitFlag != null && exitFlag.equals( "true" ) )
   {
      //  It is a logout, so redirect to the logout page
      session.removeAttribute( "EXITFLAG" );
      response.sendRedirect("logout.jsp");
   }
   else  // It is a navigation request
   {
	   
       //  Get the users record of the course items
      List<Usercourseinfo> uclist = usercourseinfoService.returnUsercourseinfo1(userinfo.getUserId(), Integer.parseInt(courseID));
      // Initialize variables that help with sequencing
      String scoID = new String();
      String lessonStatus = new String();
      String launch = new String();
      
      System.out.println("uclist="+uclist.toString());
     
      

         //  If its auto or first session
         if ( wasFirstSession || ( control.equals( "auto" )) )
         {
            //  Launch the first item that is not in a completed state
           // while ( userSCORS.next() )
        	   if(uclist!=null&&uclist.size()>0)
            {
               scoID = uclist.get(0).getId().getScoId()+"";
               launch = uclist.get(0).getLaunch();
               type = "sco";
               // Set nextItemToLaunch to the next incomplete sco or asset
               //if ( ! (lessonStatus.equalsIgnoreCase( "completed" ) ) &&
               //     ! (lessonStatus.equalsIgnoreCase( "passed" ) ) &&
               //     ! (lessonStatus.equalsIgnoreCase( "failed" ) ) )
               //{
                  nextItemToLaunch = launch;
                  courseComplete = false;
                  session.setAttribute( "SCOID", scoID );
                //  break;
               }
           // }
         }  //  Ends if it was the first time in for the session
        
         

      }  // Ends if it was a button request
      
     //  Ends if it was a navigation request

   //  If the course is complete redirect to the course
   //  complete page
   if ( courseComplete )
   {
      session.removeAttribute( "COURSEID" );
      response.sendRedirect("courseComplete.jsp"); 
   }
   else
   {
%>

<!-- ****************************************************************
**   Build the html 'please wait' page that sets the client side 
**   variables and refreshes to the appropriate course page
*******************************************************************-->  
<html>
   <head>
   <title>Sample Run-Time Environment - Sequencing Engine</title>
   <!-- **********************************************************
   **  This value is determined by the JSP database queries
   **  that are located above in this file
   **  Refresh the html page to the next item to launch  
   ***************************************************************-->
   <meta http-equiv="refresh" content="3; url=<%= nextItemToLaunch %>">
 
      <script language="JAVASCRIPT">
         function initLMSFrame()
         {
            // Set the type of control for the course in the LMS Frame 
            if ( window.opener == null )
            {
               window.top.frames[0].document.forms[0].control.value = "<%= control %>";
            }
            else //  Set up control type in the window opener (if its auto mode)
            {
               // The sequencingEngine.jsp file runs in the opened window if it is auto
               // mode so special cases exist
               window.opener.top.frames[0].document.forms[0].control.value = "<%= control %>";
            }
         }
      </script>
   </head>
         
   <body bgcolor="#FFFFFF" onload="initLMSFrame()">   
	
      <%  
         //  If control is not auto. work in this window. 
         if ( ! control.equals("auto") )
         {  %>
            <script language="javascript">
               // Hide the next and previous buttons if it is of type "choice".
               var ctrl = "<%= control %>";
               
               if (ctrl == "choice")
               { 
                  window.top.frames[0].document.forms[0].next.style.visibility = "hidden";
                  window.top.frames[0].document.forms[0].previous.style.visibility = "hidden";
                  window.top.frames[0].document.forms[0].quit.style.visibility = "visible";
               }
               else
               {
                  // Make the buttons visible
                  window.top.frames[0].document.forms[0].next.style.visibility = "visible";
                  window.top.frames[0].document.forms[0].previous.style.visibility = "visible";
                  window.top.frames[0].document.forms[0].quit.style.visibility = "visible";
               }
            </script>
 
          
            <p><font size="4">
               Please Wait....
            </font></p>
           
         </body>
      </html> 
   <% } // Ends if its not auto sequencing... then configure controls  
   %>  
      
<%
   }  // Ends else display the please wait page

}
catch ( Exception e )
{ 
	e.printStackTrace();
   out.println("!! Exception Occurred: " + e + " !!");
} 
%>