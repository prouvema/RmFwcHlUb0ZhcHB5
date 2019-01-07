import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { API_SPACE_SLOT } from "src/app/api.constant";
import { SpaceSlot } from "./space-slot.model";
import { Observable } from "rxjs";

@Injectable()
export class SpaceSlotService {

    constructor(
        private http: HttpClient
    ) { }

    public getSpaceSlotById(id: number): Observable<SpaceSlot> {
        const url = `${API_SPACE_SLOT}/${id}`;
        return this.http.get<SpaceSlot>(url);
    }
}