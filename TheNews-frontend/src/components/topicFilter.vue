<template>
  <div>
    <button v-if="!isFormVisible" @click="openTopicsForm" class="topicFilterBtn"> Настроить тематики
    </button>
    <div v-if="isFormVisible" class="topicsForm">
      <div class="topicsFormHeader">
        <div class="topicsTitle">
          <h2>Настройте тематики</h2>
        </div>
        <div>
          <button @click="closeTopicsForm" class="closeBtn">✖</button>
        </div>
      </div>
      <div>
        <div class="topicsBody">
          <div class="favTopics">
            <h3>Любимые</h3>
            <a-select v-model="selectedFavorite" mode="multiple" style="width: 300px" placeholder="Любимые..."
              @change="handleFavoriteChange">
              <a-select-option v-for="topic in availableFavoriteTopics" :key="topic.value" :value="topic.value">
                {{ topic.label }}
              </a-select-option>
            </a-select>
          </div>


          <div class="forbTopics">
            <h3>Запретные</h3>
            <a-select v-model="selectedDisliked" mode="multiple" style="width: 300px" placeholder="Запретные..."
              @change="handleDislikedChange">
              <a-select-option v-for="topic in availableDislikedTopics" :key="topic.value" :value="topic.value">
                {{ topic.label }}
              </a-select-option>
            </a-select>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import { useCatalog } from "../store/catalog.js";
import { mapState, mapActions } from "pinia";
import HomeDataService from "../services/HomeDataService";

export default {
  data() {
    return {
      themes: [],
      isFormVisible: false,
      selectedFavorite: [],
      selectedDisliked: [],
      showFavoriteList: false,
      showDislikedList: false
    }
  },
  computed: {
    ...mapState(useCatalog, ["theme"]),
    formattedThemes() {
      this.themes = this.theme[0];
      return this.themes;
    },
    availableFavoriteTopics() {
      return this.formattedThemes
        .filter(topic => !this.selectedDisliked.includes(topic))
        .map(topic => ({ value: topic, label: topic }));
    },
    availableDislikedTopics() {
      return this.formattedThemes
        .filter(topic => !this.selectedFavorite.includes(topic))
        .map(topic => ({ value: topic, label: topic }));
    }
  },
  methods: {
    openTopicsForm() {
      this.isFormVisible = true;
    },
    closeTopicsForm() {
      this.isFormVisible = false;
    },

    saveTopicsForm() {
      this.isFormVisible = false;
      this.isTopicSaved = true;
      this.saveTopics();
    },

    async saveTopics() {

    },

    updateFavorite() {
      this.selectedDisliked = this.selectedDisliked.filter(topic => !this.selectedFavorite.includes(topic));
    },
    updateDisliked() {
      this.selectedFavorite = this.selectedFavorite.filter(topic => !this.selectedDisliked.includes(topic));
    },
    handleFavoriteChange() {
      // фильтруем выбранные запретные темы, чтобы избежать дублирования
      this.selectedDisliked = this.selectedDisliked.filter((topic) => !this.selectedFavorite.includes(topic));
    },
    // обработчик изменений в списке запретных тем
    handleDislikedChange() {
      // фильтруем выбранные любимые темы, чтобы избежать дублирования
      this.selectedFavorite = this.selectedFavorite.filter((topic) => !this.selectedDisliked.includes(topic));
    },
  }
}
</script>

<style>
.topicFilterBtn {
  background-color: white;
}

.topicsForm {
  display: flex;
  flex-direction: column;
  width: 1000px;
  min-height: 250px;
  height: auto;
  margin: 0 auto;
  height: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.topicsFormHeader {
  display: flex;
  flex-direction: row;
}


.topicsTitle {
  margin-left: 365px;
}

.closeBtn {
  margin-left: 315px;
}

.topicsBody {
  display: flex;
  background-color: white;
  align-items: center;
  justify-content: space-evenly;
  margin-bottom: 10px;
}

.favTopics,
.forbTopics {
  background-color: white;
  width: 350px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.favTopics a-select,
.forbTopics a-select {
  width: 100%;
}

.topicLabel {
  margin-left: 50px;
}
</style>