import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthorDetailsComponent } from './author-details/author-details.component';
import { AuthorsComponent } from './authors/authors.component';
import { BookDetailsComponent } from './book-details/book-details.component';

import { BooksComponent } from './books/books.component';
import { IndexComponent } from './index/index.component';
import { LogElementComponent } from './log-element/log-element.component';

const routes: Routes = [
  {
    path: "books",
    pathMatch: "full",
    component: BooksComponent
  },
  {
    path: "books/:isbn",
    pathMatch: "full",
    component: BookDetailsComponent
  },
  {
    path: "authors",
    pathMatch: "full",
    component: AuthorsComponent
  },
  {
    path: "authors/:idAuthor",
    pathMatch: "full",
    component: AuthorDetailsComponent
  },
  {
    path: "losa/stat",
    pathMatch: "full",
    component: LogElementComponent
  },
  {
    path: "",
    pathMatch: "full",
    component: IndexComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
