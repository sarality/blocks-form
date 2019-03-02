package com.sarality.form.reader;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.sarality.form.FormData;
import com.sarality.form.FormField;
import com.sarality.form.value.ControlValueProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Reads data from an Drop Down Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DropDownReader extends BaseControlReader<Spinner> {

  private static final Logger logger = LoggerFactory.getLogger(DropDownReader.class);

  private DropDownReader(FormField field) {
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
    View selectedView = getView().getSelectedView();
    if (selectedView == null) {
      return null;
    }
    String value = ((TextView) selectedView).getText().toString();
    int viewId = selectedView.getId();

    ControlValueProvider valueProvider = getValueProvider();
    if (valueProvider != null) {
      String controlValue = valueProvider.getValue(viewId);
      if (controlValue != null) {
        logger.trace("Drop down selected View has value {}", controlValue);
        return controlValue;
      }
      String mappedValue = valueProvider.getMappedValue(value);
      if (mappedValue != null) {
        logger.trace("Drop down selected View has mapped value {}", mappedValue);
        return mappedValue;
      }
    }
    logger.trace("Drop down selected View has value {}", value);
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
      return new DropDownReader(field);
    }
  }
}
