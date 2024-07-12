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
        <button @click="createArticleAndCloseForm()" class="publishArticleBtn">
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
      themes: 0,
      isFormVisible: false,
      isArticleCreated: false,
      likeNum: {},
      commentNum: {},
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
    ...mapState(useCatalog, [
      "theme",
      "likeNums",
      "commentNums",
      "commentFlags",
      "commentAll",
      "commentPages",
    ]),
    formattedThemes() {
      this.themes = this.theme[0];
      return this.themes.map((t) => ({ value: t, label: t }));
    },
  },
  methods: {
    ...mapActions(useCatalog, [
      "setArticleAll",
      "setLikeNums",
      "setCommentNums",
      "setCommentFlags",
      "setCommentAll",
      "setCommentPages",
    ]),
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

      if (!this.article.topics.length) {
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
    handleTopicChange(value) {
      console.log("value", value);
      this.article.topics = value;
      console.log("this.article.topics", this.article.topics);
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
      this.createArticle();
      this.article = {
        title: "",
        article_text: "",
        image_url: "",
        topics: [],
      };
    },
    async getAll() {
      try {
        let pages = {};
        let comment = {};
        await HomeDataService.getAll().then((response) => {
          this.setArticleAll(response.data);
          this.likeNum = Object.fromEntries(
            response.data.map((article) => [article.article_id, 0])
          );
          this.commentNum = Object.fromEntries(
            response.data.map((article) => [article.article_id, 0])
          );
          this.commentFlag = Object.fromEntries(
            response.data.map((article) => [article.article_id, false])
          );
          comment = Object.fromEntries(
            response.data.map((article) => [article.article_id, []])
          );
          pages = Object.fromEntries(
            response.data.map((article) => [article.article_id, 1])
          );

          response.data.forEach((article) => {
            this.getLikeNum(article.article_id);
            this.getCommentNum(article.article_id);
          });

          this.setLikeNums(this.likeNum);
          this.setCommentNums(this.commentNum);
          this.setCommentFlags(this.commentFlag);
          this.setCommentAll(comment);
          this.setCommentPages(pages);
        });
      } catch (e) {
        this.error = "Проверьте все поля!";
      }
    },

    async getLikeNum(articleId) {
      try {
        await HomeDataService.getLikeNum(articleId).then((response) => {
          this.likeNum[articleId] = response.data;
        });
        this.setLikeNums(this.likeNum);
      } catch (e) {
        console.log("e", e);
      }
    },

    async getCommentNum(articleId) {
      try {
        await HomeDataService.getCommentNum(articleId).then((response) => {
          this.commentNum[articleId] = response.data;
        });
        this.setCommentNums(this.commentNum);
      } catch (e) {
        console.log("e", e);
      }
    },

    async createArticle() {
      try {
        console.log(this.article.topics);
        await HomeDataService.createArticle(this.article).then((response) => {
          console.log(response.data);
          this.getAll();
          this.getTheme();
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

.artTitle {
  margin-bottom: 20px;
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
