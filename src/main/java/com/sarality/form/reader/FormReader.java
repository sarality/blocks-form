package com.sarality.form.reader;

import android.app.Activity;
import android.view.View;

import com.sarality.form.FormData;
import com.sarality.form.FormField;
import com.sarality.form.binding.BindingConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Reads a Form based on a List of Fields.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class FormReader {

  private static final Logger logger = LoggerFactory.getLogger(FormReader.class);

  private final Map<String, ControlReader> viewBindingMap = new HashMap<>();
  private final Map<String, BindingConfig> bindingConfigMap = new HashMap<>();
  private final List<FormField> fieldList = new ArrayList<>();
  private final Map<String, FormReaderFactory> factoryMap = new HashMap<>();

  public FormReader(List<FormField> formFieldList, List<BindingConfig> bindingConfigList) {
    fieldList.addAll(formFieldList);
    for (FormField field : fieldList) {
      ControlReaderFactory readerFactory = field.getControlType().getReaderFactory();
      ControlReader reader = readerFactory.createReader(field);
      viewBindingMap.put(field.getName(), reader);
    }
    if (bindingConfigList != null) {
      for (BindingConfig bindingConfig : bindingConfigList) {
        FormField field = bindingConfig.getField();
        bindingConfigMap.put(field.getName(), bindingConfig);
      }
    }
  }

  public FormReader excludeFields(FormField... fields) {
    if (fields != null) {
      for (FormField field : fields) {
        fieldList.remove(field);
        String fieldName = field.getName();
        viewBindingMap.remove(fieldName);
        bindingConfigMap.remove(fieldName);
      }
    }
    return this;
  }

  public void register(FormField field, FormReaderFactory factory) {
    factoryMap.put(field.getName(), factory);
  }

  public ControlReader getReader(FormField field) {
    return viewBindingMap.get(field.getName());
  }

  public void init(Activity activity) {
    init(activity, null);
  }

  @SuppressWarnings("unchecked")
  public void init(Activity activity, View contextView) {
    for (String name : viewBindingMap.keySet()) {
      ControlReader reader = viewBindingMap.get(name);
      BindingConfig config = bindingConfigMap.get(name);
      reader.initReader(activity, contextView, config);
    }
  }

  public FormData readData() {
    FormData data = new FormData();
    for (ControlReader<?> reader : viewBindingMap.values()) {
      FormField field = reader.getField();
      if (reader.isCompoundValueField() && reader.isMultiValueField()) {
        FormReaderFactory factory = factoryMap.get(field.getName());
        List<FormData> dataList = reader.getFormDataList(factory);
        data.addFormDataList(field, dataList);
      } else if (reader.isMultiValueField()) {
        List<String> textList = reader.getValueList();
        logger.trace("Read value List {} for field {}", textList, field.getName());
        data.addValueList(field, textList);
      } else {
        String text = reader.getValue();
        logger.trace("Read value {} for field {}", text, field.getName());
        data.addValue(field, text);
      }
    }
    return data;
  }

  public List<FormField> getFieldList() {
    return fieldList;
  }
}
