package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;

import com.sarality.form.FormField;
import com.sarality.form.value.ControlValueProvider;

import java.util.List;

/**
 * Interface for all classes that  get and set accessors on the
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ViewBinding<V extends View> {

  FormField getField();

  int getViewId();

  void initBinding(Activity activity, View contextView, BindingConfig<V> config);

  String getValue();

  void setValue(String textValue);

  boolean isMultiValueField();

  List<String> getValueList();

  void setValueList(List<String> textValueList);

  void setValueProvider(ControlValueProvider valueProvider);
}
