package org.forsp.badlink.spider.impl;

import org.forsp.badlink.spider.api.ErrorResource;
import org.forsp.badlink.spider.api.WebPage;

/**
 * 
 * @author Serhii Krivtsov
 *
 */
public class ErrorResourceImpl implements ErrorResource {

    private int columnNo;
    private String content;
    private int lineNo;
    private String errorMessage;
    private WebPage page;
    private int size;
    private int status;
    private String type;

    private String url;

    public int getColumnNo() {
        return columnNo;
    }

    public String getContent() {
        return content;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getLineNo() {
        return lineNo;
    }

    public WebPage getPage() {
        return page;
    }

    public long getSize() {
        return size;
    }

    public int getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public void setColumnNo(int columnNo) {
        this.columnNo = columnNo;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public void setMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setPage(WebPage page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
