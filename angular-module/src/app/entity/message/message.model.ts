export interface Message {
    id: number;
    content: string;
    username: string;
    firstname: string;
    lastname: string;
    spaceId: number;
    createTime: string;
}

export class MessageFactory {
    public static createNewMessage(spaceId: number, content: string, username: string): Message {
        return {
            id: 0,
            spaceId: spaceId,
            content: content,
            username: username,
            firstname: null,
            lastname: null,
            createTime: null
        };
    }

    public static createEmptyMessage(): Message {
        return {
            id: 0,
            spaceId: 0,
            content: null,
            username: null,
            firstname: null,
            lastname: null,
            createTime: null
        };
    }
}