import { defineStore } from "pinia";

export const useCatalog = defineStore("catalog-store", {
  id: "news",
  state: () => {
    return {
      id: localStorage.getItem("id") || "",
      name: localStorage.getItem("name") || "",
      token: localStorage.getItem("token") || "",
      role: localStorage.getItem("role") || "",
      articleId: "",
      articleAll: {},
      likeNums: {},
      commentNums: {},
      commentFlags: {},
      commentAll: {},
      page: 1,
      commentPages: {},
    };
  },

  actions: {
    setId(id) {
      this.id = id;
      localStorage.setItem("id", id);
    },

    setName(name) {
      this.name = name;
      localStorage.setItem("name", name);
    },

    setToken(token) {
      this.token = token;
      localStorage.setItem("token", token);
    },

    setRole(role) {
      this.role = role;
      localStorage.setItem("role", role);
    },

    setArticleId(articleId) {
      this.articleId = articleId;
    },

    setArticleAll(articleAll) {
      this.articleAll = articleAll.reverse();
    },

    setLikeNums(likeNums) {
      this.likeNums = likeNums;
    },

    setCommentNums(commentNums) {
      this.commentNums = commentNums;
    },

    setCommentFlags(commentFlags) {
      this.commentFlags = commentFlags;
    },

    setCommentAll(commentAll) {
      this.commentAll = commentAll;
    },

    setPage(page) {
      this.page = page;
    },

    setCommentPages(commentPages) {
      this.commentPages = commentPages;
    },

    formatDate(dateString) {
      const date = new Date(dateString);
      return date.toLocaleString("ru-RU", {
        year: "numeric",
        month: "2-digit",
        day: "2-digit",
        hour: "2-digit",
        minute: "2-digit",
        hour12: false,
      });
    },

    scrollToTop() {
      window.scrollTo(0, 0);
    },
  },
});
