import { Form, Formik, FormikConfig } from "formik";
import React from "react";
import { useNavigate } from "react-router-dom";
import { object, string } from "yup";
import { LoginDto } from "../../../shared/generated";
import Button from "../../Base/Button";
import TextInput from "../../Base/TextInput";

const validator = object().shape({
  email: string().email("Неправилен формат на електронска пошта").required("Внесете ја вашата електронска пошта"),
  password: string().required("Внесете ја вашата лозинка"),
});

interface LoginFormPropTypes {
  onLogin: ((reg: LoginDto) => Promise<void>) | undefined;
}

const LoginForm: React.FC<LoginFormPropTypes> = ({ onLogin }) => {
  const navigate = useNavigate();

  const formik: FormikConfig<LoginDto> = {
    initialValues: {
      email: "",
      password: "",
    },
    validationSchema: validator,
    onSubmit: (values) => onLogin?.(values).then(() => navigate("/")),
  };

  return (
    <Formik {...formik}>
      <Form className="mx-auto mt-32 max-w-lg">
        <div>
          <TextInput label="Електронска пошта" type="text" name="email" id="email" placeholder="you@example.com" />
        </div>
        <div className="mt-4">
          <TextInput label="Лозинка" type="password" name="password" id="password" />
        </div>
        <div className="text-center">
          <Button className="mt-4" type={"submit"}>
            Најави се
          </Button>
        </div>
      </Form>
    </Formik>
  );
};

export default LoginForm;
