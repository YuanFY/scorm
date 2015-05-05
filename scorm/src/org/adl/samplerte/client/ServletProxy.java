/*******************************************************************************
** 
** Filename:  ServletProxy.java
**
** File Description: This class encapsulates communication between the API 
**                   Adapter applet and the LMS CMI Servlet
**    
** Author: ADL Technical Team
**
** Contract Number:
** Company Name: CTC
**
** Module/Package Name: org.adl.lms.client
** Module/Package Description: Client side Sample RTE classes
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
** OR INABILITY TO USE SOFTWARE, EVEN IF CTC  HAS BEEN ADVISED OF THE
** POSSIBILITY OF SUCH DAMAGES.
**
*******************************************************************************/

package org.adl.samplerte.client;

import java.net.*;
import java.io.*;
import org.adl.datamodels.*;
import org.adl.util.debug.DebugIndicator;

public class ServletProxy
{
   private static boolean _Debug = DebugIndicator.ON;

   private URL servletURL;
   private String sessionid;


   /****************************************************************************
   **
   ** Method:  ServletProxy(URL url)
   ** Input:   URL url  - the url of the LMS CMI Servlet
   ** Output:  none
   **
   ** Description:
   ** Constructor
   **
   ****************************************************************************/
   public ServletProxy( URL url, String sessionid ) 
   {
      this.servletURL = url;
      this.sessionid = sessionid;
   }

   /****************************************************************************
   **
   ** Method:  GetSCOData()
   ** Input:   none
   ** Output:  SCOData - an SCODataManager object containing all of the CMI data
   **                    elements relevant for the current user (student) and
   **                    current SCO.
   **
   ** Description:
   ** Reads from the LMS Server via the LMSCMI Servlet, the SCODataManager
   ** object containing all of the CMI datamodel elements relevant for the
   ** current user (student) and current SCO.
   **
   ****************************************************************************/
   public SCODataManager GetSCOData()
   {
      if(_Debug)
      {
         System.out.println( "In ServletProxy::GetSCOData()" );
      }

      try
      {
         String servletCommand = "cmigetcat";

         Serializable[] data = {servletCommand};

         if(_Debug)
         {
            System.out.println("Before postObjects()");
         }

         ObjectInputStream in =
         ServletWriter.postObjects(this.servletURL, data, sessionid );

         if(_Debug)
         {
            System.out.println( "Back In ServletProxy::GetSCOData()" );
            System.out.println( "Attempting to read servlet response now..." );
         }
         // Read the SCODataManager off of the Object Input Stream
         SCODataManager scoData = (SCODataManager) in.readObject();


         if(_Debug)
         {
            System.out.println( "Read servlet response successfully." );
            System.out.println( "Closing input stream" );
         }

         in.close();

         return scoData;
      }
      catch(Exception e)
      {
         if(_Debug)
         {
            System.out.println(
                    "Exception caught In ServletProxy::GetSCODataData()" );
            System.out.println( e.getMessage() );
         }

         e.printStackTrace();
         SCODataManager empty = new SCODataManager();
         return empty;
      }
   }

   /****************************************************************************
   **
   ** Method:  PutSCOData( SCODataManager scoData)
   ** Input:   SCODataManager scoData
   ** Output:  String "OK" if successful
   **                              "FAILED" if failed
   **
   ** Description:
   ** Writes to the LMS Server via the LMSCMI Servlet, the SCODataManager
   ** object containing all of the CMI datamodel elements relevant for SCO to 
   ** LMS communication.
   ****************************************************************************/
   public String PutSCOData( SCODataManager scoData )
   {
      try
      {
         if(_Debug)
         {
            System.out.println("In ServletProxy::PutSCOData()");
         }


         String servletCommand = "cmiputcat";

         Serializable[] data = {servletCommand, scoData};


         ObjectInputStream in =
         ServletWriter.postObjects(this.servletURL, data, sessionid );

         //String result = (String)in.readObject();  //doesn't return anything.
         in.close();
         String result = ( "OK" );
         return result;
      }
      catch(Exception e)
      {
         if(_Debug)
         {
            System.out.println(
                              "Exception caught In ServletProxy::PutSCOData()" );
            System.out.println( e.getMessage() );
         }

         e.printStackTrace();
         return "FAILED";
      }
   }

}
