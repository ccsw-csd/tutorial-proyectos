import { Component, inject, OnInit, signal } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { GameService } from '../game.service';
import { Game } from '../model/Game';
import { AuthorService } from '../../author/author.service';
import { Author } from '../../author/model/Author';
import { CategoryService } from '../../category/category.service';
import { Category } from '../../category/model/Category';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-game-edit',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatSelectModule ],
  templateUrl: './game-edit.component.html',
  styleUrl: './game-edit.component.scss',
})
export class GameEditComponent implements OnInit {
  protected readonly id = signal<number | null>(null);
  protected readonly title = signal<string | null>(null);
  protected readonly age = signal<number | null>(null);
  protected readonly categoryId = signal<number | null>(null);
  protected readonly authorId = signal<number | null>(null);
  protected readonly categories = signal<Category[]>([]);
  protected readonly authors = signal<Author[]>([]);

  protected readonly dialogRef = inject(MatDialogRef<GameEditComponent>);
  protected readonly data = inject(MAT_DIALOG_DATA);
  protected readonly gameService = inject(GameService);
  protected readonly categoryService = inject(CategoryService);
  protected readonly authorService = inject(AuthorService);

  ngOnInit(): void {
    this.loadFormData(this.data.game ?? null);
  }

  loadFormData(initialData: Game | null): void {
    this.id.set(initialData?.id ?? null);
    this.title.set(initialData?.title ?? null);
    this.age.set(initialData?.age ?? null);

    this.categoryId.set(initialData?.category?.id ?? null);
    this.categoryService.getCategories().subscribe((cats) => {
      this.categories.set(cats);
    });

    this.authorId.set(initialData?.author?.id ?? null);
    this.authorService.getAllAuthors().subscribe((auts) => {
      this.authors.set(auts);
    });
  }

  onSave() {
    const game: Game = {
      id: this.id(),
      title: this.title(),
      age: this.age(),
      category: this.categories().find(c => c.id === this.categoryId()) ?? null,
      author: this.authors().find(a => a.id === this.authorId()) ?? null,
    };
    this.gameService.saveGame(game).subscribe(() => {
      this.dialogRef.close(true);
    });
  }

  onClose() {
    this.dialogRef.close(false);
  }
}
