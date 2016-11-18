package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;

import com.sarality.form.render.ControlRenderer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base implementation for a binding between a Field and a UI Control for the field.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class BaseViewBinding<V extends View> implements ViewBinding<V> {

  public static final Logger logger = LoggerFactory.getLogger(BaseViewBinding.class);

  private final int viewId;

  private Activity activity;
  private BindingSpec<V> spec;

  private V view;

  public BaseViewBinding(int viewId) {
    this.viewId = viewId;
  }

  public V getView() {
    return view;
  }

  @Override @SuppressWarnings("unchecked")
  public void initBinding(Activity activity, BindingConfig<V> config) {
    this.activity = activity;
    this.view = (V) this.activity.findViewById(viewId);

    if (config.getBindingSpec() != null) {
      this.spec = config.getBindingSpec();
    }
  }

  @Override
  public int getViewId() {
    return viewId;
  }


  @Override
  public ControlRenderer<V> getRenderer() {
    if (spec != null) {
      return spec.getRenderer();
    }
    return null;
  }

  public Activity getActivity() {
    return activity;
  }

  public BindingSpec<V> getSpec() {
    return spec;
  }
}
