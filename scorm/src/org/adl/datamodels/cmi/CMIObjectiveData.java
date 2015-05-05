/*******************************************************************************
**
** Filename:  CMIObjectiveData.java
**
** File Description:   The CMIObjectiveData class manages the actual objective
**                     data.  The objective data identifies how the student
**                     has performed on individual objectives in the
**                     SCO.
**
** Author:  ADL Technical Team
**
** Contract Number:
** Company Name: CTC
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
** 09/22/2000     S. Thropp         PT - 111: Changed all occurrences of status
**                                  to statues to reflect changes in the CMI
**                                  Guidelines for Interoperability revision
**                                  3.0.3
**
** 10-31-2000     R. Ball           PT 293: SCORM 1.1 Changes Objectives.
**                                  modified score and status from being lists
**                                  of scores and statuses to single items.
**
** 12-14-2000     S. Thropp         PT 293: Fixed problem with an LMSGetValue()
**                                  call The processSubcategory() was always
**                                  calling performGet() on CMIScore regardless
**                                  of the element.  Added check to verify that
**                                  the request was for cmi.objectives.0.score
**                                  before calling performGet().
**
*******************************************************************************/
package org.adl.datamodels.cmi;

//native java imports
import java.io.*;
import java.lang.reflect.*;

//adl imports
import org.adl.util.debug.*;
import org.adl.datamodels.*;

public class CMIObjectiveData extends CMICategory
                              implements Serializable

