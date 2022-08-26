import React from "react";
import { DishSearchContext } from "../../../shared/context/DishSearchContext";
import { useFetch } from "../../../shared/hooks/fetch.hook";
import { usePagination } from "../../../shared/hooks/pagination.hook";
import { fetchDishes } from "../../../shared/service/dish.service";
import DishList from "../../DishList";
import Pagination from "../Pagination/Pagination";

const RestaurantDishList: React.FC = () => {
  const { pagination, update, ...paginationControls } = usePagination({ size: 9, total: 1 });
  const dishes = useFetch(fetchDishes, pagination, DishSearchContext, update);

  return (
    <>
      <div className="mt-12">
        <DishList items={dishes ?? []} />
        <Pagination pagination={pagination} update={update} {...paginationControls} />
      </div>
    </>
  );
};

export default RestaurantDishList;
