import { Reducer } from "react";
import { Pagination, PaginationAction, PaginationActionEnum } from "../model/Pagination";

export const paginationReducer: Reducer<Pagination, PaginationAction> = (state, action): Pagination => {
  const { type, payload } = action;

  switch (type) {
    case PaginationActionEnum.Next: {
      if (state.page + 2 > state.total) {
        return state;
      }

      return {
        ...state,
        page: state.page + 1,
      };
    }

    case PaginationActionEnum.Prev: {
      if (state.page - 1 < 0) {
        return state;
      }

      return {
        ...state,
        page: state.page && state.page - 1,
      };
    }

    case PaginationActionEnum.Jump: {
      return {
        ...state,
        page: payload,
      };
    }

    case PaginationActionEnum.Start: {
      return {
        ...state,
        page: 0,
      };
    }

    case PaginationActionEnum.End: {
      return {
        ...state,
        page: state.total - 1,
      };
    }

    case PaginationActionEnum.Total: {
      return {
        ...state,
        total: state.total,
      };
    }

    case PaginationActionEnum.Update: {
      return {
        ...state,
        ...payload,
      };
    }

    default: {
      return state;
    }
  }
};
