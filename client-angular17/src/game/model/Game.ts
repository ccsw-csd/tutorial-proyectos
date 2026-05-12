import { Author } from "../../author/model/Author";
import { Category } from "../../category/model/Category";

export interface Game {
    id: number;
    title: string;
    age: number;
    category: Category;
    author: Author;
}