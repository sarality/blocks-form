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

  private static final String DEFAULT_DATE4J_FORMAT = "DD/MM/YYYY";
  private static final String DEFAULT_JAVA_FORMAT = "dd/MM/yyyy";

  private final int calendarIconId;
  private final String dateFormat;
  private final String javaFormat;

  private ControlValueProvider valueProvider;

  public DatePickerRenderer(int calendarIconId, String dateFormat, String javaFormat) {
    this.calendarIconId = calendarIconId;
    this.dateFormat = dateFormat;
    this.javaFormat = javaFormat;
  }

  public DatePickerRenderer(int calendarIconId) {
    this(calendarIconId, DEFAULT_DATE4J_FORMAT, DEFAULT_JAVA_FORMAT);
  }

  @Override
  public void setValueProvider(ControlValueProvider valueProvider) {
    this.valueProvider = valueProvider;
  }

  @Override
  public void render(Activity activity, EditText view) {
    new ClickActions(activity).register(calendarIconId,
        new ShowDatePickerAction(activity, view, valueProvider)).init();
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

      DateTime date;
      if (dateString == null || dateString.trim().length() == 0) {
        date = DateTime.today(TimeZone.getDefault());
      } else {
        try {
          Date dateValue = new SimpleDateFormat(javaFormat, Locale.getDefault()).parse(dateString);
          date = DateTime.forInstant(dateValue.getTime(), TimeZone.getDefault());
        } catch (Exception e) {
          date = DateTime.today(TimeZone.getDefault());
        }
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
        textView.setText(date.format(dateFormat));
      }
      this.buttonPressed = 0;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int buttonPressed) {
      this.buttonPressed = buttonPressed;
    }
  }
}
