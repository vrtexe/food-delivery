import Pagination from "../../components/shared/Pagination/Pagination";

export enum PaginationActionEnum {
  Next = "next",
  Prev = "prev",
  Jump = "jump",
  Start = "start",
  End = "end",
  Total = "total",
  Update = "update",
}

export type PaginationAction =
  | { type: PaginationActionEnum.Next; payload?: never }
  | { type: PaginationActionEnum.Prev; payload?: never }
  | { type: PaginationActionEnum.Jump; payload: number }
  | { type: PaginationActionEnum.Start; payload?: never }
  | { type: PaginationActionEnum.End; payload?: never }
  | { type: PaginationActionEnum.Total; payload: number }
  | { type: PaginationActionEnum.Update; payload: Partial<Pagination> };

export interface Pagination {
  page: number;
  size: number;
  sort?: string[];
  total: number;
  elements?: number;
}

export interface Pageable {
  page: number;
  size: number;
  sort: string[];
}
