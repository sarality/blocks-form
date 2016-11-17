package com.sarality.form.binding;

import android.view.View;

import com.sarality.form.FormField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Binding for a field based on a Tag on a View
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ViewTagBinding<T> extends BaseViewBinding<View, T> {

  public static final Logger logger = LoggerFactory.getLogger(ViewTagBinding.class);

  private ViewTagBinding(int viewId) {
    super(viewId, null, new ViewTagDataExtractor(), null, null);
  }

  @Override
  public String getValue() {
    return getExtractor().extractValue(getActivity(), getView());
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
