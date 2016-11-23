package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;

import com.sarality.form.FormField;
import com.sarality.form.render.ControlRenderer;
import com.sarality.form.value.ControlValueProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base implementation for a binding between a Field and a UI Control for the field.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class BaseViewBinding<V extends View> implements ViewBinding<V> {

  public static final Logger logger = LoggerFactory.getLogger(BaseViewBinding.class);

  private final FormField field;
  private final int viewId;

  private Activity activity;
  private V view;

  private BindingSpec<V> spec;
  private ControlDataExtractor<V> extractor;
  private ControlRenderer<V> renderer;
  private ControlValueProvider valueProvider;

  public BaseViewBinding(FormField field) {
    this.field = field;
    this.viewId = field.getViewId();
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
      this.valueProvider = spec.getValueProvider();
      this.renderer = spec.getRenderer();
    }
    if (renderer == null) {
      this.renderer = getDefaultRenderer();
    }
    if (valueProvider != null) {
      valueProvider.init(activity);
      if (renderer != null) {
        renderer.setValueProvider(valueProvider);
      }
    }
  }

  @Override
  public FormField getField() {
    return field;
  }

  @Override
  public int getViewId() {
    return viewId;
  }


  @Override
  public void setValueProvider(ControlValueProvider valueProvider) {
    this.valueProvider = valueProvider;
    this.valueProvider.init(getActivity());
    if (renderer != null) {
      renderer.setValueProvider(this.valueProvider);
      renderer.render(getActivity(), getView());
    }
  }

  public Activity getActivity() {
    return activity;
  }

  protected ControlValueProvider getValueProvider() {
    return valueProvider;
  }

  ControlDataExtractor<V> getExtractor() {
    return extractor;
  }

  void setExtractor(ControlDataExtractor<V> extractor) {
    this.extractor = extractor;
  }

  ControlRenderer<V> getRenderer() {
    return renderer;
  }

  ControlRenderer<V> getDefaultRenderer() {
    return null;
  }
}
