export interface StudentMark {
  id?: number;
  studentId: number;
  studentName: string;
  studentEmail: string;
  classId: number;
  className: string;
  subject: string;
  marks: number;
  maxMarks: number;
  examDate: Date;
  remarks?: string;
  createdAt?: Date;
}

export interface CreateMarkRequest {
  studentId: number;
  classId: number;
  marks: number;
  maxMarks: number;
  examDate: string;
  remarks?: string;
}
