import { AxiosResponse } from "axios";
import { DishCuisineEnum, DishDto } from "../generated";
import { Paged } from "../hooks/fetch.hook";
import { Pagination } from "../model/Pagination";
import { dishControllerApi } from "./api.service";

export interface DishCriteria {
  restaurantId: string;
  category: DishCuisineEnum;
}

export const fetchDishes = async (criteria: Partial<DishCriteria>, pagination: Pagination) => {
  const { restaurantId, category } = criteria;
  const { page, size, sort } = pagination;

  return (await dishControllerApi.getAllDishesByRestaurant(category, restaurantId, page, size, sort)) as AxiosResponse<
    Paged<DishDto>
  >;
};

export const fetchSingleDish = async (id: string) => {
  return await dishControllerApi.getDishById(id);
};
