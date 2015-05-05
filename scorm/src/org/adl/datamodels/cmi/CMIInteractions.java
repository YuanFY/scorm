/*******************************************************************************
**
** Filename:  CMIInteractions.java
**
** File Description:  The CMIInteractions class manages all of the Interaction
**                    CMI Data Model elements
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
** 09/28/2000     ADLI Project      PT116 : The method "getChildren()" was
**                                  changed to return "correct_responses"
**                                  instead of "response".
**
** 11/15/2000     S. Thropp         PT 292: Change the error code in
**                                  performGet() to be a 404 element is write
**                                  only.
**
** 01/17/2001     S. Thropp         PT374: Added more debug statments.
**                                  Removed all references to hardcoded arrays
**                                  and changed code to use Vectors.
**
** 05/15/2002     B. Capone         PT1976: Modified SetCurrentErrorCode for
**                                  ArrayIndexOutOfBoundsException from 405 to
**                                  201 in the function performSet().
**
*******************************************************************************/
package org.adl.datamodels.cmi;

//native java imports
import java.io.*;
import java.util.*;

//adl imports
import org.adl.util.debug.*;

public class CMIInteractions extends CMICategory
   implements Serializable
{
   // A recognized and recordable input or group of inputs from the
   // student to the computer
   public Vector interactions;

   /****************************************************************************
    **
    ** Method:  CMIInteractions
    ** Input:   none
    ** Output:  none
    **
    ** Description:  Default constructor
    **
    **
    ***************************************************************************/
   public CMIInteractions()
   {
      super( false );
      interactions = new Vector();

   }  // end of default constructor


   /************************************************************************
    **  Accessers to the CMIInteraction Data.  SCOs should not invoke these
    **  methods.  SCOs should call LMSGetValue()
    ************************************************************************/
   public Vector getInteractions()
   {
      return interactions;
   }

   /************************************************************************
    **  Modifiers to the CMIInteraction Data.  SCOs should not invoke these
    **  methods.  SCOs should call LMSSetValue().
    ************************************************************************/
   public void setInteractions(CMIInteractionData inInteractions,
                               int index)
   {
      interactions.add(index,inInteractions);

   }  // end of setInteractions()


   /****************************************************************************
    **
    ** Method:  getChildren
    ** Input:   none
    ** Output:  String representation of the elements of the CMI Interactions
    **          category
    **
    ** Description:
    ** This method returns a String containing the list of data elements that
    ** CMI Score data object contains.
    **
    ***************************************************************************/
   public String getChildren()
   {
      String children =
         "id,objectives,time,type,correct_responses,weighting," +
         "student_response,result,latency";

      return children;
   }  // end of getChildren()

   /**************************************************************************
   **
   ** Method:  performSet
   ** Input:   CMIRequest theRequest - tokenized LMSSetValue() request.
   **          DMErrorManager dmErroMgr - Error manager
   ** Output:  none
   **
   ** Description: This method performs all the necessary steps for an
   **              LMSSetValue() request.
   **
   ***************************************************************************/
   public void performSet(CMIRequest theRequest,
                          DMErrorManager dmErrorMgr)
   {
      if ( DebugIndicator.ON )
      {
         System.out.println("\tIn CMIInteractions::performSet()");
      }

      // Get the next token off of the request
      String token = theRequest.getNextToken();

      if ( DebugIndicator.ON )
      {
         System.out.println("\tToken being processed: " + token);
      }

      if ( theRequest.isAKeywordRequest() )
      {
         // record a keyword error
         dmErrorMgr.recKeyWordError(token);
      }
      else
      {
         // The next token must be an array.  If not throw
         // an exception for this request.
         int index = -1;

         // Check to see if next token is an array index.  For
         // a LMSSetValue() this is true
         try
         {
            // Try to convert the token to the index
            Integer tmpInt = new Integer(token);
            index = tmpInt.intValue();

            // Get the Interaction Data at position of the index
            CMIInteractionData tmpInteraction =
               (CMIInteractionData)interactions.elementAt(index);

            // An interaction existed at the given index.
            // Invoke the performSet() on the Interaction Data
            tmpInteraction.performSet(theRequest,dmErrorMgr);

            // replace the old Interaction Data with the newly set
            // Interaction
            interactions.set(index,tmpInteraction);
         }
         catch ( NumberFormatException nfe )
         {
            if ( DebugIndicator.ON )
            {
               // Invalid parameter passed to LMSSetValue()
               System.out.println(
                  "\tError - Data Model Element not implemented");
               System.out.println("\tInvalid data model element: " +
                               theRequest.getRequest() +
                               " passed to LMSSetValue()");
            }
            // Notify error manager
            dmErrorMgr.recNotImplementedError(theRequest);
         }
         catch ( ArrayIndexOutOfBoundsException e)
         {
            if ( DebugIndicator.ON )
            {
               System.out.println(
                "\tFirst time setting the interaction data at the given index");
            }

            if ( index <= interactions.size() )
            {
               // Create a new Interaction Data object
               CMIInteractionData intData = new CMIInteractionData();

               // Invoke the performSet() on the Interaction Data
               intData.performSet(theRequest,dmErrorMgr);

               // Place the Interaction Data into the vector at the index position
               interactions.add(index,intData);
            }
            else
            {
                dmErrorMgr.SetCurrentErrorCode("201");
            }
         }
      }

      // Done processing.  Let the CMIRequest object know that processing
      // of the LMSSetValue() is complete
      theRequest.done();

      if ( DebugIndicator.ON )
      {
         System.out.println("\tCMIInteractions - performSet() exit");
      }

      return;
   } // end of performSet


   /****************************************************************************
    **
    ** Method:  performGet
    ** Input:   CMIRequest theRequest - tokenized LMSSetValue() request
    **          DMErrorManager dmErrorMgr - Error manager
    **
    ** Output:  String - empty
    **
    ** Description:  The LMSGetValue() request is not allowed to be invoked
    **               by an SCO.  Notify the operator
    **
    ***************************************************************************/
   public String performGet(CMIRequest theRequest,
                            DMErrorManager dmErrorMgr)
   {
      String result = new String("");
      int tokenCount = theRequest.getTotalNumTokens();

      if ( DebugIndicator.ON )
      {
         System.out.println("\tCMIInteractions::performGet()");
      }

      // Determine the type of request and process accordingly
      if ( ( tokenCount == 3 ) &&
           ( theRequest.isAChildrenRequest() ) )
      {
         if ( DebugIndicator.ON )
         {
            System.out.println("\tProcessing a _children request");
         }

         result = getChildren();
      }
      else if ( theRequest.isACountRequest() )
      {
         if ( DebugIndicator.ON )
         {
            System.out.println("\tProcessing a _count request");
         }

         // Determine the type of _count request
         if (tokenCount == 3)
         {
            // _count request for CMIInteractions
            int count = howManyInteractions();
            Integer tmpInt = new Integer(count);
            result = tmpInt.toString();
         }
         else
         {
            // _count request for CMIInteractionData
            // Get the next token off of the request
            String token = theRequest.getNextToken();

            if ( DebugIndicator.ON )
            {
               System.out.println("\tProcessing next token: " + token);
            }

            try
            {
               Integer tmpInt = new Integer(token);
               int index = tmpInt.intValue();

               // Get the Objective Data that is positioned at the input index
               CMIInteractionData intData =
                      (CMIInteractionData)interactions.elementAt(index);

               if ( intData.isInitialized() )
               {
                  result = intData.getCount(theRequest,dmErrorMgr);
               }
               else
               {
                  String nextToken = theRequest.getNextToken();

                  if ( nextToken.equals("objectives") ||
                       nextToken.equals("correct_responses") )
                  {
                     result = "0";
                  }
                  else
                  {
                     dmErrorMgr.recGetKeyWordError(theRequest.getElement());
                  }
               }
            }
            catch ( NumberFormatException nfe )
            {
               if ( DebugIndicator.ON )
               {
                 // Invalid parameter passed
                 System.out.println("\tError - Invalid LMSGetValue() request");
               }
               // Notify error manager
               dmErrorMgr.recNotImplementedError(theRequest);
            }
            catch ( ArrayIndexOutOfBoundsException e)
            {
               String nextToken = theRequest.getNextToken();

               if ( ( tokenCount == 5) &&
                    ( ( nextToken.equals("objectives") ||
                        nextToken.equals("correct_responses") ) ) )
               {
                  result = "0";
               }
               else
               {
                  dmErrorMgr.recGetKeyWordError(theRequest.getElement());
                  result = "";
               }
            }

         }
      }
      else
      {
         if ( DebugIndicator.ON )
         {
            // Error -- SCO not allowed to call LMSGetValue on launch_data
            System.out.println("\tInvalid LMSGetValue() request: " +
                            theRequest.getRequest());

            System.out.println("\tSCO is not permitted to call" +
                            " LMSGetValue() for cmi.interactions");
         }

         dmErrorMgr.SetCurrentErrorCode("404");
      }

      if ( DebugIndicator.ON )
      {
         System.out.println("\tReturning: " + result + " from CMIInteractions");
         System.out.println("\tCMIInteractions::performGet() exit");
      }

      // Done processing.  Let CMIRequest object know the processing
      // of the LMSGetValue() is done.
      theRequest.done();

      return result;
  }

   /****************************************************************************
    **
    ** Method:  howManyInteractions
    ** Input:   none
    ** Output:  int - number of interactions implemented
    **
    ** Description:  Calculates the number of interactions that have been
    **               implemented.
    **
    ***************************************************************************/
   private int howManyInteractions()
   {
      int count = 0;

      // must be asking for number of interactions
      count = interactions.size();

      return count;
   }

}  // end of CMIInteractions
