<html>
<#include "base.ftl">
<#macro title>Edit account info</#macro>
<#macro content>
<div class="container mt-5">
    <form action="edit" class="was-validated" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="name" class="form-label">Имя:</label>
            <input type="text" class="form-control" id="name" placeholder="Введите имя" name="name" pattern="^$|.{2,50}">
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">Имя должно содержать как минимум 2 символа(или не содержать ничего).</div>
        </div>
        <div class="mb-3 mt-3">
            <label for="username" class="form-label">Email:</label>
            <input type="text" class="form-control" id="username" placeholder="Введите почту" name="email" pattern=".+@.+">
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">Введеная строка точно не почта.</div>
        </div>

        <div class="mb-3">
            <label for="selfInfo" class="form-label">SelfInfo:</label>
            <input type="text" class="form-control" id="selfInfo" placeholder="Введите информацию о себе" name="selfInfo" pattern="^$|.{1,500}">
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">Информация должна содержать от 3 до 500 символов(или не содержать ничего).</div>
        </div>
        <div class="mb-3">
            <label for="picture" class="form-label">Upload a picture:</label>
            <input type="file" class="form-control" id="picture" name="picture">
        </div>
        <button type="submit" class="btn btn-primary">Отправить</button>
    </form>
</div>
    <div class="modal" tabindex="-1" role="dialog" id="errorModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Ошибка ввода данных</h5>
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
            if (typeof errorMessage !== "undefined" && errorMessage !== "") {
                errorModal.show();
            }
        });
    </script>

</#macro>
</html>
