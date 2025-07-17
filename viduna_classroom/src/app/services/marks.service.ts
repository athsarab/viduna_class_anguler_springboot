import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StudentMark, CreateMarkRequest } from '../models/marks.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class MarksService {
  private apiUrl = 'http://localhost:8080/api/marks';

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

  getAllMarks(): Observable<StudentMark[]> {
    return this.http.get<StudentMark[]>(this.apiUrl, { headers: this.getHeaders() });
  }

  getMarksByStudentId(studentId: number): Observable<StudentMark[]> {
    return this.http.get<StudentMark[]>(`${this.apiUrl}/student/${studentId}`, { headers: this.getHeaders() });
  }

  getMarksByClassId(classId: number): Observable<StudentMark[]> {
    return this.http.get<StudentMark[]>(`${this.apiUrl}/class/${classId}`, { headers: this.getHeaders() });
  }

  createMark(markData: CreateMarkRequest): Observable<StudentMark> {
    return this.http.post<StudentMark>(this.apiUrl, markData, { headers: this.getHeaders() });
  }

  updateMark(id: number, markData: CreateMarkRequest): Observable<StudentMark> {
    return this.http.put<StudentMark>(`${this.apiUrl}/${id}`, markData, { headers: this.getHeaders() });
  }

  deleteMark(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }
}
