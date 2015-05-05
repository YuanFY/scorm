<%
/*******************************************************************************
**
** Filename:  logout.jsp
**
** File Description:   This file implements the logout function.
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




<html>
<head>
    <title>ADL Sample Run-Time Environment Version 1.2.2 Logout</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <script language="JavaScript">
      <!--
     function MM_reloadPage(init) { //reloads the window if Nav4 resized
         if (init==true) with (navigator) {
                 if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
                     document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage;
                 }
             } else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
     }
     //MM_reloadPage(true);  

     window.parent.document.location.href = "LMSMain.htm";
     //-->
     
    </script>
</head>

<body bgcolor="#FFFFFF">

<%
    session.removeAttribute( "EXITFLAG" );
    session.removeAttribute( "COURSEID" );
    session.removeAttribute( "SCOID" );
    
%>

</body>
</html>
