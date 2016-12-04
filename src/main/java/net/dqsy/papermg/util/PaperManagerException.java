package net.dqsy.papermg.util;

import org.apache.log4j.Logger;

public class PaperManagerException extends RuntimeException {
    Logger logger = Logger.getLogger(getClass().getName());

    public PaperManagerException() {
        this.logger.error("持久层发生了异常！");
    }

    public PaperManagerException(String message) {
        super(message);
    }
}