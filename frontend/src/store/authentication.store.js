import {create} from 'zustand';

const useAuthStore = create((set) => ({
  loggedInUserEmail: null,
  setLoggedInUserEmail: (email) => set({ loggedInUserEmail: email }),
  resetLoggedInUserEmail: () => set({ loggedInUserEmail: null }),
}));

export default useAuthStore;
