<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  version="1.0"
  xmlns="http://www.w3.org/1999/xhtml"
  xmlns:r="http://tiling.org/xmlcatalogs/namespaces/recipe"
  exclude-result-prefixes="r">

  @open_bold@<xsl:include
    href="http://tiling.org/xmlcatalogs/xslt/utils.xslt"/>@close_bold@
    
  @ellipsis_start@<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
  
  <xsl:output
    doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
    doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"/>@ellipsis_end@
  
  <xsl:template match="r:recipe">
  @ellipsis_start@   
    <html xml:lang="en" lang="en">
      <head>
        <title>
          <xsl:call-template name="uppercase">
          <xsl:with-param name="string" select="r:name"/>
          </xsl:call-template>
        </title>
      </head>
      <body>
        <h1><xsl:value-of select="r:name"/> by <xsl:value-of select="r:author"/></h1>
        <h2>Ingredients</h2>      
        <ul>
          <xsl:for-each select="r:ingredient">
          <li><xsl:value-of select="."/></li>
          </xsl:for-each>
        </ul>
        <h2>Method</h2>      
        <p><xsl:value-of select="r:method"/></p>
      </body>
    </html>
@ellipsis_end@    
  </xsl:template>

</xsl:stylesheet>