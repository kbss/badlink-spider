package org.forsp.badlink.spider.api;

/**
 * 
 * @author Serhii Krivtsov
 *
 */
public interface Resource {

    WebPage getPage();

    // TODO: enum
    int getStatus();

    // TODO: MediaType
    String getType();

    String getUrl();

    long getSize();

    String getContent();
}
