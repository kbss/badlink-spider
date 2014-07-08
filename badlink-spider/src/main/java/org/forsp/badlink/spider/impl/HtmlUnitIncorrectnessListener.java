package org.forsp.badlink.spider.impl;

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

    public void notify(String message, Object origin) {
        logger.error(message);
    }

}
