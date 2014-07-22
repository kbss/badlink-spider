package org.forsp.badlink.spider.api.listener;

import org.forsp.badlink.spider.api.WebPage;

/**
 * 
 * @author Serhii Krivtsov
 *
 */
public interface PageListener {
    void onPageComplete(WebPage page);
}
