package com.sarality.form;

import android.app.Activity;
import android.view.View;

import com.sarality.form.binding.ViewBinding;
import com.sarality.form.binding.ViewBindingFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that reads data from a Form.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class FormDataReader {

  private final List<ViewBinding> bindingList = new ArrayList<>();

  public FormDataReader(FormField... fields) {
    List<FormField> fieldList = new ArrayList<>();
    fieldList.addAll(Arrays.asList(fields));
    for (FormField field : fieldList) {
      ViewBindingFactory bindingFactory = field.getControlType().getBindingFactory();
      ViewBinding binding = bindingFactory.createBinding(field);
      bindingList.add(binding);
    }
  }

  public void init(Activity activity, View contextView) {
    for (ViewBinding binding : bindingList) {
      binding.initBinding(activity, contextView, null);
    }
  }

  public void init(Activity activity) {
    init(activity, null);
  }

  public FormData readData() {
    FormData data = new FormData();
    for (ViewBinding binding : bindingList) {
      FormField field = binding.getField();
      String text = binding.getValue();
      data.addValue(field, text);
    }
    return data;
  }
}
