/*******************************************************************************
**
** Filename:  CMIInteractionDataUtil.java
**
** File Description:
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
** 05/14/2002     B. Capone         PT1832: Changed the ID element in the
**                                  constructor from checkString255 to
**                                  checkIdentifier.
**
*******************************************************************************/
package org.adl.datamodels.cmi;

//native java imports
import java.io.*;

import org.adl.datamodels.*;

public class CMIInteractionDataUtil extends CMICategory
   implements Serializable
{
   public Element id;
   public Element objectives;
   public Element time;
   public Element type;
   public Element correct_responses;
   public Element weighting;
   public Element student_response;
   public Element result;
   public Element latency;

   /****************************************************************************
    **
    ** Method:  CMIInteractionDataUtil
    ** Input:   none
    ** Output:  none
    **
    ** Description:  Default Constructor
    **
    ***************************************************************************/
   public CMIInteractionDataUtil()
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
      correct_responses =
         new Element("","checkFeedback","NULL",true,false,false);
      objectives = new Element("","checkString255","NULL",true,false,false);

   }  // end of default constuctor

   /****************************************************************************
    ** Here are the accessers for the CMIInteractionDataUtil class.
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
   public Element getObjID()
   {
      return objectives;
   }
   public Element getCorrectResponses()
   {
      return correct_responses;
   }

}  // end of CMIInteractionDataUtil