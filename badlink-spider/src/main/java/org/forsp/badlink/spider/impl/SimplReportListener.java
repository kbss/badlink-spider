package org.forsp.badlink.spider.impl;

import java.io.OutputStream;

import org.forsp.badlink.spider.api.Browser;
import org.forsp.badlink.spider.api.ErrorResource;
import org.forsp.badlink.spider.api.Resource;
import org.forsp.badlink.spider.api.WebPage;
import org.forsp.badlink.spider.api.enums.Status;
import org.forsp.badlink.spider.api.listener.ReportListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Serhii Krivtsov
 *
 */
public class SimplReportListener implements ReportListener {

    private static Logger logger = LoggerFactory.getLogger(SimplReportListener.class);

    private OutputStream stream;

    private String url;

    private Status status = Status.OK;

    private void setStatus(Status status) {
        if (status != null && this.status.getLevel() < status.getLevel()) {
            this.status = status;
        }
    }

    public SimplReportListener(OutputStream stream, String url) {
        this.stream = stream;
        this.url = url;
    }

    public Browser getBrowser() {
        return null;
    }

    public Status getStatus() {
        return status;
    }

    public void onComplete(Browser browser) {

    }

    public void onError(ErrorResource resource) {
        if (resource != null) {
            logger.debug("Page error: {}", resource.getErrorMessage());
            if (resource.getUrl() != null) {
                logger.debug("Resource URL: {}", resource.getUrl());
            }
        }
        setStatus(Status.ERROR);
    }

    public void onFail(ErrorResource resource) {
        if (resource != null) {
            logger.debug(resource.getErrorMessage());
        }
        setStatus(Status.ERROR);
    }

    public void onPageComplete(WebPage page) {
        logger.debug("Page validation status: {}", status);
        logger.debug("----------------------PAGE END-----------------------------");
    }

    public void onPageStart(WebPage browser) {
        logger.debug("----------------------PAGE START-----------------------------");
        logger.debug("Opening: {}", url);

    }

    public void onStart(Browser browser) {

    }

    public void onSuccess(Resource resource) {

    }

    public void onWarning(ErrorResource resource) {
        if (resource != null) {
            logger.debug("Page error: {}", resource.getErrorMessage());
        }
        setStatus(Status.WARN);
    }

}
