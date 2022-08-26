import React from "react";
import { useAuth } from "../../auth/auth.hook";
import RegisterForm from "../../components/shared/forms/RegisterForm";

const Register: React.FC = () => {
  const { register } = useAuth();

  return <RegisterForm onRegister={register} />;
};

export default Register;
