package com.sarality.form.render;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.sarality.action.ActionContext;
import com.sarality.action.ClickActions;
import com.sarality.action.ViewAction;
import com.sarality.form.FormData;
import com.sarality.form.value.ControlValueProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hirondelle.date4j.DateTime;

/**
 * Renders a Time Picker Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class TimePickerRenderer implements ControlRenderer<EditText> {

  public static final Logger logger = LoggerFactory.getLogger(TimePickerRenderer.class);
  private static final String DISPLAY_FORMAT = "hh:mm";

  private ControlValueProvider valueProvider;

  @Override
  public void setValueProvider(ControlValueProvider valueProvider) {
    this.valueProvider = valueProvider;
  }

  @Override
  public String getFieldValue(String displayValue) {
    DateTime date = parseDisplayValue(displayValue);
    if (date == null) {
      return null;
    }
    return date.format(FormData.TIME_FORMAT);
  }

  public String getDisplayValue(String fieldValue) {
    if (fieldValue == null) {
      return null;
    }
    DateTime dateTime = new DateTime(fieldValue);
    return dateTime.format(DISPLAY_FORMAT);
  }

  @Override
  public void render(Activity activity, EditText view) {
    if (valueProvider != null && !valueProvider.isEnabled()) {
      new ClickActions(activity)
          .register(view.getId(), null)
          .init();
      view.setEnabled(false);
    } else {
      ViewAction showTimePickAction = new ShowTimePickerAction(activity, view, valueProvider);
      new ClickActions(activity)
          .register(view.getId(), showTimePickAction)
          .init();
      view.setEnabled(true);
      view.setInputType(InputType.TYPE_NULL);
    }
  }

  private DateTime parseDisplayValue(String displayValue) {
    if (displayValue == null || displayValue.trim().length() == 0) {
      return null;
    }
    DateTime date;
    try {
      String[] values = displayValue.split(":");
      if (values.length == 3) {
        date = DateTime.forTimeOnly(
            Integer.valueOf(values[0]), Integer.valueOf(values[1]), Integer.valueOf(values[2]), 0);
      } else if (values.length == 2) {
        date = DateTime.forTimeOnly(
            Integer.valueOf(values[0]), Integer.valueOf(values[1]), 0, 0);
      } else if (values.length == 1) {
        date = DateTime.forTimeOnly(
            Integer.valueOf(values[0]), 0, 0, 0);
      } else {
        date = null;
      }
    } catch (Exception e) {
      logger.warn("Parsing failed for Date Display value {}", displayValue);
      date = null;
    }
    return date;
  }

  private class ShowTimePickerAction implements ViewAction {
    private final Activity activity;
    private final TextView textView;
    private final ControlValueProvider defaultValueProvider;

    private ShowTimePickerAction(Activity activity, TextView textView, ControlValueProvider defaultValueProvider) {
      this.activity = activity;
      this.textView = textView;
      this.defaultValueProvider = defaultValueProvider;
    }

    @Override
    public boolean perform(ActionContext actionContext) {
      String dateString = textView.getText().toString();
      if (dateString.trim().length() == 0) {
        // See if there is a default value if one is not defined
        if (defaultValueProvider != null) {
          dateString = defaultValueProvider.getDefaultValue();
        }
      }

      DateTime date = parseDisplayValue(dateString);
      if (date == null) {
        date = DateTime.forTimeOnly(8, 0, 0, 0);
      }

      TimePickerListener listener = new TimePickerListener(textView);
      TimePicker timePicker = new TimePicker(activity);
      timePicker.setIs24HourView(true);
      timePicker.setCurrentHour(date.getHour());
      timePicker.setCurrentMinute(date.getMinute());

      new AlertDialog.Builder(activity)
          .setPositiveButton("Set", listener)
          .setNegativeButton("Clear", listener)
          .setView(timePicker)
          .show();

      listener.setTimePicker(timePicker);
      return true;
    }
  }

  private class TimePickerListener implements TimePickerDialog.OnTimeSetListener, DialogInterface.OnClickListener {
    private final TextView textView;
    private int buttonPressed = 0;
    private TimePicker timePicker;

    private TimePickerListener(TextView textView) {
      this.textView = textView;
    }

    private void setTimePicker(TimePicker timePicker) {
      this.timePicker = timePicker;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
      if (buttonPressed == DialogInterface.BUTTON_NEGATIVE) {
        logger.debug("Time is being cleared");
        textView.setText("");
      } else if (buttonPressed == DialogInterface.BUTTON_POSITIVE) {
        logger.debug("Time is being set to hour {}, min {}", hour, minute);
        DateTime date = DateTime.forTimeOnly(hour, minute, 0, 0);
        textView.setText(date.format(DISPLAY_FORMAT));
      }
      this.buttonPressed = 0;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int buttonPressed) {
      this.buttonPressed = buttonPressed;

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        onTimeSet(timePicker, timePicker.getHour(), timePicker.getMinute());
      } else {
        onTimeSet(timePicker, timePicker.getCurrentHour(), timePicker.getCurrentMinute());
      }
    }
  }
}
