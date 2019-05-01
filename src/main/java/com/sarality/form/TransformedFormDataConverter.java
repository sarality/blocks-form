package com.sarality.form;

/**
 * A converter that calls an underlying converter but after transforming the form data.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class TransformedFormDataConverter<T> implements FormDataConverter<T> {

  private FormDataConverter<T> converter;
  private FormDataTransformer transformer;

  public TransformedFormDataConverter(FormDataConverter<T> converter, FormDataTransformer transformer) {
    this.converter = converter;
    this.transformer = transformer;
  }

  @Override
  public T generateDomainData(FormData data) {
    if (transformer != null) {
      transformer.transform(data);
    }
    return converter.generateDomainData(data);
  }

  @Override
  public FormData generateFormData(T data) {
    return converter.generateFormData(data);
  }
}
