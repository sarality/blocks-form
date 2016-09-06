package com.sarality.form;

import com.sarality.form.reader.CheckBoxReader;
import com.sarality.form.reader.EditTextReader;
import com.sarality.form.reader.FieldReader;
import com.sarality.form.reader.FieldReaderFactory;
import com.sarality.form.reader.RadioButtonReader;

/**
 * Enumeration for the Type of Field provided by the Android System.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public enum InputControl implements FieldType {
  CHECK_BOX(new CheckBoxReader.Factory()),
  EDIT_TEXT(new EditTextReader.Factory()),
  RADIO_BUTTON(new RadioButtonReader.Factory());

  private final FieldReaderFactory factory;

  InputControl(FieldReaderFactory factory) {
    this.factory = factory;
  }

  @Override
  public String getName() {
    return name();
  }

  @Override
  public FieldReader createReader(int fieldId) {
    return factory.create(fieldId);
  }
}
