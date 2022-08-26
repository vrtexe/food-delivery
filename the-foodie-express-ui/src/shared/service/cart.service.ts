import { OrderDto } from "../generated";
import { orderControllerApi } from "./api.service";

export const createOrder = async (order: OrderDto) => {
  return await orderControllerApi.create(order);
};
