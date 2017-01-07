package com.sarality.form.render;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.text.InputType;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.sarality.action.ActionContext;
import com.sarality.action.ClickActions;
import com.sarality.action.ViewAction;
import com.sarality.form.FormData;
import com.sarality.form.value.ControlValueProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
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

  public static final Logger logger = LoggerFactory.getLogger(DatePickerRenderer.class);

  private static final String DATE4J_DISPLAY_FORMAT = "DD/MM/YYYY";
  private static final String JAVA_DISPLAY_FORMAT = "dd/MM/yyyy";

  private final String displayFormat;
  private final String javaDisplayFormat;

  private ControlValueProvider valueProvider;

  private DatePickerRenderer(String displayFormat, String javaDisplayFormat) {
    this.displayFormat = displayFormat;
    this.javaDisplayFormat = javaDisplayFormat;
  }

  public DatePickerRenderer() {
    this(DATE4J_DISPLAY_FORMAT, JAVA_DISPLAY_FORMAT);
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

  public String getDisplayValue(String fieldValue) {
    if (fieldValue == null) {
      return null;
    }
    DateTime dateTime = new DateTime(fieldValue);
    DateFormat format = new SimpleDateFormat(javaDisplayFormat, Locale.getDefault());
    return format.format(new Date(dateTime.getMilliseconds(TimeZone.getDefault())));
  }

  @Override
  public void render(Activity activity, EditText view) {
    if (valueProvider != null && !valueProvider.isEnabled()) {
      new ClickActions(activity)
          .register(view.getId(), null)
          .init();
      view.setEnabled(false);
    } else {
      ViewAction showDatePickAction = new ShowDatePickerAction(activity, view, valueProvider);
      new ClickActions(activity)
          .register(view.getId(), showDatePickAction)
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
      Date dateValue = new SimpleDateFormat(javaDisplayFormat, Locale.getDefault()).parse(displayValue);
      date = DateTime.forInstant(dateValue.getTime(), TimeZone.getDefault());
    } catch (Exception e) {
      logger.warn("Parsing failed for Date Display value {}", displayValue);
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
      listener.setDialog(dialog);
      dialog.show();
      return true;
    }
  }

  private class DatePickerListener implements DatePickerDialog.OnDateSetListener, DialogInterface.OnClickListener {
    private final TextView textView;
    private int buttonPressed = 0;
    private DatePickerDialog dialog;

    private DatePickerListener(TextView textView) {
      this.textView = textView;
    }

    private void setDialog(DatePickerDialog dialog) {
      this.dialog = dialog;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
      if (buttonPressed == DialogInterface.BUTTON_NEGATIVE) {
        logger.debug("Date is being cleared");
        textView.setText("");
      } else if (buttonPressed == DialogInterface.BUTTON_POSITIVE) {
        logger.debug("Date is being set to year {}, month {}, day {}", year, month, day);
        DateTime date = DateTime.forDateOnly(year, month + 1, day);
        textView.setText(date.format(displayFormat));
      }
      this.buttonPressed = 0;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int buttonPressed) {
      this.buttonPressed = buttonPressed;
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        DatePicker datePicker = dialog.getDatePicker();
        onDateSet(datePicker, datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
      }
    }
  }
}
