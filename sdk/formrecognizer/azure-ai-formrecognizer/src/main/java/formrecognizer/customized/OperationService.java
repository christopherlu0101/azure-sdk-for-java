package formrecognizer.customized;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.ServiceInterface;

import reactor.core.publisher.Mono;
import formrecognizer.generated.models.AnalyzeOperationResult;

@Host("{operationId}")
@ServiceInterface(name = "OperationService")
public interface OperationService{
    @Get("")
    @ExpectedResponses({200})
    Mono<AnalyzeOperationResult> GetOperatonResult(@HostParam("operationId") String operationId);
}