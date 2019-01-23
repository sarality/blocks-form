package com.sarality.form.render;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.sarality.form.value.ControlValueProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Renders the DropDown Control.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DropDownRenderer implements ControlRenderer<Spinner> {

  public static final Logger logger = LoggerFactory.getLogger(DropDownRenderer.class);

  private final int itemViewId;
  private final int listViewId;
  private final ItemRenderer<String> itemRenderer;
  private ControlValueProvider valueProvider;

  private int initialPosition = -1;

  private DropDownRenderer(int itemViewId, int listViewId, ItemRenderer<String> itemRenderer) {
    this.itemViewId = itemViewId;
    this.listViewId = listViewId;
    this.itemRenderer = itemRenderer;
  }

  public DropDownRenderer() {
    this(android.R.layout.simple_spinner_item, android.R.layout.simple_spinner_dropdown_item, null);
  }

  public void setInitialPosition(int initialPosition) {
    this.initialPosition = initialPosition;
  }

  @Override
  public void setValueProvider(ControlValueProvider valueProvider) {
    this.valueProvider = valueProvider;
  }

  @Override
  public String getFieldValue(String displayValue) {
    return displayValue;
  }

  @Override
  public String getDisplayValue(String fieldValue) {
    return fieldValue;
  }

  @Override
  public void render(Activity activity, Spinner spinner) {
    if (itemRenderer == null && valueProvider != null) {
      ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, itemViewId, valueProvider.getValueList());
      adapter.setDropDownViewResource(listViewId);
      spinner.setAdapter(adapter);
      if (initialPosition >= 0) {
        spinner.setSelection(initialPosition, false);
      }
      adapter.notifyDataSetChanged();
    }
  }
}
