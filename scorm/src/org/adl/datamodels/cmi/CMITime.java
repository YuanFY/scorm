/*******************************************************************************
**
** Filename:  CMITime.java
**
** File Description:  The CMITime class manages timing information for
**                    use in the Data Model.
**
** Author:  ADLI Project
**
** Company Name: Concurrent Technologies Corporation
**
** Module/Package Name: org.adl.datamodel.cmi
** Module/Package Description: Collection of CMI Data Model objects
**
** Design Issues:
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
** notice and license appear on all copies of the software; and ii) Licensee does
** not utilize the software in a manner which is disparaging to CTC.
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
*******************************************************************************
**
** Date Changed   Author of Change  Reason for Changes
** ------------   ----------------  -------------------------------------------
** 11/15/2000     S. Thropp         PT 263: Removal of reference to a version
**                                  of SCORM.
**
** 07/06/2001     Jeff Falls        Related to PT 964: Made the "add()" and
**                                  "toString()" functions more robust to handle
**                                  maintenance of the proper format:
**                                  hh:mm:ss.ss
**
** 03/12/2002     Bill Capone       PT 1822: Modified toString(), the seconds
**                                  portion. Also, added accessors and modifiers
**                                  for CMIHours, CMIMinutes and CMISeconds.
**
*******************************************************************************/
package org.adl.datamodels.cmi;

//native java imports
import java.io.*;
import java.util.*;
import java.lang.Integer;

//adl imports
import java.lang.Float;

public class CMITime implements Serializable
{
    private int   CMIHours;
    private int   CMIMinutes;
    private float CMISeconds;


    /***************************************************************************
    **
    ** Method:  constructor
    ** Input:   String timeString
    ** Output:  none
    **
    ** Description:
    ** This constructor takes as input a string that should be in the form of
    ** HH:MM:SS.S and create a CMITime object.
    ** For this example code error checking is not being done to ensure that the
    ** string is in the correct format.
    **
    ***************************************************************************/
    public CMITime( String timeString )
    {
        StringTokenizer st = new StringTokenizer( timeString, ":" );
        String lmsHours    = st.nextToken();
        String lmsMinutes  = st.nextToken();
        String lmsSeconds  = st.nextToken();

        CMIHours   = Integer.parseInt( lmsHours );
        CMIMinutes = Integer.parseInt( lmsMinutes );
        CMISeconds = Float.valueOf( lmsSeconds ).floatValue();
        //CMISeconds = Float.parseFloat( lmsSeconds );
    }

    /************************************************************************
     **  Accessors to the CMITime Data.
     ************************************************************************/
    public int getHours()
    {
       return CMIHours;
    }
    public int getMinutes()
    {
       return CMIMinutes;
    }
    public float getSeconds()
    {
       return CMISeconds;
    }

    /************************************************************************
     **  Modifiers to the CMITime Data.
     ************************************************************************/
    public void setHours(int iHours)
    {
       CMIHours = iHours;
    }
    public void setMinutes(int iMinutes)
    {
       CMIMinutes = iMinutes;
    }
    public void setSeconds(float iSeconds)
    {
       CMISeconds = iSeconds;
    }

    /***************************************************************************
    **
    ** Method:  toString
    ** Input:   none
    ** Output:  String representation of time
    **
    ** Description:
    ** This method will return the time as a String.  The format of the time will be
    ** hh:mm:ss.SS
    **
    ***************************************************************************/
    @Override
	public String toString()
    {
       String temp = new String("0");

       String hours   = Integer.toString( CMIHours, 10 );

       if ( hours.length() == 1 )
       {
          hours = "0" + hours;
       }
       else if ( hours.length() == 0 )
       {
          hours = "00";
       }

       String minutes = Integer.toString( CMIMinutes, 10 );

       if ( minutes.length() == 1 )
       {
          minutes = "0" + minutes;
       }
       else if ( minutes.length() == 0 )
       {
          minutes = "00";
       }

       String seconds = Float.toString( CMISeconds );

       if ( seconds.length() == 1 )
       {
          seconds = "0" + seconds;
       }
       else if ( seconds.length() == 0 )
       {
          seconds = "00";
       }

       return hours + ":" + minutes + ":" + seconds;
    }

    /***************************************************************************
    **
    ** Method:  add
    ** Input:   CMITime addTime - time to add to this time
    ** Output:  none
    **
    ** Description:
    ** This method will add the time represented by the addTime input parameter to
    ** the time represented by 'this' CMITime object.  The new time will be stored
    ** back into 'this' CMITime object.
    **
    ***************************************************************************/
    public void add( CMITime addTime )
    {
       int newHours     = 0;
       int newMinutes   = 0;
       float newSeconds = (float)0.0;

       // Add the time together
       newSeconds = CMISeconds + addTime.CMISeconds;
       if ( newSeconds > 60.0 )
       {
           newSeconds = newSeconds - (float)60.0;
           newMinutes = newMinutes + 1;
       }
       // ensure that seconds is in the following format: ss.ss
       newSeconds = ((int)(newSeconds * 100)) / (float)100.00;

       newMinutes = newMinutes + CMIMinutes + addTime.CMIMinutes;
       if ( newMinutes > 60 )
       {
           newMinutes = newMinutes - 60;
           newHours   = newHours + 1;
       }
       newHours   = newHours + CMIHours + addTime.CMIHours;

       // Store off the new time back into the lms core data
       CMIHours   = newHours;
       CMIMinutes = newMinutes;
       CMISeconds = newSeconds;
    }

}
