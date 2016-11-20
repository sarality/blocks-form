package com.sarality.form.binding;

import android.widget.EditText;

import com.sarality.form.FormField;

import java.util.List;

/**
 * Reads data from an EditText Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class EditTextBinding extends BaseViewBinding<EditText> {

  private EditTextBinding(FormField field) {
    super(field);
  }

  @Override
  public boolean isMultiValueField() {
    return false;
  }

  @Override
  public String getValue() {
    return getView().getText().toString();
  }

  @Override
  public void setValue(String value) {
    getView().setText(value);
  }

  @Override
  public List<String> getValueList() {
    return null;
  }

  @Override
  public void setValueList(List<String> textValueList) {
    // No-op
  }

  public static class Factory implements ViewBindingFactory {

    @Override
    public ViewBinding createBinding(FormField field) {
      return new EditTextBinding(field);
    }
  }
}
