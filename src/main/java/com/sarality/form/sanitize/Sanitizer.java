package com.sarality.form.sanitize;

/**
 * Sanitizer a value or set of values that are extracted from a Form Binding.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface Sanitizer {

  String sanitize(String value);
}
