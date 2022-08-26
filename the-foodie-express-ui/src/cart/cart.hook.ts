import { useContext } from "react";
import { CartContext, CartContextType } from "./cart.state";

export const useCart = (): CartContextType => {
  const context = useContext(CartContext);

  if (!context) {
    throw new Error("useCart cannot be used outsize an CartContextProvider");
  }

  return context;
};
