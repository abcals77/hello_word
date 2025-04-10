<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset='utf-8' />
<script src='js/index.global.js'></script>
<script>
	document.addEventListener('DOMContentLoaded', async function () {
    	var calendarEl = document.getElementById('calendar');
    	console.log('1');
    	var events = [];
    	// Ajax call.
      
		console.log('2');
		let result = await fetch('eventList.do');
      
		let result2 = await result.json();
		console.log(result2);  

		result2.forEach(item => {
			events.push({title: item.title,
				start: item.startDate,
				end: item.endDate
			})
		})
	  
      console.log('3');
      var calendar = new FullCalendar.Calendar(calendarEl, {
        headerToolbar: {
          left: 'prev,next today',
          center: 'title',
          right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        initialDate: '2025-04-09',
        navLinks: true, // can click day/week names to navigate views
        selectable: true,
        selectMirror: true,
        select: async function (arg) {
          console.log(arg)
		var title = prompt('Event Title:');
		if (title) {
			let allDay = arg.allDay; // 하루 전체일정, 부분일정 구분
			let start = allDay ? arg.startStr : arg.startStr.substring(0, 19);
			let end = allDay ? arg.startStr : arg.endStr.substring(0, 19);
			
			//let r1 = await fetch('addEvent.do?title=' + title + '&startDate=' + start + '&endDate=' + end);
        	let r1 = await fetch('addEvent.do', {
				method: 'post',
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				body: 'title=' + title + '&startDate=' + start + '&endDate=' + end
        	})
			let r2 = await r1.json();
        	if (r2.retCode == 'OK'){
        		calendar.addEvent({
                    title: title,
                    start: arg.start,
                    end: arg.end,
                    allDay: arg.allDay
                })
			}else {
				alert('등록실패')
			}
			calendar.unselect();
			}
        },
        eventClick: function (arg) {
          console.log(arg)
          const title = arg.event.title;
          const start = arg.event.startStr; // Date 객체
          const end = arg.event.endStr;
          if (confirm('Are you sure you want to delete this event?')) {
            fetch('deleteEvent.do?title=' + title + '&startDate=' + start + '&endDate=' + end)
              .then(res => res.json())
              .then(data => {
                if (data.retCode == 'OK') {
                  arg.event.remove();
                  alert('삭제 완료');
                } else {
                  alert('삭제 실패');
                }
              })
              .catch(err => console.error(err));
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

	<div id='calendar'></div>

</body>

</html>