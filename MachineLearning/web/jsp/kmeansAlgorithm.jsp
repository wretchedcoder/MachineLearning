<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <input id="enableKmeansAlg" name="enableKmeansAlg" type="checkbox" class="algEnabler">
    <label for="enableKmeansAlg">Use K-Means Algorithm</label>
</div>
<br/>
<div style="margin: 0 auto;">
<div>
    <div style="width: 37%; text-align: right;">
        <label for="clusterOption">Clusters</label>
        <input id="clusterOption" name="clusterOption" type="text" value="2" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="maxTimeOption">Maximum Wait Time (seconds)</label>
        <input id="maxTimeOption" name="maxTimeOption" type="text" value="10" class="algMaxTime">
    </div>
</div>
</div>  <!-- Outer Option DIV --> 