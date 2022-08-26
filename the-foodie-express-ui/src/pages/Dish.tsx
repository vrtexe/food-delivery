import MinusIcon from "@heroicons/react/solid/MinusIcon";
import PlusIcon from "@heroicons/react/solid/PlusIcon";
import React, { useState } from "react";
import { useParams } from "react-router-dom";
import { useCart } from "../cart/cart.hook";
import Button from "../components/Base/Button";
import Icon from "../components/Base/Icon";
import { useSingleFetch } from "../shared/hooks/fetch.hook";
import { fetchSingleDish } from "../shared/service/dish.service";

const Dish: React.FC = () => {
  const { id } = useParams<{ id: string }>();

  const dish = useSingleFetch(fetchSingleDish, id);
  const { items, addShoppingCartItem, removeShoppingCartItem } = useCart();
  const [quantity, setQuantity] = useState(items.find((item) => item.dish?.id === id)?.quantity ?? 0);

  return (
    <>
      <div className="grid-cols-3 place-items-center gap-12 md:grid">
        <div className="col-span-1 mb-12 md:mb-0">
          <img className="w-full" src="/burger.png" />
          <div className="flex h-8 justify-between gap-8">
            <div className="flex items-center">
              <Icon name="star" type="svg" />
              <span>{3.5}</span>
            </div>
            <div className="w-[1px] bg-gray-300"></div>
            <div className="flex items-center gap-2">
              <Icon name="clock" type="svg" />
              <span>{"20min"}</span>
            </div>
          </div>
        </div>
        <div className="col-span-2 w-full rounded-xl border border-solid border-gray-300 bg-white p-4 px-8">
          <div className="flex justify-center pb-4 text-xl font-bold capitalize">{dish?.name}</div>
          <div className="flex capitalize">Cuisine type: {dish?.cuisine?.toLowerCase()}</div>
          <div className="py-4 text-sm">{dish?.description}</div>
          <div className="my-4 h-[1px] bg-gray-300"></div>
          <div className="flex">
            <div className="flex w-full flex-col items-center gap-2">
              <div>Избери количина</div>
              <div className="flex select-none items-center gap-4">
                <div
                  onClick={() => quantity > 1 && setQuantity(quantity - 1)}
                  className="box-border h-8 w-8 cursor-pointer rounded-full bg-red-600 p-2 text-white shadow-lg hover:bg-red-700 active:bg-red-800">
                  <MinusIcon className="h-full w-full" />
                </div>
                <div className="text-2xl font-bold">{quantity}</div>
                <div
                  onClick={() => setQuantity(quantity + 1)}
                  className="box-border h-8 w-8 cursor-pointer rounded-full bg-green-600 p-2 text-white shadow-lg hover:bg-green-700 active:bg-green-800">
                  <PlusIcon className="h-full w-full" />
                </div>
              </div>
              <Button
                className="flex h-8 w-full items-center justify-center text-sm"
                type="button"
                onClick={() => addShoppingCartItem({ dishId: dish?.id ?? "", quantity: quantity })}>
                Во кошничка
              </Button>
              <Button
                className="flex h-8 w-full items-center justify-center text-sm"
                type="button"
                onClick={() => removeShoppingCartItem(items.find((d) => d.dish?.id === dish?.id)?.id ?? "")}>
                Избриши
              </Button>
            </div>
            <div className="flex w-full items-center justify-center text-lg font-bold underline underline-offset-4">
              {dish?.price}мкд
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Dish;
