<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <input id="enableKernelFuzzyCMeansFeatureSpaceAlg" name="enableKernelFuzzyCMeansFeatureSpaceAlg" type="checkbox" class="algEnabler">
    <label for="enableKernelFuzzyCMeansFeatureSpaceAlg">Use Kernel Fuzzy C-Means in Feature Space Algorithm</label>
</div>
<br/>
<div style="margin: 0 auto;">
<div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelFuzzyCMeansFeatureSpaceClusterOption">Clusters</label>
        <input id="kernelFuzzyCMeansFeatureSpaceClusterOption" 
               name="kernelFuzzyCMeansFeatureSpaceClusterOption" type="text" value="2" class="algClusters">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelFuzzyCMeansFeatureSpaceMaxTimeOption">Maximum Wait Time (seconds)</label>
        <input id="kernelFuzzyCMeansFeatureSpaceMaxTimeOption" 
               name="kernelFuzzyCMeansFeatureSpaceMaxTimeOption" type="text" value="10" class="algMaxTime">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelFuzzyCMeansFeatureSpaceMembership">Membership Variable</label>
        <input id="kernelFuzzyCMeansFeatureSpaceMembership" 
               name="kernelFuzzyCMeansFeatureSpaceMembership" type="text" value="2" class="algMaxTime">
    </div>
    <div style="width: 37%; text-align: right;">
        <label for="kernelFuzzyCMeansFeatureSpaceKernel">Maximum Wait Time (seconds)</label>
        <select id="kernelFuzzyCMeansFeatureSpaceKernel" name="kernelFuzzyCMeansFeatureSpaceKernel">
            <option value="linear">linear</option>
            <option value="polynomial">polynomial degree 2</option>
            <option value="gaussian">gaussian</option>            
        </select>
    </div>
</div>
</div>  <!-- Outer Option DIV --> 