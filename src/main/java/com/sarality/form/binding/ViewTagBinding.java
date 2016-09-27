package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;

import com.sarality.form.FormField;

/**
 * Add description here
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ViewTagBinding implements ViewBinding {

  public static final String PARAM_TAG_RESOURCE_ID = "PARAM_TAG_RESOURCE_ID";

  private final int viewId;
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
    this.view = activity.findViewById(this.viewId);
    this.parameters = parameters;
  }

  @Override
  public String getValue() {
    if (parameters != null) {
      Integer tagResourceId = parameters.getInt(PARAM_TAG_RESOURCE_ID);
      if (tagResourceId != null) {
        return String.valueOf(view.getTag(tagResourceId));
      }
    }
    return String.valueOf(view.getTag());
  }

  @Override
  public void setValue(String textValue) {
    if (parameters != null) {
      Integer tagResourceId = parameters.getInt(PARAM_TAG_RESOURCE_ID);
      if (tagResourceId != null) {
        view.setTag(tagResourceId, textValue);
        return;
      }
    }
    view.setTag(textValue);
  }

  public static class Factory implements ViewBindingFactory {

    @Override
    public ViewBinding createBinding(FormField field) {
      return new ViewTagBinding(field.getViewId());
    }
  }
}
