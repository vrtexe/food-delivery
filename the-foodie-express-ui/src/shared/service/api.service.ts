import AxiosClient from "../AxiosClient";
import {
  DishControllerApi,
  OrderControllerApi,
  RestaurantControllerApi,
  ShoppingCartControllerApi,
  UserControllerApi,
} from "../generated";

const BASE_PATH = "/backend";

export const userControllerApi = new UserControllerApi(undefined, BASE_PATH, AxiosClient);
export const dishControllerApi = new DishControllerApi(undefined, BASE_PATH, AxiosClient);
export const restaurantControllerApi = new RestaurantControllerApi(undefined, BASE_PATH, AxiosClient);
export const shoppingCartControllerApi = new ShoppingCartControllerApi(undefined, BASE_PATH, AxiosClient);
export const orderControllerApi = new OrderControllerApi(undefined, BASE_PATH, AxiosClient);
