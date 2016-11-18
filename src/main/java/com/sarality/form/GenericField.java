package com.sarality.form;

import java.util.List;

/**
 * A generic class that can be used to create a FormField definition.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class GenericField implements FormField {

  private final String name;
  private final int viewId;
  private final ControlType controlType;

  public GenericField(String name, int viewId, ControlType controlType) {
    this(name, viewId, controlType, null);
  }

  public GenericField(String name, int viewId, ControlType controlType, List<Integer> childViewIdList) {
    this.name = name;
    this.viewId = viewId;
    this.controlType = controlType;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getViewId() {
    return viewId;
  }

  @Override
  public ControlType getControlType() {
    return controlType;
  }
}
