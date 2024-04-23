<template>
    <ul class="nav_bar">
        <li class="nb_li">
            <router-link :to="{ name: 'loginPage' }" class="nb_link">
                Войти
            </router-link>
        </li>
        <li class="nb_li">
            <button class="nb_li" @click="logoutFoo" type="button"> Выйти </button>
        </li>
    </ul>
</template>

<script>
import HomeDataService from '../../services/HomeDataService';
import { defineAsyncComponent } from 'vue';

export default {
    name: 'TheNav',
    data() {
        return {
            whoami: '',
        };
    },
    methods: {
        async logoutFoo() {
            try {
                const response = await HomeDataService.logout();
                console.log(response);
                this.$router.push('/');
            } catch (e) {
                this.error = 'Не прошло!';
            }
        },
        async retrieveMe() {
            HomeDataService.me()
                .then(response => {
                    this.whoami = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        },
        // scrollToTop() {
        //     // Прокручиваем страницу наверх (координаты 0, 0)
        //     window.scrollTo(0, 0);
        // },

    },
    mounted() {
    },
}
</script>

<style scoped lang="css">
.nav_bar {
    list-style: none;
}

.nb_link {
    color: #464d4e;
    font-weight: 100;
    font-size: 18px;
    line-height: 36px;
    text-align: end;
    text-decoration: none;
    margin-left: 40px;
}

.nb_link:hover {
    color: rgb(88, 93, 99);
}

ul {
    margin-bottom: 0;
}

@media (min-width:300px) and (max-width: 600px) {
    .nb_link {
        color: #464d4e;
        font-weight: 600;
        font-size: 18px;
        line-height: 36px;
        text-align: end;
        text-decoration: none;
        margin-left: 15px;
        line-height: 2.2;
    }
}
</style>