/*******************************************************************************
**
** Filename:  CMIStudentData.java
**
** File Description:  The CMIStudentData class manages information to support
**                    customization of an SCO based on a
**                    student's performance.
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
** 08/07/2000     ADLI Team         Made the attempt_record and tries attributes
**                                  public.  In order for the Java Reflection
**                                  API to function correctly, these attributes
**                                  had to be public.
**
** 10/31/2000    R. Ball            PT 295: SCORM 1.1 Changes from Slash-n-Burn
**                                  All elements were removed except for:
**                                  mastery_score, max_time_allowed, and
**                                  time_limit_action.
**
*******************************************************************************/
package org.adl.datamodels.cmi;

//native java imports
import java.io.*;
//adl imports
import org.adl.util.debug.*;
import org.adl.datamodels.*;

public class CMIStudentData extends CMICategory
implements Serializable
{
    // The passing score, as determined outside the SCO
    public Element mastery_score;

    // The amount of time the student is allowed to have in the current
    // attempt of the SCO
    public Element max_time_allowed;

    // What the SCO is to do when the max time allowed is exceeded
    public Element time_limit_action;

    private static int CMISTUD_DATA_MIN_NUM_TOKENS = 3;

    /**************************************************************************
     **
     ** Method:  Default constructor
     ** Input:   none
     ** Output:  none
     **
     ** Description:  The default constructor sets up the class and initalizes
     **               all of its attributes
     **               Parameters passed into Element:
     **                   <value>,<type>,<writeable>,<readable>,<mandatory>
     **
     **               See Element Constructor for more detail
     **************************************************************************/
    public CMIStudentData()
    {
        // Set up default values for all attributes
        super( true );
        mastery_score = new Element("","checkDecimal","NULL",false,true,false);
        max_time_allowed =
           new Element("","checkTimespan","NULL",false,true,false);
        time_limit_action = new Element("","checkVocabulary","TimeLimitAction",
                                        false,true,false);

    }  // end of defaul constructor

    /************************************************************************
     **  Accessers to the CMIStudentData Data.  AUs should not call these
     **  methods.  AUs should call LMSGetValue()
     ************************************************************************/
    public Element getMasteryScore()
    {
        return mastery_score;
    }
    public Element getMaxTimeAllowed()
    {
        return max_time_allowed;
    }
    public Element getTimeLimitAction()
    {
        return time_limit_action;
    }

    /************************************************************************
     **  Modifiers to the CMIStudentData Data.  AUs should not call these
     **  methods.  AUs should call LMSSetValue().
     ************************************************************************/
    public void setMasteryScore(String inMasteryScore)
    {
        mastery_score.setValue(inMasteryScore);
    }
    public void setMaxTimeAllowed(String inMaxTimeAllowed)
    {
        max_time_allowed.setValue(inMaxTimeAllowed);
    }
    public void setTimeLimitAction(String inTimeLimitAction)
    {
        time_limit_action.setValue(inTimeLimitAction);
    }

    /****************************************************************************
     **
     ** Method:  performGet
     ** Input:   CMIRequest theRequest - tokenized LMSGetValue() request
     **          DMErrorManager dmErrorMgr - Error manager
     **
     ** Output:  String - the value portion of the element for the LMSGetValue()
     **
     ** Description:  This method performs the LMSGetValue() request. The method
     **    determines the value for the requested data element.  The method
     **    handles the cases where there are additional sub-classes
     **    (subcategories) for the CMIStudent Data element.
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
                           "is not a valid element of the CMI Student Data\n" +
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
     ** Output:  String
     **
     ** Description:
     ** This method returns a String containing the list of data elements that
     ** CMI Score data object contains.
     **
     ***************************************************************************/
    public String getChildren()
    {
        String children = "mastery_score,max_time_allowed,time_limit_action";

        return children;
    }

    /***************************************************************************
    **
    ** Method:  performSet
    ** Input:   CMIRequest theRequest - tokenized LMSSetValue() request
    **          DMErrorManager dmErrorMgr - Error manager
    **
    ** Output:  none
    **
    ** Description:  This method performs all of the necessary steps to process
    **               an LMSSetValue() request.
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

    /***************************************************************************
     **
     ** Method:  isValidStudDataRequest
     ** Input:   CMIRequest theRequest - the LMSGetValue() request
     **
     ** Output:  Boolean value indicating whether or not the StudentData Request
     **          is valid.
     **
     ** Description:  Checks to make sure that the request meets the minimum
     **               requirements needed for a CMIStudentData Model Element.
     **
     **************************************************************************/
    private boolean isValidStudDataRequest(CMIRequest theRequest)
    {
        boolean rtrnFlag = false;

        if(theRequest.isForASetRequest())
        {
            if((theRequest.getTotalNumTokens() - 1) >=
               CMISTUD_DATA_MIN_NUM_TOKENS)
            {
                rtrnFlag = true;
            }
        }
        else
        {
            if(theRequest.getTotalNumTokens() >= CMISTUD_DATA_MIN_NUM_TOKENS)
            {
                rtrnFlag = true;
            }
        }

        return rtrnFlag;

    } // end of isValidStudDataRequest()

} // end of CMIStudentData
