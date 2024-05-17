<script setup>
import updateArticle from "../../components/updateArticle.vue";
import createArticle from "../../components/createArticle.vue";
import comments from "../../components/comments.vue";
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
              @click="commenting(article.article_id)"
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
              {{ commentNums[article.article_id] }}
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
      commentNum: {},
      commentFlag: {},
    };
  },
  computed: {
    ...mapState(useCatalog, [
      "role",
      "articleAll",
      "id",
      "page",
      "likeNums",
      "commentNums",
      "commentFlags",
      "commentAll",
      "commentPages",
      "articleId",
    ]),
  },
  methods: {
    ...mapActions(useCatalog, [
      "setArticleAll",
      "setArticleId",
      "setLikeNums",
      "setCommentNums",
      "setCommentFlags",
      "setCommentAll",
      "setCommentPages",
      "formatDate",
      "scrollToTop",
    ]),

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
          let pages = {};
          let comment = {};

          this.setArticleAll(response.data);

          this.likedFlags = Object.fromEntries(
            response.data.map((article) => [article.article_id, false])
          );
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
            this.isLiked(this.id, article.article_id);
          });

          this.setLikeNums(this.likeNum);
          this.setCommentNums(this.commentNum);
          this.setCommentFlags(this.commentFlag);
          this.setCommentAll(comment);
          this.setCommentPages(pages);
        });
      } catch (e) {
        console.log("e", e);
      }
    },

    async deleteArticle(id) {
      try {
        await HomeDataService.deleteArticle(id).then((response) => {
          console.log("deleteArticle", response.data);
          this.getAll();
        });
      } catch (e) {
        console.log("e", e);
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
              console.log("deleteLike", response.data);
            }
          );
        }
        this.getLikeNum(articleId);
        this.setLikeNums(this.likeNum);
      } catch (e) {
        console.log("e", e);
      }
    },

    async getLikeNum(articleId) {
      try {
        await HomeDataService.getLikeNum(articleId).then((response) => {
          this.likeNum[articleId] = response.data;
          console.log("getLikeNum", response.data);
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
          console.log("getcommentNum", response.data);
        });
        this.setCommentNums(this.commentNum);
      } catch (e) {
        console.log("e", e);
      }
    },

    async isLiked(id, articleId) {
      if (id) {
        try {
          await HomeDataService.isLiked(id, articleId).then((response) => {
            this.likedFlags[articleId] = response.data;
            console.log("isLiked", response.data);
          });
        } catch (e) {
          console.log("e", e);
        }
      }
    },

    async liking(user_id, article_id) {
      try {
        console.log("user_id, article_id", user_id, article_id);
        let like = {
          userL: user_id,
          articleL: article_id,
        };
        await HomeDataService.createLike(like).then((response) => {
          console.log("createLike", response.data);
        });
      } catch (e) {
        console.log("e", e);
      }
    },

    async commenting(article_id) {
      try {
        this.commentFlag[article_id] = !this.commentFlag[article_id];
        this.setCommentFlags(this.commentFlag);
        this.setArticleId(article_id);
        await this.fetchComments(article_id);
        console.log("this.commentAll", this.commentAll);
      } catch (e) {
        console.log("e", e);
      }
    },

    async fetchComments(article_id) {
      await HomeDataService.getComments(
        article_id,
        this.commentPages[article_id]
      )
        .then((response) => {
          let comments = {};

          response.data.forEach((comment) => {
            if (comments[article_id]) {
              comments[article_id].push(comment);
            } else {
              comments[article_id] = [comment];
            }
          });
          this.setCommentAll(comments);
        })
        .catch((error) => {
          console.error("Ошибка при получении комментариев:", error);
        });
    },
  },

  async created() {
    await this.getAll();
    console.log("this.articleAll", this.articleAll);
  },
};
</script>

<style scoped src="./home.css"></style>
