<script setup>
import updateArticle from "../../components/updateArticle.vue";
import createArticle from "../../components/createArticle.vue";
import comments from "../../components/comments.vue";
import http from "../../http-common";
</script>

<template>
  <div class="sub_header"></div>
  <div class="news_page">
    <createArticle v-if="role === 'ROLE_ADMIN'" class="createArticleBtn" />
    <article
      class="post"
      v-for="article in articleAll"
      :key="article.article_id"
    >
      <header>
        <div class="title">
          <h2>
            {{ article.title }}
          </h2>
          <p>
            {{ article.topics }}
          </p>
        </div>
        <div class="meta">
          <p class="published">
            {{ formatDate(article.publication_date) }}
          </p>
        </div>
      </header>
      <div class="art">
        <a class="image_main">
          <img :src="article.image_url" alt="" />
        </a>
        <p>
          {{ article.article_text }}
        </p>
      </div>
      <footer>
        <ul class="stats">
          <li v-if="role === 'ROLE_ADMIN'">
            <button
              class="unstyled-button"
              @click="deleteArticle(article.article_id)"
            >
              Удалить статью
            </button>
          </li>
          <li>
            <updateArticle
              v-if="role === 'ROLE_ADMIN'"
              class="updateArticleBtn"
              @click="setArticleId(article.article_id)"
            />
          </li>
          <li>
            <button
              class="unstyled-button"
              @click="moreArticlesFlag = !moreArticlesFlag"
            >
              {{ !moreArticlesFlag ? "Показать больше" : "Показать меньше" }}
            </button>
          </li>
          <li>
            <button
              class="unstyled-button"
              @click="toggleLikedFlag(article.article_id)"
              v-if="role === 'ROLE_USER'"
            >
              <img
                class="mini"
                src="/img/heart.svg"
                alt="mini like"
                v-show="!likedFlags[article.article_id]"
              />
              <img
                class="mini"
                src="/img/blue-heart.svg"
                alt="mini like"
                v-show="likedFlags[article.article_id]"
              />
            </button>
            <img
              class="mini"
              src="/img/heart.svg"
              alt="mini like"
              v-show="!likedFlags[article.article_id]"
              v-if="role !== 'ROLE_USER'"
            />
            <div class="unstyled-button">
              {{ likeNums[article.article_id] }}
            </div>
          </li>
          <li>
            <button
              class="unstyled-button"
              @click="
                commentFlag[article.article_id] =
                  !commentFlag[article.article_id];
                setCommentFlags(commentFlag);
                setArticleId(article.article_id);
              "
            >
              <img
                class="mini"
                src="/img/comment2.svg"
                alt="mini like"
                v-show="!commentFlags[article.article_id]"
              />
              <img
                class="mini"
                src="/img/comment-open.svg"
                alt="mini like"
                v-show="commentFlags[article.article_id]"
              />
            </button>
            <button
              class="unstyled-button"
              @click="
                commentFlag[article.article_id] =
                  !commentFlag[article.article_id];
                setCommentFlags(commentFlag);
              "
            >
              15
            </button>
          </li>
        </ul>
      </footer>
      <div v-show="commentFlags[article.article_id]">
        <comments />
      </div>
    </article>
    <div class="space"></div>
  </div>
</template>

<script>
import { useCatalog } from "../../store/catalog.js";
import { mapState, mapActions } from "pinia";
import HomeDataService from "../../services/HomeDataService";

