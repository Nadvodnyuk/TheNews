import http from "../http-common";

class HomeDataService{
    logout() {
        return http.post("/auth/users/log_out");
      }
}

export default new HomeDataService();