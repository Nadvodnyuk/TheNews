import { defineStore } from "pinia";

export const useCatalog = defineStore("catalog-store", {
  id: "news",
  state: () => {
    return {
      id: localStorage.getItem('id') || '',
      name: localStorage.getItem('name') || '',
      token: localStorage.getItem('token') || '',
      role: localStorage.getItem('role') || '',
      articleId: '',
      articleAll: {},
    };
  },

  actions: {
    setId(id) {
      this.id = id;
      localStorage.setItem('id', id);
    },

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
    },

    setArticleId(articleId) {
      this.articleId = articleId;
      console.log("this.articleId:", this.articleId);
    },

    setArticleAll(articleAll) {
      this.articleAll = articleAll.reverse();
    }
  },
});