<template>
  <div>
    <button
      v-if="!isFormVisible"
      @click="openCreateArticleForm"
      class="createArticleBtn"
    >
      Создать статью
    </button>
    <div v-if="isFormVisible" class="createArtForm">
      <div class="createFormHeader">
        <div class="createArtTitle"><h2>Форма создания статьи</h2></div>
        <div>
          <button @click="closeCreateArticleForm" class="closeBtn">✖</button>
        </div>
      </div>
      <input v-model="article.title" placeholder="Заголовок" class="artTitle" />
      <span v-if="validationErrors.title" class="error-message">{{
        validationErrors.title
      }}</span>
      <input v-model="article.topics" placeholder="Теги" class="artTags" />
      <span v-if="validationErrors.topics" class="error-message">{{
        validationErrors.topics
      }}</span>
      <textarea
        v-model="article.article_text"
        placeholder="Текст статьи"
        class="artText"
      ></textarea>
      <span v-if="validationErrors.article_text" class="error-message">{{
        validationErrors.article_text
      }}</span>
      <div class="file-input">
        <input
          v-model="article.image_url"
          placeholder="Изображение"
          class="artTags"
        />
        <span v-if="validationErrors.image_url" class="error-message">
          {{ validationErrors.image_url }}
        </span>
      </div>
      <div class="publishDiv">
        <button @click="createArticleAndCloseForm(); createArticle()" class="publishArticleBtn">
          Создать
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { useCatalog } from "../store/catalog.js";
import { mapState, mapActions } from "pinia";
import HomeDataService from "../services/HomeDataService";

export default {
  data() {
    return {
      isFormVisible: false,
      isArticleCreated: false,
      article: {
        title: "",
        article_text: "",
        image_url: "",
        topics: "",
      },
      validationErrors: {
        title: "",
        topics: "",
        article_text: "",
      },
    };
  },
  methods: {
    ...mapActions(useCatalog, ["setArticleAll"]),
    validateForm() {
      let isValid = true;
      this.validationErrors = {
        title: "",
        topics: "",
        article_text: "",
      };

      if (!this.article.title.trim()) {
        this.validationErrors.title = "Пожалуйста, введите заголовок статьи";
        isValid = false;
      } else if (this.article.title.length > 255) {
        this.validationErrors.title =
          "Максимальная длина заголовка - 255 символов";
        isValid = false;
      }

      if (!this.article.topics.trim()) {
        this.validationErrors.topics = "Пожалуйста, введите теги статьи";
        isValid = false;
      }

      if (!this.article.article_text.trim()) {
        this.validationErrors.article_text = "Пожалуйста, введите текст статьи";
        isValid = false;
      }

      if (!this.article.image_url) {
        this.validationErrors.image_url = "Пожалуйста, выберите изображение";
        isValid = false;
      }

      return isValid;
    },
    openCreateArticleForm() {
      this.isFormVisible = true;
    },
    closeCreateArticleForm() {
      this.isFormVisible = false;
      this.validationErrors = "";
    },

    handleFileUpload(event) {
      this.article.image_url = event.target.files[0];
    },
    createArticleAndCloseForm() {
      if (!this.validateForm()) {
        return;
      }
      this.isFormVisible = false;
      this.isArticleCreated = true;
    },
    async getAll() {
      try {
        await HomeDataService.getAll().then((response) => {
          console.log(response.data);
          this.setArticleAll(response.data);
        });
      } catch (e) {
        this.error = "Проверьте все поля!";
      }
    },
    async createArticle() {
      try {
        await HomeDataService.createArticle(this.article).then((response) => {
          console.log(response.data);
          this.getAll();
        });
      } catch (e) {
        this.error = "Проверьте все поля!";
      }
    },
  },
};
</script>

<style>
.createArticleBtn {
  background-color: white;
}

.createArtTitle {
  margin-right: 295px;
}

.createFormHeader {
  display: flex;
  justify-content: right;
}

.closeBtn {
  background-color: white;
  width: 30px;
  align-items: center;
  float: right; /* Помещаем кнопку вправо */
  margin-right: 0px;
  text-align: center;
  padding-right: 30px;
  padding-top: 10px;
}

.createArtForm {
  display: flex;
  flex-direction: column;
  width: 1000px;
  margin: 0 auto;
  height: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
}

.artTitle,
.artTags,
.artText,
.artImg {
  width: 100%;
  height: 10px;
  padding: 20px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 20px;
  margin-top: 20px;
}

.artText {
  overflow: hidden;
  resize: vertical; /* Разрешает вертикальное изменение размера поля */
  overflow-y: auto;
  border-radius: 5px;
  min-height: 50px;
  height: auto;
}

.artImg {
  height: 65px;
  position: relative;
  overflow: hidden;
  display: inline-block;
}

.file-input {
  align-items: center;
}

.success-message {
  color: green;
  margin-top: 10px;
}

.error-message {
  color: red;
  margin-top: 1px;
  margin-left: 21px;
  margin-bottom: 5px;
}

.publishDiv {
  display: flex;
  justify-content: center;
}

.publishArticleBtn {
  margin-top: 20px;
  background-color: white;
  width: 105px;
}
</style>
