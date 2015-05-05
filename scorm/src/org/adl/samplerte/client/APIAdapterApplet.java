/*******************************************************************************
** 
** Filename:  APIAdapterApplet.java
**
** File Description:     
**
** This is a faceless Applet...  no UI 
** 
** This class implements the Sharable Contnet Object (SCO) to Learning 
** Management System (LMS) communication API referenced in the ADL Shareable
** Content Object Reference Model (SCORM) and defined by the Airline Industry
** CBT Committee (AICC).  It is intended to be an example only and as such
** several simplifications have been made.   It is not intended to be a complete
** LMS implementation.  In the interest of time, several shortcuts have been
** taken.  For example, robust error handling along with performance
** optimizatons are left to a later development iteration.
**
** This API is implemented assuming the LMS is a web-based client/server
** application.  This applet runs within the context of the LMS client
** assumed to be HTML and Javascript based running within IE4 or IE5 using
** the Sun Java Runtime Environment Standard Edition version 1.3 OR Netscape
** 4.x using Netscapes JVM.
**
** The LMS Server component is implemented as a Java Servlet
** and handles the persistence of the data model.  At this time, the LMS,
** for the purposes of this example handles only a single user and a single
** lesson.
**
** There are many ways using currently available web technologies that
** an LMS could be implemented to be compliant with the communication mechanisms
** described in the SCORM.  We have chosen this one.
**
** Since the intended usage of this API is via LiveConnect from ECMAScript
** (a.k.a. javascript), we return values from the public LMS functions
** to the caller as String objects.  The ECMAScript caller will see the
** return values as javascript String objects via the magic of LiveConnect.
**
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
**
** Known Problems: 
** 1. In several instances, the parameters to the API functions are
** checked for a value of "null" because the Java Plug-in converts a "" (empty
** string) to null.  This is only a workaround.  The expected parameters
** are "" where stated in SCORM and not "null".
**
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

package org.adl.samplerte.client;

import java.applet.Applet;
import java.net.*;
import java.util.*;
import netscape.javascript.JSObject;
import org.adl.datamodels.*;
import org.adl.datamodels.cmi.*;
import org.adl.util.debug.DebugIndicator;

public class APIAdapterApplet extends Applet 
{

   //This controls display of log messages to the java console
   private static boolean _Debug = DebugIndicator.ON;

   // Handles all LMS Error reporting
   private static LMSErrorManager lmsErrorManager;

   // The current SCOs Data Manager
   private static SCODataManager theSCOData;

   // Handles all Data Model (DM) Error reporting
   private DMErrorManager dmErrorManager;

   private static boolean isLMSInitialized;

   private static String cmiBooleanFalse;
   private static String cmiBooleanTrue;

   private URL servletURL;

   /***************************************************************************
   **
   ** Method:  init
   ** Input:   none
   ** Output:  none
   **
   ** Description:
   ** The applet init method
   **
   ***************************************************************************/
   @Override
   public void init()
   {
      // We assume at this point that the user has successfully logged in
      // to the LMS.

      if ( _Debug )
      {
         System.out.println("In API::init()(the applet Init method)");
      }

      cmiBooleanFalse = new String("false");
      cmiBooleanTrue = new String("true");


      lmsErrorManager = new LMSErrorManager();
      dmErrorManager = new DMErrorManager();

      APIAdapterApplet.isLMSInitialized = false;

      URL codebase = this.getCodeBase();
      String host = codebase.getHost();
      String protocol = codebase.getProtocol();
      int port = codebase.getPort();

      if ( _Debug )
      {
         System.out.println("codebase url is " + codebase.toString());
      }

      try
      {
         this.servletURL =
         new URL(protocol + "://" + host + ":" + port + "/adl/lmscmi");
         String session = getCookie("JSESSIONID");
         System.out.println("session is: " + session);

         if ( _Debug )
         {
            System.out.println("servlet url is "+
                               this.servletURL.toString());
         }
      }
      catch ( Exception e )
      {
         e.printStackTrace();
         stop();  // we shouldn't proceed if we catch an exception here...
      }
   }


   /*****************************************************************************
   **
   ** Method:  getAppletInfo
   ** Input:   none
   ** Output:  String containing the information about the API applet class
   **
   ** Description:
   ** The applet getAppletInfo method
   **
   *****************************************************************************/
   @Override
