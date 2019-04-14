package user.exception.checked;

import common.business.exception.BaseTravelCheckedException;
import user.exception.UserExceptionMeta;

public class UserHasNoOrdersException extends BaseTravelCheckedException {

    public UserHasNoOrdersException(int code, String message) {
        super(code, message);
    }

    public UserHasNoOrdersException(UserExceptionMeta exceptionMeta) {
        super(exceptionMeta.getCode(), exceptionMeta.getDescription());
    }
}
