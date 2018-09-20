package com.sarality.form.reader;

import android.app.Activity;
import android.view.View;

import com.sarality.form.FormData;
import com.sarality.form.FormField;
import com.sarality.form.binding.BindingConfig;
import com.sarality.form.value.ControlValueProvider;

import java.util.List;

/**
 * Interface for all classes that read data from a Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ControlReader<V extends View> {

  FormField getField();

  int getViewId();

  void initReader(Activity activity, View contextView, BindingConfig<V> config);

  boolean isMultiValueField();

  boolean isCompoundValueField();

  String getValue();

  List<String> getValueList();

  List<FormData> getFormDataList(FormReaderFactory factory);

  void setValueProvider(ControlValueProvider valueProvider);
}
