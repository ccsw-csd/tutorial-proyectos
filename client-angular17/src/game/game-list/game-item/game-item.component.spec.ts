import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameItemComponent } from './game-item.component';

describe('GameItemComponent', () => {
  let component: GameItemComponent;
  let fixture: ComponentFixture<GameItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GameItemComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GameItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
