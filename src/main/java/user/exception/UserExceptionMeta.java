package user.exception;

public enum UserExceptionMeta {
    USER_HAS_NO_ORDERS_EXCEPTION(20, "User hasn't orders exception");

    private int code;
    private String description;

    UserExceptionMeta(int code, String message) {
        this.code = code;
        this.description = message;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
