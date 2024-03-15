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
                        <a href="#">Показать больше</a>
                    </li>
                    <li>
                        <a target="_blank">
                            <img class="mini" src="/img/heart.svg" alt="mini like" />
                        </a>
                        <a href="#" class="icon solid fa-heart">3</a>
                    </li>
                    <li>
                        <a target="_blank">
                            <img class="mini" src="/img/comment2.svg" alt="mini like" />
                        </a>
                        <a href="#" class="icon solid fa-comment">15</a>
                    </li>
                </ul>
            </footer>
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