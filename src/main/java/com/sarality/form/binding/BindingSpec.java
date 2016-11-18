package com.sarality.form.binding;

import android.view.View;

import com.sarality.form.render.ControlDataSource;
import com.sarality.form.render.ControlRenderer;

/**
 * Specification of a Field Binding that defines how data is extracted, displayed, and set.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class BindingSpec<V extends View> {

  private final ControlDataExtractor<V> extractor;
  private final ViewValueMapping viewValueMapping;
  private final ControlRenderer<V> renderer;
  private final ControlDataSource dataSource;

  public BindingSpec(ControlDataExtractor<V> extractor, ViewValueMapping viewValueMapping,
      ControlRenderer<V> renderer, ControlDataSource dataSource) {
    this.extractor = extractor;
    this.viewValueMapping = viewValueMapping;
    this.renderer = renderer;
    this.dataSource = dataSource;
  }

  public BindingSpec(ControlDataExtractor<V> extractor) {
    this(extractor, null, null, null);
  }

  public BindingSpec(ViewValueMapping viewValueMapping) {
    this(null, viewValueMapping, null, null);
  }

  public BindingSpec(ControlRenderer<V> renderer, ControlDataSource dataSource) {
    this(null, null, renderer, dataSource);
  }

  public ControlDataExtractor<V> getExtractor() {
    return extractor;
  }

  public ViewValueMapping getValueMapping() {
    return viewValueMapping;
  }

  public ControlRenderer<V> getRenderer() {
    return renderer;
  }

  public ControlDataSource getDataSource() {
    return dataSource;
  }
}
