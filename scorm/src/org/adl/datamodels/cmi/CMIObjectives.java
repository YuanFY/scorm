/*******************************************************************************
**
** Filename:  CMIObjectives.java
**
** File Description:  The CMIObjectives class manages the objective data for
**                    an Sharable Content Objects (SCOs).
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
** 11/15/2000     R. Ball           PT 293: SCORM 1.1 Changes, changed the
**                                  getChildren() function to return the correct
**                                  children.
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

public class CMIObjectives extends CMICategory
                           implements Serializable

{
   // An array of CMIObjectiveData for the SCO
   public Vector objectives;


   // The minimum number of tokens in a valid objectives Data Model Element
   // request
   static int CMIOBJ_MIN_NUM_TOKENS = 3;

   /****************************************************************************
    **
    ** Method:  Default constructor
    ** Input:   none
    ** Output:  none
    **
    ** Description: Default constructor
    **
    ***************************************************************************/
   public CMIObjectives()
   {
      super( true );

      // Create the vector to hold the objectives.
      objectives = new Vector();

   } // end of Default constructor

   /************************************************************************
    **  Accessors to the CMIObjective Data.  SCOs should not invoke these
    **  methods.   SCOs should call LMSGetValue()
    ************************************************************************/
   public Vector getObjectives()
   {
      return objectives;
   }

   /************************************************************************
    **  Modifiers to the CMIObjective Data.  SCOs should not invoke these
    **  methods.  SCOs should call LMSSetValue()
    ************************************************************************/
   public void setObjectives(CMIObjectiveData objective,
                             int index)
   {
      objectives.add(index,objective);
   }  // end of setObjectives

   /****************************************************************************
    **
    ** Method:  performGet
    ** Input:   CMIRequest theRequest - tokenized LMSGetValue() request
    **          DMErrorManager dmErrorMgr - Error manager
    **
    ** Output:  String - the value portion of the element for the LMSGetValue()
    **
    ** Description: This method takes the necessary steps to process
    **              an LMSGetValue() request
    **
    ***************************************************************************/
   public String performGet(CMIRequest theRequest,
                            DMErrorManager dmErrorMgr)
   {
      if ( DebugIndicator.ON )
      {
         System.out.println("CMIObjectives::performGet()");
      }
      // Resultant value to return
      String result = new String("");

      // Check to make sure this is a valid LMSGetValue() request
      if ( isValidObjRequest(theRequest) == true )
      {
         // Get the next token off of the request
         String token = theRequest.getNextToken();

         if ( DebugIndicator.ON )
         {
            System.out.println("Token being processed: " + token);
         }

         // Check to see if the Request has more tokens to process
         if ( theRequest.hasMoreTokensToProcess() )
         {
            // Token has to be an array index
            try
            {
               // Convert the string integer to a number
               Integer tmpInt = new Integer(token);
               int indexOfArr = tmpInt.intValue();

               try
               {
                  // Get the Objective Data that is positioned at the input index
                  CMIObjectiveData objData =
                      (CMIObjectiveData)objectives.elementAt(indexOfArr);

                  // Invoke the performGet on the Objective Data returned.
                  result = objData.performGet(theRequest,dmErrorMgr);
               }
               catch (ArrayIndexOutOfBoundsException e)
               {
                  if ( DebugIndicator.ON )
                  {
                     System.out.println("Element does not exist at the given index");
                     System.out.println("Index: " + indexOfArr );
                  }
                  // if element has not been initialized, return Invalid
                  // Argument Error
                  dmErrorMgr.SetCurrentErrorCode("201");
               }
            }
            catch ( NumberFormatException nfe )
            {
               if ( DebugIndicator.ON )
               {
                  // Invalid parameter passed to LMSGetValue()
                  System.out.println("Error - Data Model Element not implemented");
                  System.out.println("Invalid data model element: " +
                                  theRequest.getRequest() +
                                  " passed to LMSGetValue()");
                  System.out.println("Array index required");
               }
               // Notify error manager
               dmErrorMgr.SetCurrentErrorCode("401");
            }
         }
         else
         {
            // No more tokens to process

            // Check to see if the request is for the children of Core Data
            if ( theRequest.isAChildrenRequest() )
            {
               // Set result to the string of children
               result = getChildren();
            }
            else if ( theRequest.isACountRequest() )
            {
               int count = 0;

               count = objectives.size();

               System.out.println("Count: " + count);
               Integer tmpInt = new Integer(count);
               result = tmpInt.toString();
            }
            else
            {
               if ( DebugIndicator.ON )
               {
                  System.out.println("Error - Data Model Element not implemented");
                  System.out.println("Invalid request: " +
                                     theRequest.getRequest());
               }
               dmErrorMgr.recNotImplementedError(theRequest);
            }
         }
      }
      else
      {
         if ( DebugIndicator.ON )
         {
            // Error - Data Model Element not implemented
            System.out.println("Error - Data Model Element not implemented");
            System.out.println("Invalid request: " + theRequest.getRequest());
         }

         dmErrorMgr.SetCurrentErrorCode("401");
      }

      // Done processing.  Let CMIRequest object know the processing
      // of the LMSGetValue() is done.
      theRequest.done();

      if ( DebugIndicator.ON )
      {
         System.out.println("Returning from CMIObjectives::performGet()");
         System.out.println("Value returned: " + result);
      }
      return result;
   }  // end of performGet()


   /****************************************************************************
    **
    ** Method:  getChildren
    ** Input:   none
    ** Output:  String representation of the data attributes of the objectives
    **          Data Model
    **
    ** Description:
    **    This method returns a String containing the list of data elements
    **    that CMI Objectives data object contains.
    **
    ***************************************************************************/
   public String getChildren()
   {
      String children = "id,score,status";

      return children;
   } // end of getChildren()

   /***************************************************************************
   **
   ** Method:  performSet
   ** Input:   CMIRequest theRequest - tokenized LMSSetValue() request
   **          DMErrorManager dmErroMgr - Error Manager
   ** Output:  none
   **
   ** Description:  This method takes the necessary steps to process
   **               an LMSSetValue() request
   **
   ***************************************************************************/
   public void performSet(CMIRequest theRequest,
                          DMErrorManager dmErrorMgr)
   {

      if ( DebugIndicator.ON )
      {
         System.out.println("CMIObjectives::performSet()");
      }

      // The next token must be an array.  If not throw
      // an exception for this request.
      int index = -1;

      // Get the next token off of the request
      String token = theRequest.getNextToken();

      if ( DebugIndicator.ON )
      {
         System.out.println("Token being processed: " + token);
      }

      // Check to see if next token is an array index.  For
      // a LMSSetValue() this is true
      try
      {
         // Try to convert the token to the index
         Integer tmpInt = new Integer(token);
         index = tmpInt.intValue();

         // Get the Objective Data at position of the index
         CMIObjectiveData tmpObj =
            (CMIObjectiveData)objectives.elementAt(index);

         // An objective existed at the given index.
         // Invoke the performSet() on the Objective Data
         tmpObj.performSet(theRequest,dmErrorMgr);

         // replace the old ObjectiveData with the newly set Objective
         // Data.
         objectives.set(index,tmpObj);

      }
      catch ( NumberFormatException nfe )
      {
         if ( theRequest.isAKeywordRequest() == true)
         {
            dmErrorMgr.recKeyWordError(token);
         }
         else
         {
            if ( DebugIndicator.ON )
            {
               // Invalid parameter passed to LMSSetValue()
               System.out.println("Error - Data Model Element not implemented");
               System.out.println("Invalid data model element: " +
                               theRequest.getRequest() +
                               " passed to LMSSetValue()");
            }
            // Notify error manager
            dmErrorMgr.recNotImplementedError(theRequest);
         }
      }
      catch ( ArrayIndexOutOfBoundsException e)
      {
         if ( DebugIndicator.ON )
         {
            System.out.println("First time setting the Objective Data");
         }

         if ( index <= objectives.size() )
         {
            // A new Objective Data.
            CMIObjectiveData objData = new CMIObjectiveData();

            // Invoke performSet() on the new Objective Data
            objData.performSet(theRequest,dmErrorMgr);

           // Place the Objective data into the vector at the
           // index position
           objectives.add(index,objData);
         }
         else
         {
             dmErrorMgr.SetCurrentErrorCode("201");
         }
      }

      // Done processing.  Let CMIRequest object know the processing
      // of the LMSGetValue() is done.
      theRequest.done();

      return;

   } // end of performSet


   /****************************************************************************
     **
     ** Method:  isValidObjRequest
     ** Input:   CMIRequest theRequest - the LMSGetValue() request
     **
     ** Output:  Boolean value indicating whether or not the Objectives Request
     **          is valid.
     **
     ** Description:  Checks to make sure that the request meets the minimum
     **               requirements needed for a CMIObjectives Model Element.
     **
     ***************************************************************************/
   private boolean isValidObjRequest(CMIRequest theRequest)
   {
      boolean rtrnFlag = false;

      if ( theRequest.getTotalNumTokens() >= CMIOBJ_MIN_NUM_TOKENS )
      {
         rtrnFlag = true;
      }

      return rtrnFlag;

   } // end of isValidObjRequest()

}  // end of CMIObjectives
