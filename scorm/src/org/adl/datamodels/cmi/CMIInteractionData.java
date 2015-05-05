/*******************************************************************************
**
** Filename:  CMIInteractionData.java
**
** File Description: The CMIInteractionData class manages the interaction
**                   data elements.  The interaction data is a recognized
**                   and recordable input or group of inputs from the
**                   student to the computer.
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
** 09/05/2000     ADLI Project      PT110 : Any references to CMIString256 were
**                                          changed to CMIString255 to reflect
**                                          the changes in the CMI 3.4
**                                          document.
**
** 09/28/2000     ADLI Project      PT116 : The "response" element was changed
**                                          to "correct_responses" to reflect
**                                          changes in the CMI 3.4 data model.
**                                          All related function names were
**                                          also changed due to the use of
**                                          reflection.
**
** 11/15/2000     S. Thropp         PT292 : Changed the error code that was
**                                  associated with the objective_id being
**                                  and invalid type on a LMSSetValue() request.
**
**                                  PT115: Changed objective_ids to become an
**                                  array of ids instead of one id.
**
** 12/12/2000     J. Poltrack       PTXXX: The objective_ids element changed to
**                                  objectives.n.id
**
** 01/17/2001     S.Thropp          PT374: Added more debug statments.
**                                  Removed all references to hardcoded arrays
**                                  and changed code to use Vectors.
**
** 05/14/2002     B. Capone         PT1832: Changed the ID element in the
**                                  constructor from checkString255 to
**                                  checkIdentifier.  Also, made same type of
**                                  change in performSetForObjID().
**
** 05/15/2002     B. Capone         PT1976: Modified SetCurrentErrorCode for
**                                  ArrayIndexOutOfBoundsException from 405 to
**                                  201 in the function performSetForResponse().
**
*******************************************************************************/
package org.adl.datamodels.cmi;

//native java imports
import java.io.*;
import java.util.*;

//adl imports
import org.adl.util.debug.*;
import org.adl.datamodels.*;

