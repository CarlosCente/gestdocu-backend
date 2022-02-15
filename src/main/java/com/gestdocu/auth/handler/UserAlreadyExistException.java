package com.gestdocu.auth.handler;

import javax.naming.AuthenticationException;

public class UserAlreadyExistException extends AuthenticationException {

	private static final long serialVersionUID = -2467023631019879490L;

	public UserAlreadyExistException(final String msg) {
        super(msg);
    }

}