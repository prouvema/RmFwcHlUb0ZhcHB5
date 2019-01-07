import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { API_ROLE } from "../../api.constant";
import { SpaceRole } from "./role.model";

@Injectable()
export class RoleService {

    constructor(
        private http: HttpClient
    ) { }

    public getAllSpaceRoles_ForSelect(): Observable<string[]> {
        const url = `${API_ROLE}/space/select`;
        return this.http.get<string[]>(url);
    }

    public getAllSpaceRoles(): Observable<SpaceRole[]> {
        const url = `${API_ROLE}/space`;
        return this.http.get<SpaceRole[]>(url);
    }

    public postSpaceRole(spaceRole: SpaceRole): Observable<SpaceRole> {
        const url = `${API_ROLE}/space`;
        return this.http.post<SpaceRole>(url, spaceRole);
    }

    public patchSpaceRole(spaceRole: SpaceRole): Observable<SpaceRole> {
        const url = `${API_ROLE}/space`;
        return this.http.patch<SpaceRole>(url, spaceRole);
    }

    public deleteSpaceRole(id: number): Observable<any> {
        const url = `${API_ROLE}/space/${id}`;
        return this.http.delete(url);
    }

    ////////////////////////////////////////////////////////////////////////////////

    public getAllSpaceAccesses_ForSelect(): Observable<string[]> {
        const url = `${API_ROLE}/access`;
        return this.http.get<string[]>(url);
    }
}