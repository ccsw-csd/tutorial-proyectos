import { Pageable } from './Pageable';

export class PaginatedData<TData> {
    content: TData[];
    pageable: Pageable;
    totalElements: number;
}
