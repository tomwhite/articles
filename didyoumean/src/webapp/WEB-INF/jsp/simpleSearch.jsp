<?xml version="1.0" encoding="UTF-8"?>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <title>Did You Mean</title>
		<link rel="stylesheet" type="text/css" href="style.css"/>    
  </head>
 	<body>
	 	<form action="simpleSearch.html" method="get">
			<spring:bind path="searchForm.query">			
				<p> 	
			 		<input type="text" name="query" value="<c:out value="${status.value}"/>" />
			 		<input type="submit" value="Search"/>
			 	</p>	 	
			 	<p class="error">
			 		<c:out value="${status.errorMessage}"/>
			 	</p>
			 </spring:bind>
	 	</form>
	 	<c:if test="${not empty result}">
	 		<c:choose>
	 			<c:when test="${empty result.topHits}">
				 	<p>
				 	No pages were found.
				 	</p>	 			
	 			</c:when>
	 			<c:otherwise>
				 	<p>
				 	Results 1 - <c:out value="${result.topHitCount}"/> of <c:out value="${result.totalHitCount}"/> for <i><c:out value="${result.originalQuery}"/></i>. (<c:out value="${result.searchDuration}"/> milliseconds)
				 	</p>
				 	<ul>
						<c:forEach items="${result.topHits}" var="hit">
						  <li><a href="<c:out value="documents/${hit}"/>"><c:out value="${hit}"/></a></li>
						</c:forEach>
				 	</ul>
	 			</c:otherwise>
	 		</c:choose>
	 	</c:if>
	</body>
</html>