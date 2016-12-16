package com.sarality.form.render;

import android.app.Activity;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckedTextView;

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
    view.removeAllViews();
    view.invalidate();
    //generate all the tags
    for (String tagName : tagList) {
      renderTag(activity, view, tagName);
    }
  }

  private void renderTag(Activity activity, GridLayout view, String tagName) {

    final CheckedTextView toggle;
    toggle = new CheckedTextView(activity);
    toggle.setText(tagName);
    toggle.setGravity(Gravity.CENTER);
    toggle.setBackgroundResource(buttonSelectorResourceId);
    toggle.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        toggle.setChecked(!toggle.isChecked());
      }
    });

    int colSpan;

    int tagLength = tagName.length();
    if (tagLength >12) {
      colSpan = 6;
    } else if (tagLength >9) {
      colSpan = 4;
    } else {
      colSpan = 3;
    }

    GridLayout.Spec rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1);
    GridLayout.Spec colSpec = GridLayout.spec(GridLayout.UNDEFINED, colSpan);
    GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, colSpec);
    params.setGravity(Gravity.LEFT);
    view.addView(toggle, params);

  }

}