package com.sarality.form;

import android.app.Activity;

import com.sarality.form.binding.BindingConfig;
import com.sarality.form.binding.ViewBinding;
import com.sarality.form.binding.ViewBindingFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A set of Binding for Field and Controls on a Form.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class FormBindings {

  private static final Logger logger = LoggerFactory.getLogger(FormBindings.class);

  private final Map<String, ViewBinding> viewBindingMap = new HashMap<>();
  private final Map<String, BindingConfig> bindingConfigMap = new HashMap<>();
  private final List<FormField> fieldList = new ArrayList<>();

  public FormBindings(List<FormField> formFieldList, List<BindingConfig> bindingConfigList) {
    fieldList.addAll(formFieldList);
    for (FormField field : fieldList) {
      ViewBindingFactory bindingFactory = field.getControlType().getBindingFactory();
      ViewBinding binding = bindingFactory.createBinding(field);
      viewBindingMap.put(field.getName(), binding);
    }
    if (bindingConfigList != null) {
      for (BindingConfig bindingConfig : bindingConfigList) {
        FormField field = bindingConfig.getField();
        bindingConfigMap.put(field.getName(), bindingConfig);
      }
    }
  }

  public FormBindings excludeFields(FormField... fields) {
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

  public ViewBinding getBinding(FormField field) {
    return viewBindingMap.get(field.getName());
  }

  @SuppressWarnings("unchecked")
  public void init(Activity activity) {
    for (String name : viewBindingMap.keySet()) {
      ViewBinding binding = viewBindingMap.get(name);
      BindingConfig config = bindingConfigMap.get(name);
      binding.initBinding(activity, config);
    }
  }

  public FormData readData() {
    FormData data = new FormData();
    for (ViewBinding binding: viewBindingMap.values()) {
      FormField field = binding.getField();
      String text = binding.getValue();
      logger.trace("Read value {} for field {}", text, field.getName());
      data.addValue(field, text);
    }
    return data;
  }

  public void populateForm(FormData data) {
    for (FormField field : fieldList) {
      ViewBinding binding = viewBindingMap.get(field.getName());
      String value = data.getString(field);
      logger.trace("Setting value {} for field {}", value, field.getName());
      binding.setValue(value);
    }
  }

  public List<FormField> getFieldList() {
    return fieldList;
  }
}
