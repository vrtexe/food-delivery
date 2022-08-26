import { CART_KEY, TOKEN_KEY, USER_KEY } from "./const";
import { AuthResponse, ShoppingCartItem } from "./shared/generated";

export const storeAuthResponse = (auth: AuthResponse) => {
  localStorage.setItem(TOKEN_KEY, auth.jwt);
  localStorage.setItem(USER_KEY, JSON.stringify(auth.user));
};

export const loadAuthResponse = (): AuthResponse | null => {
  const jwt: string | null = localStorage.getItem(TOKEN_KEY);
  const user: string | null = localStorage.getItem(USER_KEY);

  if (!jwt || !user) return null;
  return {
    jwt: jwt,
    user: JSON.parse(user),
  } as AuthResponse;
};

export const clearAuthResponse = () => {
  localStorage.removeItem(TOKEN_KEY);
  localStorage.removeItem(USER_KEY);
};

export const storeShoppingCartItem = (item: ShoppingCartItem) => {
  let persisted = loadShoppingCart() ?? [];
  persisted = [...persisted, item];
  localStorage.setItem(CART_KEY, JSON.stringify(persisted));
};

export const removeStoredShoppingCartItem = (id: string) => {
  let persisted = loadShoppingCart() ?? [];
  persisted = persisted.filter((item) => item.id !== id);
  localStorage.setItem(CART_KEY, JSON.stringify(persisted));
};

export const loadShoppingCart = (): ShoppingCartItem[] | null => {
  const items: string | null = localStorage.getItem(CART_KEY);

  if (!items) return null;
  return JSON.parse(items);
};

export const clearShoppingCart = () => {
  localStorage.removeItem(CART_KEY);
};
