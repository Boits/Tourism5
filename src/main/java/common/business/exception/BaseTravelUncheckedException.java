package common.business.exception;

public abstract class BaseTravelUncheckedException extends RuntimeException {

    protected int code;

    public BaseTravelUncheckedException(int code, String message) {
        super(message);
        this.code = code;
    }
}
