<%-- 
    Document   : results
    Created on : Nov 25, 2014, 7:46:58 PM
    Author     : Lee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results Page</title>
    </head>
    <body>
        <div>
        <c:forEach var="item" items="${algorithmResults}">
            <div>
                ${item.iterations}                
            </div>
            <div>
                <p>1</p>
            </div>
        </c:forEach>
        </div>
    </body>
</html>
