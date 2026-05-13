import { SortPage } from "./SortPage";

export interface Pageable {
    pageNumber: number;
    pageSize: number;
    sort: SortPage[];
}