public String getAppletInfo()
   {
      return "Title: Sample RTE API Implementation \nAuthor: R. Ball, CTC \n" +
             "Example of one possible LMS API Implementation.";
   }

   /*****************************************************************************
   **
   ** Method:  getParameterInfo
   ** Input:   none
   ** Output:  String[][]
   **
   ** Description:
   ** The applet getParameterInfo method
   **
   *****************************************************************************/
   @Override
public String[][] getParameterInfo()
   {
      String[][] info = {
            {"None", "", "This applet requires no parameters."}};
      return info;
   }

   /*****************************************************************************
   **
   ** Method:  CheckInitialization
   ** Input:   none
   ** Output:  boolean
   **
   ** Description:
   ** determin
   **
   *****************************************************************************/
   private boolean CheckInitialization()
   {
      if ( isLMSInitialized != true )
      {
         APIAdapterApplet.lmsErrorManager.SetCurrentErrorCode("301");
      }

      return isLMSInitialized;
   }

   /***************************************************************************
   **
   ** Method:  LMSInitialize(String param)
   ** Input:   String param  - must be null string - reserved for future use
   ** Output:  CMIBoolean  "false" if fails, "true" if succeeds
   **
   ** Description:
   ** The applet init method
   **
   ***************************************************************************/
   public String LMSInitialize(String param)
   {
      // This function must be called by a SCO before any other
      // API calls are made.   It can not be called more than once
      // consecutively unless LMSFinish is called.

      if ( _Debug )
      {
         System.out.println("*********************");
         System.out.println("In API::LMSInitialize");
         System.out.println("*********************");
         System.out.println("");
      }

      // this holds the return value which is a CMIBoolean
      String result;
      result = cmiBooleanFalse;  // assume failure


      // Make sure param is empty string "" - as per the API spec
      // Check for "null" is a workaround described in "Known Problems"
      // in the header.
      String tempParm = String.valueOf(param);
      if ( (tempParm.equals("null") || tempParm.equals("")) != true )
      {
         APIAdapterApplet.lmsErrorManager.SetCurrentErrorCode("201");
         return result;
      }

      // Is LMS already initialized?
      if ( APIAdapterApplet.isLMSInitialized == true )
      {
         APIAdapterApplet.lmsErrorManager.SetCurrentErrorCode("101");
      }
      else
      {
         if ( _Debug )
         {
            System.out.println("Trying to get SCO Data from servlet...");
         }

         // Build the local (client-side) LMS data model cache by getting the
         // SCODataManager class instances from the LMS Servlet

         String sessionid = getCookie("JSESSIONID");

         ServletProxy servletProxy = new ServletProxy(this.servletURL, sessionid);
         APIAdapterApplet.theSCOData = servletProxy.GetSCOData();

         if ( _Debug )
         {
            System.out.println("The Core Data for the current SCO contains the following:");
            theSCOData.getCore().showData();
         }

         APIAdapterApplet.isLMSInitialized = true;

         // No errors were detected

         APIAdapterApplet.lmsErrorManager.ClearCurrentErrorCode();

         result = cmiBooleanTrue;  // success
      }

      if ( _Debug )
      {
         System.out.println("");
         System.out.println("*******************************");
         System.out.println("Done Processing LMSInitialize()");
         System.out.println("*******************************");
      }

      return result;
   }

   private String getCookie(String cookieName)
   {
      String cookie=null;
      try
      {
         // Note Ms Explorer 4.x doesn't support the Netscape.JSObject
         // so first try to get it using JavaScript on the page
         // All Applet tagsd then should be written with JavaScript
         cookie=getParameter("cookie");
         if ( cookie==null )
         {
            // If all fails try to use Netscape JSObject
            JSObject window = JSObject.getWindow(this);
            JSObject document = (JSObject)window.getMember( "document" );
            cookie=(String) document.getMember( "cookie" );
            if ( _Debug )
            {
               System.out.println("Cookies found ="+cookie);
            }
         }
         if ( cookie!=null )
         {
            // Now filter our cookie out
            StringTokenizer tk=new StringTokenizer(cookie,";");
            cookie=null;
            while ( tk.hasMoreTokens() )
            {
               String token=tk.nextToken();
               if ( token.indexOf(cookieName+"=")!=-1 )
               {
                  cookie=token.trim();
                  break;
               }
            }
            // Decode the cookie
            // We have set the date into the cookie value
            // and used a simplified URLencode to decode it
            if ( cookie!=null )
            {
               cookie = URLDecoder.decode(cookie);
            }
         }
      }
      catch ( Exception e )
      {
         //catching all because this isn't important data
         //just for debuging output the code
         if ( _Debug )
         {
            System.out.println("Couldn't retrieve the cookie"+e);
         }
         e.printStackTrace();
      }
      return cookie;
   }


   /***************************************************************************
   **
   ** Method:  LMSFinish
   ** Input:   none
   ** Output:  none
   **
   ** Description:
   **   Signals completion of communication with LMS
   **
   ***************************************************************************/
   public String LMSFinish(String param)
   {
      if ( _Debug )
      {
         System.out.println("*****************");
         System.out.println("In API::LMSFinish");
         System.out.println("*****************");
         System.out.println("");
      }

      String result = cmiBooleanFalse;  // assume failure

      // Make sure param is empty string "" - as per the API spec
      // Check for "null" is a workaround described in "Known Problems"
      // in the header.
      String tempParm = String.valueOf(param);
      if ( (tempParm.equals("null")) || (tempParm.equals("")) )
      {
         if ( CheckInitialization() == true )
         {
            CMICore lmsCore = APIAdapterApplet.theSCOData.getCore();

            // Need to add the SCOs Session Time into the running total time
            CMITime totalTime =
            new CMITime( lmsCore.getTotalTime().getValue() );

            if(_Debug)
            {
               System.out.println("\tTotal time: " + totalTime.toString());
            }
                
            CMITime sessionTime =
            new CMITime( lmsCore.getSessionTime().getValue() );
                
            if(_Debug)
            {
               System.out.println("\tSession time: " + sessionTime.toString());
            }

            totalTime.add(sessionTime);
            lmsCore.setTotalTime(totalTime.toString());

            if(_Debug)
            {
               System.out.println("\t\tTotal time: " + totalTime.toString());
            }

            // If changes are left uncommitted when LMSFinish
            // is called, the LMS forces an LMSCommit.
            if ( lmsCore.getExit().getValue().equalsIgnoreCase("suspend") )
            {
               lmsCore.setEntry("resume");
            }
            else
            {
               lmsCore.setEntry("");
            }

            if ( lmsCore.getLessonStatus().getValue().equalsIgnoreCase("not attempted") )
            {
               lmsCore.setLessonStatus("incomplete");
            }

            APIAdapterApplet.theSCOData.setCore(lmsCore);

            result = LMSCommit("");

            if ( result != cmiBooleanTrue )
            {
               if ( _Debug )
               {
                  System.out.println("LMSCommit failed causing LMSFinish to fail.");
               }
            }
            else
            {
               isLMSInitialized = false;

               // Now call a javascript function that should
               // be present in the window this applet is located in
               // that will change the SCO content frame using the
               // lesson servlet, or close the child window if one
               // was created.

               JSObject jsroot = JSObject.getWindow(this);

               //if ( this.theSCOData.getCore().getExit().getValue().equalsIgnoreCase("suspend") )
               //{
                  
                  //jsroot.eval("closeSCOContent()");
                
              // }
              // else
               //{
                  jsroot.eval("changeSCOContent()");
               //}
               result = cmiBooleanTrue;  // successful completion
            }
         }
      }
      else
      {
         APIAdapterApplet.lmsErrorManager.SetCurrentErrorCode("201");
      }

      if ( _Debug )
      {
         System.out.println("");
         System.out.println("***************************");
         System.out.println("Done Processing LMSFinish()");
         System.out.println("***************************");
      }

      return result;
   }

   /***************************************************************************
   **
   ** Method:  LMSGetValue(String element)
   ** Input:   String element
   ** Output:  String value of the cmi datamodel element named "element".
   **
   ** Description:
   **   Returns the current value of the cmi datamodel element named "element".
   **
   ***************************************************************************/
   public String LMSGetValue(String element)
   {
      if ( _Debug )
      {
         System.out.println("*******************");
         System.out.println("In API::LMSGetValue");
         System.out.println("*******************");
         System.out.println("");
      }

      if ( CheckInitialization() != true )
      {
         //LMS is not initialized    
         if(_Debug)
         {
            System.out.println("LMS Not Initialized");
         }
         String emptyString = new String("");
         return emptyString;
      }

      if(_Debug)
      {
         System.out.println("Request being processed: LMSGetValue(" + element + ")");
      }

      CMIRequest request = new CMIRequest(element,true);

      if ( _Debug )
      {
         System.out.println("Looking for the element " + request.getRequest() );
      }

      APIAdapterApplet.lmsErrorManager.ClearCurrentErrorCode();
      this.dmErrorManager.ClearCurrentErrorCode();
      String rtnVal = null;

      DataModelInterface dmInterface = new DataModelInterface();
      rtnVal = dmInterface.processGet(element,theSCOData,dmErrorManager);

      // Set the LMS Error Manager from the DataModel Manager
      lmsErrorManager.SetCurrentErrorCode(dmErrorManager.GetCurrentErrorCode());

      if ( rtnVal != null )
      {
         if ( _Debug )
         {
            System.out.println("LMSGetValue() found!");
            System.out.println("Returning: "+ rtnVal);
         }
      }
      else
      {
         if ( _Debug )
         {
            System.out.println("Found the element, but the value was null");
         }
         rtnVal = new String("");
      }

      if ( _Debug )
      {
         System.out.println("");
         System.out.println("************************************");
         System.out.println("Processing done for API::LMSGetValue");
         System.out.println("************************************");
      }

      return rtnVal;
   }


   /***************************************************************************
   **
   ** Method:  LMSSetValue(String element, String value)
   ** Input:   String element - cmi data model element (e.g. cmi.core.student_id
   **           String value - value to apply to the data element
   ** Output:  none
   **
   ** Description:
   **   Sets the cmi datamodel element to the specified value
   **
   ***************************************************************************/
   public String LMSSetValue(String element, String value)
   {
      String result = cmiBooleanFalse;

      if ( _Debug )
      {
         System.out.println("*******************");
         System.out.println("In API::LMSSetValue");
         System.out.println("*******************");
         System.out.println("");
      }

      APIAdapterApplet.lmsErrorManager.ClearCurrentErrorCode();
      this.dmErrorManager.ClearCurrentErrorCode();

      if ( CheckInitialization() != true )
      {
         //LMS is not initialized
         return result;
      }

      String setValue;

      // Check for "null" is a workaround described in "Known Problems"
      // in the header.
      String tempValue = String.valueOf(value);
      if ( tempValue.equals("null") )
      {
         setValue = new String("");
      }
      else
      {
         setValue = tempValue;
      }


      String theRequest = element + "," + setValue;
      if(_Debug)
      {
         System.out.println("Request being processed: LMSSetValue(" + theRequest + ")");
         System.out.println( "Looking for the element " + element );   
      }

      DataModelInterface dmInterface = new DataModelInterface();
      dmInterface.processSet(theRequest,theSCOData,dmErrorManager);

      // Set the LMS Error Manager from the DataModel Manager
      lmsErrorManager.SetCurrentErrorCode(dmErrorManager.GetCurrentErrorCode());
      if ( lmsErrorManager.GetCurrentErrorCode() == "0" )
      {
         result = cmiBooleanTrue; // success
      }

      if ( _Debug )
      {
         System.out.println("");
         System.out.println("************************************");
         System.out.println("Processing done for API::LMSSetValue");
         System.out.println("************************************");
      }


      return result;
   }

   /***************************************************************************
   **
   ** Method:  LMSCommit()
   ** Input:   none
   ** Output:  none
   **
   ** Description:
   **   Applies the SCO data model elements set using LMSSetValue
   **   to LMS data model elements and saves them (on the server).
   **
   ***************************************************************************/
   public String LMSCommit(String param)
   {
      if ( _Debug )
      {
         System.out.println("*************************");
         System.out.println("Processing API::LMSCommit");
         System.out.println("*************************");
         System.out.println("");
      }

      String result = cmiBooleanFalse; // assume failure

      // Make sure param is empty string "" - as per the API spec
      // Check for "null" is a workaround described in "Known Problems"
      // in the header.
      String tempParm = String.valueOf(param);
      if ( (tempParm.equals("null") || tempParm.equals("")) == true )
      {
         if ( CheckInitialization() != true )
         {
            //LMS is not initialized
            return result;
         }

         if ( _Debug )
         {
            System.out.println("Saving Data to the Server...");
            System.out.println("The SCO Data Manager for the current SCO contains " +
                               "the following:");
            theSCOData.getCore().showData();
         }

         String sessionid = getCookie("JSESSIONID");
         ServletProxy servletProxy = new ServletProxy(this.servletURL, sessionid);
         //向服务器提交数据
         String servletResult = servletProxy.PutSCOData(APIAdapterApplet.theSCOData);
         
         if ( servletResult.equals("OK") != true )
         {
            APIAdapterApplet.lmsErrorManager.SetCurrentErrorCode("101");
            if ( _Debug )
            {
               System.out.println("Put to Server was NOT successful!");
            }
         }
         else
         {
            APIAdapterApplet.lmsErrorManager.ClearCurrentErrorCode();
            result = cmiBooleanTrue; // successful completion
            if ( _Debug )
            {
               System.out.println("Put to Server succeeded!");
            }
         }
      }
      else
      {
         APIAdapterApplet.lmsErrorManager.SetCurrentErrorCode("201");
      }

      if ( _Debug )
      {
         System.out.println("");
         System.out.println("**********************************");
         System.out.println("Processing done for API::LMSCommit");
         System.out.println("**********************************");
      }

      return result;
   }

   /***************************************************************************
   **
   ** Method:  LMSGetLastError()
   ** Input:   none
   ** Output:  String - error code set by the last API function
   **
   ** Description:
   **   Retrieves the error code set by the most recently executed API function
   **   (Note:  Each API function sets or clears the error code.)
   **
   ***************************************************************************/
   public String LMSGetLastError()
   {
      if ( _Debug )
      {
         System.out.println("In API::LMSGetLastError()");
      }
      return APIAdapterApplet.lmsErrorManager.GetCurrentErrorCode();
   }

   /***************************************************************************
   **
   ** Method:  LMSGetErrorString( String errorCode)
   ** Input:   String errorCode - the error code to lookup
   ** Output:  String - the text associated with the errorCode
   **
   ** Description:
   **   This function returns the text associated with an error code
   **
   ***************************************************************************/
   public String LMSGetErrorString( String errorCode)
   {
      if ( _Debug )
      {
         System.out.println("In API::LMSGetErrorString()");
      }
      return APIAdapterApplet.lmsErrorManager.GetErrorDescription(errorCode);
   }

   /***************************************************************************
   **
   ** Method:  LMSGetDiagnostic( String errorCode)
   ** Input:   String errorCode - the error code to lookup
   ** Output:  String - the vendor specific diagnostic text associated with the
   **          errorCode
   **
   ** Description:
   **   This function returns the vendor specific diagnostic text associated
   **  with an error code
   **
   ***************************************************************************/
   public String LMSGetDiagnostic( String errorCode)
   {
      if ( _Debug )
      {
         System.out.println("In API::LMSGetDiagnostic()");
      }
      return APIAdapterApplet.lmsErrorManager.GetErrorDiagnostic(errorCode); 
   }

}
