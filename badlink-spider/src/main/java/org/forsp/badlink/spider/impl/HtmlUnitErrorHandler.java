package org.forsp.badlink.spider.impl;

import org.forsp.badlink.spider.api.listener.ReportListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.CSSParseException;
import org.w3c.css.sac.ErrorHandler;

/**
 * 
 * @author Serhii Krivtsov
 *
 */
public class HtmlUnitErrorHandler implements ErrorHandler {

    // TODO: Create enum
    private static final String CSS_TYPE = "text/css";

    private static Logger logger = LoggerFactory.getLogger(HtmlUnitErrorHandler.class);

    private ReportListener listener;

    private String url;

    public HtmlUnitErrorHandler(ReportListener listener, String url) {
        this.listener = listener;
        this.url = url;
    }

    public void error(CSSParseException arg0) throws CSSException {

        if (listener != null) {
            listener.onError(getResource(arg0));
            return;
        }
        logger.error(getMessage(arg0));
    }

    public void fatalError(CSSParseException arg0) throws CSSException {

        if (listener != null) {
            listener.onError(getResource(arg0));
            return;
        }
        logger.error(getMessage(arg0));

    }

    private String getMessage(CSSParseException e) {
        if (e != null) {
            return String.format("%s\nURL: %s\nline: %s\ncolumn: %s", e.getMessage(), e.getURI(), e.getLineNumber(), e.getColumnNumber());
        }
        return "";
    }

    private ErrorResourceImpl getResource(CSSParseException e) {
        ErrorResourceImpl resource = new ErrorResourceImpl();
        resource.setType(CSS_TYPE);
        resource.setUrl(e.getURI());
        resource.setLineNo(e.getLineNumber());
        resource.setColumnNo(e.getColumnNumber());
        resource.setMessage(getMessage(e));
        return resource;
    }

    public void warning(CSSParseException arg0) throws CSSException {

        if (listener != null) {
            listener.onWarning(getResource(arg0));
            return;
        }
        logger.error(getMessage(arg0));
    }

}