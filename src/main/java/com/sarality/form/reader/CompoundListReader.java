package com.sarality.form.reader;

import android.view.View;
import android.view.ViewGroup;

import com.sarality.form.FormData;
import com.sarality.form.FormField;

import java.util.ArrayList;
import java.util.List;

/**
 * Reads data from a List of Compound Controls
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class CompoundListReader extends BaseControlReader<ViewGroup> {

  private CompoundListReader(FormField field) {
    super(field);
  }

  @Override
  public boolean isMultiValueField() {
    return true;
  }

  @Override
  public boolean isCompoundValueField() {
    return true;
  }

  @Override
  public String getValue() {
    return null;
  }

  @Override
  public List<FormData> getFormDataList(FormReaderFactory factory) {
    ViewGroup viewGroup = getView();
    int count = viewGroup.getChildCount();
    List<FormData> formDataList = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      View childView = viewGroup.getChildAt(i);
      FormReader bindings = factory.createFormReader();
      bindings.init(factory.getActivity(), childView);
      factory.setValueProviders(bindings);
      formDataList.add(bindings.readData());
    }
    return formDataList;
  }

  @Override
  public List<String> getValueList() {
    return null;
  }

  public static class Factory implements ControlReaderFactory {

    @Override
    public ControlReader createReader(FormField field) {
      return new CompoundListReader(field);
    }
  }
}
