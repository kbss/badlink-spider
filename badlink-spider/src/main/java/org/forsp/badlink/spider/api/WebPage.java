package org.forsp.badlink.spider.api;

import java.util.Collection;

/**
 * 
 * @author Serhii Krivtsov
 *
 */
public interface WebPage extends Resource {
    Browser getBrowser();

    Collection<Resource> getResources();
}
