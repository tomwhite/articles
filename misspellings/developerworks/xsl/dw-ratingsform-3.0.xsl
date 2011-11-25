<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
 
<xsl:template name="RatingsForm">
<xsl:choose>
 <xsl:when test="/dw-document/dw-article/@localsite='worldwide'">

<xsl:choose>
<!-- 2.2 if primary content area is WSDD or DB2, call in this ratings form -->
<xsl:when test="/dw-document//content-area[1]/@name = 'websphere' or /dw-document//content-area[1]/@name = 'db2'">
 <xsl:if test="not(/dw-document//@ratings='no')">
 <table width="100%" cellspacing="0" cellpadding="0" border="0">
  <tr valign="top">
  
     <td><form method="POST"  name="rateForm" action="http://www-106.ibm.com/developerworks/apps/RateServ" onSubmit=" return commentsubmit(document.rateform.COMMENTS, 800)" >

 <xsl:variable name="titleinput">
      <xsl:call-template name="FullTitle"/>
     </xsl:variable>
     <xsl:variable name="contentid">
     <xsl:value-of select="/dw-document//id/@content-id"/>
     </xsl:variable>
     <input type="HIDDEN" name="DOC_ID" value="{$contentid}"/>
     <input type="HIDDEN" name="RedirectURL" value="http://www-106.ibm.com/developerworks/thankyou/feedback-thankyou.html"/>
<a name="rating"><!-- 11/10 tdc: changed heading style --><span class="atitle2">Rate this article</span></a><p>Please rate this article.  Your input is important to us!</p>
 <table border="0" cellpadding="0" cellspacing="0" width="600">
      <tr>
       <td colspan="6"><img src="/developerworks/i/c.gif" width="100" height="8" border="0" alt=""/></td>
      </tr>
      <tr valign="top">
      		<td width="285" valign="middle"><b>This article is ....</b></td>
		<td width="65" valign="middle"><center>Strongly<br />Disagree</center></td>
		<td width="65" valign="middle"><center>Disagree</center></td>
		<td width="65" valign="middle"><center>Neutral</center></td>
		<td width="65" valign="middle"><center>Agree</center></td>
		<td width="65" valign="middle"><center>Strongly<br />Agree</center></td>
	</tr>
     	 <tr valign="top">
      		<td width="285">Useful</td>
       	<td width="65" align="center"><input type="RADIO" name="USEFUL" value="1"/></td>
       	<td width="65" align="center"><input type="RADIO" name="USEFUL" value="2"/></td>
       	<td width="65" align="center"><input type="RADIO" name="USEFUL" value="3"/></td>
      		<td width="65" align="center"><input type="RADIO" name="USEFUL" value="4"/></td>
      		<td width="65" align="center"><input type="RADIO" name="USEFUL" value="5"/></td>
     	 </tr>
        <tr valign="top">
      		<td width="285">Technically accurate</td>
       	<td width="65" align="center"><input type="RADIO" name="ACCURATE" value="1"/></td>
       	<td width="65" align="center"><input type="RADIO" name="ACCURATE" value="2"/></td>
       	<td width="65" align="center"><input type="RADIO" name="ACCURATE" value="3"/></td>
      		<td width="65" align="center"><input type="RADIO" name="ACCURATE" value="4"/></td>
      		<td width="65" align="center"><input type="RADIO" name="ACCURATE" value="5"/></td>
     	 </tr>
        <tr valign="top">
      		<td width="285">Technically complete</td>
       	<td width="65" align="center"><input type="RADIO" name="COMPLETE" value="1"/></td>
       	<td width="65" align="center"><input type="RADIO" name="COMPLETE" value="2"/></td>
       	<td width="65" align="center"><input type="RADIO" name="COMPLETE" value="3"/></td>
      		<td width="65" align="center"><input type="RADIO" name="COMPLETE" value="4"/></td>
      		<td width="65" align="center"><input type="RADIO" name="COMPLETE" value="5"/></td>
     	 </tr>
        <tr valign="top">
      		<td width="285">Logically organized</td>
       	<td width="65" align="center"><input type="RADIO" name="ORGANIZED" value="1"/></td>
       	<td width="65" align="center"><input type="RADIO" name="ORGANIZED" value="2"/></td>
       	<td width="65" align="center"><input type="RADIO" name="ORGANIZED" value="3"/></td>
      		<td width="65" align="center"><input type="RADIO" name="ORGANIZED" value="4"/></td>
      		<td width="65" align="center"><input type="RADIO" name="ORGANIZED" value="5"/></td>
     	 </tr>
     	  <tr>
       	<td colspan="6"><img src="/developerworks/i/c.gif" width="100" height="8" border="0" alt=""/></td>
        </tr>
        <tr valign="top">
                <td colspan="6">Which word best describes your level of knowledge of the subject matter?  
                    <select name="KNOWLEDGE">
                        <option value="0">Select choice</option>
                        <option value="1">Beginner</option>
                        <option value="2">Intermediate</option>
                        <option value="3">Expert</option>
                    </select>
                </td>
         </tr>
     	 </table>     	 
     	 <p><b>Comments</b> (800 characters maximum)
       <br />
    <textarea name="COMMENTS" rows="3" cols="45" wrap="virtual" onkeyup="limitComment(document.rateForm.COMMENTS, 800)"></textarea><br />
