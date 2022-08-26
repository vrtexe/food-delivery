import { Context, useContext, useEffect, useState } from "react";
import { Pagination } from "../model/Pagination";
import { AxiosResponse } from "axios";

export interface Paged<T> {
  content: T[];
  totalPages: number;
  number?: number;
  first?: boolean;
  last?: boolean;
  elements?: number;
}

export function useFetch<T, F>(
  fetch: (filter: F, pagination: Pagination) => Promise<AxiosResponse<Paged<T>>>,
  pagination: Pagination,
  filterContext: Context<F>,
  update: (pagination: Partial<Pagination>) => void,
): Array<T> | undefined {
  let loading = false;

  const filter = useContext(filterContext);
  const [items, setItems] = useState<Array<T> | undefined>([]);

  useEffect(() => {
    if (!loading) {
      loading = true;
      fetch(filter, pagination).then((response) => {
        if (response.status !== 200) {
          return;
        }

        update({
          elements: response.data.elements,
          total: response.data.totalPages,
        });

        setItems(response.data.content);
        loading = false;
      });
    }
  }, [filter, pagination.page]);

  return items;
}

export function useSingleFetch<T, F>(fetch: (id: F) => Promise<AxiosResponse<T>>, id: F | undefined) {
  const [item, setItem] = useState<T | undefined>(undefined);

  useEffect(() => {
    if (!id) {
      return;
    }

    fetch(id).then((response) => {
      if (response.status !== 200) {
        return;
      }

      setItem(response.data);
    });
    setItem(undefined);
  }, [id]);

  return item;
}
