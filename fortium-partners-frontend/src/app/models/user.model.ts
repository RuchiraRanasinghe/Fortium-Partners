import { Department } from './employee.model';

export interface User {
  id?: number;
  name: string;
  email: string;
  password: string;
  department?: Department;
  createdAt?: Date;
  updatedAt?: Date;
}
