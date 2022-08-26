import React from "react";
import { BrowserRouter } from "react-router-dom";
import AuthProvider from "../auth/auth.context";
import CartContextProvider from "../cart/cart.context";
import Shell from "../components/Layouts/Shell";
import AppRoutes from "../router/Routes";

const App: React.FC = () => {
  return (
    <BrowserRouter>
      <AuthProvider>
        <CartContextProvider>
          <Shell>
            <AppRoutes />
          </Shell>
        </CartContextProvider>
      </AuthProvider>
    </BrowserRouter>
  );
};

export default App;
