/*******************************************************************************
**
** Filename:  CMICategory.java
**
** File Description:   This class represents a CMI Category.  This class
**                     acts as a parent to all of the CMI Data Element
**                     Categories:
**                        CMICore, CMIScore, CMISuspendData, CMILaunchData,
**                        CMIComments, CMIObjectives, CMIObjectiveData,
**                        CMIEvaluation, CMIEvaluation, CMIEvaluationData,
**                        CMIInteractions, CMIInteractionData, CMIResponse,
**                        CMIStudentData, CMIEvaluationComments, CMIAttempt,
**                        CMITries, CMIStudentDemographics, CMIPath
**                        and CMIStudentPreference
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
** 11/15/2000     S.Thropp          PT 291: For performance reasons, remove
**                                  the data model validator as an attribute
**                                  and make it a local variable when needed.
**
** 11/17/2000     S.Thropp          PT 292: Changed references to old error
**                                  codes.  Codes were changed to map to the
**                                  new error codes.
**
** 01/17/2001     S.Thropp          PT 374: Added some more debug statements
**
** 03/20/2002     Bill Capone       Added a "SetCurrentErrorCode" to "0" (zero)
**                                  in doSet().
**
*******************************************************************************/
package org.adl.datamodels.cmi;

//native java imports
import java.io.*;
import java.lang.reflect.*;

//adl imports
import org.adl.util.debug.*;
import org.adl.datamodels.*;

public class CMICategory implements Serializable
{
   // Indicates whether or not the category can be set by an AU
   protected boolean writeable;

   /****************************************************************************
    **
    ** Method: Constructor
    **
    ** Input: boolean writeableFlag
    ** Output: none
    **
    ** Description:  Constructs a Data Model Category
    **
    ***************************************************************************/
   public CMICategory(boolean writeableFlag)
   {
      writeable = writeableFlag;
   }  // end of constructor


   /****************************************************************************
    **
    ** Method: isWriteable
    **
    ** Input: none
    ** Output: boolean value stating whether or not the Data Model Category
    **         is writeable
    **
    ** Description:  Returns a boolean value which indicates whether or not
    **               the Data Model Element is writeable
    **
    ***************************************************************************/
   public boolean isWriteable()
   {
      return writeable;
   }  // end of isWriteable()

   /****************************************************************************
    **
    ** Method:  determineElementValue
    ** Input:   Object - the current object (data model category)
    **          String - Data Model Element that holds the value that
    **                   is wanted
    **          DMErrorManager - The Data Model Error manager
    **
    ** Output:  String - the requested value
    **
    ** Description:  This method determines the value of the input Data model
    **               element.
    **
    ***************************************************************************/
   public String determineElementValue(Object o,
                                       String element,
                                       DMErrorManager dmErrorMgr)
   {
      // Value to return
      String rtrnString = new String("");

      // Get the Class name off of the Object
      Class c = o.getClass();
      try
      {
         // Use reflection to obtain the appropriate field (attribute)

         // Get the Field (in Class c) that matches the element
         Field tmpField = c.getField(element);

         // Get the Element object that is represented by the Field
         Element e = (Element)tmpField.get(o);

         if ( e.isReadable() )
         {
            // get the value off of the Element
            rtrnString = e.getValue();
         }
         else
         {
            if ( DebugIndicator.ON )
            {
               System.out.println("Error - Element not readable");
               System.out.println("Value: " + element + " is not readable");
            }

            // Notify error manager
            dmErrorMgr.SetCurrentErrorCode("404");
         }
      }
      catch ( NoSuchFieldException nsfe )
      {
         if (element.equals("_count") == true)
         {
            if ( DebugIndicator.ON )
            {
               System.out.println("Error - Element not an array");
               System.out.println("Element Cannont have count");
            }
            dmErrorMgr.SetCurrentErrorCode("203");
         }
         else
         {
            if ( DebugIndicator.ON )
            {
               System.out.println("Error - Invalid Argument Error");
               System.out.println("Invalid data model element: " + element);
            }

            // Notify error manager
            dmErrorMgr.SetCurrentErrorCode("201");
         }
      }
      catch ( SecurityException se )
      {
         if ( DebugIndicator.ON )
         {
            System.out.println(se);
            System.out.println("Access to the information is denied");
         }

         // Notify error manager
         dmErrorMgr.SetCurrentErrorCode("101");
      }
      catch ( IllegalAccessException iae )
      {
         if ( DebugIndicator.ON )
         {
            System.out.println(iae);
            System.out.println("Underlying constructor is inaccessible");
         }
         // Notify error manager
         dmErrorMgr.SetCurrentErrorCode("101");
      }

      return rtrnString;

   } // end of determineElementValue()

