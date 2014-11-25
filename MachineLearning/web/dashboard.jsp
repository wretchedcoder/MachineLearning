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
        <link rel="stylesheet" href="./css/main.css">
        <script src="./javascript/jquery-ui/external/jquery/jquery.js"></script>
        <script src="./javascript/jquery-ui/jquery-ui.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">            
            $(function() {
                $('#tabs').tabs(); 
                $( "input[name='datasource']" ).button();
                $("#nextToAlgButton").button();
                $("#nextToProcessButton").button();
                $("#processButton").button();
                $("#algorithmTab").accordion({
                    collapsible: true,
                    heightStyle: "content"
                });
                $('.algClusters').spinner({
                    min: 2,
                    max: 10
                });
                $('.algMaxTime').spinner({
                    min: 10,
                    max: 60
                });
                              
                $("#nextToAlgButton").click(function() {
                    var selected = $("#tabs").tabs("option", "selected");
                    $("#tabs").tabs("option", "selected", selected+1);
                });
            });
        </script>
    </head>
    <body>
        <form action="./Processing" method="POST">
        <div id="tabs" style="width: 95%;">
            <ul>
                <li><a href="#dataSrcTab">Data Source</a></li>
                <li><a href="#algorithmTab">Algorithms</a></li>
                <li><a href="#resultsTab">Processing & Results</a></li>
            </ul>
            <div id="dataSrcTab">
                <div style="width: 100%; text-align: center;">                    
                    <c:forEach var="item" items="${datasources}">
                        <div id="Test1" style="width: 30%; display: inline-block;">
                            <input type="radio" name="datasource" id="${item.name}radio" value="${item.filePath}"><label for="${item.name}radio" style="width: 100%;">${item.name}</label>
                        </div>
                    </c:forEach>   
                </div>
                <div style="width: 100%; text-align: center; padding-top: 1em;">
                    <button id="nextToAlgButton" type="button">Next</button>
                </div>
            </div>
            <div id="algorithmTab">
                <h3>Test Algorithm</h3>
                <div id="testTab">                    
                    <jsp:include page="./jsp/testAlgorithm.jsp"/>   
                </div>                 
            </div> <!-- End of Algorithm Tab -->
            <div id="resultsTab">
                <button id="processButton" type="submit">Process</button>
            </div>
        </div>
        </form>
    </body>
</html>
