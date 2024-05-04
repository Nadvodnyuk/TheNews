import http from "../http-common";

class HomeDataService {
  async logout() {
    return http.post("/both/users/log_out");
  }
  async me() {
    return http.get("/auth/users/me");
  }
  async deleteUser(id) {//admin
    return http.delete(`/admin/users/${id}`);
  }

  async getAll() {
    return http.get("/auth/articles/all");
  }
  async createArticle(data) {//admin
    return http.post("/admin/articles/create", data);
  }
  async getArticle(id) {//admin
    return http.get(`/admin/articles/${id}`);
  }
  async updateArticle(id, data) {//admin
    return http.put(`/admin/articles/update/${id}`, data);
  }
  async deleteArticle(id) {//admin
    return http.delete(`/admin/articles/${id}`);
  }

  async getComment() {
    return http.get("/auth/comments/showComments");
  }
  async createComment(userId, articleId) {//user
    return http.delete(`/user/comments/postComment/${userId}/${articleId}`);
  }
  async deleteComment(id) {//admin
    return http.delete(`/admin/comments/${id}`);
  }

  async likeNum(data) {
    return http.post("/auth/likes/likeNum", data);
  }
  async createLike(data) {//user
    return http.post("/user/likes/postLike", data);
  }
  async deleteLike(userId, articleId) {//user
    return http.delete(`/user/likes/${userId}/${articleId}`);
  }
}

export default new HomeDataService();
