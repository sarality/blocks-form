package com.sarality.form.binding;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * Interface for classes that extract data from a View.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ControlDataExtractor<V extends View> {

  /**
   * Extract single value from the Control's View
   *
   * @param view The View associated with the Control
   * @return String representation of the value for the Control.
   */
  String extractValue(Activity activity, V view);

  /**
   * @return {@code true} if ocntrol is multi-valued, {@code false otherwise}
   */
  boolean isMultiValue();

  /**
   * Extract list of values from multi-valued control.
   *
   * @param view The View associated with the Control
   * @return List of String representations of the values for the Control.
   */
  List<String> extractValueList(Activity activity, V view);
}
