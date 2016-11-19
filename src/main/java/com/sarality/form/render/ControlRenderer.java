package com.sarality.form.render;

import android.content.Context;

import com.sarality.form.value.ControlValueProvider;

/**
 * Interface for classes that render a UI control.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ControlRenderer<V> {

  void setValueProvider(ControlValueProvider valueProvider);

  void render(Context context, V view);
}
