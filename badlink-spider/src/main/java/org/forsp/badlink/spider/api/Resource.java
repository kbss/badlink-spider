package org.forsp.badlink.spider.api;

/**
 * 
 * @author Serhii Krivtsov
 *
 */
public interface Resource {

    String getContent();

    WebPage getPage();

    long getSize();

    // TODO: enum
    int getStatus();

    // TODO: MediaType
    String getType();

    String getUrl();
}
