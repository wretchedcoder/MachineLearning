<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <input id="enableNeuralGasAlg" name="enableNeuralGasAlg" type="checkbox" class="algEnabler">
    <label for="enableNeuralGasAlg">Use Neural Gas Algorithm</label>
</div>
<br/>
<div style="margin: 0 auto;">
<div>
    <div style="width: 37%; text-align: right;">
        <label for="neuralGasClusterOption">Clusters</label>
        <input id="neuralGasClusterOption" name="neuralGasClusterOption" type="text" value="2" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="neuralGasMaxTimeOption">Maximum Wait Time (seconds)</label>
        <input id="neuralGasMaxTimeOption" name="neuralGasMaxTimeOption" type="text" value="10" class="algMaxTime">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="neuralGasMaxTimeStep">Maximum Time(t) Steps</label>
        <input id="neuralGasMaxTimeStep" name="neuralGasMaxTimeStep" type="text" value="10" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="neuralGasMinEpsilon">Minimum Epsilon</label>
        <input id="neuralGasMinEpsilon" name="neuralGasMinEpsilon" type="text" value="10" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="neuralGasMaxEpsilon">Maximum Epsilon</label>
        <input id="neuralGasMaxEpsilon" name="neuralGasMaxEpsilon" type="text" value="10" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="neuralGasDecay">Neural Gas Decay</label>
        <input id="neuralGasDecay" name="neuralGasDecay" type="text" value="10" class="algClusters">
    </div>
</div>
</div>  <!-- Outer Option DIV --> 