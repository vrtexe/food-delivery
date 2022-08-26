import React from "react";
import { SearchContext } from "../../../shared/context/SearchContext";
import { useFetch } from "../../../shared/hooks/fetch.hook";
import { usePagination } from "../../../shared/hooks/pagination.hook";
import { fetchRestaurants } from "../../../shared/service/restaurant.service";
import Pagination from "../Pagination/Pagination";
import RestaurantCard from "./RestaurantCard";

const RestaurantList: React.FC = () => {
  const { pagination, update, ...paginationControls } = usePagination({ size: 16, total: 1 });

  const restaurants = useFetch(fetchRestaurants, pagination, SearchContext, update);

  return (
    <>
      <div className="my-8 grid gap-4 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
        {restaurants?.map((r, i) => (
          <RestaurantCard key={`${r.id}-${i}`} {...r} />
        ))}
      </div>
      <Pagination pagination={pagination} {...paginationControls} update={update} />
    </>
  );
};

export default RestaurantList;
