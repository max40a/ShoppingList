function createItem() {
    var taskId = sessionStorage['currentTaskId'];
    var description = $('#description').val();
    
    $.post("/host/tasks/items/", {
        taskId: taskId,
        description: description
    }, "json");
}