   /****************************************************************************
    **
    ** Method:  doSet
    ** Input:   Object - the current Object (data model category)
    **          String - Data Model Element that needs set
    **          String -  Value to use in the set
    **          DMErrorManager - The Data Model Error manager
    ** Output:  none
    **
    ** Description:  This method performs all of the necessary steps in order
    **               to process an LMSSetValue() request.  The method uses
    **               reflection to determine and find the element being set.
    **               The method reports any errors while processing the
    **               request.
    **
    ***************************************************************************/
   public void doSet(Object o,
                     String element,
                     String value,
                     DMErrorManager dmErrorMgr)
   {
      if ( DebugIndicator.ON )
      {
         System.out.println("CMICategory::doSet()");
         System.out.println("Object: " + o.getClass().getName());
         System.out.println("Element: " + element);
         System.out.println("Value: " + value);
      }
      // Get the class from the object passed in
      Class c = o.getClass();
      try
      {
         // Use reflection to obtain the appropriate field (attribute)
         Field tmpField = c.getField(element);

         // Get the Element object that is represented by the Field
         Element e = (Element)tmpField.get(o);

         // Check to see if the Element was implemented
         if ( e.isImplemented() )
         {
            // Check to see if the element was writeable
            if ( e.isWriteable() )
            {
               // Verifiy the type of the value to be used for setting
               // matches the type of the element to be set
               if ( validateType(o,e,element,value,dmErrorMgr) )
               {
                  // set the value
                  e.setValue(value);
                  dmErrorMgr.SetCurrentErrorCode("0");
                  if ( DebugIndicator.ON )
                  {
                     System.out.println("Element: " + element +
                                     " was set to [" + value + "]");
                  }
               }
               else
               {
                  if ( DebugIndicator.ON )
                  {
                     System.out.println("*** INVALID LMSSetValue() CALL ***");
                     System.out.println("          Invalid Type            ");
                     System.out.println("Element: " + element +
                                        " was NOT set!");
                  }
                  dmErrorMgr.SetCurrentErrorCode("405");
               }
            }
            else
            {
               if ( DebugIndicator.ON )
               {
                  // Error - Data Model Element not writeable
                  System.out.println("Error - Element not Writeable");
                  System.out.println("Element: " + element +
                                  " is not writeable");
               }
               // Notify error manager
               dmErrorMgr.SetCurrentErrorCode("403");
            }
         }
         else
         {
            // Element was not implemented, check to see if it is a
            // mandatory element
            if ( e.isMandatory() )
            {
               if ( DebugIndicator.ON )
               {
                  // Error - Mandatory Data Model Element not implemented
                  System.out.println(
                     "Error - Mandatory Element not Implemented");
                  System.out.println("Mandatory Value: " + element +
                                  " was not implemented");
               }
               // Notify error manager
               dmErrorMgr.SetCurrentErrorCode("401");
            }
            else
            {
               if ( DebugIndicator.ON )
               {
                  // Not an Error - Optional element not implemented
                  System.out.println(
                     "Warning - Optional Value Not Implemented");
                  System.out.println("Optional Element: " + element +
                                  " was not implemented");
               }
               // Notify error manager
               dmErrorMgr.SetCurrentErrorCode("0");
            }
         }
      }
      catch ( NoSuchFieldException nsfe )
      {
         if ( DebugIndicator.ON )
         {
            System.out.println("Error - Invalid Argument Error");
            System.out.println("Invalid data model element: " + element);
         }
         // Notify error manager
         dmErrorMgr.SetCurrentErrorCode("201");
      }
      catch ( SecurityException se )
      {
         if ( DebugIndicator.ON )
         {
            System.out.println(se);
            System.out.println("Access to the information is denied");
         }
         // Notify error manager
         dmErrorMgr.SetCurrentErrorCode("101");
      }
      catch ( IllegalAccessException iae )
      {
         if ( DebugIndicator.ON )
         {
            System.out.println(iae);
            System.out.println("Underlying constructor is inaccessible");
         }

         // Notify error manager
         dmErrorMgr.SetCurrentErrorCode("101");
      }

      if ( DebugIndicator.ON )
      {
         System.out.println("Exiting CMICategory::doSet()");
      }

   }  // end of doSet()


