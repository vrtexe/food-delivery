import ClockIcon from "@heroicons/react/outline/ClockIcon";
import FireIcon from "@heroicons/react/solid/FireIcon";
import StarIcon from "@heroicons/react/solid/StarIcon";
import React from "react";
import { Link } from "react-router-dom";
import { Dish } from "../shared/generated";

interface DishListItemPropTypes {
  dish: Dish;
  vertical?: boolean;
}

const DishListItem: React.FC<DishListItemPropTypes> = ({ dish, vertical }) => {
  return (
    <Link to={`/dish/${dish.id}`}>
      <div
        className={`h-full cursor-pointer ${
          vertical ? "grid grid-cols-2 place-items-center" : "flex flex-col"
        } justify-around gap-2 rounded-lg border border-solid border-gray-300 bg-gray-50  p-4  shadow-md hover:border-green-400 hover:bg-gray-100`}>
        <div className="col-span-1 ">
          <img src="/burger.png" alt="Burger image" />
        </div>
        <div>
          <div className="mt-4 flex flex-col items-start justify-between text-lg font-bold">
            <span>{dish.name}</span>
            <span>{dish.price} ден.</span>
          </div>
          <div className="flex items-center justify-between text-sm text-gray-500">
            <span>20 мин.</span>
          </div>
          <div className="mt-4 flex items-center justify-between text-sm">
            <div className="flex items-center gap-1">
              <StarIcon className="h-4 w-4 text-orange-600" />
              <span>3.5</span>
            </div>
            <div className="flex items-center gap-1">
              <ClockIcon className="h-4 w-4 text-orange-600" />
              <span>20 min.</span>
            </div>
            <div className="flex items-center gap-1">
              <FireIcon className="h-4 w-4 text-orange-600" />
              <span>850 kcal</span>
            </div>
          </div>
        </div>
      </div>
    </Link>
  );
};

export default DishListItem;
