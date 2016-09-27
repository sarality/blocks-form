package com.sarality.form.binding;

import android.app.Activity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sarality.form.FormField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Reads data from an EditText Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class RadioButtonBinding implements ViewBinding {

  private final int fieldId;
  private final List<Integer> childFieldIdList;
  private Map<Integer, RadioButton> radioButtonMap = new HashMap<>();

  public RadioButtonBinding(int fieldId, List<Integer> childFieldIdList) {
    this.fieldId = fieldId;
    this.childFieldIdList = childFieldIdList;
  }

  @Override
  public int getViewId() {
    return fieldId;
  }

  @Override
  public void initBinding(Activity activity, BindingParameters parameters) {
    RadioGroup radioGroup = (RadioGroup) activity.findViewById(fieldId);
    if (childFieldIdList != null) {
      for (Integer childFieldId : childFieldIdList) {
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(childFieldId);
        radioButtonMap.put(childFieldId, radioButton);
      }
    }
  }

  @Override
  public String getValue() {
    String value = null;
    for (RadioButton radioButton : radioButtonMap.values()) {
      if (radioButton.isChecked()) {
        value = radioButton.getText().toString();
        break;
      }
    }
    return value;
  }

  @Override
  public void setValue(String value) {
    for (RadioButton radioButton : radioButtonMap.values()) {
      if (value != null && value.equals(radioButton.getText())) {
        radioButton.setChecked(true);
      } else {
        radioButton.setChecked(false);
      }
    }
  }

  public static class Factory implements ViewBindingFactory {

    @Override
    public ViewBinding createBinding(FormField field) {
      return new RadioButtonBinding(field.getViewId(), field.getChildViewIdList());
    }
  }
}
