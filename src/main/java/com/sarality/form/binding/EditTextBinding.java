package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;

import com.sarality.form.FormField;
import com.sarality.form.value.ControlValueProvider;

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
  public void initBinding(Activity activity, View contextView, BindingConfig<EditText> config) {
    super.initBinding(activity, contextView, config);
    BindingSpec<EditText> spec = config.getBindingSpec();
    if (spec != null && spec.getValueProvider() != null) {
      ControlValueProvider valueProvider = spec.getValueProvider();
      String defaultValue = valueProvider.getDefaultValue();
      if (defaultValue != null) {
        getView().setText(defaultValue);
      }
    }
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
