import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from '../types/Book';

import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    "Content-Type": "application/json",
  })
}

@Injectable({providedIn: 'root'})
export class BookService {

  private books: Book[] = [];
  private http: HttpClient;
  private booksUrl = "http://localhost:8080/books";

  constructor(http: HttpClient) {
    this.http = http;

    this.http.get(this.booksUrl).subscribe((books: Book[]) => {
      for (let book of books)
        this.books.push(book);
    });
    //console.log(this.books);
  }

  public getBooks() {
    return this.books;
  }

  public getBook(isbn: number): Book | undefined {
    return this.books.find(b => b.isbn == isbn);
  }

  public updateBook(book: Book) {
    let url = this.booksUrl + "/" + book.isbn;
    this.http.put<Book>(url, book, httpOptions)
      .pipe(catchError(this.handleError)).subscribe();

    let index = this.books.findIndex(b => b.isbn == book.isbn);
    if (index != -1)
      this.books[index] = book;
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // Return an observable with a user-facing error message.
    return throwError(
      'Something bad happened; please try again later.');
  }

  public deleteBook(book: Book) {
    //console.log(book);
    let url = this.booksUrl + "/" + book.isbn;
    this.http.delete(url, httpOptions)
      .pipe(catchError(this.handleError)).subscribe();

    let index = this.books.findIndex(b => b.isbn == book.isbn);
    if (index != -1)
      this.books.splice(index, 1);
  }
}
