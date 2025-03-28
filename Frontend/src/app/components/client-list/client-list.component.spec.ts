import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatModule } from '../../mat.module';
import { ClientListComponent } from './client-list.component';
import { ClientService } from '../../services/client.service';
import { of } from 'rxjs';

describe('ClientListComponent', () => {
  let component: ClientListComponent;
  let fixture: ComponentFixture<ClientListComponent>;
  let clientService: jasmine.SpyObj<ClientService>;

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('ClientService', ['getClients', 'exportToCsv']);
    spy.getClients.and.returnValue(of([]));

    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        MatModule
      ],
      declarations: [ ClientListComponent ],
      providers: [
        { provide: ClientService, useValue: spy }
      ]
    }).compileComponents();

    clientService = TestBed.inject(ClientService) as jasmine.SpyObj<ClientService>;
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load clients on init', () => {
    expect(clientService.getClients).toHaveBeenCalled();
  });

  it('should perform search with form values', () => {
    const searchValues = {
      sharedKey: 'test',
      businessId: 'bid',
      email: 'test@test.com'
    };

    component.searchForm.patchValue(searchValues);
    component.onSearch();

    expect(clientService.getClients).toHaveBeenCalledWith(searchValues);
  });
}); 