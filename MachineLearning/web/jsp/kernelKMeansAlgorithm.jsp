<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <input id="enableKernelKmeansAlg" name="enableKernelKmeansAlg" type="checkbox" class="algEnabler">
    <label for="enableKernelKmeansAlg">Use Kernel K-Means Algorithm</label>
</div>
<br/>
<div style="margin: 0 auto;">
<div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelKMmeansClusterOption">Clusters</label>
        <input id="kernelKMmeansClusterOption" name="kernelKMmeansClusterOption" type="text" value="2" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelKMeansMaxTimeOption">Maximum Wait Time (seconds)</label>
        <input id="kernelKMeansMaxTimeOption" name="kernelKMeansMaxTimeOption" type="text" value="10" class="algMaxTime">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelKMmeansKernel">Maximum Wait Time (seconds)</label>
        <select id="kernelKMmeansKernel" name="kernelKMmeansKernel">
            <option value="linear">linear</option>
            <option value="polynomial">polynomial degree 2</option>
            <option value="gaussian">gaussian</option>            
        </select>
    </div>
</div>
</div>