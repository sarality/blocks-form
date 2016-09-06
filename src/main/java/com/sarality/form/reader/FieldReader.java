package com.sarality.form.reader;

import android.app.Activity;

/**
 * Interface for all classes that read data from a FormField
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface FieldReader {

  int getFieldId();

  void init(Activity activity);

  String getText();
}
