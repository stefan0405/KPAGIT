var today = new Date();
$('#datePicker').calendar({
    type: 'date',
    monthFirst: false,
    minDate: new Date(today.getFullYear(), today.getMonth(), today.getDate() + 1),
    formatter: {
        date: function (date, settings) {
            if (!date)
                return '';
            var day = date.getDate();
            var month = date.getMonth() + 1;
            var year = date.getFullYear();
            return day + '/' + month + '/' + year;
        }
    }
});