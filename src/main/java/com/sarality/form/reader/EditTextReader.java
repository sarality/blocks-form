package com.sarality.form.reader;

import android.widget.EditText;

import com.sarality.form.FormData;
import com.sarality.form.FormField;

import java.util.List;

/**
 * Reads data from an EditText Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class EditTextReader extends BaseControlReader<EditText> {

  private EditTextReader(FormField field) {
    super(field);
  }

  @Override
  public boolean isCompoundValueField() {
    return false;
  }

  @Override
  public boolean isMultiValueField() {
    return false;
  }

  @Override
  public String getValue() {
    return getView().getText().toString();
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
      return new EditTextReader(field);
    }
  }
}
