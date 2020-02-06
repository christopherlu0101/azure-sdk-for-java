package formrecognizer.customized;


import com.azure.core.http.HttpPipelineCallContext;
import com.azure.core.http.HttpPipelineNextPolicy;
import com.azure.core.http.HttpResponse;
import com.azure.core.http.policy.HttpPipelinePolicy;
import reactor.core.publisher.Mono;

/**
 * Policy that adds the {@link FormRecognizerApiKeyCredential} into the request's `Ocp-Apim-Subscription-Key`
 * header.
 */
public final class FormRecognizerCredentialPolicy implements HttpPipelinePolicy {
    private static final String OCP_APIM_SUBSCRIPTION_KEY = "Ocp-Apim-Subscription-Key";
    private final FormRecognizerApiKeyCredential credential;

    /**
     * Creates a {@link SubscriptionKeyCredentialPolicy} pipeline policy that adds the
     * {@link FormRecognizerApiKeyCredential} into the request's `Ocp-Apim-Subscription-Key` header.
     *
     * @param credential the {@link FormRecognizerApiKeyCredential} credential used to create the policy.
     */
    public FormRecognizerCredentialPolicy(FormRecognizerApiKeyCredential credential) {
        this.credential = credential;
    }

    @Override
    public Mono<HttpResponse> process(HttpPipelineCallContext context, HttpPipelineNextPolicy next) {
        context.getHttpRequest().setHeader(OCP_APIM_SUBSCRIPTION_KEY, credential.getSubscriptionKey());
        return next.process();
    }
}