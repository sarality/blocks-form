package com.sarality.form;

import android.app.Activity;

import com.sarality.form.binding.BindingConfig;
import com.sarality.form.binding.BindingParameters;
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

  private final Map<Integer, ViewBinding> viewBindingMap = new HashMap<>();
  private final Map<Integer, BindingParameters> viewBindingParametersMap = new HashMap<>();
  private final List<FormField> fieldList = new ArrayList<>();
  private final Map<Integer, FormField> fieldMap = new HashMap<>();

  public FormBindings(List<FormField> formFieldList, List<BindingConfig> bindingConfigList) {
    fieldList.addAll(formFieldList);
    for (FormField field : fieldList) {
      ViewBindingFactory bindingFactory = field.getControlType().getBindingFactory();
      ViewBinding binding = bindingFactory.createBinding(field);
      viewBindingMap.put(field.getViewId(), binding);
      fieldMap.put(field.getViewId(), field);
    }
    if (bindingConfigList != null) {
      for (BindingConfig bindingConfig : bindingConfigList) {
        registerParameters(bindingConfig.getField(), bindingConfig.getParameters());
      }
    }
  }

  public FormBindings excludeFields(FormField... fields) {
    if (fields != null) {
      for (FormField field : fields) {
        fieldList.remove(field);
        int fieldId = field.getViewId();
        fieldMap.remove(fieldId);
        viewBindingMap.remove(fieldId);
        viewBindingParametersMap.remove(fieldId);
      }
    }
    return this;
  }

  public FormBindings registerParameters(FormField field, BindingParameters parameters) {
    viewBindingParametersMap.put(field.getViewId(), parameters);
    return this;
  }

  public void init(Activity activity) {
    for (Integer viewId : viewBindingMap.keySet()) {
      ViewBinding binding = viewBindingMap.get(viewId);
      BindingParameters parameters = viewBindingParametersMap.get(viewId);
      binding.initBinding(activity, parameters);
    }
  }

  public FormData readData() {
    FormData data = new FormData();
    for (ViewBinding binding: viewBindingMap.values()) {
      int fieldId = binding.getViewId();
      String text = binding.getValue();
      logger.trace("Read value {} for field {}", text, fieldMap.get(fieldId).getName());
      data.addValue(fieldId, text);
    }
    return data;
  }

  public void populateForm(FormData data) {
    for (FormField field : fieldList) {
      ViewBinding binding = viewBindingMap.get(field.getViewId());
      String value = data.getString(field);
      logger.trace("Setting value {} for field {}", value, field.getName());
      binding.setValue(value);
    }
  }

  public List<FormField> getFieldList() {
    return fieldList;
  }
}
