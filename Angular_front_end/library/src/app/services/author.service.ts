import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Author } from '../types/Author';
import { Book } from '../types/Book';

const httpOptions = {
  headers: new HttpHeaders({
    "Content-Type": "application/json",
  })
}

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  private authors: Author[] = [];
  private authorsUrl = "http://localhost:8080/authors";

  constructor(private http: HttpClient) {
    this.http.get(this.authorsUrl).subscribe((authors: Author[]) => {
      for (let author of authors)
        this.authors.push(author);
    })
    //console.log(this.authors);
  }

  public getAuthors() {
    return this.authors;
  }

  public getAuthor(id: number): Author | undefined {
    return this.authors.find(a => a.idAutore == id);
  }

  public getBooksByAuthor(id: number): Book[] | undefined {
    let books: Book[] = [];
    let url = this.authorsUrl + "/" + id + "/books";
    this.http.get(url, httpOptions).subscribe((bs: Book[]) => {
      for (let book of bs)
        books.push(book);
    });

    return books;
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

  public updateAuthor(author: Author) {
    let url = this.authorsUrl + "/" + author.idAutore;
    this.http.put<Author>(url, author, httpOptions)
      .pipe(catchError(this.handleError)).subscribe();

    let index = this.authors.findIndex(a => a.idAutore == author.idAutore);
    if (index != -1)
      this.authors[index] = author;
  }

  public deleteAuthor(author: Author) {
    let url = this.authorsUrl + "/" + author.idAutore;
    this.http.delete(url, httpOptions)
      .pipe(catchError(this.handleError)).subscribe();

    let index = this.authors.findIndex(a => a.idAutore == author.idAutore);
    if (index != -1)
      this.authors.splice(index, 1);
  }
}
