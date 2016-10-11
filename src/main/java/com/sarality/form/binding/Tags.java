package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;

import com.sarality.form.FormField;
import com.sarality.util.log.Resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility to set Tags on a View
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class Tags {

  private static final Logger logger = LoggerFactory.getLogger(Tags.class);

  public static void setValue(Activity activity, FormField field, int tagResourceId, Object tagValue) {
    setValue(activity, field.getViewId(), tagResourceId, tagValue);
  }

  public static void setValue(Activity activity, int viewId, int tagResourceId, Object tagValue) {
    logger.trace("Setting Tag {} on View {} with value {}", Resources.name(activity, tagResourceId),
        Resources.name(activity, viewId), tagValue);
    View view = activity.findViewById(viewId);
    if (view != null && tagValue != null) {
      view.setTag(tagResourceId, tagValue);
    } else {
      if (view == null) {
        throw new IllegalArgumentException("View with Id " + Resources.name(activity, viewId) + " not found.");
      } else {
        throw new IllegalArgumentException("Tag with id " + Resources.name(activity, tagResourceId) + " on view " +
            Resources.name(activity, viewId) + " cannot be set to null");
      }
    }
  }
}
