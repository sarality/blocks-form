package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Base implementation of the Control Data Extractor.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class BaseControlDataExtractor<V extends View> implements ControlDataExtractor<V> {

  @Override
  public boolean isMultiValue() {
    return false;
  }

  @Override
  public List<String> extractValueList(Activity activity, V view) {
    List<String> list = new ArrayList<>();
    list.add(extractValue(activity, view));
    return list;
  }
}
