import { Component, OnInit } from '@angular/core';
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
  templateUrl: './game-list.component.html',
  styleUrl: './game-list.component.scss',
})
export class GameListComponent implements OnInit {
  categories: Category[];
  games: Game[];
  filterCategory: Category;
  filterTitle: string;

  constructor(
    private gameService: GameService,
    private categoryService: CategoryService,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.gameService.getGames().subscribe((games) => (this.games = games));

    this.categoryService
      .getCategories()
      .subscribe((categories) => (this.categories = categories));
  }

  onCleanFilter(): void {
    this.filterTitle = null;
    this.filterCategory = null;

    this.onSearch();
  }

  onSearch(): void {
    const title = this.filterTitle;
    const categoryId =
      this.filterCategory != null ? this.filterCategory.id : null;

    this.gameService
      .getGames(title, categoryId)
      .subscribe((games) => (this.games = games));
  }

  createGame() {
    const dialogRef = this.dialog.open(GameEditComponent, {
      data: {},
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.ngOnInit();
    });
  }

  editGame(game: Game) {
    const dialogRef = this.dialog.open(GameEditComponent, {
      data: { game: game },
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.onSearch();
    });
  }
}
