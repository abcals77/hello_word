/**
 * event.js
 */

document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
	var events = [];
	// Ajax call.
	console.log('1');
	fetch('eventList.do')
	.then(result => result.json())
	.then(eventListCallback) 
	.catch(err => console.error(err));
	
	function eventListCallback(result){
		console.log('2');
		events = result.map(ev => {
		    return {
		      title: ev.title,
		      start: ev.startDate,
		      end: ev.endDate
		    };
		  });	
	
	
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
      select: function(arg) {
		console.log(arg)
		var title = prompt('Event Title:');
		  if (title) {
		    // 달력에 먼저 그리기
		    calendar.addEvent({
		      title: title,
		      start: arg.start,
		      end: arg.end,
		      allDay: arg.allDay
		    });

				
		    // 서버에 저장 요청
		    fetch('addEvent.do?title=' + title + '&startDate=' + arg.startStr + '&endDate=' + arg.endStr)
		    .then(response => response.json())
		    .then(data => {
		      console.log('서버 응답:', data);
		      alert('등록 완료');
		    })
		    .catch(err => {
				console.log('addEvent.do?title=' + title + '&startDate=' + arg.startStr + '&endDate=' + arg.endStr);
		      console.error(err);
		      alert('등록 실패');
		    });
		  }
		  calendar.unselect();
      },
      eventClick: function(arg) {
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
	console.log('3');
    calendar.render();
	} // end of eventListCallback
  });
/*
function addEvent() {
	// control을 통해서 db 한건생성.
	fetch('addEvent.do?title=' + title + '&startDate=' + startDate + '&endDate=' + endDate)
	.then(result => result.json()) // json 포맷으로 결과를 변경
	.then(
		() => alert('등록완료')
	)
	.catch(err => console.error(err));
}*/
