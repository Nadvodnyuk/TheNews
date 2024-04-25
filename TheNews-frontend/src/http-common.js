import axios from "axios";

const instance = axios.create({
  baseURL: "http://localhost:8080/",
  withCredentials: true,
  headers: {
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Methods": "GET,PUT,POST,DELETE,PATCH,OPTIONS",
    "Content-type": "application/json",
  },
});

instance.interceptors.request.use(
    function (config) {
    config.headers['Access-Control-Allow-Origin'] = 'http://localhost:8081/';
    config.headers['Access-Control-Allow-Methods'] = 'GET, POST, OPTIONS, PUT, PATCH, DELETE';
    config.headers['Access-Control-Allow-Headers'] = 'Content-Type, Authorization';
    config.headers['Access-Control-Allow-Credentials'] = true;
    return config;
  },
  function (error) {
    return Promise.reject(error);
  }
);

export default instance;