public class CMIInteractionData extends CMICategory
   implements Serializable
{
   // Unique alphanumeric label created by the content developer
   public Element id;

   // Indication of any objectives associated with the interaction
   public Vector objectives;

   // Indication of when the interaction is available to the student
   public Element time;

   // Indication of which category of interaction is recorded
   public Element type;

   // Expected student feedback in the interaction
   public Vector correct_responses;

   // Factor that is used to identify the relative importance of one
   // interaction compared to another
   public Element weighting;

   // Description of the computer measurable action of a student in an
   // interaction
   public Element student_response;

   // Judgement of the student's response
   public Element result;

   // The time from the presentation of the stimulus to the completion of the
   // measurable response
   public Element latency;

   // Indicates if the Interaction Data has been initialized
   private boolean initialized;

   /****************************************************************************
    **
    ** Method:  CMIInteractionData
    ** Input:   none
    ** Output:  none
    **
    ** Description:  Default Constructor
    **
    ***************************************************************************/
   public CMIInteractionData()
   {
      super( true );
      id = new Element("","checkIdentifier","NULL",true,false,false);
      time = new Element("","checkTime","NULL",true,false,false);
      type = new Element("","checkVocabulary","Interaction",true,false,false);
      weighting = new Element("","checkDecimal","NULL",true,false,false);
      student_response =
         new Element("","checkFeedback","NULL",true,false,false);
      result = new Element("","checkVocabulary","Result",true,false,false);
      latency = new Element("","checkTimespan","NULL",true,false,false);

      correct_responses = new Vector();
      objectives = new Vector();

      initialized = false;

   }  // end of default constuctor

   /****************************************************************************
   ** Here are the accessers for the CMIInteractionData class.
   ** AUs should not invoke these methods.  AUs should call LMSGetValue()
   ***************************************************************************/
   public Element getID()
   {
      return id;
   }
   public Element getLatency()
   {
      return latency;
   };
   public Element getResult()
   {
      return result;
   };
   public Element getStudentResponse()
   {
      return student_response;
   }
   public Element getTime()
   {
      return time;
   }
   public Element getType()
   {
      return type;
   }
   public Element getWeighting ()
   {
      return weighting;
   }
   public Vector getObjID()
   {
      return objectives;
   }
   public Vector getCorrectResponses()
   {
      return correct_responses;
   }

   /****************************************************************************
   ** Here are the modifiers for the CMIInteractionData class.
   ** AUs should not invoke these methods.  AUs should call LMSSetValue().
   ***************************************************************************/
   public void setID(String inId)
   {
      id.setValue(inId);
      initialized = true;
   }
   public void setLatency(String inLatency)
   {
      latency.setValue(inLatency);
      initialized = true;
   }
   public void setResult(String inResult)
   {
      result.setValue(inResult);
      initialized = true;
   }
   public void setStudentResponse(String inStudentResponse)
   {
      student_response.setValue(inStudentResponse);
      initialized = true;
   }
   public void setTime(String inTime)
   {
      time.setValue(inTime);
      initialized = true;
   }
   public void setType(String inType)
   {
      type.setValue(inType);
      initialized = true;
   }
   public void setWeighting(String inWeighting)
   {
      weighting.setValue(inWeighting);
      initialized = true;
   }

   public void setObjID(String inObjId,
                        int index)
   {
      objectives.add(index,inObjId);
   }  // end of setObjIDs()

   public void setCorrectResponses(CMIResponse inResponse,int index)
   {
      correct_responses.add(index,inResponse);
   } // end of setResponse()

   public boolean isInitialized()
   {
      return initialized;
   }

   /****************************************************************************
   **
   ** Method:  performSet
   ** Input:   CMIRequest theRequest - tokenized LMSSetValue() request
   ** Output:  none
   **
   ** Description:  This method performs all the necessary steps for an
   **               LMSSetValue() request
   **
   ***************************************************************************/
   public void performSet(CMIRequest theRequest,
                          DMErrorManager dmErrorMgr)
   {
      if ( DebugIndicator.ON )
      {
         System.out.println("\t\tInteractionData::performSet()");
      }

      // Get the next token off of the request
      String token = theRequest.getNextToken();

      if ( DebugIndicator.ON )
      {
         System.out.println("\t\tProcessing next token: " + token);
      }

      // Check to see if the request has any more tokens to
      // process
      if ( theRequest.hasMoreTokensToProcess() )
      {
         // More tokens to process - subcategory found
         if ( (token.equals("objectives")) == true )
         {
            token = theRequest.getNextToken();

            if ( DebugIndicator.ON )
            {
               System.out.println("\t\tProcessing next token " + token);
            }
            performSetForObjID(token,theRequest,dmErrorMgr);
         }
         else if ( (token.equals("correct_responses")) == true )
         {
            token = theRequest.getNextToken();
            if ( DebugIndicator.ON )
            {
               System.out.println("\t\tProcessing next token " + token);
            }
            performSetForResponse(token,theRequest,dmErrorMgr);
         }
         else
         {
            if ( DebugIndicator.ON )
            {
               // Error - Data Model Element not implemented
               System.out.println("\t\tError - Data Model Element not implemented");
               System.out.println("\t\tElement being processed: " + token +
                               " is not a valid element of the CMI Interactions\n" +
                               "Data Model Category");
            }

            dmErrorMgr.recNotImplementedError(theRequest);
         }
      }
      else
      {
         //  No more tokens to process
         // Set the value
         if ( theRequest.isAKeywordRequest() )
         {
            dmErrorMgr.recKeyWordError(theRequest.getElement());
         }
         else
         {
            if ( DebugIndicator.ON )
            {
               System.out.println("\t\tPerforming set");
            }
            doSet(this,token,theRequest.getValue(),dmErrorMgr);
            initialized = true;
         }
      }

      if ( DebugIndicator.ON )
      {
          System.out.println("\t\tExiting CMIInteractionData::peformSet()");
      }

   } // end of performSet()

   /****************************************************************************
   **
   ** Method:  performSetForObjID
   ** Input:   String token - Array index as a token (String)
   **          CMIRequest theRequest - tokenized LMSSetValue() request
   **          DMErrorManager dmErrorMgr - Error Manager
   ** Output:  none
   **
   ** Description:  This method takes the necessary steps to set the
   **                Objective IDs for the Interaction Data
   **
   ***************************************************************************/
   private void performSetForObjID(String token,
                                   CMIRequest theRequest,
                                   DMErrorManager dmErrorMgr)
   {
      if ( DebugIndicator.ON )
      {
         System.out.println("\t\tCMIInteractionData::performSetForObjID()");
      }

      // Convert the token to an array index
      int arrayIndex = getArrayIndex(token,theRequest,dmErrorMgr);
      String value = theRequest.getValue();

      try
      {
         if ( DebugIndicator.ON )
         {
            System.out.println(
               "\t\tChecking to see if an element already exists");
            System.out.println("\t\tat array position " + arrayIndex);
         }

         Element tmpElement = (Element)objectives.elementAt(arrayIndex);

         if ( DebugIndicator.ON )
         {
            System.out.println("\t\tElement already exists");
            System.out.println("\t\tVector size: " + objectives.size());
         }

         // before setting the type make sure the type is valid
         boolean result = validateType(this,
                                       tmpElement,
                                       "id",
                                       value,
                                       dmErrorMgr);
         if (result)
         {
            // If this does not throw an ArrayOutOfBoundsException
            // an element existed at the array index.  Set the element
            tmpElement.setValue(value);
            objectives.set(arrayIndex,tmpElement);

            if ( DebugIndicator.ON )
            {
               System.out.println("\t\tVector size: " + objectives.size());
            }
         }
         else
         {
            if ( DebugIndicator.ON )
            {
               System.out.println("***** INVALID LMSSetValue() CALL *****");
               System.out.println("          Invalid Type                ");
               System.out.println("Element was NOT set!");
               System.out.println("");
               System.out.println("\t\tVector size: " + objectives.size());
            }
            dmErrorMgr.SetCurrentErrorCode("405");
         }
      }
      catch ( ArrayIndexOutOfBoundsException e)
      {
         if ( DebugIndicator.ON )
         {
            System.out.println("\t\tElement does not already exists");
         }

         if ( arrayIndex <= objectives.size() )
         {
            // Create an element using the request value.
            Element tmpElement =
                  new Element(theRequest.getValue(),
                              "checkIdentifier","NULL",true,false,false);

            // before setting the type make sure the type is valid
            boolean result = validateType(this,
                                       tmpElement,
                                       "id",
                                       theRequest.getValue(),
                                       dmErrorMgr);

            // result was true - valid type - perform set.
            if ( result == true )
            {
               objectives.add(arrayIndex,tmpElement);
               if ( DebugIndicator.ON )
               {
                  System.out.println("\t\tVector size: " + objectives.size());
               }
            }
            else
            {
               if ( DebugIndicator.ON )
               {
                  System.out.println("***** INVALID LMSSetValue() CALL *****");
                  System.out.println("          Invalid Type                ");
                  System.out.println("Element was NOT set!");
                  System.out.println("");
                  System.out.println("\t\tVector size: " + objectives.size());
               }
               dmErrorMgr.SetCurrentErrorCode("405");
            }
         }
         else
         {
            dmErrorMgr.SetCurrentErrorCode("201");
         }

      }
      if ( DebugIndicator.ON )
      {
         System.out.println("\t\tExiting performSetForObjID()");
      }

   }  // end of performSetForObjID()

   /****************************************************************************
   **
   ** Method:  performSetForResponse
   ** Input:   String token - Array index as a token (String)
   **          CMIRequest theRequest - tokenized LMSSetValue() request
   **          DMErrorManager dmErrorMgr - Error Manager
   ** Output:  none
   **
   ** Description:  This method takes the necessary steps to set the
   **                Response for the Interaction Data** Input:   String
   **
   ***************************************************************************/
   private void performSetForResponse(String token,
                                      CMIRequest theRequest,
                                      DMErrorManager dmErrorMgr)
   {
      if ( DebugIndicator.ON )
      {
         System.out.println("\t\tCMIInteractionData::performSetForResponses()");
      }

      // Convert the token to an array index
      int arrayIndex = getArrayIndex(token,theRequest,dmErrorMgr);
      String value = theRequest.getValue();

      try
      {
         if ( DebugIndicator.ON )
         {
            System.out.println(
               "\t\tChecking to see if an element already exists");
            System.out.println("\t\tat array position " + arrayIndex);
         }

         CMIResponse tmpResponse =
            (CMIResponse)correct_responses.elementAt(arrayIndex);

         if ( DebugIndicator.ON )
         {
            System.out.println("\t\tElement already exists");
            System.out.println("\t\tSet the value in the element and replace");
            System.out.println("\t\tVector size: " + correct_responses.size());
         }

         // If this does not throw an ArrayOutOfBoundsException
         // an element existed at the array index.  Set the element
         tmpResponse.performSet(theRequest,dmErrorMgr);
         correct_responses.set(arrayIndex,tmpResponse);

         if ( DebugIndicator.ON )
         {
            System.out.println("\t\tVector size: " + correct_responses.size());
         }
      }
      catch ( ArrayIndexOutOfBoundsException e)
      {
         if ( DebugIndicator.ON )
         {
            System.out.println("\t\tElement does not already exists");
            System.out.println("\t\tVector size: " + correct_responses.size());
         }

         if (arrayIndex <= correct_responses.size() )
         {
            CMIResponse response = new CMIResponse();
            response.performSet(theRequest,dmErrorMgr);
            correct_responses.add(arrayIndex,response);

            if ( DebugIndicator.ON )
            {
               System.out.println("\t\tVector size: " + correct_responses.size());
            }
         }
         else
         {
             dmErrorMgr.SetCurrentErrorCode("201");
         }
      }

      if ( DebugIndicator.ON )
      {
         System.out.println("\t\tExiting performSetForResponses()");
      }

   } // end of performSetForResponse()

   /***************************************************************************
   **
   ** Method:  getArrayIndex
   ** Input:   String token - array index as a string
   **          CMIRequest theRequest - tokenized LMSGetValue() request
   **          DMErrorManager dmErrorMgr - Error Manager
   **
   ** Output:  int - array index from the request
   **
   ** Description:  This method determines the array index based on the
   **               request.
   **
   ***************************************************************************/
   private int getArrayIndex(String token,
                             CMIRequest theRequest,
                             DMErrorManager dmErrorMgr)
   {
      int index=-1;

      // Token has to be an array index
      try
      {
         // Convert the string integer to a number
         Integer tmpInt= new Integer(token);
         index = tmpInt.intValue();
      }
      catch ( NumberFormatException nfe )
      {
         if ( DebugIndicator.ON )
         {
            // Invalid parameter passed to LMSGetValue()
            System.out.println(
               "\t\tError - Data Model Element not implemented");
            System.out.println("\t\tInvalid data model element: " +
                            theRequest.getRequest());
            System.out.println("\t\tInvalid element " +
                            token + ", Array index expected");
         }
         // Notify error manager
         dmErrorMgr.SetCurrentErrorCode("401");
      }

      return index;
   } // end of getArrayIndex()

   /***************************************************************************
   **
   ** Method:  getCount
   ** Input:   CMIRequest theRequest - tokenized LMSGetValue() request
   **
   ** Output:  String - the count of records matching the request
   **
   ** Description:
   **
   ***************************************************************************/
   public String getCount(CMIRequest theRequest,
                          DMErrorManager dmErrorMgr)
   {
       if ( DebugIndicator.ON )
       {
          System.out.println("\t\tCMIInteractionData::getCount()");
       }

       String result = new String("");

       // Get the next token off of the request
       String token = theRequest.getNextToken();

       if ( DebugIndicator.ON )
       {
          System.out.println("\t\tProcessing next token: " + token);
       }

       if ( token.equals("objectives") )
       {
          if ( DebugIndicator.ON )
          {
             System.out.println("\t\tProcessing objectives._count");
          }
          int count = 0;
          count = objectives.size();

          if (DebugIndicator.ON )
          {
             System.out.println("\t\tNumber of objective ids: " + count);
          }
          Integer tmpInt = new Integer(count);
          result = tmpInt.toString();
       }
       else if ( token.equals("correct_responses") )
       {
          if ( DebugIndicator.ON )
          {
             System.out.println("\t\tInteractionData:correct_responses._count");
          }
          int count = 0;
          count = correct_responses.size();

          if (DebugIndicator.ON )
          {
             System.out.println("\t\tNumber of correct_responses: " + count);
          }

          Integer tmpInt = new Integer(count);
          result = tmpInt.toString();
       }
       else
       {
          if ( DebugIndicator.ON )
          {
             // Error -- AU not allowed to call LMSGetValue on launch_data
             System.out.println("\t\tInvalid LMSGetValue() request: " +
                             theRequest.getRequest());

             System.out.println("\t\tSCO is not permitted to call" +
                             " LMSGetValue() for cmi.interactions");
          }

          // Error - no more elements represented in data model
          dmErrorMgr.SetCurrentErrorCode("404");
       }

       if ( DebugIndicator.ON )
       {
          System.out.println("\t\tReturning from getCount() count returning: " +
                              result);
       }

       return result;
   }

}  // end of CMIInteractionData
