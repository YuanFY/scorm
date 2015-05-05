/*******************************************************************************
** 
** Filename:  ServletWriter.java
**
** File Description:  This class provides a simple method of posting multiple
** Serialized objects to a Java servlet and getting objects in return. This 
** code was inspired by code samples from the book 'Java Servlet Programming'
** by Jason Hunter and William Crawford (O'Reilly & Associates. 1998).
**    
** Author: ADL Technical Team
**
** Contract Number:
** Company Name: CTC
**
** Module/Package Name:
** Module/Package Description:
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
** OR INABILITY TO USE SOFTWARE, EVEN IF CTC  HAS BEEN ADVISED OF THE
** POSSIBILITY OF SUCH DAMAGES.
**
*******************************************************************************/
package org.adl.samplerte.client;

import java.net.*;
import java.io.*;
import org.adl.util.debug.DebugIndicator;

public class ServletWriter
{
    private static boolean _Debug = DebugIndicator.ON;

    static public ObjectInputStream postObjects(URL servlet, Serializable objs[], String sessionid)
    throws Exception
    {
        if(_Debug)
        {
            System.out.println( "In ServletWriter::postObjects()" );
        }
        
        URLConnection con;

        try
        {
            if(_Debug)
            {
                System.out.println( "Opening HTTP URL connection to servlet." );
            }

            con = servlet.openConnection();
        }
        catch(Exception e)
        {
            if(_Debug)
            {
                System.out.println( "Exception caught In ServletWriter::postObjects()");
            }
            
            System.out.println( e.getMessage() );
            e.printStackTrace();
            throw e;
        }


        if(_Debug)
        {
            System.out.println( "HTTP connection to servlet is open" );
            System.out.println( "configuring HTTP connection properties" );
        }
        
        con.setDoInput( true );
        con.setDoOutput( true );
        con.setUseCaches( false );
        con.setRequestProperty( "Content-Type","text/plain" );
        con.setAllowUserInteraction(false);
        con.setRequestProperty("Cookie", sessionid);

        // Write the arguments as post data
        ObjectOutputStream out;
        try
        {
            if(_Debug)
            {
                System.out.println( "Creating new http output stream" );
            }

            out = new ObjectOutputStream(con.getOutputStream());
            if(_Debug)
            {
                System.out.println( "Created new http output stream." );
                System.out.println( "Writing command and data to servlet..." );
            }
            int numObjects = objs.length;
            
            if(_Debug)
            {
               System.out.println ( "Num objects: " + numObjects);
            }

            for(int x = 0; x < numObjects; x++)
            {
                out.writeObject( objs[x] );
                if(_Debug)
                {
                    System.out.println(
                                      "just wrote a serialized object on output stream... " +
                                      objs[x].getClass().getName() );
                }
            }
        }
        catch(Exception e)
        {
            if(_Debug)
            {
                System.out.println( "Exception caught In ServletWriter::postObjects()" );
                System.out.println( e.getMessage() );
            }
            e.printStackTrace();
            throw e;
        }

        try
        {
            if(_Debug)
            {
                System.out.println( "Flushing Object Output Stream." );
            }
            out.flush();
        }
        catch(IOException  ioe)
        {
            if(_Debug)
            {
                System.out.println( "Caught IOException when calling out.flush()" );
            }
            ioe.printStackTrace();
            throw ioe;
        }
        catch(Exception e)
        {
            if(_Debug)
            {
                System.out.println( "Caught Exception when calling out.flush()" );
            }
            e.printStackTrace();
            throw e;
        }

        try
        {
            
            if(_Debug)
            {
                System.out.println( "Closing Object Output Stream." );
            }
            out.close();
        }
        catch(IOException  ioe)
        {
            if(_Debug)
            {
                System.out.println( "Caught IOException when calling out.close()" );
                System.out.println( ioe.getMessage());
            }
            ioe.printStackTrace();
            throw ioe;
        }
        catch(Exception e)
        {
            if(_Debug)
            {
                System.out.println( "Caught Exception when calling out.close()" );
            }
            e.printStackTrace();
            throw e;
        }

        //if(_Debug)
        //{
        //   System.out.println("about to do four things");
        //   if ( con == null )
        //   {
        //       System.out.println("the connection is gone");
        //   }
        //   System.out.println("Content: "+ String.valueOf(con.getContent()));
        //   System.out.println("did first");
        //   System.out.println("Content Type: " + con.getContentType());
        //   System.out.println("did second");
        //   System.out.println("Content Encoding: " + con.getContentEncoding());
        //   System.out.println("did third");
        //   System.out.println( "Ready to create ObjectInputStream object." );
        //   System.out.println("did last");
        //}
        ObjectInputStream in;

        try
        {
            if(_Debug)
            {
                System.out.println( "Creating new http input stream." );
            }

            in = new ObjectInputStream( con.getInputStream() );
        }
        catch(Exception e)
        {
            if(_Debug)
            {
                System.out.println(
                                  "Exception caught In ServletWriter::postObjects()");
                System.out.println( e.getMessage() );
            }
            e.printStackTrace();
            throw e;
        }

        return in;
    }
}
