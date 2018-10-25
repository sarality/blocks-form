package com.sarality.form.reader;

import android.widget.EditText;

import com.sarality.form.FormData;
import com.sarality.form.FormField;
import com.sarality.form.render.TimePickerRenderer;

import java.util.List;

/**
 * Reads date value from a Time Picker Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class TimePickerReader extends BaseControlReader<EditText> {

  private TimePickerReader(FormField field) {
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
    return new TimePickerRenderer().getFieldValue(value);
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
      return new TimePickerReader(field);
    }
  }
}
