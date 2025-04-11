/**
 * js/cart.js
 */
const basket = {
	updatePNum: function(index, diff) {
		// <span onclick="javascript: basket.updatePNum(0, 1);">
		// <span onclick="javascript: basket.updatePNum(0, -1);">
		// updatePNum index에 0 전달, diff에 1 전달
		const numInput = document.getElementById('p_num' + index);
		// input value에 값이 있을 경우 해당 값을 없을 경우 0을 currentNum에 담음
		let currentNum = parseInt(numInput.value) || 0;
		// updataPNum이 diff에 전달한 값에 따라 input value +1 또는 -1 반영
		currentNum += diff;
		// currentNum이 0 보다 작을 경우 0 반환 => 최소값 0 설정
		if (currentNum < 0) currentNum = 0;
		if (currentNum > 99) currentNum = 99;
		// basket.updatePNum 이벤트에 따라 변경된 값을 input value에 전달
		numInput.value = currentNum;
		// index 값을 changPNum 함수에 전달 
		this.changePNum(index);
	},
	// id : "p_num + 숫자(index)" 태그를 가져옴 index 값은 basket.changePNum(값) 전달
	// input onkeyup : 키입력 완료 시 이벤트 발생
	// basket 객체의 changePNum 함수 index에 '0' 이라는 값을 인수로 전달해서 호출
	changePNum: function(index) {
		const price = parseInt(document.getElementById('p_price' + index).value);
		const quantity = parseInt(document.getElementById('p_num' + index).value) || 0;
		const sum = price * quantity;


		const sumElement = document.getElementById('sum' + index);
		sumElement.textContent = this.formatCurrency(sum) + '원';
		this.updateTotal();
	},
	updateTotal: function() {
		const checkboxes = document.querySelectorAll('input[name="buy"]:checked');
		let totalQty = 0;
		let totalPrice = 0;

		checkboxes.forEach((checkbox, i) => {
			const index = Array.from(document.querySelectorAll('input[name="buy"]')).indexOf(checkbox);
			const price = parseInt(document.getElementById('p_price' + index).value);
			const quantity = parseInt(document.getElementById('p_num' + index).value) || 0;
			totalQty += quantity;
			totalPrice += price * quantity;
		});

		// DOM 업데이트
		document.querySelector('#sum_p_num span').textContent = totalQty;
		document.querySelector('#sum_p_price span').textContent = this.formatCurrency(totalPrice);
	},

	delAllItem: function() {
		// 모든 체크박스 해제
		const checkboxes = document.querySelectorAll('input[name="buy"]');
		checkboxes.forEach((checkbox) => {
			checkbox.checked = false;
		});
		this.updateTotal();
	},

	formatCurrency: function(number) {
		return number.toLocaleString();
	}
};


window.onload = () => {
	const allNumInputs = document.querySelectorAll('.p_num');
	allNumInputs.forEach((el, i) => basket.changePNum(i));

};

const checkboxes = document.querySelectorAll('input[name="buy"]');
checkboxes.forEach((checkbox) => {
	checkbox.addEventListener('change', () => {
		basket.updateTotal();
	});
});
