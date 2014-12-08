<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <input id="enableKernelNeuralGasAlg" name="enableKernelNeuralGasAlg" type="checkbox" class="algEnabler">
    <label for="enableKernelNeuralGasAlg">Use Kernel Neural Gas Algorithm</label>
</div>
<br/>
<div style="margin: 0 auto;">
<div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelNeuralGasClusterOption">Clusters</label>
        <input id="kernelNeuralGasClusterOption" name="kernelNeuralGasClusterOption" type="text" value="2" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelNeuralGasMaxTimeOption">Maximum Wait Time (seconds)</label>
        <input id="kernelNeuralGasMaxTimeOption" name="kernelNeuralGasMaxTimeOption" type="text" value="10" class="algMaxTime">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelNeuralGasKernel">Kernel Type</label>
        <select id="kernelNeuralGasKernel" name="kernelNeuralGasKernel">
            <option value="linear">linear</option>
            <option value="polynomial">polynomial degree 2</option>
            <option value="gaussian">gaussian</option>            
        </select>
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelNeuralGasMaxTimeStep">Maximum Time(t) Steps</label>
        <input id="kernelNeuralGasMaxTimeStep" name="kernelNeuralGasMaxTimeStep" type="text" value="10" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelNeuralGasMinEpsilon">Minimum Epsilon</label>
        <input id="kernelNeuralGasMinEpsilon" name="kernelNeuralGasMinEpsilon" type="text" value="10" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelNeuralGasMaxEpsilon">Maximum Epsilon</label>
        <input id="kernelNeuralGasMaxEpsilon" name="kernelNeuralGasMaxEpsilon" type="text" value="10" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelNeuralGasDecay">Neural Gas Decay</label>
        <input id="kernelNeuralGasDecay" name="kernelNeuralGasDecay" type="text" value="10" class="algClusters">
    </div>
</div>
</div>