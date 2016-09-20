package com.sarality.form;

/**
 * A generic class that can be used to create a FormField definition.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class GenericField implements FormField {
  private final int fieldId;
  private final ControlType fieldType;

  public GenericField(int fieldId, ControlType fieldType) {
    this.fieldId = fieldId;
    this.fieldType = fieldType;
  }

  @Override
  public int getViewId() {
    return fieldId;
  }

  @Override
  public ControlType getControlType() {
    return fieldType;
  }
}
