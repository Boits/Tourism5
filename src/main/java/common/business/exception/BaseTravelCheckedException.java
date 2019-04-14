package common.business.exception;

public abstract class BaseTravelCheckedException extends Exception {

    protected int code;

    public BaseTravelCheckedException(int code, String message) {
        super(message);
        this.code = code;
    }
}
