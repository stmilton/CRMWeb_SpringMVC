/*
 * Created on : 2009/1/16
 */
package com.mdbs.web.editor;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ringo
 */
public class TimestampEditor extends PropertyEditorSupport {

    final Logger logger = LoggerFactory.getLogger(getClass());

    DateFormat dateFormat;

    public TimestampEditor(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setAsText(String text) throws IllegalArgumentException {
logger.debug("setAsText:" + dateFormat);
        try {
            setValue(new Timestamp(dateFormat.parse(text).getTime()));
        } catch (Exception e) {
            setValue(null);
        }
    }

    public String getAsText() {
logger.debug("getAsText:" + dateFormat);
         Timestamp value = (Timestamp) getValue();
logger.debug("getAsText:" + value);
logger.debug("getAsText:" + (value != null ? this.dateFormat.format(value) : ""));
         return (value != null ? this.dateFormat.format(value) : "");
    }
}
