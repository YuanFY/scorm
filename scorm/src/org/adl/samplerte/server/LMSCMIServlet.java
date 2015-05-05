/*******************************************************************************
** 
** Filename:  LMSCMIServlet.java
**
** File Description:     
**
** This class defines the LMSCMIServlet that is used to handle the server side 
** data model communication of the Sample RTE.
**
** This servlet handles persistence of the AICC Data Model elements.
** Persistence is being handled via flat files and the 
** built in Java serialization mechanism rather than via a database.
**
** This servlet works in conjunction with the LMS APIAdapter Applet in the
** org.adl.lms.client package that is part of this sample.
**
** Author: ADL Technical Team
**
** Contract Number:
** Company Name: CTC
**
** Module/Package Name:
** Module/Package Description:
**
** Design Issues:
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

package org.adl.samplerte.server;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import org.adl.datamodels.*;
import org.adl.datamodels.cmi.*;
import org.adl.util.debug.DebugIndicator;

public class LMSCMIServlet extends HttpServlet
{
    // These strings are being used to hold the location of the serialized core
    // data.
    private String LMSSCODataFile = 
    "\\LMSSampleDB\\User01\\Course01\\Lesson01\\scodata";
    private String LMSUser01   = "\\LMSSampleDB\\User01\\Course01\\Lesson01";
    private String scoFile;
    private String userID;
    private String courseID;
    private String scoID;
    private boolean logoutFlag;

    private SCODataManager scoData;

    //This controls display of log messages to the java console
    private static boolean _Debug = DebugIndicator.ON;


    /****************************************************************************
    **
    ** Method:  doPost
    ** Input:   HttpServletRequest request, HttpServletResponse response
    ** Output:  none
    **
    ** Description:
    ** This method handles post messages to the servlet.  This servlet will respond
    ** to the following commands:
    **   cmigetcat
    **   cmiputcat
    **
    ** A real LMS would probably want to handle each request as a seperate servlet,
    ** but for the purpose of demonstrating a sample LMS it was easier to have a 
    ** single servlet.
    **
    ****************************************************************************/
    @Override
	public void doPost( HttpServletRequest  request,
                        HttpServletResponse response )
    throws ServletException, IOException
    {
        try
        {
            if ( _Debug )
            {
                System.out.println("requested session: " + request.getRequestedSessionId());
            }

            if ( request.isRequestedSessionIdFromCookie() == true )
            {
                if ( _Debug )
                {
                    System.out.println("requested session id from cookie");
            
                }
            }

            if ( _Debug )
            {
                System.out.println("query string: " + request.getQueryString());
                System.out.println("header string: " + request.getContextPath());
                System.out.println("cookie header: " + request.getHeader("Cookie"));
            }

            
            
            if ( _Debug )
            {
				for ( Enumeration e = request.getHeaderNames(); e.hasMoreElements() ; )
                    System.out.println(e.nextElement());
            }
           

            if ( _Debug )
            {
                System.out.println("POST received by LMSCMIServlet");
            }
            HttpSession session = request.getSession( false );
            if ( session == null )
            {
                if ( _Debug )
                {
                    System.out.println("this is bad - no session in cmi servlet");
                }
            }
            else 
            {
                if ( _Debug )
                {
                    System.out.println("session id is: " + session.getId());
                }
            }
            
            if ( _Debug )
            {
                System.out.println("about to check attributes");
            }

            if ( _Debug )
            {
				for ( Enumeration e = request.getHeaderNames(); e.hasMoreElements() ; )
                    System.out.println(e.nextElement());
            }

            scoID = (String)session.getAttribute( "SCOID" );
            courseID = (String)session.getAttribute( "COURSEID" );
            userID = (String)session.getAttribute( "USERID" );

            if ( _Debug )
            {
                System.out.println("session scoID: " + scoID);
            }
            scoFile = "\\SampleRTEFiles\\" + userID + "\\" + courseID + "\\" + scoID;

            // Open the input stream and pull off the incomming command
            ObjectInputStream in = new ObjectInputStream( request.getInputStream() );
            if ( _Debug )
            {
                System.out.println("Created REQUEST object INPUT stream successfully");
            }
            ObjectOutputStream out = new ObjectOutputStream( response.getOutputStream() );
            if ( _Debug )
            {
                System.out.println("Created RESPONSE object OUTPUT stream successfully");
            }
            String command = (String)in.readObject();

            if ( _Debug )
            {
                System.out.println("Command to LMSCMIServlet is: " + command);
            }

            // Process the incomming command accordingly
            if ( command.equalsIgnoreCase( "cmiputcat" ) )
            {
                logoutFlag = false;
                SCODataManager inSCOData = (SCODataManager)in.readObject();
                
                if ( _Debug )
                {
                    System.out.println("LMSCMIServlet read in the SCODataManager object");
                }

                HandleData( inSCOData );

                if ( logoutFlag == true )
                {
                    session.setAttribute( "EXITFLAG", "true" );
                }
                else
                {
                    session.removeAttribute( "EXITFLAG" );
                }
            }
            else if ( command.equalsIgnoreCase( "cmigetcat" ) )
            {
                FileInputStream fi        = new FileInputStream( scoFile );
                if ( _Debug )
                {
                    System.out.println("Created LMSSCODataFile FILE input stream successfully");
                }
                ObjectInputStream file_in = new ObjectInputStream( fi );
                if ( _Debug )
                {
                    System.out.println("Created OBJECT input stream successfully");
                }
                scoData = (SCODataManager)file_in.readObject();
                scoData.getCore().setSessionTime("00:00:00.0");

                file_in.close();
                out.writeObject( scoData );
                if ( _Debug )
                {
                    System.out.print("LMSCMIServlet processed get for SCO Data\n");
                }
            }
            else // invalid command sent, real LMS would handle this more gracefully
            {
                String err_msg = "invalid command";
                out.writeObject( err_msg );
            }

            // Close the input and output streams
            in.close();
            out.close();
        }
        catch ( Exception e )
        {
			if ( _Debug )
            {
            e.printStackTrace();
			}
        }
    } // end doPost



    /****************************************************************************
    **
    ** Method:  HandleData
    ** Input:   SCODataManager scoData
    ** Output:  none
    **
    ** Description:
    ** This method handles processing of the core data being sent from the lesson
    ** to the LMS.  The data needs to be processed and made persistant.
    ** For this example LMS only the core data has been implemented.
    **
    ****************************************************************************/
    private void HandleData( SCODataManager inSCOData )
    {
        try
        {
            String lessonStatus = new String();
            String lessonExit = new String();
            String lessonEntry = new String();

            // Handle some sequencing issues.  If the exit of the current
            // SCO is set to "suspend", the user has hit the "Go Back" button
            // on the current SCO.  When this happens, the mode of the SCO
            // that would be served up prior to the current SCO is set to
            // "review".  This is done so that the LMSLessonServlet will know
            // to display the correct SCO.  Also, the current SCO entry will be
            // set to "resume" because at some point the student will resume
            // with the current SCO

            // Need to get the core object off of the SCO Data Manager
            // for the current SCO
            CMICore lmsCore = inSCOData.getCore();
      
            // Need to add the SCOs Session Time into the running total time
            //CMITime totalTime =
            //new CMITime( lmsCore.getTotalTime().getValue() );

            //System.out.println("\tTotal time: " + totalTime.toString());
            //CMITime sessionTime =
            //new CMITime( lmsCore.getSessionTime().getValue() );
            //System.out.println("\tSession time: " + sessionTime.toString());

            //totalTime.add(sessionTime);
            //lmsCore.setTotalTime(totalTime.toString());

            //System.out.println("\t\tTotal time: " + totalTime.toString());

            //if ( lmsCore.getExit().getValue().equalsIgnoreCase( "suspend" ) )
            //{
            //    lmsCore.setEntry( "resume" );
                //if ( currentSCO != 0 )
                //{
                //    // Set the previous SCOs Lesson Mode to review
                //    CMICore previousCore = lmsData[currentSCO - 1].getCore();
                //    previousCore.setLessonMode( "review" );
                //    lmsData[currentSCO - 1].setCore(previousCore);
                //}
            //}
            if ( lmsCore.getExit().getValue().equalsIgnoreCase( "logout" ) )
            {
                // Nothing special needs to be done when a logout occurs
                // Sequencing will go to the first SCO with a "review" mode or
                // to the first SCO with an incomplete status.
                //lmsCore.setExit( "" );
                logoutFlag = true;
            }
            //else
            //{
                // User has hit the "continue" button on the current SCO.  The
                // mode will be set to "normal" in case the mode was "review".
            //    lmsCore.setLessonMode( "normal" );
            //}

            lessonStatus = lmsCore.getLessonStatus().getValue();
            lessonExit = lmsCore.getExit().getValue();
            lessonEntry = lmsCore.getEntry().getValue();

            // Write out the updated data to disk
            inSCOData.setCore(lmsCore);

            if ( _Debug )
            {
                System.out.println("Saving Data to the File ...");
                System.out.println("Prior to Save");
                System.out.println("The SCO Data Manager for the current SCO contains " +
                                   "the following:");
                inSCOData.getCore().showData();
            }

            // Write out the updated data to disk
            FileOutputStream fo         = new FileOutputStream( scoFile );
            ObjectOutputStream out_file = new ObjectOutputStream( fo );
            out_file.writeObject( inSCOData );
            out_file.close();
            //.print("LMSCMIServlet updated LMS SCO data info.\n");

            // Now we need to update UserSCOInfo in DB
            Connection conn; 
            PreparedStatement stmtUpdateUserSCO;
            PreparedStatement stmtSelectUserSCO;

            String sqlUpdateUserSCO
             = "UPDATE UserSCOInfo SET LessonStatus = ?, Exit = ?, Entry = ? " +
               "WHERE UserID = ? AND CourseID = ? AND SCOID = ?";

            String sqlSelectUserSCO
             = "SELECT * FROM UserSCOInfo WHERE UserID = ? AND CourseID = ? AND SCOID = ?";

            String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
            String connectionURL = "jdbc:odbc:SampleRTE";

            Class.forName(driverName).newInstance();
            conn = DriverManager.getConnection(connectionURL);

            stmtUpdateUserSCO = conn.prepareStatement( sqlUpdateUserSCO );
            stmtSelectUserSCO = conn.prepareStatement( sqlSelectUserSCO );

            if ( _Debug )
            {
                System.out.println("about to update userSCOInfo");
                System.out.println("userID: " + userID);
                System.out.println("courseID: " + courseID);
            }
            synchronized( stmtUpdateUserSCO )
            {
                stmtUpdateUserSCO.setString( 1, lessonStatus );
                stmtUpdateUserSCO.setString( 2, lessonExit );
                stmtUpdateUserSCO.setString( 3, lessonEntry );
                stmtUpdateUserSCO.setString( 4, userID );
                stmtUpdateUserSCO.setString( 5, courseID );
                stmtUpdateUserSCO.setString( 6, scoID );
                stmtUpdateUserSCO.executeUpdate();
            }

            ResultSet userSCORS = null;
            synchronized( stmtSelectUserSCO )
            {
                stmtSelectUserSCO.setString( 1, userID );
                stmtSelectUserSCO.setString( 2, courseID );
                stmtSelectUserSCO.setString( 3, scoID );
                userSCORS = stmtSelectUserSCO.executeQuery();
            }
            userSCORS.next();
            String newStatus = userSCORS.getString( "LessonStatus" );
            //System.out.println("the status in sequencingEngine after set is: " + newStatus);

            //System.out.println("about to close stmt");
            //stmtUpdateUserSCO.close();
            //System.out.println("about to close connection");
            //conn.close();
        }
        catch ( Exception e )
        {
           if ( _Debug )
            {
               e.printStackTrace(); 
            }
        }
    } // end HandleData

}
