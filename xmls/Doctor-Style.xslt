<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" encoding="UTF-8"/>

  <xsl:template match="/">
    <html>
      <head>
        <title>Doctor's profile:</title> 
        <style>
        .green-text {
            color: green;
        }
        .green-header {
            color: green;
        }
        .center-align {
            margin-left: auto;
            margin-right: auto;
          }
          table.center-align th, table.center-align td {
            text-align: center;
          }
          .center-text {
            text-align: center;
          }
          .center-container {
            text-align: center; /* Center-aligns the content */
          }
          .header-background {
            background-color: #f0f0f0; 
            padding: 10px;
            border-radius: 5px;
          }
           .content-background {
            background-color: #baf0ba; 
            padding: 20px;
            border-radius: 5px;
            min-height: 100vh;
          }
           .table-container {
              display: flex;
              justify-content: space-around;
          }
    </style>
      </head>
      <body>
        <h1 class="center-text header-background" >Hospital-Emergency Department</h1>
        <xsl:for-each select="Doctor">
			<p><b><h2 class="green-text center-text">
            Doctor's profile:
            <xsl:text> </xsl:text>
            <xsl:value-of select="name"/>
            <xsl:text> </xsl:text>
            <xsl:value-of select="surname"/>
		  </h2></b></p>
          
          <div class="content-background">
          <div class="center-container">
           <xsl:value-of select="//name " />
           <xsl:text> </xsl:text>
           <xsl:value-of select="//surname" />
            </div>
           
          <table border="2" class="center-align">
            <tr>
              <th>id</th>
              <th>Name</th>
              <th>Surname</th>
              <th>Email</th>
              <th>Speciality</th>
            </tr>
            <tr>
              <td><xsl:value-of select="@id"/></td>
              <td><xsl:value-of select="name"/></td>
              <td><xsl:value-of select="surname"/></td>
              <td><xsl:value-of select="email"/></td>
              <td><xsl:value-of select="speciality/type"/></td>
            </tr>
          </table>

		<div class="table-container">
              <!-- Box Table -->
          <xsl:for-each select="Boxes/box">
                <table border="2" class="center-align">
                  <tr>
                    <th>Box ID</th>
                    <th>Available</th>
                    <th>Speciality</th>
                  </tr>
                  <tr>
                    <td><xsl:value-of select="@id"/></td>
                    <td><xsl:value-of select="@available"/></td>
                    <td><xsl:value-of select="speciality/type"/></td>
                  </tr>
                </table>
              </xsl:for-each>
              
             <xsl:for-each select="Boxes/box/Patients/patients">
                <table border="2" class="center-align">
                  <tr>
                    <th>Patient ID</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Weight</th>
                    <th>Height</th>
                    <th>Urgency</th>
                    <th>Status</th>
                    <th>Sex</th>
                    <th>BirthDate</th>
                  </tr>
                  <tr>
                    <td><xsl:value-of select="@id"/></td>
                    <td><xsl:value-of select="name"/></td>
                    <td><xsl:value-of select="surname"/></td>
                    <td><xsl:value-of select="@weight"/></td>
                    <td><xsl:value-of select="@height"/></td>
                    <td><xsl:value-of select="@urgency"/></td>
                    <td><xsl:value-of select="status"/></td>
                    <td><xsl:value-of select="sex"/></td>
                    <td><xsl:value-of select="birthDate"/></td>
                  </tr>
                </table>
              </xsl:for-each>
          </div>
          </div>
        </xsl:for-each>
      </body>
    </html>
  </xsl:template>
  
</xsl:stylesheet>