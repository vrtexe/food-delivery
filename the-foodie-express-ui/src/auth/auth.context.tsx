import React, { PropsWithChildren, useReducer } from "react";
import { LoginDto, RegisterDto } from "../shared/generated";
import authReducer, { AuthActionsEnum, InitialAuthState } from "./auth.reducer";
import { login, register } from "./auth.service";
import { AuthContext } from "./auth.state";

export const AuthProvider: React.FC<PropsWithChildren<unknown>> = ({ children }) => {
  const [state, dispatch] = useReducer(authReducer, InitialAuthState);

  const handleLogin = async (payload: LoginDto) => {
    const response = await login(payload);

    await dispatch({
      type: AuthActionsEnum.Login,
      payload: response.user,
    });
  };

  const handleRegister = async (payload: RegisterDto) => {
    const response = await register(payload);

    await dispatch({
      type: AuthActionsEnum.Register,
      payload: response.user,
    });
  };

  const handleLogout = () => {
    dispatch({
      type: AuthActionsEnum.Logout,
    });
  };

  return (
    <>
      <AuthContext.Provider
        value={{ state: state, login: handleLogin, logout: handleLogout, register: handleRegister }}>
        {children}
      </AuthContext.Provider>
    </>
  );
};

export default AuthProvider;
