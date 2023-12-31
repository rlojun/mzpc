// to get current year
function getYear() {
    var currentDate = new Date();
    var currentYear = currentDate.getFullYear();
    document.querySelector("#displayYear").innerHTML = currentYear;
}

getYear();


// isotope js
$(window).on('load', function () {
    $('.filters_menu li').click(function () {
        $('.filters_menu li').removeClass('active');
        $(this).addClass('active');

        var data = $(this).attr('data-filter');
        $grid.isotope({
            filter: data
        })
    });

    var $grid = $(".grid").isotope({
        itemSelector: ".all",
        percentPosition: false,
        masonry: {
            columnWidth: ".all"
        }
    })

        $('.filters_menu_member li').click(function () {
            $('.filters_menu_member li').removeClass('active');
            $(this).addClass('active');

            var filterValue = $(this).attr('data-filter');
            $(".gridMembers").isotope({
                filter: filterValue
            })
        });

        var $gridMembers = $(".gridMembers").isotope({
        itemSelector: ".menu-item, .favorite-item",
        percentPosition: false,
        masonry: {
            columnWidth: ".menu-item"
        }
    });
});

// nice select
$(document).ready(function() {
    $('select').niceSelect();
  });


// client section owl carousel
$(".client_owl-carousel").owlCarousel({
    loop: true,
    margin: 0,
    dots: false,
    nav: true,
    navText: [],
    autoplay: true,
    autoplayHoverPause: true,
    navText: [
        '<i class="fa fa-angle-left" aria-hidden="true"></i>',
        '<i class="fa fa-angle-right" aria-hidden="true"></i>'
    ],
    responsive: {
        0: {
            items: 1
        },
        768: {
            items: 2
        },
        1000: {
            items: 2
        }
    }
});