import React from "react";
import { useCart } from "../cart/cart.hook";
import DishList from "../components/DishList";
import OrderForm from "../components/shared/forms/OrderForm";
import { createOrder } from "../shared/service/cart.service";

const Cart: React.FC = () => {
  const { items, clearCart } = useCart();
  const dishes = items.map((it) => it.dish ?? {});

  return (
    <div className="grid-cols-2 gap-12 md:grid md:px-8">
      <div className="mx-6 max-h-[55vh] overflow-y-auto">
        <DishList items={dishes} vertical={true} />
      </div>
      <div>
        <OrderForm
          onOrder={async (order) => {
            await createOrder(order).then((response) => {
              if (response.status === 200) {
                clearCart();
              }
            });
          }}
        />
      </div>
    </div>
  );
};

export default Cart;
