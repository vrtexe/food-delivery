import { useContext } from "react";
import { AuthContext } from "./auth.state";

export const useAuth = (): AuthContext => {
  const context = useContext(AuthContext);

  if (!context) {
    throw new Error("useAuth cannot be used outsize an AuthProvide");
  }

  return context;
};
