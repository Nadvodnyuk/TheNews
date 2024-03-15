<template>
    <div class="sub_header"></div>
    <div class="news_page">
        <article class="post">
            <header>
                <div class="title">
                    <h2>Самая свежая новость</h2>
                    <p>Теги самой свежей новости</p>
                </div>
                <div class="meta">
                    <time class="published" datetime="2024-03-12">{{ date }}</time>
                </div>
            </header>
            <a class="image_main"><img src="/img/rain.jpg" alt="" /></a>
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
            <footer>
                <ul class="stats">
                    <li>
                        <button class="unstyled-button">Показать больше</button>
                    </li>
                    <li>
                        <button class="unstyled-button">
                            <img class="mini" src="/img/heart.svg" alt="mini like" />
                        </button>
                        <button class="unstyled-button">3</button>
                    </li>
                    <li>
                        <button class="unstyled-button">
                            <img class="mini" src="/img/comment2.svg" alt="mini like" />
                        </button>
                        <button class="unstyled-button">15</button>
                    </li>
                </ul>
            </footer>
            <div class="comment">
                <div class="title_comment">
                    <p>Lorem ipsum dolor sit amet, qui in ea voluptate velit esse,
                        quam nihil molestiae consequatur, vel illum, quae ab illo
                        inventore veritatis et quasi architecto beatae vitae dicta
                        sunt, explicabo. Sed ut perspiciatis, consectetur adipiscing
                        elit, sunt in culpa qui officia deserunt mollit anim id est laborum.
                    </p>
                    <h3>Комментарий 1</h3>
                </div>
                <div class="title_comment">
                    <p>Lorem ipsum dolor sit amet, qui in ea voluptate velit esse,
                        quam nihil molestiae consequatur, vel illum, quae ab illo
                        inventore veritatis et quasi architecto beatae vitae dicta
                        sunt, explicabo. Sed ut perspiciatis, consectetur adipiscing
                        elit, sunt in culpa qui officia deserunt mollit anim id est laborum.
                    </p>
                    <h3>Комментарий 2</h3>
                </div>
                <div class="title_comment">
                    <p>Lorem ipsum dolor sit amet, qui in ea voluptate velit esse,
                        quam nihil molestiae consequatur, vel illum, quae ab illo
                        inventore veritatis et quasi architecto beatae vitae dicta
                        sunt, explicabo. Sed ut perspiciatis, consectetur adipiscing
                        elit, sunt in culpa qui officia deserunt mollit anim id est laborum.
                    </p>
                    <h3>Комментарий 3</h3>
                </div>
                <div class="comment_more">
                    <ul class="state">
                        <li>
                            <button class="unstyled-button">Показать больше</button>
                        </li>
                    </ul>
                </div>
                <div class="comment_bar">
                    <input 
                    type="input" 
                    class="leave_comment" 
                    v-model="myNewComment" 
                    placeholder="Оставить комметарий"
                    >
                </div>
                <div class="comment_more">
                    <ul class="state">
                        <li>
                            <button class="unstyled-button">Отправить комментарий</button>
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
            commentFlag: false,
            likeFlag: false,
            moreFlag: false,
            date: '',
            myNewComment: '',
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
    },
    mounted() {
        this.date = this.printDate();
    },
}
</script>

<style scoped src="./home.css"></style>