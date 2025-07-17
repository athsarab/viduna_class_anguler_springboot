export interface ClassRoom {
  id?: number;
  title: string;
  description: string;
  teacherName: string;
  teacherEmail: string;
  teacherImage?: string;
  subject: string;
  schedule: string;
  classImage?: string;
  createdAt?: Date;
  updatedAt?: Date;
}

export interface CreateClassRequest {
  title: string;
  description: string;
  teacherName: string;
  teacherEmail: string;
  subject: string;
  schedule: string;
}
