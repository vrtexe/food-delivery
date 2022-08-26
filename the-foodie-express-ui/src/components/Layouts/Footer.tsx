import React from "react";
import Button from "../Base/Button";

const Footer: React.FC = () => {
  return (
    <div className="overflow-hidden">
      <div className="relative -bottom-16 -left-6  flex h-80 w-[120%] lg:w-[140%] 2xl:w-[150%] -rotate-6 items-center justify-center bg-green-700 text-white shadow-inner">
        <div className="absolute left-6 top-48 sm:top-36 md:top-44 lg:top-36 xl:top-32 2xl:top-20 rotate-6">Тим 24 - Copyright &copy; {new Date().getFullYear()}</div>
        <div className="absolute right-[20%] 2xl:right-[40%] lg:right-[30%] top-[10%] flex h-[2.8rem] rotate-6 gap-4">
          <Button className="bg-gray-300 bg-opacity-90" icon="info" iconType="icon">
            Инфо
          </Button>
          <Button className="bg-gray-300 bg-opacity-90" icon="about" iconType="icon">
            За Нас
          </Button>
        </div>
      </div>
    </div>
  );
};

export default Footer;
