package formrecognizer.customized;

import java.time.Duration;
import formrecognizer.generated.models.AnalyzeOperationResult;
import formrecognizer.generated.models.AnalyzeResult;

public class AnalyzeOperation{

    private static final Duration DEFAULT_RETRY_TIME = Duration.ofSeconds(2);

    private final Duration _retryTime;
    private final OperationService _operationService;
    private final String _operationId;    
    private AnalyzeOperationResult _operationResult;        

    public Boolean HasValue;
    public Boolean HasComplete;
    public AnalyzeResult Value;

    public AnalyzeOperation(final OperationService operationService, final String operationId){
        this(operationService, operationId, DEFAULT_RETRY_TIME);
    }

    public AnalyzeOperation(final OperationService operationService, final String operationId, final Duration retryTime){
        _retryTime = retryTime;
        _operationService = operationService;
        _operationId = operationId;
        HasValue = false;
        HasComplete = false;
        Value = null;
    }

    public AnalyzeResult WaitForComplete() throws InterruptedException {
        while (!CheckComplete()){
            Thread.sleep(_retryTime.toMillis());
        }
        return Value;            
    }

    public boolean CheckComplete(){
        if (!HasComplete){
            _operationResult = _operationService.GetOperatonResult(_operationId).block();
            System.out.println(_operationResult.getStatus());
            switch (_operationResult.getStatus()){
                case SUCCEEDED:
                    Value = _operationResult.getAnalyzeResult();
                    HasValue = true;
                case FAILED:
                    HasComplete = true;
                    return true;
                default:
                    return false;
            }
        }
        return true;
    }

}