package com.sarality.form.sanitize;

import android.app.Activity;

import com.sarality.form.FormData;
import com.sarality.form.FormField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A registery for a Set of Santizers to be applied on Form Fields
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class Sanitizers {

  private final Map<String, FormField> fieldMap = new HashMap<>();
  private final Map<String, Sanitizer> sanitizerMap = new HashMap<>();

  public Sanitizers register(FormField field, Sanitizer sanitizer) {
    fieldMap.put(field.getName(), field);
    sanitizerMap.put(field.getName(), sanitizer);
    return this;
  }

  public void sanitize(Activity activity, FormData formData) {
    for (String fieldName : sanitizerMap.keySet()) {
      Sanitizer sanitizer = sanitizerMap.get(fieldName);
      FormField field = fieldMap.get(fieldName);
      String value = formData.getValue(field);
      if (value != null) {
        String sanitizedValue = sanitizer.sanitize(value);
        formData.addValue(field, sanitizedValue);
      }
      List<String> valueList = formData.getValueList(field);
      if (valueList != null && valueList.size() > 0) {
        List<String> sanitizedValueList = new ArrayList<>();
        for (String valueListItem : valueList) {
          sanitizedValueList.add(sanitizer.sanitize(valueListItem));
        }
        formData.addValueList(field, sanitizedValueList);
      }
    }
  }
}