{
   // A developer defined, SCO specific identifier for an objective
   public Element id;

   // The score obtained by the student after each attempt to master
   // the objective
   public CMIScore score;

   // The status obtained by the student after each attempt to master
   // the objective
   public Element status;

   // Indicates if the objective data has been initialized
   private boolean initialized;

   static int CMIOBJECTIVEDATA_MIN_NUM_TOKENS = 3;

   /****************************************************************************
    **
    ** Method:  CMIObjectiveData()
    ** Input:   none
    **
    ** Output:  none
    **
    ** Description: This is the default constructor for the ObjectiveData
    **              class.  Default values are created for each element
    **
    ***************************************************************************/
   public CMIObjectiveData()
   {
        super( true );

        id = new Element("","checkIdentifier","NULL",true,true,false);
        score = new CMIScore();
        status = new Element("","checkVocabulary","Status",true,true,false);

   }


    /************************************************************************
    **  Accessers to the CMIObjectiveData.  SCOs should not call these methods.
    **  SCOs should call LMSGetValue().
    ************************************************************************/

   public Element getStatus()
   {
      return status;
   }
   public CMIScore getScore()
   {
      return score;
   }
   public Element getId()
   {
      return id;
   }

   /************************************************************************
    **  Modifiers to the CMIObjectives Data.  SCOs should not call these methods.
    **  SCOs should call LMSSetValue()
    ************************************************************************/
   public void setStatus(String inStatus)
   {
      status.setValue(inStatus);
   }
   public void setScore(CMIScore inScore)
   {
      score.getRaw().setValue( inScore.getRaw().getValue() );
      score.getMin().setValue( inScore.getMin().getValue() );
      score.getMax().setValue( inScore.getMax().getValue() );
   }
   public void setId(String inID)
   {
      id.setValue(inID);
   }


   public boolean isInitialized()
   {
      return initialized;
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
      if ( DebugIndicator.ON )
      {
         System.out.println("CMIObjectiveData::performGet()");
      }

      // Resultant value to return
      String result = new String("");

      // Check to make sure this is a valid LMSGetValue() request
      if ( isValidObjectiveDataRequest(theRequest) )
      {
         // Get the next token off of the request
         String token = theRequest.getNextToken();

         if ( DebugIndicator.ON )
         {
            System.out.println("Token: " + token);
         }

         // Check to see if the Request has more tokens to process
         if ( theRequest.hasMoreTokensToProcess() )
         {
            // Subcategory found in request
            result = processSubcategory(theRequest,token,dmErrorMgr);
         }
         else
         {
            // No more tokes to process

            // Check to see if the request is for the children of Objective Data
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
            System.out.println("Request being processed: " +
                               theRequest.getRequest() +
                               "\nis not a valid request for the CMI Objective\n" +
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
    ** Output:  String representation of the elements of the CMI Objective
    **          category
    **
    ** Description:
    **      This method returns a String containing the list of data elements
    **      that CMI Objective data object contains.
    **
    ***************************************************************************/
   public String getChildren()
   {
      String children = "id,status,score";

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
      if ( isValidObjectiveDataRequest(theRequest) == true )
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
               // Perform the setting of the value
               doSet(this,token,value,dmErrorMgr);
               initialized = true;
            }
         }
      }
      else
      {
         if ( DebugIndicator.ON )
         {
            // Error - Data Model Element not implemented
            System.out.println("Error - Data Model Element not implemented");
            System.out.println("Request being processed: " +
                               theRequest.getRequest() +
                               "\nis not a valid request for the CMI Objective\n" +
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
    ** Description: This method processes the subcategories of the CMIObjectives
    **              Data Model Category.  The only valid subcategory of the
    **              CMI Objectives class is the CMIScore class.  Any other
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
      if ( DebugIndicator.ON )
      {
         System.out.println("In CMIObjectiveData::processSubcategory()");
         System.out.println("The Element: " + theElement);
      }
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
            // Determine if the Request is for a keyword.
            else if ( theRequest.isAKeywordRequest() )
            {
               dmErrorMgr.recGetKeyWordError(theRequest.getElement());
            }
            else
            {
               dmErrorMgr.recNotImplementedError(theRequest);
            }
         }
      }
      catch ( NoSuchFieldException nsfe )
      {
         if ( DebugIndicator.ON )
         {
            // Error - Data Model Element not implemented
            // *** The only subcategory in the CMI Objective Data Category
            // *** is the score.  Any thing else is an error
            System.out.println("Error - Data Model Element not implemented");
            System.out.println("Element being processed: " + theElement +
                            "is not a valid sub category of the CMI " +
                            "Objectives\nData Model Category");
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
    ** Method:  isValidObjectiveDataRequest
    ** Input:   CMIRequest theRequest - the LMSGetValue() request
    **
    ** Output:  Boolean value indicating whether or not the Request is
    **          valid.
    **
    ** Description:  Checks to make sure that the request meets the minimum
    **               requirements needed for a CMIObjectiveData Model Element.
    **
    ***************************************************************************/
   private boolean isValidObjectiveDataRequest(CMIRequest theRequest)
   {
      boolean rtrnFlag = false;

      // Check to see if the Request has the appropriate length for
      // a CMI Objective request
      if ( theRequest.getTotalNumTokens() >= CMIOBJECTIVEDATA_MIN_NUM_TOKENS )
      {
         rtrnFlag = true;
      }

      return rtrnFlag;

   } // end of isValidObjectiveDataRequest()

   /****************************************************************************
    **
    ** Method:  isValidRequest
    ** Input:   CMIRequest theRequest - tokenized request
    **          DMErrorManager dmErrorMgr - instance of the Data Model Error
    **              manager
    **
    ** Output:  boolean result indicating the validity of the request
    **
    ** Description:  This method checks to make sure the request is a valid
    **               Data Model request.
    **
    ***************************************************************************/
   public String isValidRequest(CMIRequest theRequest,
                                 DMErrorManager dmErrorMgr)
   {

      boolean flag = false;
      String result = new String("");

      if ( theRequest.getTotalNumTokens() <= 5)
      {
         // Get the next token off of the request
         String token = theRequest.getNextToken();

         if ( DebugIndicator.ON )
         {
             System.out.println("token: " + token);
         }

         if (token.equals("id") ||
             token.equals("status") )
         {
            if ( theRequest.hasMoreTokensToProcess() )
            {
               flag = false;
               dmErrorMgr.recNotImplementedError(theRequest);
            }
            else
            {
               dmErrorMgr.SetCurrentErrorCode("0");
            }
         }
         else if (token.equals("score") )
         {
             flag = score.isValidRequest(theRequest);
             if ( theRequest.isAChildrenRequest() )
             {
                 result = score.getChildren();
             }
         }
      }
      else
      {
          // More than 5 tokens element is not implemented
          dmErrorMgr.recNotImplementedError(theRequest);
      }

      return result;
   }


   /****************************************************************************
    **
    ** Method:  showData
    ** Input:   none
    ** Output: none
    **
    ** Description: Displays the current state of the CMI Objective Data object
    **
    ***************************************************************************/
   public void showData()
   {
      if ( DebugIndicator.ON )
      {
         System.out.println("CMI Objective Data Object:");

         System.out.println("\tid: " + getId().getValue());
         System.out.println("\tstatus: " + getStatus().getValue());
         System.out.println("\tscore.raw: " + getScore().getRaw().getValue());
         System.out.println("\tscore.min: " + getScore().getMin().getValue());
         System.out.println("\tscore.max: " + getScore().getMax().getValue());
      }
   }



   }  // end of CMIObjectiveData
