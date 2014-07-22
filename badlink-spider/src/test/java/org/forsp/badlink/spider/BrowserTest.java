package org.forsp.badlink.spider;

import org.forsp.badlink.spider.api.WebPage;
import org.forsp.badlink.spider.impl.HtmlUnitBrowser;
import org.forsp.badlink.spider.impl.SimpleSeoAnalyzer;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author Serhii Krivtsov
 *
 */
public class BrowserTest {

    private int NOT_FOUND_CODE = 404;
    private int OK_STATUS = 200;

    @Test
    public void testBrowser() {
        HtmlUnitBrowser br = new HtmlUnitBrowser("http://znaj.net");
        br.addPageListeners(new SimpleSeoAnalyzer());
        WebPage page = br.open("http://znaj.net");
        Assert.assertNotNull(page);
        Assert.assertNotNull(page.getContent());
        Assert.assertEquals(OK_STATUS, page.getStatus());
    }

    @Test
    public void testNotFoundPage() {
        String badLink = "http://znaj.net/unknown-page-" + System.currentTimeMillis() + ".html";
        HtmlUnitBrowser br = new HtmlUnitBrowser("http://znaj.net/");
        WebPage page = br.open(badLink);
        Assert.assertNotNull(page);
        Assert.assertEquals(NOT_FOUND_CODE, page.getStatus());
    }
}
