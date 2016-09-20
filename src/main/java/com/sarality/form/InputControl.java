package com.sarality.form;

import com.sarality.form.binding.CheckBoxBinding;
import com.sarality.form.binding.EditTextBinding;
import com.sarality.form.binding.ViewBindingFactory;
import com.sarality.form.binding.RadioButtonBinding;

/**
 * Enumeration for the Type of Field provided by the Android System.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public enum InputControl implements ControlType {
  CHECK_BOX(new CheckBoxBinding.Factory()),
  EDIT_TEXT(new EditTextBinding.Factory()),
  RADIO_BUTTON(new RadioButtonBinding.Factory());

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
