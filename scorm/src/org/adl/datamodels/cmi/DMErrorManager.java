/*******************************************************************************
**
** Filename:  DMErrorManager.java
**
** File Description: This class implements the error handling capabilities
**                   of the Data Model. It manages the error codes set by
**                   any data model class.
**
** Author: ADLI Project
**
** Company Name: Concurrent Technologies Corporation
**
** Module/Package Name: org.adl.datamodel.cmi
** Module/Package Description: Collection of CMI Data Model objects
**
** Design Issues:
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
**  doesnot utilize the software in a manner which is disparaging to CTC.
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
** OR INABILITY TO USE SOFTWARE, EVEN IF CTC  HAS BEEN ADVISED OF THE
** POSSIBILITY OF SUCH DAMAGES.
**
*******************************************************************************
**
** Date Changed   Author            Reason for Changes
** ------------   ----------------  -------------------------------------------
** 11/15/2000     S. Thropp         PT 292: Clean up of error codes to match
**                                  changes to CMI001 revision 3.4
**
** 12/28/2000     J. Poltrack       PT 292: Mapped the error code descriptions
**                                  to the descriptions in SCORM 1.1
**
** 08/27/2001     J. Falls          1) Changed "can not" to "cannot".
**                                  2) Changed "key word" to "keyword".
**
** 03/20/2002     B. Capone         Removed the "void" from the Constructor.
**
*******************************************************************************/
package org.adl.datamodels.cmi;

//adl imports
import org.adl.util.debug.*;

public class DMErrorManager
{

   private static String currentErrorCode;

   private static String[][] errors =
   {
      {"0", "No Error",
         "The previous LMS API Function call completed successfully."},
      {"101", "General Exception",
         "An unspecified, unexpected exception has occurred."},
      {"201", "Invalid argument error", ""},
      {"202", "Element cannot have children", ""},
      {"203", "Element not an array - cannot have count", ""},
      {"301", "Not initialized", "The LMS is not initialized."},
      {"401", "Not implemented error",
         "The data model element in question was not implemented."},
      {"402", "Invalid set value, element is a keyword",
         "Trying to set a reserved keyword in the data model.  " +
         "Trying to set a keyword (_count, _children, or _version).  " +
         "This is prohibited."},
      {"403", "Element is read only",
         "Data Element is Read Only (Not Writeable).  "+
         "Cannot call LMSSetValue() for the element in question."},
      {"404", "Element is write only",
         "Data Element is Write Only (Not Readable).  "+
         "Cannot call LMSGetValue() for the element in question."},
      {"405", "Incorrect Data Type",
         "Invalid Type being used for setting element.  "+
         "The type being used as the set value argument does not match" +
         " that of the element being set."  }
   };

   /*****************************************************************************
   **
   ** Method:  DMErrorManager()
   ** Input:   none
   ** Output:  none
   **
   ** Description:
   ** Default Constructor - intializes the current error code to "No Error".
   **
   *****************************************************************************/
   public DMErrorManager()
   {
      currentErrorCode = "0";
   }

   /*****************************************************************************
   **
   ** Method:  recNotImplementedError()
   ** Input:   CMIRequest theRequest: the request being processed
   ** Output:  none
   **
   ** Description:
   **   Records the appropriate Not Implemented error
   *****************************************************************************/
   public void recNotImplementedError(CMIRequest theRequest)
   {
      if ( theRequest.isAChildrenRequest() )
      {
         if ( DebugIndicator.ON )
         {
            System.out.println(
               "Trying to get/set an _children element.\n  This" +
               " keyword is not permitted to be implemented on" +
               " the given element");
         }

         SetCurrentErrorCode("401");
      }
      else if ( theRequest.isACountRequest() )
      {
         if ( DebugIndicator.ON )
         {
            System.out.println("Trying to get/set an _count element.\n  This" +
                            " keyword is not permitted to be implemented on" +
                            " the given element");
         }
         SetCurrentErrorCode("401");
      }
      else
      {
         // Notify error manager
         SetCurrentErrorCode("401");
      }

   }

   /*****************************************************************************
   **
   ** Method:  recKeyWordError()
   ** Input:   String inElement: element being processed
   ** Output:  none
   **
   ** Description:
   **   Determines which keyword (_children, _count, _version) is being processed
   **   and sets the correct error code
   **
   *****************************************************************************/
   public void recKeyWordError(String inElement)
   {
      if ( inElement.equals("_children") )
      {
         if ( DebugIndicator.ON )
         {
            System.out.println("Trying to set an _children element.  This" +
                            " action is not permitted!");
         }
         SetCurrentErrorCode("402");
      }
      else if ( inElement.equals("_count") )
      {
         if ( DebugIndicator.ON )
         {
            System.out.println("Trying to set an _count element.  This" +
                            " action is not permitted!");
         }
         SetCurrentErrorCode("402");
      }
      else
      {
         if ( DebugIndicator.ON )
         {
            System.out.println("Trying to set an _version element.  This" +
                            " action is not permitted!");
         }
         SetCurrentErrorCode("402");
      }

   }

