package com.fivemin.mzpc.editor;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CustomLocalTimeEditor extends PropertyEditorSupport {

    private final DateTimeFormatter formatter;
    private final boolean allowEmpty;

    public CustomLocalTimeEditor(DateTimeFormatter formatter, boolean allowEmpty) {
        this.formatter = formatter;
        this.allowEmpty = allowEmpty;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (allowEmpty && StringUtils.isBlank(text)) {
            setValue(null);
        } else {
            setValue(LocalTime.parse(text, formatter));
        }
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        return (value != null ? this.formatter.format((LocalTime) value) : "");
    }
}
