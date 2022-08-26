import React, { PropsWithChildren, useReducer } from "react";
import { ShoppingCartItemDto } from "../shared/generated";
import { clearShoppingCart } from "../utils";
import cartReducer, { CartActionsEnum, InitialCartState } from "./cart.reducer";
import { addItemToCart, removeItemfromCart } from "./cart.service";
import { CartContext } from "./cart.state";

const CartContextProvider: React.FC<PropsWithChildren<unknown>> = ({ children }) => {
  const [state, dispatch] = useReducer(cartReducer, InitialCartState);

  const addShoppingCartItem = async (item: ShoppingCartItemDto) => {
    const response = await addItemToCart(item);
    await dispatch({
      type: CartActionsEnum.AddItem,
      payload: response,
    });
  };

  const removeShoppingCartItem = async (id: string) => {
    await removeItemfromCart(id);
    await dispatch({
      type: CartActionsEnum.RemoveItem,
      payload: id,
    });
  };

  const clearCart = async () => {
    clearShoppingCart();
    await dispatch({
      type: CartActionsEnum.Clear,
      payload: null,
    });
  };

  return (
    <CartContext.Provider
      value={{
        items: state.items,
        addShoppingCartItem: addShoppingCartItem,
        removeShoppingCartItem: removeShoppingCartItem,
        clearCart: clearCart,
      }}>
      {children}
    </CartContext.Provider>
  );
};

export default CartContextProvider;
