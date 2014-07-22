package org.forsp.badlink.spider.impl;

import java.util.Collection;

import org.forsp.badlink.spider.api.Browser;
import org.forsp.badlink.spider.api.Resource;
import org.forsp.badlink.spider.api.WebPage;

import com.gargoylesoftware.htmlunit.Page;

/**
 * 
 * @author Serhii Krivtsov
 *
 */
public class HtmlUnitWebPage implements WebPage {

    private Page page;

    private Browser browser;

    public HtmlUnitWebPage(Browser browser, Page page) {
        this.browser = browser;
        this.page = page;
    }

    public Page getHtmlUnitPage() {
        return page;
    }

    public WebPage getPage() {
        return this;
    }

    public int getStatus() {
        return page.getWebResponse().getStatusCode();
    }

    public String getType() {
        return page.getWebResponse().getContentType();
    }

    public String getUrl() {
        return page.getUrl().toString();
    }

    public long getSize() {
        return getContent().length();
    }

    public String getContent() {
        return page.getWebResponse().getContentAsString();
    }

    public Browser getBrowser() {
        return browser;
    }

    public Collection<Resource> getResources() {
        // TODO Auto-generated method stub
        return null;
    }

}
