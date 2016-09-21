package com.sarality.form;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic class that can be used to create a FormField definition.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class GenericField implements FormField {
  private final int viewId;
  private final List<Integer> childViewIdList = new ArrayList<>();
  private final ControlType controlType;

  public GenericField(int viewId, ControlType controlType) {
    this.viewId = viewId;
    this.controlType = controlType;
  }

  public GenericField(int viewId, List<Integer> childViewIdList, ControlType controlType) {
    this.viewId = viewId;
    this.childViewIdList.addAll(childViewIdList);
    this.controlType = controlType;
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
