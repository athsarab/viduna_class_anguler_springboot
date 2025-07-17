export interface User {
  id?: number;
  email: string;
  password?: string;
  firstName: string;
  lastName: string;
  role: 'STUDENT' | 'ADMIN';
  profileImage?: string;
  createdAt?: Date;
}

export interface LoginRequest {
  email: string;
  password: string;
} 

export interface RegisterRequest {
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  role: 'STUDENT' | 'ADMIN';
}
