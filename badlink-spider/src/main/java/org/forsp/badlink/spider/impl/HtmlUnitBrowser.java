package org.forsp.badlink.spider.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.forsp.badlink.spider.api.Browser;
import org.forsp.badlink.spider.api.WebPage;
import org.forsp.badlink.spider.api.listener.PageListener;
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

    private List<PageListener> pageListeners;

    private ReportListener reportListener;

    private WebClient webClient;

    public HtmlUnitBrowser(String baseUrl) {
        this.base = baseUrl;
    }

    public String getBaseUrl() {
        return base;
    }

    public Collection<PageListener> getPageListeners() {
        if (pageListeners == null) {
            pageListeners = new ArrayList<PageListener>();
        }
        return pageListeners;
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
        for (PageListener listener : getPageListeners()) {
            listener.onPageComplete(webPage);
        }
        return webPage;
    }

    public void addPageListeners(PageListener pageListeners) {
        getPageListeners().add(pageListeners);
    }

    public void setReportListener(ReportListener reportListener) {
        this.reportListener = reportListener;
    }

}
