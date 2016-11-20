package com.sarality.form.binding;

import android.widget.CheckBox;

import com.sarality.form.FormField;

import java.util.List;

/**
 * Reads data from an EditText Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class CheckBoxBinding extends BaseViewBinding<CheckBox> {

  private CheckBoxBinding(FormField field) {
    super(field);
  }

  @Override
  public boolean isMultiValueField() {
    return false;
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

  @Override
  public List<String> getValueList() {
    return null;
  }

  @Override
  public void setValueList(List<String> textValueList) {
    // No-Op
  }

  public static class Factory implements ViewBindingFactory {

    @Override
    public ViewBinding createBinding(FormField field) {
      return new CheckBoxBinding(field);
    }
  }
}
