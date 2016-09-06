package com.sarality.form;

import com.sarality.form.reader.FieldReader;

/**
 * Interface for all Enums that define the types of Fields that can be used in a Form.
 * <p/>
 * The System provided controls are defined by the enum {@link InputControl}, while custom controls needs to
 * define a class that extends this
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface FieldType {

  String getName();

  FieldReader createReader(int fieldId);
}
