package com.sarality.form;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic class that can be used to create a FormField definition.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class GenericField implements FormField {

  private final String name;
  private final int viewId;
  private final List<Integer> childViewIdList = new ArrayList<>();
  private final ControlType controlType;

  public GenericField(String name, int viewId, ControlType controlType) {
    this(name, viewId, controlType, null);
  }

  public GenericField(String name, int viewId, ControlType controlType, List<Integer> childViewIdList) {
    this.name = name;
    this.viewId = viewId;
    this.controlType = controlType;
    if (childViewIdList != null) {
      this.childViewIdList.addAll(childViewIdList);
    }
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
  public List<Integer> getChildViewIdList() {
    return childViewIdList;
  }

  @Override
  public ControlType getControlType() {
    return controlType;
  }
}
