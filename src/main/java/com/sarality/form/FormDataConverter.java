package com.sarality.form;

/**
 * Converts Form Data to and from a Domain Object
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface FormDataConverter<T> {

  T generateDomainData(FormData data);

  FormData generateFormData(T data);
}
