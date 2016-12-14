package com.sarality.form.render;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.view.ContextThemeWrapper;
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
  private int buttonSelectorResourceId;

  //TODO(@Satya) selector doesnt set the width/height correctly, does this need a style?
  public TagCloudRenderer(int selectorResourceId) {
    buttonSelectorResourceId = selectorResourceId;
  }

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
    private final Context context;
    private final GridLayout parentView;

    public TagRenderer(Context context, GridLayout parentView, String tagName) {
      this.context = context;
      this.parentView = parentView;
      toggleButton = new ToggleButton(context);
      toggleButton.setBackgroundResource(buttonSelectorResourceId);
      toggleButton.setTextOn(tagName);
      toggleButton.setTextOff(tagName);
      toggleButton.setChecked(false);

    }

    public void render() {
      //TODO(@Satya) determine the column weight dynamically based on the size of the text
      int colWeight = 4;
      GridLayout.Spec rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1);
      GridLayout.Spec colSpec = GridLayout.spec(GridLayout.UNDEFINED, colWeight, (float) colWeight);
      GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, colSpec);
      params.setGravity(Gravity.FILL_HORIZONTAL);
      params.width = 0;
      parentView.addView(toggleButton, params);
    }

  }

}
