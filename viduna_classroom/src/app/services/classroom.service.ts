import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ClassRoom, CreateClassRequest } from '../models/classroom.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ClassroomService {
  private apiUrl = 'http://localhost:8080/api/classes';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  private getHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
  }

  getAllClasses(): Observable<ClassRoom[]> {
    return this.http.get<ClassRoom[]>(this.apiUrl, { headers: this.getHeaders() });
  }

  getClassById(id: number): Observable<ClassRoom> {
    return this.http.get<ClassRoom>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }

  createClass(classData: CreateClassRequest): Observable<ClassRoom> {
    return this.http.post<ClassRoom>(this.apiUrl, classData, { headers: this.getHeaders() });
  }

  updateClass(id: number, classData: CreateClassRequest): Observable<ClassRoom> {
    return this.http.put<ClassRoom>(`${this.apiUrl}/${id}`, classData, { headers: this.getHeaders() });
  }

  deleteClass(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }

  uploadClassImage(classId: number, file: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);
    
    const token = this.authService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.post(`${this.apiUrl}/${classId}/upload-image`, formData, { headers });
  }

  uploadTeacherImage(classId: number, file: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);
    
    const token = this.authService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.post(`${this.apiUrl}/${classId}/upload-teacher-image`, formData, { headers });
  }
}
