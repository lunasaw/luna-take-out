package com.luna.meal.exception;

import com.luna.common.exception.BaseException;

/**
 * @author luna
 */
public class UserException extends BaseException {

    public UserException(int code, String message) {
        super(code, message);
    }
}