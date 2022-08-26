export interface Validation {
  min?: number;
  max?: number;
  minLength?: number;
  maxLength?: number;
  required?: boolean;
  pattern?: string;
}

export const MinMaxValueValidation = (
  min: number = Number.MIN_SAFE_INTEGER,
  max: number = Number.MAX_SAFE_INTEGER,
): Partial<Validation> => {
  return {
    max,
    min,
  };
};

export const MinMaxLengthValidation = (minLength = 1, maxLength = 256): Partial<Validation> => {
  return {
    maxLength,
    minLength,
  };
};

export const RequiredValidation = (): Partial<Validation> => {
  return {
    required: true,
  };
};

export const RegularExpressionValidation = (pattern: string): Partial<Validation> => {
  return {
    pattern,
  };
};
