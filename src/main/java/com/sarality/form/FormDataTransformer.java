package com.sarality.form;

/**
 * Interface for classes that transform a Form Data object.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface FormDataTransformer {

  void init();

  void transform(FormData form);
}
