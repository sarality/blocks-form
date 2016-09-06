package com.sarality.form;

/**
 * A generic class that can be used to create a FormField definition.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class GenericField implements FormField {
  private final int fieldId;
  private final FieldType fieldType;

  public GenericField(int fieldId, FieldType fieldType) {
    this.fieldId = fieldId;
    this.fieldType = fieldType;
  }

  @Override
  public int getFieldId() {
    return fieldId;
  }

  @Override
  public FieldType getFieldType() {
    return fieldType;
  }
}
