package org.forsp.badlink.spider.api;

import java.util.Collection;

import org.forsp.badlink.spider.api.listener.PageListener;


/**
 * 
 * @author Serhii Krivtsov
 *
 */
public interface Browser {

    WebPage open(String url);
    
    Collection<PageListener> getPageListeners();
    
}
