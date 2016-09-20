package com.sarality.form;

import java.util.List;

/**
 * Interface for enumerations that define fields of a form.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface FormField {

  int getViewId();

  List<Integer> getChildViewIdList();

  ControlType getControlType();
}
