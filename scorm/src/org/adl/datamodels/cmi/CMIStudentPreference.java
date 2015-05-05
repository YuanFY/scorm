/*******************************************************************************
**
** Filename:  CMIStudentPreference.java
**
** File Description:  The CMIStudentPreference class manages student selected
**                    options that are appropriate for subsequent Assignable
**                    Units.
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
** 09/05/2000     ADLI Project      PT110 : Any references to CMIString256 were
**                                          changed to CMIString255 to reflect
**                                          the changes in the CMI 3.0.3
**                                          document.
**
** 09/07/2000     ADLI Project      PT112 : The "language" element was changed
**                                          to CMIString255 due to the fact that
**                                          CMILocale no longer exists.
**
** 11/15/2000     S. Thropp         PT 296: Removal of all elements except for
**                                          audio,language, speed, and text.
**
** 05/14/2002     B. Capone         PT1912: Modified performSet() - needed to
**                                  restrict the allowable range of values for
**                                  audio, speed and text.
**
*********************************************************************************/
package org.adl.datamodels.cmi;

//native java imports
import java.io.*;
//adl imports
import org.adl.util.debug.*;
import org.adl.datamodels.*;

public class CMIStudentPreference extends CMICategory
   implements Serializable

{
   // Sound on/off and volume control
   public Element audio;

   // Identifies in what language the information should be delivered
   public Element language;

   // Pace of content delivery
   public Element speed;

   // Written content visibility control
   public Element text;

   // The minimum number of tokens in a valid Student Preference Data Model
   // Element request
   static int CMISTPREF_MIN_NUM_TOKENS = 3;

   /**************************************************************************
   **
   ** Method:  Default constructor
   ** Input:   none
   ** Output:  none
   **
   ** Description:  The default constructor sets up the class and initalizes
   **                 all of its attributes
   **
   **************************************************************************/
   public CMIStudentPreference()
   {
      // Set up default values for all attributes
      super( true );
      audio = new Element("","checkSInteger","NULL",true,true,true);
      language = new Element("","checkString255","NULL",true,true,true);
      speed = new Element("","checkSInteger","NULL",true,true,true);
      text = new Element("","checkSInteger","NULL",true,true,true);

   }  // end of default constructor

   /************************************************************************
    **  Accessers to the CMIStudentPreference Data.  AUs should not call
    **  these methods.  AUs should call LMSGetValue().
    ************************************************************************/
   public Element getAudio()
   {
      return audio;
   }
   public Element getLanguage()
   {
      return language;
   }
   public Element getSpeed()
   {
      return speed;
   }
   public Element getText()
   {
      return text;
   }

   /************************************************************************
    **  Modifiers to the CMIStudentPreference Data.  AUs should not call
    **  these methods.  AUs should call LMSSetValue()
    ************************************************************************/
   public void setAudio(String inAudio)
   {
      audio.setValue(inAudio);
   }
   public void setLanguage(String inLanguage)
   {
      language.setValue(inLanguage);
   }
   public void setSpeed(String inSpeed)
   {
      speed.setValue(inSpeed);
   }
   public void setText(String inText)
   {
      text.setValue(inText);
   }

   /****************************************************************************
    **
    ** Method:  performGet
    ** Input:   CMIRequest theRequest - tokenized LMSGetValue() request
    **          DMErrorManager dmErrorMgr - Error manager
    **
    ** Output:  String - the value portion of the element for the LMSGetValue()
    **
    ** Description:  This method performs the LMSGetValue() request.  The method
    **    determines the value for the requested data element.  The method
    **    handles the cases where there are additional sub-classes
    **    (subcategories) for the CMICoreData data element.
    **
    ***************************************************************************/
   public String performGet(CMIRequest theRequest,
                            DMErrorManager dmErrorMgr)
   {
      // Resultant value to return
      String result = new String("");

      // Check to make sure this is a valid LMSGetValue() request
      if ( isValidStPrefRequest(theRequest) == true )
      {
         // Get the next token off of the request
         String token = theRequest.getNextToken();

         // Check to see if the Request has more tokens to process
         if ( theRequest.hasMoreTokensToProcess() )
         {
            if ( DebugIndicator.ON )
            {
               // Invalid parameter passed to LMSGetValue()
               System.out.println("Error - Data Model Element not implemented");
               System.out.println("Invalid data model element: " +
                               theRequest.getRequest() +
                               " passed to interface");
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

            // Check to see if the request is for the children of Core Data
            if ( theRequest.isAChildrenRequest() )
            {
               // Set result to the string of children
               result = getChildren();
            }
            else
            {
               // determine the value associated with the element requested
               result = determineElementValue(this,token,dmErrorMgr);
            }
         } // end of ifHasMoreTokens()
      } // end of isVaildStPrefRequest
      else
      {
         if ( DebugIndicator.ON )
         {
            // Invalid parameter passed to LMSGetValue()
            System.out.println("Error - Data Model Element not implemented");
            System.out.println("Invalid data model element: " +
                            theRequest.getRequest() +
                            " passed to interface");
         }
         // Notify error manager
         dmErrorMgr.recNotImplementedError(theRequest);
      }

      // Done getting requested element.  Let the Request object know
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
    ** Description: This method performs all of the necessary steps to process
    **              an LMSSetValue() request.
    **
    ***************************************************************************/
   public void performSet(CMIRequest theRequest,
                          DMErrorManager dmErrorMgr)
   {
      // Check to see if this is a valid LMSSetValue() request
      if ( isValidStPrefRequest(theRequest) == true )
      {
         // Get the next token off of the request
         String token = theRequest.getNextToken();

         // Check to see if the request has any more tokens to
         // process
         if ( theRequest.hasMoreTokensToProcess() )
         {
            if ( DebugIndicator.ON )
            {
               // Invalid parameter passed to LMSGetValue()
               System.out.println("Error - Data Model Element not implemented");
               System.out.println("Invalid data model element: " +
                               theRequest.getRequest() +
                               " passed to interface");
            }
            // Notify error manager
            dmErrorMgr.recNotImplementedError(theRequest);
         }
         else
         {
            //  No more tokens to process
            if ( theRequest.isAChildrenRequest() )
            {
               if ( DebugIndicator.ON )
               {
                  System.out.println("Error - trying to set a keyword");
               }
               dmErrorMgr.recKeyWordError(token);
            }
            else
            {
               // Get the value to use for setting off of the request
               String value = theRequest.getValue();

               //Special cases for audio, speed and text
               if (token.equals("audio") || token.equals("speed") ||
                   token.equals("text"))
               {
                  try
                  {
                     int ivalue;
                     ivalue = Integer.parseInt(value);
                     if ((token.equals("audio") && ((ivalue >= -1)
                           && (ivalue <= 100))) ||
                         (token.equals("speed") && ((ivalue >= -100)
                           && (ivalue <= 100))) ||
                         (token.equals("text") && ((ivalue >= -1)
                                                    && (ivalue <= 1))))
                     {
                        // Set the value
                        doSet(this,token,value,dmErrorMgr);
                     }
                     else
                     {
                        dmErrorMgr.SetCurrentErrorCode("405");
                     }
                  }
                  catch(NumberFormatException nfe)
                  {
                     dmErrorMgr.SetCurrentErrorCode("405");
                     if ( DebugIndicator.ON )
                     {
                        System.out.println("Error - Number Format Exception");
                     }
                  }
               }
               else
               {
                  // For all others, Set the value
                  doSet(this,token,value,dmErrorMgr);
               }
            }
         }
      }
      else
      {
         if ( DebugIndicator.ON )
         {
            // Invalid parameter passed to LMSSetValue()
            System.out.println("Error - Data Model Element not implemented");
            System.out.println("Invalid data model element: " +
                            theRequest.getRequest() +
                            " passed to interface");
         }
         // Notify error manager
         dmErrorMgr.recNotImplementedError(theRequest);
      }

      // Done getting requested element.  Let the Request object know
      // that processing of the LMSGetValue() request is done
      theRequest.done();

      return;

   }  // end of performSet()


   /****************************************************************************
    **
    ** Method:  isValidStPrefRequest
    ** Input:   CMIRequest theRequest - the LMSGetValue() request
    **
    ** Output:  Boolean value indicating whether or not the Student Preference
    **          Request is valid.
    **
    ** Description:  Checks to make sure that the request meets the minimum
    **               requirements needed for a CMIStudentPreferenceData Model
    **               Element.
    **
    ***************************************************************************/
   private boolean isValidStPrefRequest(CMIRequest theRequest)
   {
      boolean rtrnFlag = false;

      if ( theRequest.getTotalNumTokens() >= CMISTPREF_MIN_NUM_TOKENS )
      {
         rtrnFlag = true;
      }

      return rtrnFlag;

   } // end of isValidStPrefRequest()

   /****************************************************************************
    **
    ** Method:  getChildren
    ** Input:   none
    **
    ** Output:  String representation of the children (attributes) of the
    **          CMIStudentPreference Data Model Category
    **
    ** Description:  Returns a String representation of the children of the
    **               CMIStudentPreference
    **
    ***************************************************************************/
   private String getChildren()
   {
      String children = new String("audio,language,speed,text,");

      return children;
   } // end of getChildren()

}  // end of StudentPreference
