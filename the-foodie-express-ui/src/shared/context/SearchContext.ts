import { createContext } from "react";
import { RestaurantCriteria } from "../service/restaurant.service";

export const SearchContext = createContext<Partial<RestaurantCriteria>>({});
