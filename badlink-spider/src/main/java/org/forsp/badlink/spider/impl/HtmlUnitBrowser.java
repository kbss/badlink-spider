package org.forsp.badlink.spider.impl;

import java.io.IOException;
import java.net.MalformedURLException;

import org.forsp.badlink.spider.api.Browser;
import org.forsp.badlink.spider.api.WebPage;
import org.forsp.badlink.spider.api.listener.ReportListener;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

/**
 * 
 * @author Serhii Krivtsov
 *
 */
public class HtmlUnitBrowser implements Browser {

    private String base;

    // private Page page;

    private ReportListener reportListener;

    private WebClient webClient;

    public HtmlUnitBrowser(String baseUrl) {
        this.base = baseUrl;
    }

    public String getBaseUrl() {
        return base;
    }

    public ReportListener getReportListener() {
        return reportListener;
    }

    public WebPage open(String url) {

        webClient = new WebClient();
        reportListener = new SimplReportListener(null, url);
        webClient.setIncorrectnessListener(new HtmlUnitIncorrectnessListener(reportListener, url));
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.setJavaScriptErrorListener(new HtmlUnitJavaErrorScriptListener());
        webClient.setCssErrorHandler(new HtmlUnitErrorHandler(reportListener, url));
        webClient.setWebConnection(new ReportWebConnectionWrapper(webClient, reportListener));
        reportListener.onPageStart(null);
        Page page = null;
        try {
            page = webClient.getPage(url);
        } catch (MalformedURLException e) {
            // TODO: FIXME: Custom exception
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO: FIXME: Custom exception
            throw new RuntimeException(e);
        }
        WebPage webPage = new HtmlUnitWebPage(this, page);
        reportListener.onPageComplete(webPage);
        return webPage;
    }

    public void setReportListener(ReportListener reportListener) {
        this.reportListener = reportListener;
    }

}
