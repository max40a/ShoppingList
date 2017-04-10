var tempId = 1;

function getAllTasks() {
    $.get("/host/tasks/user/" + tempId, function (tasks) {
        fillTaskTable(tasks);

        $.each(tasks, function (index, task) {
            $('#tasks').on("click", '#show_item_task_' + task.id, function () {

                if ($('#itemsList').is(':hidden')) {
                    $('#itemsList').show();
                } else if ($('#itemsList').is(':visible')) {
                    $('#itemsList').hide();
                }

                $.get('/host/tasks/' + task.id, function (items) {
                    fillItemTable(items);
                });
                sessionStorage.setItem('currentTaskId', task.id);
            });

            $('#tasks').on('click', '#create_item_for_task_id_' + task.id, function () {
                sessionStorage.setItem('taskId', task.id);
                open('http://localhost:8080/html/create_item.html',
                    'newItem',
                    'height=300, width=300, left=533, top=150');
            })
        });
    });
}

function fillTaskTable(tasks) {
    $.each(tasks, function (index, task) {
        var countOfAllItemsInTask = task.items.length;
        var countOfUnfinishedItem = calculateUnfinishedItemsForTask(task);
        var progress = calculatePercentageOfFillingProgressLine(countOfAllItemsInTask, countOfUnfinishedItem);

        var taskStatusLabelClass;
        if (task.status == 'INCOMPLETE') {
            taskStatusLabelClass = 'label label-warning';
        } else if (task.status == 'COMPLETE') {
            taskStatusLabelClass = 'label label-success';
        }

        $('#tasks').append(
            $('<tr>').append(
                $('<td>', {
                    colspan: '5'
                }).append(
                    $('<table>', {
                        class: 'table'
                    }).append(
                        $('<tr>').append(
                            $('<td >', {
                                colspan: '5',
                                align: 'center',
                                html: $('<div>', {
                                    class: 'alert alert-info',
                                    text: task.title
                                })
                            })
                        )
                    )
                )
            ),
            $('<tr>').append(
                $('<td>', {
                    html: $('<button>', {
                        id: 'show_item_task_' + task.id,
                        class: 'btn btn-default btn-md',
                        ariaLabel: 'Center Align',
                        html: $('<span>', {
                            class: 'glyphicon glyphicon-eye-open'
                        })
                    })
                }),
                $('<td>', {
                    html: $('<label>', {
                        id: 'task_status_label_' + task.id,
                        class: taskStatusLabelClass,
                        text: task.status
                    })
                }),
                $('<td>', {
                    html: $('<button>', {
                        class: 'btn btn-default btn-md',
                        ariaLabel: 'Center Align',
                        html: $('<span>', {
                            class: 'glyphicon glyphicon-play',
                            ariaHidden: 'true'
                        })
                    })
                }),
                $('<td>', {
                    html: $('<button>', {
                        id: 'create_item_for_task_id_' + task.id,
                        class: 'btn btn-default btn-md',
                        ariaLabel: 'Center Align',
                        html: $('<span>', {
                            class: 'glyphicon glyphicon-plus',
                            ariaHidden: 'true'
                        })
                    })
                })
            ),
            $('<tr>').append(
                $('<td>', {
                    colspan: '5',
                    html: $('<div>', {
                        class: 'progress',
                        marginBottom: '0',
                        html: $('<div>', {
                            id: 'progress_for_task_' + task.id,
                            class: 'progress-bar',
                            role: 'progressbar',
                            ariaValuenow: '60',
                            ariaValuemin: '0',
                            ariaValuemax: '100',
                            width: progress + '%',
                            html: $('<span>', {
                                class: 'sr-only'
                            })
                        })
                    })
                })
            )
        );

        if (progress == 100) {
            changeTaskStatus(task.id, 'COMPLETE', 'label label-success');
        } else if (progress != 100) {
            changeTaskStatus(task.id, 'INCOMPLETE', 'label label-warning');
        }
    });
}

function changeTaskStatus(taskId, taskStatus, labelClass) {
    $.ajax({
        type: "PUT",
        url: '/host/tasks/' + taskId,
        data: {
            status: taskStatus
        }
    }).done(function () {
        var $label = $('#task_status_label_' + taskId);

        $label.attr('class', labelClass);
        $label.text(taskStatus);
    });
}

function fillItemTable(items) {
    $('#items').empty();
    $.each(items, function (index, item) {
        var itemStatusLabelClass;

        if (item.status == 'UNREADY') {
            itemStatusLabelClass = 'label label-warning';
        } else if (item.status == 'READY') {
            itemStatusLabelClass = 'label label-success';
        }

        $('#items').append(
            $('<tr>').append(
                $('<td>', {
                    text: item.description
                }),
                $('<td>', {
                    html: $('<label>', {
                        class: itemStatusLabelClass,
                        text: item.status
                    })
                }),
                $('<td>', {
                    html: $('<button>', {
                        id: 'change_item_status_' + item.id,
                        class: 'btn btn-default btn-md',
                        ariaLabel: 'Center Align',
                        html: $('<span>', {
                            class: 'glyphicon glyphicon-ok',
                            ariaHidden: 'true'
                        })
                    })
                }),
                $('<td>', {
                    html: $('<button>', {
                        id: 'delete_item_id_' + item.id,
                        class: 'btn btn-default btn-md',
                        ariaLabel: 'Center Align',
                        html: $('<span>', {
                            class: 'glyphicon glyphicon-trash',
                            ariaHidden: 'true'
                        })
                    })
                })
            )
        );

        $('#delete_item_id_' + item.id).click(function () {
            deleteItem(item.id);
            redrawTaskTable()
        });

        $('#change_item_status_' + item.id).click(function () {
            changeItemStatus(item.id, item.status);
            redrawTaskTable();
        })
    });
}

function changeItemStatus(itemId, itemStatus) {
    $.ajax({
        type: 'PUT',
        url: '/host/tasks/items/' + itemId,
        data: {
            status: itemStatus
        }
    }).done(function () {
        redrawItemsTable()
    });
}

function deleteItem(itemId) {
    $.ajax({
        type: 'DELETE',
        url: '/host/tasks/items/' + itemId
    }).done(function () {
        redrawItemsTable();
    });
}

function redrawItemsTable() {
    var currentTaskId = sessionStorage.getItem('currentTaskId');
    $('#items').empty();
    $.get('/host/tasks/' + currentTaskId, function (items) {
        fillItemTable(items);
    });
}

function redrawTaskTable() {
    $.get("/host/tasks/user/" + tempId, function (tasks) {
        $('#tasks').empty();
        fillTaskTable(tasks)
    });
}

function calculateUnfinishedItemsForTask(task) {
    var count = 0;
    $.each(task.items, function (index, item) {
        if (item.status == 'READY') {
            ++count;
        }
    });
    return count;
}

function calculatePercentageOfFillingProgressLine(allCount, completeCount) {
    return ((completeCount / allCount) * 100).toFixed(2);
}