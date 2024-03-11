import create from 'zustand';


export const useRegistrationStore = create((set) => ({
  name: '',
  email: '',
  password: '',
  course: '',
  hobbies: [],
  societies: [],
  sports: [],
  ethnicity: '',
  userId: 0, 
  setName: (name) => set((state) => ({ name })),
  setEmail: (email) => set((state) => ({ email })),
  setPassword: (password) => set((state) => ({ password })),
  setCourse: (course) => set((state) => ({ course })),
  setHobbies: (hobbies) => set((state) => ({ hobbies })),
  setSocieties: (societies) => set((state) => ({ societies })),
  setSports: (sports) => set((state) => ({ sports })),
  setEthnicity: (ethnicity) => set((state) => ({ ethnicity })),
  setUserId: (userId) => set((state) => ({ userId })),
  reset: () => set((state) => ({
    name: '',
    email: '',
    password: '',
    course: '',
    hobbies: [],
    societies: [],
    sports: [],
    ethnicity: '',
  })),
}));