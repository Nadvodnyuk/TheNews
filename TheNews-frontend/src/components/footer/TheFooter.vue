<template>
  <footer class="mainFooter">
    <div class="footer-text">
      <div class="social-links">
        <span class="social github">
          <a
            class="link"
            href="https://github.com/Nadvodnyuk/TheNews"
            target="_blank"
          >
            Github
          </a>
        </span>
      </div>
      <p>© 2024 TheNews. All Right Not Reserved.</p>
    </div>
  </footer>
</template>

<style scoped lang="css">
.mainFooter {
  width: 100%;
  height: 100px;
  display: flex;
  justify-content: center;
  text-align: center;
  align-items: center;
  background-color: white;
  padding-top: 20px;
  font-size: 14px;
  font-weight: 200;
  color: darkgrey;
  margin-top: -3em;
}

.link {
  color: #464d4e;
  align-items: center;
  justify-content: center;
  display: flex;
  transition: all 0.3s;
  width: 80px;
  font-weight: 600;
}

.social-links {
  align-items: center;
  justify-content: center;
  display: flex;
}
</style>
