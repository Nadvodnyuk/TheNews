<template>
  <div>
    <button
      v-if="!isFormVisible"
      @click="openupdateArticleForm"
      class="updateArticleBtn"
    >
      РЕДАКТИРОВАТЬ СТАТЬЮ
    </button>
    <div v-if="isFormVisible" class="updateArtForm">
      <div class="updateFormHeader">
        <div class="updateArtTitle">
          <h2>Форма редактирования статьи</h2>
        </div>
        <div>
          <button @click="closeupdateArticleForm" class="closeBtn">✖</button>
        </div>
      </div>
      <input v-model="article.title" placeholder="Заголовок" class="artTitle" />
      <span v-if="validationErrors.title" class="error-message">{{
        validationErrors.title
      }}</span>
      <a-select
        v-model="article.topics"
        mode="multiple"
        style="width: 100%"
        placeholder="Теги"
        :options="formattedThemes"
        @change="handleTopicChange"
      ></a-select>
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
        <button @click="updateArticleAndCloseForm" class="publishArticleBtn">
          Сохранить изменения
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
      themes: 0,
      isFormVisible: false,
      isArticleupdated: false,
      article: {
        title: "",
        article_text: "",
        image_url: "",
        topics: [],
      },
      validationErrors: {
        title: "",
        topics: "",
        article_text: "",
      },
    };
  },
  computed: {
    ...mapState(useCatalog, ["articleId", "theme"]),
    formattedThemes() {
      this.themes = this.theme[0];
      return this.themes.map((t) => ({ value: t, label: t }));
    },
  },

  methods: {
    ...mapActions(useCatalog, ["setArticleAll"]),

    validateForm() {
      let isValid = true;
      this.validationErrors = {
        title: "",
        tags: "",
        content: "",
      };

      if (!this.article.title.trim()) {
        this.validationErrors.title = "Пожалуйста, введите заголовок статьи";
        isValid = false;
      } else if (this.article.title.length > 255) {
        this.validationErrors.title =
          "Максимальная длина заголовка - 255 символов";
        isValid = false;
      }

      if (!this.article.topics.length) {
        this.validationErrors.topics = "Пожалуйста, введите теги статьи";
        isValid = false;
      }

      if (!this.article.article_text.trim()) {
        this.validationErrors.article_text = "Пожалуйста, введите текст статьи";
        isValid = false;
      }

      if (!this.article.image_url.trim()) {
        this.validationErrors.image_url = "Пожалуйста, выберите изображение";
        isValid = false;
      }

      return isValid;
    },

    openupdateArticleForm() {
      this.getArticle();
      this.isFormVisible = true;
    },
    handleTopicChange(value) {
      console.log("value update", value);
      this.article.topics = value;
      console.log("this.article.topics update", this.article.topics);
    },
    closeupdateArticleForm() {
      this.isFormVisible = false;
      this.validationErrors = "";
    },
    handleFileUpload(event) {
      this.article.image_url = event.target.files[0];
    },
    updateArticleAndCloseForm() {
      if (!this.validateForm()) {
        return;
      }
      this.updateArticle();
      this.isFormVisible = false;
      this.isArticleupdated = true;
      this.getArticle();
    },
    async getArticle() {
      try {
        await HomeDataService.getArticle(this.articleId).then((response) => {
          console.log("one", response.data);
          this.article.title = response.data.title;
          this.article.article_text = response.data.article_text;
          this.article.topics = response.data.topics;
          // this.handleTopicChange(response.data.topics)
          console.log("проверкаааа", this.article);
          this.article.image_url = response.data.image_url;
        });
      } catch (e) {
        this.error = "Проверьте все поля!";
      }
    },
    async getAll() {
      try {
        await HomeDataService.getAll().then((response) => {
          console.log("getAll", response.data);
          this.setArticleAll(response.data);
        });
      } catch (e) {
        this.error = "Проверьте все поля!";
      }
    },
    async updateArticle() {
      try {
        await HomeDataService.updateArticle(this.articleId, this.article).then(
          (response) => {
            console.log("updateArticle", response.data);
            this.article.title = response.data.title;
            this.article.article_text = response.data.article_text;
            this.article.topics = response.data.topics;
            this.article.image_url = response.data.image_url;
            this.getAll();
          }
        );
      } catch (e) {
        this.error = "Проверьте все поля!";
      }
    },
  },
  watch: {
    articleId(newVal) {
      this.getArticle();
    },
  },
};
</script>

<style>
.updateArticleBtn {
  border: none;
  padding: 0;
  background: none;
  display: inline-block;
  font-family: "Raleway", Helvetica, sans-serif;
  font-weight: 400;
  letter-spacing: 0.25em;
  line-height: 2;
  text-transform: uppercase;
  color: #738392;
}

.updateArtTitle {
  margin-right: 175px;
}

.updateFormHeader {
  display: flex;
  justify-content: right;
}

.closeBtn {
  background-color: white;
  width: 30px;
  align-items: center;
  float: right;
  /* Помещаем кнопку вправо */
  margin-right: 0px;
  text-align: center;
  padding-right: 30px;
  padding-top: 10px;
}

.updateArtForm {
  display: flex;
  flex-direction: column;
  width: 800px;
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
  resize: vertical;
  /* Разрешает вертикальное изменение размера поля */
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
