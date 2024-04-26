<script setup> 
import updateArticle from '../../components/updateArticle.vue'; 
import createArticle from '../../components/createArticle.vue'; 
import comments from '../../components/comments.vue'; 
</script> 

<template>
    <div class="sub_header"></div>
    <div class="news_page">
        <createArticle class="createArticleBtn" /> 
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
                    <li @click="deleteArticle">
                        Удалить статью
                    </li>
                    <li>
                        <updateArticle class="updateArticleBtn" />
                    </li>
                    <li>
                        <button class="unstyled-button" @click="moreArticlesFlag = !moreArticlesFlag">
                            {{ !moreArticlesFlag ? 'Показать больше' : 'Показать меньше' }}
                        </button>
                    </li>
                    <li>
                        <button class="unstyled-button" @click="likedFlag = !likedFlag">
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
                        <button class="unstyled-button" @click="commentFlag = !commentFlag">
                            15
                        </button>
                    </li>
                </ul>
            </footer>
        <comments/>
        </article>
        <div class="space">
        </div>
    </div>
</template>

<script>
import { useCatalog } from '../../store/catalog.js';
import { mapState, mapActions } from 'pinia';
import HomeDataService from '../../services/HomeDataService'

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
            },

            comments: {

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
        },
    },
    mounted() {
        this.date = this.printDate();
        this.time = this.printTime();
    },
}
</script>

<style scoped src="./home.css"></style>