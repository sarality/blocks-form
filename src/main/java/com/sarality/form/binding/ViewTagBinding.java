package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;

import com.sarality.form.FormField;
import com.sarality.util.log.Resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Add description here
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ViewTagBinding implements ViewBinding {

  private static final Logger logger = LoggerFactory.getLogger(ViewTagBinding.class);

  public static final String PARAM_TAG_RESOURCE_ID = "PARAM_TAG_RESOURCE_ID";

  private final int viewId;
  private Activity activity;
  private View view;
  private BindingParameters parameters;

  public ViewTagBinding(int viewId) {
    this.viewId = viewId;
  }

  @Override
  public int getViewId() {
    return viewId;
  }

  @Override
  public void initBinding(Activity activity, BindingParameters parameters) {
    logger.trace("Initialing Binding for Tag on View with Id {}", Resources.name(activity, viewId));
    this.activity = activity;
    this.view = activity.findViewById(viewId);
    this.parameters = parameters;
  }

  @Override
  public String getValue() {
    if (parameters != null) {
      Integer tagResourceId = parameters.getInt(PARAM_TAG_RESOURCE_ID);
      logger.trace("Looking up Tag with Id {} on View with Id {}", Resources.name(activity, tagResourceId),
          Resources.name(activity, viewId));
      String tagValue = String.valueOf(view.getTag(tagResourceId));
      logger.trace("Tag with Id {} has value {} on View with Id {}", Resources.name(activity, tagResourceId),
          tagValue, Resources.name(activity, viewId));
      return tagValue;
    }
    return String.valueOf(view.getTag());
  }

  @Override
  public void setValue(String textValue) {
    // Tag is a read only binding. The value is not set via the Binding
  }

  public static class Factory implements ViewBindingFactory {

    @Override
    public ViewBinding createBinding(FormField field) {
      return new ViewTagBinding(field.getViewId());
    }
  }
}
