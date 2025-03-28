import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ClientService } from '../../services/client.service';
import { Client } from '../../models/client.model';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.scss']
})
export class ClientListComponent implements OnInit {
  displayedColumns: string[] = ['sharedKey', 'businessId', 'email', 'phone'];
  dataSource: MatTableDataSource<Client>;
  searchForm: FormGroup;
  showAdvancedSearch = false;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private clientService: ClientService,
    private fb: FormBuilder,
    private router: Router
  ) {
    this.searchForm = this.fb.group({
      sharedKey: [''],
      businessId: [''],
      email: ['']
    });

    this.dataSource = new MatTableDataSource();
  }

  ngOnInit(): void {
    this.loadClients();
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  loadClients(): void {
    this.clientService.getClients().subscribe(
      clients => {
        this.dataSource.data = clients;
      }
    );
  }

  onSearch(): void {
    const filters = this.searchForm.value;
    this.clientService.getClients(filters).subscribe(
      clients => {
        this.dataSource.data = clients;
      }
    );
  }

  exportToCsv(): void {
    this.clientService.exportToCsv(this.dataSource.data);
  }

  createNewClient(): void {
    this.router.navigate(['/clients/new']);
  }
} 