import { ShoppingCartItem, ShoppingCartItemDto } from "../shared/generated";
import { shoppingCartControllerApi } from "../shared/service/api.service";
import { removeStoredShoppingCartItem, storeShoppingCartItem } from "../utils";

export const addItemToCart = async (item: ShoppingCartItemDto): Promise<ShoppingCartItem> => {
  const { data } = await shoppingCartControllerApi.createShoppingCartItem(item);
  storeShoppingCartItem(data);
  return data;
};

export const removeItemfromCart = async (id: string): Promise<ShoppingCartItem> => {
  const { data } = await shoppingCartControllerApi.deleteShoppingCartItem(id);
  removeStoredShoppingCartItem(id);
  return data;
};
