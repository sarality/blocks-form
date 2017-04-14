package com.sarality.form;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hirondelle.date4j.DateTime;

/**
 * Stores and provides the value for fields in a Form
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class FormData {

  public static final String DATE_FORMAT = "YYYY-MM-DD";

  private final Map<String, String> fieldValueMap = new HashMap<>();
  private final Map<String, List<String>> fieldValueListMap = new HashMap<>();

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
    if (TextUtils.isEmpty(value)) {
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
    if (TextUtils.isEmpty(value)) {
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
    if (TextUtils.isEmpty(value)) {
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

  public DateTime getDate(FormField field) {
    return getDate(field.getName());
  }

  public DateTime getDate(String fieldName) {
    String value = getValue(fieldName);
    if (TextUtils.isEmpty(value)) {
      return null;
    }
    return new DateTime(value);
  }

  public void setDate(FormField field, DateTime value) {
    if (value == null) {
      addValue(field, null);
    } else {
      addValue(field, value.format(DATE_FORMAT));
    }
  }



  public void setBoolean(FormField field, Boolean value) {
    addValue(field, value.toString());
  }

  public Boolean getBoolean(FormField field)  {
    return getValue(field) != null;
  }

  public <T extends Enum<T>> T getEnum(FormField field, Class<T> enumClass) {
    return getEnum(field.getName(), enumClass);
  }

  public <T extends Enum<T>> T getEnum(String fieldName, Class<T> enumClass) {
    String value = getValue(fieldName);
    if (TextUtils.isEmpty(value)) {
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

  public <T extends Enum<T>> List<T> getEnumList(FormField field, Class<T> enumClass) {
    List<String> valueList = getValueList(field.getName());
    if (valueList == null) {
      return null;
    }
    List<T> enumList = new ArrayList<>();
    for (String value : valueList) {
      enumList.add(Enum.valueOf(enumClass, value));
    }
    return enumList;
  }

  public <T extends Enum<T>> void setEnum(FormField field, List<T> enumList) {
    if (enumList != null) {
      List<String> valueList = new ArrayList<>();
      for (T enumValue : enumList) {
        valueList.add(enumValue.name());
      }
      addValueList(field, valueList);
    } else {
      addValueList(field, null);
    }
  }


  public void addValue(FormField field, String value) {
    fieldValueMap.put(field.getName(), value);
  }

  public void addValueList(FormField field, List<String> valueList) {
    fieldValueListMap.put(field.getName(), valueList);
  }

  public String getValue(FormField field) {
    return getValue(field.getName());
  }

  private String getValue(String fieldName) {
    return fieldValueMap.get(fieldName);
  }

  public List<String> getValueList(FormField field) {
    return getValueList(field.getName());
  }

  private List<String> getValueList(String fieldName) {
    return fieldValueListMap.get(fieldName);
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
