package com.ruoyi.common.exception.user;

public class SignUpException extends UserException {
    private static final long serialVersionUID = 1L;

    private int type;

    public static final int USERNAME_EXISTED = 1;      // 用户名已存在

    public static final int PHONE_NUMBER_EXISTED = 2;  // 手机号已存在

    public SignUpException(int type) {
        super("user.sign.up", null);
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
