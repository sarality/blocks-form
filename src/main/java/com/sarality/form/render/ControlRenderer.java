package com.sarality.form.render;

import android.content.Context;

/**
 * Interface for classes that render a UI control.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ControlRenderer<V, T> {

  void setDataSource(ControlDataSource<T> dataSource);

  void render(Context context, V view);
}
