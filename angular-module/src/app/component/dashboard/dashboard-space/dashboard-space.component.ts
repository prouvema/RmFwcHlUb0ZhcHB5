import { Component, OnInit, OnDestroy } from "@angular/core";
import { SpaceSlot } from "src/app/entity/space-slot/space-slot.model";
import { SpaceSlotService } from "src/app/entity/space-slot/space-slot.service";
import { ActivatedRoute, Router } from "@angular/router";
import { MessageService } from "src/app/entity/message/message.service";
import { MessageFactory, Message } from "src/app/entity/message/message.model";
import { User } from "src/app/entity/user/user.model";
import { Observable, Subscription } from "rxjs";
import { UserService } from "src/app/entity/user/user.service";
import { Store } from "@ngrx/store";
import { AppState, selectUserState } from "src/app/app.states";

@Component({
    selector: 'body-dashboard-space',
    templateUrl: './dashboard-space.component.html',
    styleUrls: ['./dashboard-space.component.scss']
})
export class DashboardSpaceComponent implements OnInit, OnDestroy {

    public messageContent: string;
    public spaceSlot: SpaceSlot;
    public messages: Message[] = [];

    public user: User;

    public userState: Observable<any>;
    public userSubscription: Subscription;

    constructor(
        private spaceSlotService: SpaceSlotService,
        private messageService: MessageService,
        private route: ActivatedRoute,
        private router: Router,
        private userService: UserService,
        private store: Store<AppState>
    ) {
        this.userState = this.store.select(selectUserState);
    }

    ngOnInit() {
        this.userService.loadCurrentUser();
        this.userSubscription = this.userState.subscribe((state) => {
            this.user = state;
        });
        this.setSpaceSlot_WithService();
        this.setMessages_WithService();
    }

    ngOnDestroy(): void {
        this.userSubscription.unsubscribe();
    }

    public sendMessage(): void {
        let spaceId = +this.route.snapshot.paramMap.get('spaceId');
        const message: Message = MessageFactory.createNewMessage(spaceId, this.messageContent, this.user.username);
        this.messageService.post(message).subscribe(message => this.messages.push(message));
    }

    public navigateToUrgenceContacts(): void {
        const url = `/home/space/${this.spaceSlot.space.id}/slot/${this.spaceSlot.id}/urgencecontacts`;
        this.router.navigateByUrl(url);
    }

    private setMessages_WithService(): void {
        let spaceId = +this.route.snapshot.paramMap.get('spaceId');
        this.messageService.getPage(spaceId, 0, 15).subscribe(messages => this.messages = messages);
    }

    private setSpaceSlot_WithService(): void {
        let slotId = +this.route.snapshot.paramMap.get('slotId');
        this.spaceSlotService.getSpaceSlotById(slotId).subscribe(slot => this.spaceSlot = slot);
    }
}
