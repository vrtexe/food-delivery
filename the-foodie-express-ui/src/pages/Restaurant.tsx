import StarIcon from "@heroicons/react/solid/StarIcon";
import React, { useState } from "react";
import { useParams } from "react-router-dom";
import CategoryFilter from "../components/shared/Filters/CategoryFilter";
import RestaurantDishList from "../components/shared/RestaurantList/RestaurantDishList";
import { DishSearchContext } from "../shared/context/DishSearchContext";
import { DishCuisineEnum } from "../shared/generated";
import { useSingleFetch } from "../shared/hooks/fetch.hook";
import { fetchSingleRestaurant } from "../shared/service/restaurant.service";

const RestaurantPage: React.FC = () => {
  const { id } = useParams();
  const restaurant = useSingleFetch(fetchSingleRestaurant, id);
  const [category, setCategory] = useState<DishCuisineEnum | undefined>(undefined);

  return (
    <div className="pb-14">
      <div className="my-4 rounded-lg bg-green-200 drop-shadow-md">
        <CategoryFilter setValue={setCategory} />
      </div>
      <div style={{ backgroundImage: "url(/restaurant-bg.jpg)" }} className="h-80 rounded-3xl object-cover">
        <div className="mr-4 flex items-center justify-end gap-1 pt-4 text-3xl font-bold text-white">
          <StarIcon className="w-1h-16 h-16 text-orange-600" />
        </div>
        <div className="mt-12 flex items-center justify-center pr-6">
          <span className="text-7xl font-bold text-white">{restaurant?.name}</span>
        </div>
      </div>
      <DishSearchContext.Provider value={{ category: category, restaurantId: id }}>
        <RestaurantDishList />
      </DishSearchContext.Provider>
    </div>
  );
};

export default RestaurantPage;
