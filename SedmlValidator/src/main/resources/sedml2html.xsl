<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:math="http://www.w3.org/1998/Math/MathML"
	xmlns:sed="http://sed-ml.org/" xmlns:jmath="org.sedml.Libsedml"
	xmlns:html="http://www.w3.org/1999/xhtml">
	<xsl:output method="html"/>

	<xsl:param name="div"></xsl:param>
	<xsl:param name="jlibsedml">true</xsl:param>

	<xsl:template match="/">
		<html>
			<head>
				<xsl:copy-of  select="document('style.xml')/style" />
				<script type="text/javascript" language="javascript">
					<xsl:text>
				var tooltip=function(){
	var id = 'tt';
	var top = 3;
	var left = 3;
	var maxw = 300;
	var speed = 10;
	var timer = 20;
	var endalpha = 95;
	var alpha = 0;
	var tt,t,c,b,h;
	var ie = document.all ? true : false;
	return{
		show:function(v,w){
			if(tt == null){
				tt = document.createElement('div');
				tt.setAttribute('id',id);
				t = document.createElement('div');
				t.setAttribute('id',id + 'top');
				c = document.createElement('div');
				c.setAttribute('id',id + 'cont');
				b = document.createElement('div');
				b.setAttribute('id',id + 'bot');
				tt.appendChild(t);
				tt.appendChild(c);
				tt.appendChild(b);
				document.body.appendChild(tt);
				tt.style.opacity = 0;
				tt.style.filter = 'alpha(opacity=0)';
				document.onmousemove = this.pos;
			}
			tt.style.display = 'block';
			c.innerHTML = v;
			tt.style.width = w ? w + 'px' : 'auto';
			if(!w &amp;&amp; ie){
				t.style.display = 'none';
				b.style.display = 'none';
				tt.style.width = tt.offsetWidth;
				t.style.display = 'block';
				b.style.display = 'block';
			}
			if(tt.offsetWidth &gt; maxw){tt.style.width = maxw + 'px'}
			h = parseInt(tt.offsetHeight) + top;
			clearInterval(tt.timer);
			tt.timer = setInterval(function(){tooltip.fade(1)},timer);
		},
		pos:function(e){
			var u = ie ? event.clientY + document.documentElement.scrollTop : e.pageY;
			var l = ie ? event.clientX + document.documentElement.scrollLeft : e.pageX;
			tt.style.top = (u - h) + 'px';
			tt.style.left = (l + left) + 'px';
		},
		fade:function(d){
			var a = alpha;
			if((a != endalpha &amp;&amp; d == 1) || (a != 0 &amp;&amp; d == -1)){
				var i = speed;
				if(endalpha - a &lt; speed &amp;&amp; d == 1){
					i = endalpha - a;
				}else if(alpha &lt; speed &amp;&amp; d == -1){
					i = a;
				}
				alpha = a + (i * d);
				tt.style.opacity = alpha * .01;
				tt.style.filter = 'alpha(opacity=' + alpha + ')';
			}else{
				clearInterval(tt.timer);
				if(d == -1){tt.style.display = 'none'}
			}
		},
		hide:function(){
			clearInterval(tt.timer);
			tt.timer = setInterval(function(){tooltip.fade(-1)},timer);
		}
	};
}();
</xsl:text>



				</script>
			</head>
			<body>
		
				<xsl:apply-templates select="sed:sedML"></xsl:apply-templates>
			</body>
		</html>

	</xsl:template>

	<!-- Converts a Node to a string representation of that node. We 
		use local name to replace the namespace and prefix in a uniform way. -->
	<xsl:template match="*" mode="escape">
		<xsl:variable name="xmlnsDec">
			xmlns:math="http://www.w3.org/1998/Math/MathML"
		</xsl:variable>
		<xsl:text>&lt;math:</xsl:text>
		<xsl:choose>
			<xsl:when test="local-name() = 'math'">
				<xsl:value-of select="concat(local-name(), ' ',  $xmlnsDec)" />
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="local-name()" />
			</xsl:otherwise>
		</xsl:choose>

		<xsl:apply-templates mode="escape" select="@*" />
		<xsl:text>&gt;</xsl:text>
		<xsl:apply-templates mode="escape" />
		<xsl:text>&lt;/math:</xsl:text>
		<xsl:value-of select="local-name()" />
		<xsl:text>&gt;</xsl:text>
	</xsl:template>

    <!-- General way to rewrite escaped attributes -->
	<xsl:template match="@*" mode="escape">
		<xsl:text> </xsl:text>
		<xsl:value-of select="name()" />
		<xsl:text>="</xsl:text>
		<xsl:value-of select="." />
		<xsl:text>"</xsl:text>
	</xsl:template>
	
	<!-- General way to re-write HTML nodes and remove spurious xml-ns attributes -->
	<xsl:template match="*" mode="html">
		<xsl:text>&lt;</xsl:text>
		<xsl:value-of select="local-name()" />
		<xsl:apply-templates  select="@*" mode="escape" />
		<xsl:text>&gt;</xsl:text>
		<xsl:apply-templates mode="html" />
		<xsl:text>&lt;/</xsl:text>
		<xsl:value-of select="local-name()" />
		<xsl:text>&gt;</xsl:text>
	</xsl:template>

	


	<xsl:template match="sed:sedML">
	    <xsl:copy-of select="sed:notes/html:html/html:body/html:* |  sed:notes/html:*[not(local-name()='html') and not(local-name()='body')]"
		 ></xsl:copy-of>
		<xsl:apply-templates select="sed:listOfModels"></xsl:apply-templates>
		
		<xsl:apply-templates select="sed:listOfSimulations"></xsl:apply-templates>
		<xsl:apply-templates select="sed:listOfTasks"></xsl:apply-templates>
		<xsl:apply-templates select="sed:listOfDataGenerators"></xsl:apply-templates>
		<xsl:apply-templates select="sed:listOfOutputs"></xsl:apply-templates>
		
	</xsl:template>

	<!-- Matches any sub-content of body, or any top level element that 
		is not body or html -->
	<xsl:template name="noteMouseOver">
	<xsl:param name="text"></xsl:param>
	<xsl:choose>
		<xsl:when test="count(sed:notes) &gt; 0">
		
		<xsl:variable name="htmlnotes">
		<xsl:apply-templates select="sed:notes/html:html/html:body/html:* |  sed:notes/html:*[not(local-name()='html') and not(local-name()='body')]"
		 mode="html"></xsl:apply-templates>	
	</xsl:variable>
	   <xsl:element name = "span">
	     <xsl:attribute name="class">hotspot</xsl:attribute>
	     <xsl:attribute name="onmouseout">tooltip.hide();</xsl:attribute>
	     <xsl:attribute name="onmouseover">tooltip.show(' <xsl:value-of select="translate($htmlnotes,'&#13;&#10;',' ')"></xsl:value-of>  ');</xsl:attribute>
	     <xsl:value-of select="$text"></xsl:value-of>
	   </xsl:element>			
		</xsl:when>
		
		<xsl:otherwise>
			<xsl:value-of select="$text"></xsl:value-of>
		</xsl:otherwise>
	</xsl:choose>
	
	</xsl:template>

	<xsl:template match="sed:plot2D">
		<tr>
			<td>
				<xsl:value-of select="@name"></xsl:value-of>
				<xsl:text>, [</xsl:text>
				<xsl:call-template name="noteMouseOver">
				 <xsl:with-param name="text"><xsl:value-of select="@id"></xsl:value-of></xsl:with-param>
				</xsl:call-template>
				<xsl:text>]</xsl:text>
			</td>
			<td>
				<table>
					<tr class="tblColTitle">
						<th>Curve name [ID]</th>
						<td>X-axis</td>
						<td>X-axis log-scale?</td>
						<th>Y-axis</th>
						<td>Y-axis log-scale?</td>
					</tr>
					<xsl:for-each select="sed:listOfCurves/sed:curve">
						<tr>
							<td>
								<xsl:value-of select="@name"></xsl:value-of>
								<xsl:text> [</xsl:text>
								<xsl:call-template name="noteMouseOver">
				 <xsl:with-param name="text"><xsl:value-of select="@id"></xsl:value-of></xsl:with-param>
				</xsl:call-template>
								<xsl:text>]</xsl:text>
							</td>
							<td>
								<a href="#{@xDataReference}">
									<xsl:value-of select="@xDataReference"></xsl:value-of>
								</a>
							</td>
							<td>
								<xsl:value-of select="@logX"></xsl:value-of>
							</td>

							<td>
								<a href="#{@yDataReference}">
									<xsl:value-of select="@yDataReference"></xsl:value-of>
								</a>
							</td>
							<td>
								<xsl:value-of select="@logY"></xsl:value-of>
							</td>
						</tr>

					</xsl:for-each>
				</table>

			</td>

		</tr>

	</xsl:template>
	<xsl:template match="plot3D">
		<tr>
			<td>
				<xsl:value-of select="@name"></xsl:value-of>
				<xsl:text>, [</xsl:text>
				<xsl:call-template name="noteMouseOver">
				 <xsl:with-param name="text"><xsl:value-of select="@id"></xsl:value-of></xsl:with-param>
				</xsl:call-template>
				<xsl:text>]</xsl:text>
			</td>
			<td>
				<table>
					<tr class="tblColTitle">
						<th>Curve name [ID]</th>
						<th>X-axis</th>
						<th>X-axis log-scale?</th>
						<th>Y-axis</th>
						<th>Y-axis log-scale?</th>
						<th>Z-axis</th>
						<th>Z-axis log-scale?</th>
					</tr>
					<xsl:for-each select="sed:listOfSurfaces/sed:surface">
						<tr>
							<td>
								<xsl:value-of select="@name"></xsl:value-of>
								<xsl:text> [</xsl:text>
								<xsl:call-template name="noteMouseOver">
				 <xsl:with-param name="text"><xsl:value-of select="@id"></xsl:value-of></xsl:with-param>
				</xsl:call-template>
								<xsl:text>]</xsl:text>
							</td>
							<td>
								<a href="#{@xDataReference}">
									<xsl:value-of select="@xDataReference"></xsl:value-of>
								</a>
							</td>
							<td>
								<xsl:value-of select="@logX"></xsl:value-of>
							</td>

							<td>
								<a href="#{@yDataReference}">
									<xsl:value-of select="@yDataReference"></xsl:value-of>
								</a>
							</td>
							<td>
								<xsl:value-of select="@logY"></xsl:value-of>
							</td>
							<td>
								<a href="#{@zDataReference}">
									<xsl:value-of select="@zDataReference"></xsl:value-of>
								</a>
							</td>
							<td>
								<xsl:value-of select="@logZ"></xsl:value-of>
							</td>
						</tr>

					</xsl:for-each>
				</table>

			</td>

		</tr>
	</xsl:template>
	<xsl:template match="sed:report">
		<tr>
			<td>
				<xsl:value-of select="@name"></xsl:value-of>
				<xsl:text> [</xsl:text>
				<xsl:call-template name="noteMouseOver">
				 <xsl:with-param name="text"><xsl:value-of select="@id"></xsl:value-of></xsl:with-param>
				</xsl:call-template>
				<xsl:text>]</xsl:text>
			</td>
			<td>
				<table>
					<tr class="tblColTitle">
						<th>Dataset name [ID]</th>
						<td>Datagenerator</td>
					</tr>
					<xsl:for-each select="sed:listOfDataSets/sed:dataSet">
						<tr>
							<td>
								<xsl:value-of select="@name"></xsl:value-of>
								<xsl:text> [</xsl:text>
								<xsl:call-template name="noteMouseOver">
				 <xsl:with-param name="text"><xsl:value-of select="@id"></xsl:value-of></xsl:with-param>
				</xsl:call-template>
								<xsl:text>]</xsl:text>
							</td>
							<td>
								<a href="#{@dataReference}">
									<xsl:value-of select="@dataReference"></xsl:value-of>
								</a>
							</td>
						</tr>

					</xsl:for-each>
				</table>

			</td>

		</tr>
	</xsl:template>

	<xsl:template match="sed:listOfOutputs">
		<h3>Outputs</h3>
		<xsl:if test="count(sed:plot2D) > 0">
			<h4>2D Plots</h4>

			<table>
				<tr class="tblColTitle">
					<th>Plot name [id]</th>
					<th>Curve</th>
				</tr>
				<xsl:apply-templates></xsl:apply-templates>
			</table>
		</xsl:if>
		<xsl:if test="count(sed:plot3D) > 0">
			<h4>3D Plots</h4>

			<table>
				<tr class="tblColTitle">
					<th>Plot name [id]</th>
					<th>Curve</th>
				</tr>

				<xsl:apply-templates></xsl:apply-templates>


			</table>
		</xsl:if>

		<xsl:if test="count(sed:report) > 0">
			<h4>Reports</h4>
			<table>
				<tr class="tblColTitle">
					<th>Report name [id]</th>
					<th>Curve</th>
				</tr>
				<xsl:apply-templates></xsl:apply-templates>

			</table>
		</xsl:if>


	</xsl:template>

	<xsl:template match="sed:listOfDataGenerators">
		<h3>Data Generators</h3>
		<table cellpadding="10">
			<tr class="tblColTitle">
				<th>Name [Id]</th>
				<th>Referred variable</th>
				<th>Post-processing</th>
			</tr>
			<xsl:for-each select="sed:dataGenerator">
				<tr>
					<td>
						<xsl:value-of select="@name"></xsl:value-of>
						<xsl:text> [</xsl:text>
						<a name="{@id}" />
						<xsl:call-template name="noteMouseOver">
				 <xsl:with-param name="text"><xsl:value-of select="@id"></xsl:value-of></xsl:with-param>
				</xsl:call-template>
						<xsl:text> ]</xsl:text>
					</td>

					<td>
						<table>
							<tr class="tblColTitle">
								<th>Variable Name [Id]</th>
								<th>Model reference (task)</th>
							</tr>
							<xsl:apply-templates select="sed:listOfVariables" />

						</table>
					</td>
					<td>
						<xsl:variable name="maths">
							<xsl:apply-templates select="math:math"
								mode="escape" />
						</xsl:variable>
						<xsl:choose>
							<xsl:when test="$jlibsedml='true'">
								<xsl:value-of select="jmath:MathMLXMLToText($maths)" />
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="translate($maths, '&#x9;','')" />
							</xsl:otherwise>
						</xsl:choose>

					</td>

				</tr>
			</xsl:for-each>
		</table>

	</xsl:template>

	<xsl:template match="sed:listOfVariables">

		<xsl:for-each select="sed:variable">
			<tr>
				<td>
				<xsl:value-of select="@name"></xsl:value-of>
				[ <xsl:call-template name="noteMouseOver">
				 <xsl:with-param name="text"><xsl:value-of select="@id"></xsl:value-of></xsl:with-param>
				</xsl:call-template>  ]
				</td>
				<td>
					<xsl:value-of select="@target"></xsl:value-of>
					<xsl:value-of select="@symbol"></xsl:value-of>
					<xsl:text>, task [</xsl:text>
					<a href="#{@taskReference}">
						<xsl:value-of select="@taskReference"></xsl:value-of>
					</a>
					<xsl:text>]</xsl:text>
				</td>
			</tr>

		</xsl:for-each>

	</xsl:template>

	<xsl:template match="sed:listOfTasks">
		<h3>Tasks</h3>
		<table border="1" cellpadding="10">
			<tr class="tblColTitle">
				<th>Task ID</th>
				<th>Name</th>
				<th>Model reference</th>
				<th>Simulation reference</th>
			</tr>
			<xsl:for-each select="sed:task">
				<tr>
					<td>
						<a name="{@id}" />
						<xsl:call-template name="noteMouseOver">
				 <xsl:with-param name="text"><xsl:value-of select="@id"></xsl:value-of></xsl:with-param>
				</xsl:call-template>
					</td>
					<td>
						<xsl:value-of select="@name"></xsl:value-of>
					</td>
					<td>
						<a href="#{@modelReference}">
							<xsl:value-of select="@modelReference"></xsl:value-of>
						</a>
					</td>
					<td>
						<a href="#{@simulationReference}">
							<xsl:value-of select="@simulationReference"></xsl:value-of>
						</a>
					</td>
				</tr>
			</xsl:for-each>
		</table>
	</xsl:template>

	<xsl:template match="sed:listOfSimulations">
		<h3>Simulations</h3>
		<table border="1" cellpadding="10">
			<tr class="tblColTitle">
				<th>Simulation ID</th>
				<th>Name</th>
				<th>Start time</th>
				<th>Output start</th>
				<th>Output end</th>
				<th>Number of data points</th>
			</tr>
			<xsl:for-each select="sed:uniformTimeCourse">
				<tr>
					<td>
						<a name="{@id}" />
						<xsl:call-template name="noteMouseOver">
				 <xsl:with-param name="text"><xsl:value-of select="@id"></xsl:value-of></xsl:with-param>
				</xsl:call-template>
					</td>
					<td>
						<xsl:value-of select="@name"></xsl:value-of>
					</td>
					<td>
						<xsl:value-of select="@initialTime"></xsl:value-of>
					</td>
					<td>
						<xsl:value-of select="@outputStartTime"></xsl:value-of>
					</td>
					<td>
						<xsl:value-of select="@outputEndTime"></xsl:value-of>
					</td>
					<td>
						<xsl:value-of select="@numberOfPoints"></xsl:value-of>
					</td>
				</tr>
			</xsl:for-each>
		</table>

	</xsl:template>

	<xsl:template match="sed:listOfModels">
		<h3>Models</h3>
		
			<xsl:for-each select="sed:model">
			    <h4> <xsl:value-of select="@name">  </xsl:value-of><a name="{@id}" /> [
						<xsl:call-template name="noteMouseOver">
				 <xsl:with-param name="text"><xsl:value-of select="@id"></xsl:value-of></xsl:with-param>
				</xsl:call-template>
				]
					</h4>
			    <table>
			      <tr class="tblColTitle">
			       <th>Attribute</th>
			       <th>Value</th>
			      </tr>
				
				<tr>
					<td>Name</td>
					<td>
						<xsl:value-of select="@name"></xsl:value-of>
					</td>
			    </tr>
			    <tr>
			    	<td>Source</td>
					<xsl:variable name="src">
						<xsl:value-of select="@source"></xsl:value-of>
					</xsl:variable>
					<xsl:choose>
						<xsl:when test="starts-with($src, 'http://')">
							<td>
								<a href="{$src}">
									<xsl:value-of select="$src"></xsl:value-of>
								</a>
							</td>
						</xsl:when>

						<xsl:when test="starts-with($src, 'urn:miriam:biomodels.db:')">
							<td>
								<xsl:variable name="biomodelsURL">
									<xsl:value-of
										select="concat('http://www.ebi.ac.uk/biomodels-main/',
									                   substring-after($src, 'urn:miriam:biomodels.db:'))"></xsl:value-of>
								</xsl:variable>
								<a href="{$biomodelsURL}">
									<xsl:value-of select="$src"></xsl:value-of>
								</a>
							</td>
						</xsl:when>
						<xsl:otherwise>
							<td>
								<xsl:value-of select="$src"></xsl:value-of>
							</td>
						</xsl:otherwise>
					</xsl:choose>
					</tr>
					<tr>
					<td>Language</td>
					<td>
						<xsl:value-of select="@language"></xsl:value-of>
					</td>
					</tr>
					<tr>
					 <td>Changes</td>
					<td>
						<xsl:if test="count(sed:listOfChanges/sed:changeAttribute) > 0">
							<p> Attribute changes</p>
							<table>
								<tr>
									<th> Variable</th>
									<th> New value</th>
								</tr>
								<xsl:for-each select="sed:listOfChanges/sed:changeAttribute">
									<tr>
										<td>
										 <xsl:call-template name="noteMouseOver">
				 								<xsl:with-param name="text">
													 <xsl:value-of select="@target">
				 							</xsl:value-of></xsl:with-param>
											</xsl:call-template>
											
										</td>
										<td>
											<xsl:value-of select="@newValue"></xsl:value-of>
										</td>
									</tr>
								</xsl:for-each>
							</table>
						</xsl:if>

					</td>
				</tr>
				</table>
				<div style="margin-bottom: 20px;"></div>
			</xsl:for-each>
		
	</xsl:template>

</xsl:stylesheet>