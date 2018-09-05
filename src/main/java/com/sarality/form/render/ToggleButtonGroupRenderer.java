package com.sarality.form.render;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.sarality.form.value.ControlValueProvider;

/**
 * Renderer for a group of Toggle Buttons
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ToggleButtonGroupRenderer implements ControlRenderer<ViewGroup> {
  private ControlValueProvider valueProvider;

  @Override
  public void setValueProvider(ControlValueProvider valueProvider) {
    this.valueProvider = valueProvider;
  }

  @Override
  public String getFieldValue(String displayValue) {
    return displayValue;
  }

  @Override
  public String getDisplayValue(String fieldValue) {
    return fieldValue;
  }

  @Override
  public void render(Activity activity, ViewGroup view) {
    for (Integer viewId : valueProvider.getViewIds()) {
      ToggleButton toggleButton = view.findViewById(viewId);
      toggleButton.setChecked(valueProvider.getDefaultValue(viewId) != null);
      toggleButton.setEnabled(valueProvider.isEnabled(viewId));
    }
  }
}
