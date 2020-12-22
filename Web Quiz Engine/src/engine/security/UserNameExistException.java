package engine.security;

import org.springframework.security.core.AuthenticationException;

public class UserNameExistException extends AuthenticationException {
    public UserNameExistException(String msg) {
        super(msg);
    }
}
