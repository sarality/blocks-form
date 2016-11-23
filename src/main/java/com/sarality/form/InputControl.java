package com.sarality.form;

import com.sarality.form.binding.CheckBoxBinding;
import com.sarality.form.binding.CheckBoxGroupBinding;
import com.sarality.form.binding.DatePickerBinding;
import com.sarality.form.binding.DropDownBinding;
import com.sarality.form.binding.EditTextBinding;
import com.sarality.form.binding.RadioButtonGroupBinding;
import com.sarality.form.binding.ViewBindingFactory;
import com.sarality.form.binding.ViewTagBinding;

/**
 * Enumeration for the Type of Field provided by the Android System.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public enum InputControl implements ControlType {
  CHECK_BOX(new CheckBoxBinding.Factory()),
  CHECK_BOX_GROUP(new CheckBoxGroupBinding.Factory()),
  DATE_PICKER(new DatePickerBinding.Factory()),
  DROP_DOWN(new DropDownBinding.Factory()),
  EDIT_TEXT(new EditTextBinding.Factory()),
  RADIO_BUTTON_GROUP(new RadioButtonGroupBinding.Factory()),
  VIEW_TAG(new ViewTagBinding.Factory());

  private final ViewBindingFactory bindingFactory;

  InputControl(ViewBindingFactory bindingFactory) {
    this.bindingFactory = bindingFactory;
  }

  @Override
  public String getName() {
    return name();
  }

  @Override
  public ViewBindingFactory getBindingFactory() {
    return bindingFactory;
  }
}
