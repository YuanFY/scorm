/*******************************************************************************
**
** Filename:  CMIScore.java
** File Description:  The CMIScore class manages information indicating
**                    the performance of the student during their last attempt
**                    on the SCO (AU).
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
** 11/15/2000     S.Thropp          PT 263: Removed reference to SCORM version
**
** 12/27/2001     Jeff Falls        changed check call from checkDecimal() to
**                                  checkScoreDecimal() to test the
**                                  "cmi.core.score" elements for negative
**                                  values
**
** 03/20/2002     Bill Capone       Added a "SetCurrentErrorCode" to "0" (zero)
**                                  in performGet().
**
*******************************************************************************/
package org.adl.datamodels.cmi;

//native java imports
import java.io.*;

//adl imports
import org.adl.util.debug.*;
import org.adl.datamodels.*;

public class CMIScore extends CMICategory
   implements Serializable
{
   // Numerical representation of student performance in the SCO
   public Element raw;

   // The minimum score that the student could have achieved
   public Element min;

   // The maxium score or total number that the student could have achieved
   public Element max;


   /****************************************************************************
    **
    ** Method:  Default constructor
    ** Input:   none
    ** Output:  none
    **
    ** Description: Default constructor for the CMIScore
    **
   ****************************************************************************/
   public CMIScore()
   {
      // Set up default values for all attributes

      super( true );
      raw = new Element("","checkScoreDecimal","NULL",true,true,true);
      min = new Element("","checkScoreDecimal","NULL",true,true,true);
      max = new Element("","checkScoreDecimal","NULL",true,true,true);
   }


   /************************************************************************
    **  Accessers to the CMIScore Data.  SCOs should not call these methods.
    **  SCOs should call LMSGetValue()
    ************************************************************************/
   public Element getRaw()
   {
      return raw;
   }
   public Element getMin()
   {
      return min;
   }
   public Element getMax()
   {
      return max;
   }

   /************************************************************************
    **  Modifiers to the CMIScore Data.  SCOs should not call these methods.
    **  SCOs should call LMSSetValue()
    ************************************************************************/
   public void setRaw(String inRaw)
   {
      raw.setValue(inRaw);
   }
   public void setMin(String inMin)
   {
      min.setValue(inMin);
   }
   public void setMax(String inMax)
   {
      max.setValue(inMax);
   }

   public boolean isInitialized()
   {
      boolean flag = false;
      if ( (raw.isInitialized() ||
            min.isInitialized() ||
            max.isInitialized() ) )
      {
         flag = true;
      }

      return flag;
   }

   /****************************************************************************
    **
    ** Method:  isValidRequest
    ** Input:   CMIRequest theRequest - The tokenized request
    **
    ** Output:  boolean flag indicating whether or not the request is
    **          a valid request.
    **
    ** Description:  This method determins if the request is a valid
    **               CMI request.
    **
    ***************************************************************************/
   public boolean isValidRequest(CMIRequest theRequest)
   {
      boolean flag = false;
      String token = theRequest.getNextToken();

      if ( token.equals("_children") )
      {
         flag = true;
      }
      else if ( (token.equals("raw") ) ||
                (token.equals("min") ) ||
                (token.equals("max") ) )
      {
         flag = true;
         if (theRequest.hasMoreTokensToProcess() )
         {
            if ( theRequest.isAChildrenRequest() )
            {
               flag = true;
            }
            else
            {
               flag = false;
            }
         }
      }
      return flag;
   }

   /****************************************************************************
    **
    ** Method:  performGet
    ** Input:   CMIRequest theRequest - The tokenized LMSGetValue() request
    **          DMErrorManager dmErrorMgr - Error manager
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

      // Get the next token off of the request
      String token = theRequest.getNextToken();

      // Check to see if the Request has more tokens to process
      if ( theRequest.hasMoreTokensToProcess() )
      {
         if ( DebugIndicator.ON )
         {
            // Error - Data Model Element not implemented
            // *** There are no more subcategories in the CMI Score class
            // *** Since there are more tokens to process, the data model
            // *** element was not implemented
            System.out.println("Error - Data Model Element not implemented");
            System.out.println("Element being processed: " + token +
                            "is not a valid element of the CMI Score\n" +
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

         if ( theRequest.isAChildrenRequest() )
         {
            result = getChildren();
            dmErrorMgr.SetCurrentErrorCode("0");
         }
         else
         {
            // determine the value associated with the element requested
            result = determineElementValue(this,token,dmErrorMgr);
         }
      }
      return result;
   }


   /****************************************************************************
    **
    ** Method:  getChildren
    ** Input:   none
    ** Output:  String - string representation of the attributes
    **
    ** Description: This method returns the attributes of the class in
    **              string format.
    **
    ***************************************************************************/
   public String getChildren()
   {
      String children = "raw,min,max";

      return children;
   }

   /****************************************************************************
    **
    ** Method:  performSet
    ** Input:   CMIRequest theRequest - The tokenized LMSSetValue() request
    **          DMErrorManager dmErrorMgr - Error manager
    ** Output:  none
    **
    ** Description:  This method performs the LMSSetValue() request.
    **
    ***************************************************************************/
   public void performSet(CMIRequest theRequest,
                          DMErrorManager dmErrorMgr)
   {
      // Get the next token off of the request
      // raw, max, or min
      String token = theRequest.getNextToken();

      // Check to see if the request has any more tokens to
      // process
      if ( theRequest.hasMoreTokensToProcess() )
      {
         if ( DebugIndicator.ON )
         {
            // More tokens to process -- Error

            // Error - Data Model Element not implemented
            System.out.println("Error - Data Model Element not implemented");
            System.out.println("Element being processed: " + token +
                            "is not a valid element of the CMI Score\n" +
                            "Data Model Category");
         }
         // Notify error manager
         dmErrorMgr.recNotImplementedError(theRequest);
      }
      else
      {
         //  No more tokens to process

         // Get the value to use for setting off of the request
         String value = theRequest.getValue();

         if ( theRequest.isAKeywordRequest() == false )
         {
            // Set the value
            doSet(this,token,value,dmErrorMgr);
         }
         else
         {
            if ( DebugIndicator.ON )
            {
               // Error - Data Model Element is not writeable
               // *** Trying to set the _children element.
               System.out.println("Error - Cannot Set Data Model Element");
               System.out.println("Element being processed: " + token +
                               "cannot be set\n");
            }

            // Notify error manager
            dmErrorMgr.recKeyWordError(token);

         }
      }
   }  // end of performSet

} // end of CMIScore
