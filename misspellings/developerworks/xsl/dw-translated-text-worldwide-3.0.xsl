<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">

<!-- In xsl:output -->
<xsl:variable name="encoding">UTF-8</xsl:variable>

<!-- In template match="/" -->
<xsl:variable name="Attrib-javaworld">Reprinted with permission from <a href="http://www.javaworld.com/?IBMDev">JavaWorld magazine</a>. Copyright IDG.net, an IDG Communications company.  Register for free <a href="http://www.javaworld.com/subscribe?IBMDev">JavaWorld email newsletters</a>.
</xsl:variable>

<!-- In template name="Attrib-javaworld" -->
<xsl:variable name="stylesheet-id">XSLT stylesheet used to transform this file:  dw-document-html-3.0.xsl</xsl:variable>

<!-- In template name="AuthorBottom" -->
<xsl:variable name="aboutTheAuthor">About the author</xsl:variable>
<xsl:variable name="aboutTheAuthors">About the authors</xsl:variable>

<!-- In template name="AuthorTop" -->
<xsl:variable name="job-co-errormsg">Error:  Please enter a value for the author element's jobtitle attribute, or the company attribute, or both.</xsl:variable>
<xsl:variable name="updated">Updated </xsl:variable>
<xsl:variable name="daychar"></xsl:variable>
<xsl:variable name="monthchar"></xsl:variable>
<xsl:variable name="yearchar"></xsl:variable>

<!-- In template name="Download" -->
<xsl:variable name="download-heading">Download</xsl:variable>
<xsl:variable name="download-description-heading">Description</xsl:variable>
<xsl:variable name="download-filetype-heading">File type</xsl:variable>
<xsl:variable name="download-filesize-heading">File size</xsl:variable>
<xsl:variable name="download-method-heading">Download method</xsl:variable>
<xsl:variable name="download-http-alt">HTTP download</xsl:variable>
<xsl:variable name="download-ftp-alt">FTP download</xsl:variable>
<xsl:variable name="download-method-link">Which download method should I choose?</xsl:variable>

