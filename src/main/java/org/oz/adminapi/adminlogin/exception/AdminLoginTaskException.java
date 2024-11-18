package org.oz.adminapi.adminlogin.exception;
import lombok.Data;

@Data
public class AdminLoginTaskException extends RuntimeException {

    private int status;
    private String msg;

    public AdminLoginTaskException(final int status, final String msg) {
        super(status +"_" + msg);
        this.status = status;
        this.msg = msg;
    }

}