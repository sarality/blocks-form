package com.sarality.form.binding;

import android.app.Activity;
import android.widget.EditText;

import com.sarality.form.FormField;

/**
 * Reads data from an EditText Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class EditTextBinding implements ViewBinding {

  private final int viewId;
  private EditText editText;

  public EditTextBinding(int viewId) {
    this.viewId = viewId;
  }

  @Override
  public int getViewId() {
    return viewId;
  }

  @Override
  public void initBinding(Activity activity) {
    editText = (EditText) activity.findViewById(viewId);
  }

  @Override
  public String getValue() {
    return editText.getText().toString();
  }

  @Override
  public void setValue(String value) {
    editText.setText(value);
  }

  public static class Factory implements ViewBindingFactory {

    @Override
    public ViewBinding createBinding(FormField field) {
      return new EditTextBinding(field.getViewId());
    }
  }
}
