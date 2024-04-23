import http from "../http-common";

class HomeDataService {
  logout() {
    return http.post("/auth/users/log_out");
  }
  me() {
    return http.get("/auth/users/me");
  }
}

export default new HomeDataService();
