import React, { useState } from "react";
import CategoryFilter from "../components/shared/Filters/CategoryFilter";
import SearchFilter from "../components/shared/Filters/SearchFilter";
import RestaurantList from "../components/shared/RestaurantList/RestaurantList";
import { SearchContext } from "../shared/context/SearchContext";
import { DishCuisineEnum } from "../shared/generated";

const Home: React.FC = () => {
  const [search, setSearch] = useState<string | undefined>("");
  const [category, setCategory] = useState<DishCuisineEnum | undefined>(undefined);

  return (
    <>
      <div className="w-full">
        <SearchFilter setValue={setSearch} />
        <CategoryFilter setValue={setCategory} />
        <SearchContext.Provider value={{ name: search, cuisines: category }}>
          <RestaurantList />
        </SearchContext.Provider>
      </div>
    </>
  );
};

export default Home;
