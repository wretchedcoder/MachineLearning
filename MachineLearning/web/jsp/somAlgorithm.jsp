<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <input id="enableSomAlg" name="enableSomAlg" type="checkbox" class="algEnabler">
    <label for="enableSomAlg">Use SOM Algorithm</label>
</div>
<br/>
<div style="margin: 0 auto;">
<div>
    <div style="width: 37%; text-align: right;">
        <label for="somClusterOption">Clusters</label>
        <input id="somClusterOption" name="somClusterOption" type="text" value="2" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="somMaxTimeOption">Maximum Wait Time (seconds)</label>
        <input id="somsMaxTimeOption" name="somMaxTimeOption" type="text" value="10" class="algMaxTime">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="somMaxTimeStep">Maximum Time(t) Steps</label>
        <input id="somMaxTimeStep" name="somMaxTimeStep" type="text" value="10" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="somMinEpsilon">Minimum Epsilon</label>
        <input id="somMinEpsilon" name="somMinEpsilon" type="text" value="10" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="somMaxEpsilon">Maximum Epsilon</label>
        <input id="somMaxEpsilon" name="somMaxEpsilon" type="text" value="10" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="somMinSigma">Minimum Sigma</label>
        <input id="somMinSigma" name="somMinSigma" type="text" value="10" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="somMaxSigma">Maximum Sigma</label>
        <input id="somMaxSigma" name="somMaxSigma" type="text" value="10" class="algClusters">
    </div>
</div>
</div>  <!-- Outer Option DIV --> 