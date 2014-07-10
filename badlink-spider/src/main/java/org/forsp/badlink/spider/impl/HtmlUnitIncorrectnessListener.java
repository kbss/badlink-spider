package org.forsp.badlink.spider.impl;

import org.forsp.badlink.spider.api.listener.ReportListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.IncorrectnessListener;

/**
 * 
 * @author Serhii Krivtsov
 *
 */
public class HtmlUnitIncorrectnessListener implements IncorrectnessListener {

    private static Logger logger = LoggerFactory.getLogger(HtmlUnitIncorrectnessListener.class);

    private String url;
    private ReportListener listener;

    public HtmlUnitIncorrectnessListener(ReportListener listener, String url) {
        this.listener = listener;
        this.url = url;
    }

    public void notify(String message, Object origin) {

        if (listener != null) {
            ErrorResourceImpl error = new ErrorResourceImpl();
            error.setMessage(message);
            error.setUrl(url);
            listener.onWarning(error);
            return;
        }
        logger.error(message);

    }

}
