package com.uber.invoice.exceptions;

public class PrinterException extends BaseException {

    /**
     * 
     */
    private static final long serialVersionUID = -4565969351832232291L;

    public PrinterException(String message, Throwable cause) {
        super(message, cause);
        // 
    }

    public PrinterException(String message) {
        super(message);
        // 
    }

}
