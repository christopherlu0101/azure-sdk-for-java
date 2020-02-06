package formrecognizer.customized;

import reactor.core.publisher.Mono;

public class ReceiptClient extends BaseClient {

    static final Boolean DEFAULT_INCLUDE_DETAIL = false;

    public ReceiptClient(String endpoint, FormRecognizerApiKeyCredential credential) {
        super(endpoint, credential);
    }

    public AnalyzeOperation StartAnalyze(String contentUrl) {
        return StartAnalyzeAsync(contentUrl, DEFAULT_INCLUDE_DETAIL).block();
    }

    public AnalyzeOperation StartAnalyze(String contentUrl, Boolean includeTextDetail) {
        return StartAnalyzeAsync(contentUrl, includeTextDetail).block();
    }

    public AnalyzeOperation StartAnalyze(byte[] content, String contentType) {
        return StartAnalyzeAsync(content, contentType, DEFAULT_INCLUDE_DETAIL).block();
    }

    public AnalyzeOperation StartAnalyze(byte[] content, String contentType, Boolean includeTextDetail) {
        return StartAnalyzeAsync(content, contentType, includeTextDetail).block();
    }

    public Mono<AnalyzeOperation> StartAnalyzeAsync(String contentUrl) {
        return StartAnalyzeAsync(contentUrl, DEFAULT_INCLUDE_DETAIL);
    }

    public Mono<AnalyzeOperation> StartAnalyzeAsync(String contentUrl, Boolean includeTextDetail) {
        AnalyzeRequest analyzeRequest = new AnalyzeRequest(contentUrl);
        return StartAnalyzeAsyncPrivate(analyzeRequest, "application/json", includeTextDetail);
    }

    public Mono<AnalyzeOperation> StartAnalyzeAsync(byte[] content, String contentType) {
        return StartAnalyzeAsync(content, contentType, DEFAULT_INCLUDE_DETAIL);
    }

    public Mono<AnalyzeOperation> StartAnalyzeAsync(byte[] content, String contentType, Boolean includeTextDetail) {
        return StartAnalyzeAsyncPrivate(content, contentType, includeTextDetail);
    }

    private Mono<AnalyzeOperation> StartAnalyzeAsyncPrivate(Object content, String contentType,
            Boolean includeTextDetail) {
        return Mono.defer(() -> {
            String operationId;
            switch (contentType) {
            case "application/json":
                operationId = service.analyzeReceiptAsyncWithRestResponseAsync(includeTextDetail, content).block()
                        .getDeserializedHeaders().getOperationLocation();
                break;
            case "application/pdf":
                operationId = service.analyzeReceiptAsyncWithPdfWithRestResponseAsync(includeTextDetail, content)
                        .block().getDeserializedHeaders().getOperationLocation();
                break;
            case "image/jpeg":
                operationId = service.analyzeReceiptAsyncWithJpegWithRestResponseAsync(includeTextDetail, content)
                        .block().getDeserializedHeaders().getOperationLocation();
                break;
            case "image/png":
                operationId = service.analyzeReceiptAsyncWithPngWithRestResponseAsync(includeTextDetail, content)
                        .block().getDeserializedHeaders().getOperationLocation();
                break;
            case "image/tiff":
                operationId = service.analyzeReceiptAsyncWithTiffWithRestResponseAsync(includeTextDetail, content)
                        .block().getDeserializedHeaders().getOperationLocation();
                break;
            default:
                throw new RuntimeException("Unsupport Content Type.");
            }
            return Mono.just(new AnalyzeOperation(operationService, operationId));
        });
    }
}