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
public @interface ExcelExport {
 
    /** 欄位名稱 */
    String value();
 
    /** 匯出排序先後: 數位越小越靠前（預設按Java類欄位順序匯出） */
    int sort() default 0;
 
    /** 匯出對映，格式如：0-未知;1-男;2-女 */
    String kv() default "";
 
    /** 匯出模板範例值（有值的話，直接取該值，不做對映） */
    String example() default "";
 
}