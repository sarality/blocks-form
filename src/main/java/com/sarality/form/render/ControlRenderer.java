package com.sarality.form.render;

import android.content.Context;

/**
 * Interface for classes that render a UI control.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ControlRenderer<V> {

  void setDataSource(ControlDataSource dataSource);

  void render(Context context, V view);
}
