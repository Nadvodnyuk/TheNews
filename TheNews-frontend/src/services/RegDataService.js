import http from "../http-common";

class RegDataService {
  create(data) {
    return http.post("/auth/sign_up", data);
  }
}

export default new RegDataService();
