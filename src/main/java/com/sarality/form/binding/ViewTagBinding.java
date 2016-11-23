package com.sarality.form.binding;

import android.app.Activity;
import android.view.View;

import com.sarality.form.FormField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Binding for a field based on a Tag on a View
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ViewTagBinding extends BaseViewBinding<View> {

  public static final Logger logger = LoggerFactory.getLogger(ViewTagBinding.class);

  private ViewTagBinding(FormField field) {
    super(field);
  }

  @Override
  public void initBinding(Activity activity, BindingConfig<View> config) {
    super.initBinding(activity, config);
    if (getExtractor() == null) {
      setExtractor(new ViewTagDataExtractor());
    }
  }

  @Override
  public boolean isMultiValueField() {
    return false;
  }

  @Override public List<String> getValueList() {
    return null;
  }

  @Override
  public void setValueList(List<String> textValueList) {
    // Tag is a read only binding. The value is not set via the Binding
  }

  @Override
  public String getValue() {
    return getExtractor().extractValue(getActivity(), getView());
  }

  @Override
  public void setValue(String textValue) {
    // Tag is a read only binding. The value is not set via the Binding
  }

  public static class Factory implements ViewBindingFactory {

    @Override
    public ViewBinding createBinding(FormField field) {
      return new ViewTagBinding(field);
    }
  }
}
