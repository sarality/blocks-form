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

  public Long getLong(int fieldId) {
    String value = getValue(fieldId);
    if (value == null) {
      return null;
    }
    return Long.getLong(value);
  }

  public Integer getInt(int fieldId) {
    String value = getValue(fieldId);
    if (value == null) {
      return null;
    }
    return Integer.getInteger(value);
  }

  public <T extends Enum<T>> T getEnum(int fieldId, Class<T> enumClass) {
    String value = getValue(fieldId);
    if (value == null) {
      return null;
    }
    return Enum.valueOf(enumClass, value);
  }

  void addValue(int fieldId, String value) {
    fieldValueMap.put(fieldId, value);
  }

  private String getValue(int fieldId) {
    return fieldValueMap.get(fieldId);
  }
}
