/*******************************************************************************
**
** Filename:  CMIResponse.java
**
** File Description:   The CMIResponse class manages expected student
**                     feedback in the interaction.
**
** Author: ADLI Project
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
** 09/28/2000     ADLI Project      PT116 : The "value" and "description"
**                                          elements were removed and the
**                                          "pattern" element was added to
**                                          reflect the changes in the CMI 3.4
**                                          Guidelines For Interoperability
**                                          document.  All methods were modified
**                                          to reflect these changes
**
** 11/15/2000     S.Thropp         PT 263: Added comment describing pattern
**                                 attribute and removed reference to version
**                                 of SCORM.
*******************************************************************************/
package org.adl.datamodels.cmi;

//native java imports
import java.io.*;

//adl imports
import org.adl.util.debug.*;
import org.adl.datamodels.*;

public class CMIResponse extends CMICategory
                         implements Serializable
{
   // Definition of possible student response
   public Element pattern;

   /***************************************************************************
    **
    ** Method:  Constructor
    ** Input:   none
    ** Output:  none
    **
    ** Description: Default constructor
    **
    ***************************************************************************/
   public CMIResponse()
   {
      super( true );
      pattern = new Element("","checkFeedback","NULL",true,false,false);
   }  // end of default constructor

   /************************************************************************
   **  Accesser to the CMIResponse Data.  AUs should not call this method.
   **  AUs should call LMSGetValue().
   ************************************************************************/
   public Element getPattern()
   {
      return pattern;
   }

   public boolean isInitialized()
   {
      boolean flag = false;
      if ( pattern.isInitialized() )
      {
         flag = true;
      }

      return flag;
   }
   /************************************************************************
   **  Modifier to the CMIResponse Data.  AUs should not call this method.
   **  AUs should call LMSSetValue().
   ************************************************************************/
   public void setPattern(String inPattern)
   {
      pattern.setValue(inPattern);
   }


   /***************************************************************************
   **
   ** Method:  performSet
   ** Input:   CMIRequest theRequest - tokenized LMSSetValue() request
   **          DMErrorManager dmErrorMgr - Error Manager
   ** Output:  none
   **
   ** Description: This method performs all of the necessary steps to
   **              process an LMSSetValue() request.
   **
   ***************************************************************************/
   public void performSet(CMIRequest theRequest,
                          DMErrorManager dmErrorMgr)
   {
      // Get the next token off of the request
      String token = theRequest.getNextToken();

      // Check to see if the request has any more tokens to
      // process
      if ( theRequest.hasMoreTokensToProcess() )
      {
         if ( DebugIndicator.ON )
         {
            // Error - Data Model Element not implemented
            System.out.println("Error - Data Model Element not implemented");
            System.out.println("Element being processed: " + token +
                            "is not a valid element of the CMI Response\n" +
                            "Data Model Category");
         }

         dmErrorMgr.recNotImplementedError(theRequest);
      }
      else
      {
         //  No more tokens to process
         if (theRequest.isAKeywordRequest() == false)
         {

            // Get the value to use for setting off of the request
            String value = theRequest.getValue();

            // Set the value
            doSet(this,token,value,dmErrorMgr);
         }
         else
         {
            dmErrorMgr.recKeyWordError(token);
         }
      }
   }  // end of performSet()


} // end of CMIResponse

