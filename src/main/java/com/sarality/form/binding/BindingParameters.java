package com.sarality.form.binding;

import java.util.HashMap;
import java.util.Map;

/**
 * A set of parameters passed to the ViewBinding
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class BindingParameters {

  private final Map<String, String> constantMap = new HashMap<>();

  public BindingParameters() {
  }

  public BindingParameters withInt(String parameterName, Integer parameterValue) {
    if (parameterValue == null) {
      constantMap.put(parameterName, null);
    } else {
      constantMap.put(parameterName, String.valueOf(parameterValue));
    }
    return this;
  }

  public String getString(String parameterName) {
    return constantMap.get(parameterName);
  }

  public Integer getInt(String parameterName) {
    String value = constantMap.get(parameterName);
    if (value == null) {
      return null;
    }
    return Integer.valueOf(value);
  }
}
