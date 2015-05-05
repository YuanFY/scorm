<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import = "java.sql.*, java.util.*" %>
<html>
<head>
<title>Menu</title>

<script type="text/javascript" src="mtmcode.js">
</script>

<script type="text/javascript">

<%
 Vector title_vector = new Vector();
 Vector id_vector = new Vector();
 Vector level_vector = new Vector();
 Vector parent_vector = new Vector();
 Vector item_number_vector = new Vector();
 String userID = "";
 String courseID = "";
 String control = "";

 int length;
 int current_int_level;
 int current_index;
 int z;
 String previous_level = new String();
 int parent_index;
 String course_title = new String();
 String menu_name = new String();
 int new_level;
 
try
{
   // Get the course and item info from the database
   Connection conn; 
   PreparedStatement stmtSelectUserSCO;
   PreparedStatement stmtSelectCourse;
   /**
   	课件
   */
   String sqlSelectUserSCO
    = "SELECT * FROM ItemInfo WHERE CourseID = ? ORDER BY Sequence";
           
   String sqlSelectCourse
    = "SELECT * FROM CourseInfo WHERE CourseID = ?";
                     
   String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
   String connectionURL = "jdbc:odbc:SampleRTE";

   Class.forName(driverName).newInstance();
   conn = DriverManager.getConnection(connectionURL);
         
   stmtSelectUserSCO= conn.prepareStatement( sqlSelectUserSCO );
   stmtSelectCourse= conn.prepareStatement( sqlSelectCourse );
   
   userID = (String)session.getAttribute( "USERID" );
   
   courseID = (String)session.getAttribute( "COURSEID" );
    
   control = (String)session.getAttribute( "control" );
    
   if (courseID != "") 
   {//  Execute the course info database query
      ResultSet courseInfo = null;
      synchronized( stmtSelectCourse )
      {
         stmtSelectCourse.setString( 1, courseID );
         courseInfo = stmtSelectCourse.executeQuery();
      }
   
    // Move into the first record in the record set
      while ( courseInfo.next() )
      {
      //  Get the CONTROL column
         control = courseInfo.getString("Control");
      //get course name
         course_title = courseInfo.getString("CourseTitle");
      }
      }
      userID = (String)session.getAttribute( "USERID" );
  
      ResultSet itemInfo = null;
      if ((courseID != "")  && ((control != null) && ((control.equals( "mixed")) || (control.equals( "choice")))))
      {
         synchronized( stmtSelectUserSCO )
         {
            stmtSelectUserSCO.setString( 1, courseID );
            itemInfo = stmtSelectUserSCO.executeQuery();
         }  
         int i = 0;
         while(itemInfo.next())
         {
            // add the info to the vectors
            title_vector.addElement(itemInfo.getString("Title"));
            id_vector.addElement(itemInfo.getString("Identifier"));
            level_vector.addElement(itemInfo.getString("TheLevel"));
         } //end while
      }  // end if
    
} // end try
  catch(Exception e)
{
   e.printStackTrace();   
}
  %>  
    
    
<% // begin menu construction
   if ((courseID != "")  && (courseID != null) && ((control != null) && ((control.equals( "mixed")) || (control.equals( "choice")))))
   {  
      int i = 0;
    %>var MTMenuText = "<%=course_title%>";
      <% String previous_parent = "menu";
      previous_level = level_vector.elementAt(i).toString();%>
      // first item is menu root
      menu.MTMAddItem(new MTMenuItem("<%=title_vector.elementAt(i).toString()%>", "javascript:launchItem('<%=id_vector.elementAt(i).toString()%>')", "code"));
      <% parent_index = 0;
      parent_vector.addElement("menu");
      length = title_vector.size();
      item_number_vector.addElement("0");
      current_index = 0; 
      i++;
      while ( i < length )
      {   // if nesting level of current item is same as that of previous item
            if (level_vector.elementAt(i).toString().equals(previous_level))
            { %>
                <%=parent_vector.elementAt(parent_index).toString()%>.MTMAddItem(new MTMenuItem("<%=title_vector.elementAt(i).toString()%>", "javascript:launchItem('<%=id_vector.elementAt(i).toString()%>')", "code"));
               <% //increment item_number_vector at current_index so know which item are at
               Integer inc = new Integer(item_number_vector.elementAt(current_index).toString());
               new_level = inc.intValue();
               new_level++;
               item_number_vector.setElementAt(inc.toString(new_level), current_index);
               i++;
            }// end if
            //if level is greater, get new menu name, add name to 
            //parent_vector and use as current menu name
            else if ( (previous_level.compareTo(level_vector.elementAt(i).toString()))<0)
            {  
               menu_name = "sub"+parent_index;
               Integer tempInt = new Integer(item_number_vector.elementAt(current_index).toString()); 
               int item_number = tempInt.intValue();
               %>var <%=menu_name%> = new MTMenu();
               <%= parent_vector.elementAt(parent_index).toString()%>.items[<%=item_number%>].MTMakeSubmenu(<%=menu_name%>);
               <% parent_vector.addElement(menu_name);
               parent_index++;
               item_number_vector.addElement("0");
               current_index++;%>                 
               <%=menu_name%>.MTMAddItem(new MTMenuItem("<%=title_vector.elementAt(i).toString()%>", "javascript:launchItem('<%=id_vector.elementAt(i).toString()%>')", "code")); 
               <%
               previous_level = level_vector.elementAt(i++).toString();
            } //end else if
            else
              //if level is less
            { 
               Integer int1 = new Integer(previous_level);
               Integer int2 = new Integer(level_vector.elementAt(i).toString());
               current_int_level = int1.intValue() - int2.intValue(); 
               for (z = 0; z<current_int_level; z++)
               {  
                  parent_vector.removeElementAt(parent_index--);
                  item_number_vector.removeElementAt(current_index--);
               }// end for %>
                                  <%=parent_vector.elementAt(parent_index).toString()%>.MTMAddItem(new MTMenuItem("<%=title_vector.elementAt(i).toString()%>", "javascript:launchItem('<%=id_vector.elementAt(i).toString()%>')", "code")); 
               <% //increment item_number_vector at current_index so know which item are at
               Integer inc = new Integer(item_number_vector.elementAt(current_index).toString());
               new_level = inc.intValue();
               new_level++;
               item_number_vector.setElementAt(inc.toString(new_level), current_index);
               previous_level = level_vector.elementAt(i++).toString();
            }// end else
    } //end while
 } // end menu creation%>
 
      </script>
</head>

<body onload="MTMStartMenu()" bgcolor="#FFFFFF" text="#black" link="yellow" vlink="lime" alink="red"  >
</body>

</html>
