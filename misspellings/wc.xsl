<?xml version="1.0"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
  xmlns="http://www.w3.org/TR/xhtml1/transitional">

<xsl:output method="text"/>

<xsl:template match="docbody">
  <xsl:copy>
    <xsl:value-of select="."/>
  </xsl:copy>
</xsl:template>

<xsl:template match="text()"/>


</xsl:stylesheet>