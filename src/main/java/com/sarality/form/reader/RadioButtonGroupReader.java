package com.sarality.form.reader;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sarality.form.FormData;
import com.sarality.form.FormField;
import com.sarality.form.value.ControlValueProvider;

import java.util.List;

/**
 * Reads data from an Radio Button Group Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class RadioButtonGroupReader extends BaseControlReader<RadioGroup> {

  private RadioButtonGroupReader(FormField field) {
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
    RadioGroup radioGroup = getView();
    int viewId = radioGroup.getCheckedRadioButtonId();

    if (viewId == -1) {
      return null;
    }

    RadioButton radioButton = radioGroup.findViewById(viewId);
    String value = radioButton.getText().toString();

    ControlValueProvider valueProvider = getValueProvider();
    if (valueProvider != null ) {
      String controlValue = valueProvider.getValue(viewId);
      if (controlValue != null) {
        return controlValue;
      }
      String mappedValue = valueProvider.getMappedValue(value);
      if (mappedValue != null) {
        return mappedValue;
      }
    }
    return value;
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
      return new RadioButtonGroupReader(field);
    }
  }
}
