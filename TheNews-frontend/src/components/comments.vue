<template>
  <br />
  <div class="comments" v-show="commentFlags[articleId]">
    <div
      v-if="commentAll[articleId] && commentAll[articleId].length"
      class="title_comment"
    >
      <br />
      <div v-for="(comment, index) in commentAll[articleId]" :key="index">
        <div class="comment_trash">
          {{ comment.comment_text }}
          <button
            @click="deleteComment(comment.comment_id)"
            v-if="role === 'ROLE_ADMIN'"
            class="nostyle"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="16"
              height="16"
              fill="currentColor"
              class="bi bi-trash"
              viewBox="0 0 16 16"
            >
              <path
                d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"
              />
              <path
                fill-rule="evenodd"
                d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"
              />
            </svg>
          </button>
        </div>
        <p class="comment_date">
          {{ formatDate(comment.commentDate) }}
        </p>
      </div>
    </div>
    <div v-else>
      <p>Нет комментариев</p>
    </div>
    <div class="comment_more">
      <ul class="state">
        <li>
          <button
            class="nostyle"
            @click="fetchMoreComments()"
            v-if="
              this.commentPages[this.articleId] <
              commentNums[this.articleId] / 3
            "
          >
            Ещё комментарии
          </button>
        </li>
      </ul>
    </div>
    <div v-if="role === 'ROLE_USER'">
      <div class="comment_bar">
        <textarea
          type="input"
          cols="60"
          rows="14"
          wrap="soft"
          class="leave_comment"
          v-model="comment_text.comment_text"
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
            <button class="put_comm_btn" @click="submitComment()">
              Отправить комментарий
            </button>
          </li>
        </ul>
      </div>
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
      commentFlag: false,
      moreCommentsFlag: false,
      sentFlag: false,
      errors: {
        comment: "",
      },
      comment_text: {
        comment_text: "",
      },
      pages: {},
    };
  },
  computed: {
    ...mapState(useCatalog, [
      "id",
      "articleId",
      "commentFlags",
      "commentAll",
      "page",
      "commentPages",
      "role",
      "commentNums",
    ]),

    loadComments() {
      if (this.commentFlags[this.articleId]) {
        this.fetchComments();
      }
    },
  },
  methods: {
    ...mapActions(useCatalog, [
      "setCommentFlags",
      "setCommentAll",
      "setPage",
      "setCommentPages",
      "formatDate",
      "scrollToTop",
      "setCommentNums",
    ]),

    validateComment() {
      this.errors.comment =
        this.comment_text.comment_text.length <= 1000
          ? ""
          : "Comment has more than 1000 characters.";
    },

    async fetchComments() {
      await HomeDataService.getComments(
        this.articleId,
        this.commentPages[this.articleId]
      )
        .then((response) => {
          let existingComments = this.commentAll[this.articleId] || [];
          let newComments = response.data;

          this.setCommentAll({
            ...this.commentAll,
            [this.articleId]: [...existingComments, ...newComments],
          });
        })
        .catch((error) => {
          console.error("Ошибка при получении комментариев:", error);
        });
    },

    async deleteComment(article_id) {
      await HomeDataService.deleteComment(article_id)
        .then((response) => {
          this.setCommentPages({
            ...this.commentPages,
            [this.articleId]: 1,
          });
          this.setCommentAll({
            ...this.commentAll,
            [this.articleId]: [],
          });
          this.fetchComments();
          this.getCommentNum();
          console.log(response.data);
        })
        .catch((error) => {
          console.error("Ошибка при получении комментариев:", error);
        });
    },

    async fetchMoreComments() {
      this.moreCommentsFlag = !this.moreCommentsFlag;
      this.setCommentPages({
        ...this.commentPages,
        [this.articleId]: this.commentPages[this.articleId] + 1,
      });
      await this.fetchComments();
    },

    async submitComment() {
      console.log("Submit comment...");
      this.validateComment();
      if (!this.errors.comment) {
        console.log("Form submitted:", {
          comment: this.comment_text.comment_text,
        });
        try {
          await HomeDataService.createComment(
            this.id,
            this.articleId,
            this.comment_text
          ).then((response) => {
            console.log("createComment", response.data);
            this.setCommentPages({
              ...this.commentPages,
              [this.articleId]: 1,
            });
            this.setCommentAll({
              ...this.commentAll,
              [this.articleId]: [],
            });
            this.fetchComments();
            this.getCommentNum();
            this.sentFlag = true;
            this.comment_text.comment_text = "";
          });
        } catch (e) {
          console.log("e", e);
          this.e = "!";
        }
      }
    },

    async getCommentNum() {
      try {
        let commentNum = {};
        await HomeDataService.getCommentNum(this.articleId).then((response) => {
          commentNum[this.articleId] = response.data;
          console.log("getcommentNum", response.data);
        });
        this.setCommentNums(commentNum);
      } catch (e) {
        console.log("e", e);
      }
    },
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
  border-top: solid 1px rgba(160, 160, 160, 0.3);
  flex-grow: 1;
  padding: 0 2em 0 2em;
}

.leave_comment {
  margin-bottom: 20px;
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

.comment_date {
  font-size: 0.6em;
  text-align: right;
}

.comment_trash {
  display: flex;
  justify-content: space-between;
  margin: 15px 0 15px 0;
}
</style>