<span class="dwsmall">IBM reserves the right to remove any inappropriate comments without notice.  Although we cannot publish all comments, be assured that we do read all of them.</span></p>
<p>	If you would like a response to your comment, please enter your e-mail address:
                    <br />
                    <input type="hidden" name="emailTo" value="wsdd@us.ibm.com" />
                    e-mail:   <input type="text" name="emailFrom" size="38" />
</p>
     
         <input type="SUBMIT" name="SUBMITTYPE" value="Submit feedback"/><img src="/developerworks/i/c.gif" width="16" eight="8" border="0" alt=""/><input type="SUBMIT" name="SUBMITTYPE" value="View results"/>
        </form></td>
  </tr>
  <tr valign="top">
   <td bgcolor="#FFFFFF"><img src="/developerworks/i/c.gif" width="100" height="8" border="0" alt=""/></td>
  </tr>
 </table>
 </xsl:if>
</xsl:when>

<!-- If first zone is webservices use servlet-based ratings code -->
<xsl:when test="/dw-document//content-area[1]/@name = 'webservices'">  
  <xsl:if test="not(/dw-document//@ratings='no')">
   <table width="100%" cellspacing="0" cellpadding="0" border="0">
   <tr valign="top">
   <td><form method="POST" action="/servlet/RatingsHandler">
     <xsl:variable name="titleinput">
      <xsl:call-template name="FullTitle"/>
     </xsl:variable>
     <input type="HIDDEN" name="ArticleTitle" value="{$titleinput}"/>
     <xsl:variable name="contentareaforinput">
      <xsl:for-each select="content-area">
       <xsl:if test="position()!=1">, </xsl:if>
       <xsl:call-template name="ContentAreaInputName">
        <xsl:with-param name="contentarea"><xsl:value-of select="@name"/></xsl:with-param>
       </xsl:call-template>
      </xsl:for-each>
      </xsl:variable>
     <input type="HIDDEN" name="Zone" value="{$contentareaforinput}"/>
     <input type="HIDDEN" name="RedirectURL" value="{$ratings-thankyou-url}"/>
     <xsl:variable name="id">
        <xsl:value-of select="/dw-document//id/@domino-uid"/>
   </xsl:variable>
   <input type="HIDDEN" name="ArticleID" value="{$id}"/>
     <a name="rating"><b>{$ratings-question-text}</b></a>
     <table border="0" cellpadding="0" cellspacing="0" width="600">
      <tr>
       <td colspan="5"><img src="/developerworks/i/c.gif" width="100" height="8" border="0" alt=""/></td>
      </tr>
      <tr valign="top">
       <td width="16%"><input type="RADIO" name="Rating" value="5"/><xsl:value-of select="$ratings-value5-text"/></td>
       <td width="20%"><input type="RADIO" name="Rating" value="4"/><xsl:value-of select="$ratings-value4-text"/></td>
       <td width="24%"><input type="RADIO" name="Rating" value="3"/><xsl:value-of select="$ratings-value3-text"/></td>
       <td width="22%"><input type="RADIO" name="Rating" value="2"/><xsl:value-of select="$ratings-value2-text"/></td>
       <td width="18%"><input type="RADIO" name="Rating" value="1"/><xsl:value-of select="$ratings-value1-text"/></td>
      </tr>
     </table>
     <br />
     <xsl:variable name="formtext">
      <xsl:choose>
	<xsl:when test="//forumurl/@url=''"><xsl:value-of select="$comments-noforum-text"/></xsl:when>
	<xsl:when test="//forumurl/@url!=''"><xsl:value-of select="$comments-withforum-text"/></xsl:when>
	<xsl:otherwise><xsl:value-of select="$comments-noforum-text"/></xsl:otherwise>
      </xsl:choose>
     </xsl:variable>
     <b><xsl:value-of select="$formtext"/></b>
     <br />
     <textarea name="Comments" wrap="virtual" rows="5" cols="60"></textarea>
     <br />
     <br />
     <input type="SUBMIT" value="{$submit-feedback-text}"/>
     </form></td>
  </tr>
  <tr valign="top">
   <td bgcolor="#FFFFFF"><img src="/developerworks/i/c.gif" width="100" height="8" border="0" alt=""/></td>
  </tr>
 </table>
