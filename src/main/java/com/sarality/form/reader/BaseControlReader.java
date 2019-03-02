package com.sarality.form.reader;

import android.app.Activity;
import android.view.View;

import com.sarality.form.FormField;
import com.sarality.form.binding.BindingConfig;
import com.sarality.form.binding.BindingSpec;
import com.sarality.form.binding.ControlDataExtractor;
import com.sarality.form.value.ControlValueProvider;

/**
 * Base implementation for a binding between a Field and a UI Control for the field.
 *
 * @author abhideep@ (Abhideep Singh)
 */
abstract class BaseControlReader<V extends View> implements ControlReader<V> {

  private final FormField field;
  private final int viewId;

  private Activity activity;
  private View contextView;
  private V view;

  private ControlDataExtractor<V> extractor;
  private ControlValueProvider valueProvider;

  BaseControlReader(FormField field) {
    this.field = field;
    this.viewId = field.getViewId();
  }

  public V getView() {
    return view;
  }

  public View getContextView() {
    return contextView;
  }

  @Override @SuppressWarnings("unchecked")
  public void initReader(Activity activity, View contextView, BindingConfig<V> config) {
    this.activity = activity;
    this.contextView = contextView;
    if (contextView == null) {
      this.view = (V) this.activity.findViewById(viewId);
    } else {
      this.view = (V) this.contextView.findViewById(viewId);
    }


    if (config.getBindingSpec() != null) {
      BindingSpec<V> spec = config.getBindingSpec();
      this.valueProvider = spec.getValueProvider();
      this.extractor = spec.getExtractor();
    }
    if (valueProvider != null) {
      valueProvider.init(activity);
    }
    if (extractor == null) {
      this.extractor = getDefaultExtractor();
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
  }

  public Activity getActivity() {
    return activity;
  }

  protected ControlDataExtractor<V> getExtractor() {
    return extractor;
  }

  protected ControlDataExtractor<V> getDefaultExtractor() {
    return null;
  }

  protected ControlValueProvider getValueProvider() {
    return valueProvider;
  }
}
