<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <input id="enableFuzzyCMeansAlg" name="enableFuzzyCMeansAlg" type="checkbox" class="algEnabler">
    <label for="enableFuzzyCMeansAlg">Use Fuzzy C-Means Algorithm</label>
</div>
<br/>
<div style="margin: 0 auto;">
<div>
    <div style="width: 37%; text-align: right;">
        <label for="fuzzyCMeansClusterOption">Clusters</label>
        <input id="fuzzyCMeansClusterOption" name="fuzzyCMeansClusterOption" type="text" value="2" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="fuzzyCMeansMaxTimeOption">Maximum Wait Time (seconds)</label>
        <input id="fuzzyCMeansMaxTimeOption" name="fuzzyCMeansMaxTimeOption" type="text" value="10" class="algMaxTime">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="fuzzyCMeansMembership">Membership Variable</label>
        <input id="fuzzyCMeansMembership" name="fuzzyCMeansMembership" type="text" value="2" class="algMaxTime">
    </div>
</div>
</div>  <!-- Outer Option DIV --> 