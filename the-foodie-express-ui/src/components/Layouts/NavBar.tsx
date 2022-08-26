import ShoppingCartIcon from "@heroicons/react/solid/ShoppingCartIcon";
import React, { useEffect, useState } from "react";
import { NavLink, useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "../../auth/auth.hook";
import { useCart } from "../../cart/cart.hook";
import Button from "../Base/Button";

const NavBar: React.FC = () => {
  const {
    state: { user },
    logout,
  } = useAuth();
  const [open, setOpen] = useState(false);
  const navigate = useNavigate();
  const location = useLocation();
  const { items } = useCart();

  useEffect(() => setOpen(false), [location.pathname]);

  return (
    <nav
      className={
        "mx-auto flex h-[4rem] max-w-screen-xl items-center justify-between rounded-full border border-solid border-gray-300 bg-white  px-6 py-3 shadow-md  drop-shadow-sm"
      }>
      <NavLink to={"/"} className="flex items-center gap-1">
        <img src="/public/assets/icon/restaurant.png" className="h-12" />
        <span className="font-medium text-gray-600">The Foodie Express</span>
      </NavLink>
      <div className={"flex h-full items-center gap-4"}>
        {!user && (
          <>
            <Button to="/auth/login" text={true}>
              Најави се
            </Button>
            <Button to="/auth/register" text={true}>
              Креирај сметка
            </Button>
          </>
        )}
        {user && (
          <>
            <div className="relative" onClick={() => setOpen((old) => !old)}>
              <div className={"cursor-pointer text-sm font-bold uppercase text-green-500"}>
                {user.name} {user.surname}
              </div>
              <div
                className={`${
                  open ? "scale-100 opacity-100" : "scale-95 opacity-0"
                } z-8 absolute top-8 right-0 w-48 origin-top-right transform rounded-md bg-white py-1 shadow-lg ring-1 ring-black ring-opacity-5 transition duration-100 ease-out selection:absolute focus:outline-none`}
                role="menu"
                aria-orientation="vertical"
                aria-labelledby="user-menu-button"
                tabIndex={-1}>
                <div
                  onClick={() => {
                    logout?.();
                    navigate("/");
                  }}
                  className="block cursor-pointer px-4 py-2 text-sm text-gray-700"
                  role="menuitem"
                  tabIndex={-1}
                  id="user-menu-item-0">
                  Одјави се
                </div>
              </div>
            </div>
            <NavLink to={"/cart"} className="flex items-center gap-2 rounded-full border border-gray-300 px-4 py-2">
              <ShoppingCartIcon className="h-6 w-6 text-orange-600" />
              <span>{items.length}</span>
            </NavLink>
          </>
        )}
      </div>
    </nav>
  );
};

export default NavBar;
