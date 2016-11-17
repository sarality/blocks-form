package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Extracts the value of a Tag associated with a View.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ViewTagDataExtractor extends BaseControlDataExtractor<View> {

  public static final Logger logger = LoggerFactory.getLogger(ViewTagDataExtractor.class);

  private final Integer tagResourceId;

  public ViewTagDataExtractor(Integer tagResourceId) {
    this.tagResourceId = tagResourceId;
  }

  ViewTagDataExtractor() {
    this(null);
  }

  public Integer getTagResourceId() {
    return tagResourceId;
  }

  @Override
  public String extractValue(Activity context, View view) {
    String value;
    if (tagResourceId != null) {
      value = String.valueOf(view.getTag(tagResourceId));
    } else {
      value = String.valueOf(view.getTag());
    }
    return value;
  }
}
