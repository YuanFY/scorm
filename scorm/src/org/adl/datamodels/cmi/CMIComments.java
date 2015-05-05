/*******************************************************************************
**
** Filename:  CMIComments.java
**
** File Description:  The CMIComments class manages comments from the AU or
**                    Student. The comments are a collection of comments
**                    concatenated together for a given Assignable Unit (AU).
**
** Author:  ADLI Project
**
** Company Name: Concurrent Technologies Corporation
**
** Module/Package Name:  org.adl.datamodel.cmi
** Module/Package Description:  Collection of CMI Data Model objects
**
** Design Issues:
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
** Date Changed   Author of Change  Reason for Changes
** ------------   ----------------  -------------------------------------------
** 09/22/2000     S. Thropp         PT - 114: Change the way student comments
**                                  get set.  The change to the CMI documentation
**                                  was to get rid of the array of student
**                                  comments and change this to be a concatenated
**                                  string of comments.  Changes were made to
**                                  reflect this change.
**
** 11/15/2000     S. Thropp         PT - 290: With the addition of the new
**                                  cmi.comments_from_lms, this file was cleaned up
**                                  to remove all references to instructorComments.
**                                  File was also cleaned up to make cmi.comments
**                                  both read and write.  Also changed the name of
**                                  the attribute to comments instead of
**                                  studentComments.
**
** 01/28/2002     B. Capone         Modified the setting of the return Error
**                                  Code from 205 to 405 in performSet() if the
**                                  string to be set is greater than the allowed
**                                  length of 4096.
**
*******************************************************************************/
package org.adl.datamodels.cmi;

//native java imports
import java.io.*;
//adl imports
import org.adl.util.debug.*;
import org.adl.datamodels.*;

public class CMIComments extends CMICategory
   implements Serializable
{
   // Student comments
   public Element comments;


   /****************************************************************************
    **
    ** Method:   Constructor
    ** Input:    String comments - Comments from the AU or student
    ** Output:   none
    **
    ** Description: A constructor used to initialize the comments.  This
    **              constructor takes in a String of comments to use for
    **              initialization.
    **
    ***************************************************************************/
   public CMIComments(String commentsString)
   {
      super( true );

      comments = new Element(commentsString,"checkString4096","NULL",
                                     true,true,false);

   } // end of constructor

   /****************************************************************************
    **
    ** Method:   Default Constructor
    ** Input:    none
    ** Output:   none
    **
    ** Description:  Sets up the comments attribute with a default value.
    **
    ***************************************************************************/
   public CMIComments()
   {
      super( true );

      comments =
            new Element("","checkString4096","NULL",true,true,false);

   } // end of default constructor


   /************************************************************************
    **  Accessors to the CMIComments Data.  AUs should not invoke these
    **  methods.  AUs should call LMSGetValue()
    ************************************************************************/
   public Element getComments()
   {
       return comments;
   }

   /************************************************************************
    **  Modifiers to the CMIComments Data.  AUs should not invoke these
    **  methods.  AUs should call LMSSetValue()
    ************************************************************************/
   public void setComments(String inComments)
   {
      comments.setValue(inComments);
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
    **     value for the Comments data model element.
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
         // Error - Data Model Element not implemented
         // *** There are no more subcategories in the CMI Comments class
         // *** Since there are more tokens to process, the data model
         // *** element was not implemented
         if ( DebugIndicator.ON )
         {
            System.out.println("Error - Data Model Element not implemented");
            System.out.println("Element being processed: " +
                            theRequest.getElement() +
                            "is not a valid data model element.\n");
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
         result = comments.getValue();
      }

      // Done getting requested element.  Let CMIRequest object know that
      // processing of the LMSGetValue() is complete
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
    ** Description:  This method performs the necessary steps to set the
    **     Comments Data Model Element.
    **     The comments are collected as a String not to exceen 4096 bytes
    **
    ***************************************************************************/
   public void performSet(CMIRequest theRequest,
                          DMErrorManager dmErrorMgr)
   {

      // Check to see if the Request has more tokens to process
      if ( !theRequest.hasMoreTokensToProcess() )
      {

         // get the value to use for setting off of the request
         String content = theRequest.getValue();

         // Check to make sure that the string to be used to set the comments
         // does not exceed 4096 bytes.
         if ( content.length() <= 4096)
         {
            // get the current student comments
            String currentComments = comments.getValue();

            // Concatenate the comments together
            currentComments += content;

            // set the student comments with the new concatenated
            // comments
            comments.setValue(currentComments);
         }
         else
         {
            if ( DebugIndicator.ON )
            {
               System.out.println("***** INVALID LMSSetValue() CALL *****");
               System.out.println("             Invalid Type             ");
               System.out.println("         Element was not set!         ");
            }
            dmErrorMgr.SetCurrentErrorCode("405");
         }

      }
      else
      {
         // Error - Data Model Element not implemented
         if ( DebugIndicator.ON )
         {
            System.out.println("Error - Data Model Element not implemented");
            System.out.println("Element being processed: " +
                               theRequest.getElement() +
                               "is not a valid data model element.\n");
         }

         dmErrorMgr.recNotImplementedError(theRequest);

      }

      // Done setting requested element.  Let CMIRequest object know that
      // processing of the LMSSetValue() is complete
      theRequest.done();

   } // end of performSet()

} // end of CMIComments

