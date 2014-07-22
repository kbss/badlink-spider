package org.forsp.badlink.spider.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.forsp.badlink.spider.api.Browser;
import org.forsp.badlink.spider.api.WebPage;
import org.forsp.badlink.spider.api.listener.PageListener;
import org.forsp.badlink.spider.api.listener.ReportListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 
 * @author Serhii Krivtsov
 *
 */
public class SimpleSeoAnalyzer implements PageListener {

    private static Logger logger = LoggerFactory.getLogger(SimpleSeoAnalyzer.class);

    private boolean isCompitable = true;

    private ReportListener reportListener;

    private String KEYWORDS_XPATH = "//meta[@name='keywords']";

    public void onPageComplete(WebPage page) {
        if (page != null && isCompitable) {
            if (page instanceof HtmlUnitWebPage) {
                reportListener = getReportListener(page);
                analyze(((HtmlUnitWebPage) page).getHtmlUnitPage());
            } else {
                logger.error("Incompitable class, expected {}, but found: {}", Page.class, page.getClass().getSimpleName());
                isCompitable = false;
            }
        }
    }

    private ReportListener getReportListener(WebPage page) {
        Browser browser = null;
        if (page == null) {

            return null;
        }
        browser = page.getBrowser();
        if (browser != null && browser instanceof HtmlUnitBrowser) {
            return ((HtmlUnitBrowser) browser).getReportListener();
        }
        return null;
    }

    private void analyze(Page page) {
        logger.error("Page : {}", page.getClass().getSimpleName());
        if (reportListener == null) {
            return;
        }
        if (page instanceof HtmlPage) {
            HtmlPage htmlPage = (HtmlPage) page;
            List<?> keywords = htmlPage.getByXPath(KEYWORDS_XPATH);
            if (keywords.isEmpty()) {
                logger.error("Page does not contains keywords");
                return;
            }
            if (keywords.size() > 1) {

            }
            for (Object elemetn : keywords) {
                if (elemetn instanceof HtmlElement) {
                    HtmlElement htmlElement = (HtmlElement) elemetn;
                    String keywordsString = htmlElement.getAttribute("content");
                    if (StringUtils.isNotBlank(keywordsString)) {
                        logger.debug("Page keywords: {}", keywordsString);
                        String[] words = keywordsString.split(",");
                        logger.debug("Keywords count: {}", words.length);
                    }
                }
            }
        }

    }
}
