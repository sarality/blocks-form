package com.sarality.form.reader;

import com.sarality.form.reader.FieldReader;

/**
 * Interface for all class that create an instance of the FormFieldReader
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface FieldReaderFactory {

  FieldReader create(int viewId);
}
