import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { API_REFERENCE } from "src/app/api.constant";
import { map } from "rxjs/operators";

@Injectable()
export class ReferenceService {

    constructor(
        private http: HttpClient
    ) { }

    public getAllFamilyLinks(): Observable<string[]> {
        const url = `${API_REFERENCE}/familylinks`;
        return this.http.get<string[]>(url);
    }

    public postFamilyLink(name: string): Observable<string> {
        const url = `${API_REFERENCE}/familylinks`;
        return this.http.post<string>(url, name);
    }

    public deleteFamilyLink(name: string): Observable<any> {
        const url = `${API_REFERENCE}/familylinks/${name}`;
        return this.http.delete<any>(url);
    }

}