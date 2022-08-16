package com.ddim.happygo.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
/**
 * @author sunnyzyq
 * @date 2021/12/17
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelImport {
 
    /** 欄位名稱 */
    String value();
 
    /** 匯出對映，格式如：0-未知;1-男;2-女 */
    String kv() default "";
 
    /** 是否為必填欄位（預設為非必填） */
    boolean required() default false;
 
    /** 最大長度（預設255） */
    int maxLength() default 255;
 
    /** 匯入唯一性驗證（多個欄位則取聯合驗證） */
    boolean unique() default false;
 
}