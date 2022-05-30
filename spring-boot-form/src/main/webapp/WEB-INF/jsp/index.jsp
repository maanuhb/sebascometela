<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

 <%@include file="/WEB-INF/jsp/include-css.jsp" %>
        <%@include file="/WEB-INF/jsp/include-js.jsp" %>
</head>
<body class="skin-megna fixed-layout">
	<div class="preloader">
		<div class="loader">
			<div class="loader__figure"></div>
			<p class="loader__label">ADMIN</p>
		</div>
	</div>
	<%@include file="/WEB-INF/jsp/header.jsp" %>
		<%@include file="/WEB-INF/jsp/menu.jsp" %>
	<div id="main-wrapper">
		<%@include file="/WEB-INF/jsp/footer.jsp" %>
	</div>
</body>
</html>