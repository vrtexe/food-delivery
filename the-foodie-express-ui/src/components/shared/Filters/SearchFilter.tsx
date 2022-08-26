import React, { Dispatch, memo, SetStateAction, useMemo } from "react";
import { useInput } from "../../../shared/hooks/input.hook";
import Icon from "../../Base/Icon";

const SearchFilter: React.FC<{ setValue: Dispatch<SetStateAction<string | undefined>> }> = ({ setValue }) => {
  const [SearchInput, searchValue] = useInput<string>({
    name: "search",
    type: "text",
    placeholder: "Пребарувај",
    className: `border
                border-gray-400
                border-solid
                rounded-lg
                overflow-hidden
                h-full
                pl-10
                focus:outline-none
                focus:outline-green-400
                focus:outline-2
                focus:outline-offset-0
                focus:border-none
                w-full
                `,
  });

  useMemo(() => {
    setValue(searchValue);
  }, [searchValue]);

  return useMemo(
    () => (
      <>
        <div
          className="W-full flex h-[50rem] items-center justify-center"
          style={{
            backgroundImage: "url(/assets/image/background.png)",
            backgroundRepeat: "no-repeat",
            backgroundSize: "cover",
            backgroundPosition: "center center",
          }}>
          <div
            className={
              "flex w-[40%] min-w-fit flex-col items-center gap-4 rounded-lg bg-white bg-opacity-90 p-4 shadow-2xl"
            }>
            <span className="text-2xl font-bold text-sky-900">
              Што ќе <span className="text-green-500">јадеме</span> денес
            </span>
            <div className="relative flex h-[2.5rem] w-full items-center">
              <div className="absolute flex h-[1.5rem] w-fit px-2">
                <Icon name="search" type="svg" />
              </div>
              <SearchInput />
            </div>
          </div>
        </div>
      </>
    ),
    [],
  );
};

export default memo(SearchFilter);
