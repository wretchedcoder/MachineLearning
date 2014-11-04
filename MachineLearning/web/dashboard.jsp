<%-- 
    Document   : dashboard
    Created on : Oct 27, 2014, 7:04:47 PM
    Author     : Lee
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="./javascript/jquery-ui/jquery-ui.min.css">
        <script src="./javascript/jquery-ui/external/jquery/jquery.js"></script>
        <script src="./javascript/jquery-ui/jquery-ui.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">            
            $(function() {
               $('#tabs').tabs(); 
               $('#dataSrcSelect').selectmenu();
            });
        </script>

    </head>
    <body>
        <div id="tabs" style="width: 95%;">
            <ul>
                <li><a href="#tabs-1">Data Source</a></li>
                <li><a href="#tabs-2">Algorithms</a></li>
                <li><a href="#tabs-3">Results</a></li>
            </ul>
            <div id="tabs-1" style="width: 100%;">
                <div style="width: 25%">
                    <select id="dataSrcSelect" style="width: 100%">
                        <c:forEach var="item" items="${datasources}">
                            <option value='${item.name}'>${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
    </body>
</html>
