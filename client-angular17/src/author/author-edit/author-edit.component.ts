import { Component, inject, OnInit, signal } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthorService } from '../author.service';
import { Author } from '../model/Author';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { validateFields } from '../../core/helpers/validation.helper';

@Component({
  selector: 'app-author-edit',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule ],
  templateUrl: './author-edit.component.html',
  styleUrl: './author-edit.component.scss',
})
export class AuthorEditComponent implements OnInit {
  protected readonly authorService = inject(AuthorService);
  protected readonly dialogRef = inject(MatDialogRef<AuthorEditComponent>);
  protected readonly data = inject(MAT_DIALOG_DATA);

  protected readonly id = signal<number | null>(null);
  protected readonly name = signal<string | null>(null);
  protected readonly nationality = signal<string | null>(null);

  ngOnInit(): void {
    this.loadFormData(this.data.author ?? null);
  }

  loadFormData(initialData: Author | null): void {
    this.id.set(initialData?.id ?? null);
    this.name.set(initialData?.name ?? null);
    this.nationality.set(initialData?.nationality ?? null);
  }

  onSave() {
    const id = this.id();
    const name = this.name();
    const nationality = this.nationality();

    const requiredFields = ["name", "nationality"] as const
    const data = { name, nationality }

    if (!validateFields(data, requiredFields)) {
      return;
    }

    const author = {
      id,
      name,
      nationality,
    } as Author;
    this.authorService.saveAuthor(author).subscribe(() => {
      this.dialogRef.close(true);
    });
  }

  onClose() {
    this.dialogRef.close(false);
  }
}
