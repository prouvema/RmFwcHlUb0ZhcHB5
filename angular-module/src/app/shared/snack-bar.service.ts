import { Injectable } from "@angular/core";
import { MatSnackBar } from "@angular/material";

@Injectable()
export class SnackBarService {

    public static DELETE_ACTION = 'Supprimé';
    public static ADDED_ACTION = 'Ajouté';
    public static SAVED_ACTION = 'Sauvé';
    public static ERROR_ACTION = 'Error';

    private static DEFAULT_DURATION = 3000;

    constructor(
        private snackBar: MatSnackBar
    ) { }

    public openSnackBar(message: string, action: string, duration?: number): void {
        this.snackBar.open(message, action, {
            duration: duration ? duration: SnackBarService.DEFAULT_DURATION,
        });
    }
}