</xsl:if>
</xsl:when>
<!-- If zone is not webservices use domino-based ratings code -->
<xsl:otherwise>
 <xsl:if test="not(/dw-document//@ratings='no')">
 <table width="100%" cellspacing="0" cellpadding="0" border="0">
  <tr valign="top">
   <td><form method="POST" action="{$domino-ratings-post-url}">
     <xsl:variable name="titleinput">
      <xsl:call-template name="FullTitle"/>
     </xsl:variable>
     <!-- ArticleTitle should probably get a more generic name eventually -->
     <input type="HIDDEN" name="ArticleTitle" value="{$titleinput}"/>
     <xsl:variable name="contentareaforinput">
      <xsl:for-each select="content-area">
       <xsl:if test="position()!=1">, </xsl:if>
       <xsl:call-template name="ContentAreaInputName">
        <xsl:with-param name="contentarea"><xsl:value-of select="@name"/></xsl:with-param>
       </xsl:call-template>
      </xsl:for-each>
      </xsl:variable>
     <input type="HIDDEN" name="Zone" value="{$contentareaforinput}"/>
     <input type="HIDDEN" name="RedirectURL" value="{$ratings-thankyou-url}"/>
     <a name="rating"><b><xsl:value-of select="$ratings-question-text"/></b></a>
     <table border="0" cellpadding="0" cellspacing="0" width="600">
      <tr>
       <td colspan="5"><img src="/developerworks/i/c.gif" width="100" height="8" border="0" alt=""/></td>
      </tr>
      <tr valign="top">
       <td width="16%"><input type="RADIO" name="Rating" value="5"/><xsl:value-of select="$ratings-value5-text"/></td>
       <td width="20%"><input type="RADIO" name="Rating" value="4"/><xsl:value-of select="$ratings-value4-text"/></td>
       <td width="24%"><input type="RADIO" name="Rating" value="3"/><xsl:value-of select="$ratings-value3-text"/></td>
       <td width="22%"><input type="RADIO" name="Rating" value="2"/><xsl:value-of select="$ratings-value2-text"/></td>
       <td width="18%"><input type="RADIO" name="Rating" value="1"/><xsl:value-of select="$ratings-value1-text"/></td>
      </tr>
     </table>
     <br />
     <xsl:variable name="formtext">
      <xsl:choose>
	<xsl:when test="//forumurl/@url=''"><xsl:value-of select="$comments-noforum-text"/></xsl:when>
	<xsl:when test="//forumurl/@url!=''"><xsl:value-of select="$comments-withforum-text"/></xsl:when>
	<xsl:otherwise><xsl:value-of select="$comments-noforum-text"/></xsl:otherwise>
      </xsl:choose>
     </xsl:variable>
     <b><xsl:value-of select="$formtext"/></b>
     <br />
     <textarea name="Comments" wrap="virtual" rows="5" cols="60"></textarea>
     <br />
     <br />
     <input type="SUBMIT" value="{$submit-feedback-text}"/>
    </form></td>
  </tr>
  <tr valign="top">
   <td bgcolor="#FFFFFF"><img src="/developerworks/i/c.gif" width="100" height="8" border="0" alt=""/></td>
  </tr>
 </table>
