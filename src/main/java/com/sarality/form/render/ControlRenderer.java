package com.sarality.form.render;

import android.app.Activity;

import com.sarality.form.value.ControlValueProvider;

/**
 * Interface for classes that render a UI control.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ControlRenderer<V> {

  void setValueProvider(ControlValueProvider valueProvider);

  String getFieldValue(String displayValue);

  String getDisplayValue(String fieldValue);

  void render(Activity activity, V view);
}
