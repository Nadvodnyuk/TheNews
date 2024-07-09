<template>
  <div>
    <button
      v-if="!isFormVisible"
      @click="openTopicsForm"
      class="topicFilterBtn"
    >
      Настроить тематики
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
      <div class="topicsBodyTitle">
        <div class="checkboxGroupTitle">
          <div class="topicLabelTitle">Тема</div>
          <label class="checkboxLabelTitle">Любимая</label>
          <label class="checkboxLabelTitle">Запретная</label>
        </div>
        <div
          v-for="(topic, index) in formattedThemes"
          :key="index"
          class="topicItem"
        >
          <div class="topicLabel">{{ topic }}</div>
          <div class="checkboxGroup">
            <label class="checkboxLabel">
              <input
                type="checkbox"
                v-model="selectedFavoriteFlag[index]"
                class="checkbox favorite"
                :disabled="selectedDislikedFlag[index]"
              />
            </label>
            <label class="checkboxLabel">
              <input
                type="checkbox"
                v-model="selectedDislikedFlag[index]"
                class="checkbox disliked"
                :disabled="selectedFavoriteFlag[index]"
              />
            </label>
          </div>
        </div>
      </div>
      <div class="topicsFormFooter">
        <button @click="saveTopicsForm" class="saveBtn">Сохранить</button>
      </div>
    </div>
  </div>
</template>

<script>
import HomeDataService from "../services/HomeDataService";
import { useCatalog } from "../store/catalog.js";
import { mapState, mapActions } from "pinia";

export default {
  data() {
    return {
      themes: 0,
      isFormVisible: false,
      selectedFavoriteFlag: [],
      selectedDislikedFlag: [],
      selectedFavorite: [],
      selectedDisliked: [],
    };
  },
  computed: {
    ...mapState(useCatalog, ["theme", "favorite", "blocked"]),
    formattedThemes() {
      this.themes = this.theme[0];
      console.log(this.themes, this.theme);
      console.log(this.favorite, this.blocked);
      return this.themes;
    },
  },
  methods: {
    ...mapActions(useCatalog, ["setFavorite", "setBlocked", "setArticleAll"]),
    openTopicsForm() {
      this.isFormVisible = true;
      this.selectedFavoriteFlag = this.formattedThemes.map((theme) =>
        this.favorite.includes(theme)
      );
      this.selectedDislikedFlag = this.formattedThemes.map((theme) =>
        this.blocked.includes(theme)
      );
    },
    closeTopicsForm() {
      this.isFormVisible = false;
    },

    async getAll() {
      try {
        await HomeDataService.getAll().then((response) => {
          console.log(response.data);
          this.setArticleAll(response.data);
        });
      } catch (e) {
        this.error = "Проверьте все поля!";
      }
    },

    async saveTopicsForm() {
      this.selectedFavorite = this.formattedThemes.filter(
        (_, index) => this.selectedFavoriteFlag[index]
      );
      this.selectedDisliked = this.formattedThemes.filter(
        (_, index) => this.selectedDislikedFlag[index]
      );
      this.isFormVisible = false;
      console.log("Favorite", this.selectedFavorite);
      console.log("Disliked", this.selectedDisliked);

      await HomeDataService.saveTopics({
        favorite: this.selectedFavorite,
        disliked: this.selectedDisliked,
      })
        .then((response) => {
          this.setFavorite(this.selectedFavorite);
          this.setBlocked(this.selectedDisliked);
          this.getAll();
          console.log("Data saved successfully", response.data);
        })
        .catch((error) => {
          console.error("Error saving data", error);
        });
    },
  },
};
</script>

<style scoped>
.topicFilterBtn {
  background-color: white;
}

.topicsForm {
  width: 100%;
  max-width: 600px;
  margin: 0 auto;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 20px;
}

.topicsFormHeader {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.topicsTitle {
  font-size: 18px;
}

.closeBtn {
  background-color: transparent;
  border: none;
  cursor: pointer;
  font-size: 18px;
}

.topicsBody {
  display: flex;
  flex-direction: column;
}

.topicsBodyTitle {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
}

.topicItem {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.topicLabel {
  min-width: 140px;
  margin-right: 10px;
}

.topicLabelTitle {
  min-width: 140px;
}

.checkboxGroup {
  display: flex;
  align-items: center;
}

.checkboxGroupTitle {
  display: flex;
  margin-bottom: 20px;
}

.checkboxLabel {
  margin-right: 20px;
}

.checkboxLabelTitle {
  margin-left: 20px;
}

.checkbox {
  appearance: none;
  width: 20px;
  height: 20px;
  border: 2px solid #ccc;
  border-radius: 50%;
  cursor: pointer;
  position: relative;
  transition: background-color 0.3s, border-color 0.3s;
}

.checkbox.favorite:checked {
  background-color: #4caf50;
  border-color: #4caf50;
}

.checkbox.favorite {
  margin-left: 40px;
  margin-right: 40px;
}

.checkbox.disliked:checked {
  background-color: #f44336;
  border-color: #f44336;
}

.checkbox:disabled {
  cursor: not-allowed;
}

.checkbox:checked:after {
  content: "";
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 10px;
  height: 5px;
  border-left: 2px solid #fff;
  border-bottom: 2px solid #fff;
  transform-origin: bottom left;
  transition: transform 0.3s;
}

.checkbox.disliked:checked:after {
  font-size: 12px;
  color: #fff;
}

.checkbox:checked:after,
.checkbox.disliked:checked:after {
  display: block;
}

.checkbox:not(:checked):after,
.checkbox.disliked:not(:checked):after {
  display: none;
}

.topicsFormFooter {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}

.saveBtn {
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
}

.saveBtn:hover {
  background-color: #45a049;
}
</style>
