import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { ClassroomService } from '../services/classroom.service';
import { MarksService } from '../services/marks.service';
import { UserService } from '../services/user.service';
import { User } from '../models/user.model';
import { ClassRoom } from '../models/classroom.model';
import { StudentMark } from '../models/marks.model';

declare var bootstrap: any;

@Component({
  selector: 'app-admin-dashboard',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent implements OnInit {
  currentUser: User | null = null;
  classes: ClassRoom[] = [];
  marks: StudentMark[] = [];
  students: User[] = [];
  activeTab = 'classes';
  isLoading = false;

  // Forms
  classForm: FormGroup;
  markForm: FormGroup;

  // Modal states
  editingClass: ClassRoom | null = null;
  editingMark: StudentMark | null = null;
  selectedClass: ClassRoom | null = null;

  // File uploads
  selectedClassImage: File | null = null;
  selectedTeacherImage: File | null = null;

  constructor(
    private authService: AuthService,
    private classroomService: ClassroomService,
    private marksService: MarksService,
    private userService: UserService,
    private router: Router,
    private fb: FormBuilder
  ) {
    this.classForm = this.createClassForm();
    this.markForm = this.createMarkForm();
  }

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUser();
    
    if (!this.currentUser || this.currentUser.role !== 'ADMIN') {
      this.router.navigate(['/']);
      return;
    }

    this.loadClasses();
    this.loadStudents();
  }

  createClassForm(): FormGroup {
    return this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      teacherName: ['', Validators.required],
      teacherEmail: ['', [Validators.required, Validators.email]],
      subject: ['', Validators.required],
      schedule: ['', Validators.required]
    });
  }

  createMarkForm(): FormGroup {
    return this.fb.group({
      studentId: ['', Validators.required],
      classId: ['', Validators.required],
      marks: ['', [Validators.required, Validators.min(0)]],
      maxMarks: ['', [Validators.required, Validators.min(1)]],
      examDate: ['', Validators.required],
      remarks: ['']
    });
  }

  setActiveTab(tab: string): void {
    this.activeTab = tab;
    
    if (tab === 'classes') {
      this.loadClasses();
    } else if (tab === 'marks') {
      this.loadMarks();
    } else if (tab === 'students') {
      this.loadStudents();
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
    this.isLoading = true;
    this.marksService.getAllMarks().subscribe({
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

  loadStudents(): void {
    this.isLoading = true;
    this.userService.getAllStudents().subscribe({
      next: (students) => {
        this.students = students;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading students:', error);
        this.isLoading = false;
      }
    });
  }

  openClassModal(): void {
    this.editingClass = null;
    this.classForm.reset();
    const modal = new bootstrap.Modal(document.getElementById('classModal'));
    modal.show();
  }

  editClass(classData: ClassRoom): void {
    this.editingClass = classData;
    this.classForm.patchValue(classData);
    const modal = new bootstrap.Modal(document.getElementById('classModal'));
    modal.show();
  }

  saveClass(): void {
    if (this.classForm.invalid) return;

    const classData = this.classForm.value;

    if (this.editingClass) {
      this.classroomService.updateClass(this.editingClass.id!, classData).subscribe({
        next: () => {
          this.loadClasses();
          this.closeModal('classModal');
        },
        error: (error) => console.error('Error updating class:', error)
      });
    } else {
      this.classroomService.createClass(classData).subscribe({
        next: () => {
          this.loadClasses();
          this.closeModal('classModal');
        },
        error: (error) => console.error('Error creating class:', error)
      });
    }
  }

  deleteClass(classId: number): void {
    if (confirm('Are you sure you want to delete this class?')) {
      this.classroomService.deleteClass(classId).subscribe({
        next: () => this.loadClasses(),
        error: (error) => console.error('Error deleting class:', error)
      });
    }
  }

  uploadImages(classData: ClassRoom): void {
    this.selectedClass = classData;
    this.selectedClassImage = null;
    this.selectedTeacherImage = null;
    const modal = new bootstrap.Modal(document.getElementById('imageModal'));
    modal.show();
  }

  onClassImageSelected(event: any): void {
    this.selectedClassImage = event.target.files[0];
  }

  onTeacherImageSelected(event: any): void {
    this.selectedTeacherImage = event.target.files[0];
  }

  uploadSelectedImages(): void {
    if (!this.selectedClass) return;

    if (this.selectedClassImage) {
      this.classroomService.uploadClassImage(this.selectedClass.id!, this.selectedClassImage).subscribe({
        next: () => this.loadClasses(),
        error: (error) => console.error('Error uploading class image:', error)
      });
    }

    if (this.selectedTeacherImage) {
      this.classroomService.uploadTeacherImage(this.selectedClass.id!, this.selectedTeacherImage).subscribe({
        next: () => this.loadClasses(),
        error: (error) => console.error('Error uploading teacher image:', error)
      });
    }

    this.closeModal('imageModal');
  }

  openMarkModal(): void {
    this.editingMark = null;
    this.markForm.reset();
    const modal = new bootstrap.Modal(document.getElementById('markModal'));
    modal.show();
  }

  editMark(mark: StudentMark): void {
    this.editingMark = mark;
    this.markForm.patchValue({
      studentId: mark.studentId,
      classId: mark.classId,
      marks: mark.marks,
      maxMarks: mark.maxMarks,
      examDate: new Date(mark.examDate).toISOString().split('T')[0],
      remarks: mark.remarks
    });
    const modal = new bootstrap.Modal(document.getElementById('markModal'));
    modal.show();
  }

  saveMark(): void {
    if (this.markForm.invalid) return;

    const markData = this.markForm.value;

    if (this.editingMark) {
      this.marksService.updateMark(this.editingMark.id!, markData).subscribe({
        next: () => {
          this.loadMarks();
          this.closeModal('markModal');
        },
        error: (error) => console.error('Error updating mark:', error)
      });
    } else {
      this.marksService.createMark(markData).subscribe({
        next: () => {
          this.loadMarks();
          this.closeModal('markModal');
        },
        error: (error) => console.error('Error creating mark:', error)
      });
    }
  }

  deleteMark(markId: number): void {
    if (confirm('Are you sure you want to delete this mark?')) {
      this.marksService.deleteMark(markId).subscribe({
        next: () => this.loadMarks(),
        error: (error) => console.error('Error deleting mark:', error)
      });
    }
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

  closeModal(modalId: string): void {
    const modal = bootstrap.Modal.getInstance(document.getElementById(modalId));
    if (modal) {
      modal.hide();
    }
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }
}
