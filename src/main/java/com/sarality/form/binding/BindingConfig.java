package com.sarality.form.binding;

import com.sarality.form.FormField;

/**
 * Interface for all Configurations for View Binding.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface BindingConfig {

  FormField getField();

  BindingParameters getParameters();
}
