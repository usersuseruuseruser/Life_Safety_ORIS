<html>
<#include "base.ftl">
<#macro title>Registration page</#macro>
<#macro content>
<div class="container mt-5">
    <form action="registration" class="was-validated" method="post">

        <div class="mb-3 mt-3">
            <label for="username" class="form-label">Логин:</label>
            <input type="text" class="form-control" id="username" placeholder="Введите логин" name="login" required pattern=".{3,}">
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">Логин должен содержать как минимум 3 символа.</div>
        </div>

        <div class="mb-3">
            <label for="name" class="form-label">Имя:</label>
            <input type="text" class="form-control" id="name" placeholder="Введите имя" name="name" required pattern=".{2,}">
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">Имя должно содержать как минимум 2 символа.</div>
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Пароль:</label>
            <input type="password" class="form-control" id="password" placeholder="Введите пароль" name="password" required pattern=".{8,}">
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">Пароль должен содержать как минимум 8 символов.</div>
        </div>

        <button type="submit" class="btn btn-primary">Отправить</button>
    </form>
</div>

    <div class="modal" tabindex="-1" role="dialog" id="errorModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Ошибка регистрации</h5>
                    <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>${errorMessage}</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var errorModal = new bootstrap.Modal(document.getElementById('errorModal'));
            errorModal.show();
        });
    </script>
</#macro>
</html>
