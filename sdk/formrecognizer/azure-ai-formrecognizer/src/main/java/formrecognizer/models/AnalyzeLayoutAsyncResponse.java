// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is
// regenerated.

package formrecognizer.models;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.ResponseBase;

/**
 * Contains all response data for the analyzeLayoutAsync operation.
 */
public final class AnalyzeLayoutAsyncResponse extends ResponseBase<AnalyzeLayoutAsyncHeaders, Void> {
    /**
     * Creates an instance of AnalyzeLayoutAsyncResponse.
     *
     * @param request the request which resulted in this AnalyzeLayoutAsyncResponse.
     * @param statusCode the status code of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param value the deserialized value of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     */
    public AnalyzeLayoutAsyncResponse(HttpRequest request, int statusCode, HttpHeaders rawHeaders, Void value, AnalyzeLayoutAsyncHeaders headers) {
        super(request, statusCode, rawHeaders, value, headers);
    }
}
