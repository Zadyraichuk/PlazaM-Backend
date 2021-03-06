$(document).ready(function () {
    $("#calendar").calendar(new Date());
});

(function ($) {

    $.fn.calendar = function (opts) {
        let options = $.extend({
            selectedDate: opts,
            // currentYear: null,
            // currentMonth: null,
            // currentDay: null,
            // currentCalendar: null,
            months: monthsCalendarList,
            days: daysCalendarList,
            onSelect: function (event) {
            }
        }, $.fn.calendar.defaults, opts);

        return this.each(function () {
            let currentYear, currentMonth, currentDay, currentCalendar;

            initCalendar($(this), options);
        });
    };

    function initCalendar(wrapper, options) {
        wrapper.addClass('calendar').empty();

        let header = $('<header>').appendTo(wrapper);
        header.addClass('calendar-header');

        let buttonLeft = $('<span>').appendTo(header);
        buttonLeft.addClass('button').addClass('left');
        buttonLeft.html(' &lang; ');
        buttonLeft.bind('click', function () {
            currentCalendar = $(this).parents('.calendar');
            selectMonth(false, options);
            let curMonth = options.months.indexOf($($(".header-label")[0]).text().split(" ")[0]);
            let curYear = parseInt($($(".header-label")[0]).text().split(" ")[1]);
            if (curMonth === options.selectedDate.getMonth() && curYear === options.selectedDate.getFullYear()) {
                let dayNumber = options.selectedDate.getDay();
                if (dayNumber === 0) {
                    $($(".calendar table th")[6]).addClass("selected");
                } else {
                    $($(".calendar table th")[dayNumber - 1]).addClass("selected");
                }
            } else {
                $(".calendar table th").removeClass("selected");
            }
        });

        let headerLabel = $('<span>').appendTo(header);
        headerLabel.addClass('header-label')
        headerLabel.html(' Month Year ');
        headerLabel.bind('click', function () {
            currentCalendar = $(this).parents('.calendar');
            selectMonth(null, options, options.selectedDate.getMonth(), options.selectedDate.getFullYear());

            currentDay = options.selectedDate.getDate();
            triggerSelectEvent(options.onSelect);
        });

        let buttonRight = $('<span>').appendTo(header);
        buttonRight.addClass('button').addClass('right');
        buttonRight.html(' &rang; ');
        buttonRight.bind('click', function () {
            currentCalendar = $(this).parents('.calendar');
            selectMonth(true, options);
            let curMonth = options.months.indexOf($($(".header-label")[0]).text().split(" ")[0]);
            let curYear = parseInt($($(".header-label")[0]).text().split(" ")[1]);
            if (curMonth === options.selectedDate.getMonth() && curYear === options.selectedDate.getFullYear()) {
                let dayNumber = options.selectedDate.getDay();
                if (dayNumber === 0) {
                    $($(".calendar table th")[6]).addClass("selected");
                } else {
                    $($(".calendar table th")[dayNumber - 1]).addClass("selected");
                }
            } else {
                $(".calendar table th").removeClass("selected");
            }
        });

        let dayNames = $('<table>').appendTo(wrapper);
        dayNames.append('<thead><th>' + options.days.join('</th><th>') + '</th></thead>');
        let dayNumber = options.selectedDate.getDay();
        if (dayNumber === 0) {
            $($(".calendar table th")[6]).addClass("selected");
        } else {
            $($(".calendar table th")[dayNumber - 1]).addClass("selected");
        }

        let calendarFrame = $('<div>').appendTo(wrapper);
        calendarFrame.addClass('calendar-frame');

        headerLabel.click();
    }

    function selectMonth(next, options, month, year) {
        let tmp = currentCalendar.find('.header-label').text().trim().split(' '), tmpYear = parseInt(tmp[1], 10);

        if (month === 0) {
            currentMonth = month;
        } else {
            currentMonth = month || ((next) ? ((tmp[0] === options.months[options.months.length - 1]) ? 0 : options.months.indexOf(tmp[0]) + 1) : ((tmp[0] === options.months[0]) ? 11 : options.months.indexOf(tmp[0]) - 1));
        }

        currentYear = year || ((next && currentMonth === 0) ? tmpYear + 1 : (!next && currentMonth === 11) ? tmpYear - 1 : tmpYear);

        let calendar = createCalendar(currentMonth, currentYear, options), frame = calendar.frame();

        currentCalendar.find('.calendar-frame').empty().append(frame);
        currentCalendar.find('.header-label').html(calendar.label);

        frame.on('click', 'td', function () {
            if (!$(this).hasClass("disabled") && !$(this).find("span").hasClass("disabled")) {
                $('td').removeClass('selected');
                $(this).addClass('selected');
                $('thead th').removeClass("selected");
                let curMonth = options.months.indexOf($($(".header-label")[0]).text().split(" ")[0]);
                let curYear = parseInt($($(".header-label")[0]).text().split(" ")[1]);
                let curDate = new Date();
                curDate.setFullYear(curYear);
                curDate.setMonth(curMonth);
                curDate.setDate(parseInt($(this).text()));
                if (curDate.getDay() === 0) {
                    $($(".calendar table th")[6]).addClass("selected");
                } else {
                    $($(".calendar table th")[curDate.getDay() - 1]).addClass("selected");
                }
                options.selectedDate = curDate;

                currentDay = $(this).text();
                triggerSelectEvent(options.onSelect);
            }
        });
    }

    function createCalendar(month, year, options) {
        let currentDay = 1, daysLeft = true,
            startDay = new Date(year, month, currentDay).getDay() - 1,
            lastDays = [31, (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31],
            calendar = [];

        let i = 0;
        while (daysLeft) {
            calendar[i] = [];

            for (let d = 0; d < 7; d++) {
                if (i == 0) {
                    if (d == startDay) {
                        calendar[i][d] = currentDay++;
                        startDay++;
                    } else if (startDay === -1) {
                        calendar[i][6] = currentDay++;
                        startDay++;
                    }
                } else if (currentDay <= lastDays[month]) {
                    calendar[i][d] = currentDay++;
                } else {
                    calendar[i][d] = '';
                    daysLeft = false;
                }

                if (currentDay > lastDays[month]) {
                    daysLeft = false;
                }
            }

            i++;
        }

        let frame = $('<table>').addClass('current');
        let frameBody = $('<tbody>').appendTo(frame);

        for (let j = 0; j < calendar.length; j++) {
            let frameRow = $('<tr>').appendTo(frameBody);

            $.each(calendar[j], function (index, item) {
                let frameItem = $('<td>').appendTo(frameRow);
                if (typeof item !== "undefined" && item !== "") {
                    if (typeof availableDates === 'undefined') {
                        frameItem.html("<span class='date'>" + item + "</span>");
                    } else {
                        let dateString = currentYear + '-' + (currentMonth + 1 < 10 ? '0' + (currentMonth + 1) : (currentMonth + 1)) + '-' +
                            (item < 10 ? '0' + item : item);
                        if (availableDates.includes(dateString) && new Date(dateString) >= new Date()) {
                            frameItem.html("<span class='date'>" + item + "</span>");
                        } else {
                            frameItem.html("<span class='date disabled'>" + item + "</span>");
                        }
                    }
                } else {
                    frameItem.addClass("disabled");
                }
            });
        }

        $('td:empty', frame).addClass('disabled');
        if (currentMonth === options.selectedDate.getMonth() && currentYear === options.selectedDate.getFullYear()) {
            $('td', frame).filter(function () {
                return $(this).text() === options.selectedDate.getDate().toString();
            }).addClass('selected');
        }

        return {
            frame: function () {
                return frame.clone()
            }, label: options.months[month] + ' <span class="current-year">' + year + '</span>'
        };
    }

    function triggerSelectEvent(event) {
        let date = new Date(currentYear, currentMonth, currentDay);

        let label = [];
        label[0] = (date.getDate() < 10) ? '0' + date.getDate() : date.getDate();
        label[1] = ((date.getMonth() + 1) < 10) ? '0' + (date.getMonth() + 1) : date.getMonth() + 1;
        label[2] = (date.getFullYear());

        if (event != undefined) {
            event({date: date, label: label.join('.')});
        }
    }
}(jQuery));