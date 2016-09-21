package com.sarality.form;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores and provides the value for fields in a Form
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class FormData {

  private final Map<Integer, String> fieldValueMap = new HashMap<>();

  public String getString(int fieldId) {
    return getValue(fieldId);
  }

  public void setString(FormField field, String value) {
    addValue(field.getViewId(), value);
  }

  public Long getLong(int fieldId) {
    String value = getValue(fieldId);
    if (value == null) {
      return null;
    }
    return Long.getLong(value);
  }

  public void setLong(FormField field, Long value) {
    if (value != null) {
      addValue(field.getViewId(), String.valueOf(value));
    } else {
      addValue(field.getViewId(), null);
    }
  }

  public Integer getInt(int fieldId) {
    String value = getValue(fieldId);
    if (value == null) {
      return null;
    }
    return Integer.getInteger(value);
  }

  public void setInt(FormField field, Integer value) {
    if (value != null) {
      addValue(field.getViewId(), String.valueOf(value));
    } else {
      addValue(field.getViewId(), null);
    }
  }

  public <T extends Enum<T>> T getEnum(int fieldId, Class<T> enumClass) {
    String value = getValue(fieldId);
    if (value == null) {
      return null;
    }
    return Enum.valueOf(enumClass, value);
  }

  public <T extends Enum<T>> void setEnum(FormField field, T value) {
    if (value != null) {
      addValue(field.getViewId(), value.name());
    } else {
      addValue(field.getViewId(), null);
    }
  }

  void addValue(int fieldId, String value) {
    fieldValueMap.put(fieldId, value);
  }

  private String getValue(int fieldId) {
    return fieldValueMap.get(fieldId);
  }
}
