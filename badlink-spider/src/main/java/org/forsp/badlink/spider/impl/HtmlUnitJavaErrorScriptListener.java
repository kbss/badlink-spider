package org.forsp.badlink.spider.impl;

import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;

/**
 * 
 * @author Serhii Krivtsov
 *
 */
public class HtmlUnitJavaErrorScriptListener implements JavaScriptErrorListener {

    private static Logger logger = LoggerFactory.getLogger(JavaScriptErrorListener.class);

    public void scriptException(HtmlPage htmlPage, ScriptException scriptException) {
        logger.error(scriptException.getMessage());

    }

    public void timeoutError(HtmlPage htmlPage, long allowedTime, long executionTime) {
        logger.error("Timeout, Allowed time:{} ms.,  Execution time: {} ms.");

    }

    public void malformedScriptURL(HtmlPage htmlPage, String url, MalformedURLException malformedURLException) {
        logger.error(malformedURLException.getMessage());

    }

    public void loadScriptError(HtmlPage htmlPage, URL scriptUrl, Exception exception) {
        logger.error("Unable to load script: {}", scriptUrl, exception.getMessage());
    }

}
