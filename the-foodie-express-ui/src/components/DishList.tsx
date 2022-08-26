import React from "react";
import { Dish } from "../shared/generated";
import DishListItem from "./DishListItem";

interface DishListPropTypes {
  items: Dish[];
  vertical?: boolean;
}

const DishList: React.FC<DishListPropTypes> = ({ items, vertical }) => {
  return (
    <>
      <div className={`my-8 grid gap-4 ${!vertical && "md:grid-cols-2 lg:grid-cols-3"}`}>
        {items.map((dish, i) => (
            <DishListItem key={`${dish.id}-${i}`} dish={dish} vertical={vertical} />
        ))}
      </div>
    </>
  );
};

export default DishList;
