package com.sarality.form.binding;

import android.view.View;

import com.sarality.form.render.ControlRenderer;
import com.sarality.form.value.ControlValueProvider;

/**
 * Specification of a Field Binding that defines how data is extracted, displayed, and set.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class BindingSpec<V extends View> {

  private final ControlDataExtractor<V> extractor;
  private final ControlValueProvider valueProvider;
  private final ControlRenderer<V> renderer;

  public BindingSpec(ControlDataExtractor<V> extractor, ControlValueProvider valueProvider,
      ControlRenderer<V> renderer) {
    this.extractor = extractor;
    this.valueProvider = valueProvider;
    this.renderer = renderer;
  }

  public BindingSpec(ControlDataExtractor<V> extractor) {
    this(extractor, null, null);
  }

  public BindingSpec(ControlValueProvider valueProvider) {
    this(null, valueProvider, null);
  }

  public BindingSpec(ControlRenderer<V> renderer) {
    this(null, null, renderer);
  }

  public ControlDataExtractor<V> getExtractor() {
    return extractor;
  }

  public ControlValueProvider getValueProvider() {
    return valueProvider;
  }

  public ControlRenderer<V> getRenderer() {
    return renderer;
  }
}
