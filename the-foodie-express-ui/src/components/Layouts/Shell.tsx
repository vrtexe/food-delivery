import React from "react";
import Footer from "./Footer";
import NavBar from "./NavBar";

interface ShellPropTypes {
  children?: React.ReactNode;
}

const Shell: React.FC<ShellPropTypes> = ({ children }) => {
  return (
    <div className="pt-6">
      <NavBar />
      <main className="mx-6 mt-6 min-h-[65vh] max-w-screen-xl md:mx-auto">{children}</main>
      <Footer />
    </div>
  );
};

export default Shell;
