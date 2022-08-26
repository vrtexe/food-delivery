import React from "react";
import { Link } from "react-router-dom";
import { Restaurant } from "../../../shared/generated";
import Icon from "../../Base/Icon";

const RestaurantCard: React.FC<Restaurant> = (restaurant) => {
  return (
    <>
      <Link to={`/restaurants/${restaurant.id}`}>
        <div className="flex h-full min-h-[16rem] cursor-pointer flex-col justify-around gap-2 rounded-lg border border-solid border-gray-300 bg-gray-50 p-4 shadow-md hover:border-green-400 hover:bg-gray-100">
          <div className="h-full w-full p-4">
            <img className="h-full w-full" src="assets/image/restaurant.png" alt="restaurant logo" />
          </div>
          <div className="py-4 font-bold">{restaurant.name}</div>
          <div className="flex items-center justify-between gap-5">
            <div className="flex h-6 w-[20%] items-center gap-4">
              <Icon name="star" type="svg" />
              {restaurant.rating}
            </div>
            <div className="flex h-4 w-[80%] items-center justify-end gap-4">
              <Icon name="clock" type="svg" />
              {restaurant.workingHours}
            </div>
          </div>
        </div>
      </Link>
    </>
  );
};

export default RestaurantCard;
