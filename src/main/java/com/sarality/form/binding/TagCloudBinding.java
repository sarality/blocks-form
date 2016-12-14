package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.sarality.form.FormField;
import com.sarality.form.value.ControlValueProvider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Reads/Sets data in a tag cloud rendered as a collection of toggle buttons in a gridlayout
 *
 * @author satya@ (Satya Puniani)
 */
public class TagCloudBinding extends BaseViewBinding<ViewGroup> {

  private TagCloudBinding(FormField field) {
    super(field);
  }

  @Override
  public void initBinding(Activity activity, View contextView, BindingConfig<ViewGroup> config) {
    super.initBinding(activity, contextView, config);
    BindingSpec<ViewGroup> spec = config.getBindingSpec();
    ViewGroup viewGroup = getView();
    if (spec != null && spec.getValueProvider() != null) {
      ControlValueProvider valueProvider = spec.getValueProvider();
    }
  }

  @Override
  public boolean isMultiValueField() {
    return true;
  }

  @Override
  public String getValue() {
    return null;
  }

  @Override
  public void setValue(String value) {
    // No-op
  }

  @Override
  public List<String> getValueList() {
    List<String> valueList = new ArrayList<>();
    ViewGroup viewGroup = getView();
    int count = viewGroup.getChildCount();
    for (int i = 0; i < count; i++) {
      View childView = viewGroup.getChildAt(i);
      if (childView instanceof ToggleButton) {
        ToggleButton toggleButton = (ToggleButton) childView;
        if (toggleButton.isChecked()) {
          valueList.add(toggleButton.getText().toString());
        }
      }
    }
    return valueList;
  }

  @Override
  public void setValueList(List<String> textValueList) {
    Set<String> textValueSet = new HashSet<>();
    if (textValueList == null) {
      return;
    }
    textValueSet.addAll(textValueList);

    ViewGroup viewGroup = getView();

    int count = viewGroup.getChildCount();
    for (int i = 0; i < count; i++) {
      View childView = viewGroup.getChildAt(i);
      if (childView instanceof ToggleButton) {
        ToggleButton toggleButton = (ToggleButton) childView;
        String value = toggleButton.getText().toString();
        toggleButton.setChecked(textValueSet.contains(value));
      }
    }
  }

  public static class Factory implements ViewBindingFactory {

    @Override
    public ViewBinding createBinding(FormField field) {
      return new TagCloudBinding(field);
    }
  }
}
