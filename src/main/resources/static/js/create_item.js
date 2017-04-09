function addNewItemInTask() {
    var description = $('#description').val();
    var taskId = sessionStorage.getItem('taskId');

    $.post('/host/tasks/items', {
        taskId: taskId,
        description: description
    }, 'json');
}