package com.sarality.form.reader;

import com.sarality.form.FormField;
import com.sarality.form.binding.ViewBinding;

/**
 * Interface for all class that create a Reader for a Control
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ControlReaderFactory {

  ControlReader createReader(FormField field);
}
