<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset='utf-8' />
  <script src='js/index.global.js'></script>
  <script>
    document.addEventListener('DOMContentLoaded', async function () {

      let events = []; //이벤트를 받을 변수.

      // fullcalendar를 표시할 위치.
      var calendarEl = document.getElementById('calendar');

      // Ajax Call.
      let result = await fetch('eventList.do');
      // await.
      events = await result.json();

      var calendar = new FullCalendar.Calendar(calendarEl, {
        headerToolbar: {
          left: 'prev,next today',
          center: 'title',
          right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        initialDate: new Date(),
        navLinks: true, // can click day/week names to navigate views
        selectable: true,
        selectMirror: true,
        select: async function (arg) {
        	console.log(arg)
          var title = prompt('Event Title:');
          if (title) {
            let allDay = arg.allDay;
            let startStr = allDay ? arg.startStr : arg.startStr.substring(0, 19);
            let endStr = allDay ? arg.endStr : arg.endStr.substring(0, 19);
            let xhtp = await fetch('addEvent.do?title=' + title + '&start=' + startStr + '&end=' + endStr);
            let result = await xhtp.json();
            if (result.retCode == 'OK') {
              //화면출력.
              calendar.addEvent({
                title: title,
                start: arg.start,
                end: arg.end,
                allDay: arg.allDay
              })
            } else {
              alert('Transaction error!');
            }
          }
          calendar.unselect()
        },
        eventClick: async function (arg) {
          console.log(arg);
          if (confirm('Are you sure you want to delete this event?')) {
            let xhtp = await fetch('removeEvent.do?title=' + arg.event.title + '&start=' + arg.event.startStr + '&end=' + arg.event.endStr);
            let result = await xhtp.json();
            if (result.retCode == 'OK') {
              arg.event.remove();
            } else {
              alert('Transaction error!');
            }
          }
        },
        editable: true,
        dayMaxEvents: true, // allow "more" link when too many events
        events: events
      });

      calendar.render();

    });
  </script>
  <style>
    body {
      margin: 40px 10px;
      padding: 0;
      font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
      font-size: 14px;
    }

    #calendar {
      max-width: 1100px;
      margin: 0 auto;
    }
  </style>
</head>

<body>
  <h3>현재 페이지는 event2.jsp</h3>
  <div id='calendar'></div>

</body>

</html>