package com.sarality.form.render;

import android.app.Activity;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.widget.ToggleButton;

import com.sarality.form.R;
import com.sarality.form.value.ControlValueProvider;

import java.util.List;

/**
 * Control Renderer for a Tag Cloud - which is a list of toggle buttons inside a GridLayout
 *
 * @author Satya@ (Satya Puniani) on 13/12/16
 */

public class TagCloudRenderer implements ControlRenderer<GridLayout> {
  private ControlValueProvider valueProvider;

  @Override
  public void setValueProvider(ControlValueProvider valueProvider) {
    this.valueProvider = valueProvider;
  }

  @Override public String getFieldValue(String displayValue) {
    return displayValue;
  }

  @Override public String getDisplayValue(String fieldValue) {
    return fieldValue;
  }

  @Override public void render(Activity activity, GridLayout view) {
    List<String> tagList = valueProvider.getValueList();
    //generate all the tags
    for (String tagName : tagList
        ) {
      TagRenderer tagRenderer = new TagRenderer(activity, view, tagName);
      tagRenderer.render();
    }

  }

  private class TagRenderer {
    private final ToggleButton toggleButton;
    private final Activity activity;
    private final GridLayout parentView;

    public TagRenderer(Activity activity, GridLayout parentView, String tagName) {
      this.activity = activity;
      this.parentView = parentView;
      toggleButton = new ToggleButton(activity);
      toggleButton.setBackgroundResource(R.drawable.selector_tags);
      toggleButton.setTextOn(tagName);
      toggleButton.setTextOff(tagName);
      toggleButton.setChecked(false);
    }

    public void render() {
      int colWeight = 3;
      GridLayout.Spec rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1);
      GridLayout.Spec colSpec = GridLayout.spec(GridLayout.UNDEFINED, colWeight, (float) colWeight);
      GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, colSpec);
      params.setGravity(Gravity.FILL_HORIZONTAL);
      params.width = 0;
      parentView.addView(toggleButton, params);
    }

  }

}
