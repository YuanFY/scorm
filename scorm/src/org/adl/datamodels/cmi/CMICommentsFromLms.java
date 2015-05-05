/*******************************************************************************
**
** Filename:  CMICommentsFromLms.java
**
** File Description:  The CMICommentsFromLms class manages the comments that
**                    would come from an LMS or Instructor. These comments would
**                    be available to the student or AU upon request.
**                    The comments from this class are read only.
**
** Author:  S. Thropp
**
** Contract Number:
** Company Name: CTC
**
** Module/Package Name:
** Module/Package Description:
**
** Design Issues:
** Implementation Issues:
** Known Problems:
** Side Effects:
**
** References: AICC CMI Data Model
**             ADL SCORM Version 1.1
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
** 11/15/2000     R.Ball            PT 290 - Initial creation of class to
**                                  manage comments from the LMS.
**
*******************************************************************************/
package org.adl.datamodels.cmi;

//native java imports
import java.io.*;
//adl imports
import org.adl.util.debug.*;
import org.adl.datamodels.*;

public class CMICommentsFromLms extends CMICategory
   implements Serializable
{
   // Instructor comments
   public Element comments_from_lms;

   // Number of Student Comments

   /****************************************************************************
    **
    ** Method:   Constructor
    ** Input:    String comments - comments to be used to initialize the attribute
    ** Output:   none
    **
    ** Description:  Sets up the comments_from_lms with the comments that were
    **               passed in
    **
    ***************************************************************************/
   public CMICommentsFromLms(String comments)
   {
      super( true );
      comments_from_lms = new Element (comments,"checkString4096","NULL",
                                        false,true,false);


   } // end of constructor

   /****************************************************************************
    **
    ** Method:   Default Constructor
    ** Input:    none
    ** Output:   none
    **
    ** Description:  Sets up the comment attributes with default values.
    **
    ***************************************************************************/
   public CMICommentsFromLms()
   {
      super( true );
      comments_from_lms = new Element("","checkString4096","NULL",
                                       false,true,false);

   } // end of default constructor


   /************************************************************************
    **  Accessors to the CMIComments Data.  AUs should not invoke these
    **  methods.  AUs should call LMSGetValue()
    ************************************************************************/
   public Element getCommentsFromLms()
   {
       return comments_from_lms;
   }

    /************************************************************************
    **  Modifiers to the CMIComments Data.  AUs should not invoke these
    **  methods.  AUs should call LMSSetValue()
    ************************************************************************/
   public void setCommentsFromLms(String inCommentsFromLMS)
   {
      comments_from_lms.setValue(inCommentsFromLMS);
   }


   /****************************************************************************
    **
    ** Method:  performGet
    ** Input:   CMIRequest theRequest - the tokenized LMSGetValue() request
    **          DMErrorManager dmErrorMgr - Error manager
    **
    ** Output:  String - the value portion of the element for the LMSGetValue()
    **
    ** Description:  This method performs the necessary steps to retrieve the
    **               value for the suspend_data data model element.
    **
    ***************************************************************************/
   public String performGet(CMIRequest theRequest,
                            DMErrorManager dmErrorMgr)
   {
      // String to hold the value of the final element
      String result = new String("");

      // Check to see if the Request has more tokens to process
      if ( theRequest.hasMoreTokensToProcess() )
      {
         if ( DebugIndicator.ON )
         {
            // Error - Data Model Element not implemented
            // No more elements should exist
            System.out.println("Error - Data Model Element not implemented\n");
            System.out.println("Element being processed: " + theRequest.getRequest() +
                            "element of the CMI Suspend Data " +
                            "Data Model Category");
         }

         // Determine if the Request is for a keyword.
         if ( theRequest.isAKeywordRequest() )
         {
            dmErrorMgr.recGetKeyWordError(theRequest.getElement());
         }
         else
         {
            dmErrorMgr.recNotImplementedError(theRequest);
         }
      }
      else
      {
         // No more tokes to process

         // determine the value associated with the element requested
         result = comments_from_lms.getValue();
      }

      // Done getting requested element.  Let the CMIRequest object
      // know that processing of the LMSGetValue() is done
      theRequest.done();

      return result;
   }  // end of performGet

   /****************************************************************************
    **
    ** Method:  performSet
    ** Input:   CMIRequest theRequest - tokenized LMSSetValue() request
    **          DMErrorManager dmErrorMgr - Error manager
    **
    ** Output:  none
    **
    ** Description:  The LMSGetValue() request is not allowed to be invoked
    **               by an AU.  Notify the operator
    **
    ***************************************************************************/
   public void performSet(CMIRequest theRequest,
                          DMErrorManager dmErrorMgr)
   {
      if ( DebugIndicator.ON )
      {
         // Error -- AU not allowed to call LMSSetValue on launch_data
         System.out.println("Invalid LMSSetValue() request: " +
                         theRequest.getRequest());

         System.out.println("Assignable Unit is not permitted to call" +
                         " LMSSetValue() for cmi.comments_from_lms");
      }

      // Error - no more elements represented in data model
      dmErrorMgr.SetCurrentErrorCode("403");

   }
 } // end of CMICommentsFromLms

