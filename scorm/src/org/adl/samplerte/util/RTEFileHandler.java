/*******************************************************************************
** 
** Filename:  RTEFileHandler.java
**
** File Description: 
**
** Author:  ADLI Project
**
** Company Name: CTC
**
** Module/Package Name: org.adl.tsuite.packaging
** Module/Package Description:
**
** Design Issues:
**
** Implementation Issues:
** Known Problems:
** Side Effects:
**
** References:  SCORM
**
*******************************************************************************
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
*******************************************************************************
**
** Date Changed   Author of Change  Reason for Changes
** ------------   ----------------  -------------------------------------------
**
*******************************************************************************/
package org.adl.samplerte.util;

import java.io.*;
import java.sql.*;

//ADL imports
import org.adl.util.debug.DebugIndicator;
import org.adl.datamodels.*;
import org.adl.datamodels.cmi.*;

public class RTEFileHandler
{
    private String userID;
    private String courseID;
    private String sampleRTERoot;
    private String scoID;
    private String location;
    private String masteryScore;
    private String parameterString;
    private String lessonStatus;
    private String prerequisites;
    private String exit;
    private String entry;
    private String dataFromLMS;
    private String maxTimeAllowed;
    private String timeLimitAction;
    private int sequence;

    private static boolean _Debug = DebugIndicator.ON;


    /**************************************************************************
    **
    ** Method:  CSFParser()
    ** Input:   
    **         
    ** Output: 
    **
    ** Description:  Constructor.
    **
    ***************************************************************************/
    public RTEFileHandler()
    {
        userID = new String();
        courseID = new String();
        scoID = new String();
        location = new String();
        masteryScore = new String();
        parameterString = new String();
        lessonStatus = new String();
        prerequisites = new String();
        exit = new String();
        entry = new String();
        dataFromLMS = new String();
        maxTimeAllowed = new String();
        timeLimitAction = new String();
        sequence = 0;
        sampleRTERoot = "\\SampleRTEFiles";
    }

    public void setUserID( String idIn )
    {
        userID = idIn;
    }

    public void setCourseID( String idIn )
    {
        courseID = idIn;
    }

    public void printRTEFileHandler()
    {
       if ( _Debug )
       {
          System.out.println( "userID:          " + userID );
          System.out.println( "courseID:        " + courseID );
          System.out.println( "SampleRTERoot:   " + sampleRTERoot );
       }
    }

