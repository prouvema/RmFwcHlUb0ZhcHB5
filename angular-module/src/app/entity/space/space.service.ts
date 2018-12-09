import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { LightSpace } from "./space.model";
import { API_SPACE } from "src/app/api.constant";

@Injectable()
export class SpaceService {

    constructor(
        private http: HttpClient
    ) {}

    public getAllSpaces() {
        const url = `${API_SPACE}`;
        return this.http.get<LightSpace[]>(url);
    }
}