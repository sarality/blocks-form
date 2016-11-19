package com.sarality.form.binding;

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

  private RadioButtonGroupBinding(int viewId) {
    super(viewId);
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

    BindingSpec<RadioGroup> spec = getSpec();
    RadioButton radioButton = (RadioButton) radioGroup.findViewById(viewId);
    if (spec != null && spec.getValueProvider() != null) {
      ControlValueProvider mapping = spec.getValueProvider();
      return mapping.getValue(viewId);
    } else {
      return radioButton.getText().toString();
    }
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
    BindingSpec<RadioGroup> spec = getSpec();
    RadioGroup radioGroup = getView();

    if (spec != null && spec.getValueProvider() != null) {
      ControlValueProvider mapping = spec.getValueProvider();
      int selectedButoonId = mapping.getViewId(value);
      for (Integer viewId : mapping.getViewIds()) {
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(viewId);
        if (viewId == selectedButoonId) {
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
      return new RadioButtonGroupBinding(field.getViewId());
    }
  }
}
