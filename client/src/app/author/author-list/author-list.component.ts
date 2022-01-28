import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { DialogConfirmationComponent } from 'src/app/core/dialog-confirmation/dialog-confirmation.component';
import { Pageable } from 'src/app/core/model/page/Pageable';
import { AuthorEditComponent } from '../author-edit/author-edit.component';
import { AuthorService } from '../author.service';
import { Author } from '../model/Author';

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrls: ['./author-list.component.scss']
})
export class AuthorListComponent implements OnInit {

  pageNumber: number = 0;
  pageSize: number = 5;
  totalElements: number = 0;

  dataSource = new MatTableDataSource<Author>();
  displayedColumns: string[] = ['id', 'name', 'nationality', 'action'];

  constructor(
      private authorService: AuthorService,
      public dialog: MatDialog,
  ) { }

  ngOnInit(): void {
      this.loadPage();
  }

  loadPage(event?: PageEvent) {

      let pageable : Pageable =  {
          pageNumber: this.pageNumber,
          pageSize: this.pageSize,
          sort: [{
          property: 'id',
          direction: 'ASC'
          }]
      }

      if (event != null) {
          pageable.pageSize = event.pageSize
          pageable.pageNumber = event.pageIndex;
      }

      this.authorService.getAuthors(pageable).subscribe(data => {
          this.dataSource.data = data.content;
          this.pageNumber = data.pageable.pageNumber;
          this.pageSize = data.pageable.pageSize;
          this.totalElements = data.totalElements;
      }
      );

  }  

  createAuthor() {      
      const dialogRef = this.dialog.open(AuthorEditComponent, {
          data: {}
      });

      dialogRef.afterClosed().subscribe(result => {
          this.ngOnInit();
      });      
  }  

  editAuthor(author: Author) {    
      const dialogRef = this.dialog.open(AuthorEditComponent, {
          data: { author: author }
      });

      dialogRef.afterClosed().subscribe(result => {
          this.ngOnInit();
      });    
  }

  deleteAuthor(author: Author) {    
      const dialogRef = this.dialog.open(DialogConfirmationComponent, {
          data: { title: "Eliminar autor", description: "Atención si borra el autor se perderán sus datos.<br> ¿Desea eliminar el autor?" }
      });

      dialogRef.afterClosed().subscribe(result => {
          if (result) {
              this.authorService.deleteAuthor(author.id).subscribe(result =>  {
                  this.ngOnInit();
              }); 
          }
      });
  }  
}