package org.forsp.badlink.spider.api.enums;

/**
 * 
 * @author Serhii Krivtsov
 *
 */

public enum Status {

    OK((short) 0), WARN((short) 1), ERROR((short) 2);

    short level;

    private Status(short level) {
        this.level = level;
    }

    public short getLevel() {
        return level;
    }
}
