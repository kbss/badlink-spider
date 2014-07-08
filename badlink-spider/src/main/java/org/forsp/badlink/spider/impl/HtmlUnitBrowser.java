package org.forsp.badlink.spider.impl;

import java.io.IOException;
import java.net.MalformedURLException;

import org.forsp.badlink.spider.api.Browser;
import org.forsp.badlink.spider.api.WebPage;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

/**
 * 
 * @author Serhii Krivtsov
 *
 */
public class HtmlUnitBrowser implements Browser {

    private String base;

    private WebClient webClient;

    private Page page;

    public HtmlUnitBrowser(String baseUrl) {
        this.base = baseUrl;
        webClient = new WebClient();
        webClient.setIncorrectnessListener(new HtmlUnitIncorrectnessListener());
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.setJavaScriptErrorListener(new HtmlUnitJavaErrorScriptListener());
    }

    public String getBaseUrl() {
        return base;
    }

    public WebPage open(String url) {
        try {
            page = webClient.getPage(url);
        } catch (FailingHttpStatusCodeException e) {
            // e.printStackTrace();
        } catch (MalformedURLException e) {
            // e.printStackTrace();
        } catch (IOException e) {
            // e.printStackTrace();
        }
        return new HtmlUnitWebPage(this, page);
    }

}
