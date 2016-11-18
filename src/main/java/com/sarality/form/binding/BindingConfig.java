package com.sarality.form.binding;

import android.view.View;

import com.sarality.form.FormField;
import com.sarality.form.render.ControlDataSource;
import com.sarality.form.render.ControlRenderer;

/**
 * Interface for all Configurations for View Binding.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface BindingConfig<V extends View> {

  FormField getField();

  BindingSpec<V> getBindingSpec();
}
