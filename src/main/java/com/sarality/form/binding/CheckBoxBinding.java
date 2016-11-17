package com.sarality.form.binding;

import android.widget.CheckBox;

import com.sarality.form.FormField;

/**
 * Reads data from an EditText Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class CheckBoxBinding<T> extends BaseViewBinding<CheckBox, T> {

  private CheckBoxBinding(int viewId) {
    super(viewId, null, null, null, null);
  }

  @Override
  public String getValue() {
    CheckBox checkBox = getView();
    if (checkBox.isChecked()) {
      return checkBox.getText().toString();
    }
    return null;
  }

  @Override
  public void setValue(String value) {
    CheckBox checkBox = getView();
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
