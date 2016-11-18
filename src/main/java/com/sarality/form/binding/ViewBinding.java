package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;

import com.sarality.form.render.ControlDataSource;
import com.sarality.form.render.ControlRenderer;

import java.util.List;

/**
 * Interface for all classes that  get and set accessors on the
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ViewBinding<V extends View> {

  int getViewId();

  void initBinding(Activity activity, BindingConfig<V> config);

  String getValue();

  void setValue(String textValue);

  boolean isMultiValueField();

  List<String> getValueList();

  void setValueList(List<String> textValueList);

  ControlRenderer<V> getRenderer();
}
