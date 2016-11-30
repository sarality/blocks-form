package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.sarality.form.FormField;
import com.sarality.form.render.ControlRenderer;
import com.sarality.form.render.DropDownRenderer;
import com.sarality.form.value.ControlValueProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Reads data from an Drop Down Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DropDownBinding extends BaseViewBinding<Spinner> {

  private static final Logger logger = LoggerFactory.getLogger(DropDownBinding.class);

  private DropDownBinding(FormField field) {
    super(field);
  }

  @Override
  public void initBinding(Activity activity, BindingConfig<Spinner> config) {
    super.initBinding(activity, config);
    getRenderer().render(activity, getView());
  }

  @Override
  ControlRenderer<Spinner> getDefaultRenderer() {
    return new DropDownRenderer();
  }

  @Override
  public boolean isMultiValueField() {
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
  public void setValue(String value) {
    Spinner spinner = getView();
    ControlValueProvider valueProvider = getValueProvider();
    if (value != null && valueProvider != null) {
      List<String> controlValueList = valueProvider.getValueList();
      int index = 0;
      for (String controlValue : controlValueList) {
        String mappedValue = valueProvider.getMappedValue(controlValue);
        logger.trace("Matching value {} against Control Value {} with mapped value {} ",
            value, controlValue, mappedValue);
        if (value.equals(mappedValue) || (mappedValue == null && value.equals(controlValue))) {
          spinner.setSelection(index);
          break;
        }
        index++;
      }
    }
  }

  @Override
  public List<String> getValueList() {
    return null;
  }

  @Override
  public void setValueList(List<String> textValueList) {
    // No - op
  }

  public static class Factory implements ViewBindingFactory {

    @Override
    public ViewBinding createBinding(FormField field) {
      return new DropDownBinding(field);
    }
  }
}
