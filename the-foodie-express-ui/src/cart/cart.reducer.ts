import { Reducer } from "react";
import { ShoppingCartItem } from "../shared/generated";
import { loadShoppingCart } from "../utils";

export enum CartActionsEnum {
  AddItem = "AddItem",
  RemoveItem = "RemoveItem",
  Clear = "Clear",
}

type CartAction =
  | {
      type: CartActionsEnum.AddItem;
      payload: ShoppingCartItem;
    }
  | {
      type: CartActionsEnum.RemoveItem;
      payload: string;
    }
  | {
      type: CartActionsEnum.Clear;
      payload: null;
    };

export interface CartContextState {
  items: ShoppingCartItem[];
}

export const InitialCartState: CartContextState = {
  items: loadShoppingCart() ?? [],
};

const cartReducer: Reducer<CartContextState, CartAction> = (state, action): CartContextState => {
  const { type, payload } = action;
  switch (type) {
    case CartActionsEnum.AddItem: {
      return {
        items: [...state.items, payload],
      };
    }
    case CartActionsEnum.RemoveItem: {
      return {
        items: state.items.filter((item) => item.id !== payload),
      };
    }
    case CartActionsEnum.Clear: {
      return {
        items: [],
      };
    }
    default: {
      throw new Error(`No action with type: ${type} found CartReducer.`);
    }
  }
};

export default cartReducer;
