<template>
  <div class="comments" v-show="commentFlags[articleId]">
    <div v-if="commentFlags[articleId]">
        <div v-if="commentAll[articleId] && commentAll[articleId].length">
          <div v-for="(comment, index) in commentAll[articleId]" :key="index">
            <p>{{ comment }}</p>
          </div>
        </div>
        <div v-else>
          <p>Нет комментариев</p>
        </div>
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
        v-model="comment.comment_text"
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
            class="put_comm_btn"
            @click="
              submitComment();
              sentFlag = true;
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
      page: 0,
      commentFlag: false,
      moreCommentsFlag: false,
      sentFlag: false,
      errors: {
        comment: "",
      },
      comment: {
        comment_text: "",
      },
      commentAll: {},
    };
  },
  computed: {
    ...mapState(useCatalog, ["id", "articleId", "commentFlags"]),

    loadComments() {
      if (this.commentFlags[this.articleId]) {
        this.fetchComments();
      }
    },
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
        this.comment.comment_text.length <= 100
          ? ""
          : "Comment has more than 1000 characters.";
    },

    async fetchComments() {
      await HomeDataService.getComment()
        .then((response) => {
          console.log(response.data);
          this.commentAll[this.articleId] = response.data;
        })
        .catch((error) => {
          console.error("Ошибка при получении комментариев:", error);
        });
    },

    async submitComment() {
      console.log("Submit comment...");
      this.validateComment();
      if (!this.errors.comment) {
        console.log("Form submitted:", { comment: this.comment.comment_text });
        try {
          await HomeDataService.createComment(
            this.id,
            this.articleId,
            this.comment
          ).then((response) => {
            console.log(response.data);
          });
        } catch (e) {
          console.log(e);
          this.e = "!";
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
  caret-color: rgb(0, 0, 0);
}

.leave_comment:focus::placeholder {
  color: transparent;
}

.put_comm_btn {
  background-color: white;
  border: 1px solid black;
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
