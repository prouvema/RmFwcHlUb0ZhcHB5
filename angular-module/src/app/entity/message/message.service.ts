import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { API_MESSAGE } from "src/app/api.constant";
import { Message } from "./message.model";

@Injectable()
export class MessageService {

    constructor(
        private http: HttpClient
    ) { }

    public getPage(spaceId: number, page: number, size: number): Observable<Message[]> {
        const url = `${API_MESSAGE}/space/${spaceId}/page/${page}/size/${size}`;
        return this.http.get<Message[]>(url);
    }
    
    public post(message: Message): Observable<Message> {
        const url = `${API_MESSAGE}`;
        return this.http.post<Message>(url, message);
    }
}