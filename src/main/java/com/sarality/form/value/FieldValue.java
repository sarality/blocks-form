package com.sarality.form.value;

import android.app.Activity;

/**
 * A value for a Field that is calculated or extracted from a View
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface FieldValue {

  void init(Activity activity);

  String getValue();
}
