import { Form, Formik, FormikConfig } from "formik";
import React from "react";
import { useNavigate } from "react-router-dom";
import { object, ref, string } from "yup";
import { RegisterDto, UserRoleEnum } from "../../../shared/generated";
import Button from "../../Base/Button";
import TextInput from "../../Base/TextInput";

interface RegisterFormPropTypes {
  onRegister: ((reg: RegisterDto) => Promise<void>) | undefined;
}

interface RegisterFormValues extends RegisterDto {
  confirmPassword: string;
}

const validator = object().shape({
  name: string().required("Внесете го вашето име"),
  surname: string().required("Внесете го вашето презиме"),
  email: string().email("Неправилен формат на електронска пошта").required("Внесете ја вашата електронска пошта"),
  password: string().min(8).max(16).required("Внесете ја вашата лозинка"),
  confirmPassword: string()
    .oneOf([ref("password")], "Лозинките не одговараат")
    .required("Потврдете ја вашата лозинка"),
});

const RegisterForm: React.FC<RegisterFormPropTypes> = ({ onRegister }) => {
  const navigate = useNavigate();

  const formik: FormikConfig<RegisterFormValues> = {
    initialValues: {
      name: "",
      surname: "",
      email: "",
      password: "",
      confirmPassword: "",
      role: UserRoleEnum.User,
    },
    validationSchema: validator,
    onSubmit: (values) => onRegister?.(values).then(() => navigate("/")),
  };

  return (
    <Formik {...formik}>
      <Form className="mx-auto mt-32 max-w-lg">
        <div>
          <TextInput label="Име" type="text" name="name" id="name" />
        </div>
        <div className="mt-4">
          <TextInput label="Презиме" type="text" name="surname" id="surname" />
        </div>
        <div className="mt-4">
          <TextInput label="Електронска пошта" type="text" name="email" id="email" placeholder="you@example.com" />
        </div>
        <div className="mt-4">
          <TextInput label="Лозинка" type="password" name="password" id="password" />
        </div>
        <div className="mt-4">
          <TextInput label="Потврди лозинка" type="password" name="confirmPassword" id="confirmPassword" />
        </div>
        <div className="text-center">
          <Button className="mt-4" type={"submit"}>
            Регистрирај се
          </Button>
        </div>
      </Form>
    </Formik>
  );
};

export default RegisterForm;
