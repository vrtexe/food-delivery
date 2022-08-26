import { ErrorMessage, Field } from "formik";
import React from "react";

interface TextInputPropTypes extends React.InputHTMLAttributes<HTMLInputElement> {
  name: string;
  label: string;
}

const TextInput: React.FC<TextInputPropTypes> = ({ label, className, ...rest }) => {
  return (
    <div>
      <label htmlFor={rest.id} className="block text-sm font-medium text-gray-700">
        {label}
      </label>
      <div className="mt-1">
        <Field
          className={`block w-full rounded-md border-gray-300 shadow-sm focus:border-green-500 focus:ring-green-500 sm:text-sm ${className}`}
          {...rest}
        />
      </div>
      <ErrorMessage name={rest.name}>
        {(errorMessage) => (
          <div data-cy={"error-" + rest.name} className="mt-1 text-xs font-medium text-red-600">
            {errorMessage}
          </div>
        )}
      </ErrorMessage>
    </div>
  );
};

export default TextInput;
