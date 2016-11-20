package com.sarality.form.binding;

import android.view.View;

import com.sarality.form.FormField;

/**
 * Interface for all Configurations for View Binding.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface BindingConfig<V extends View> {

  FormField getField();

  BindingSpec<V> getBindingSpec();
}
