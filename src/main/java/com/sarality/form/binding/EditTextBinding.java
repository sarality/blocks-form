package com.sarality.form.binding;

import android.widget.EditText;

import com.sarality.form.FormField;

/**
 * Reads data from an EditText Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class EditTextBinding<T> extends BaseViewBinding<EditText, T> {

  private EditTextBinding(int viewId) {
    super(viewId, null, null, null, null);
  }

  @Override
  public String getValue() {
    return getView().getText().toString();
  }

  @Override
  public void setValue(String value) {
    getView().setText(value);
  }

  public static class Factory implements ViewBindingFactory {

    @Override
    public ViewBinding createBinding(FormField field) {
      return new EditTextBinding(field.getViewId());
    }
  }
}
