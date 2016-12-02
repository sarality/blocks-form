package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sarality.form.FormField;
import com.sarality.form.value.ControlValueProvider;

import java.util.List;

/**
 * Reads data from an Radio Button Group Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class RadioButtonGroupBinding extends BaseViewBinding<RadioGroup> {

  private RadioButtonGroupBinding(FormField field) {
    super(field);
  }

  @Override
  public void initBinding(Activity activity, View contextView, BindingConfig<RadioGroup> config) {
    super.initBinding(activity, contextView, config);
    ControlValueProvider valueProvider = getValueProvider();
    if (valueProvider != null) {
      RadioGroup radioGroup = getView();
      String defaultValue = valueProvider.getDefaultValue();
      for (Integer viewId : valueProvider.getViewIds()) {
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(viewId);
        if (valueProvider.getValue(viewId).equals(defaultValue)) {
          radioButton.setChecked(true);
        } else {
          radioButton.setChecked(false);
        }
      }
    }
  }

  @Override
  public boolean isMultiValueField() {
    return false;
  }

  @Override
  public String getValue() {
    RadioGroup radioGroup = getView();
    int viewId = radioGroup.getCheckedRadioButtonId();

    if (viewId == -1) {
      return null;
    }

    RadioButton radioButton = (RadioButton) radioGroup.findViewById(viewId);
    String value = radioButton.getText().toString();

    ControlValueProvider valueProvider = getValueProvider();
    if (valueProvider != null ) {
      String controlValue = valueProvider.getValue(viewId);
      if (controlValue != null) {
        return controlValue;
      }
      String mappedValue = valueProvider.getMappedValue(value);
      if (mappedValue != null) {
        return mappedValue;
      }
    }
    return value;
  }

  @Override
  public List<String> getValueList() {
    return null;
  }

  @Override
  public void setValueList(List<String> textValueList) {
    // No-Op
  }

  @Override
  public void setValue(String value) {
    RadioGroup radioGroup = getView();

    ControlValueProvider valueProvider = getValueProvider();
    if (valueProvider != null) {
      Integer selectedButtonId = valueProvider.getViewId(value);
      for (Integer viewId : valueProvider.getViewIds()) {
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(viewId);
        if (selectedButtonId != null && selectedButtonId.equals(viewId)) {
          radioButton.setChecked(true);
        } else {
          radioButton.setChecked(false);
        }
      }
    } else {
      int count = radioGroup.getChildCount();
      for (int i = 0; i < count; i++) {
        View childView = radioGroup.getChildAt(i);
        if (childView instanceof RadioButton) {
          RadioButton radioButton = (RadioButton) childView;
          String text = radioButton.getText().toString();
          if (text.equals(value)) {
            radioButton.setChecked(true);
          } else {
            radioButton.setChecked(false);
          }
        }
      }
    }
  }

  public static class Factory implements ViewBindingFactory {

    @Override
    public ViewBinding createBinding(FormField field) {
      return new RadioButtonGroupBinding(field);
    }
  }
}
