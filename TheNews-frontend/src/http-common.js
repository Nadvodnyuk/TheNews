import axios from "axios";

// Получаем токен из localStorage
const token = localStorage.getItem("token");

// Проверяем, что токен существует и не равен null
if (token) {
  // Если токен существует, создаем экземпляр axios с заголовком Authorization
  axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
} else {
  // Если токен отсутствует, удаляем заголовок Authorization (если он был установлен ранее)
  delete axios.defaults.headers.common["Authorization"];
}

// Создаем экземпляр axios с базовым URL и другими настройками
export default axios.create({
  baseURL: "http://localhost:8080/",
  withCredentials: true,
  headers: {
    "Content-type": "application/json",
    "Accept": "application/json"
  }
});
// import axios from "axios";

// const instance = axios.create({
//   baseURL: "http://localhost:8080/",
//   withCredentials: true,
//   headers: {
//     "Access-Control-Allow-Origin": "*",
//     "Access-Control-Allow-Methods": "GET,PUT,POST,DELETE,PATCH,OPTIONS",
//     "Content-type": "application/json",
//   },
// });

// instance.interceptors.request.use(
//     function (config) {
//     config.headers['Access-Control-Allow-Origin'] = 'http://localhost:8081/';
//     config.headers['Access-Control-Allow-Methods'] = 'GET, POST, OPTIONS, PUT, PATCH, DELETE';
//     config.headers['Access-Control-Allow-Headers'] = 'Content-Type, Authorization';
//     config.headers['Access-Control-Allow-Credentials'] = true;
//     return config;
//   },
//   function (error) {
//     return Promise.reject(error);
//   }
// );

// export default instance;