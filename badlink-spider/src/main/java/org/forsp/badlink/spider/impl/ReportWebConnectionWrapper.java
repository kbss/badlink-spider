package org.forsp.badlink.spider.impl;

import java.io.IOException;

import org.forsp.badlink.spider.api.listener.ReportListener;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.WebConnectionWrapper;

/**
 * 
 * @author user
 *
 */
public class ReportWebConnectionWrapper extends WebConnectionWrapper {

    private ReportListener listener;

    public ReportWebConnectionWrapper(WebClient webClient, ReportListener listener) throws IllegalArgumentException {
        super(webClient);
        this.listener = listener;
    }

    @Override
    public WebResponse getResponse(WebRequest request) throws IOException {
        WebResponse response = super.getResponse(request);
        processResponse(response);
        return response;
    }

    private void processResponse(WebResponse response) {
        if (String.valueOf(response.getStatusCode()).matches("(4|5)\\d\\d")) {
            if (listener != null) {
                ErrorResourceImpl error = new ErrorResourceImpl();
                error.setUrl(response.getWebRequest().getUrl().toExternalForm());
                error.setStatus(response.getStatusCode());
                String statusMessage = response.getStatusMessage();
                error.setMessage(String.format("Bad status - %s (%s)", statusMessage, response.getStatusCode()));
                error.setContent(response.getContentAsString());
                listener.onError(error);
            }
        }
    }
}