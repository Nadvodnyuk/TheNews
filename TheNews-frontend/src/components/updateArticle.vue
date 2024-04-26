<template>
    <div>
    <button v-if="!isFormVisible" @click="openupdateArticleForm" class="updateArticleBtn">РЕДАКТИРОВАТЬ СТАТЬЮ</button>
    <div v-if="isFormVisible" class="updateArtForm">
            <div class="updateFormHeader" ><div class="updateArtTitle"><h2 >Форма редактирования статьи</h2></div><div><button @click="closeupdateArticleForm" class="closeBtn">✖</button></div></div>
            <input v-model="article.title" placeholder="Заголовок" class="artTitle">
            <span v-if="validationErrors.title" class="error-message">{{ validationErrors.title }}</span>
            <input v-model="article.tags" placeholder="Теги" class="artTags">
            <span v-if="validationErrors.tags" class="error-message">{{ validationErrors.tags }}</span>
            <textarea v-model="article.content" placeholder="Текст статьи" class="artText"></textarea>
            <span v-if="validationErrors.content" class="error-message">{{ validationErrors.content }}</span>
            <div class="file-input">
                <input type="file" @change="handleFileUpload" class="artImg" id="fileUpload">
                <span v-if="validationErrors.image" class="error-message">{{ validationErrors.image }}</span>
            </div >
            <div class="publishDiv"><button @click="updateArticleAndCloseForm" class="publishArticleBtn">Сохранить изменения</button></div>
            </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        isFormVisible: false,
        isArticleupdated: false,
        article: {
          title: '',
          tags: '',
          content: '',
          image: null
        },
        validationErrors: {
        title: '',
        tags: '',
        content: ''
      }
      }
    },
    methods: {
        validateForm() {
        let isValid = true;
        this.validationErrors = {
        title: '',
        tags: '',
        content: ''
        };

        if (!this.article.title.trim()) {
            this.validationErrors.title = 'Пожалуйста, введите заголовок статьи';
            isValid = false;
        } else if (this.article.title.length > 255) {
            this.validationErrors.title = 'Максимальная длина заголовка - 255 символов';
            isValid = false;
        }

        if (!this.article.tags.trim()) {
            this.validationErrors.tags = 'Пожалуйста, введите теги статьи';
            isValid = false;
        }

        if (!this.article.content.trim()) {
            this.validationErrors.content = 'Пожалуйста, введите текст статьи';
            isValid = false;
        }

        if (!this.article.image) {
            this.validationErrors.image = 'Пожалуйста, выберите изображение';
            isValid = false; 
        }

        return isValid;
        },
        openupdateArticleForm() {
            this.isFormVisible = true;
        },
        closeupdateArticleForm() {
            this.isFormVisible = false;
            this.validationErrors='';
        },

        handleFileUpload(event) {
            this.article.image = event.target.files[0];
        },
        updateArticleAndCloseForm() {
            if (!this.validateForm()) {
            return;
            }
            this.isFormVisible = false;
            this.isArticleupdated = true;
        }

    }
  }
  </script>

<style>

.updateArticleBtn {
    background-color: white;
}

.updateArtTitle {
    margin-right: 175px;
}

.updateFormHeader {
    display:flex;
    justify-content: right;
}

.closeBtn {
    background-color: white;
    width: 30px;
    align-items: center;
    float: right; /* Помещаем кнопку вправо */
    margin-right: 0px;
    text-align: center;
    padding-right: 30px;
    padding-top: 10px;
}

.updateArtForm {
    display: flex;
    flex-direction: column;
    width: 800px;
    margin: 0 auto;
    height: auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
}

.artTitle,.artTags,.artText,.artImg {
    width: 100%;
    height: 10px;
    padding: 20px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 20px;
    margin-top: 20px;
}

.artText {
    overflow: hidden;
    resize: vertical; /* Разрешает вертикальное изменение размера поля */
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

.success-message {
  color: green;
  margin-top: 10px;
}

.error-message {
  color: red;
  margin-top: 1px;
  margin-left: 21px;
  margin-bottom: 5px;
}

.publishDiv {
    display: flex;
    justify-content: center;
}

.publishArticleBtn {
    margin-top: 20px;
    background-color: white;
    width: 105px;
}

</style>