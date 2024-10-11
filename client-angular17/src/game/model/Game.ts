import { Author } from "../../author/model/Author";
import { Category } from "../../category/model/Category";

export class Game {
    id: number;
    title: string;
    age: number;
    category: Category;
    author: Author;
}