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
  private boolean isClickable;

  //TODO(@Satya) selector doesnt set the width/height correctly, does this need a style?
  public TagCloudRenderer(int selectorResourceId) {
    this(selectorResourceId, false);
  }

  public TagCloudRenderer(int selectorResourceId, boolean displayOnly) {
    buttonSelectorResourceId = selectorResourceId;
    isClickable = !displayOnly;
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

  //TODO(@Satya) render should take in a list of tags to toggle, and then do the following:
  //highlight the ones that exist
  //unselect the ones that dont
  //highlight in colorTagNotDefined any tags that arent in the base ControlValueProvider

  @Override
  public void render(Activity activity, GridLayout view) {
    List<String> tagList = valueProvider.getValueList();
    //generate all the tags
    for (String tagName : tagList) {
      renderTag(activity, view, tagName);
    }
  }

  private void renderTag(Activity activity, GridLayout view, String tagName) {

    ToggleButton toggleButton = new ToggleButton(activity);
    toggleButton.setBackgroundResource(buttonSelectorResourceId);
    toggleButton.setTextOn(tagName);
    toggleButton.setTextOff(tagName);
    toggleButton.setChecked(false);
    toggleButton.setClickable(isClickable);

    //TODO(@Satya) determine the column weight dynamically based on the size of the text
    int colWeight = 4;
    GridLayout.Spec rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1);
    GridLayout.Spec colSpec = GridLayout.spec(GridLayout.UNDEFINED, colWeight, (float) colWeight);
    GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, colSpec);
    params.setGravity(Gravity.FILL);
    params.width = 0;
    view.addView(toggleButton, params);
  }

}