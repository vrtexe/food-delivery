import { Reducer } from "react";
import { User } from "../shared/generated";
import { loadAuthResponse } from "../utils";
import { logout } from "./auth.service";

export enum AuthActionsEnum {
  Login = "login",
  Register = "register",
  Logout = "logout",
}

type AuthAction =
  | { type: AuthActionsEnum.Login; payload: User }
  | { type: AuthActionsEnum.Register; payload: User }
  | { type: AuthActionsEnum.Logout; payload?: never };

export interface AuthContextState {
  user?: User;
}

export const InitialAuthState: AuthContextState = {
  user: loadAuthResponse()?.user,
};

const authReducer: Reducer<AuthContextState, AuthAction> = (state, action): AuthContextState => {
  const { type, payload } = action;

  switch (type) {
    case AuthActionsEnum.Login: {
      return {
        user: payload,
      };
    }

    case AuthActionsEnum.Register: {
      return {
        user: payload,
      };
    }

    case AuthActionsEnum.Logout: {
      logout();
      return {
        user: undefined,
      };
    }

    default: {
      throw new Error(`No action with type: ${type} found AuthReducer.`);
    }
  }
};

export default authReducer;
