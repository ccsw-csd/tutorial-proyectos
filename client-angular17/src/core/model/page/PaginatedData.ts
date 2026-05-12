import { Pageable } from './Pageable';

export interface PaginatedData<TData> {
    content: TData[];
    pageable: Pageable;
    totalElements: number;
}
