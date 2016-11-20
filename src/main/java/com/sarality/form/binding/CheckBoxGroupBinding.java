package com.sarality.form.binding;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

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
    BindingSpec<ViewGroup> spec = getSpec();
    if (spec != null && spec.getValueProvider() != null) {
      ControlValueProvider mapping = spec.getValueProvider();
      for (Integer viewId : mapping.getViewIds()) {
        CheckBox checkBox = (CheckBox) viewGroup.findViewById(viewId);
        if (checkBox.isChecked()) {
          valueList.add(mapping.getValue(viewId));
        }
      }
    } else {
      int count = viewGroup.getChildCount();
      for (int i = 0; i < count; i++) {
        View childView = viewGroup.getChildAt(i);
        if (childView instanceof CheckBox) {
          CheckBox checkBox = (CheckBox) childView;
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
    BindingSpec<ViewGroup> spec = getSpec();
    if (spec != null && spec.getValueProvider() != null) {
      ControlValueProvider mapping = spec.getValueProvider();
      for (Integer viewId : mapping.getViewIds()) {
        CheckBox checkBox = (CheckBox) viewGroup.findViewById(viewId);
        String value = mapping.getValue(viewId);
        checkBox.setChecked(textValueSet.contains(value));
      }
    } else {
      int count = viewGroup.getChildCount();
      for (int i = 0; i < count; i++) {
        View childView = viewGroup.getChildAt(i);
        if (childView instanceof CheckBox) {
          CheckBox checkBox = (CheckBox) childView;
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
