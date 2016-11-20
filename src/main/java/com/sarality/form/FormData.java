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

  private final Map<String, String> fieldValueMap = new HashMap<>();

  public String getString(FormField field) {
    return getString(field.getName());
  }

  public String getString(String fieldName) {
    return getValue(fieldName);
  }

  public void setString(FormField field, String value) {
    addValue(field, value);
  }

  public Long getLong(FormField field) {
    return getLong(field.getName());
  }

  public Long getLong(String fieldName) {
    String value = getValue(fieldName);
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
    return getInt(field.getName());
  }

  public Integer getInt(String fieldName) {
    String value = getValue(fieldName);
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

  public Double getDouble(FormField field) { return getDouble(field.getName()); }

  public Double getDouble(String fieldName) {
    String value = getValue(fieldName);
    if (value == null) {
      return null;
    } else if (value.equals("")) {
      return null;
    }
    return Double.valueOf(value);
  }

  public void setDouble(FormField field, Double value) {
    if (value == null) {
      addValue(field, null);
    } else {
      addValue(field, String.valueOf(value));
    }
  }

  public <T extends Enum<T>> T getEnum(FormField field, Class<T> enumClass) {
    return getEnum(field.getName(), enumClass);
  }

  public <T extends Enum<T>> T getEnum(String fieldName, Class<T> enumClass) {
    String value = getValue(fieldName);
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

  void addValue(FormField field, String value) {
    fieldValueMap.put(field.getName(), value);
  }


  private String getValue(FormField field) {
    return getValue(field.getName());
  }

  private String getValue(String fieldName) {
    return fieldValueMap.get(fieldName);
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
