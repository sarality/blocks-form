package com.sarality.form.binding;

import android.app.Activity;

/**
 * Interface for all classes that  get and set accessors on the
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ViewBinding {

  int getViewId();

  void initBinding(Activity activity, BindingParameters parameters);

   String getValue();

  void setValue(String textValue);
}
