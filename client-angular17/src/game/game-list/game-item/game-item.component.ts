import { Component, OnInit, Input } from '@angular/core';
import { Game } from '../../model/Game';
import {MatCardModule} from '@angular/material/card';
@Component({
  selector: 'app-game-item',
  standalone: true,
  imports: [MatCardModule],
  templateUrl: './game-item.component.html',
  styleUrl: './game-item.component.scss'
})
export class GameItemComponent {
  @Input() game: Game;
}
