package com.sarality.form.value;

import android.app.Activity;
import android.util.SparseArray;
import android.util.SparseIntArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
  public static final Logger logger = LoggerFactory.getLogger(ControlValueProvider.class);

  private final List<String> valueList = new ArrayList<>();
  private final Set<String> valueSet = new HashSet<>();

  private boolean isEnabled = true;

  private String minValue = null;

  private String defaultValue;
  private FieldValue defaultFieldValue;
  private List<String> defaultValueList;

  private final Set<Integer> viewIdSet = new HashSet<>();
  private final List<ViewValueTuple> tupleList = new ArrayList<>();
  // Map between View Id and where it's Tuple is stored in the List of Tuples
  private final SparseIntArray viewTupleIndexArray = new SparseIntArray();

  // Lookup data structures
  private final SparseArray<ViewValueTuple> viewIdTupleMap = new SparseArray<>();
  private final Map<String, ViewValueTuple> valueTupleMap = new HashMap<>();

  private final Map<String, String> valueMap = new HashMap<>();
  private final Map<String, String> mappedValueMap = new HashMap<>();

  public ControlValueProvider(List<String> values) {
    if (values != null) {
      valueList.addAll(values);
    }
  }

  public ControlValueProvider setEnabled(boolean isEnabled) {
    this.isEnabled = isEnabled;
    return this;
  }

  public ControlValueProvider withMinValue(String value) {
    this.minValue = value;
    return this;
  }

  public ControlValueProvider withDefaultValue(String value) {
    this.defaultValue = value;
    return this;
  }

  public ControlValueProvider withDefaultValue(FieldValue value) {
    this.defaultFieldValue = value;
    return this;
  }

  public ControlValueProvider withDefaultValues(List<String> valueList) {
    this.defaultValueList = valueList;
    return this;
  }

  public ControlValueProvider withViewValue(Integer viewId, String value) {
    return withViewValue(viewId, value, true, null);
  }

  public ControlValueProvider withMappedValue(String value, String mappedValue) {
    logger.trace("Mapping {} to value {}", value, mappedValue);
    addValue(value);
    mappedValueMap.put(value, mappedValue);
    valueMap.put(mappedValue, value);
    return this;
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
    addValue(value);
    return this;
  }

  public void init(Activity activity) {
    if (defaultFieldValue != null) {
      defaultFieldValue.init(activity);
    }
  }

  public Set<Integer> getViewIds() {
    return viewIdSet;
  }

  public List<String> getValueList() {
    return valueList;
  }

  public String getMinValue() {
    return minValue;
  }

  public String getDefaultValue() {
    if (defaultValue == null && defaultFieldValue != null) {
      return defaultFieldValue.getValue();
    }
    return defaultValue;
  }

  public List<String> getDefaultValues() {
    return defaultValueList;
  }

  public Integer getViewId(String value) {
    ViewValueTuple tuple = valueTupleMap.get(value);
    if (tuple != null) {
      return tuple.getViewId();
    }
    return null;
  }

  public String getValue(Integer viewId) {
    ViewValueTuple tuple = viewIdTupleMap.get(viewId);
    if (tuple != null) {
      return tuple.getValue();
    }
    return null;
  }

  public String getValue(String mappedValue) {
    return valueMap.get(mappedValue);
  }

  public String getMappedValue(String value) {
    return mappedValueMap.get(value);
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  public boolean isEnabled(Integer viewId) {
    ViewValueTuple tuple = viewIdTupleMap.get(viewId);
    return tuple.isEnabled();
  }

  public String getDefaultValue(Integer viewId) {
    ViewValueTuple tuple = viewIdTupleMap.get(viewId);
    return tuple.getDefaultValue();
  }

  private void addValue(String value) {
    if (!valueSet.contains(value)) {
      valueSet.add(value);
      valueList.add(value);
    }
  }
}
