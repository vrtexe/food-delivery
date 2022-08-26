import axios, { AxiosInstance, AxiosRequestConfig } from "axios";
import { loadAuthResponse } from "../utils";

const config: AxiosRequestConfig = {
  baseURL: import.meta.env.VITE_APP_ITEMS_API_URL,
};

const AxiosClient: AxiosInstance = axios.create(config);

AxiosClient.interceptors.request.use((request: AxiosRequestConfig) => {
  console.log(request.method?.toUpperCase(), request.baseURL, request.url);

  const persistedAuth = loadAuthResponse();
  if (persistedAuth) {
    request = {
      ...request,
      headers: {
        ...request.headers,
        Authorization: `Bearer ${persistedAuth.jwt}`,
      },
    };
    return request;
  }

  return request;
});

export default AxiosClient;
