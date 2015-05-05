<%@page
	import="java.sql.*,java.io.*,org.adl.samplerte.server.*,org.adl.parsers.dom.*, com.jspsmart.upload.*,java.util.*,java.util.zip.*,org.xml.sax.InputSource"%>
<%
/*******************************************************************************
**
** Filename:  importUtil.jsp
**
** File Description:   This file implements utility classes for importCourse.jsp.  
**
**
**
**
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
** OR INABILITY TO USE SOFTWARE, EVEN IF CTC HAS BEEN ADVISED OF THE POSSIBILITY
** OF SUCH DAMAGES.
**
*******************************************************************************/%>


<%!

/****************************************************************************
**
** Function:  setUpInputSource()
** Input:   fileName - String
** Output:  is - InputSource
**
** Description:  This function returns the input source.
**
***************************************************************************/

private InputSource setUpInputSource(String fileName)
{
   
   InputSource is = new InputSource();
   is = setupFileSource(fileName);
   return is;
} 

/****************************************************************************
**
** Function:  setUpFileSource()
** Input:   fileName - String
** Output:  is - InputSource
**
** Description:  This function returns the input source.
**
***************************************************************************/
private InputSource setupFileSource(String filename)
{
   try
   {
      java.io.File xmlFile = new java.io.File( filename );
      if ( xmlFile.isFile() )
      {
         FileReader fr = new FileReader( xmlFile );
         InputSource is = new InputSource(fr);
         is.setEncoding("UTF-8");
         return is;
      }
      else
      {
      }    
   }
   catch ( NullPointerException  npe )
   {
      System.out.println( "Null pointer exception" + npe); 
   }
   catch ( SecurityException se )
   {
      System.out.println( "Security Exception" + se); 
   }
   catch ( FileNotFoundException fnfe )
   {
      System.out.println("File Not Found Exception" + fnfe);
   }
   return new InputSource();
}
%>
