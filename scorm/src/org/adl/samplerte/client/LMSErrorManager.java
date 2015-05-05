/*******************************************************************************
** 
** Filename:  LMSErrorManager.java
**
** File Description:     
**
** This class implements the error handling capabilities of the RTE API.  It
** manages the error codes set by the API functions in the API Adapter Applet.
** For purposes of this example, this class uses a hardcoded array to store 
** the error mapping.
**
** Author: ADL Technical Team
**
** Contract Number:
** Company Name: CTC
**
** Module/Package Name:  org.adl.lms.client
** Module/Package Description:  Client side Sample RTE classes
**
** Design Issues:
**
** Implementation Issues:
** Known Problems:
** Side Effects:
**
** References: ADL SCORM
**
/*******************************************************************************
**
** Concurrent Technologies Corporation (CTC) grants you ("Licensee") a non-
** exclusive, royalty free, license to use, modify and redistribute this
** software in source and binary code form, provided that i) this copyright
** notice and license appear on all copies of the software; and ii) Licensee
**  does not utilize the software in a manner which is disparaging to CTC.
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
*******************************************************************************/

package org.adl.samplerte.client;

public class LMSErrorManager
{

   private static String currentErrorCode;

   private static String[][] errors =
   {
      {"0", "No Error",
         "The previous LMS API Function call completed successfully."},
      {"101", "General Exception", 
         "An unspecified, unexpected exception has occured"},
      {"201", "Invalid argument error", ""},
      {"202", "Element cannot have children", ""},
      {"203", "Element not an array - cannot have count", ""},
      {"301", "Not initialized", "The LMS is not initialized."},
      {"401", "Not implemented error",
         "The data model element in question was not implemented"},
      {"402", "Invalid set value, element is a keyword",
         "Trying to set a reserved keyword in the data model" +
         "Trying to set a keyword (_count, _children, or _version) " +
         "This is prohibited"},
      {"403", "Element is read only",
         "Data Element is Read Only (Not Writeable)"+
         "Cannot call LMSSetValue() for the element in question"},
      {"404", "Element is write only",
         "Data Element is Write Only (Not Readable)"+
         "Cannot call LMSGetValue() for the element in question"},
      {"405", "Incorrect Data Type",
         "Invalid Type being used for setting element"+
         "The type being used as the set value argument does not match" +
         " that of the element being set"}
   };

   /****************************************************************************
   **
   ** Method:  LMSErrorManager()
   ** Input:   none
   ** Output:  none
   **
   ** Description:
   ** Default Constructor - intializes the current error code to "No Error".
   **
   ****************************************************************************/
   public void LMSErrorManager()
   {
      currentErrorCode = "0";
   }


   /****************************************************************************
   **
   ** Method:  GetCurrentErrorCode()
   ** Input:   none
   ** Output:  String - current error code
   **
   ** Description:
   ** Returns the value of the Current Error Code that was set by the most
   ** recent API call.
   **
   ****************************************************************************/
   public String GetCurrentErrorCode()
   {
      return currentErrorCode;
   }

   /****************************************************************************
   **
   ** Method:  SetCurrentErrorCode(String code)
   ** Input:   String code - The error code (from the predefined list of codes)
   ** Output:  none
   **
   ** Description:
   ** Sets the value of the Current Error Code.
   **
   ****************************************************************************/
   public void SetCurrentErrorCode(String code)
   {

      if((code != null) && (code != ""))
      {
         LMSErrorManager.currentErrorCode = code;
      }
      else
      {
         LMSErrorManager.currentErrorCode = "0";
      }


   }

   /****************************************************************************
   **
   ** Method:  ClearCurrentErrorCode()
   ** Input:   none
   ** Output:  none
   **
   ** Description:
   ** Sets the value of the Current Error Code to "No Error".
   **
   ****************************************************************************/
   public void ClearCurrentErrorCode()
   {
      LMSErrorManager.currentErrorCode = errors[0][0];
   }


   /****************************************************************************
   **
   ** Method:  GetErrorDescription(String code)
   ** Input:   String code - error code
   ** Output:  String - the description associated with the given error code
   **
   ** Description:
   ** Retrieves and returns the text associated with the given error code
   **
   ****************************************************************************/
   public String GetErrorDescription(String code) 
   {
      if((code != null) && (code != ""))
      {
         // retrieves and returns the description of the provided error code
         return GetErrorElement(code)[1];
      }
      else
      {
         return "";
      }
   }

   /****************************************************************************
   **
   ** Method:  GetErrorDiagnostic(String code) 
   ** Input:   String code - error code
   ** Output:  String - the diagnostic text associated with the given error code
   **
   ** Description:
   ** Retrieves and returns the diagnostic text associated with the error code
   **
   *****************************************************************************/
   public String GetErrorDiagnostic( String code) 
   {
      if((code != null) && (code != ""))
      {
         // retrieves and returns the diagnostic text of the provided error code
         return GetErrorElement(code)[2];
      }
      else
      {
         // returns diagnostic text of previous error
         return GetErrorElement(currentErrorCode)[2];
      }
   }

   /****************************************************************************
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
   ****************************************************************************/
   private String[] GetErrorElement(String code)
   {
      for(int i=0; i<LMSErrorManager.errors.length; i++)
      {
         if(LMSErrorManager.errors[i][0].equalsIgnoreCase(code) == true)
            return LMSErrorManager.errors[i];
      }

      String[] tmp = {"","",""};
      return tmp;

   }
}
