package com.sarality.form.render;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.sarality.form.value.ControlValueProvider;

/**
 * Renders the DropDown Control.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DropDownRenderer<T> implements ControlRenderer<Spinner> {

  private final int itemViewId;
  private final int listViewId;
  private final ItemRenderer<String> itemRenderer;
  private ControlValueProvider valueProvider;

  private DropDownRenderer(int itemViewId, int listViewId, ItemRenderer<String> itemRenderer) {
    this.itemViewId = itemViewId;
    this.listViewId = listViewId;
    this.itemRenderer = itemRenderer;
  }

  public DropDownRenderer() {
    this(android.R.layout.simple_spinner_item, android.R.layout.simple_spinner_dropdown_item, null);
  }

  @Override
  public void setValueProvider(ControlValueProvider valueProvider) {
    this.valueProvider = valueProvider;
  }

  @Override
  public void render(Context context, Spinner spinner) {
    if (itemRenderer == null) {
      ArrayAdapter<String> adapter = new ArrayAdapter<>(context, itemViewId, valueProvider.getValueList());
      adapter.setDropDownViewResource(listViewId);
      spinner.setAdapter(adapter);
    }
  }
}
