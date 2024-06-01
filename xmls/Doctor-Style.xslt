<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <p><b><xsl:value-of select="//name" /></b></p>
   <p><b><xsl:value-of select="//surname" /></b></p>
   <p><b>Contents: </b><xsl:value-of select="//speciality" /></p>
   <p><b>Boxes</b></p>
   <table border="1">
      <th>id</th>
      <th>Speciality</th>
      <th>Availability</th>
      <xsl:for-each select="Doctor/Boxes/Patients">
      <xsl:sort select="@name" />
         <xsl:if test="salary &gt; 0">
            <tr>
            <td><i><xsl:value-of select="name" /></i></td>
            <td><i><xsl:value-of select="surname" /></i></td>
            <td><xsl:value-of select="sex" /></td>
            <td><xsl:value-of select="@height" /></td>
             <td><xsl:value-of select="@weight" /></td>
            </tr>
         </xsl:if>
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>