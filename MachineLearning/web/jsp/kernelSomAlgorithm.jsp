<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <input id="enableKernelSomAlg" name="enableKernelSomAlg" type="checkbox" class="algEnabler">
    <label for="enableKernelSomAlg">Use Kernel SOM Algorithm</label>
</div>
<br/>
<div style="margin: 0 auto;">
<div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelSomClusterOption">Clusters</label>
        <input id="kernelSomClusterOption" name="kernelSomClusterOption" type="text" value="2" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelSomMaxTimeOption">Maximum Wait Time (seconds)</label>
        <input id="kernelSomMaxTimeOption" name="kernelSomMaxTimeOption" type="text" value="10" class="algMaxTime">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelSomKernel">Kernel Type</label>
        <select id="kernelSomKernel" name="kernelSomKernel">
            <option value="linear">linear</option>
            <option value="polynomial">polynomial degree 2</option>
            <option value="gaussian">gaussian</option>            
        </select>
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelSomMaxTimeStep">Maximum Time(t) Steps</label>
        <input id="kernelSomMaxTimeStep" name="kernelSomMaxTimeStep" type="text" value="10" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelSomMinEpsilon">Minimum Epsilon</label>
        <input id="kernelSomMinEpsilon" name="kernelSomMinEpsilon" type="text" value="10" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelSomMaxEpsilon">Maximum Epsilon</label>
        <input id="kernelSomMaxEpsilon" name="kernelSomMaxEpsilon" type="text" value="10" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelSomMinSigma">Minimum Sigma</label>
        <input id="kernelSomMinSigma" name="kernelSomMinSigma" type="text" value="10" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelSomMaxSigma">Maximum Sigma</label>
        <input id="kernelSomMaxSigma" name="kernelSomMaxSigma" type="text" value="10" class="algClusters">
    </div>
</div>
</div>