import { defineStore } from "pinia";

export const useCatalog = defineStore("catalog-store", {
  id: "news",
  state: () => {
    return {
      name: localStorage.getItem('name') || '',
      token: localStorage.getItem('token') || '',
      role: localStorage.getItem('role') || ''
    };
  },

  actions: {
    setName(name) {
      this.name = name;
      localStorage.setItem('name', name);
    },

    setToken(token) {
      this.token = token;
      localStorage.setItem('token', token)
    },

    setRole(role) {
      this.role = role;
      localStorage.setItem('role', role)
    }
  },
});