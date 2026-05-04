import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryListPage } from './category-list.page';

describe('CategoryListPage', () => {
  let component: CategoryListPage;
  let fixture: ComponentFixture<CategoryListPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoryListPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CategoryListPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
