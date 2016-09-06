package com.sarality.form.reader;

import android.app.Activity;
import android.widget.EditText;

/**
 * Reads data from an EditText Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class EditTextReader implements FieldReader {

  private final int fieldId;
  private EditText editText;

  public EditTextReader(int fieldId) {
    this.fieldId = fieldId;
  }

  @Override
  public int getFieldId() {
    return fieldId;
  }

  @Override
  public void init(Activity activity) {
    editText = (EditText) activity.findViewById(fieldId);
  }

  @Override
  public String getText() {
    return editText.getText().toString();
  }

  public static class Factory implements FieldReaderFactory {

    @Override
    public FieldReader create(int viewId) {
      return new EditTextReader(viewId);
    }
  }
}
