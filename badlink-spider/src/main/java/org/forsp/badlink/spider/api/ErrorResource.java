package org.forsp.badlink.spider.api;

/**
 * 
 * @author Serhii Krivtsov
 *
 */
public interface ErrorResource extends Resource {

    int getLineNo();

    int getOffset();

    String getErrorMessage();
}