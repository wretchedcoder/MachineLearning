<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <input id="enableKmeansAlg" name="enableKmeansAlg" type="checkbox" class="algEnabler">
    <label for="enableKmeansAlg">Use K-Means Algorithm</label>
</div>
<br/>
<div style="margin: 0 auto;">
<div>
    <div style="width: 37%; text-align: right;">
        <label for="kmeansClusterOption">Clusters</label>
        <input id="kmeansClusterOption" name="kmeansClusterOption" type="text" value="2" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kmeansMaxTimeOption">Maximum Wait Time (seconds)</label>
        <input id="kmeansMaxTimeOption" name="kmeansMaxTimeOption" type="text" value="10" class="algMaxTime">
    </div>
</div>
</div>  <!-- Outer Option DIV --> 