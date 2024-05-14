<template>
  <div
    class="comments"
    v-show="commentFlags"
    v-for="(comments, key, index) in comments"
    :key="key"
  >
    <div class="title_comment">
      <p>
        {{ comments[key] }}
      </p>
      <h3>Комментарий {{ index + 1 }}</h3>
    </div>
    <div class="comment_more">
      <ul class="state">
        <li>
          <button class="nostyle" @click="moreCommentsFlag = !moreCommentsFlag">
            Ещё комментарии
          </button>
        </li>
      </ul>
    </div>
    <div class="comment_bar">
      <textarea
        type="input"
        cols="60"
        rows="14"
        wrap="soft"
        class="leave_comment"
        v-model="comments.comment_text"
        @input="validateComment"
        placeholder="Оставить комментарий"
      >
      </textarea>
    </div>
    <p v-if="errors.comment">
      {{ errors.comment }}
    </p>
    <div class="comment_more">
      <ul class="state">
        <li>
          <button
            class="unstyled-button"
            @click="
              sentFlag = true;
              submitComment;
            "
          >
            Отправить комментарий
          </button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { useCatalog } from "../store/catalog.js";
import { mapState, mapActions } from "pinia";
import HomeDataService from "../services/HomeDataService";

export default {
  name: "comments",
  data() {
    return {
      size: 30,
      commentFlag: false,
      moreCommentsFlag: false,
      sentFlag: false,
      errors: {
        comment: "",
      },

      comments: {
        comment_text:""
      },
    };
  },
  computed: {
    ...mapState(useCatalog, ["id", "articleId", "commentFlags"]),
  },
  methods: {
    ...mapActions(useCatalog, ["setCommentFlags"]),

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
        this.comments.comment_text.length <= 100
          ? ""
          : "Comment has more than 1000 characters.";
    },
    async submitComment() {
      this.validateComment();
      if (!this.errors.comment) {
        console.log("Form submitted:", { comment: this.comments.comment_text });
        try {
        await HomeDataService.createComment(id, articleId,this.comments).then((response) => {
          console.log(response.data);
        });
      } catch (e) {
        this.error = "!";
      }
      }
    },
  },
  mounted() {
    this.date = this.printDate();
    this.time = this.printTime();
  },
};
</script>

<style>
.title {
  flex-grow: 1;
  padding: 2em;
}

.title h2 {
  font-weight: 900;
  font-size: 1.5em;
}

.title_comment {
  flex-grow: 1;
  padding: 2em 2em 0 2em;
}

.leave_comment {
  margin-bottom: 20px;
  /* height: 100%; */
  align-items: center;
  justify-content: center;
  display: flex;
  width: 100%;
  height: 100px;
  border-radius: 6px;
  caret-color: rgba(160, 160, 160, 0.3);
  background-color: white;
  border-width: 1px;
  padding-top: 10px;
  padding-left: 10px;
  text-align: start;
}
.comment_bar {
  padding: 2em;
  white-space: normal;
}
.comment_more {
  display: flex;
  align-items: center;
  justify-content: flex-end;
}

.state {
  cursor: default;
  list-style: none;
  padding: 0;
}

.state li {
  display: inline-block;
  font-family: "Raleway", Helvetica, sans-serif;
  font-size: 0.6em;
  font-weight: 400;
  letter-spacing: 0.25em;
  line-height: 2;
  margin: 0 0 0 2em;
  padding: 0 0 0 2em;
  text-transform: uppercase;
}

.state li .icon {
  border-bottom: 0;
}

.state li .icon:before {
  color: rgba(160, 160, 160, 0.3);
  margin-right: 0.75em;
}

.nostyle {
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
</style>
