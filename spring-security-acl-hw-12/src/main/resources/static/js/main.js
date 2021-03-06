$(document).ready(function() {
    $(".ui.fluid.search.dropdown").dropdown({
        "fullTextSearch": true
    });

    $('.ui.form').form({
        on: 'blur',
        fields: {
            nameInput: {
                identifier : 'name',
                rules: [{type : 'empty'}]
            },
            textInput: {
                identifier : 'text',
                rules: [{type : 'empty'}]
            },
            authorDropdown: {
                identifier : 'author_id',
                rules: [{type : 'empty'}]
            },
            genreDropdown: {
                identifier : 'genre_id',
                rules: [{type : 'empty'}]
            },
            bookDropdown: {
                identifier : 'book_id',
                rules: [{type : 'empty'}]
            }
        }
    });
});

function deleteEntity(url, table) {
    $.ajax({
        url: url,
        type: 'DELETE',
        success: function () {
            table.DataTable().ajax.reload();
        },
        error: function() {
            $('#accessDeniedSidebar').sidebar('toggle');
        }
    });
}

function createEntity(url, data, table) {
    $.ajax({
        url: url,
        type: 'POST',
        data: data,
        success: function () {
            table.DataTable().ajax.reload();
        },
        error: function() {
            $('#accessDeniedSidebar').sidebar('toggle');
        }
    });
}

function updateEntity(url, data, table) {
    $.ajax({
        url: url,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function () {
            table.DataTable().ajax.reload();
        },
        error: function() {
            $('#accessDeniedSidebar').sidebar('toggle');
        }
    });
}