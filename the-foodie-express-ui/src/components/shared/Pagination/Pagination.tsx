import React, { MouseEventHandler, PropsWithChildren, useRef } from "react";
import { PaginationControl } from "../../../shared/hooks/pagination.hook";

const Pagination: React.FC<PaginationControl> = ({ pagination, next, prev, end, start, jump }) => {
  const scrollRef = useRef<HTMLDivElement>(null);

  let mouseDown = false;

  const handleMouseMove: MouseEventHandler = (e) => {
    if (!mouseDown) {
      return;
    }

    scrollRef.current?.scrollBy({ left: -e.movementX });
  };

  const NumberField: React.FC<PropsWithChildren<{ active: boolean; onClick: MouseEventHandler<HTMLDivElement> }>> = ({
    children,
    active,
    onClick,
  }) => (
    <div
      key={`div-${children}`}
      onClick={onClick}
      className={`${
        active ? "cursor-not-allowed bg-green-100 " : "bg-gray-100 hover:border-green-400 hover:bg-gray-200"
      } h-full cursor-pointer select-none rounded-md border border-solid border-gray-300  px-4 py-2 shadow-md `}>
      {children}
    </div>
  );

  const generateNumbers = () => {
    return [...Array(pagination.total)].map((_, pageNumber) => (
      <NumberField key={pageNumber} active={pageNumber === pagination.page} onClick={() => jump(pageNumber)}>
        {pageNumber + 1}
      </NumberField>
    ));
  };

  return (
    <>
      <div className="flex justify-center gap-4 py-4  px-4 xl:px-0">
        <div key={-2} className="py-1">
          <NumberField onClick={() => start()} active={false}>
            &lt;&lt;
          </NumberField>
        </div>
        <div key={-1} className="py-1">
          <NumberField onClick={() => prev()} active={false}>
            &lt;
          </NumberField>
        </div>
        <div
          ref={scrollRef}
          onMouseDown={() => (mouseDown = true)}
          onMouseUp={() => (mouseDown = false)}
          onMouseLeave={() => (mouseDown = false)}
          onMouseMove={handleMouseMove}
          className="flex gap-4 overflow-hidden py-1">
          {generateNumbers()}
        </div>
        <div className="py-1">
          <NumberField key={pagination.total + 1} active={false} onClick={() => next()}>
            &gt;
          </NumberField>
        </div>
        <div className="py-1">
          <NumberField key={pagination.total + 2} active={false} onClick={() => end()}>
            &gt;&gt;
          </NumberField>
        </div>
      </div>
    </>
  );
};

export default Pagination;
