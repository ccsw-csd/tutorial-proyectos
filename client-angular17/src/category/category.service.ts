import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Category } from './model/Category';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  constructor(private http: HttpClient) {}

  private baseUrl = 'http://localhost:8080/category';

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.baseUrl);
  }

  saveCategory(category: Category): Observable<Category> {
    const { id } = category;
    const url = id ? `${this.baseUrl}/${id}` : this.baseUrl;
    return this.http.put<Category>(url, category);
  }

  deleteCategory(idCategory : number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${idCategory}`);
  }  
}
