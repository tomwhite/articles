<?xml version="1.0"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
  xmlns:xhtml="http://www.w3.org/1999/xhtml">

	<xsl:output method="text"/>

	<xsl:template match="xhtml:pre"/>
	
	<xsl:template
	  match="*|@*|processing-instruction()|text()">
	  <xsl:copy>
	    <xsl:apply-templates
	     select="*|@*|processing-instruction()|text()"/>
	  </xsl:copy>
	</xsl:template>

</xsl:stylesheet>