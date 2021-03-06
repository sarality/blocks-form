package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.sarality.form.FormField;
import com.sarality.form.value.ControlValueProvider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Reads data from a Group of CheckBox Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class CheckBoxGroupBinding extends BaseViewBinding<ViewGroup> {

  private CheckBoxGroupBinding(FormField field) {
    super(field);
  }

  @Override
  public void initBinding(Activity activity, View contextView, BindingConfig<ViewGroup> config) {
    super.initBinding(activity, contextView, config);
    BindingSpec<ViewGroup> spec = config.getBindingSpec();
    ViewGroup viewGroup = getView();
    if (spec != null && spec.getValueProvider() != null) {
      ControlValueProvider valueProvider = spec.getValueProvider();
      for (Integer viewId : valueProvider.getViewIds()) {
        ToggleButton checkBox = (ToggleButton) viewGroup.findViewById(viewId);
        checkBox.setChecked(valueProvider.getDefaultValue(viewId) != null);
        checkBox.setEnabled(valueProvider.isEnabled(viewId));
      }
    }
  }

  @Override
  public boolean isMultiValueField() {
    return true;
  }

  @Override
  public String getValue() {
    return null;
  }

  @Override
  public void setValue(String value) {
    // No-op
  }

  @Override
  public List<String> getValueList() {
    List<String> valueList = new ArrayList<>();
    ViewGroup viewGroup = getView();
    ControlValueProvider valueProvider = getValueProvider();
    if (valueProvider != null) {
      for (Integer viewId : valueProvider.getViewIds()) {
        ToggleButton checkBox = (ToggleButton) viewGroup.findViewById(viewId);
        if (checkBox.isChecked()) {
          String viewValue = valueProvider.getValue(viewId);
          if (viewValue != null) {
            valueList.add(viewValue);
            continue;
          }
          String textValue = checkBox.getText().toString();
          String mappedValue = valueProvider.getValue(textValue);
          if (mappedValue != null) {
            valueList.add(mappedValue);
          }
        }
      }
    } else {
      int count = viewGroup.getChildCount();
      for (int i = 0; i < count; i++) {
        View childView = viewGroup.getChildAt(i);
        if (childView instanceof ToggleButton) {
          ToggleButton checkBox = (ToggleButton) childView;
          if (checkBox.isChecked()) {
            valueList.add(checkBox.getText().toString());
          }
        }
      }
    }
    return valueList;
  }

  @Override
  public void setValueList(List<String> textValueList) {
    Set<String> textValueSet = new HashSet<>();
    textValueSet.addAll(textValueList);

    ViewGroup viewGroup = getView();

    ControlValueProvider valueProvider = getValueProvider();
    if (valueProvider != null) {
      for (Integer viewId : valueProvider.getViewIds()) {
        ToggleButton checkBox = (ToggleButton) viewGroup.findViewById(viewId);
        String value = valueProvider.getValue(viewId);
        checkBox.setChecked(value != null && textValueSet.contains(value));
        checkBox.setEnabled(valueProvider.isEnabled(viewId));
      }
    } else {
      int count = viewGroup.getChildCount();
      for (int i = 0; i < count; i++) {
        View childView = viewGroup.getChildAt(i);
        if (childView instanceof ToggleButton) {
          ToggleButton checkBox = (ToggleButton) childView;
          String value = checkBox.getText().toString();
          checkBox.setChecked(textValueSet.contains(value));
        }
      }
    }
  }

  public static class Factory implements ViewBindingFactory {

    @Override
    public ViewBinding createBinding(FormField field) {
      return new CheckBoxGroupBinding(field);
    }
  }
}
