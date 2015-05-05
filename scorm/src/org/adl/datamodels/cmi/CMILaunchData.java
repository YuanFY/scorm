/*******************************************************************************
**
** Filename:  CMILaunchData.java
**
** File Description:  The CMILaunchData class manages unique information
**                    generated at the Assignable Unit's creation that is
**                    needed for every use.
**
** Author:  ADLI Project
**
** Company Name: Concurrent Technologies Corporation
**
** Module/Package Name: org.adl.datamodel.cmi
** Module/Package Description: Collection of CMI Data Model objects
**
** Design Issues:
**        In order to use Reflection (Java Feature) the defined Java
**        coding standards are NOT being followed.  Reflection requires
**        field names to match identically to input parameter.  The
**        attribute names match what is expected from a LMSGetValue()
**        or LMSSetValue() request.  Also the attribute values are declared
**        as public scope in order to use reflection.
**
** Implementation Issues:
** Known Problems:
** Side Effects:
**
** References: AICC CMI Data Model
**             ADL SCORM Version 1.0
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
** 11/15/2000     S. Thropp         PT 292: Changed the error code for the
**                                  performSet to map to the new Error Codes.
**
*******************************************************************************/
package org.adl.datamodels.cmi;

//native java imports
import java.io.*;

//adl imports
import org.adl.util.debug.*;
import org.adl.datamodels.*;

public class CMILaunchData extends CMICategory
                           implements Serializable
{
   // Unique information generated at the Assignable Unit's creation that
   // is needed for every use
   public Element launch_data;


   /****************************************************************************
    **
    ** Method:   Constructor
    ** Input:    String launchData - Unique information generated at the AU's
    **                               creation that is needed for every use.
    ** Output:   none
    **
    ** Description: Constructor for the launch data.
    **
    **               See Element Constructor for more detail
    **
    ***************************************************************************/
   public CMILaunchData(String launchData)
   {
      super( false );
      launch_data = new Element (launchData,"checkString4096","NULL",
                                 false,true,true);

   }  // end of constructor

   /****************************************************************************
    **
    ** Method:   Default Constructor
    ** Input:    none
    ** Output:   none
    **
    ** Description: Default constructor for the CMI Launch Data
    **
    **               See Element Constructor for more detail
    ***************************************************************************/
   public CMILaunchData()
   {
      super( false );
      launch_data = new Element("","checkString4096","NULL",
                                false,true,true);

   }  // end of default constructor

   /************************************************************************
    **  Accessors to the CMILaunchData Data.  AUs should not invoke these
    **  methods.  AUs should call LMSGetValue()
    ************************************************************************/
   public Element getLaunchData()
   {
      return launch_data;
   }

   /************************************************************************
    **  Modifiers to the CMILaunchData Data.  AUs should not invoke these
    **  methods.  AUs should call LMSSetValue().
    ************************************************************************/
   public void setLaunchData(String inLaunchData)
   {
      launch_data.setValue(inLaunchData);
   }


   /****************************************************************************
    **
    ** Method:  performGet
    ** Input:   CMIRequest theRequest - tokenized LMSGetValue() request
    **          DMErrorManager dmErrorMgr - Error manager
    **
    ** Output:  String - the value portion of the element for the LMSGetValue()
    **
    ** Description:  This method performs the necessary steps to retrieve the
    **     value for the launch_data data model element.
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
            // *** The only subcategory in the CMI Core Data Category
            // *** is the score.  Any thing else is an error
            System.out.println("Error - Data Model Element not implemented");
            System.out.println("Element being processed: " +
                            theRequest.getRequest() +
                            "is not a valid element of the CMI Launch Data\n" +
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
         // No more tokens to process

         // determine the value associated with the element requested
         result = launch_data.getValue();
      }

      // Done getting requested element.  Let the CMIRequest object know
      // that processing of the LMSGetValue() request is done
      theRequest.done();

      return result;
   }  // end of performGet()

   /****************************************************************************
    **
    ** Method:  performSet
    ** Input:   CMIRequest theRequest - tokenized LMSSetValue() request
    **          DMErrorManager dmErrorMgr - Error manager
    **
    ** Output:  none
    **
    ** Description:  The LMSSetValue() request is not allowed to be invoked
    **               by an AU.  Notify the operator
    **
    ***************************************************************************/
   public void performSet(CMIRequest theRequest,
                          DMErrorManager dmErrorMgr)
   {
      if ( DebugIndicator.ON )
      {
         // Error -- AU not allowed to call LMSSetValue on launch_data
         System.out.println("Error - Element cannot be set");
         System.out.println("Element is Read Only");
         System.out.println("Invalid LMSSetValue() request");
      }

      // Error - Element is read only
      dmErrorMgr.SetCurrentErrorCode("403");

      return;
   }  // end of performSet()

} // end of CMILaunchData