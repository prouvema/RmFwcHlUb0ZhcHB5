import { Component, OnInit, OnDestroy } from "@angular/core";
import { ReferenceService } from "src/app/entity/reference/reference.service";

@Component({
    selector: 'references',
    templateUrl: './references.component.html',
    styleUrls: ['./references.component.scss']
})
export class ReferencesComponent implements OnInit, OnDestroy {

    public familyLinks: string[] = [];
    public familyLink: string;

    constructor(
        private referenceService: ReferenceService
    ) { }

    ngOnInit(): void {
        this.referenceService.getAllFamilyLinks().subscribe(familyLinks => this.familyLinks = familyLinks);
    }

    ngOnDestroy(): void {

    }

    public onSubmitFamilyLink(): void {
        this.referenceService.postFamilyLink(this.familyLink).subscribe(
            link => this.familyLinks.push(link)
        );
    }

    public onDeleteFamilyLink(familyLink: string): void {
        this.referenceService.deleteFamilyLink(familyLink).subscribe(result => {
            const index = this.familyLinks.indexOf(familyLink);
            this.familyLinks.splice(index, 1);
        });
    }

}