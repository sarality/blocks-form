package com.sarality.form;

import android.app.Activity;

import com.sarality.form.reader.FieldReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that reads data from a Form.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class FormDataReader {

  private final List<FieldReader> readerList = new ArrayList<>();

  public FormDataReader(FormField... fields) {
    List<FormField> fieldList = new ArrayList<>();
    fieldList.addAll(Arrays.asList(fields));
    for (FormField field : fieldList) {
      FieldReader reader = field.getFieldType().createReader(field.getFieldId());
      readerList.add(reader);
    }
  }

  public void init(Activity activity) {
    for (FieldReader reader : readerList) {
      reader.init(activity);
    }
  }

  public FormFieldData readData() {
    FormFieldData data = new FormFieldData();
    for (FieldReader reader: readerList) {
      int fieldId = reader.getFieldId();
      String text = reader.getText();
      data.addValue(fieldId, text);
    }
    return data;
  }
}
