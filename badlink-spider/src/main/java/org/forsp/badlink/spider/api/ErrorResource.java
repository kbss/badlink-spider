package org.forsp.badlink.spider.api;

/**
 * 
 * @author Serhii Krivtsov
 *
 */
public interface ErrorResource extends Resource {

    String getErrorMessage();

    int getLineNo();

    int getColumnNo();
}