package com.sarality.form.binding;

import android.util.SparseArray;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Mapping between Views and their associated value.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ViewValueMapping {
  private final Set<Integer> viewIdSet = new HashSet<>();
  private final SparseArray<String> viewValueMap = new SparseArray<>();
  private final Map<String, Integer> valueViewMap = new HashMap<>();

  public ViewValueMapping map(Integer viewId, String value) {
    viewIdSet.add(viewId);
    viewValueMap.put(viewId, value);
    valueViewMap.put(value, viewId);
    return this;
  }

  public Set<Integer> getViewIds() {
    return viewIdSet;
  }

  Integer getViewId(String value) {
    return valueViewMap.get(value);
  }

  String getValue(Integer viewId) {
    return viewValueMap.get(viewId);
  }
}