   /*****************************************************************************
   **
   ** Method:  recGetKeyWordError()
   ** Input:   String inElement: element being processed
   ** Output:  none
   **
   ** Description:
   **   Determines which keyword (_children, _count, _version) is being processed
   **   and sets the correct error code
   **
   *****************************************************************************/
   public void recGetKeyWordError(String inElement)
   {
      if ( inElement.equals("_children") )
      {
         if ( DebugIndicator.ON )
         {
            System.out.println("Trying to get an _children element.  This" +
                            " action is not permitted!");
         }
         SetCurrentErrorCode("202");
      }
      else if ( inElement.equals("_count") )
      {
         if ( DebugIndicator.ON )
         {
            System.out.println("Trying to get an _count element.  This" +
                            " action is not permitted!");
         }
         SetCurrentErrorCode("203");
      }
      else
      {
         if ( DebugIndicator.ON )
         {
            System.out.println("Trying to get an _version element.  This" +
                            " action is not permitted!");
         }
         SetCurrentErrorCode("401");
      }

   }

   /*****************************************************************************
   **
   ** Method:  GetCurrentErrorCode()
   ** Input:   none
   ** Output:  String - current error code
   **
   ** Description:
   ** Returns the value of the Current Error Code that was set by the most
   ** recent API call.
   **
   *****************************************************************************/
   public String GetCurrentErrorCode()
   {
      return currentErrorCode;
   }

   /*****************************************************************************
   **
   ** Method:  SetCurrentErrorCode(String code)
   ** Input:   String code - The error code (from the predefined list of codes)
   ** Output:  none
   **
   ** Description:
   ** Sets the value of the Current Error Code.
   **
   *****************************************************************************/
   public void SetCurrentErrorCode(String code)
   {
      currentErrorCode = code;
   }

   /*****************************************************************************
   **
   ** Method:  ClearCurrentErrorCode()
   ** Input:   none
   ** Output:  none
   **
   ** Description:
   ** Sets the value of the Current Error Code to "No Error".
   **
   *****************************************************************************/
   public void ClearCurrentErrorCode()
   {
      currentErrorCode = errors[0][0];
   }


   /*****************************************************************************
   **
   ** Method:  GetErrorDescription(String code)
   ** Input:   String code - error code
   ** Output:  String - the description associated with the given error code
   **
   ** Description:
   ** Retrieves and returns the text associated with the given error code
   **
   *****************************************************************************/
   public String GetErrorDescription(String code)
   {
      // retrieves and returns the description of the provided error code
      return GetErrorElement(code)[1];
   }

   /*****************************************************************************
   **
   ** Method:  GetErrorDiagnostic(String code)
   ** Input:   String code - error code
   ** Output:  String - the diagnostic text associated with the given error code
   **
   ** Description:
   ** Retrieves and returns the diagnostic text associated with the error code
   **
   *****************************************************************************/
   public String GetErrorDiagnostic( String code )
   {
      return GetErrorElement(code)[2];
   }

   /*****************************************************************************
   **
   ** Method:  GetErrorElement(String code)
   ** Input:   String code - error code
   ** Output:  String[] - the array corresponding to the element identified by
   **                     the given error code
   **
   ** Description:
   ** Private function that gets the array containing the error element info
   ** identified by the given error code
   **
   *****************************************************************************/
   private String[] GetErrorElement(String code)
   {
      for ( int i=0; i<errors.length; i++ )
      {
         if ( errors[i][0].equalsIgnoreCase(code) == true )
            return errors[i];
      }

      String[] tmp = {"","",""};
      return tmp;

   }

   /*****************************************************************************
   **
   ** Method:  isValidErrorCode()
   ** Input:   String errorCode
   ** Output:  boolean indicating whether or not the error code is valid
   **
   ** Description:
   **   Determines whether or not the Error Code passed in is a valid SCORM
   **   error code.
   **
   *****************************************************************************/
   public boolean isValidErrorCode(String errorCode)
   {
      boolean result = false;
      int i = 0;

      while ( !result && (i < errors.length) )
      {
         if ( errors[i][0].equalsIgnoreCase(errorCode) == true )
         {
            result = true;
         }
         i++;
      }

      return result;

   }
}
