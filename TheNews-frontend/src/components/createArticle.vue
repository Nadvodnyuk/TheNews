<template>
    <div>
        <button v-if="!isFormVisible" @click="openCreateArticleForm" class="createArticleBtn">Создать статью</button>
        <div v-if="isFormVisible" class="createArtForm">
            <h2 class="createFormHeader">Форма создания статьи</h2>
            <input v-model="article.title" placeholder="Заголовок" class="artTitle">
            <input v-model="article.tags" placeholder="Теги" class="artTags">
            <textarea v-model="article.content" placeholder="Текст статьи" class="artText"></textarea>
            <div class="file-input">
                <input type="file" @change="handleFileUpload" class="artImg" id="fileUpload">
            </div>
            <button @click="createArticle" class="createArticleBtn">Создать</button>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            isFormVisible: false,
            article: {
                title: '',
                tags: '',
                content: '',
                image: null
            }
        }
    },
    methods: {
        openCreateArticleForm() {
            this.isFormVisible = true;
        },
        handleFileUpload(event) {
            this.article.image = event.target.files[0];
        },
        createArticle() {
            // Логика для отправки данных статьи на сервер 
            // Используйте например Axios для отправки запроса 
            // В this.article у вас будут все данные статьи, включая изображение 
            // Не забудьте закрыть форму после успешной отправки статьи 
        }
    }
}
</script>

<style>
.createArticleBtn {
    background-color: white;
}

.createArtForm {
    display: flex;
    flex-direction: column;
    width: 1000px;
    margin: 0 auto;
    height: auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
}

.artTitle,
.artTags,
.artText,
.artImg {
    width: 100%;
    height: 10px;
    padding: 20px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 20px;
    margin-bottom: 20px;
}

.artText {
    overflow: hidden;
    resize: vertical;
    /* Разрешает вертикальное изменение размера поля */
    overflow-y: auto;
    border-radius: 5px;
    min-height: 50px;
    height: auto;
}

.artImg {
    height: 65px;
    position: relative;
    overflow: hidden;
    display: inline-block;
}

.file-input {
    align-items: center;
}
</style>