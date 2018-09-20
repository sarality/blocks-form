package com.sarality.form.reader;

import android.app.Activity;

import com.sarality.form.FormField;
import com.sarality.form.binding.BindingConfig;
import com.sarality.form.value.ControlValueProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory that creates a Form Reader
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class FormReaderFactory {

  private final Activity activity;
  private final List<FormField> formFieldList;
  private final List<BindingConfig> bindingConfigList;
  private final Map<FormField, ControlValueProvider> valueProviderMap = new HashMap<>();

  public FormReaderFactory(Activity activity, List<FormField> formFieldList,
      List<BindingConfig> bindingConfigList) {
    this.activity = activity;
    this.formFieldList = formFieldList;
    this.bindingConfigList = bindingConfigList;
  }

  public FormReaderFactory register(FormField field, ControlValueProvider valueProvider) {
    valueProviderMap.put(field, valueProvider);
    return this;
  }

  public Activity getActivity() {
    return activity;
  }

  public FormReader createFormReader() {
    return new FormReader(formFieldList, bindingConfigList);
  }

  public void setValueProviders(FormReader reader) {
    for (FormField formField : valueProviderMap.keySet()) {
      ControlValueProvider valueProvider = valueProviderMap.get(formField);
      if (valueProvider != null) {
        reader.getReader(formField).setValueProvider(valueProvider);
      }
    }
  }
}
