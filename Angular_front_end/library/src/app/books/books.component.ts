import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookService } from '../services/book.service';
import { Book } from '../types/Book';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {
  private bookService: BookService;
  private router: Router;
  public books = [];

  constructor(bookService: BookService, router: Router) {
    this.bookService = bookService;
    this.router = router;
  }

  ngOnInit(): void {
    this.books = this.bookService.getBooks();
  }

  public infoBook(book: Book) {
    this.router.navigate(["books", book.isbn]);
  }
}
