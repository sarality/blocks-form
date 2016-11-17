package com.sarality.form.render;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains data needed to render a Control.
 * <p/>
 * Also provides the mapping between the value in the UI and the values used in the data objects.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ControlDataSource<T> {

  private final List<String> dataList;
  private final Map<String, T> dataMap = new HashMap<>();

  private final Map<String, String> valueMap = new HashMap<>();
  private final Map<String, String> reverseValueMap = new HashMap<>();

  public ControlDataSource(List<String> dataList) {
    this.dataList = dataList;
  }

  public ControlDataSource<T> add(String label, String value, T data) {
    dataMap.put(label, data);
    valueMap.put(label, value);
    reverseValueMap.put(value, label);
    return this;
  }

  public List<String> getDataList() {
    return dataList;
  }
}
