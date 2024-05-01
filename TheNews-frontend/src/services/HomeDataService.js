import http from "../http-common";

class HomeDataService {
  async logout() {
    return http.post("/both/users/log_out");
  }
  async me() {
    return http.get("/auth/users/me");
  }
}

export default new HomeDataService();
