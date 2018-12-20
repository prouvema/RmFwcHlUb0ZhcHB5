import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { LightSpace, Space } from "./space.model";
import { API_SPACE } from "src/app/api.constant";
import { Observable } from "rxjs";

@Injectable()
export class SpaceService {

    constructor(
        private http: HttpClient
    ) {}

    public getAllSpaces(): Observable<LightSpace[]> {
        const url = `${API_SPACE}`;
        return this.http.get<LightSpace[]>(url);
    }

    public getSpace(id: number): Observable<Space> {
        const url = `${API_SPACE}/${id}`;
        return this.http.get<Space>(url);
    }

    public postSpace(space: Space): Observable<Space> {
        const url = `${API_SPACE}`;
        return this.http.post<Space>(url, space);
    }

    patchSpace(space: Space): any {
        const url = `${API_SPACE}`;
        return this.http.patch<Space>(url, space);
    }
}