package com.sarality.form;

import com.sarality.form.binding.CheckBoxBinding;
import com.sarality.form.binding.CheckBoxGroupBinding;
import com.sarality.form.binding.DatePickerBinding;
import com.sarality.form.binding.DropDownBinding;
import com.sarality.form.binding.EditTextBinding;
import com.sarality.form.binding.RadioButtonGroupBinding;
import com.sarality.form.binding.TagCloudBinding;
import com.sarality.form.binding.TimePickerBinding;
import com.sarality.form.binding.ViewBindingFactory;
import com.sarality.form.binding.ViewTagBinding;
import com.sarality.form.reader.CompoundListReader;
import com.sarality.form.reader.ControlReaderFactory;
import com.sarality.form.reader.DropDownReader;
import com.sarality.form.reader.EditTextReader;
import com.sarality.form.reader.ViewTagReader;

/**
 * Enumeration for the Type of Field provided by the Android System.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public enum InputControl implements ControlType {
  CHECK_BOX(new CheckBoxBinding.Factory(), null),
  CHECK_BOX_GROUP(new CheckBoxGroupBinding.Factory(), null),
  DATE_PICKER(new DatePickerBinding.Factory(), null),
  DROP_DOWN(new DropDownBinding.Factory(), new DropDownReader.Factory()),
  EDIT_TEXT(new EditTextBinding.Factory(), new EditTextReader.Factory()),
  COMPOUND_LIST(null, new CompoundListReader.Factory()),
  RADIO_BUTTON_GROUP(new RadioButtonGroupBinding.Factory(), null),
  TIME_PICKER(new TimePickerBinding.Factory(), null),
  TAG_CLOUD(new TagCloudBinding.Factory(), null),
  VIEW_TAG(new ViewTagBinding.Factory(), new ViewTagReader.Factory());

  private final ViewBindingFactory bindingFactory;
  private final ControlReaderFactory readerFactory;

  InputControl(ViewBindingFactory bindingFactory, ControlReaderFactory readerFactory) {
    this.bindingFactory = bindingFactory;
    this.readerFactory = readerFactory;
  }

  @Override
  public String getName() {
    return name();
  }

  @Override
  public ViewBindingFactory getBindingFactory() {
    return bindingFactory;
  }

  @Override
  public ControlReaderFactory getReaderFactory() {
    return readerFactory;
  }
}
