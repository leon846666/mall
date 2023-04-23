package com.common.exception;

/**
 * @Description:  ERROR CODES
 * @Created: with IntelliJ IDEA.

 * @createTime: 2020-05-27 17:29
 *
 *
 *  10: general
 *      001：param format
 *      002：sms too frequent
 *  11: product
 *  12: order
 *  13: cart
 *  14: feight
 *  15：user
 *
 *
 *
 **/

public enum BizCodeEnum {

    UNKNOW_EXCEPTION(10000,"Unknown Error"),
    VAILD_EXCEPTION(10001,"Param invalid "),
    TO_MANY_REQUEST(10002,"Too many request ，please try again later"),
    SMS_CODE_EXCEPTION(10002,"SMS is sent too frequent, please wait"),
    PRODUCT_UP_EXCEPTION(11000,"product listing wrong,please try again later"),
    USER_EXIST_EXCEPTION(15001,"user is already exist"),
    PHONE_EXIST_EXCEPTION(15002,"phone is already used"),
    NO_STOCK_EXCEPTION(21000,"product out of stock "),
    LOGINACCT_PASSWORD_EXCEPTION(15003,"incorrect credentials, please try again."),
    ;

    private Integer code;

    private String message;

    BizCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
