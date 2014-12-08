<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <input id="enableProbFuzzyCMeansAlg" name="enableProbFuzzyCMeansAlg" type="checkbox" class="algEnabler">
    <label for="enableProbFuzzyCMeansAlg">Use Probabilistic Fuzzy C-Means Algorithm</label>
</div>
<br/>
<div style="margin: 0 auto;">
<div>
    <div style="width: 37%; text-align: right;">
        <label for="probFuzzyCMeansClusterOption">Clusters</label>
        <input id="probFuzzyCMeansClusterOption" name="probFuzzyCMeansClusterOption" type="text" value="2" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="probFuzzyCMeansMaxTimeOption">Maximum Wait Time (seconds)</label>
        <input id="probFuzzyCMeansMaxTimeOption" name="probFuzzyCMeansMaxTimeOption" type="text" value="10" class="algMaxTime">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="probFuzzyCMeansMembership">Membership Variable</label>
        <input id="probFuzzyCMeansMembership" name="probFuzzyCMeansMembership" type="text" value="2" class="algMaxTime">
    </div>
</div>
</div>  <!-- Outer Option DIV --> 