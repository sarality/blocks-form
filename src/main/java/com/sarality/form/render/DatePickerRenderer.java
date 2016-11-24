package com.sarality.form.render;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.sarality.action.ActionContext;
import com.sarality.action.ClickActions;
import com.sarality.action.ViewAction;
import com.sarality.form.FormData;
import com.sarality.form.value.ControlValueProvider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import hirondelle.date4j.DateTime;

/**
 * Renders a Date Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DatePickerRenderer implements ControlRenderer<EditText> {

  private static final String DATE4J_DISPLAY_FORMAT = "DD/MM/YYYY";
  private static final String JAVA_DISPLAY_FORMAT = "dd/MM/yyyy";

  private final int calendarIconId;
  private final String displayFormat;
  private final String javaDisplayFormat;

  private ControlValueProvider valueProvider;

  public DatePickerRenderer(int calendarIconId, String displayFormat, String javaDisplayFormat) {
    this.calendarIconId = calendarIconId;
    this.displayFormat = displayFormat;
    this.javaDisplayFormat = javaDisplayFormat;
  }

  public DatePickerRenderer(int calendarIconId) {
    this(calendarIconId, DATE4J_DISPLAY_FORMAT, JAVA_DISPLAY_FORMAT);
  }

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
    return date.format(FormData.DATE_FORMAT);
  }

  @Override
  public void render(Activity activity, EditText view) {
    new ClickActions(activity).register(calendarIconId,
        new ShowDatePickerAction(activity, view, valueProvider)).init();
  }

  private DateTime parseDisplayValue(String displayValue) {
    if (displayValue == null || displayValue.trim().length() == 0) {
      return null;
    }
    DateTime date;
    try {
      Date dateValue = new SimpleDateFormat(javaDisplayFormat, Locale.getDefault()).parse(displayValue);
      date = DateTime.forInstant(dateValue.getTime(), TimeZone.getDefault());
    } catch (Exception e) {
      date = null;
    }
    return date;
  }

  private class ShowDatePickerAction implements ViewAction {
    private final Activity activity;
    private final TextView textView;
    private final ControlValueProvider defaultValueProvider;

    private ShowDatePickerAction(Activity activity, TextView textView, ControlValueProvider defaultValueProvider) {
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
        date = DateTime.today(TimeZone.getDefault());
      }

      DatePickerListener listener = new DatePickerListener(textView);
      DatePickerDialog dialog = new DatePickerDialog(activity, listener,
          date.getYear(), date.getMonth() - 1, date.getDay());
      dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Set", listener);
      dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Clear", listener);
      dialog.show();
      return true;
    }
  }

  private class DatePickerListener implements DatePickerDialog.OnDateSetListener, DialogInterface.OnClickListener {
    private final TextView textView;
    private int buttonPressed = 0;

    private DatePickerListener(TextView textView) {
      this.textView = textView;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
      if (buttonPressed == DialogInterface.BUTTON_NEGATIVE) {
        textView.setText("");
      } else {
        DateTime date = DateTime.forDateOnly(year, month + 1, day);
        textView.setText(date.format(displayFormat));
      }
      this.buttonPressed = 0;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int buttonPressed) {
      this.buttonPressed = buttonPressed;
    }
  }
}
