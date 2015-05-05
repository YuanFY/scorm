/*******************************************************************************
**
** Filename:  DataModelInterface.java
**
** File Description:  The DataModelInterface class acts as an interface
**                    between the LMS API and the CMI Data Model itself.
**                    The API will invoke methods on the Data Model Interface
**                    for LMSGetValue() and LMSSetValue() request.
**
**                    The Data Model Interface then will interact with the
**                    SCO Data Manager to process the appropriate request.
**
** Author:  ADLI Project
**
** Company Name: Concurrent Technologies Corporation
**
** Module/Package Name:  org.adl.datamodel.cmi
** Module/Package Description: Collection of CMI Data Model objects
**
** Design Issues:
** Implementation Issues:
** Known Problems:
** Side Effects:
**
** References: AICC CMI Data Model
**             ADL SCORM
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
** 11/15/2000     S. Thropp         PT 263: Removal of reference to version of
**                                  SCORM.
**
** 01/12/2001     S. Thropp         PT 377: Changed all occurrences of AU/au to
**                                  SCO/sco.
**
*******************************************************************************/
package org.adl.datamodels;

import java.util.*;
//adl imports
import org.adl.util.debug.*;
import org.adl.datamodels.cmi.*;

public class DataModelInterface
{

   /****************************************************************************
    **
    ** Method:  DataModelInterface()
    ** Input:   none
    ** Output:  none
    **
    ** Description:  Default constructor
    **
    ***************************************************************************/
   public DataModelInterface()
   {}

   /****************************************************************************
    **
    ** Method:  processGet
    ** Input:   String theRequest - The LMSGetValue() request from the SCO
    **          SCODataManager - The SCO Data Manager that holds all of the
    **                 data/data structure for the SCO
    **
    ** Output:  String - the value of the Data model element requested
    **
    ** Description:  This method begins the processing of an LMSGetValue()
    **       request.  The method first creates a CMIRequest object.  This
    **       object is responsible for tokenizing the requested string.  The
    **       method then invokes the getValue() method on the SCO Data Manager
    **       and returns the associated result.
    **
    ***************************************************************************/
   public String processGet(String theRequest,
                            SCODataManager scoData,
                            DMErrorManager dmErrMgr)
   {
      if ( DebugIndicator.ON )
      {
         System.out.println("");
         System.out.println("**********************************");
         System.out.println("In DataModelInterface::processGet()");
         System.out.println("**********************************");
         System.out.println("");
      }
      // Variable to hold the result from the LMSGetValue()
      String result = new String("");

      if ( DebugIndicator.ON )
      {
         System.out.println("In DataModelInterface::processGet");
      }

      if ( isValidRequest(theRequest) == true )
      {
         // Create a CMI Request object
         CMIRequest request = new CMIRequest(theRequest,true);

         // Process the requst
         result = scoData.getValue(request,
                                  dmErrMgr);
      }
      else
      {
         if ( DebugIndicator.ON )
         {
            // Invalid parameter passed to LMSGetValue() or LMSSetValue()
            System.out.println("Data model not supported by SCORM");
         }

         // Notify error manager
         dmErrMgr.SetCurrentErrorCode("201");
      }

      if ( DebugIndicator.ON )
      {
         System.out.println("");
         System.out.println("**********************************");
         System.out.println("Returning to API: [" + result + "]");
         System.out.println("**********************************");
         System.out.println("");
      }
      return result;
   }

   /****************************************************************************
    **
    ** Method:  processSet
    ** Input:   String theRequest - The LMSGetValue() request from the SCO
    **          SCODataManager - The SCO Data Manager that holds all of the
    **                 data/data structure for the SCO
    **          LMSErrorManager - Error manager used for error reporting
    **
    ** Output:  none
    **
    ** Description: This method begins the processing of an LMSSetValue()
    **       request.  The method first creates a CMIRequest object.  This
    **       object is responsible for tokenizing the requested string.  The
    **       method then invokes the setValue() method on the SCO Data Manager
    **
    ***************************************************************************/
   public void processSet(String theRequest,
                          SCODataManager scoData,
                          DMErrorManager dmErrMgr)
   {
      if ( DebugIndicator.ON )
      {
         System.out.println("");
         System.out.println("***********************************");
         System.out.println("In DataModelInterface::processSet()");
         System.out.println("***********************************");
         System.out.println("");
      }

      StringTokenizer stk = new StringTokenizer(theRequest, ",", false);
      String tmpRequest = stk.nextToken();

      if ( isValidRequest(tmpRequest) == true )
      {
         // Create a CMI Request object
         CMIRequest request = new CMIRequest(theRequest,false);

         // Process the requst
         scoData.setValue(request,
                         dmErrMgr);
      }
      else
      {
         if ( DebugIndicator.ON )
         {
            // Invalid parameter passed to LMSGetValue() or LMSSetValue()
            System.out.println("Data model not supported by SCORM");
         }

         // Notify error manager
         dmErrMgr.SetCurrentErrorCode("201");
      }
   } // end of processSet

   /****************************************************************************
    **
    ** Method:  isValidRequest
    ** Input:   String request - the request from the SCO
    ** Output:  Boolean value stating whether the request is valid
    **
    ** Description:  This method checks to see if the request received is
    **      valid.  The request must have at lease 2 tokens in order to
    **      be valid (i.e. cmi.suspend_data)
    **
    ***************************************************************************/
   private boolean isValidRequest(String theRequest)
   {
      boolean rtrnFlag = true;
      StringTokenizer stk = new StringTokenizer(theRequest, ".", false);
      int totalNumOfTok = stk.countTokens();

      // The request must have at least 2 tokens.  If a request has less
      // than two tokens then there was an invalid request received
      if ( totalNumOfTok < 2 )
      {
         rtrnFlag = false;
      }

      String token = stk.nextToken();
      // Check to make sure SCO is using the cmi data model
      if ( token.equals("cmi") )
      {
         rtrnFlag = true;
      }
      else
      {
         rtrnFlag = false;
      }

      return rtrnFlag;

   } // end of isValidRequest()

} // end of DataModelInterface
