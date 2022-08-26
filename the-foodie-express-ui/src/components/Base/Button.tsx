import React, { MouseEventHandler, PropsWithChildren, Ref } from "react";
import { Link } from "react-router-dom";
import Icon from "./Icon";

export interface ButtonProps extends PropsWithChildren<unknown> {
  onClick?: MouseEventHandler<HTMLButtonElement>;
  ref?: Ref<HTMLButtonElement>;
  text?: boolean;
  color?: "white" | "black";
  className?: string;
  icon?: string;
  iconType?: "icon" | "svg";
  iconOnly?: boolean;
  to?: string;
  compact?: boolean;
  gap?: number;
  outline?: boolean;
  type?: "button" | "submit" | "reset" | undefined;
}

const Button: React.FC<ButtonProps> = (props) => {
  const { children, text, compact, outline, gap, className, to, icon, iconType, iconOnly, ...prop } = props;

  const ButtonElement: React.FC = () => (
    <>
      <button
        className={`${className ?? ""}
                    ${text ? "bg-transparent" : "bg-white"}
                    ${!text && ""}
                    ${!compact && "py-2 px-4"}
                    inline-flex
                    h-full 
                    items-center
                    rounded-md
                    ${!text && "border border-gray-300 shadow-md hover:bg-gray-50 hover:text-green-600"}
                    ${outline && "border border-gray-300 shadow-md hover:bg-gray-50 hover:text-green-600"}
                    font-medium
                    text-gray-700
                    focus:outline-none
                    focus:ring-2
                    focus:ring-green-500 
                    focus:ring-offset-0
                  `}
        {...prop}>
        <div className={`flex gap-${gap ?? 4} h-full`}>
          {(icon || iconOnly) && (
            <span className="h-full">
              <Icon name={icon ?? ""} type={iconType ?? "icon"} />
            </span>
          )}

          <span className="flex items-center justify-center">{iconOnly ?? children}</span>
        </div>
      </button>
    </>
  );

  return (
    <>
      {to ? (
        <Link className="flex h-full items-center" to={to}>
          <ButtonElement />
        </Link>
      ) : (
        <ButtonElement />
      )}
    </>
  );
};
export default Button;
