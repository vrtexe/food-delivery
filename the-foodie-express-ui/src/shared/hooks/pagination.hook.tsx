import { useReducer } from "react";
import { Pagination, PaginationActionEnum } from "../model/Pagination";
import { paginationReducer } from "../reducer/Pagination";

export const initialPagination: Pagination = {
  page: 0,
  size: 9,
  total: 10,
  sort: [],
};

export interface PaginationControl {
  pagination: Pagination;
  next: () => void;
  prev: () => void;
  jump: (page: number) => void;
  start: () => void;
  end: () => void;
  total: (total: number) => void;
  update: (pagination: Partial<Pagination>) => void;
}

export const usePagination = (initial?: Partial<Pagination>): PaginationControl => {
  const [pagination, dispatch] = useReducer(paginationReducer, {
    ...initialPagination,
    ...initial,
  });

  const next = () => {
    dispatch({ type: PaginationActionEnum.Next });
  };

  const prev = () => {
    dispatch({ type: PaginationActionEnum.Prev });
  };

  const jump = (page: number) => {
    dispatch({ type: PaginationActionEnum.Jump, payload: page });
  };

  const start = () => {
    dispatch({ type: PaginationActionEnum.Start });
  };

  const end = () => {
    dispatch({ type: PaginationActionEnum.End });
  };

  const total = (total: number) => {
    dispatch({ type: PaginationActionEnum.Total, payload: total });
  };

  const update = (pagination: Partial<Pagination>) => {
    dispatch({ type: PaginationActionEnum.Update, payload: pagination });
  };

  return {
    pagination,
    next,
    prev,
    jump,
    start,
    end,
    total,
    update,
  };
};
