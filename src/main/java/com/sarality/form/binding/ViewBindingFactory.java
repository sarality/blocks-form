package com.sarality.form.binding;

import com.sarality.form.FormField;

/**
 * Interface for all class that create a binding for a View
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ViewBindingFactory {

  ViewBinding createBinding(FormField field);
}
