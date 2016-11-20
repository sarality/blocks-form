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
  private final boolean isActive;

  ViewValueTuple(int viewId, String value, boolean isActive, String defaultValue) {
    this.viewId = viewId;
    this.value = value;
    this.defaultValue = defaultValue;
    this.isActive = isActive;
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

  boolean isActive() {
    return isActive;
  }
}
