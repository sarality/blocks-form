package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;

import com.sarality.form.FormField;
import com.sarality.form.render.ControlRenderer;
import com.sarality.form.render.DatePickerRenderer;
import com.sarality.form.value.ControlValueProvider;

import java.util.List;

/**
 * Reads date value from a Date Picker Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DatePickerBinding extends BaseViewBinding<EditText> {

  private DatePickerBinding(FormField field) {
    super(field);
  }

  @Override
  public void initBinding(Activity activity, View contextView, BindingConfig<EditText> config) {
    super.initBinding(activity, contextView, config);
    ControlValueProvider valueProvider = getValueProvider();
    if (valueProvider != null) {
      String defaultValue = valueProvider.getDefaultValue();
      if (defaultValue != null) {
        getView().setText(defaultValue);
      }
    }
    getRenderer().render(getActivity(), getView());
  }

  @Override
  ControlRenderer<EditText> getDefaultRenderer() {
    return new DatePickerRenderer(getViewId());
  }

  @Override
  public boolean isMultiValueField() {
    return false;
  }

  @Override
  public String getValue() {
    String value = getView().getText().toString();
    return getRenderer().getFieldValue(value);
  }

  @Override
  public void setValue(String value) {
    String displayValue = getRenderer().getDisplayValue(value);
    getView().setText(displayValue);
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
      return new DatePickerBinding(field);
    }
  }
}
