import { createContext } from "react";
import { DishCriteria } from "../service/dish.service";

export const DishSearchContext = createContext<Partial<DishCriteria>>({});
