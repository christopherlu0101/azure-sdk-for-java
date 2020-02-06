// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is
// regenerated.

package formrecognizer.generated.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/**
 * Contains all response data for the analyzeReceiptAsyncWithPdf operation.
 */
public final class AnalyzeReceiptAsyncWithPdfResponse extends ResponseBase<AnalyzeReceiptAsyncWithPdfHeaders, Void> {
    /**
     * Creates an instance of AnalyzeReceiptAsyncWithPdfResponse.
     *
     * @param request the request which resulted in this AnalyzeReceiptAsyncWithPdfResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public AnalyzeReceiptAsyncWithPdfResponse(HttpRequest request, int statusCode, HttpHeaders rawHeaders, Void value, AnalyzeReceiptAsyncWithPdfHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
