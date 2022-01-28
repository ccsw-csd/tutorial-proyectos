import { AuthorPage } from "./AuthorPage";

export const AUTHOR_DATA: AuthorPage = {
    content: [
        { id: 1, name: 'Klaus Teuber', nationality: 'Alemania' },
        { id: 2, name: 'Matt Leacock', nationality: 'Estados Unidos' },
        { id: 3, name: 'Keng Leong Yeo', nationality: 'Singapur' },
        { id: 4, name: 'Gil Hova', nationality: 'Estados Unidos'},
        { id: 5, name: 'Kelly Adams', nationality: 'Estados Unidos' },
        { id: 6, name: 'J. Alex Kavern', nationality: 'Estados Unidos' },
        { id: 7, name: 'Corey Young', nationality: 'Estados Unidos' },
    ],  
    pageable : {
        pageSize: 5,
        pageNumber: 0,
        sort: [
            {property: "id", direction: "ASC"}
        ]
    },
    totalElements: 7
}