package org.forsp.badlink.spider.api.listener;

import org.forsp.badlink.spider.api.Browser;
import org.forsp.badlink.spider.api.ErrorResource;
import org.forsp.badlink.spider.api.WebPage;
import org.forsp.badlink.spider.api.Resource;
import org.forsp.badlink.spider.api.enums.Status;

/**
 * 
 * @author Serhii Krivtsov
 *
 */
public interface ReportListener {

    Browser getBrowser();

    void onComplete(Browser browser);

    void onFail(ErrorResource resource);

    void onPageComplete(WebPage browser);

    void onPageStart(WebPage browser);

    void onStart(Browser browser);

    void onSuccess(Resource resource);

    void onError(ErrorResource resource);

    void onWarning(ErrorResource resource);

    Status getStatus();
}