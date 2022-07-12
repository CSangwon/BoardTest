let index ={
    init: function() {
        $('#btn-movie-find').on('click', () => {
            this.movieFind()
        });
    },
    movieFind: function () {
        let data = {
            keyword: $('#keyword').val()
        }

        $.ajax({
            type: "GET",
            url: "/api/v1/movies/" + data.keyword,
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }).done(function (res) {
            alert(JSON.stringify(res));
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};
index.init();