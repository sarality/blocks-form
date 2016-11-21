package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.sarality.form.FormField;
import com.sarality.form.value.ControlValueProvider;

import java.util.List;

/**
 * Reads data from an Drop Down Input Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DropDownBinding extends BaseViewBinding<Spinner> {

  private DropDownBinding(FormField field) {
    super(field);
  }

  @Override
  public void initBinding(Activity activity, BindingConfig<Spinner> config) {
    super.initBinding(activity, config);
    BindingSpec<Spinner> spec = getSpec();
    if (spec != null && spec.getRenderer() != null) {
      spec.getRenderer().render(activity, getView());
    }
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

    BindingSpec<Spinner> spec = getSpec();
    if (spec != null && spec.getValueProvider() != null) {
      ControlValueProvider valueProvider = spec.getValueProvider();
      String controlValue = valueProvider.getValue(viewId);
      if (controlValue != null) {
        return controlValue;
      }
      controlValue = valueProvider.getMappedValue(value);
      if (controlValue != null) {
        return controlValue;
      }
    }
    return value;
  }

  @Override
  public void setValue(String value) {
    Spinner spinner = getView();
    // TODO(abhideep): Get Index of Value and then set selection
    // spinner.setSelection(2);
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
