/*******************************************************************************
**
** Filename:  SCODataVerifier.java
**
** File Description:
**
** Author: ADLI Technical Team
**
** Company Name: Concurrent Technologies Corporation
**
** Module/Package Name: org.adl.datamodel.cmi
** Module/Package Description:
**
** Design Issues:
**
** Implementation Issues:
**
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
**
*******************************************************************************/
package org.adl.datamodels;

//native java imports
import java.io.*;

import org.adl.datamodels.cmi.*;

public class SCODataVerifier implements Serializable
{
   public CMICore core;
   public CMISuspendData suspend_data;
   public CMILaunchData launch_data;
   public CMIComments comments;
   public CMICommentsFromLms comments_from_lms;
   public CMIObjectivesUtil objectives;
   public CMIInteractionsUtil interactions;
   public CMIStudentData student_data;
   public CMIStudentPreference student_preference;

   //***************************************
   //**  Accessors to the SCODataVerifier Data
   //***************************************
   public CMICore getCore()
   {
      return core;
   }
   public CMISuspendData getSuspendData()
   {
      return suspend_data;
   }
   public CMILaunchData getLaunchData()
   {
      return launch_data;
   }
   public CMIComments getComments()
   {
      return comments;
   }

   public CMICommentsFromLms getCommentsFromLMS()
   {
      return comments_from_lms;
   }

   public CMIObjectivesUtil getObjectives()
   {
      return objectives;
   }

   public CMIInteractionsUtil getInteractions()
   {
      return interactions;
   }
   public CMIStudentData getStudentData()
   {
      return student_data;
   }

   public CMIStudentPreference getStudentPreference()
   {
      return student_preference;
   }

   /****************************************************************************
    **
    ** Method:  SCODataManager
    ** Input:   none
    ** Output:  none
    **
    ** Description:  Default Constructor
    **
    ***************************************************************************/
   public SCODataVerifier()
   {
      core = new CMICore();
      suspend_data = new CMISuspendData();
      launch_data = new CMILaunchData();
      comments = new CMIComments();
      comments_from_lms = new CMICommentsFromLms();
      objectives = new CMIObjectivesUtil();
      interactions = new CMIInteractionsUtil();
      student_data = new CMIStudentData();
      student_preference = new CMIStudentPreference();

   } // end of default constructor

}  // end of SCODataVerifier