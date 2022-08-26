import {  RestaurantDto } from "../generated";
import { restaurantControllerApi } from "./api.service";
import { AxiosResponse } from "axios";
import { Paged } from "../hooks/fetch.hook";
import { Pagination } from "../model/Pagination";
export interface RestaurantCriteria {
  name?: string;
  cuisines?:
    | "ITALIAN"
    | "INDIAN"
    | "JAPANESE"
    | "CHINESE"
    | "THAI"
    | "TURKISH"
    | "MEXICAN"
    | "FRENCH"
    | "SPANISH"
    | "MACEDONIAN";
  rating?: number;
  workingHours?: string;
  address?: string;
  phoneNumber?: string;
}

export const fetchRestaurants = async (criteria: RestaurantCriteria, pagination: Pagination) => {
  const { name, cuisines, rating, address, workingHours, phoneNumber } = criteria;
  const { page, size, sort } = pagination;

  return (await restaurantControllerApi.getAllRestaurants(
    name ? name : undefined,
    cuisines ?? undefined,
    rating ?? undefined,
    workingHours ?? undefined,
    address ?? undefined,
    phoneNumber ?? undefined,
    page,
    size,
    sort,
  )) as AxiosResponse<Paged<RestaurantDto>>;
};

export const fetchSingleRestaurant = async (id: string) => {
  return await restaurantControllerApi.getRestaurantById(id);
};
