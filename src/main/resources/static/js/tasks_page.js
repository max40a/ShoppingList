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
                open('http://localhost:8090/tests/create_item.html',
                    'newItem',
                    'height=300, width=300, left=533, top=150');
            })
        });
    });
}

function fillTaskTable(tasks) {
    $.each(tasks, function (index, task) {
        var taskStatusLabelClass;
        var taskProgress;

        if (task.status == 'INCOMPLETE') {
            taskStatusLabelClass = 'label label-warning';
            taskProgress = 0;
        } else if (task.status == 'COMPLETE') {
            taskStatusLabelClass = 'label label-success';
            taskProgress = 100;
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
                        id: "change_status_for_task_" + task.id,
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
                            width: taskProgress + '%',
                            html: $('<span>', {
                                class: 'sr-only'
                            })
                        })
                    })
                })
            )
        );

        $('#change_status_for_task_' + task.id).click(function () {
            changeTaskStatus(task.id, task.status);
        })
    });
}

function changeTaskStatus(taskId, taskStatus) {
    $.ajax({
        type: "PUT",
        url: '/host/tasks/' + taskId,
        data: {
            status: taskStatus
        }
    }).done(function () {
        var $label = $('#task_status_label_' + taskId);
        var $progressForTask = $('#progress_for_task_' + taskId);

        if ($label.text() == "INCOMPLETE") {
            $label.attr('class', 'label label-success');
            $label.text('COMPLETE');

            $progressForTask.css('width', '100%');
        } else if ($label.text() == "COMPLETE") {
            $label.attr('class', 'label label-warning');
            $label.text('INCOMPLETE');

            $progressForTask.css('width', '0%');
        }
    });
}

function fillItemTable(items) {
    $('#items').empty();
    $.each(items, function (index, item) {
        var itemStatusLabelClass;

        console.log(item.status);

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
        });

        $('#change_item_status_' + item.id).click(function () {
            changeItemStatus(item.id, item.status);
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
        redrawItemsTable()
    });
}

function redrawItemsTable() {
    var currentTaskId = sessionStorage.getItem('currentTaskId');
    $('#items').empty();
    $.get('/host/tasks/' + currentTaskId, function (items) {
        fillItemTable(items);
    });
}