package com.sarality.form.reader;

import android.widget.EditText;

import com.sarality.form.FormData;
import com.sarality.form.FormField;
import com.sarality.form.render.DatePickerRenderer;

import java.util.List;

/**
 * Reads date value from a Date Picker Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DatePickerReader extends BaseControlReader<EditText> {

  private DatePickerReader(FormField field) {
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
    String value = getView().getText().toString();
    return new DatePickerRenderer().getFieldValue(value);
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
      return new DatePickerReader(field);
    }
  }
}
