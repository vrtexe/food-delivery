import React from "react";
import {useAuth} from "../../auth/auth.hook";
import LoginForm from "../../components/shared/forms/LoginForm";

const Login: React.FC = () => {
  const { login } = useAuth();

  return <LoginForm onLogin={login} />;
};

export default Login;
