function createTask() {
    var userId = sessionStorage["userId"];
    var title = $('#title').val();
    $.post("/host/tasks", {
            title: title,
            userId: userId
        }, "json"
    );
}