package formrecognizer.customized;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.rest.RestProxy;

import formrecognizer.generated.FormRecognizerClient;
import formrecognizer.generated.implementation.FormRecognizerClientBuilder;

public class BaseClient{
    
    protected FormRecognizerClient service;    
    protected OperationService operationService;

    public BaseClient(String endpoint, FormRecognizerApiKeyCredential credential){
        HttpPipeline pipeline = new HttpPipelineBuilder().policies(new FormRecognizerCredentialPolicy(credential)).build();
        this.service = new FormRecognizerClientBuilder().endpoint(endpoint).pipeline(pipeline).build();
        this.operationService = RestProxy.create(OperationService.class, pipeline);
    }
}