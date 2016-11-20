package com.sarality.form.render;

import android.view.View;

/**
 * Interface for classes that render an item in a control.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ItemRenderer<T> {

  int getItemViewId();

  void renderItemView(View view, T data);
}
