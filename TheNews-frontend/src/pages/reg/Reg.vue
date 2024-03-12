<template>
    <div class="reg_page">
    <div class="reg_block">
        <h1 class="reg_header">Регистрация </h1>
        <div class="reg_container">
            <div class="name_input">
                <input
                    type="text"
                    placeholder=" Имя"
                    id="firstname"
                    required=""
                />
                <input
                    type="text"
                    placeholder=" Фамилия"
                    id="lastname"
                    required=""
                />
            </div>
            <div class="info_input">
                <input
                    type="text"
                    placeholder=" Email"
                    id="email"
                    required=""
                />
                <input
                    type="text"
                    placeholder=" Пароль"
                    id="password"
                    required=""
                />
            </div>
            <div class="reg_btn">
                <button type="button"> Создать аккаунт </button>
            </div>
            <div class="login_ref">
                <router-link :to="{ name: 'loginPage' }">
                    <a href="url">Есть аккаунт? Войти</a>
                </router-link>
            </div>
        </div>
    </div>
</div>
</template>

<script>

</script>

<style scoped src="./reg.css"></style>