package com.sarality.form.value;

import android.app.Activity;
import android.widget.EditText;

/**
 * Extracts the value of an Edit Text
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class EditTextValue implements FieldValue {

  private final int viewId;
  private EditText view;

  public EditTextValue(int viewId) {
    this.viewId = viewId;
  }

  @Override
  public void init(Activity activity) {
    this.view = (EditText) activity.findViewById(viewId);
  }

  @Override
  public String getValue() {
    if (view == null) {
      return "";
    }
    return view.getText().toString();
  }
}
