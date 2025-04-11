/**
 * js/array2.js
 */
const numAry = [10, 17, 23, 26, 49];

let filterAry = numAry.filter(function(item, idx, ary){ // (배열 요소, index, 배열 그자체)
	return item % 2 == 0; // true값을 반환하는 item을 배열로 저장.
});
// console.log(filterAry);
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

const jsonAry = JSON.parse(jsonStr);
// {id, first_name, ...salary}
filterAry = jsonAry.filter(item => item.salary >= 5000);
// console.log(filterAry);
filterAry = jsonAry.filter(item => item.gender == "Female" && item.salary >= 5000);
// console.log(filterAry);

// reduce() acc : 
console.clear();
let result = [10, 23, 34, 48, 51].reduce(function(acc, item) {
	console.log(acc, item);
	if(item > 30){
		acc.push(item);
	}
	return acc; // acc 값으로 활용.
}, []); // => acc 초기값은 빈 배열
console.log('result : ' +  result);
console.clear();
let list = document.querySelector('#list'); // <ul id = "list" />
/*[10, 23, 34, 48, 51].reduce((acc, item) => {
	let li = document.createElement('LI');
	li.innerHTML = item;
	acc.appendChild(li);
	
	return acc;
}, list);*/
const ary = [
	{name: "홍길동", phone: "010-1111-2222"},
	{name: "최길동", phone: "010-2222-3333"},
	{name: "초길동", phone: "010-4444-5555"},
	{name: "황길동", phone: "010-6666-7777"}
]
ary.reduce((acc, item) => {
	let li = document.createElement('Li');
	li.innerHTML = '이름 : ' + item.name + ' - 전화번호 : ' + item.phone;
	acc.appendChild(li);
	
	return acc;
	
}, list)