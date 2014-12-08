<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <input id="enableKernelFuzzyCMeansKernelMetricAlg" name="enableKernelFuzzyCMeansKernelMetricAlg" type="checkbox" class="algEnabler">
    <label for="enableKernelFuzzyCMeansKernelMetricAlg">Use Kernel Fuzzy C-Means w/ Kernelized Metric Algorithm</label>
</div>
<br/>
<div style="margin: 0 auto;">
<div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelFuzzyCMeansKernelMetricClusterOption">Clusters</label>
        <input id="kernelFuzzyCMeansKernelMetricClusterOption" 
               name="kernelFuzzyCMeansKernelMetricClusterOption" type="text" value="2" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelFuzzyCMeansKernelMetricMaxTimeOption">Maximum Wait Time (seconds)</label>
        <input id="kernelFuzzyCMeansKernelMetricMaxTimeOption" 
               name="kernelFuzzyCMeansKernelMetricMaxTimeOption" type="text" value="10" class="algMaxTime">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelFuzzyCMeansKernelMetricMembership">Membership Variable</label>
        <input id="kernelFuzzyCMeansKernelMetricMembership" 
               name="kernelFuzzyCMeansKernelMetricMembership" type="text" value="2" class="algMaxTime">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelFuzzyCMeansKernelMetricKernel">Maximum Wait Time (seconds)</label>
        <select id="kernelFuzzyCMeansKernelMetricKernel" name="kernelFuzzyCMeansKernelMetricKernel">
            <option value="linear">linear</option>
            <option value="polynomial">polynomial degree 2</option>
            <option value="gaussian">gaussian</option>            
        </select>
    </div>
</div>
</div>  <!-- Outer Option DIV --> 