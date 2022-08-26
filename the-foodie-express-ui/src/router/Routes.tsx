import React, { ComponentType } from "react";
import { Route, Routes } from "react-router-dom";
import About from "../pages/About";
import Login from "../pages/Auth/Login";
import Register from "../pages/Auth/Register";
import Cart from "../pages/Cart";
import Dish from "../pages/Dish";
import Home from "../pages/Home";
import RestaurantDetailsPage from "../pages/Restaurant";

const routes: { path: string; Element: ComponentType }[] = [
  {
    path: "/",
    Element: Home,
  },
  {
    path: "/about",
    Element: About,
  },
  {
    path: "/cart",
    Element: Cart,
  },
  {
    path: "/restaurants/:id",
    Element: RestaurantDetailsPage,
  },
  {
    path: "/dish/:id",
    Element: Dish,
  },
  {
    path: "/auth/login",
    Element: Login,
  },
  {
    path: "/auth/register",
    Element: Register,
  },
];

const AppRoutes: React.FC = () => {
  return (
    <Routes>
      {routes.map(({ path, Element }) => (
        <Route key={path} path={path} element={<Element />} />
      ))}
    </Routes>
  );
};

export default AppRoutes;
