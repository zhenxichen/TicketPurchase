package com.ruoyi.common.exception.user;

public class PhoneNumberNotUniqueException extends UserException {
    private static final long serialVersionUID = 1L;

    public PhoneNumberNotUniqueException() {
        super("user.phone.not.unique", null);
    }
}
