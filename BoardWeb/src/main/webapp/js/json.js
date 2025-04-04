/**
 * json
 */

const jsonStr = `[{"id":1,"first_name":"Felizio","last_name":"Benton","email":"fbenton0@moonfruit.com","gender":"Male","salary":7111},
{"id":2,"first_name":"Jayne","last_name":"Faircliffe","email":"jfaircliffe1@guardian.co.uk","gender":"Female","salary":6243},
{"id":3,"first_name":"Marlyn","last_name":"Sugge","email":"msugge2@ebay.com","gender":"Female","salary":5688},
{"id":4,"first_name":"Salaidh","last_name":"Kalberer","email":"skalberer3@godaddy.com","gender":"Female","salary":4309},
{"id":5,"first_name":"Joella","last_name":"Bolan","email":"jbolan4@exblog.jp","gender":"Female","salary":1676},
{"id":6,"first_name":"Lynn","last_name":"Clevely","email":"lclevely5@ow.ly","gender":"Female","salary":2881},
{"id":7,"first_name":"Lay","last_name":"Barby","email":"lbarby6@technorati.com","gender":"Genderfluid","salary":5781},
{"id":8,"first_name":"Hadria","last_name":"Phippard","email":"hphippard7@bing.com","gender":"Female","salary":7868},
{"id":9,"first_name":"Ced","last_name":"Skoggings","email":"cskoggings8@blogs.com","gender":"Male","salary":3680},
{"id":10,"first_name":"Rabi","last_name":"Baudouin","email":"rbaudouin9@nydailynews.com","gender":"Male","salary":5326},
{"id":11,"first_name":"Bernardo","last_name":"Alday","email":"baldaya@apache.org","gender":"Male","salary":4398},
{"id":12,"first_name":"Nicolais","last_name":"Satcher","email":"nsatcherb@yelp.com","gender":"Male","salary":9313},
{"id":13,"first_name":"Cole","last_name":"Lovart","email":"clovartc@geocities.jp","gender":"Polygender","salary":9571},
{"id":14,"first_name":"Ameline","last_name":"Mottershaw","email":"amottershawd@seattletimes.com","gender":"Female","salary":6574},
{"id":15,"first_name":"Jamesy","last_name":"Collins","email":"jcollinse@ameblo.jp","gender":"Male","salary":1462}]`;

let obj = JSON.parse(jsonStr); // json문자열 -> object 변경.
let str = JSON.stringify(obj); // object -> json문자열 변경
console.log(obj);

// 한건 데이터를 매개값으로 tr을 생성하는 함수.
function makeRow(emp = {id, first_name, last_name, email, gender, salary}){
	const fields = ['id', 'first_name', 'last_name', 'email'];
	let tr = document.createElement('tr'); // <tr></tr>
	for(let i = 0; i < fields.length; i++){
		let td = document.createElement('td');
		td.innerHTML = emp[fields[i]]; // <td>1</td>
		tr.appendChild(td); // <tr><td>1</td><td>Felizio</td>...</tr>
	}	
	return tr;
}

// 화면출력
obj.forEach(function(item, idx, ary){
	let tr = makeRow(item);
	document.querySelector('tbody#target').appendChild(tr);
});








