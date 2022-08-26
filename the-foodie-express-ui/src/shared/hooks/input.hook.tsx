import React, { ChangeEvent, FunctionComponent, HTMLInputTypeAttribute, useMemo, useState } from "react";
import { Validation } from "../validations/base.validation";

export type InputValueType = string | number | readonly string[];

export interface Input<T> {
  element: React.FC;
  value: T;
}

export interface Inputs<T> {
  element: React.FC[];
  value: T;
}

export interface InputProps<T> {
  className?: string;
  name: string;
  placeholder?: string;
  initial?: T;
  type: HTMLInputTypeAttribute;
  validation?: Validation;
  disabled?: boolean;
}

export function useInput<T extends InputValueType>({
  initial,
  placeholder,
  type,
  ...props
}: InputProps<T>): [FunctionComponent, T | undefined] {
  const [value, setValue] = useState<T | undefined>(initial);
  const onChange = (e: ChangeEvent<HTMLInputElement>) => setValue(e.target.value as T);
  const element: React.FC = () =>
    useMemo(
      () => (
        <input value={value} onChange={onChange} autoFocus={true} type={type} placeholder={placeholder} {...props} />
      ),
      [value],
    );

  return [element, value];
}
