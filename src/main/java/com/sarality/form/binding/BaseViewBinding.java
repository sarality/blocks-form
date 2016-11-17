package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;

import com.sarality.form.render.ControlDataSource;
import com.sarality.form.render.ControlRenderer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Add description here
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class BaseViewBinding<V extends View, T> implements ViewBinding<V, T> {

  public static final Logger logger = LoggerFactory.getLogger(BaseViewBinding.class);

  private final int viewId;
  private final List<Integer> childViewIdList;

  private Activity activity;
  private ControlDataExtractor<V> extractor;
  private ControlDataSource<T> dataSource;
  private ControlRenderer<V, T> renderer;

  private V view;
  private Map<Integer, View> childViewMap = new HashMap<>();

  public BaseViewBinding(int viewId, List<Integer> childViewIdList, ControlDataExtractor<V> extractor,
      ControlDataSource<T> dataSource, ControlRenderer<V, T> renderer) {
    this.viewId = viewId;
    this.childViewIdList = childViewIdList;
    this.extractor = extractor;
    this.dataSource = dataSource;
    this.renderer = renderer;
  }

  public V getView() {
    return view;
  }

  public List<Integer> getChildViewIdList() {
    return childViewIdList;
  }

  public View getChildView(Integer childViewId) {
    return childViewMap.get(childViewId);
  }

  @Override @SuppressWarnings("unchecked")
  public void initBinding(Activity activity, BindingConfig<V, T> config) {
    this.activity = activity;
    view = (V) this.activity.findViewById(viewId);
    if (childViewIdList != null) {
      for (Integer childFieldId : childViewIdList) {
        View childView = view.findViewById(childFieldId);
        childViewMap.put(childFieldId, childView);
      }
    }
    if (config.getExtractor() != null) {
      extractor = config.getExtractor();
    }
    if (config.getDataSource() != null) {
      dataSource = config.getDataSource();
    }
    if (config.getRenderer() != null) {
      renderer = config.getRenderer();
      if (config.getDataSource() != null) {
        renderer.setDataSource(config.getDataSource());
      }
    }
  }

  @Override
  public int getViewId() {
    return viewId;
  }

  public Activity getActivity() {
    return activity;
  }

  public ControlDataExtractor<V> getExtractor() {
    return extractor;
  }

  public ControlRenderer<V, T> getRenderer() {
    return renderer;
  }

  @Override
  public ControlDataSource<T> getDataSource() {
    return dataSource;
  }

  @Override
  public void setDataSource(ControlDataSource<T> dataSource) {
    this.dataSource = dataSource;
    if (renderer != null) {
      renderer.setDataSource(dataSource);
    }
  }
}
