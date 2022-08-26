import React, { Dispatch, MouseEventHandler, SetStateAction, useMemo, useRef, useState } from "react";
import { DishCuisineEnum } from "../../../shared/generated";

const CategoryFilter: React.FC<{ setValue: Dispatch<SetStateAction<DishCuisineEnum | undefined>> }> = ({
  setValue,
}) => {
  const scrollRef = useRef<HTMLDivElement>(null);
  const [category, setCategory] = useState<DishCuisineEnum | undefined>(undefined);

  useMemo(() => {
    setValue(category);
  }, [category]);

  let mouseDown = false;

  const handleMouseMove: MouseEventHandler = (e) => {
    if (!mouseDown) {
      return;
    }

    scrollRef.current?.scrollBy({ left: -e.movementX });
  };

  const handleCategoryUpdate = (value: DishCuisineEnum) => {
    if (value === category) {
      return setCategory(undefined);
    }
    setCategory(value);
  };

  const generateFields = () => {
    return Object.keys(DishCuisineEnum)
      .map((s) => s as keyof typeof DishCuisineEnum)
      .map((cuisine, i) => (
        <div
          onClick={() => handleCategoryUpdate(DishCuisineEnum[cuisine])}
          className={`${
            category === DishCuisineEnum[cuisine]
              ? "border border-solid border-green-500"
              : "border border-solid border-gray-200"
          } cursor-pointer rounded-xl bg-white px-4 py-2 shadow-md`}
          key={`${cuisine}-${i}`}>
          {cuisine}
        </div>
      ));
  };

  return useMemo(
    () => (
      <>
        <div className="flex justify-center">
          <div
            ref={scrollRef}
            onMouseDown={() => (mouseDown = true)}
            onMouseUp={() => (mouseDown = false)}
            onMouseLeave={() => (mouseDown = false)}
            onMouseMove={handleMouseMove}
            className="flex select-none gap-4 overflow-hidden py-4 px-2">
            {generateFields()}
          </div>
        </div>
      </>
    ),
    [category],
  );
};

export default CategoryFilter;
