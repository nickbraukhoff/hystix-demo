$(document).ready(function () {
    var intervalHandle = null;

    $('#testButton').click(function () {
        if (intervalHandle === null) {
            intervalHandle = setInterval(function () {
                var num = Math.floor((Math.random() * 4) + 1);
                if (num === 1) {
                    $.ajax({
                        url: "http://localhost:8080/v1/people"
                    }).then(function (data) {
                    });
                } else if (num === 2) {
                    $.ajax({
                        url: "http://localhost:8080/v1/people/1"
                    }).then(function (data) {
                    });
                } else if (num === 3) {
                    $.ajax({
                        url: "http://localhost:8080/v1/ships"
                    }).then(function (data) {
                    });
                } else {
                    $.ajax({
                        url: "http://localhost:8080/v1/people/search?name=R2-D2"
                    }).then(function (data) {
                    });
                }
            }, 2000);
        } else {
            clearInterval(intervalHandle);
            intervalHandle = null;
        }

    });
});