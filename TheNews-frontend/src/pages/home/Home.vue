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
            {{ article.publication_date }}
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
          <li v-if="role === 'ROLE_ADMIN'" @click="deleteArticle">
            Удалить статью
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
            <button class="unstyled-button" @click="likedFlag = !likedFlag">
              <img
                class="mini"
                src="/img/heart.svg"
                alt="mini like"
                v-show="!likedFlag"
              />
              <img
                class="mini"
                src="/img/blue-heart.svg"
                alt="mini like"
                v-show="likedFlag"
              />
            </button>
            <button class="unstyled-button">3</button>
          </li>
          <li>
            <button class="unstyled-button" @click="commentFlag = !commentFlag">
              <img
                class="mini"
                src="/img/comment2.svg"
                alt="mini like"
                v-show="!commentFlag"
              />
              <img
                class="mini"
                src="/img/comment-open.svg"
                alt="mini like"
                v-show="commentFlag"
              />
            </button>
            <button class="unstyled-button" @click="commentFlag = !commentFlag">
              15
            </button>
          </li>
        </ul>
      </footer>
      <comments />
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
      commentFlag: false,
      likeFlag: false,
      moreArticlesFlag: false,
      moreCommentsFlag: false,
      sentFlag: false,
      likedFlag: false,
      errors: {
        comment: "",
      },

      comments: {},
    };
  },
  computed: {
    ...mapState(useCatalog, ["role"]),
    ...mapState(useCatalog, ["articleAll"]),
  },
  methods: {
    ...mapActions(useCatalog, ["setArticleAll"]),
    ...mapActions(useCatalog, ["setArticleId"]),
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
          console.log(response.data);
          this.setArticleAll(response.data);
        });
      } catch (e) {
        this.error = "Проверьте все поля!";
      }
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