    public void initializeCourseFiles()
    {
        try
        {
            if ( _Debug )
            {
               System.out.println("****GOT IN INITIALIZECOURSEFILES****");
            }
            String userDir = sampleRTERoot + "\\" + userID + "\\" + courseID;

            File theRTESCODataDir = new File( userDir );

            // The course directory should not exist yet
            if ( !theRTESCODataDir.isDirectory() )
            {
                theRTESCODataDir.mkdirs();
            }
            else
            {
               if ( _Debug )
               {
                  System.out.println("In RTEFileHandler user directory already exists for new course???");
               }
            }

            // Now determine sco items for the course and create a data file for each
            Connection conn; 
            PreparedStatement rtestmtSelectItem;
            PreparedStatement stmtInsertUserSCO;

            String sqlSelectItem
            = "SELECT * FROM ItemInfo WHERE CourseID = ?";

            String sqlInsertUserSCO
             = "INSERT INTO UserSCOInfo (UserID, CourseID, SCOID, Launch, ParameterString, " +
               "LessonStatus, Prerequisites, Exit, Entry, MasteryScore, Sequence, Type) " +
               "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

            //String sqlInsertUserSCO
            // = "INSERT INTO UserSCOInfo VALUES(?,?,?,?,?,?,?,?,?,?,?)";

            String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
            String connectionURL = "jdbc:odbc:SampleRTE";

            Class.forName(driverName).newInstance();
            conn = DriverManager.getConnection(connectionURL);

            rtestmtSelectItem = conn.prepareStatement( sqlSelectItem );
            stmtInsertUserSCO = conn.prepareStatement( sqlInsertUserSCO );

            if ( _Debug )
            {
               System.out.println("about to call item in RTEFile");
               System.out.println("userID: " + userID);
               System.out.println("courseID: " + courseID);
            }
            ResultSet itemRS = null;
            synchronized( rtestmtSelectItem )
            {
                rtestmtSelectItem.setString( 1, courseID );
                itemRS = rtestmtSelectItem.executeQuery();
            }

            if ( _Debug )
            {
               System.out.println("call to itemRS is complete");
            }
            while ( itemRS.next() )
            {
               String type = itemRS.getString( "Type" );
               if ( type.equals( "sco" ) || type.equals( "asset" ) )
               {
                  scoID = itemRS.getString( "Identifier" );
                  location = itemRS.getString( "Launch" );
                  masteryScore = itemRS.getString( "MasteryScore" );
                  prerequisites = itemRS.getString( "Prerequisites" );
                  parameterString = itemRS.getString( "ParameterString" );
                  dataFromLMS = itemRS.getString( "DataFromLMS" );
                  maxTimeAllowed = itemRS.getString( "MaxTimeAllowed" );
                  timeLimitAction = itemRS.getString( "TimeLimitAction" );
                  sequence = itemRS.getInt( "Sequence" );
                  
                  String RTESCODataFile = userDir + "\\" + scoID;

                  File theRTESCODataFile = new File( RTESCODataFile );

                  SCODataManager rteSCOData = new SCODataManager();
                  rteSCOData = initSCOData();

                  // Write out the data to disk using serialization
                  FileOutputStream fo    = new FileOutputStream( RTESCODataFile );
                  ObjectOutputStream out = new ObjectOutputStream( fo );
                  out.writeObject( rteSCOData );
                  out.close();
                  if ( _Debug )
                  {
                     System.out.println("RTEFileHandler created SCO file for: " + scoID);
                  }
                  // Insert entry into UserSCOInfo DB table
                  synchronized( stmtInsertUserSCO )
                  {
                     stmtInsertUserSCO.setString( 1, userID );
                     stmtInsertUserSCO.setString( 2, courseID );
                     stmtInsertUserSCO.setString( 3, scoID );
                     stmtInsertUserSCO.setString( 4, location );
                     stmtInsertUserSCO.setString( 5, parameterString );
                     stmtInsertUserSCO.setString( 6, "not attempted" );
                     stmtInsertUserSCO.setString( 7, prerequisites );
                     stmtInsertUserSCO.setString( 8, "" );
                     stmtInsertUserSCO.setString( 9, "ab-initio" );
                     stmtInsertUserSCO.setString( 10, masteryScore );
                     stmtInsertUserSCO.setInt( 11, sequence );
                     stmtInsertUserSCO.setString( 12, type );
                     stmtInsertUserSCO.executeUpdate();
                  }
               }
               else
               {
                  if ( _Debug )
                  {
                     System.out.println("NOT A SCO OR ASSET");
                  }
               }
            }
        }
        catch ( Exception e )
        {
           if ( _Debug )
           {
              e.printStackTrace();
           }
        }
    }

    public void deleteCourseFiles()
    {
        try
        {
            String userDir = sampleRTERoot + "\\" + userID + "\\" + courseID;

            File theRTESCODataDir = new File( userDir );

            File scoFiles[] = theRTESCODataDir.listFiles();

            for( int i = 0; i < scoFiles.length; i++ )
            {
                scoFiles[i].delete();
            }

            theRTESCODataDir.delete();
        }
        catch ( Exception e )
        {
           if ( _Debug )
           {
              e.printStackTrace();
           }
        }
    }



