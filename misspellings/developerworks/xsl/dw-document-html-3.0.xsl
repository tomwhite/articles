<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="xsl fo">
  <xsl:output method="html" indent="no" omit-xml-declaration="yes" encoding="UTF-8"/>
  <xsl:key name="column-icons" match="column-info" use="@col-name"/>
  <xsl:include href="dw-entities-3.0.xsl"/>
  <xsl:include href="xslt-utilities-3.0.xsl"/>
  
  <!-- 3.0 01/12 tdc: Each local site includes only their own translated text file here.  -->
  <xsl:include href="dw-translated-text-worldwide-3.0.xsl"/>
  <!-- <xsl:include href="dw-translated-text-china-3.0.xsl"/> -->
  <!-- <xsl:include href="dw-translated-text-korea-3.0.xsl"/> -->
  <!-- <xsl:include href="dw-translated-text-taiwan-3.0.xsl"/> -->
  
  
  <xsl:template match="/">
    <html>
      <xsl:comment>
        <xsl:value-of select="$stylesheet-id"/>
      </xsl:comment>
      <xsl:apply-templates select="dw-document/dw-article |
                                                     dw-document/dw-sidefile  | 
                                                     dw-document/dw-subscription-landing"/>
    </html>
  </xsl:template>
  <!--                                                                                              ARTICLE ARTICLE ARTICLE ARTICLE ARTICLE -->
  <xsl:template match="dw-article">
    <head>
      <title>
        <xsl:call-template name="FullTitle"/>
      </title>
      <xsl:call-template name="META"/>
      <xsl:call-template name="articleJavaScripts"/>
    </head>
    <body bgcolor="#ffffff" marginheight="2" marginwidth="2" topmargin="2" leftmargin="2">
      <xsl:call-template name="Masthead"/>
      <table width="100%" cellspacing="0" cellpadding="0" border="0">
        <tr valign="top">
          <!-- RIGHT GUTTER -->
          <td width="5">
            <img src="/developerworks/i/c.gif" width="5" height="1" border="0" alt=""/>
          </td>
          <xsl:call-template name="generalBreadCrumbTrail"/>
        </tr>
      </table>
      <xsl:call-template name="TitleArea"/>
      <!-- START BODY AREA -->
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr valign="top">
          <!-- LEFT GUTTER -->
          <td width="5">
            <img src="/developerworks/i/c.gif" width="5" height="1" border="0" alt=""/>
          </td>
          <!-- START CENTER COLUMN -->
          <td width="100%">
            <!-- START STANDARD SIDEBAR AREA -->
            <table width="168" align="right" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <!-- Sidebar Gutter -->
                <td width="8">
                  <img src="/developerworks/i/c.gif" width="5" height="21" alt=""/>
                </td>
                <td width="160">
                  <xsl:call-template name="TableOfContents"/>
                  <xsl:call-template name="articleRelatedContents"/>
                  <xsl:call-template name="Subscriptions"/>
                  <xsl:call-template name="TableOfContentsClose"/>
                  <!-- END STANDARD SIDEBAR AREA -->
                </td>
              </tr>
            </table>
            <xsl:call-template name="VisibleSubtitle"/>
            <xsl:call-template name="Indicators"/>
            <xsl:call-template name="AuthorTop"/>
            <xsl:call-template name="AbstractForDisplay"/>
            <xsl:apply-templates select="docbody"/>
            <xsl:call-template name="Resources"/>
            <xsl:call-template name="AuthorBottom"/>
            <xsl:call-template name="Attribution"/>
            <!-- END PAPER BODY -->
            <br clear="all"/>
            <img src="/developerworks/i/c.gif" width="100" height="10" border="0" alt=""/>
            <br/>
            <!-- REPEAT ICON LINKS AT BOTTOM OF PAGE -->
            <table width="100%" cellspacing="0" cellpadding="0" border="0">
              <tr valign="top">
                <td width="100%" align="right">
                  <xsl:call-template name="IconLinks"/>
                </td>
                <td width="5">
                  <img src="/developerworks/i/c.gif" width="5" height="1" border="0" alt=""/>
                </td>
              </tr>
              <!-- Black Line Separator -->
              <tr valign="top">
                <td colspan="2" bgcolor="#000000">
                  <img src="/developerworks/i/c.gif" width="100" height="1" border="0" alt=""/>
                </td>
              </tr>
              <tr valign="top">
                <td colspan="2" bgcolor="#FFFFFF">
                  <img src="/developerworks/i/c.gif" width="100" height="8" border="0" alt=""/>
                </td>
              </tr>
            </table>
            <xsl:call-template name="Download"/>
            <xsl:call-template name="RatingsForm"/>
            <table width="100%" cellspacing="0" cellpadding="0" border="0">
              <tr valign="top">
                <!-- RIGHT GUTTER -->
                <xsl:call-template name="generalBreadCrumbTrail"/>
              </tr>
            </table>
          </td>
          <td width="1">
            <img src="/developerworks/i/c.gif" width="1" height="1" border="0" alt=""/>
          </td>
        </tr>
      </table>
      <xsl:call-template name="Footer"/>
    </body>
  </xsl:template>
  <!--                                                                                                     SIDEFILE SIDEFILE SIDEFILE SIDEFILE SIDEFILE  -->
  <xsl:template match="dw-document/dw-sidefile">
    <head>
      <title>
        <xsl:call-template name="HTMLTitle"/>
      </title>
      <xsl:call-template name="META"/>
      <xsl:call-template name="sidefileJavaScripts"/>
    </head>
    <body bgcolor="#ffffff" marginheight="2" marginwidth="2" topmargin="2" leftmargin="2">
      <xsl:call-template name="Masthead"/>
      <table width="100%" cellspacing="0" cellpadding="0" border="0">
        <tr valign="top">
          <!-- RIGHT GUTTER -->
          <td width="5">
            <img src="/developerworks/i/c.gif" width="5" height="1" border="0" alt=""/>
          </td>
          <xsl:call-template name="generalBreadCrumbTrail"/>
        </tr>
      </table>
      <xsl:call-template name="TitleArea"/>
      <!-- START BODY AREA -->
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr valign="top">
          <!-- LEFT GUTTER -->
          <td width="5">
            <img src="/developerworks/i/c.gif" width="5" height="1" border="0" alt=""/>
          </td>
          <!-- START CENTER COLUMN -->
          <td width="100%">
            <!-- START STANDARD SIDEBAR AREA -->
            <table width="168" align="right" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <!-- Sidebar Gutter -->
                <td width="8">
                  <img src="/developerworks/i/c.gif" width="5" height="21" alt=""/>
                </td>
                <td width="160">
                  <img src="/developerworks/i/c.gif" width="160" height="21" alt=""/>
                  <!-- END STANDARD SIDEBAR AREA -->
                </td>
              </tr>
            </table>
            <xsl:call-template name="VisibleSubtitle"/>
            <xsl:apply-templates select="docbody"/>
            <xsl:call-template name="Resources"/>
            <xsl:call-template name="Attribution"/>
            <!-- END PAPER BODY -->
            <br clear="all"/>
            <img src="/developerworks/i/c.gif" width="100" height="10" border="0" alt=""/>
            <br/>
            <!-- BLACK LIKE SEPARATOR -->
            <table width="100%" cellspacing="0" cellpadding="0" border="0">
              <tr valign="top">
                <td colspan="2" bgcolor="#000000">
                  <img src="/developerworks/i/c.gif" width="100" height="1" border="0" alt=""/>
                </td>
              </tr>
              <tr valign="top">
                <td colspan="2" bgcolor="#FFFFFF">
                  <img src="/developerworks/i/c.gif" width="100" height="8" border="0" alt=""/>
                </td>
              </tr>
            </table>
            <table width="100%" cellspacing="0" cellpadding="0" border="0">
              <tr valign="top">
                <!-- RIGHT GUTTER -->
                <xsl:call-template name="generalBreadCrumbTrail"/>
              </tr>
            </table>
          </td>
          <td width="1">
            <img src="/developerworks/i/c.gif" width="1" height="1" border="0" alt=""/>
          </td>
        </tr>
      </table>
      <xsl:call-template name="Footer"/>
    </body>
  </xsl:template>
      
  <!--                                                                                                     SUBSCRIPTION SUBSCRIPTION SUBSCRIPTION SUBSCRIPTION -->
  <xsl:template match="dw-subscription-landing">
    <head>
      <title>
        <xsl:call-template name="FullTitle"/>
      </title>
      <xsl:call-template name="META"/>
      <xsl:call-template name="dwsubJavaScripts"/>
    </head>
    <body bgcolor="#ffffff" marginheight="2" marginwidth="2" topmargin="2" leftmargin="2">
      <xsl:call-template name="Masthead"/>
      <table width="100%" cellspacing="0" cellpadding="0" border="0">
        <tr valign="top">
          <!-- RIGHT GUTTER -->
          <td width="5">
            <img src="/developerworks/i/c.gif" width="5" height="1" border="0" alt=""/>
          </td>
          <xsl:call-template name="generalBreadCrumbTrail"/>
        </tr>
      </table>
      <xsl:call-template name="TitleArea"/>
      <!-- START BODY AREA -->
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr valign="top">
          <!-- LEFT GUTTER -->
          <td width="5">
            <img src="/developerworks/i/c.gif" width="5" height="1" border="0" alt=""/>
          </td>
          <!-- START CENTER COLUMN -->
          <td width="100%">
            <!-- START STANDARD SIDEBAR AREA -->
            <table width="168" align="right" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <!-- Sidebar Gutter -->
                <td width="8">
                  <img src="/developerworks/i/c.gif" width="5" height="21" alt=""/>
                </td>
                <td width="160">
                  <xsl:call-template name="BundleAvailability"/>
                  <xsl:call-template name="TableOfContents"/>
                  <xsl:call-template name="BundleMoreAbout"/>
                  <xsl:call-template name="dwsubLinks"/>
                  <xsl:call-template name="dwsubSupportLinks"/>
                  <xsl:call-template name="dwsubCustomCDs"/>
                  <xsl:call-template name="dwsubRelatedContents"/>
                  <xsl:call-template name="TableOfContentsClose"/>
                  <!-- END STANDARD SIDEBAR AREA -->
                </td>
              </tr>
            </table>
            <xsl:call-template name="VisibleSubtitle"/>
            <xsl:call-template name="Indicators"/>
            <xsl:call-template name="BundleNote"/>
            <xsl:call-template name="BundleOverview"/>
            <xsl:call-template name="BundlePlatforms"/>
            <xsl:call-template name="BundleLanguages"/>
            <xsl:call-template name="BundleRequirements"/>
            <xsl:call-template name="BundleComponents"/>
            <!-- END BODY -->
            <br clear="all"/>
            <img src="/developerworks/i/c.gif" width="100" height="10" border="0" alt=""/>
            <br/>
            <table width="100%" cellspacing="0" cellpadding="0" border="0">
              <tr valign="top">
                <!-- RIGHT GUTTER -->
                <xsl:call-template name="generalBreadCrumbTrail"/>
              </tr>
            </table>
          </td>
          <td width="1">
            <img src="/developerworks/i/c.gif" width="1" height="1" border="0" alt=""/>
          </td>
        </tr>
      </table>
      <xsl:call-template name="Footer"/>
    </body>
  </xsl:template>
  <xsl:template match="a">
    <xsl:copy>
          <xsl:for-each select="@*">
            <xsl:copy/>
          </xsl:for-each>
          <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>
  <xsl:template name="Abstract">
    <xsl:variable name="icon">
      <xsl:for-each select="key('column-icons', /dw-document/dw-article/seriestitle)">
        <xsl:value-of select="@col-icon"/>
      </xsl:for-each>
    </xsl:variable>
    <xsl:choose>
      <xsl:when test="$icon!=''">
        <blockquote>
          <xsl:text disable-output-escaping="yes"><![CDATA[<img src="/developerworks/i/]]></xsl:text>
          <xsl:value-of select="$icon"/>
          <xsl:text disable-output-escaping="yes"><![CDATA[" border="0" alt="Column icon" height="38" width="38" align="left"/>]]></xsl:text>
          <xsl:value-of select="//abstract"/>
        </blockquote>
      </xsl:when>
      <xsl:otherwise>
        <blockquote>
          <xsl:value-of select="//abstract"/>
        </blockquote>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template name="AbstractExtended">
    <xsl:variable name="icon">
      <xsl:for-each select="key('column-icons', /dw-document/dw-article/seriestitle)">
        <xsl:value-of select="@col-icon"/>
      </xsl:for-each>
    </xsl:variable>
    <xsl:choose>
      <xsl:when test="$icon!=''">
        <blockquote>
          <xsl:text disable-output-escaping="yes"><![CDATA[<img src="/developerworks/i/]]></xsl:text>
          <xsl:value-of select="$icon"/>
          <xsl:text disable-output-escaping="yes"><![CDATA[" border="0" alt="Column icon" height="38" width="38" align="left"/>]]></xsl:text>
          <xsl:apply-templates select="//abstract-extended"/>
        </blockquote>
      </xsl:when>
      <xsl:otherwise>
        <blockquote>
          <xsl:apply-templates select="//abstract-extended"/>
        </blockquote>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template match="attribution">
    <br clear="all"/>
    <img src="/developerworks/i/c.gif" width="100" height="5" border="0" alt=""/>
    <table border="1" cellspacing="1" cellpadding="3">
      <tr>
        <td>
          <xsl:choose>
            <xsl:when test="@type = 'javaworld'">
              <xsl:call-template name="Attrib-javaworld"/>
            </xsl:when>
            <!-- if the type itself is null (or "not (not null)") -->
            <xsl:when test="@type = not(.!='')">
              <xsl:copy>
                <xsl:for-each select="@*">
                  <xsl:copy/>
                </xsl:for-each>
                <xsl:apply-templates/>
              </xsl:copy>
            </xsl:when>
          </xsl:choose>
        </td>
      </tr>
    </table>
  </xsl:template>
  <xsl:template name="AbstractForDisplay">
    <xsl:choose>
      <xsl:when test="//abstract-extended !=''">
        <xsl:call-template name="AbstractExtended"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:call-template name="Abstract"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template name="Attribution">
    <xsl:apply-templates select="attribution"/>
  </xsl:template>
  <xsl:template name="Attrib-javaworld">
    <xsl:copy-of select="$Attrib-javaworld"/>
  </xsl:template>
  <xsl:template name="AuthorBottom">
    <table border="0" cellspacing="0" cellpadding="0" width="100%">
      <xsl:for-each select="/dw-document/*/author">
        <xsl:if test="position()=1">
          <tr>
            <td>
              <a name="author1"/>
              <xsl:if test="./bio != ''">
                <span class="atitle2">
                  <xsl:choose>
                    <xsl:when test="count(//author) = 1">
                      <xsl:value-of select="$aboutTheAuthor"/>
                    </xsl:when>
                    <xsl:otherwise>
                      <xsl:value-of select="$aboutTheAuthors"/>
                    </xsl:otherwise>
                  </xsl:choose>
                </span>
              </xsl:if>
              <br/>
              <xsl:apply-templates select="./img"/>
              <xsl:apply-templates select="./bio"/>
              <xsl:apply-templates select="./text()"/>
            </td>
          </tr>
        </xsl:if>
        <xsl:if test="position()!=1">
          <tr>
            <td>
              <p>
                <a name="author{position()}">
                  <br/>
                </a>
                <xsl:apply-templates select="./img"/>
                <xsl:apply-templates select="./bio"/>
                <xsl:apply-templates select="./text()"/>
              </p>
            </td>
          </tr>
        </xsl:if>
      </xsl:for-each>
    </table>
  </xsl:template>
  <xsl:template name="AuthorTop">
    <p>
      <xsl:for-each select="author">
        <xsl:if test="./bio!=''">
          <a href="#author{position()}">
            <xsl:apply-templates select="name"/>
          </a>
        </xsl:if>
        <xsl:if test="not(./bio!='')">
          <xsl:apply-templates select="name"/>
        </xsl:if>
         <xsl:choose>
       <xsl:when test="/dw-document/dw-article/@localsite='worldwide'">
        <xsl:if test="@email and @email!=''"> (<a href="mailto:{@email}?cc={@email_cc}&amp;subject={/dw-document//title}">
            <xsl:value-of select="@email"/>
          </a>)</xsl:if>
       </xsl:when>
       <xsl:otherwise></xsl:otherwise>
       </xsl:choose>
        <xsl:if test="count(//author) = 1">
          <br/>
        </xsl:if>
        <xsl:if test="count(//author) > 1">, </xsl:if>
        <xsl:if test="@jobtitle='' and @company=''">
          <br/>
          <br/>
          <b>
            <font color="red">
              <xsl:value-of select="$job-co-errormsg"/>
            </font>
          </b>
          <br/>
          <br/>
        </xsl:if>
        <xsl:if test="@jobtitle!=''">
          <xsl:value-of select="@jobtitle"/>
          <xsl:if test="@company!=''">, </xsl:if>
        </xsl:if>
        <xsl:if test="@company">
          <xsl:value-of select="@company"/>
        </xsl:if>
        <br/>
      </xsl:for-each>
      <xsl:if test="count(//author) > 1">
        <br/>
      </xsl:if>
      <!-- here is coding for dates on worldwide site -->
        <xsl:choose>
       <xsl:when test="/dw-document/dw-article/@localsite='worldwide'">
          <xsl:variable name="monthname">
        <xsl:call-template name="MonthName">
          <xsl:with-param name="month">
            <xsl:value-of select="//date/@month"/>
          </xsl:with-param>
        </xsl:call-template>
      </xsl:variable>
      <xsl:text disable-output-escaping="no"> </xsl:text>
      <xsl:if test="//date/@day">
        <xsl:value-of select="//date/@day"/><xsl:copy-of select="$daychar"/>
        <xsl:text> </xsl:text>
      </xsl:if>
      <xsl:value-of select="$monthname"/><xsl:copy-of select="$monthchar"/>
      <xsl:text disable-output-escaping="no">  </xsl:text>
      <xsl:value-of select="//date/@year"/><xsl:copy-of select="$yearchar"/>
     </xsl:when>
     <xsl:otherwise>
     <xsl:value-of select="//date/@year"/><xsl:copy-of select="$yearchar"/>
     <xsl:text disable-output-escaping="no">  </xsl:text>
     <xsl:variable name="monthname">
        <xsl:call-template name="MonthName">
          <xsl:with-param name="month">
            <xsl:value-of select="//date/@month"/>
          </xsl:with-param>
        </xsl:call-template>
      </xsl:variable>
     <xsl:value-of select="$monthname"/><xsl:copy-of select="$monthchar"/>
      <xsl:text disable-output-escaping="no">  </xsl:text>
     
      <xsl:if test="//date/@day">
        <xsl:value-of select="//date/@day"/><xsl:copy-of select="$daychar"/>
      </xsl:if>
       </xsl:otherwise>
    </xsl:choose>     
    
       <xsl:choose>
       <xsl:when test="/dw-document/dw-article/@localsite='worldwide'">
      <xsl:if test="date-updated">
        <xsl:variable name="monthupdatedname">
          <xsl:call-template name="MonthName">
            <xsl:with-param name="month">
              <xsl:value-of select="//date-updated/@month"/>
            </xsl:with-param>
          </xsl:call-template>
        </xsl:variable>
        <br/>
        <xsl:value-of select="$updated"/>
        <xsl:if test="//date-updated/@day">
          <xsl:value-of select="//date-updated/@day"/><xsl:copy-of select="$daychar"/>
          <xsl:text>  </xsl:text>
        </xsl:if>
        <xsl:value-of select="$monthupdatedname"/><xsl:copy-of select="$monthchar"/>

        <xsl:text>  </xsl:text>
        <xsl:value-of select="//date-updated/@year"/><xsl:copy-of select="$yearchar"/>
      </xsl:if>
   </xsl:when>
   <xsl:otherwise>
     <xsl:if test="date-updated">
     <br />
      <xsl:value-of select="//date-updated/@year"/><xsl:copy-of select="$yearchar"/>
      <xsl:text>  </xsl:text>
      <xsl:variable name="monthupdatedname">
          <xsl:call-template name="MonthName">
            <xsl:with-param name="month">
              <xsl:value-of select="//date-updated/@month"/>
            </xsl:with-param>
          </xsl:call-template>
        </xsl:variable>
         <xsl:value-of select="$monthupdatedname"/><xsl:copy-of select="$monthchar"/>
        <xsl:text>  </xsl:text>
         <xsl:if test="//date-updated/@day">
          <xsl:value-of select="//date-updated/@day"/><xsl:copy-of select="$daychar"/>
          <xsl:text>  </xsl:text>

         </xsl:if>
         </xsl:if>
          <xsl:value-of select="$updated"/>
       </xsl:otherwise>  
   </xsl:choose>
    </p>
  </xsl:template>
  <xsl:template match="b">
    <xsl:choose>
      <xsl:when test="@class='red'">
        <span class="rboldcode">
          <xsl:apply-templates/>
        </span>
      </xsl:when>
      <xsl:when test="@class='blue'">
        <span class="bboldcode">
          <xsl:apply-templates/>
        </span>
      </xsl:when>
      <xsl:when test="@class='green'">
        <span class="gboldcode">
          <xsl:apply-templates/>
        </span>
      </xsl:when>
      <xsl:when test="ancestor::code">
        <span class="boldcode">
          <xsl:apply-templates/>
        </span>
      </xsl:when>
      <xsl:otherwise>
        <xsl:copy>
          <xsl:for-each select="@*">
            <xsl:copy/>
          </xsl:for-each>
          <xsl:apply-templates/>
        </xsl:copy>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template match="bio">
    <xsl:apply-templates select="*|text()"/>
  </xsl:template>
  <xsl:template match="br">
    <xsl:if test="not(ancestor::resourcelist)">
      <xsl:copy-of select="."/>
    </xsl:if>
  </xsl:template>
  <xsl:template name="generalBreadCrumbTrail">
    <td width="100%">
      <table width="100%" cellspacing="0" cellpadding="0" border="0">
        <tr valign="top">
          <td width="100%">
            <img src="/developerworks/i/c.gif" width="2" height="4" border="0" alt=""/>
            <br/>
            <xsl:choose>
              <xsl:when test="/dw-document//content-area/@name='none'">
                <a class="dwbctl" href="{$developerworks-top-url}">developerWorks</a>
              </xsl:when>
              <xsl:when test="/dw-document//content-area/@name!='none'">
                <a class="dwbctl" href="{$developerworks-top-url}">developerWorks</a>  &gt;  
        <xsl:for-each select="/dw-document//content-area">
                  <xsl:choose>
                    <xsl:when test="@name='opensource'">
                      <a class="dwbctl" href="http://www-124.ibm.com/developerworks/oss/">
                        <xsl:call-template name="ContentAreaName">
                          <xsl:with-param name="contentarea">
                            <xsl:value-of select="@name"/>
                          </xsl:with-param>
                        </xsl:call-template>
                      </a>
                    </xsl:when>
                    <xsl:when test="@name!='opensource'">
                      <a class="dwbctl" href="{$developerworks-top-url}{@name}/">
                        <xsl:call-template name="ContentAreaName">
                          <xsl:with-param name="contentarea">
                            <xsl:value-of select="@name"/>
                          </xsl:with-param>
                        </xsl:call-template>
                      </a>
                    </xsl:when>
                  </xsl:choose>
                  <xsl:if test="position()!=last()">
                    <xsl:text> | </xsl:text>
                  </xsl:if>
                </xsl:for-each>
                <xsl:if test="/dw-document//content-area/@name='subscription'">
                    &gt; <a class="dwbctl" href="/developerworks/subscription/download.html">
                    <xsl:value-of select="Downloads"/>
                  </a>
                </xsl:if>
              </xsl:when>
            </xsl:choose>
            <xsl:choose>
              <xsl:when test="/dw-document//breadcrumbappend/@text!=''">  &gt;  <a class="dwbctl" href="{/dw-document//breadcrumbappend/@url}">
                  <xsl:value-of select="/dw-document//breadcrumbappend/@text"/>
                </a>
              </xsl:when>
            </xsl:choose>
          </td>
          <td width="136" align="right">
            <a href="{$developerworks-top-url}">
              <img src="/developerworks/i/dwlogo-small.gif" width="136" height="24" border="0" alt="developerWorks"/>
            </a>
          </td>
          <td width="5">
            <img src="/developerworks/i/c.gif" width="5" height="1" border="0" alt=""/>
          </td>
        </tr>
      </table>
    </td>
  </xsl:template>
  <xsl:template name="BundleAvailability">
    <table width="160" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td bgcolor="#000000" height="1" width="160">
          <img src="/developerworks/i/c.gif" width="160" height="1" alt=""/>
        </td>
      </tr>
      <tr>
        <td height="5" background="/developerworks/i/bg-gold.gif" align="center">
          <b>Availability:</b>
        </td>
      </tr>
      <tr>
        <td bgcolor="#666666" height="1" width="160">
          <img src="/developerworks/i/c.gif" width="160" height="1" alt=""/>
        </td>
      </tr>
      <tr>
        <td>
          <table width="160" border="0" cellspacing="0" cellpadding="1">
            <tr>
              <td>
                <xsl:if test="/dw-document/dw-subscription-landing/bundleavailability/@cd = 'yes'">
                  <img src="/developerworks/i/issuecd2.gif" width="38" height="43" border="0" alt="CD"/>
                </xsl:if>
                <xsl:if test="/dw-document/dw-subscription-landing/bundleavailability/@cdonly = 'yes'">
                  <img src="/developerworks/i/cdonly2.gif" width="29" height="43" border="0" alt="CD only"/>
                </xsl:if>
                <xsl:if test="/dw-document/dw-subscription-landing/bundleavailability/@customcd = 'yes'">
                  <img src="/developerworks/i/customcd2.gif" width="45" height="43" border="0" alt="Custom CD"/>
                </xsl:if>
                <xsl:if test="/dw-document/dw-subscription-landing/bundleavailability/@web = 'yes'">
                  <img src="/developerworks/i/web2.gif" width="31" height="43" border="0" alt="Web"/>
                </xsl:if>
                <xsl:if test="/dw-document/dw-subscription-landing/bundleavailability/@webonly = 'yes'">
                  <img src="/developerworks/i/webonly2.gif" width="31" height="43" border="0" alt="Web only"/>
                </xsl:if>
              </td>
            </tr>
            <tr>
              <td height="1">
                <img src="/developerworks/i/c.gif" width="160" height="5" alt=""/>
              </td>
            </tr>
            <tr>
              <td height="1">
                <img src="/developerworks/i/c.gif" width="160" height="5" alt=""/>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
  </xsl:template>
  <xsl:template name="BundleComponents">
    <xsl:variable name="subscriptionlevelname">
      <xsl:call-template name="SubscriptionLevel"/>
    </xsl:variable>
    <a name="downloads">
      <xsl:apply-templates select="/dw-document/dw-subscription-landing/bundlecomponents/heading"/>
    </a>
    <!-- 3.0 01/20 tdc: Changed next two para's per Barry -->
    <p>The components of this bundle are designed to be used or installed together; they are not stand-alone products.  They are intended to be used or installed with the other components in this bundle listed below.  If the order of installation is important, the sequence number is indicated to the left of the component. </p>
    <p>You must have a developerWorks Subscription with <xsl:value-of select="$subscriptionlevelname"/> Level access to download each component of this bundle.  <a href="/developerworks/subscription/newcomers.html">Learn more</a> about the developerWorks Subscription or <a href="/developerworks/subscription/ordering.html">purchase a subscription</a> now.</p>
    <xsl:for-each select="/dw-document/dw-subscription-landing/bundlecomponents/bundlecomponent">
      <a href="{@url}">
        <xsl:value-of select="@text"/>
      </a>
      <br/>
    </xsl:for-each>
    <p>
      <a href="#top">
        <img src="/developerworks/i/u.gif" width="16" height="16" border="no" alt="top of page"/>Top of page</a>
    </p>
  </xsl:template>
  <xsl:template name="BundleNote">
    <p>
      <font color="#cc6633">
        <b>NOTE:  </b>
      </font>
      <b>
        <i>
          <xsl:value-of select="/dw-document/dw-subscription-landing/title"/>
        </i> is a bundle that includes multiple components intended to be used or installed together.</b>
    </p>
    <p>
      <a href="#downloads">
        <img src="/developerworks/i/fw_c.gif" width="16" height="16" vspace="1" hspace="4" align="left" alt="View bundle components" border="no"/>  View bundle components</a>
    </p>
  </xsl:template>
  <xsl:template name="BundleMoreAbout">
    <xsl:if test="/dw-document/dw-subscription-landing/bundlemoreabout">
      <table width="160" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td bgcolor="#000000" height="1" width="160">
            <img src="/developerworks/i/c.gif" width="160" height="1" alt=""/>
          </td>
        </tr>
        <tr>
          <td height="5" background="/developerworks/i/bg-gold.gif" align="center">
            <b>More about this product:</b>
          </td>
        </tr>
        <tr>
          <td bgcolor="#666666" height="1" width="160">
            <img src="/developerworks/i/c.gif" width="160" height="1" alt=""/>
          </td>
        </tr>
        <tr>
          <td>
            <table width="160" border="0" cellspacing="0" cellpadding="1">
              <tr>
                <td>
                  <xsl:apply-templates select="/dw-document/dw-subscription-landing/bundlemoreabout/a"/>
                </td>
              </tr>
              <tr>
                <td height="1">
                  <img src="/developerworks/i/c.gif" width="160" height="10" alt=""/>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </xsl:if>
  </xsl:template>
  <xsl:template name="BundleOverview">
    <xsl:apply-templates select="/dw-document/dw-subscription-landing/bundleoverview"/>
  </xsl:template>
  <xsl:template name="BundleLanguages">
    <xsl:if test="not(dw-document/dw-subscription-landing/bundlelanguages='')">
      <xsl:apply-templates select="/dw-document/dw-subscription-landing/bundlelanguages/*"/>
    </xsl:if>
  </xsl:template>
  <xsl:template name="BundlePlatforms">
    <xsl:if test="not(dw-document/dw-subscription-landing/bundleplatforms='')">
      <xsl:apply-templates select="/dw-document/dw-subscription-landing/bundleplatforms/*"/>
    </xsl:if>
  </xsl:template>
  <xsl:template name="BundleRequirements">
    <xsl:apply-templates select="/dw-document/dw-subscription-landing/bundlerequirements/*"/>
  </xsl:template>
  <xsl:template match="code">
    <xsl:if test="@type='inline' or not(@type)">
      <code>
        <xsl:apply-templates/>
      </code>
    </xsl:if>
    <xsl:if test="@type='section'">
      <xsl:apply-templates select="heading"/>
      <xsl:if test="@width">
        <xsl:choose>
          <xsl:when test="contains(@width, '%')">
            <table border="1" cellspacing="0" cellpadding="5" width="{@width}" bgcolor="#CCCCCC">
              <tr>
                <td>
                  <pre>
                    <code>
                      <xsl:apply-templates select="*[not(name() = 'heading')] | text()"/>
                    </code>
                  </pre>
                </td>
              </tr>
            </table>
          </xsl:when>
          <xsl:when test="not(contains(@width, '%'))">
            <xsl:choose>
              <xsl:when test="@width&lt;=600">
                <table border="1" cellspacing="0" cellpadding="5" width="{@width}" bgcolor="#CCCCCC">
                  <tr>
                    <td>
                      <pre>
                        <code>
                          <xsl:for-each select="*|text()">
                            <xsl:if test="not(self::heading)">
                              <xsl:apply-templates select="."/>
                            </xsl:if>
                          </xsl:for-each>
                        </code>
                      </pre>
                    </td>
                  </tr>
                </table>
              </xsl:when>
              <xsl:when test="@width>600">
                <table border="1" cellspacing="0" cellpadding="5" width="600" bgcolor="#CCCCCC">
                  <tr>
                    <td>
                      <pre>
                        <code>
                          <xsl:for-each select="*|text()">
                            <xsl:if test="not(self::heading)">
                              <xsl:apply-templates select="."/>
                            </xsl:if>
                          </xsl:for-each>
                        </code>
                      </pre>
                    </td>
                  </tr>
                </table>
              </xsl:when>
            </xsl:choose>
          </xsl:when>
        </xsl:choose>
      </xsl:if>
      <xsl:if test="not(@width!='')">
        <table border="1" cellspacing="0" cellpadding="5" width="100%" bgcolor="#CCCCCC">
          <tr>
            <td>
              <pre>
                <code>
                  <xsl:for-each select="*|text()">
                    <xsl:if test="not(self::heading)">
                      <xsl:apply-templates select="."/>
                    </xsl:if>
                  </xsl:for-each>
                </code>
              </pre>
            </td>
          </tr>
        </table>
      </xsl:if>
    </xsl:if>
  </xsl:template>
  <xsl:template name="Default" match="ul | ol | li | i | sup | sub | pre | blockquote | dd | dl">
    <xsl:copy>
      <xsl:for-each select="@*">
        <xsl:copy/>
      </xsl:for-each>
      <xsl:apply-templates select="*|text()"/>
    </xsl:copy>
  </xsl:template>
  <xsl:template match="dt">
    <dt>
      <xsl:for-each select="@*">
        <xsl:copy/>
      </xsl:for-each>
      <b>
        <xsl:apply-templates select="*|text()"/>
      </b>
    </dt>
  </xsl:template>
  <xsl:template name="Download">
    <xsl:choose>
      <xsl:when test="/dw-document//content-area[1]/@name = 'websphere' or /dw-document//content-area[1]/@name = 'db2'">
        <xsl:if test="(download-feature/@url-ftp!='') or (download-feature/@url-http!='')">
          <a name="download"/>
          <span class="atitle2">
            <xsl:value-of select="$download-heading"/>
          </span>
          <table border="0" cellpaddding="0" cellspacing="4">
            <tr>
              <td class="download" bgcolor="bbccff">
                <xsl:value-of select="$download-description-heading"/>
              </td>
              <td class="download" bgcolor="bbccff">
                <xsl:value-of select="$download-filetype-heading"/>
              </td>
              <td class="download" bgcolor="bbccff">
                <xsl:value-of select="$download-filesize-heading"/>
              </td>
              <td class="download" bgcolor="bbccff">
                <xsl:value-of select="$download-method-heading"/>
              </td>
            </tr>
            <xsl:for-each select="download-feature">
              <xsl:if test="@url-ftp!='' or @url-http!=''">
                <xsl:if test="position()!=last()">
                  <tr valign="bottom">
                    <td class="download">
                      <xsl:value-of select="@filename"/>
                    </td>
                    <td class="download">
                      <xsl:value-of select="@filetype"/>
                    </td>
                    <td class="download">
                      <xsl:value-of select="@size"/>
                    </td>
                    <td class="download">
                      <xsl:if test="(@url-http!='')">HTTP<a href="{@url-http}">
                          <img src="/developerworks/i/download_now.gif" width="21" height="21" border="0" alt="{$download-http-alt}"/>
                        </a>
                        <img src="/developerworks/i/c.gif" width="8" height="1" alt=""/>
                      </xsl:if>
                      <xsl:if test="(@url-ftp!='')">FTP<a href="{@url-ftp}">
                          <img src="/developerworks/i/download_now.gif" width="21" height="21" border="0" alt="{$download-ftp-alt}"/>
                        </a>
                      </xsl:if>
                    </td>
                  </tr>
                </xsl:if>
                <xsl:if test="position()=last()">
                  <tr valign="bottom">
                    <td class="download">
                      <xsl:value-of select="@filename"/>
                    </td>
                    <td class="download">
                      <xsl:value-of select="@filetype"/>
                    </td>
                    <td class="download">
                      <xsl:value-of select="@size"/>
                    </td>
                    <td class="download">
                      <xsl:if test="(@url-http!='')">HTTP<a href="{@url-http}">
                          <img src="/developerworks/i/download_now.gif" width="21" height="21" border="0" alt="{$download-http-alt}"/>
                        </a>
                        <img src="/developerworks/i/c.gif" width="8" height="1" alt=""/>
                      </xsl:if>
                      <xsl:if test="(@url-ftp!='')">FTP<a href="{@url-ftp}">
                          <img src="/developerworks/i/download_now.gif" width="21" height="21" border="0" alt="{$download-ftp-alt}"/>
                        </a>
                      </xsl:if>
                    </td>
                  </tr>
                </xsl:if>
              </xsl:if>
            </xsl:for-each>
          </table>
          <p>
            <img src="/developerworks/i/fprtarrow.gif" border="0" height="11" width="12" alt="*"/>
            <a href="/developerworks/{$dclanguage-content}/library/whichmethod.html"><xsl:value-of select="$download-method-link"/></a>
          </p>
        </xsl:if>
      </xsl:when>
      <xsl:otherwise>
        <br/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template name="dwsubLinks">
    <table width="160" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td bgcolor="#000000" height="1" width="160">
          <img src="/developerworks/i/c.gif" width="160" height="1" alt=""/>
        </td>
      </tr>
      <tr>
        <td height="5" background="/developerworks/i/bg-gold.gif" align="center">
         <b>developerWorks Subscription:</b>
        </td>
      </tr>
      <tr>
        <td bgcolor="#666666" height="1" width="160">
          <img src="/developerworks/i/c.gif" width="160" height="1" alt=""/>
        </td>
      </tr>
      <tr>
        <td>
          <table width="160" border="0" cellspacing="0" cellpadding="1">
            <tr>
              <td>
                <a href="/developerworks/subscription/newcomers.html">Learn about developerWorks Subscription</a>
              </td>
            </tr>
            <tr>
              <td height="1">
                <img src="/developerworks/i/c.gif" width="160" height="5" alt=""/>
              </td>
            </tr>
            <tr>
              <td>
                <a href="/developerworks/subscription/download.html 
">Browse the online catalog</a>
              </td>
            </tr>
            <tr>
              <td height="1">
                <img src="/developerworks/i/c.gif" width="160" height="5" alt=""/>
              </td>
            </tr>
            <tr>
              <td>
                <a href="/developerworks/subscription/ordering.html">Purchase a subscription</a>
              </td>
            </tr>
            <tr>
              <td height="1">
                <img src="/developerworks/i/c.gif" width="160" height="5" alt=""/>
              </td>
            </tr>
            <tr>
              <td height="1">
                <img src="/developerworks/i/c.gif" width="160" height="5" alt=""/>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
  </xsl:template>
  <xsl:template name="dwsubCustomCDs">
    <table width="160" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td bgcolor="#000000" height="1" width="160">
          <img src="/developerworks/i/c.gif" width="160" height="1" alt=""/>
        </td>
      </tr>
      <tr>
        <td height="5" background="/developerworks/i/bg-gold.gif" align="center">
          <b>My subscription:</b>
        </td>
      </tr>
      <tr>
        <td bgcolor="#666666" height="1" width="160">
          <img src="/developerworks/i/c.gif" width="160" height="1" alt=""/>
        </td>
      </tr>
      <tr>
        <td>
          <table width="160" border="0" cellspacing="0" cellpadding="1">
            <tr>
              <td>
                <a href="https://www7b.software.ibm.com/webapp/toolbox/CustomDisc?event=MainNoLoginURL">Create custom CDs</a>
              </td>
            </tr>
            <tr>
              <td height="1">
                <img src="/developerworks/i/c.gif" width="160" height="5" alt=""/>
              </td>
            </tr>
            <tr>
              <td>Sign up for <a href="https://www7b.software.ibm.com/webapp/toolbox/jsp/DTB_MyToolboxUP.jsp
">Content notification</a>
              </td>
            </tr>
            <tr>
              <td height="1">
                <img src="/developerworks/i/c.gif" width="160" height="5" alt=""/>
              </td>
            </tr>
            <tr>
              <td height="1">
                <img src="/developerworks/i/c.gif" width="160" height="5" alt=""/>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
  </xsl:template>
  <xsl:template name="dwsubSupportLinks">
    <table width="160" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td bgcolor="#000000" height="1" width="160">
          <img src="/developerworks/i/c.gif" width="160" height="1" alt=""/>
        </td>
      </tr>
      <tr>
        <td height="5" background="/developerworks/i/bg-gold.gif" align="center">
          <b>Support:</b>
        </td>
      </tr>
      <tr>
        <td bgcolor="#666666" height="1" width="160">
          <img src="/developerworks/i/c.gif" width="160" height="1" alt=""/>
        </td>
      </tr>
      <tr>
        <td>
          <table width="160" border="0" cellspacing="0" cellpadding="1">
            <tr>
              <td>
                <a href="/developerworks/subscription/support.html">Learn more or access support resources</a>
              </td>
            </tr>
            <tr>
              <td height="1">
                <img src="/developerworks/i/c.gif" width="160" height="10" alt=""/>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
  </xsl:template>
  <xsl:template match="figure">
    <p>
      <xsl:apply-templates/>
    </p>
  </xsl:template>
  <xsl:template name="FilterAbstract">
    <xsl:variable name="doublequote">"</xsl:variable>
    <xsl:variable name="singlequote">'</xsl:variable>
    <xsl:variable name="newline">
      <xsl:text>
</xsl:text>
    </xsl:variable>
    <xsl:choose>
      <xsl:when test="//abstract!=''">
        <xsl:value-of select="translate(//abstract,$doublequote,$singlequote)"/>
      </xsl:when>
      <xsl:when test="//abstract=''">
        <xsl:value-of select="translate(//abstract-extended,$doublequote,$singlequote)"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="translate(//abstract-extended,$doublequote,$singlequote)"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template name="Footer">
    <xsl:copy-of select="$footer-inc"/>
  </xsl:template>
  <xsl:template name="FullTitle">
    <xsl:param name="escapeQuotes" select="false()"/>
     <xsl:if test="/dw-document/dw-subscription-landing/content-area/@name='subscription'">
      <xsl:text disable-output-escaping="no">developerWorks Subscription : </xsl:text>
    </xsl:if>
    <xsl:if test="string-length(/dw-document/*/seriestitle) &gt; 0">
      <xsl:choose>
        <xsl:when test="$escapeQuotes and 
                       contains(/dw-document/*/seriestitle, '&quot;')">
          <xsl:call-template name="replace-substring">
            <xsl:with-param name="original" select="/dw-document/*/seriestitle"/>
            <xsl:with-param name="substring" select="'&quot;'"/>
            <xsl:with-param name="replacement" select="'\&quot;'"/>
          </xsl:call-template>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="/dw-document/*/seriestitle"/>
        </xsl:otherwise>
      </xsl:choose>
       <xsl:text>: </xsl:text>
       </xsl:if>
    <xsl:if test="string-length(/dw-document/*/title) &gt; 0">
      <xsl:choose>
        <xsl:when test="$escapeQuotes and 
                       contains(/dw-document/*/title, '&quot;')">
          <xsl:call-template name="replace-substring">
            <xsl:with-param name="original" select="/dw-document/*/title"/>
            <xsl:with-param name="substring" select="'&quot;'"/>
            <xsl:with-param name="replacement" select="'\&quot;'"/>
          </xsl:call-template>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="/dw-document/*/title"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:if>
  </xsl:template>
  <xsl:template match="dw-article/docbody//heading">
    <xsl:variable name="newid">
      <xsl:choose>
        <xsl:when test="@refname != ''">
          <xsl:value-of select="@refname"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="generate-id()"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:choose>
      <xsl:when test="@type='code'">
        <a name="{$newid}">
          <b>
            <xsl:apply-templates select="*|text()"/>
          </b>
        </a>
        <br/>
      </xsl:when>
      <xsl:when test="@type='figure'"><xsl:copy-of select="$figurechar"/>
        <a name="{$newid}">
          <b>
            <xsl:value-of select="."/>
          </b>
        </a>
        <br/>
      </xsl:when>
      <xsl:otherwise>
        <p>
          <xsl:choose>
            <xsl:when test="@type='major'">
              <a name="{$newid}">
                <span class="atitle2">
                  <xsl:value-of select="."/>
                </span>
              </a>
              <br/>
            </xsl:when>
            <xsl:when test="@type='minor'">
              <a name="{$newid}">
                <span class="atitle3">
                  <xsl:value-of select="."/>
                </span>
              </a>
              <br/>
            </xsl:when>
            <xsl:when test="@type='sidebar'">
              <a name="{$newid}">
                <b>
                  <xsl:value-of select="."/>
                </b>
              </a>
              <br/>
            </xsl:when>
          </xsl:choose>
          <xsl:if test="name(following-sibling::*[1]) = 'p'">
            <xsl:for-each select="following-sibling::*[1]">
              <xsl:call-template name="processParagraph"/>
            </xsl:for-each>
          </xsl:if>
        </p>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template match="dw-sidefile/docbody//heading|dw-subscription-landing//heading">
    <xsl:variable name="newid">
      <xsl:choose>
        <xsl:when test="@refname != ''">
          <xsl:value-of select="@refname"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="generate-id()"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:choose>
      <xsl:when test="@type='major'">
        <a name="{$newid}">
          <span class="atitle2">
            <xsl:value-of select="."/>
          </span>
        </a>
        <br/>
      </xsl:when>
      <xsl:when test="@type='minor'">
        <a name="{$newid}">
          <span class="atitle3">
            <xsl:value-of select="."/>
          </span>
        </a>
        <br/>
      </xsl:when>
      <xsl:when test="@type='sidebar'">
        <a name="{$newid}">
          <b>
            <xsl:value-of select="."/>
          </b>
        </a>
        <br/>
      </xsl:when>
      <xsl:when test="@type='figure'">
        <a name="{$newid}">
          <b>
            <xsl:value-of select="."/>
          </b>
        </a>
        <br/>
      </xsl:when>
      <xsl:when test="@type='code'">
        <a name="{$newid}">
          <b>
            <xsl:value-of select="."/>
          </b>
        </a>
      </xsl:when>
    </xsl:choose>
  </xsl:template>
  <xsl:template match="htmlinsert">
    <xsl:message>AAAAARRRRRRRRRRGGGGGGGGGHHHHHHHHHH!!!!!!!!!!!!!!!!!!!</xsl:message>
    <xsl:value-of select="." disable-output-escaping="yes"/>
  </xsl:template>
  <xsl:template name="HTMLTitle">
    <xsl:text>developerWorks : </xsl:text>
    <xsl:for-each select="/dw-document/dw-sidefile/content-area">
      <xsl:call-template name="ContentAreaName">
        <xsl:with-param name="contentarea">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:if test="position()!=last()"> | </xsl:if>
    </xsl:for-each>
    <xsl:text> : </xsl:text>
    <xsl:call-template name="FullTitle"/>
  </xsl:template>
  <xsl:template name="IconLinks">
    <xsl:if test="forumurl/@url!=''">
      <a href="javascript:void forumWindow()">
        <img src="{$icon-discuss-gif}" width="42" height="26" border="0" alt="{$icon-discuss-alt}"/>
      </a>
    </xsl:if>
    <xsl:choose>
      <xsl:when test="/dw-document//content-area[1]/@name = 'websphere' or /dw-document//content-area[1]/@name = 'db2'">
        <xsl:if test="download-feature/@url-ftp!='' or download-feature/url-http!=''">
          <a href="#download">
            <img src="{$icon-code-gif}" width="30" height="26" border="0" alt="{$icon-code-download-alt}"/>
          </a>
        </xsl:if>
      </xsl:when>
      <xsl:otherwise>
        <xsl:if test="download-feature/@downloadtype='code' and download-feature/@url-ftp!=''">
          <a href="{download-feature/@url-ftp}">
            <img src="{$icon-code-gif}" width="30" height="26" border="0" alt="{$icon-code-alt}"/>
          </a>
        </xsl:if>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:if test="pdf/@url!=''">
      <a href="{pdf/@url}">
        <img src="{$icon-pdf-gif}" width="35" height="26" border="0" alt="{pdf/@size}"/>
      </a>
    </xsl:if>
    <xsl:choose>
    <!-- 3.0 3/10 tdc:  Added additional test for localsite not present -->
       <xsl:when test="/dw-document/dw-article/@localsite='worldwide' or not(/dw-document/dw-article/@localsite)">
          <xsl:if test="not(/dw-document/dw-subscription-landing)">
              <a href="javascript:void newWindow()">
                  <img src="/developerworks/i/icon-email.gif" width="46" height="26" border="0" alt="e-mail it!"/>
                      </a>
          </xsl:if>
    </xsl:when>
    <xsl:otherwise>
    </xsl:otherwise>
    </xsl:choose>
    
  </xsl:template>
  <xsl:template match="img">
    <xsl:choose>
      <xsl:when test="ancestor::author and not(@align!='')">
        <xsl:copy>
          <xsl:for-each select="@*">
            <xsl:copy/>
          </xsl:for-each>
          <xsl:attribute name="align">left</xsl:attribute>
        </xsl:copy>
      </xsl:when>
      <xsl:otherwise>
        <xsl:copy-of select="."/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template name="Indicators">
    <xsl:choose>
      <xsl:when test="/dw-document/dw-article">
        <xsl:variable name="levelname">
          <xsl:call-template name="SkillLevel"/>
        </xsl:variable>
        <xsl:if test="/dw-document/dw-article/@level">
          <table cellpadding="0" cellspacing="0" border="0">
            <tr align="left" valign="top">
              <td>
                <p>
                  <xsl:value-of select="$level-text-heading"/>
                  <xsl:value-of select="$levelname"/>
                </p>
              </td>
            </tr>
          </table>
        </xsl:if>
        <xsl:if test="/dw-document/dw-article/content-area[1]/@name = 'webservices' and
                /dw-document/dw-article/title != '{$Summary}'">
          <table cellpadding="0" cellspacing="0" border="0">
            <tr align="left" valign="top">
              <td>
                <xsl:text disable-output-escaping="yes"><![CDATA[<!--#config errmsg="" --><!--#include virtual="/inc/jsp/ratings_display.jsp?article_id=]]></xsl:text>
                <xsl:value-of select="/dw-document//id/@domino-uid"/>
                <xsl:text disable-output-escaping="yes"><![CDATA[" --><!--#config errmsg="[An Error has occured using an include directive]" -->]]></xsl:text>
              </td>
            </tr>
          </table>
        </xsl:if>
      </xsl:when>
      <xsl:when test="/dw-document/dw-subscription-landing">
        <xsl:variable name="subscriptionlevelname">
          <xsl:call-template name="SubscriptionLevel"/>
        </xsl:variable>
        <xsl:if test="/dw-document/dw-subscription-landing/@subscriptionlevel">
          <table cellpadding="0" cellspacing="0" border="0">
            <tr align="left" valign="top">
              <td>
                <p>Subscription level: <xsl:value-of select="$subscriptionlevelname"/>
                </p>
              </td>
            </tr>
          </table>
        </xsl:if>
      </xsl:when>
      <xsl:otherwise/>
    </xsl:choose>
  </xsl:template>
  <!-- LK to verify with teams that they have not changed any of the following scripts -->
  <xsl:template name="articleJavaScripts">
    <script type="text/javascript" language="JavaScript" src="http://www-106.ibm.com/developerworks/js/style.js"/>
    <script type="text/javascript" language="JavaScript" src="http://www-106.ibm.com/developerworks/js/spinbox.js"/>

    
    <!-- START FORUM SCRIPTS - MUST OCCUR IN THIS ORDER -->
    <script type="text/javascript" language="JavaScript">var title = "<xsl:call-template name="FullTitle">
        <xsl:with-param name="escapeQuotes" select="true()"/>
      </xsl:call-template>"; </script>
    <script type="text/javascript" language="JavaScript">var forumURL = "<xsl:value-of select="/dw-document/dw-article/forumurl/@url" disable-output-escaping="yes"/>"; </script>
    <script type="text/javascript" language="JavaScript">var contentAreaList = "<xsl:call-template name="ContentAreaList"/>"; </script>
    <script type="text/javascript" language="JavaScript" src="http://www-106.ibm.com/developerworks/js/forumwindow.js"/>
    <!-- END OF FORUM SCRIPTS -->
    <!-- START E-MAIL IT! SCRIPTS - MUST OCCUR IN THIS ORDER -->
    <script type="text/javascript" language="JavaScript">var emailAbstract = "<xsl:call-template name="FilterAbstract"/>"; </script>
    <script type="text/javascript" language="JavaScript" SRC="http://www-106.ibm.com/developerworks/email/grabtitle.js"/>
    <script type="text/javascript" language="JavaScript" SRC="http://www-106.ibm.com/developerworks/email/emailfriend2.js"/>
    <!-- END OF E-MAIL IT! SCRIPTS -->
    <!-- START DEMO SCRIPTS - MUST OCCUR IN THIS ORDER -->
    <script type="text/javascript" language="JavaScript">var demoURL = "<xsl:value-of select="/dw-document/dw-article/demourl/@url" disable-output-escaping="yes"/>"; </script>
    <script type="text/javascript" language="JavaScript" SRC="http://www-106.ibm.com/developerworks/js/demowindow.js"/>
    <!-- END OF DEMO SCRIPTS -->
   
  </xsl:template>
  <xsl:template name="dwsubJavaScripts">
    <script type="text/javascript" language="JavaScript" src="http://www-106.ibm.com/developerworks/js/style.js"/>
    <script type="text/javascript" language="JavaScript" src="http://www-106.ibm.com/developerworks/js/spinbox.js"/>
  </xsl:template>
  <xsl:template name="sidefileJavaScripts">
    <script type="text/javascript" language="JavaScript" src="http://www-106.ibm.com/developerworks/js/style.js"/>
    <script type="text/javascript" language="JavaScript" src="http://www-106.ibm.com/developerworks/js/spinbox.js"/>
    <!-- START FORUM SCRIPTS - MUST OCCUR IN THIS ORDER -->
    <script type="text/javascript" language="JavaScript">var forumURL = "<xsl:value-of select="/dw-document/dw-sidefile/forumurl/@url" disable-output-escaping="yes"/>"; </script>
    <script type="text/javascript" language="JavaScript" src="http://www-106.ibm.com/developerworks/js/forumwindow.js"/>
    <!-- END OF FORUM SCRIPTS -->
    <!-- START E-MAIL IT! SCRIPTS - MUST OCCUR IN THIS ORDER -->
    <script type="text/javascript" language="JavaScript">var emailAbstract = "<xsl:call-template name="FilterAbstract"/>"; </script>
    <script type="text/javascript" language="JavaScript" SRC="http://www-106.ibm.com/developerworks/email/grabtitle.js"/>
    <script type="text/javascript" language="JavaScript" SRC="http://www-106.ibm.com/developerworks/email/emailfriend2.js"/>
    <!-- END OF E-MAIL IT! SCRIPTS -->
    <!-- 3.0 3/5 tdc:  Removed demo scripts.  -->
  </xsl:template>
  <xsl:template match="meta">
    <xsl:copy-of select="."/>
  </xsl:template>
  <xsl:template name="Masthead">
    <xsl:copy-of select="$topmast-inc"/>
  </xsl:template>
  <xsl:template name="META">
    <xsl:text disable-output-escaping="yes"><![CDATA[<meta http-equiv="PICS-Label" content='(PICS-1.1
    "http://www.icra.org/ratingsv02.html" l gen true r (cz 1 lz 1 nz 1 oz 1 vz 1)
    "http://www.rsac.org/ratingsv01.html" l gen true r (n 0 s 0 v 0 l 0)
    "http://www.classify.org/safesurf/" l gen true r (SS~~000 1))'/>]]></xsl:text>
    <meta http-equiv="Expires" content="0"/>
    <xsl:apply-templates select="meta"/>
    <xsl:variable name="filteredabstract">
      <xsl:call-template name="FilterAbstract"/>
    </xsl:variable>
    <xsl:text disable-output-escaping="yes">&lt;meta name="ABSTRACT" content="</xsl:text>
    <xsl:value-of select="$filteredabstract"/>
    <xsl:text disable-output-escaping="yes">"/&gt;
&lt;meta name="DESCRIPTION" content="</xsl:text>
    <xsl:value-of select="$filteredabstract"/>
    <xsl:text disable-output-escaping="yes">"/&gt;</xsl:text>
    <xsl:variable name="metatitle">
      <xsl:call-template name="FullTitle"/>
    </xsl:variable>
    <xsl:text disable-output-escaping="yes">&lt;meta name="TITLE" content="</xsl:text>
    <xsl:if test="/dw-document/dw-subscription-landing/content-area/@name='subscription'">
      <xsl:text disable-output-escaping="no">developerWorks Subscription : </xsl:text>
    </xsl:if>
    <xsl:value-of select="$metatitle"/>
    <xsl:text disable-output-escaping="yes">"/&gt;</xsl:text>
    <xsl:variable name="ownerteam">
      <xsl:call-template name="OwnerTeam"/>
    </xsl:variable>
    <xsl:text disable-output-escaping="yes">
&lt;meta name="OWNER" content="</xsl:text>
    <xsl:value-of select="$owner-meta-url"/>
    <xsl:if test="/dw-document/dw-article/@localsite='worldwide'">
      <xsl:value-of select="$ownerteam"/>
    </xsl:if>
    <xsl:text disable-output-escaping="yes">"/&gt;</xsl:text>
    <xsl:if test="not(dw-document/dw-subscription-landing)">
      <xsl:variable name="dcdate">
        <xsl:choose>
          <xsl:when test="//date-updated">
            <xsl:value-of select="//date-updated/@year"/>
            <xsl:text>-</xsl:text>
            <xsl:value-of select="//date-updated/@month"/>
            <xsl:text>-</xsl:text>
            <xsl:value-of select="//date-updated/@day"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:value-of select="//date/@year"/>
            <xsl:text>-</xsl:text>
            <xsl:value-of select="//date/@month"/>
            <xsl:text>-</xsl:text>
            <xsl:value-of select="//date/@day"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:variable>
      <xsl:text disable-output-escaping="yes">&lt;meta name="DC.Date" scheme="iso8601" content="</xsl:text>
      <xsl:value-of select="$dcdate"/>
      <xsl:text disable-output-escaping="yes">"/&gt;</xsl:text>
    </xsl:if>
    <xsl:text disable-output-escaping="yes">&lt;meta name="DC.Language" scheme="rfc1766" content="</xsl:text>
    <xsl:value-of select="$dclanguage-content"/>
    <xsl:text disable-output-escaping="yes">"/&gt;</xsl:text>
    <xsl:text disable-output-escaping="yes">&lt;meta name="IBM.Country" content="</xsl:text>
    <xsl:value-of select="$ibmcountry-content"/>
    <xsl:text disable-output-escaping="yes">"/&gt;</xsl:text>
    <xsl:text disable-output-escaping="yes">&lt;meta name="SECURITY" content="Public"/&gt;</xsl:text>
    <xsl:if test="not(dw-document/dw-subscription-landing)">
      <xsl:variable name="copyrightyear">
        <xsl:value-of select="//date/@year"/>
      </xsl:variable>
      <xsl:text disable-output-escaping="yes">&lt;meta name="DC.Rights" content="Copyright (c) </xsl:text>
      <xsl:value-of select="$copyrightyear"/>
      <xsl:text disable-output-escaping="yes"> by IBM Corporation"/&gt;</xsl:text>
    </xsl:if>
    <xsl:choose>
      <xsl:when test="/dw-document/dw-article">
        <xsl:text disable-output-escaping="yes"> &lt;meta name="ROBOTS" content="index,follow"/&gt;</xsl:text>
      </xsl:when>
      <xsl:when test="/dw-document/dw-sidefile">
        <xsl:text disable-output-escaping="yes"> &lt;meta name="ROBOTS" content="noindex,nofollow"/&gt;</xsl:text>
      </xsl:when>
      <xsl:when test="/dw-document/dw-subscription-landing">
        <xsl:text disable-output-escaping="yes"> &lt;meta name="ROBOTS" content="index,follow"/&gt;</xsl:text>
      </xsl:when>
      <xsl:otherwise>
        <xsl:text disable-output-escaping="yes"> &lt;meta name="ROBOTS" content="index,follow"/&gt;</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template name="MonthName">
    <xsl:param name="month"/>
    <xsl:choose>
      <xsl:when test="not(number($month))">
        <xsl:value-of select="$month"/>
      </xsl:when>
      <xsl:when test="$month = '1' ">
        <xsl:value-of select="$month-1-text"/>
      </xsl:when>
      <xsl:when test="$month = '2' ">
        <xsl:value-of select="$month-2-text"/>
      </xsl:when>
      <xsl:when test="$month = '3' ">
        <xsl:value-of select="$month-3-text"/>
      </xsl:when>
      <xsl:when test="$month = '4' ">
        <xsl:value-of select="$month-4-text"/>
      </xsl:when>
      <xsl:when test="$month = '5' ">
        <xsl:value-of select="$month-5-text"/>
      </xsl:when>
      <xsl:when test="$month = '6' ">
        <xsl:value-of select="$month-6-text"/>
      </xsl:when>
      <xsl:when test="$month = '7' ">
        <xsl:value-of select="$month-7-text"/>
      </xsl:when>
      <xsl:when test="$month = '8' ">
        <xsl:value-of select="$month-8-text"/>
      </xsl:when>
      <xsl:when test="$month = '9' ">
        <xsl:value-of select="$month-9-text"/>
      </xsl:when>
      <xsl:when test="$month = '10' ">
        <xsl:value-of select="$month-10-text"/>
      </xsl:when>
      <xsl:when test="$month = '11' ">
        <xsl:value-of select="$month-11-text"/>
      </xsl:when>
      <xsl:when test="$month = '12' ">
        <xsl:value-of select="$month-12-text"/>
      </xsl:when>
    </xsl:choose>
  </xsl:template>
  <xsl:template name="OwnerTeam">
    <xsl:choose>
      <xsl:when test="/dw-document//content-area[1]/@name = 'autonomic'
             or /dw-document//content-area[1]/@name = 'grid' 
		or /dw-document//content-area[1]/@name = 'java' 
		or /dw-document//content-area[1]/@name = 'linux' 
		or /dw-document//content-area[1]/@name = 'opensource' 
		or /dw-document//content-area[1]/@name = 'security' 
		or /dw-document//content-area[1]/@name = 'web'  
		or /dw-document//content-area[1]/@name = 'webservices' 
		or /dw-document//content-area[1]/@name = 'wireless'  
		or /dw-document//content-area[1]/@name = 'xml'">developerworks</xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="/dw-document//content-area[1]/@name"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template match="docbody//p">
    <xsl:if test="not(preceding-sibling::*[1][name()='heading'][@type='major']) and
                  not(preceding-sibling::*[1][name()='heading'][@type='minor']) and
                  not(preceding-sibling::*[1][name()='heading'][@type='sidebar'])">
      <p>
        <xsl:apply-templates/>
      </p>
    </xsl:if>
  </xsl:template>
  <xsl:template match="dw-sidefile/docbody/p">
    <p>
      <xsl:apply-templates select="*|text()"/>
    </p>
  </xsl:template>
  <xsl:template name="processParagraph">
    <xsl:apply-templates select="*|text()"/>
  </xsl:template>
  <xsl:template match="docbody">
    <xsl:apply-templates select="a | b | blockquote | br | code | dl | figure | heading | i | img | ol | p | pre | sidebar | table | sub | sup | ul | text()"/>
  </xsl:template>
  <xsl:include href="dw-ratingsform-3.0.xsl"/>
  <xsl:template match="relatedlist/a">
    <tr>
      <td>
              <xsl:copy>
              <xsl:for-each select="@*">
                <xsl:copy/>
              </xsl:for-each>
              <xsl:apply-templates/>
            </xsl:copy>
         
      </td>
    </tr>
    <tr>
      <td height="1">
        <img src="/developerworks/i/c.gif" width="160" height="5" alt=""/>
      </td>
    </tr>
  </xsl:template>
  <xsl:template name="articleRelatedContents">
    <xsl:choose>
      <xsl:when test="/dw-document/dw-article/@ratings='no' and
                                 /dw-document/dw-article/@related_contents='no' and 
                                 /dw-document/dw-article/@toc='no'">
        <xsl:message>Note about related contents:  Even though this file was coded with the dw-article element, the stylesheet made the assumption that this is a sidefile, not an article.</xsl:message>
      </xsl:when>
      <xsl:otherwise>
        <xsl:if test="//relatedlist/a/@href!=''">
          <table width="160" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td bgcolor="#000000" height="1" width="160">
                <img src="/developerworks/i/c.gif" width="160" height="1" alt=""/>
              </td>
            </tr>
            <tr>
              <td height="5" background="/developerworks/i/bg-gold.gif" align="center">
                <b>
                  <xsl:value-of select="$related-content-heading"/>
                </b>
              </td>
            </tr>
            <tr>
              <td bgcolor="#666666" height="1" width="160">
                <img src="/developerworks/i/c.gif" width="160" height="1" alt=""/>
              </td>
            </tr>
            <tr>
              <td>
                <table width="160" border="0" cellspacing="0" cellpadding="1">
                  <xsl:apply-templates select="/dw-document//relatedlist/a"/>
                  <tr>
                    <td height="1">
                      <img src="/developerworks/i/c.gif" width="160" height="5" alt=""/>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
        </xsl:if>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template name="Subscriptions">
    <table width="160" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td bgcolor="#000000" height="1" width="160">
          <img src="/developerworks/i/c.gif" width="160" height="1" alt=""/>
        </td>
      </tr>
      <tr>
        <td height="5" background="/developerworks/i/bg-gold.gif" align="center">
          <b>
            <xsl:value-of select="$subscriptions-heading"/>
          </b>
        </td>
      </tr>
      <tr>
        <td bgcolor="#666666" height="1" width="160">
          <img src="/developerworks/i/c.gif" width="160" height="1" alt=""/>
        </td>
      </tr>
      <tr>
        <td>
          <table width="160" border="0" cellspacing="0" cellpadding="1">
            <tr>
              <td>
                <a href="{$dw-newsletter-url}">
                  <xsl:value-of select="$dw-newsletter-text"/>
                </a>
              </td>
            </tr>
            <tr>
              <td height="1">
                <img src="/developerworks/i/c.gif" width="160" height="5" alt=""/>
              </td>
            </tr>
            <tr>
              <td>
                <a href="{$dw-subscription-url}">
                  <xsl:copy-of select="$dw-subscription-text"/>
                </a>
              </td>
            </tr>
            <tr>
              <td height="1">
                <img src="/developerworks/i/c.gif" width="160" height="5" alt=""/>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
  </xsl:template>
  <xsl:template name="dwsubRelatedContents">
    <xsl:if test="/dw-document/dw-subscription-landing/@related_contents='yes'">
      <table width="160" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td bgcolor="#000000" height="1" width="160">
            <img src="/developerworks/i/c.gif" width="160" height="1" alt=""/>
          </td>
        </tr>
        <tr>
          <td height="5" background="/developerworks/i/bg-gold.gif" align="center">
            <b>Related content:</b>
          </td>
        </tr>
        <tr>
          <td bgcolor="#666666" height="1" width="160">
            <img src="/developerworks/i/c.gif" width="160" height="1" alt=""/>
          </td>
        </tr>
        <tr>
          <td>
            <table width="160" border="0" cellspacing="0" cellpadding="1">
              <xsl:apply-templates select="/dw-document//relatedlist/a"/>
              <tr>
                <td height="1">
                  <img src="/developerworks/i/c.gif" width="160" height="5" alt=""/>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </xsl:if>
  </xsl:template>
  <xsl:template match="resourcelist">
    <p>
      <a name="resources">
        <span class="atitle2">
          <xsl:value-of select="$resourcelist-heading"/>
        </span>
      </a>
      <xsl:apply-templates/>
    </p>
  </xsl:template>
  <xsl:template name="Resources">
    <xsl:apply-templates select="resourcelist"/>
  </xsl:template>
  <xsl:template match="resourcelist/heading">
    <span class="atitle3">
      <xsl:value-of select="."/>
    </span>
  </xsl:template>
  <xsl:template match="resourcelist/ul">
    <ul>
      <xsl:if test=". = /dw-document/dw-article/resourcelist/ul[1] and /dw-document/dw-article/forumurl/@url !=''">
        <li>
          <xsl:copy-of select="$resourcelist-forum-text"/>
          <br/>
          <br/>
        </li>
      </xsl:if>
      <xsl:for-each select="li">
        <xsl:choose>
          <xsl:when test="ul or ol">
            <li>
              <xsl:apply-templates select="*|text()"/>
              <br/>
            </li>
          </xsl:when>
          <xsl:otherwise>
            <li>
              <xsl:apply-templates select="*|text()"/>
              <br/>
              <br/>
            </li>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:for-each>
    </ul>
  </xsl:template>
  <xsl:template match="sidebar">
    <xsl:variable name="width">
      <xsl:choose>
        <xsl:when test="@width!=''">
          <xsl:value-of select="@width"/>
        </xsl:when>
        <xsl:when test="not(@width!='')">30%</xsl:when>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="align">
      <xsl:choose>
        <xsl:when test="@align!=''">
          <xsl:value-of select="@align"/>
        </xsl:when>
        <xsl:when test="not(@align!='')">right</xsl:when>
      </xsl:choose>
    </xsl:variable>
    <xsl:choose>
      <xsl:when test="contains($width, '%')">
        <p>
          <table width="{$width}" cellpadding="5" cellspacing="0" border="1" align="{$align}">
            <tr>
              <td background="/developerworks/i/bg-gold.gif">
                <xsl:apply-templates/>
              </td>
            </tr>
          </table>
        </p>
      </xsl:when>
      <xsl:when test="not(contains($width, '%'))">
        <p>
          <table width="{$width}" cellpadding="5" cellspacing="0" border="1" align="{$align}">
            <tr>
              <td background="/developerworks/i/bg-gold.gif">
                <xsl:apply-templates/>
              </td>
            </tr>
          </table>
        </p>
      </xsl:when>
    </xsl:choose>
  </xsl:template>
  <xsl:template name="SkillLevel">
    <xsl:choose>
      <xsl:when test="/dw-document/dw-article/@level = '1' ">
        <xsl:value-of select="$level-1-text"/>
      </xsl:when>
      <xsl:when test="/dw-document/dw-article/@level = '2' ">
        <xsl:value-of select="$level-2-text"/>
      </xsl:when>
      <xsl:when test="/dw-document/dw-article/@level = '3' ">
        <xsl:value-of select="$level-3-text"/>
      </xsl:when>
      <xsl:when test="/dw-document/dw-article/@level = '4' ">
        <xsl:value-of select="$level-4-text"/>
      </xsl:when>
      <xsl:when test="/dw-document/dw-article/@level = '5' ">
        <xsl:value-of select="$level-5-text"/>
      </xsl:when>
    </xsl:choose>
  </xsl:template>
  <xsl:template name="SubscriptionLevel">
    <xsl:choose>
      <xsl:when test="/dw-document/dw-subscription-landing/@subscriptionlevel = '1' ">
        <xsl:text>Guest</xsl:text>
      </xsl:when>
      <xsl:when test="/dw-document/dw-subscription-landing/@subscriptionlevel = '2' ">
        <xsl:text>Starter</xsl:text>
      </xsl:when>
      <xsl:when test="/dw-document/dw-subscription-landing/@subscriptionlevel = '3' ">
        <xsl:text>Professional</xsl:text>
      </xsl:when>
      <xsl:when test="/dw-document/dw-subscription-landing/@subscriptionlevel = '4' ">
        <xsl:text>Enterprise</xsl:text>
      </xsl:when>
    </xsl:choose>
  </xsl:template>
  <xsl:template match="table">
    <table>
      <xsl:for-each select="@border | @cellpadding | @cellspacing | @cols | @width">
        <xsl:attribute name="{name()}"><xsl:value-of select="."/></xsl:attribute>
      </xsl:for-each>
      <xsl:apply-templates select="*"/>
    </table>
  </xsl:template>
  <xsl:template match="tr">
    <tr>
      <xsl:for-each select="@*">
        <xsl:attribute name="{name()}"><xsl:value-of select="."/></xsl:attribute>
      </xsl:for-each>
      <xsl:apply-templates select="*"/>
    </tr>
  </xsl:template>
  <xsl:template match="td">
    <td>
      <xsl:for-each select="@bgcolor | @height | @width | @colspan | @rowspan | @align | @valign">
        <xsl:attribute name="{name()}"><xsl:value-of select="."/></xsl:attribute>
      </xsl:for-each>
      <xsl:apply-templates select="*|text()"/>
    </td>
  </xsl:template>
  <xsl:template name="TableOfContents">
    <xsl:if test="not(/dw-document/*/@toc='no')">
      <table width="160" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td bgcolor="#000000" height="1" width="160">
            <img src="/developerworks/i/c.gif" width="160" height="1" alt=""/>
          </td>
        </tr>
        <tr>
          <td height="5" background="/developerworks/i/bg-gold.gif" align="center">
            <b>
              <xsl:value-of select="$tableofcontents-heading"/>
            </b>
          </td>
        </tr>
        <tr>
          <td bgcolor="#666666" height="1" width="160">
            <img src="/developerworks/i/c.gif" width="160" height="1" alt=""/>
          </td>
        </tr>
        <tr>
          <td>
            <table width="160" border="0" cellspacing="0" cellpadding="0">
              <xsl:for-each select="//heading">
                <xsl:variable name="newid">
                  <xsl:choose>
                    <xsl:when test="@refname != ''">
                      <xsl:value-of select="concat('#', @refname)"/>
                    </xsl:when>
                    <xsl:otherwise>
                      <xsl:value-of select="concat('#', generate-id())"/>
                    </xsl:otherwise>
                  </xsl:choose>
                </xsl:variable>
                <xsl:variable name="toctext">
                  <xsl:choose>
                    <xsl:when test="@alttoc != ''">
                      <xsl:value-of select="@alttoc"/>
                    </xsl:when>
                    <xsl:otherwise>
                      <xsl:value-of select="."/>
                    </xsl:otherwise>
                  </xsl:choose>
                </xsl:variable>
                <xsl:if test="@toc='yes'">
                  <tr>
                    <td>
                      <a href="{$newid}">
                        <xsl:value-of select="$toctext"/>
                      </a>
                    </td>
                  </tr>
                  <tr>
                    <td height="1">
                      <img src="/developerworks/i/c.gif" width="160" height="5" alt=""/>
                    </td>
                  </tr>
                </xsl:if>
              </xsl:for-each>
              <xsl:comment>Standard links for every dw-article</xsl:comment>
              <xsl:if test="//resourcelist">
                <tr>
                  <td>
                    <a href="#resources">
                      <xsl:value-of select="$resourcelist-heading"/>
                    </a>
                  </td>
                </tr>
              </xsl:if>
              <tr>
                <td height="1">
                  <img src="/developerworks/i/c.gif" width="160" height="5" alt=""/>
                </td>
              </tr>
              <xsl:if test="//author/.!=''">
                <tr>
                  <td>
                    <a href="#author1">
                      <xsl:choose>
                        <xsl:when test="count(//author) = 1">
                          <xsl:value-of select="$aboutTheAuthor"/>
                        </xsl:when>
                        <xsl:otherwise>
                          <xsl:value-of select="$aboutTheAuthors"/>
                        </xsl:otherwise>
                      </xsl:choose>
                    </a>
                  </td>
                </tr>
                <tr>
                  <td height="1">
                    <img src="/developerworks/i/c.gif" width="160" height="5" alt=""/>
                  </td>
                </tr>
              </xsl:if>
              <xsl:if test="not(/dw-document/*/@ratings='no') and not(/dw-document/dw-subscription-landing)">
                <tr>
                  <td>
                    <a href="#rating">
                      <xsl:value-of select="$ratethisarticle-heading"/>
                    </a>
                  </td>
                </tr>
              </xsl:if>
              <tr>
                <td>
                  <img src="/developerworks/i/c.gif" width="160" height="10" alt=""/>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </xsl:if>
  </xsl:template>
  <xsl:template name="TableOfContentsClose">
    <xsl:choose>
      <xsl:when test="not(/dw-document/*/@toc='no') or not(/dw-document/*/@related_contents='no')">
        <table width="160" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td colspan="2" bgcolor="#000000" height="2" width="150">
              <img src="/developerworks/i/c.gif" width="160" height="2" alt=""/>
            </td>
          </tr>
          <tr>
            <td colspan="2" bgcolor="#FFFFFF" height="2" width="150">
              <img src="/developerworks/i/c.gif" width="160" height="2" alt=""/>
            </td>
          </tr>
        </table>
      </xsl:when>
    </xsl:choose>
  </xsl:template>
  <xsl:template name="TitleArea">
    <a name="main"/>
    <a name="top"/>
    <table width="100%" cellspacing="0" cellpadding="0" border="0">
      <tr valign="top">
        <td colspan="5">
          <img src="/developerworks/i/c.gif" width="5" height="15" border="0" alt=""/>
        </td>
      </tr>
      <tr valign="top">
        <td width="2">
          <img src="/developerworks/i/c.gif" width="2" height="1" border="0" alt=""/>
        </td>
        <td>
        <xsl:call-template name="VisibleTitle"/>
       </td>
        <td width="8">
          <img src="/developerworks/i/c.gif" width="8" height="1" border="0" alt=""/>
        </td>
        <td width="180" align="right">
          <img src="/developerworks/i/c.gif" width="180" height="1" border="0" alt=""/>
          <br/>
          <nobr>
            <xsl:call-template name="IconLinks"/>
          </nobr>
        </td>
        <td width="6">
          <img src="/developerworks/i/c.gif" width="6" height="1" border="0" alt=""/>
        </td>
      </tr>
      <!-- Black line separator -->
      <tr valign="top">
        <td colspan="5" bgcolor="#000000">
          <img src="/developerworks/i/c.gif" width="100" height="1" border="0" alt=""/>
        </td>
      </tr>
      <tr valign="top">
        <td colspan="5" bgcolor="#FFFFFF">
          <img src="/developerworks/i/c.gif" width="100" height="8" border="0" alt=""/>
        </td>
      </tr>
    </table>
  </xsl:template>
  <xsl:template name="VisibleSubtitle">
    <xsl:if test="subtitle/.!=''">
      <span class="atitle2">
        <xsl:value-of select="subtitle"/>
      </span>
      <br/>
    </xsl:if>
  </xsl:template>
  <xsl:template name="VisibleTitle">
    <xsl:if test="seriestitle/.!=''">
      <span class="astitle">
        <xsl:value-of select="seriestitle"/>
        <xsl:text>: </xsl:text>
      </span>
    </xsl:if>
    <xsl:if test="title/.!=''">
      <span class="atitle">
        <xsl:value-of select="title"/>
      </span>
    </xsl:if>
  </xsl:template>
  <xsl:template name="ContentAreaInputName">
    <xsl:param name="contentarea"/>
    <xsl:choose>
      <xsl:when test="$contentarea = 'autonomic' ">Autonomic computing</xsl:when>
      <xsl:when test="$contentarea = 'grid' ">Grid computing</xsl:when>
      <xsl:when test="$contentarea= 'java' ">Java</xsl:when>
      <xsl:when test="$contentarea= 'linux' ">Linux</xsl:when>
      <xsl:when test="$contentarea= 'opensource' ">Open source</xsl:when>
      <xsl:when test="$contentarea= 'webservices' ">Web services</xsl:when>
      <xsl:when test="$contentarea= 'xml' ">XML</xsl:when>
      <xsl:when test="$contentarea= 'components' ">Components</xsl:when>
      <xsl:when test="$contentarea= 'security' ">Security</xsl:when>
      <xsl:when test="$contentarea= 'web' ">Web architecture</xsl:when>
      <xsl:when test="$contentarea= 'wireless' ">Wireless</xsl:when>
      <xsl:when test="$contentarea= 'ibm' ">Scenarios</xsl:when>
      <xsl:when test="$contentarea= 'db2' ">DB2</xsl:when>
      <xsl:when test="$contentarea= 'eserver' ">eServer</xsl:when>
      <xsl:when test="$contentarea= 'lotus' ">Lotus</xsl:when>
      <xsl:when test="$contentarea= 'rational' ">Rational</xsl:when>
      <xsl:when test="$contentarea= 'tivoli' ">Tivoli</xsl:when>
      <xsl:when test="$contentarea= 'subscription' ">developerWorks Subscription</xsl:when>
      <xsl:when test="$contentarea= 'websphere' ">WebSphere</xsl:when>
    </xsl:choose>
  </xsl:template>
  <xsl:template name="ContentAreaList">
    <xsl:for-each select="content-area">
      <xsl:choose>
        <xsl:when test="position()=last()">
          <xsl:value-of select="@name"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="@name"/>
          <xsl:text>, </xsl:text>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:for-each>
  </xsl:template>
  <xsl:template match="content-area">
    <xsl:copy>
      <xsl:for-each select="@*">
        <xsl:copy/>
      </xsl:for-each>
      <xsl:apply-templates/>
    </xsl:copy>
  </xsl:template>
  <xsl:template name="ContentAreaName">
    <xsl:param name="contentarea"/>
    <xsl:choose>
      <xsl:when test="$contentarea = 'autonomic' "><xsl:copy-of select="$contentarea-ui-name-au"/></xsl:when>
      <xsl:when test="$contentarea = 'grid' "><xsl:copy-of select="$contentarea-ui-name-gr"/></xsl:when>
      <xsl:when test="$contentarea = 'java' "><xsl:copy-of select="$contentarea-ui-name-j"/></xsl:when>
      <xsl:when test="$contentarea= 'linux' "><xsl:copy-of select="$contentarea-ui-name-l"/></xsl:when>
      <xsl:when test="$contentarea= 'opensource' "><xsl:copy-of select="$contentarea-ui-name-os"/></xsl:when>
      <xsl:when test="$contentarea= 'webservices' "><xsl:copy-of select="$contentarea-ui-name-ws"/></xsl:when>
      <xsl:when test="$contentarea= 'xml' "><xsl:copy-of select="$contentarea-ui-name-x"/></xsl:when>
      <xsl:when test="$contentarea= 'components' "><xsl:copy-of select="$contentarea-ui-name-co"/></xsl:when>
      <xsl:when test="$contentarea= 'security' "><xsl:copy-of select="$contentarea-ui-name-s"/></xsl:when>
      <xsl:when test="$contentarea= 'web' "><xsl:copy-of select="$contentarea-ui-name-wa"/></xsl:when>
      <xsl:when test="$contentarea= 'wireless' "><xsl:copy-of select="$contentarea-ui-name-wi"/></xsl:when>
      <xsl:when test="$contentarea= 'ibm' "><xsl:copy-of select="$contentarea-ui-name-i"/></xsl:when>
      <xsl:when test="$contentarea= 'db2' "><xsl:copy-of select="$contentarea-ui-name-db2"/></xsl:when>
      <xsl:when test="$contentarea= 'eserver' "><xsl:copy-of select="$contentarea-ui-name-es"/></xsl:when>
      <xsl:when test="$contentarea= 'lotus' "><xsl:copy-of select="$contentarea-ui-name-lo"/></xsl:when>
      <xsl:when test="$contentarea= 'rational' "><xsl:copy-of select="$contentarea-ui-name-r"/></xsl:when>
      <xsl:when test="$contentarea= 'tivoli' "><xsl:copy-of select="$contentarea-ui-name-tiv"/></xsl:when>
      <xsl:when test="$contentarea= 'websphere' "><xsl:copy-of select="$contentarea-ui-name-web"/></xsl:when>
      <xsl:when test="$contentarea= 'subscription' "><xsl:copy-of select="$contentarea-ui-name-sub"/></xsl:when>
    </xsl:choose>
  </xsl:template>
  <xsl:template match="span">
    <span>
      <xsl:for-each select="@*">
        <xsl:copy/>
      </xsl:for-each>
      <xsl:apply-templates select="*|text()"/>
    </span>
  </xsl:template>
  <xsl:template match="caption">
    <caption>
      <xsl:for-each select="@*">
        <xsl:copy/>
      </xsl:for-each>
      <xsl:apply-templates select="*|text()"/>
    </caption>
  </xsl:template>
  <xsl:template match="th">
    <td align="center" valign="top">
      <xsl:for-each select="@*">
        <xsl:copy/>
      </xsl:for-each>
      <b>
        <xsl:apply-templates select="*|text()"/>
      </b>
    </td>
  </xsl:template>
  <xsl:template match="div | object | param | embed">
    <xsl:element name="{name()}">
      <xsl:for-each select="@*">
        <xsl:attribute name="{name()}"><xsl:value-of select="."/></xsl:attribute>
      </xsl:for-each>
      <xsl:apply-templates select="*|text()|comment()|processing-instruction()"/>
    </xsl:element>
  </xsl:template>
  <!-- Delete this template asap, but not until the first round of testing is done -->
  <xsl:template match="*">
    <xsl:element name="{name()}">
      <xsl:for-each select="@*">
        <xsl:attribute name="{name()}"><xsl:value-of select="."/></xsl:attribute>
      </xsl:for-each>
      <xsl:apply-templates select="*|text()|comment()|processing-instruction()"/>
    </xsl:element>
  </xsl:template>
</xsl:stylesheet>
