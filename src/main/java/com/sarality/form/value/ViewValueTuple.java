package com.sarality.form.value;

/**
 * A Tuple for View, its associated value, the default value and whether it is active or not.
 *
 * @author abhideep@ (Abhideep Singh)
 */
class ViewValueTuple {
  private final int viewId;
  private final String value;
  private final String defaultValue;
  private final boolean isEnabled;

  ViewValueTuple(int viewId, String value, boolean isEnabled, String defaultValue) {
    this.viewId = viewId;
    this.value = value;
    this.defaultValue = defaultValue;
    this.isEnabled = isEnabled;
  }

  int getViewId() {
    return viewId;
  }

  String getValue() {
    return value;
  }

  String getDefaultValue() {
    return defaultValue;
  }

  boolean isEnabled() {
    return isEnabled;
  }
}