    /****************************************************************************
    **
    ** Method:  initSCOData
    ** Input:   none
    ** Output:  none
    **
    ** Description:
    ** This method is used to initialize the data model with temporary data.
    **
    ****************************************************************************/
    private SCODataManager initSCOData()
    {
        SCODataManager tmpSCOData = new SCODataManager();

        try
        {
            // Need to get some user data from database
            Connection conn; 
            PreparedStatement stmtSelectUser;

            String sqlSelectUser
            = "SELECT * FROM UserInfo WHERE UserID = ?";

            String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
            String connectionURL = "jdbc:odbc:SampleRTE";

            Class.forName(driverName).newInstance();
            conn = DriverManager.getConnection(connectionURL);

            stmtSelectUser= conn.prepareStatement( sqlSelectUser );
            if ( _Debug )
            {
               System.out.println("getting user info");
            }

            ResultSet userRS = null;
            synchronized( stmtSelectUser )
            {
                stmtSelectUser.setString( 1, userID );
                userRS = stmtSelectUser.executeQuery();
            }
            String firstName = new String();
            String lastName = new String();

            if ( userRS.next() )
            {
				if ( _Debug )
				{
                System.out.println("in if to get names");
				}
                firstName = userRS.getString( "FirstName" );
                lastName = userRS.getString( "LastName" );
            }
                        //userRS.close();
            //stmtSelectUser.close();
            //conn.close();

            String studentName = lastName + ", " + firstName;
            if ( _Debug )
            {
               System.out.println("student name: " + studentName);
            }

            // Build Core
            CMIScore tmpScore   = new CMIScore();
            tmpScore.setRaw("0");
            tmpScore.setMax("0");
            tmpScore.setMin("0");

            CMICore tmpCore = new CMICore();
            tmpCore.setStudentId( userID );
            tmpCore.setStudentName( studentName );
            tmpCore.setLessonLocation( location );
            tmpCore.setCredit( "credit" );
            tmpCore.setLessonStatus( "not attempted" );
            tmpCore.setEntry( "ab-initio" );
            tmpCore.setTotalTime( "00:00:00.0" );
            tmpCore.setLessonMode( "normal" );
            tmpCore.setExit( "" );
            tmpCore.setScore( tmpScore );
            tmpSCOData.setCore( tmpCore );


            // Build Suspend Data
            CMISuspendData tmpSuspendData = new CMISuspendData();
            tmpSuspendData.setSuspendData("");
            tmpSCOData.setSuspendData(tmpSuspendData);

            // Build Launch Data
            CMILaunchData tmpLaunchData = new CMILaunchData();
            tmpLaunchData.setLaunchData( dataFromLMS );
            tmpSCOData.setLaunchData(tmpLaunchData);

            // Build Comments
            CMICommentsFromLms tmpComments = new CMICommentsFromLms("");
            tmpSCOData.setCommentsFromLMS(tmpComments);

            // Build Objective Data
            /*
            CMIObjectives tmpObjectives = new CMIObjectives();
            CMIObjectiveData tmpObjectiveData = new CMIObjectiveData();
            tmpObjectiveData.setId("O001");
            tmpObjectiveData.setScore(tmpScore);
            tmpObjectiveData.setStatus("not attempted");
            tmpObjectives.setObjectives(tmpObjectiveData,0);
            tmpSCOData.setObjectives(tmpObjectives);
            */

            // Build Student Data
            CMIStudentData tmpStudentData = new CMIStudentData();

            tmpStudentData.setMasteryScore( masteryScore );
            tmpStudentData.setMaxTimeAllowed( maxTimeAllowed );
            tmpStudentData.setTimeLimitAction( timeLimitAction );
            tmpSCOData.setStudentData(tmpStudentData);

            // Build Student Preference
            CMIStudentPreference tmpStudentPreference = new CMIStudentPreference();
            tmpStudentPreference.setAudio("3");
            tmpStudentPreference.setLanguage("English");
            tmpStudentPreference.setSpeed("4");
            tmpStudentPreference.setText("10");
            tmpSCOData.setStudentPreference(tmpStudentPreference);
        }
        catch ( Exception e )
        {
           if ( _Debug )
           {
              e.printStackTrace();
           }
        }

        return tmpSCOData;
    }

}
