import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientJsonpModule, HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BooksComponent } from './books/books.component';
import { BookComponent } from './book/book.component';
import { BookDetailsComponent } from './book-details/book-details.component';
import { FormsModule } from '@angular/forms';
import { BookService } from './services/book.service';
import { AuthorsComponent } from './authors/authors.component';
import { AuthorComponent } from './author/author.component';
import { AuthorDetailsComponent } from './author-details/author-details.component';
import { AuthorService } from './services/author.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LogElementComponent } from './log-element/log-element.component';
import { LogElementService } from './services/log-element.service';
import { IndexComponent } from './index/index.component';

@NgModule({
  declarations: [
    AppComponent,
    BooksComponent,
    BookComponent,
    BookDetailsComponent,
    AuthorsComponent,
    AuthorComponent,
    AuthorDetailsComponent,
    LogElementComponent,
    IndexComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    HttpClientJsonpModule,
    NgbModule
  ],
  providers: [BookService, AuthorService, LogElementService],
  bootstrap: [AppComponent]
})
export class AppModule { }
