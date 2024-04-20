import http from "../http-common";

class HomeDataService{
    logout() {
        return http.post("/auth/users/logout");
      }
}

export default new HomeDataService();