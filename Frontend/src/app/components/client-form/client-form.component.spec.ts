import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatModule } from '../../mat.module';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ClientFormComponent } from './client-form.component';
import { ClientService } from '../../services/client.service';
import { of, throwError } from 'rxjs';

describe('ClientFormComponent', () => {
  let component: ClientFormComponent;
  let fixture: ComponentFixture<ClientFormComponent>;
  let clientService: jasmine.SpyObj<ClientService>;
  let snackBar: jasmine.SpyObj<MatSnackBar>;

  beforeEach(async () => {
    const clientServiceSpy = jasmine.createSpyObj('ClientService', ['createClient']);
    const snackBarSpy = jasmine.createSpyObj('MatSnackBar', ['open']);

    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        MatModule
      ],
      declarations: [ ClientFormComponent ],
      providers: [
        { provide: ClientService, useValue: clientServiceSpy },
        { provide: MatSnackBar, useValue: snackBarSpy }
      ]
    }).compileComponents();

    clientService = TestBed.inject(ClientService) as jasmine.SpyObj<ClientService>;
    snackBar = TestBed.inject(MatSnackBar) as jasmine.SpyObj<MatSnackBar>;
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should validate required fields', () => {
    expect(component.clientForm.valid).toBeFalsy();
    
    component.clientForm.patchValue({
      sharedKey: 'key1',
      businessId: 'bid1',
      email: 'test@test.com',
      phone: '1234567890'
    });

    expect(component.clientForm.valid).toBeTruthy();
  });

  it('should validate email format', () => {
    const emailControl = component.clientForm.get('email');
    
    emailControl?.setValue('invalid-email');
    expect(emailControl?.errors?.email).toBeTruthy();

    emailControl?.setValue('valid@email.com');
    expect(emailControl?.errors).toBeFalsy();
  });

  it('should validate phone number format', () => {
    const phoneControl = component.clientForm.get('phone');
    
    phoneControl?.setValue('abc123');
    expect(phoneControl?.errors?.pattern).toBeTruthy();

    phoneControl?.setValue('1234567890');
    expect(phoneControl?.errors).toBeFalsy();
  });

  it('should submit form when valid', () => {
    const mockClient = {
      sharedKey: 'key1',
      businessId: 'bid1',
      email: 'test@test.com',
      phone: '1234567890'
    };

    clientService.createClient.and.returnValue(of(mockClient));
    
    component.clientForm.patchValue(mockClient);
    component.onSubmit();

    expect(clientService.createClient).toHaveBeenCalledWith(mockClient);
    expect(snackBar.open).toHaveBeenCalledWith(
      'Client created successfully',
      'Close',
      { duration: 3000 }
    );
  });

  it('should handle error on submit', () => {
    clientService.createClient.and.returnValue(throwError('Error'));
    
    component.clientForm.patchValue({
      sharedKey: 'key1',
      businessId: 'bid1',
      email: 'test@test.com',
      phone: '1234567890'
    });
    component.onSubmit();

    expect(snackBar.open).toHaveBeenCalledWith(
      'Error creating client',
      'Close',
      { duration: 3000 }
    );
  });
}); 