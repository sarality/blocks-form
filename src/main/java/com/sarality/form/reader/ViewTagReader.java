package com.sarality.form.reader;

import android.view.View;

import com.sarality.form.FormData;
import com.sarality.form.FormField;
import com.sarality.form.binding.ControlDataExtractor;
import com.sarality.form.binding.ViewTagDataExtractor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Reader data from a Tag on a View
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ViewTagReader extends BaseControlReader<View> {

  public static final Logger logger = LoggerFactory.getLogger(ViewTagReader.class);

  private ViewTagReader(FormField field) {
    super(field);
  }

  @Override
  public ControlDataExtractor<View> getDefaultExtractor() {
    return new ViewTagDataExtractor(null);
  }

  @Override
  public boolean isMultiValueField() {
    return false;
  }

  @Override
  public boolean isCompoundValueField() {
    return false;
  }

  @Override
  public String getValue() {
    return getExtractor().extractValue(getActivity(), getView());
  }

  @Override
  public List<String> getValueList() {
    return null;
  }

  @Override
  public List<FormData> getFormDataList(FormReaderFactory factory) {
    return null;
  }

  public static class Factory implements ControlReaderFactory {

    @Override
    public ControlReader createReader(FormField field) {
      return new ViewTagReader(field);
    }
  }
}
