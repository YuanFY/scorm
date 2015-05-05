/*******************************************************************************
**
** Filename:  CMICore.java
**
** File Description:  The CMICore class manages information required
**                    to be furnished by all LMS Systems.  It contains
**                    data that all Assignable Units may depend upon at
**                    startup.
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
** Date Changed   Author            Reason for Changes
** ------------   ----------------  -------------------------------------------
** 09/05/2000     ADLI Project      PT110 : Changed the check for CMIString256
**                                          to CMIString255 to reflect the CMI
**                                          3.4 document.
**
** 11/15/2000     S. Thropp         PT 263 - Cosmetic changes to change the
**                                  version number of SCORM.
*******************************************************************************/

package org.adl.datamodels.cmi;

//native java imports
import java.io.*;
import java.lang.reflect.*;

//adl imports
import org.adl.util.debug.*;
import org.adl.datamodels.*;

public class CMICore extends CMICategory
   implements Serializable
{
   // Indicates whether the student is being credited by the LMS for their
   // performance in the AU
   public Element credit;

   // Indication of whether the student has been in the AU before
   public Element entry;

   // Indication of how or why the student left an AU
   public Element exit;

   // Lesson exit point passed to the LMS the last time the student experienced
   // the AU
   public Element lesson_location;

   // Identification of student-related information that may be used to change
   // the behavior of the AU
   public Element lesson_mode;

   // Student status as determined by the LMS
   public Element lesson_status;

   // Indication of the performance of the student during their last attempt on
   // the AU
   public CMIScore score;

   // Time spent in the AU during the session that is ending
   public Element session_time;

   // Unique alpha-numeric identifier that refers to  a single user of the LMS
   public Element student_id;

   // Offical name of the student
   public Element student_name;

   // Accumulated time of all the student sessions in the AU
   public Element total_time;

   // The minimum number of tokens in a valid core Data Model Element
   // request
   static int CMICORE_MIN_NUM_TOKENS = 3;


   /****************************************************************************
    **
    ** Method:  Default constructor
    ** Input:   none
    ** Output:  none
    **
    ** Description:  The default constructor sets up the class and initalizes
    **                all of its attributes.
    **
    **               See Element Constructor for more detail
    ***************************************************************************/
   public CMICore()
   {
      // Set up default values for all attributes

      super( true );
      student_id = new Element("","checkIdentifier","NULL",false,true,true);
      student_name = new Element("","checkString255","NULL",false,true,true);
      lesson_location = new Element("","checkString255","NULL",true,true,true);
      credit = new Element("","checkVocabulary","Credit",false,true,true);
      lesson_status = new Element("","checkVocabulary","Status",true,true,true);
      entry = new Element("","checkVocabulary","Entry",false,true,true);
      total_time = new Element("","checkTimespan","NULL",false,true,true);
      lesson_mode = new Element("","checkVocabulary","Mode",false,true,true);
      exit = new Element("","checkVocabulary","Exit",true,false,true);
      session_time = new Element("","checkTimespan","NULL",true,false,true);
      score = new CMIScore();
   }

   /************************************************************************
    **  Accessers to the CMICore Data.  AUs should not call these methods.
    **  AUs should call LMSGetValue().
    ************************************************************************/
   public Element getCredit()
   {
      return credit;
   }
   public Element getEntry()
   {
      return entry;
   }
   public Element getExit()
   {
      return exit;
   }
   public Element getLessonLocation()
   {
      return lesson_location;
   }
   public Element getLessonMode()
   {
      return lesson_mode;
   }
   public Element getLessonStatus()
   {
      return lesson_status;
   }
   public CMIScore getScore()
   {
      return score;
   }
   public Element getSessionTime()
   {
      return session_time;
   }
   public Element getStudentId()
   {
      return student_id;
   }
   public Element getStudentName()
   {
      return student_name;
   }
   public Element getTotalTime()
   {
      return total_time;
   }

   /************************************************************************
    **  Modifiers to the CMICore Data.  AUs should not call these methods.
    **  AUs should call LMSSetValue()
    ************************************************************************/
   public void setCredit(String inCredit)
   {
      credit.setValue(inCredit);
   }
   public void setEntry(String inEntry)
   {
      entry.setValue(inEntry);
   }
   public void setExit(String inExit)
   {
      exit.setValue(inExit);
   }
   public void setLessonLocation(String inLessonLocation)
   {
      lesson_location.setValue(inLessonLocation);
   }
   public void setLessonMode(String inLessonMode)
   {
      lesson_mode.setValue(inLessonMode);
   }
   public void setLessonStatus(String inLessonStatus)
   {
      lesson_status.setValue(inLessonStatus);
   }
   public void setScore(CMIScore inScore)
   {
      score.getRaw().setValue( inScore.getRaw().getValue() );
      score.getMin().setValue( inScore.getMin().getValue() );
      score.getMax().setValue( inScore.getMax().getValue() );
   }
   public void setSessionTime(String inSessionTime)
   {
      session_time.setValue(inSessionTime);
   }
   public void setStudentId(String inStudentID)
   {
      student_id.setValue(inStudentID);
   }
   public void setStudentName(String inStudentName)
   {
      student_name.setValue(inStudentName);
   }
   public void setTotalTime(String inTotalTime)
   {
      total_time.setValue(inTotalTime);
   }


   /****************************************************************************
    **
    ** Method:  performGet
    ** Input:   CMIRequest theRequest - the LMSGetValue() request
    **          DMErrorManager dmErrorMgr - Error manager, handles all
    **                                        error reporting
    **
    ** Output:  String - the value portion of the element for the LMSGetValue()
    **
    ** Description:  This method performs the LMSGetValue() request.  The method
    **    determines the value for the requested data element.
    **
    ***************************************************************************/
   public String performGet(CMIRequest theRequest,
                            DMErrorManager dmErrorMgr)
   {
      // Resultant value to return
      String result = new String("");

      // Check to make sure this is a valid LMSGetValue() request
      if ( isValidCoreRequest(theRequest) )
      {
         // Get the next token off of the request
         String token = theRequest.getNextToken();

         // Check to see if the Request has more tokens to process
         if ( theRequest.hasMoreTokensToProcess() )
         {
            // Subcategory found in request
            result = processSubcategory(theRequest,token,dmErrorMgr);
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
         }
      }
      else
      {
         if ( DebugIndicator.ON )
         {
            // Error - Data Model Element not implemented
            System.out.println("Error - Data Model Element not implemented");
            System.out.println("Request being processed: " + theRequest.getRequest() +
                            "\nis not a valid request for the CMI Core\n" +
                            "Data Model Category");
         }

         dmErrorMgr.recNotImplementedError(theRequest);
      }

      // Done getting requested element.  Let the CMIRequest object
      // know that the LMSGetValue() processing is done
      theRequest.done();

      return result;
   }  // end of performGet

   /***************************************************************************
    **
    ** Method:  getChildren
    ** Input:   none
    ** Output:  String representation of the elements of the CMI Core
    **          category
    **
    ** Description:
    **      This method returns a String containing the list of data elements
    **      that CMI Core data object contains.
    **
    ***************************************************************************/
   public String getChildren()
   {
      String children = "student_id,student_name,lesson_location," +
                        "credit,lesson_status,entry,score,total_time,lesson_mode," +
                        "exit,session_time";

      return children;
   }  // end of getChildren

   /****************************************************************************
    **
    ** Method:  performSet
    ** Input:   CMIRequest theRequest - request to process
    **          DMErrorManager dmErrorMgr - Error manager
    **
    ** Output:  none
    **
    ** Description:  This method performs the LMSSetValue() request.
    **
    ***************************************************************************/
   public void performSet(CMIRequest theRequest,
                          DMErrorManager dmErrorMgr)
   {
      // Check to see if this is a valid LMSSetValue() request
      if ( isValidCoreRequest(theRequest) == true )
      {
         // Get the next token off of the request
         String token = theRequest.getNextToken();

         // Check to see if the request has any more tokens to
         // process
         if ( theRequest.hasMoreTokensToProcess() )
         {
            // More tokens to process - subcategory found
            String result = processSubcategory(theRequest,token,dmErrorMgr);
         }
         else
         {
            //  No more tokens to process

            // Get the value to use for setting off of the request
            String value = theRequest.getValue();

            // Set the value
            if ( theRequest.isAKeywordRequest() )
            {
               dmErrorMgr.recKeyWordError(token);
            }
            else
            {
               // Check to see if the set is for lesson_status
               // SCOs are not permitted to set cmi.core.lesson_status
               // to not attempted
               if (token.equals("lesson_status") )
               {
                   if ( value.equals("not attempted") )
                   {
                       dmErrorMgr.SetCurrentErrorCode("405");
                   }
                   else
                   {
                       // Perform the setting of the value
                       doSet(this,token,value,dmErrorMgr);
                   }
               }
               else
               {
                   // Perform the setting of the value
                   doSet(this,token,value,dmErrorMgr);
               }
            }
         }
      }
      else
      {
         if ( DebugIndicator.ON )
         {
            // Error - Data Model Element not implemented
            System.out.println("Error - Data Model Element not implemented");
            System.out.println("Request being processed: " + theRequest.getRequest() +
                            "\nis not a valid request for the CMI Core\n" +
                            "Data Model Category");
         }

         dmErrorMgr.recNotImplementedError(theRequest);
      }

      // Done setting requested element.  Let the request object know
      // that the LMSSetValue() processing is finished
      theRequest.done();

   } // end of performSet

   /****************************************************************************
    **
    ** Method:  processSubcategory
    ** Input:   CMIRequest - the current request being processed
    **          String - the subcategory being processed
    **          DMErrorManager - the Data Model Error Manager
    **
    ** Output: String -  result to be returned.  If the request is for
    **                   an LMSSetValue() request an empty string is
    **                   returned
    **
    ** Description: This method processes the subcategories of the CMICore
    **              Data Model Category.  The only valid subcategory of the
    **              CMI Core class is the CMIScore class.  Any other \
    **              subcategories are invalid - non compliant.
    **
    **              If the request is for an LMSSetValue(), then an empty
    **              String is returned.
    **
    ***************************************************************************/
   private String processSubcategory(CMIRequest theRequest,
                                     String theElement,
                                     DMErrorManager dmErrorMgr)
   {
      String result = new String("");

      try
      {
         Field tmpField = this.getClass().getField(theElement);

         // Call Get or Set on CMIScore
         if ( theRequest.isForASetRequest() )
         {
            // Set the value
            if ( theRequest.isAKeywordRequest() )
            {
               dmErrorMgr.recKeyWordError(theRequest.getElement());
            }
            else
            {
               // Invoke the performSet() on the subcategory
               score.performSet(theRequest,dmErrorMgr);
            }
         }
         else
         {
            if ( theElement.equals("score") )
            {
               result = score.performGet(theRequest,dmErrorMgr);
            }
            else
            {
               dmErrorMgr.recGetKeyWordError(theRequest.getElement());
            }
         }
      }
      catch ( NoSuchFieldException nsfe )
      {
         if ( DebugIndicator.ON )
         {
            // Error - Data Model Element not implemented
            // *** The only subcategory in the CMI Core Data Category
            // *** is the score.  Any thing else is an error
            System.out.println("Error - Data Model Element not implemented");
            System.out.println("Element being processed: " + theElement +
                            "is not a valid sub category of the CMI Core\n" +
                            "Data Model Category");
         }

         dmErrorMgr.SetCurrentErrorCode("401");
      }
      catch ( SecurityException se )
      {
         if ( DebugIndicator.ON )
         {
            System.out.println(se);
            System.out.println("Access to the information is denied");
         }
         dmErrorMgr.SetCurrentErrorCode("101");
      }

      return result;
   }

   /****************************************************************************
    **
    ** Method:  isValidCoreRequest
    ** Input:   CMIRequest theRequest - the LMSGetValue() request
    **
    ** Output:  Boolean value indicating whether or not the Core Request is
    **          valid.
    **
    ** Description:  Checks to make sure that the request meets the minimum
    **               requirements needed for a CMICore Model Element.
    **
    ***************************************************************************/
   private boolean isValidCoreRequest(CMIRequest theRequest)
   {
      boolean rtrnFlag = false;

      // Check to see if the Request has the appropriate length for
      // a CMI Core request
      if ( theRequest.getTotalNumTokens() >= CMICORE_MIN_NUM_TOKENS )
      {
         rtrnFlag = true;
      }

      return rtrnFlag;

   } // end of isValidCoreRequest()

   /****************************************************************************
    **
    ** Method:  showData
    ** Input:   none
    ** Output: none
    **
    ** Description: Displays the current state of the CMI Core Data object
    **
    ***************************************************************************/
   public void showData()
   {
      if ( DebugIndicator.ON )
      {
         System.out.println("CMI Core Object:");

         System.out.println("\tstudent_id: " + getStudentId().getValue());
         System.out.println("\tstudent_name: " + getStudentName().getValue());
         System.out.println("\tlesson_location: " + getLessonLocation().getValue());
         System.out.println("\tcredit: " + getCredit().getValue());
         System.out.println("\tslesson_status: " + getLessonStatus().getValue());
         System.out.println("\tentry: " + getEntry().getValue());
         System.out.println("\texit: " + getExit().getValue());
         System.out.println("\tscore.raw: " + getScore().getRaw().getValue());
         System.out.println("\tscore.min: " + getScore().getMin().getValue());
         System.out.println("\tscore.max: " + getScore().getMax().getValue());
         System.out.println("\tsession_time: " + getSessionTime().getValue());
         System.out.println("\ttotal_time: " + getTotalTime().getValue());
         System.out.println("\tlesson_mode: " + getLessonMode().getValue());
      }
   }
}  // end of CMICore