export default {
  name: "App",
  data() {
    return {
      size: 30,
      picture: "",
      date: "",
      time: "",
      myNewComment: "",
      likeFlag: false,
      moreArticlesFlag: false,
      moreCommentsFlag: false,
      sentFlag: false,
      likedFlag: false,
      errors: {
        comment: "",
      },
      like: {
        userL: "",
        articleL: "",
      },
      likedFlags: {},
      likeNum: {},
      comment: {},
      commentFlag: {},
      articleId: "",
    };
  },
  computed: {
    ...mapState(useCatalog, [
      "role",
      "articleAll",
      "id",
      "likeNums",
      "commentFlags",
      "commentAll",
    ]),
  },
  methods: {
    ...mapActions(useCatalog, [
      "setArticleAll",
      "setArticleId",
      "setLikeNums",
      "setCommentFlags",
      "setCommentAll",
    ]),

    scrollToTop() {
      // Прокручиваем страницу наверх (координаты 0, 0)
      window.scrollTo(0, 0);
    },

    printDate() {
      return new Date().toLocaleDateString();
    },

    printTime() {
      return new Date().toLocaleTimeString();
    },

    validateComment() {
      this.errors.comment =
        this.myNewComment.length <= 100
          ? ""
          : "Comment has more than 1000 characters.";
    },

    submitComment() {
      this.validateComment();
      if (!this.errors.comment) {
        console.log("Form submitted:", { comment: this.myNewComment });
      }
    },

    async getAll() {
      try {
        await HomeDataService.getAll().then((response) => {
          this.setArticleAll(response.data);
          this.likedFlags = Object.fromEntries(
            response.data.map((article) => [article.article_id, false])
          );
          this.likeNum = Object.fromEntries(
            response.data.map((article) => [article.article_id, 0])
          );
          this.commentFlag = Object.fromEntries(
            response.data.map((article) => [article.article_id, false])
          );
          this.comment = Object.fromEntries(
            response.data.map((article) => [article.article_id, []])
          );
          response.data.forEach((article) => {
            this.getNum(article.article_id);
            this.isLiked(this.id, article.article_id);
          });
          this.setLikeNums(this.likeNum);
          this.setcommentFlags(this.commentFlag);
          this.setCommentAll(this.comment);
        });
      } catch (e) {
        this.error = "Проверьте все поля!";
      }
    },
    async deleteArticle(id) {
      try {
        await HomeDataService.deleteArticle(id).then((response) => {
          console.log(response.data);
          this.getAll();
        });
      } catch (e) {
        this.error = "Проверьте все поля!";
      }
    },
    async toggleLikedFlag(articleId) {
      try {
        const currentState = this.likedFlags[articleId];
        this.likedFlags[articleId] = !currentState;
        const user_id = this.id;
        if (this.likedFlags[articleId]) {
          await this.liking(user_id, articleId);
        } else {
          await HomeDataService.deleteLike(user_id, articleId).then(
            (response) => {
              console.log(response.data);
            }
          );
        }
        this.getNum(articleId);
        this.setLikeNums(this.likeNum);
      } catch (e) {
        this.error = "Не удалось поставить лайк!";
      }
    },
    async getNum(articleId) {
      try {
        await HomeDataService.getLikeNum(articleId).then((response) => {
          this.likeNum[articleId] = response.data;
          console.log(response.data);
        });
        this.setLikeNums(this.likeNum);
      } catch (e) {
        this.error = "Не удалось поставить лайк!";
      }
    },
    async isLiked(id, articleId) {
      try {
        await HomeDataService.isLiked(id, articleId).then((response) => {
          this.likedFlags[articleId] = response.data;
          console.log(response.data);
        });
      } catch (e) {
        this.error = "!";
      }
    },

    async liking(user_id, article_id) {
      try {
        console.log(user_id, article_id);
        let like = {
          userL: user_id,
          articleL: article_id,
        };
        await HomeDataService.createLike(like).then((response) => {
          console.log(response.data);
        });
      } catch (e) {
        this.error = "Не удалось поставить лайк!";
      }
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      return date.toLocaleString("ru-RU", {
        year: "numeric",
        month: "2-digit",
        day: "2-digit",
        hour: "2-digit",
        minute: "2-digit",
        hour12: false,
      });
    },
  },
  async created() {
    await this.getAll();
    console.log(this.articleAll);
  },
  mounted() {
    this.date = this.printDate();
    this.time = this.printTime();
  },
};
</script>

<style scoped src="./home.css"></style>
