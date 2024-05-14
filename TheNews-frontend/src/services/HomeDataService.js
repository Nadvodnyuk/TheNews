import http from "../http-common";

class HomeDataService {
  async logout() {
    return http.post("/both/users/log_out");
  }
  async me() {
    return http.get("/auth/users/me");
  }
  async deleteUser(id) {
    //admin
    return http.delete(`/admin/users/${id}`);
  }

  async getAll() {
    return http.get("/auth/articles/all");
  }
  async createArticle(data) {
    //admin
    return http.post("/admin/articles/create", data);
  }
  async getArticle(id) {
    //admin
    return http.get(`/admin/articles/${id}`);
  }
  async updateArticle(id, data) {
    //admin
    return http.put(`/admin/articles/update/${id}`, data);
  }
  async deleteArticle(id) {
    //admin
    return http.delete(`/admin/articles/${id}`);
  }

  async getComment() {
    return http.get("/auth/comments/showComments");
  }
  async createComment(user_id, article_id, data) {
    //user
    return http.post(`/user/comments/postComment?user_id=${user_id}&article_id=${article_id}`, data);
  }
  async deleteComment(id) {
    //admin
    return http.delete(`/admin/comments/${id}`);
  }

  async getLikeNum(article_id) {
    return http.post(`/auth/likes/likeNum?article_id=${article_id}`);
  }

  async isLiked(user_id, article_id) {
    return http.post(`/auth/likes/isLiked?user_id=${user_id}&article_id=${article_id}`)
  }
  async createLike(data) {
    //user
    return http.post("/user/likes/postLike", data);
  }
  async deleteLike(userL, articleL) {
    //user
    return http.delete(`/user/likes/${userL}/${articleL}`);
  }
}

export default new HomeDataService();
