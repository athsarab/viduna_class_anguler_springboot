import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { ClassroomService } from '../services/classroom.service';
import { MarksService } from '../services/marks.service';
import { User } from '../models/user.model';
import { ClassRoom } from '../models/classroom.model';
import { StudentMark } from '../models/marks.model';

@Component({
  selector: 'app-student-dashboard',
  imports: [CommonModule],
  templateUrl: './student-dashboard.component.html',
  styleUrl: './student-dashboard.component.css'
})
export class StudentDashboardComponent implements OnInit {
  currentUser: User | null = null;
  classes: ClassRoom[] = [];
  marks: StudentMark[] = [];
  activeTab = 'classes';
  isLoading = false;

  constructor(
    private authService: AuthService,
    private classroomService: ClassroomService,
    private marksService: MarksService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUser();
    
    if (!this.currentUser || this.currentUser.role !== 'STUDENT') {
      this.router.navigate(['/']);
      return;
    }

    this.loadClasses();
  }

  setActiveTab(tab: string): void {
    this.activeTab = tab;
    
    if (tab === 'classes') {
      this.loadClasses();
    } else if (tab === 'marks') {
      this.loadMarks();
    }
  }

  loadClasses(): void {
    this.isLoading = true;
    this.classroomService.getAllClasses().subscribe({
      next: (classes) => {
        this.classes = classes;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading classes:', error);
        this.isLoading = false;
      }
    });
  }

  loadMarks(): void {
    if (!this.currentUser?.id) return;
    
    this.isLoading = true;
    this.marksService.getMarksByStudentId(this.currentUser.id).subscribe({
      next: (marks) => {
        this.marks = marks;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading marks:', error);
        this.isLoading = false;
      }
    });
  }

  calculateGrade(marks: number, maxMarks: number): string {
    const percentage = (marks / maxMarks) * 100;
    
    if (percentage >= 90) return 'A+';
    if (percentage >= 80) return 'A';
    if (percentage >= 70) return 'B+';
    if (percentage >= 60) return 'B';
    if (percentage >= 50) return 'C';
    if (percentage >= 40) return 'D';
    return 'F';
  }

  getGradeBadgeClass(marks: number, maxMarks: number): string {
    const percentage = (marks / maxMarks) * 100;
    
    if (percentage >= 80) return 'bg-success';
    if (percentage >= 60) return 'bg-primary';
    if (percentage >= 40) return 'bg-warning';
    return 'bg-danger';
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }
}
