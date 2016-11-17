package com.sarality.form.binding;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sarality.form.FormField;

import java.util.List;

/**
 * Reads data from an EditText Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class RadioButtonBinding<T> extends BaseViewBinding<RadioGroup, T> {

  private RadioButtonBinding(int viewId, List<Integer> childFieldIdList) {
    super(viewId, childFieldIdList, null, null, null);
  }

  @Override
  public String getValue() {
    String value = null;
    List<Integer> childViewIdList = getChildViewIdList();
    if (childViewIdList != null) {
      for (Integer childViewId : childViewIdList) {
        RadioButton radioButton = (RadioButton) getChildView(childViewId);
        if (radioButton.isChecked()) {
          value = radioButton.getText().toString();
          break;
        }
      }
    }
    return value;
  }

  @Override
  public void setValue(String value) {
    List<Integer> childViewIdList = getChildViewIdList();
    if (childViewIdList != null) {
      for (Integer childViewId : childViewIdList) {
        RadioButton radioButton = (RadioButton) getChildView(childViewId);
        if (value != null && value.equals(radioButton.getText())) {
          radioButton.setChecked(true);
        } else {
          radioButton.setChecked(false);
        }
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
