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
        <script src="./javascript/canvasjs/jquery.canvasjs.min.js"></script>
        <script src="./javascript/dojo/dojo/dojo.js" data-dojo-config="async: true"></script>
        <script src="./javascript/dojo/digit/digit.js" data-dojo-config="async: true"></script>
        <script src="./javascript/dojo/dojox/dojox.js" data-dojo-config="async: true"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">    
            
            require(["dojox/charting/Chart", 
                    "dojox/charting/axis2d/Default", 
                    "dojox/charting/plot2d/Scatter", 
                    "dojo/ready"],
            function(Chart, Default, Lines, ready){
                function makeChart(name, data){
                    var chart1 = new Chart(name + "Chart");
                    chart1.addPlot("default", {type: "Scatter"});
                    chart1.addAxis("x", {fixLower: "major", fixUpper: "major", includeZero: true, max: 25});
                    chart1.addAxis("y", {vertical: true, fixLower: "major", fixUpper: "major", includeZero: true, max: 25});
                    chart1.addSeries("Series", data);
                    chart1.render();
                    
                }
            
            
            $(document).ready(function() {
                $.get("./datasets", function(data){
                    var obj = JSON.parse(data);
                    var i;
                    for (i = 0; i < obj.datasources.length; i++)
                    {
                        var newDiv = document.createElement("div");
                        $(newDiv).attr("style","width: 30%; display: inline-block; margin: 1px;");
                        $('#dataSourcesDiv').append(newDiv);
                        
                        var inputElement = document.createElement("input");
                        $(inputElement).attr("id", obj.datasources[i].name + "DataSrc");
                        $(inputElement).attr("value",obj.datasources[i].path);
                        $(inputElement).attr("name","datasource");
                        $(inputElement).attr("type","radio");
                        $(inputElement).attr("style","width: 100%;");
                        $(newDiv).append(inputElement);
                        
                        var labelElement = document.createElement("label");
                        $(labelElement).attr("for", obj.datasources[i].name + "DataSrc");
                        $(labelElement).html(obj.datasources[i].name);
                        $(labelElement).attr("style","width: 100%;");
                        $(newDiv).append(labelElement);
                        
                        var chartCanvas = document.createElement("div");
                        $(chartCanvas).attr("id",obj.datasources[i].name + "Chart");
                        $(chartCanvas).attr("style",
                          "width: 100%; height: 240px; margin: 10px auto 0px auto;");
                        $(newDiv).append(chartCanvas);
                        
                        var dataObj = new Array();
                        var j;
                        for (j = 0; j < obj.datasources[i].patterns.length; j++)
                        {
                            var tst = obj.datasources[i].patterns[j].attributes[0];
                            dataObj.push({x: obj.datasources[i].patterns[j].attributes[0],
                              y: obj.datasources[i].patterns[j].attributes[1],
                              stroke: "green"});
                        }                        
                        makeChart(obj.datasources[i].name, dataObj);             
                        
                    } // End of DataSources Loop
                    
                    $( "input[name='datasource']" ).button();
                });
            });
            
            function parseData (jsonData)
            {
                var colors = ["red", "purple", "blue", "green", "yellow"];
                
                $('#resultsDiv').remove();
                
                var resultsDiv = document.createElement("div");
                $(resultsDiv).prop("id", "resultsDiv");
                $(resultsDiv).addClass("navTab");
                $('body').append(resultsDiv);
                
                var i;
                for (i=0; i < jsonData.length; i++)
                {
                    var newh3 = document.createElement("h3");
                    $(newh3).html(jsonData[i].algorithmName)
                    $('#resultsDiv').append(newh3);
                    
                    var newDiv = document.createElement("div");
                    $(newDiv).attr("style","width: 30%; display: inline-block; margin: 1px;");
                    var newTable = document.createElement("table");
                    $(newTable).prop("border","1");
                    
                    var j;
                    for (keyname in jsonData[i].items)
                    {
                        var itemRow = document.createElement("tr");
                        $(itemRow).html("<td>" + keyname + "</td><td>" + jsonData[i].items[keyname] + "</td>")
                        $(newTable).append(itemRow);
                    }
                    
                    var chartDiv = document.createElement("div");
                    $(chartDiv).attr("id", jsonData[i].algorithmId + "Chart");
                    $(newDiv).append(chartDiv);
                    
                    $(newDiv).append(newTable);
                    
                    $('#resultsDiv').append(newDiv);
                    
                    var chart1 = new Chart(jsonData[i].algorithmId + "Chart");
                    chart1.addPlot("default", {type: "Scatter"});
                    chart1.addAxis("x", {fixLower: "major", fixUpper: "major", includeZero: true, max: 25});
                    chart1.addAxis("y", {vertical: true, fixLower: "major", fixUpper: "major", includeZero: true, max: 25});
                    
                    for (j = 0; j < jsonData[i].regions.length; j++)
                    {
                        var dataObj = new Array();
                        
                        var k;
                        for (k = 0; k < jsonData[i].regions[j].patterns.length; k++)
                        {
                            dataObj.push({x: jsonData[i].regions[j].patterns[k].attributes[0], 
                                y: jsonData[i].regions[j].patterns[k].attributes[1],
                                stroke: colors[j],
                                fill: colors[j]});
                        }
                        
                        dataObj.push({x: jsonData[i].centroids[j].attributes[0], 
                            y: jsonData[i].centroids[j].attributes[1],
                            stroke: colors[j],
                            fill: "white"});
                        
                        chart1.addSeries("Series" + j, dataObj);
                    }     
                    
                    chart1.render();
                }
                
                $('#resultsDiv').accordion({
                    collapsible: true,
                    heightStyle: "content"
                });     
            }
            
            $(function() {
                $('#navDiv').buttonset();                
                
                $('#dataSrcTab').prop('hidden', true);
                $('#algorithmTab').prop('hidden', true);
                $('#resultsTab').prop('hidden', true);
                
                
                $('#tabs').tabs(); 
                
                $("#nextToAlgButton").button();
                $("#nextToProcessButton").button();
                $("#processButton").button();
                $("#algorithmTab").accordion({
                    collapsible: true,
                    heightStyle: "content"
                });
                
                $('.algClusters').spinner({
                    min: 1
                });
                $('.algMaxTime').spinner({
                    min: 10
                });
                
                $('#dataSrcNavBtn').click(function(e){
                    $(".navTab").prop('hidden',true);
                    $('#dataSrcTab').prop('hidden', false);
                });
                $('#algorithmNavBtn').click(function(e){
                    $(".navTab").prop('hidden',true);
                    $('#algorithmTab').prop('hidden', false);
                });
                $('#resultsNavBtn').click(function(e){
                    $(".navTab").prop('hidden',true);
                    
                    // Call Java to get results
                    $.post('./Processing', $('#processForm').serialize(), 
                        function(data){
                            alert(data);
                            var obj = jQuery.parseJSON(data);
                            parseData(obj);
                    });
                });
                $('#dataSrcNavBtn').click();
            });
        });
            
            
            
            
        </script>
    </head>
    <body style="background-color: #F0EAD6;">
        <div id="navDiv">
            <input id="dataSrcNavBtn" name="navRadio" type="radio"><label for="dataSrcNavBtn">Data Source</label>
            <input id="algorithmNavBtn" name="navRadio" type="radio"><label for="algorithmNavBtn">Algorithms</label>
            <input id="resultsNavBtn" name="navRadio" type="radio"><label for="resultsNavBtn">Get Results</label>
        </div>
        <form id="processForm" action="./Processing" method="post">
        <div id="dataSrcTab" class="navTab">
            <h2>Data Sources</h2>
            <div id="dataSourcesDiv" style="width: 100%;">  
                <!--
                <c:forEach var="item" items="${datasources}">
                    <div id="Test1" style="width: 30%; display: inline-block;">
                        <input type="radio" name="datasource" id="${item.name}radio" value="${item.filePath}"><label for="${item.name}radio" style="width: 100%;">${item.name}</label>
                    </div>
                </c:forEach>   
                -->
            </div>
        </div>
        <div id="algorithmTab" class="navTab">
            <h3>K-Means Algorithm</h3>
            <div id="testTab">                    
                <jsp:include page="./jsp/kmeansAlgorithm.jsp"/>   
            </div>   
            <h3>SOM Algorithm</h3>
            <div id="testTab">                    
                <jsp:include page="./jsp/somAlgorithm.jsp"/>   
            </div>   
            <h3>Neural Gas Algorithm</h3>
            <div id="testTab">                    
                <jsp:include page="./jsp/neuralGasAlgorithm.jsp"/>   
            </div> 
            <h3>Fuzzy C-Means Algorithm</h3>
            <div id="testTab">                    
                <jsp:include page="./jsp/fuzzyCMeansAlgorithm.jsp"/>   
            </div> 
            <h3>Possibilistic Fuzzy C-Means Algorithm</h3>
            <div id="testTab">                    
                <jsp:include page="./jsp/possFuzzyCMeansAlgorithm.jsp"/>   
            </div> 
            <h3>Kernel K-Means Algorithm</h3>
            <div id="testTab">                    
                <jsp:include page="./jsp/kernelKMeansAlgorithm.jsp"/>   
            </div>
            <h3>Kernel SOM Algorithm</h3>
            <div id="testTab">                    
                <jsp:include page="./jsp/kernelSomAlgorithm.jsp"/>   
            </div>
            <h3>Kernel Neural Gas Algorithm</h3>
            <div id="testTab">                    
                <jsp:include page="./jsp/kernelNeuralGasAlgorithm.jsp"/>   
            </div>
            <h3>Kernel Fuzzy C-Means w/ Kernelized Metric Algorithm</h3>
            <div id="testTab">                    
                <jsp:include page="./jsp/kernelFuzzyCMeansKernelMetric.jsp"/>   
            </div>
        </div> <!-- End of Algorithm Tab -->
        </form>
    </body>
</html>
