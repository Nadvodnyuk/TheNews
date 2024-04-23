import http from "../http-common";

class HomeDataService {
  logout() {
    return http.post("/both/users/log_out");
  }
  me() {
    return http.get("/both/users/me");
  }
}

export default new HomeDataService();
