import { Component, OnInit, Input } from '@angular/core';
import { Game } from '../../model/Game';

@Component({
    selector: 'app-game-item',
    templateUrl: './game-item.component.html',
    styleUrls: ['./game-item.component.scss']
})
export class GameItemComponent implements OnInit {

    @Input() game: Game;

    constructor() { }

    ngOnInit(): void {
    }

}