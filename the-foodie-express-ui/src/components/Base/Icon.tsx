import React, { lazy, Suspense, useMemo } from "react";

type IconProp = { name: string; type: "icon" | "svg" | "image" };

const baseSvgPath = "/public/assets";
const basePngPath = "/assets";

const Icon: React.FC<IconProp> = ({ name, type }) => {
  const SvgComponent = lazy(
    () =>
      import(
        /* @vite-ignore */
        `${baseSvgPath}/${type}/${name}.${type}`
      ),
  );

  return useMemo(
    () => (
      <>
        {type === "svg" && (
          <Suspense>
            <SvgComponent className="h-full" />
          </Suspense>
        )}
        {type === "icon" && <img className="h-full" src={`${basePngPath}/icon/${name}.png`} alt={name} />}
      </>
    ),
    [],
  );
};

export default Icon;
