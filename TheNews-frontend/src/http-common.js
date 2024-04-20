import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8080/",
  withCredentials: false,
  headers: {
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Methods": "GET,PUT,POST,DELETE,PATCH,OPTIONS",
    "Content-type": "application/json",
  },
});

axiosInstance.interceptors.request.use(
    function (config) {
    // Do something before request is sent
    config.headers['Access-Control-Allow-Origin'] = 'http://localhost:5173/';
    config.headers['Access-Control-Allow-Methods'] = 'GET, POST, OPTIONS, PUT, PATCH, DELETE';
    config.headers['Access-Control-Allow-Headers'] = 'Content-Type, Authorization';
    config.headers['Access-Control-Allow-Credentials'] = true;
    return config;
  },
  function (error) {
    // Do something with request error
    return Promise.reject(error);
  }
);
