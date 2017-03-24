var userId = sessionStorage["userId"];

function getAllTasksOfUser() {
    $.get("/host/tasks/user/" + userId, function (tasks) {
        drawTaskTable(tasks);

        $.each(tasks, function (index, task) {
            $('#user_tasks').on("click", "#" + task.id, function () {
                showTaskItems(task);
            });

            $('#user_tasks').on("click", "#change" + task.id, function () {
                changeTaskStatusToDb(task.id, task.status);
            });
        });
    });

}

function changeTaskStatusToDb(taskId, taskStatus) {
    $.ajax({
        type: 'PUT',
        url: "/host/tasks/" + taskId,
        data: {status: taskStatus}
    });

    $.get("/host/tasks/user/" + userId, function (tasks) {
        $('#user_tasks').empty();
        drawTaskTable(tasks);
    })
}

function drawTaskTable(tasks) {
    $.each(tasks, function (index, task) {
        var insertStatusLine;
        if (task.status === 'INCOMPLETE') {
            insertStatusLine = $("<label/>", {
                class: 'label label-warning',
                id: 'currentStatus' + task.id,
                text: 'INCOMPLETE'
            });
        } else if (task.status === 'COMPLETE') {
            insertStatusLine = $("<label/>", {
                class: 'label label-success',
                id: 'currentStatus' + task.id,
                text: 'COMPLETE'
            });
        }

        $('#user_tasks').append(
            $("<tr/>").append(
                $("<td/>", {
                    html: $("<button/>", {
                        class: 'btn btn-primary btn-sm',
                        id: task.id,
                        text: task.title
                    })
                }),
                $("<td/>", {html: insertStatusLine}),
                $("<td/>", {
                    html: $("<button/>", {
                        class: 'btn btn-info btn-circle',
                        id: 'change' + task.id,
                        text: 'Change Status'
                    })
                })
            )
        ); // end  $('#user_tasks').append(
    }) //end each
}

function showTaskItems(task) {
    $("#items").empty();

    $.get("/host/tasks/" + task.id, function (items) {
        $('td[style]').css('visibility', (items.length != 0) ? 'visible' : 'hidden');

        $.each(items, function (index, item) {
            $("#items").append(
                $("<tr/>").append(
                    $("<td/>", {text: item.description}),
                    $("<td/>", {
                        html: $("<span/>", {
                            class: 'label label-warning',
                            id: 'currentStatus',
                            text: item.status
                        })
                    }),
                    $("<td/>", {
                        html: $("<button/>", {
                            class: 'btn btn-danger',
                            id: 'closedItem' + item.id,
                            text: 'Closed'
                        })
                    }),
                    $("<td/>", {
                        html: $("<button/>", {
                            class: 'btn btn-danger',
                            id: 'deleteItem' + item.id,
                            text: 'Delete'
                        })
                    })
                )
            ); // end $("#items").append(

            $('#deleteItem' + item.id).on('click', function () {
               deleteItem(item.id);
            })

        }); //end each

        $("#items").append(
            $("<tr/>").append(
                $("<td/>", {
                    html: $("<button/>", {
                        class: 'btn btn-info',
                        id: 'addItem' + task.id,
                        text: 'Add'
                    })
                })
            )
        ); //end append add item button

        $('#addItem' + task.id).on('click', function () {
            var url = "create_item.html";
            $(location).attr('href', url);
            sessionStorage['currentTaskId'] = task.id;
        })

    }); //end $.get function
}

function deleteItem(itemId) {
    $.ajax({
       type: 'DELETE',
        url: '/host/tasks/items/' + itemId
    });
}