   /****************************************************************************
    **
    ** Method: validateType()
    **
    ** Input: Object o - object for which the LMSSetValue is taking place
    **        Element e -  element class being set
    **        String element - element (string representation) being set
    **        String value - value being used for set
    **        DMErrorManager dmErrorMgr -  Data Model Error Manager
    **
    ** Output: boolean value stating whether or not this is a valid LMSSetValue
    **         request
    **
    ** Description:  Returns a boolean value which indicates whether or not
    **               the LMSSetValue() request is valid.  The method
    **               uses the Data Model Validator to check to see if the
    **               value to be used for setting is the correct type for
    **               the element to be set.
    **
    ***************************************************************************/
   protected boolean validateType(Object o,
                                Element e,
                                String element,
                                String value,
                                DMErrorManager dmErrorMgr)
   {
      if ( DebugIndicator.ON )
      {
         System.out.println("   Validating data type of the set value");
         System.out.println("   Check method: " + e.getType());
         System.out.println("   Element: " + element);
         System.out.println("   Value: " + value);
      }

      boolean result = false;

      DataModelValidator dmValidator = new DataModelValidator();
      Class dmClass = dmValidator.getClass();

      // Set up the array of Class objects
      // Each element in the array corresponds to a
      // parameter of the method you want to invoke
      Class[] parameterTypes =
         new Class[] {e.getClass(), String.class};

      // Declare a variable to hold the method
      Method theMethod;

      // Declare an arry of objects for the arguments to the method
      Object[] arguments = new Object[] {e,value};
      try
      {
         // Find the method on Class c that is represented by the
         // name of the method ("performSet") and the parameter
         // types
         theMethod = dmClass.getMethod(e.getType(), parameterTypes);

         // Invoke the method that was found matching the method name
         // and parameter types
         Boolean theResult = (Boolean) theMethod.invoke(dmValidator,arguments);
         result = theResult.booleanValue();
      }
      catch ( NoSuchMethodException nsme )
      {
         if ( DebugIndicator.ON )
         {
            System.out.println(nsme);
            System.out.println("No Such method: " + e.getType() + "()");
         }

         // Notify error manager
         dmErrorMgr.SetCurrentErrorCode("101");

      }
      catch ( IllegalAccessException iae )
      {
         if ( DebugIndicator.ON )
         {
            System.out.println(iae);
         }
         // Notify error manager
         dmErrorMgr.SetCurrentErrorCode("101");
      }
      catch ( InvocationTargetException ite )
      {
         if ( DebugIndicator.ON )
         {
            System.out.println(ite);
         }
         // Notify error manager
         dmErrorMgr.SetCurrentErrorCode("101");
      }

      if ( DebugIndicator.ON )
      {
         System.out.println("   Result from validation: " + result);
      }
      return result;
   }


}  // end of CMICategory
