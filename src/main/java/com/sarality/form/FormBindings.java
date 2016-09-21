package com.sarality.form;

import android.app.Activity;

import com.sarality.form.binding.ViewBinding;
import com.sarality.form.binding.ViewBindingFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A set of Binding for Field and Controls on a Form.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class FormBindings {

  private final Map<Integer, ViewBinding> viewBindingMap = new HashMap<>();
  private final List<FormField> fieldList = new ArrayList<>();

  public FormBindings(FormField... fields) {
    fieldList.addAll(Arrays.asList(fields));
    for (FormField field : fieldList) {
      ViewBindingFactory bindingFactory = field.getControlType().getBindingFactory();
      ViewBinding binding = bindingFactory.createBinding(field);
      viewBindingMap.put(field.getViewId(), binding);
    }
  }

  public void init(Activity activity) {
    for (ViewBinding binding : viewBindingMap.values()) {
      binding.initBinding(activity);
    }
  }

  public FormData readData() {
    FormData data = new FormData();
    for (ViewBinding binding: viewBindingMap.values()) {
      int fieldId = binding.getViewId();
      String text = binding.getValue();
      data.addValue(fieldId, text);
    }
    return data;
  }

  public void populateForm(FormData data) {
    for (FormField field : fieldList) {
      ViewBinding binding = viewBindingMap.get(field.getViewId());
      String value = data.getString(field.getViewId());
      binding.setValue(value);
    }
  }
}
