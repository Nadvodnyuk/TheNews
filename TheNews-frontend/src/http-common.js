import axios from "axios";

const instance = axios.create({
  baseURL: "http://localhost:8080/",
  withCredentials: true,
  headers: {
    "Content-type": "application/json",
    Accept: "application/json",
  },
});

// Перехватываем запросы перед отправкой и добавляем заголовок Authorization, если токен есть
instance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default instance;

// // Получаем токен из localStorage
// const token = localStorage.getItem("token");

// // Проверяем, что токен существует и не равен null
// if (token) {
//   // Если токен существует, создаем экземпляр axios с заголовком Authorization
//   axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
// } else {
//   // Если токен отсутствует, удаляем заголовок Authorization (если он был установлен ранее)
//   delete axios.defaults.headers.common["Authorization"];
// }

// // Создаем экземпляр axios с базовым URL и другими настройками
// export default axios.create({
//   baseURL: "http://localhost:8080/",
//   withCredentials: true,
//   headers: {
//     "Content-type": "application/json",
//     "Accept": "application/json"
//   }
// });
