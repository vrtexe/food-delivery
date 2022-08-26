import { createContext } from "react";
import { LoginDto, RegisterDto } from "../shared/generated";
import { AuthContextState, InitialAuthState } from "./auth.reducer";

export interface AuthContext {
  state: AuthContextState;
  login?: (payload: LoginDto) => Promise<void>;
  register?: (payload: RegisterDto) => Promise<void>;
  logout?: () => void;
}

export const AuthContext = createContext<AuthContext>({
  state: InitialAuthState,
});
