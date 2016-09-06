package com.sarality.form.reader;

import android.app.Activity;
import android.widget.CheckBox;

/**
 * Reads data from an EditText Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class CheckBoxReader implements FieldReader {

  private final int fieldId;
  private CheckBox checkBox;

  public CheckBoxReader(int fieldId) {
    this.fieldId = fieldId;
  }

  @Override
  public int getFieldId() {
    return fieldId;
  }

  @Override
  public void init(Activity activity) {
    checkBox = (CheckBox) activity.findViewById(fieldId);
  }

  @Override
  public String getText() {
    return checkBox.getText().toString();
  }

  public static class Factory implements FieldReaderFactory {

    @Override
    public FieldReader create(int viewId) {
      return new CheckBoxReader(viewId);
    }
  }

}
