<template>
    <div class="sub_header"></div>
    <div class="news_page">
        <article class="post">
            <header>
                <div class="title">
                    <h2>
                        Самая свежая новость
                    </h2>
                    <p>
                        #Теги #самой #свежей #новости
                    </p>
                </div>
                <div class="meta">
                    <time class="published">
                        {{ date }}
                    </time>
                </div>
            </header>
            <a class="image_main">
                <img src="/img/rain.jpg" alt="" />
            </a>
            <p>
                Mauris neque quam, fermentum ut nisl vitae,
                convallis maximus nisl. Sed mattis nunc id lorem euismod
                placerat. Vivamus porttitor magna enim, ac accumsan tortor
                cursus at. Phasellus sed ultricies mi non congue ullam corper.
                Praesent tincidunt sed tellus ut rutrum. Sed vitae justo condimentum, porta
                lectus vitae, ultricies congue gravida diam non fringilla.
            </p>
            <p>
                Mauris neque quam, fermentum ut nisl vitae,
                convallis maximus nisl. Sed mattis nunc id lorem euismod
                placerat. Vivamus porttitor magna enim, ac accumsan tortor
                cursus at. Phasellus sed ultricies mi non congue ullam corper.
                Praesent tincidunt sed tellus ut rutrum. Sed vitae justo condimentum, porta
                lectus vitae, ultricies congue gravida diam non fringilla.
            </p>
            <p v-show="moreArticlesFlag">
                Mauris neque quam, fermentum ut nisl vitae,
                convallis maximus nisl. Sed mattis nunc id lorem euismod
                placerat. Vivamus porttitor magna enim, ac accumsan tortor
                cursus at. Phasellus sed ultricies mi non congue ullam corper.
                Praesent tincidunt sed tellus ut rutrum. Sed vitae justo condimentum, porta
                lectus vitae, ultricies congue gravida diam non fringilla.
            </p>
            <footer>
                <ul class="stats">
                    <li>
                        <button 
                        class="unstyled-button" 
                        @click="moreArticlesFlag = !moreArticlesFlag">
                            {{ !moreArticlesFlag ? 'Показать больше' : 'Показать меньше' }}
                        </button>
                    </li>
                    <li>
                        <button 
                        class="unstyled-button" 
                        @click="likedFlag = !likedFlag">
                            <img class="mini" src="/img/heart.svg" alt="mini like" v-show="!likedFlag" />
                            <img class="mini" src="/img/blue-heart.svg" alt="mini like" v-show="likedFlag" />
                        </button>
                        <button class="unstyled-button">
                            3
                        </button>
                    </li>
                    <li>
                        <button class="unstyled-button" @click="commentFlag = !commentFlag">
                            <img class="mini" src="/img/comment2.svg" alt="mini like" v-show="!commentFlag" />
                            <img class="mini" src="/img/comment-open.svg" alt="mini like" v-show="commentFlag" />
                        </button>
                        <button 
                        class="unstyled-button" 
                        @click="commentFlag = !commentFlag">
                            15
                        </button>
                    </li>
                </ul>
            </footer>
            <div class="comment" v-show="commentFlag">
                <div class="title_comment">
                    <p>Lorem ipsum dolor sit amet, qui in ea voluptate velit esse,
                        quam nihil molestiae consequatur, vel illum, quae ab illo
                        inventore veritatis et quasi architecto beatae vitae dicta
                        sunt, explicabo. Sed ut perspiciatis, consectetur adipiscing
                        elit, sunt in culpa qui officia deserunt mollit anim id est laborum.
                    </p>
                    <h3>
                        Комментарий 1
                    </h3>
                </div>
                <div class="title_comment">
                    <p>Lorem ipsum dolor sit amet, qui in ea voluptate velit esse,
                        quam nihil molestiae consequatur, vel illum, quae ab illo
                        inventore veritatis et quasi architecto beatae vitae dicta
                        sunt, explicabo. Sed ut perspiciatis, consectetur adipiscing
                        elit, sunt in culpa qui officia deserunt mollit anim id est laborum.
                    </p>
                    <h3>
                        Комментарий 2
                    </h3>
                </div>
                <div class="title_comment">
                    <p>Lorem ipsum dolor sit amet, qui in ea voluptate velit esse,
                        quam nihil molestiae consequatur, vel illum, quae ab illo
                        inventore veritatis et quasi architecto beatae vitae dicta
                        sunt, explicabo. Sed ut perspiciatis, consectetur adipiscing
                        elit, sunt in culpa qui officia deserunt mollit anim id est laborum.
                    </p>
                    <h3>
                        Комментарий 3
                    </h3>
                </div>
                <div class="comment_more">
                    <ul class="state">
                        <li>
                            <button 
                            class="unstyled-button" 
                            @click="moreCommentsFlag = !moreCommentsFlag">
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
                    v-model="myNewComment" 
                    @input="validateComment"
                    placeholder="Оставить комментарий">
                    </textarea>
                </div>
                <p v-if="errors.comment">{{ errors.comment }}</p>
                <div class="comment_more">
                    <ul class="state">
                        <li>
                            <button class="unstyled-button" @click="sentFlag = true; submitComment;">
                                Отправить комментарий
                            </button>
                        </li>
                    </ul>
                </div>
            </div>

        </article>
        <div class="space">
        </div>
    </div>
</template>

<script>
import { useCatalog } from '../../store/catalog.js';
import { mapState, mapActions } from 'pinia';

export default {
    name: 'App',
    data() {
        return {
            size: 30,
            picture: '',
            date: '',
            time: '',
            myNewComment: '',
            commentFlag: false,
            likeFlag: false,
            moreArticlesFlag: false,
            moreCommentsFlag: false,
            sentFlag: false,
            likedFlag: false,
            errors: {
                comment: ''
            }
        };
    },
    methods: {
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
            this.errors.comment = this.myNewComment.length <= 100 ? "" : "Comment has more than 1000 characters.";
        },
        submitComment() {
            this.validateComment();
            if (!this.errors.comment) {
                console.log("Form submitted:", { comment: this.myNewComment });
            }
        }
    },
    mounted() {
        this.date = this.printDate();
        this.time = this.printTime();
    },
}
</script>

<style scoped src="./home.css"></style>