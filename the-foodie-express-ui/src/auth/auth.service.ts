import { LoginDto, RegisterDto } from "../shared/generated";
import { userControllerApi } from "../shared/service/api.service";
import { clearAuthResponse, storeAuthResponse } from "../utils";

export const login = async (credentials: LoginDto) => {
  const result = await userControllerApi.login(credentials);
  storeAuthResponse(result.data);
  return result.data;
};

export const register = async (params: RegisterDto) => {
  const result = await userControllerApi.register(params);
  storeAuthResponse(result.data);
  return result.data;
};

export const logout = async () => {
  clearAuthResponse();
};
