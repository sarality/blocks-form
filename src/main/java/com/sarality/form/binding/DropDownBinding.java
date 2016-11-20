package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.sarality.form.FormField;
import com.sarality.form.render.DropDownRenderer;
import com.sarality.form.value.ControlValueProvider;

import java.util.ArrayList;
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
    } else {
      DropDownRenderer renderer = new DropDownRenderer();
      renderer.setValueProvider(new ControlValueProvider(new ArrayList<String>()));
      renderer.render(activity, getView());
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
    return ((TextView) selectedView).getText().toString();
  }

  @Override
  public void setValue(String value) {
    Spinner spinner = getView();
    spinner.setSelection(2);
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
