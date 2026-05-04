import { Component, OnInit, inject, signal } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { GameEditComponent } from '../game-edit/game-edit.component';
import { GameService } from '../game.service';
import { Game } from '../model/Game';
import { CategoryService } from '../../category/category.service';
import { Category } from '../../category/model/Category';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { GameItemComponent } from './game-item/game-item.component';

@Component({
  selector: 'app-game-list',
  standalone: true,
  imports: [
    MatButtonModule,
    MatIconModule,
    MatTableModule,
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    GameItemComponent
  ],
  templateUrl: './game-list.page.html',
  styleUrl: './game-list.page.scss',
})
export class GameListPage implements OnInit {
  protected readonly categories = signal<Category[]>([]);
  protected readonly games = signal<Game[]>([]);
  protected readonly filterCategory = signal<Category | null>(null);
  protected readonly filterTitle = signal<string>('');

  protected readonly gameService = inject(GameService);
  protected readonly categoryService = inject(CategoryService);
  protected readonly dialog = inject(MatDialog);

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    this.gameService.getGames().subscribe((games) => this.games.set(games));
    this.categoryService.getCategories().subscribe((categories) => this.categories.set(categories));
  }

  onCleanFilter(): void {
    this.filterTitle.set('');
    this.filterCategory.set(null);
    this.onSearch();
  }

  onSearch(): void {
    const title = this.filterTitle();
    const categoryId = this.filterCategory() != null ? this.filterCategory().id : null;

    this.gameService
      .getGames(title, categoryId)
      .subscribe((games) => this.games.set(games));
  }

  createGame() {
    const dialogRef = this.dialog.open(GameEditComponent, {
      data: {},
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (!result) return;
      this.onSearch();
    });
  }

  editGame(game: Game) {
    const dialogRef = this.dialog.open(GameEditComponent, {
      data: { game: game },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (!result) return;
      this.onSearch();
    });
  }
}
