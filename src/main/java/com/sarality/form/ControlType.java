package com.sarality.form;

import com.sarality.form.binding.ViewBinding;
import com.sarality.form.binding.ViewBindingFactory;

import java.util.List;

/**
 * Interface for all Enums that define the types of Fields that can be used in a Form.
 * <p/>
 * The System provided controls are defined by the enum {@link InputControl}, while custom controls needs to
 * define a class that extends this
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ControlType {

  String getName();

  ViewBindingFactory getBindingFactory();
}
