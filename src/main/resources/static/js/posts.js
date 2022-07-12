'use strict';

let index ={
    init: function(){
        $('#btn-save').on('click', () => {
            this.save();
        });
    },
    save : function (){
        let data = {
            title: $('title').val(),
            content: $('content').val()
        }

        $.ajax({
            type: 'POST',
            url: 'api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

index.init();