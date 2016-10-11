package com.sarality.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stores and provides the value for fields in a Form
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class FormData {

  private final Map<Integer, String> fieldValueMap = new HashMap<>();

  public String getString(FormField field) {
    return getString(field.getViewId());
  }

  public String getString(int fieldId) {
    return getValue(fieldId);
  }

  public void setString(FormField field, String value) {
    addValue(field, value);
  }

  public Long getLong(FormField field) {
    return getLong(field.getViewId());
  }

  public Long getLong(int fieldId) {
    String value = getValue(fieldId);
    if (value == null) {
      return null;
    }
    return Long.valueOf(value);
  }

  public void setLong(FormField field, Long value) {
    if (value != null) {
      addValue(field, String.valueOf(value));
    } else {
      addValue(field, null);
    }
  }

  public Integer getInt(FormField field) {
    return getInt(field.getViewId());
  }

  public Integer getInt(int fieldId) {
    String value = getValue(fieldId);
    if (value == null) {
      return null;
    }
    return Integer.valueOf(value);
  }

  public void setInt(FormField field, Integer value) {
    if (value != null) {
      addValue(field, String.valueOf(value));
    } else {
      addValue(field, null);
    }
  }

  public <T extends Enum<T>> T getEnum(FormField field, Class<T> enumClass) {
    return getEnum(field.getViewId(), enumClass);
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
      addValue(field, value.name());
    } else {
      addValue(field, null);
    }
  }

  private void addValue(FormField field, String value) {
    addValue(field.getViewId(), value);
  }

  void addValue(int fieldId, String value) {
    fieldValueMap.put(fieldId, value);
  }

  private String getValue(FormField field) {
    return getValue(field.getViewId());
  }

  private String getValue(int fieldId) {
    return fieldValueMap.get(fieldId);
  }

  public String displayString(List<FormField> fields) {
    StringBuilder builder = new StringBuilder();
    if (fields != null) {
      for (FormField field : fields) {
        builder.append(field.getName()).append(" (").append(field.getViewId()).append(") : ");
        builder.append(getValue(field)).append("\n");
      }
    }
    return builder.toString();
  }
}
