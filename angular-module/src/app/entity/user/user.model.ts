export interface User {
    username: string | null;
    firstname: string | null;
    lastname: string | null;
    roles: string[];
    authorities: string[];
}