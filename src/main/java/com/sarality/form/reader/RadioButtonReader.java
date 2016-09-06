package com.sarality.form.reader;

import android.app.Activity;
import android.widget.RadioButton;

/**
 * Reads data from an EditText Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class RadioButtonReader implements FieldReader {

  private final int fieldId;
  private RadioButton radioButton;

  public RadioButtonReader(int fieldId) {
    this.fieldId = fieldId;
  }

  @Override
  public int getFieldId() {
    return fieldId;
  }

  @Override
  public void init(Activity activity) {
    radioButton = (RadioButton) activity.findViewById(fieldId);
  }

  @Override
  public String getText() {
    return radioButton.getText().toString();
  }

  public static class Factory implements FieldReaderFactory {

    @Override
    public FieldReader create(int viewId) {
      return new RadioButtonReader(viewId);
    }
  }
}
