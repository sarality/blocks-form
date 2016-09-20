package com.sarality.form.binding;

import android.app.Activity;
import android.widget.CheckBox;

import com.sarality.form.FormField;

/**
 * Reads data from an EditText Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class CheckBoxBinding implements ViewBinding {

  private final int viewId;
  private CheckBox checkBox;

  public CheckBoxBinding(int viewId) {
    this.viewId = viewId;
  }

  @Override
  public int getViewId() {
    return viewId;
  }

  @Override
  public void initBinding(Activity activity) {
    checkBox = (CheckBox) activity.findViewById(viewId);
  }

  @Override
  public String getValue() {
    if (checkBox.isChecked()) {
      return checkBox.getText().toString();
    }
    return null;
  }

  @Override
  public void setValue(String value) {
    boolean isChecked = value != null &&
        (value.equals(Boolean.TRUE.toString()) || value.equals(checkBox.getText()));
    checkBox.setChecked(isChecked);
  }

  public static class Factory implements ViewBindingFactory {

    @Override
    public ViewBinding createBinding(FormField field) {
      return new CheckBoxBinding(field.getViewId());
    }
  }

}
