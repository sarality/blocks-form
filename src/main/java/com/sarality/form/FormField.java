package com.sarality.form;

/**
 * Interface for enumerations that define fields of a form.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface FormField {

  String getName();

  int getViewId();

  ControlType getControlType();
}