</xsl:if>

  </xsl:otherwise>
</xsl:choose>
</xsl:when>

<xsl:otherwise>
 <xsl:if test="not(/dw-document//@ratings='no')">
 <table width="100%" cellspacing="0" cellpadding="0" border="0">
  <tr valign="top">
   <td><form method="POST" action="{$domino-ratings-post-url}">
     <xsl:variable name="titleinput">
      <xsl:call-template name="FullTitle"/>
     </xsl:variable>
     <!-- ArticleTitle should probably get a more generic name eventually -->
     <input type="HIDDEN" name="ArticleTitle" value="{$titleinput}"/>
     <xsl:variable name="contentareaforinput">
      <xsl:for-each select="content-area">
       <xsl:if test="position()!=1">, </xsl:if>
       <xsl:call-template name="ContentAreaInputName">
        <xsl:with-param name="contentarea"><xsl:value-of select="@name"/></xsl:with-param>
       </xsl:call-template>
      </xsl:for-each>
      </xsl:variable>
     <input type="HIDDEN" name="Zone" value="{$contentareaforinput}"/>
     <input type="HIDDEN" name="RedirectURL" value="{$ratings-thankyou-url}"/>
      <xsl:variable name="ratings-localsite">
           <xsl:value-of select="/dw-document/dw-article/@localsite"/></xsl:variable>
     <input type="HIDDEN" name="localsite" value="{$ratings-localsite}"/>
     
      <xsl:if test="/dw-document/dw-article/@localsite='china' or /dw-document/dw-article/@localsite='korea'">
      <!-- this is added for DBCS rating sortings -->
              <xsl:text disable-output-escaping="yes"><![CDATA[<script language="javascript">document.write('<input type="HIDDEN" name="url" value="'+location.href+'">');</script>]]></xsl:text>
 
     </xsl:if>
      <a name="rating"><b><xsl:value-of select="$ratings-question-text"/></b></a>
     <table border="0" cellpadding="0" cellspacing="0" width="600">
      <tr>
       <td colspan="5"><img src="/developerworks/i/c.gif" width="100" height="8" border="0" alt=""/></td>
      </tr>
      <tr valign="top">
       <td width="16%"><input type="RADIO" name="Rating" value="5"/><xsl:value-of select="$ratings-value5-text"/></td>
       <td width="20%"><input type="RADIO" name="Rating" value="4"/><xsl:value-of select="$ratings-value4-text"/></td>
       <td width="24%"><input type="RADIO" name="Rating" value="3"/><xsl:value-of select="$ratings-value3-text"/></td>
       <td width="22%"><input type="RADIO" name="Rating" value="2"/><xsl:value-of select="$ratings-value2-text"/></td>
       <td width="18%"><input type="RADIO" name="Rating" value="1"/><xsl:value-of select="$ratings-value1-text"/></td>
      </tr>
     </table>
     <br />
     <xsl:variable name="formtext">
      <xsl:choose>
	<xsl:when test="//forumurl/@url=''"><xsl:value-of select="$comments-noforum-text"/></xsl:when>
	<xsl:when test="//forumurl/@url!=''"><xsl:value-of select="$comments-withforum-text"/></xsl:when>
	<xsl:otherwise><xsl:value-of select="$comments-noforum-text"/></xsl:otherwise>
      </xsl:choose>
     </xsl:variable>
     <xsl:choose>
     <!-- tdc question:  Leah, should this be xsl:if, or are you waiting to put the rest of the code here? -->
     <xsl:when test="/dw-document/dw-article/@localsite='korea'">
     </xsl:when>
    <xsl:otherwise>
          <b><xsl:value-of select="$formtext"/></b>
     <br />
     <textarea name="Comments" wrap="virtual" rows="5" cols="60"></textarea>
      <br />
      </xsl:otherwise>
     </xsl:choose>
    
     <br />
     <input type="SUBMIT" value="{$submit-feedback-text}"/>
    </form></td>
  </tr>
  <tr valign="top">
   <td bgcolor="#FFFFFF"><img src="/developerworks/i/c.gif" width="100" height="8" border="0" alt=""/></td>
  </tr>
 </table>


</xsl:if>
</xsl:otherwise>
</xsl:choose>



</xsl:template>

</xsl:stylesheet>
