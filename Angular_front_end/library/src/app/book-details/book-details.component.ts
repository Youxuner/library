import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../services/book.service';
import { Book } from '../types/Book';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {
  private service: BookService;
  private actRouter: ActivatedRoute;
  private router: Router;
  public _book: Book;

  public set book(book: Book) {
    let b = Object.assign({}, book);
    this._book = b;
  }

  public get book(): Book {
    return this._book;
  }

  constructor(bookService: BookService, router: Router, actRouter: ActivatedRoute) {
    this.service = bookService;
    this.actRouter = actRouter;
    this.router = router;
  }

  ngOnInit(): void {
    this.actRouter.params.subscribe((params) => {
      this.book = this.service.getBook(params.isbn);
    })
  }

  public updateBook(f: NgForm) {
    if (f.invalid)
      return;
    this.service.updateBook(this.book);
    this.router.navigate(["books"]);
  }

  public deleteBook(event: Event) {
    this.service.deleteBook(this.book);
    this.router.navigate(["books"]);
  }

}
