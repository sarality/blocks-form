package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;

import com.sarality.form.render.ControlDataSource;

/**
 * Interface for all classes that  get and set accessors on the
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ViewBinding<V extends View, T> {

  int getViewId();

  void initBinding(Activity activity, BindingConfig<V, T> config);

  ControlDataSource<T> getDataSource();

  void setDataSource(ControlDataSource<T> dataSource);

  String getValue();

  void setValue(String textValue);
}
