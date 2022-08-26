import React from "react";
import { ShoppingCartItem, ShoppingCartItemDto } from "../shared/generated";

export interface CartContextType {
  items: ShoppingCartItem[];
  addShoppingCartItem: (item: ShoppingCartItemDto) => void;
  removeShoppingCartItem: (item: string) => void;
  clearCart: () => void;
}

export const CartContext = React.createContext<CartContextType>({
  items: [],
  addShoppingCartItem: () => {
    throw new Error("context used outside provider");
  },
  removeShoppingCartItem: () => {
    throw new Error("context used outside provider");
  },
  clearCart: () => {
    throw new Error("context used outside provider");
  },
});
