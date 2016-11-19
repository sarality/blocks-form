package com.sarality.form.value;

import android.util.SparseArray;
import android.util.SparseIntArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Provides the display and field Values for a given control along with other information like default values and
 * whether the specific view in the control is active.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ControlValueProvider {
  private final List<String> valueList = new ArrayList<>();
  private final Set<String> valueSet = new HashSet<>();

  private final Set<Integer> viewIdSet = new HashSet<>();
  private final List<ViewValueTuple> tupleList = new ArrayList<>();
  // Map between View Id and where it's Tuple is stored in the List of Tuples
  private final SparseIntArray viewTupleIndexArray = new SparseIntArray();

  // Lookup data structures
  private final SparseArray<ViewValueTuple> viewIdTupleMap = new SparseArray<>();
  private final Map<String, ViewValueTuple> valueTupleMap = new HashMap<>();


  public ControlValueProvider(List<String> values) {
    if (values != null) {
      valueList.addAll(values);
    }
  }

  public ControlValueProvider withViewValue(Integer viewId, String value) {
    return withViewValue(viewId, value, true, null);
  }

  public ControlValueProvider withViewValue(Integer viewId, String value, boolean isActive, String defaultValue) {
    int index = viewTupleIndexArray.indexOfKey(viewId);
    if (index < 0) {
      tupleList.add(new ViewValueTuple(viewId, value, isActive, defaultValue));
      index = tupleList.size() - 1;
      viewTupleIndexArray.put(viewId, index);
    }
    ViewValueTuple tuple = tupleList.get(index);
    valueTupleMap.put(value, tuple);
    viewIdTupleMap.put(viewId, tuple);

    viewIdSet.add(viewId);
    if (!valueSet.contains(value)) {
      valueSet.add(value);
      valueList.add(value);
    }
    return this;
  }

  public Set<Integer> getViewIds() {
    return viewIdSet;
  }

  public List<String> getValueList() {
    return valueList;
  }

  public Integer getViewId(String value) {
    ViewValueTuple tuple = valueTupleMap.get(value);
    return tuple.viewId;
  }

  public String getValue(Integer viewId) {
    ViewValueTuple tuple = viewIdTupleMap.get(viewId);
    return tuple.value;
  }

  public boolean isActive(Integer viewId) {
    ViewValueTuple tuple = viewIdTupleMap.get(viewId);
    return tuple.isActive;
  }

  public String getDefaultValue(Integer viewId) {
    ViewValueTuple tuple = viewIdTupleMap.get(viewId);
    return tuple.defaultValue;
  }

  private class ViewValueTuple {
    private final int viewId;
    private final String value;
    private final String defaultValue;
    private final boolean isActive;

    private ViewValueTuple(int viewId, String value, boolean isActive, String defaultValue) {
      this.viewId = viewId;
      this.value = value;
      this.defaultValue = defaultValue;
      this.isActive = isActive;
    }
  }
}
