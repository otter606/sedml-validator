<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="styles.css">
<title>Validation result</title>
</head>
<body>
<script language="javascript" type="text/javascript"  src="header.txt"></script>
<h1> Validation result</h1>

<script language="javascript" type="text/javascript"  src="nav.txt"></script>
<div id="content">

<!--  generated from servlet -->
<%=request.getAttribute("content") %>

<!-- end of content section -->
</div>
<script language="javascript" type="text/javascript"  src="footer.txt"></script>
</body>
</html>