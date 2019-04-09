package com.sarality.form.reader;

import android.widget.CheckBox;

import com.sarality.form.FormData;
import com.sarality.form.FormField;

import java.util.List;

/**
 * Reads data from a Checkbox Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class CheckBoxReader extends BaseControlReader<CheckBox> {

  private CheckBoxReader(FormField field) {
    super(field);
  }

  @Override
  public boolean isMultiValueField() {
    return false;
  }

  @Override
  public boolean isCompoundValueField() {
    return false;
  }

  @Override
  public String getValue() {
    CheckBox checkBox = getView();
    if (checkBox.isChecked()) {
      return checkBox.getText().toString();
    }
    return null;
  }

  @Override
  public List<String> getValueList() {
    return null;
  }

  @Override
  public List<FormData> getFormDataList(FormReaderFactory factory) {
    return null;
  }

  public static class Factory implements ControlReaderFactory {

    @Override
    public ControlReader createReader(FormField field) {
      return new CheckBoxReader(field);
    }
  }
}
