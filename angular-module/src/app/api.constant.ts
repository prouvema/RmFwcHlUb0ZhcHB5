import { environment } from "src/environments/environment";

export const API_AUTH = environment.apiEndpoint + '/oauth';
export const API_USER = environment.apiEndpoint + '/api/users';
export const API_SPACE = environment.apiEndpoint + '/api/spaces';
export const API_SPACE_SLOT = environment.apiEndpoint + '/api/spaceslots';
export const API_REFERENCE = environment.apiEndpoint + '/api/references';
export const API_ROLE = environment.apiEndpoint + '/api/roles';
export const API_MESSAGE = environment.apiEndpoint + '/api/spacemessages';