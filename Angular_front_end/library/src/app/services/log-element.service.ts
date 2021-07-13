import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { LogElement } from '../types/LogElement';

const httpOptions = {
  headers: new HttpHeaders({
    "Content-Type": "application/json",
  })
}

@Injectable({
  providedIn: 'root'
})
export class LogElementService {

  private logUrl = "http://localhost:8080/losa/stat";
  constructor(private http: HttpClient) { }

  public statisticheNelPeriodo(status: string): LogElement[] | undefined {
    let logs: LogElement[] = [];
    let url = this.logUrl + "/" + status;
    this.http.get(url, httpOptions).pipe(catchError(this.handleError))
      .subscribe((ls: LogElement[]) => {
      for(let log of ls)
        logs.push(log);
    });

    return logs;
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
}
