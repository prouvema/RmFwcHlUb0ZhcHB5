import { Injectable } from "@angular/core";
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from "@angular/common/http";
import { Router } from "@angular/router";
import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";
import { SnackBarService } from "../shared/snack-bar.service";

const UNAUTHORIZED_STATUS = 401;

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

    constructor(
        private router: Router,
        private snackBarService: SnackBarService
    ) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(
            catchError((response: any) => {
                if (response instanceof HttpErrorResponse) {
                    this.treatUnauthorized(response);
                    this.openSnackBar_WithService(response);
                    return throwError(response);
                }
            })
        )
    }

    private openSnackBar_WithService(response: HttpErrorResponse): any {
        let errorMessage = response.error.error ? response.error.error : response.error;
        this.snackBarService.openSnackBar(errorMessage, SnackBarService.ERROR_ACTION, 5000);
    }

    private treatUnauthorized(response: any): void {
        if (this.isUnauthorized(response)) {
            this.router.navigateByUrl('/unauthorized');
        }
    }

    private isUnauthorized(response: any): boolean {
        return response.status === UNAUTHORIZED_STATUS;
    }
}