<!-- In template name="Footer" -->
<xsl:variable name="footer-inc"><xsl:text disable-output-escaping="yes"><![CDATA[<!--#include virtual="/developerworks/inc/dw-topfooterv11.inc" -->]]></xsl:text></xsl:variable>

<!-- in template name="generalBreadCrumbTrail"  -->
<xsl:variable name="developerworks-top-url">http://www-136.ibm.com/developerworks/</xsl:variable>
<xsl:variable name="developerworks-top-heading">developerWorks</xsl:variable>

<!-- in template name="heading"  -->
<xsl:variable name="figurechar"></xsl:variable>


<!-- In template name="IconLinks" -->
<xsl:variable name="icon-discuss-gif">/developerworks/i/icon-discuss.gif</xsl:variable>
<xsl:variable name="icon-discuss-alt">Discuss</xsl:variable>
<xsl:variable name="icon-code-gif">/developerworks/i/icon-code.gif</xsl:variable>
<xsl:variable name="icon-code-download-alt">Download</xsl:variable>
<xsl:variable name="icon-code-alt">Code</xsl:variable>
<xsl:variable name="icon-pdf-gif">/developerworks/i/icon-pdf.gif</xsl:variable>
<xsl:variable name="Summary">Summary</xsl:variable>
<xsl:variable name="english-source-heading"></xsl:variable>


<!-- In template name="Indicators" -->
<xsl:variable name="level-text-heading">Level: </xsl:variable>

<!-- In template name="Masthead" -->
<xsl:variable name="topmast-inc"><xsl:text disable-output-escaping="yes"><![CDATA[<!--#include virtual="/developerworks/inc/dw-topmast.inc" -->]]></xsl:text></xsl:variable>

<!-- In template name="META" -->
<xsl:variable name="owner-meta-url"> https://www-136.ibm.com/developerworks/secure/feedback.jsp?domain=</xsl:variable>
<xsl:variable name="dclanguage-content">en-us</xsl:variable>
<xsl:variable name="ibmcountry-content">zz</xsl:variable>

<!-- In template name="MonthName" -->
<xsl:variable name="month-1-text">Jan</xsl:variable>
<xsl:variable name="month-2-text">Feb</xsl:variable>
<xsl:variable name="month-3-text">Mar</xsl:variable>
<xsl:variable name="month-4-text">Apr</xsl:variable>
<xsl:variable name="month-5-text">May</xsl:variable>
<xsl:variable name="month-6-text">Jun</xsl:variable>
<xsl:variable name="month-7-text">Jul</xsl:variable>
<xsl:variable name="month-8-text">Aug</xsl:variable>
<xsl:variable name="month-9-text">Sep</xsl:variable>
<xsl:variable name="month-10-text">Oct</xsl:variable>
<xsl:variable name="month-11-text">Nov</xsl:variable>
<xsl:variable name="month-12-text">Dec</xsl:variable>

<!-- In template name="articleRelatedContents" -->
<xsl:variable name="related-content-heading">Related content:</xsl:variable>

<!-- In template name="Subscriptions" -->
<xsl:variable name="subscriptions-heading">Subscriptions:</xsl:variable>
<xsl:variable name="dw-subscription-text">dW Subscription<br />(CDs and downloads)</xsl:variable>
<xsl:variable name="dw-subscription-url">/developerworks/subscription/</xsl:variable>
<xsl:variable name="dw-newsletter-text">dW newsletters</xsl:variable>
<xsl:variable name="dw-newsletter-url">http://www-106.ibm.com/developerworks/newsletter/</xsl:variable>

<!-- In template name="Resources" and "TableofContents" -->
<xsl:variable name="resourcelist-heading">Resources</xsl:variable>

<!-- In template name="resourcelist/ul" -->
<xsl:variable name="resourcelist-forum-text">Participate in the <a href="javascript:void forumWindow()">discussion forum</a> on this article.  (You can also click <b>Discuss</b> at the top or bottom of the article to access the forum.)</xsl:variable>

<!-- In template name="SkillLevel" -->
<xsl:variable name="level-1-text">Introductory</xsl:variable>
<xsl:variable name="level-2-text">Introductory</xsl:variable>
<xsl:variable name="level-3-text">Intermediate</xsl:variable>
<xsl:variable name="level-4-text">Advanced</xsl:variable>
<xsl:variable name="level-5-text">Advanced</xsl:variable>

<!-- In template name="TableOfContents" -->
<xsl:variable name="tableofcontents-heading">Contents:</xsl:variable>
<xsl:variable name="ratethisarticle-heading">Rate this article</xsl:variable>

<!-- In file "dw-ratingsform-3.0.xsl  -->
<xsl:variable name="domino-ratings-post-url">http://www.alphaworks.ibm.com/developerworks/ratings.nsf/RateArticle?CreateDocument</xsl:variable>

<xsl:variable name="ratings-thankyou-url">http://www-106.ibm.com/developerworks/thankyou/feedback-thankyou.html</xsl:variable>
<xsl:variable name="ratings-question-text">What do you think of this document?</xsl:variable>
<xsl:variable name="ratings-value5-text">Killer! (5)</xsl:variable>
<xsl:variable name="ratings-value4-text">Good stuff (4)</xsl:variable>
<xsl:variable name="ratings-value3-text">So-so; not bad (3)</xsl:variable>
<xsl:variable name="ratings-value2-text">Needs work (2)</xsl:variable>
<xsl:variable name="ratings-value1-text"><xsl:text>Lame! (1)</xsl:text></xsl:variable>

<xsl:variable name="comments-noforum-text">Comments?</xsl:variable>
<xsl:variable name="comments-withforum-text">Send us your comments or click Discuss to share your comments with others.</xsl:variable>
<xsl:variable name="submit-feedback-text">Submit feedback</xsl:variable>

<!-- in template name="ContentAreaName" -->

<xsl:variable name="contentarea-ui-name-au">Autonomic computing</xsl:variable>
<xsl:variable name="contentarea-ui-name-gr">Grid computing</xsl:variable>
<xsl:variable name="contentarea-ui-name-j">Java technology</xsl:variable>
<xsl:variable name="contentarea-ui-name-l">Linux</xsl:variable>
<xsl:variable name="contentarea-ui-name-os">Open source projects</xsl:variable>
<xsl:variable name="contentarea-ui-name-ws">Web services</xsl:variable>
<xsl:variable name="contentarea-ui-name-x">XML</xsl:variable>
<xsl:variable name="contentarea-ui-name-co">Components</xsl:variable>
<xsl:variable name="contentarea-ui-name-s">Security</xsl:variable>
<xsl:variable name="contentarea-ui-name-wa">Web architecture</xsl:variable>
<xsl:variable name="contentarea-ui-name-wi">Wireless</xsl:variable>
<xsl:variable name="contentarea-ui-name-i">Scenarios</xsl:variable>
<xsl:variable name="contentarea-ui-name-db2">DB2</xsl:variable>
<xsl:variable name="contentarea-ui-name-es">eServer</xsl:variable>
<xsl:variable name="contentarea-ui-name-lo">Lotus</xsl:variable>
<xsl:variable name="contentarea-ui-name-r">Rational</xsl:variable>
<xsl:variable name="contentarea-ui-name-tiv">Tivoli</xsl:variable>
<xsl:variable name="contentarea-ui-name-web">WebSphere</xsl:variable>
<xsl:variable name="contentarea-ui-name-sub">developerWorks Subscription</xsl:variable>
</xsl:stylesheet>



