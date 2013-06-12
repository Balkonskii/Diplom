
var pre_lang = 'ru';

aLang = '';
var qPanelLang =  {
gotoModule	:	"Перейти к модулю",
whatEdit	:	"Что редактировать?",
createnew	:	"Создать заметку",
createnewalert:	"Теперь нужно выделить область страницы, к которой Вы собираетесь создать заметку.",
collapse	:	"Свернуть",
edit		:	"Редактировать",
hide		:	"Скрыть",
uncollapse	:	"Развернуть панель",
statictics	:	"Переходы",
administration:	"Администрирование",
quit		:	"Выход"
}; 



var is_ie = !(navigator.appName.indexOf("Netscape") != -1);


includeJS("/js/custom.js");
includeJS("/js/client/cookie.js");
includeJS("/js/client/catalog.js");
includeJS("/js/client/stat.js");
includeJS("/js/client/vote.js");
includeJS("/js/client/users.js");
includeJS("/js/client/eshop.js");
includeJS("/js/client/forum.js");
includeJS("/js/client/mouse.js");
includeJS("/js/client/quickEdit.js");
includeJS("/js/client/qPanel.js");
includeJS("/js/client/umiTicket.js");
includeJS("/js/client/umiTickets.js");
includeJS("/js/client/floatReferers.js");


Event.observe(document, "keydown", function(event) {
	var oTargetEl = Event.element(event);
	if (oTargetEl) {
		if (oTargetEl.tagName.toUpperCase() == 'INPUT' || oTargetEl.tagName.toUpperCase() == 'TEXTAREA' || oTargetEl.tagName.toUpperCase() == 'IFRAME') {
			return true;
		}
	}

	var iKCode = event.which;

	if(event.keyCode == 27) {
		quickEdit.getInstance().hide();
	}

	if ((event.shiftKey || event.metaKey) && event.keyCode == 68) {
		quickEdit.getInstance().show();
	}

	if ((event.shiftKey || event.metaKey) && event.keyCode == 67) {
		umiTickets.getInstance().beginCreatingTicket();
	}

	if(event.ctrlKey || event.metaKey) {
		
		if(event.keyCode == 37) {
			var obj = document.getElementById('toprev');
			if(obj) {
				document.location = obj.href.toString();
				
				if(is_safari()) {
					return false;
				}
			}
		}

		if(event.keyCode == 39) {
			var obj = document.getElementById('tonext');
			if(obj) {
				document.location = obj.href.toString();
			}
		}

		if(event.keyCode == 36) {
			var obj = document.getElementById('tobegin');
			if(obj) {
				document.location = obj.href.toString();
				
				if(is_safari()) {
					return false;
				}
			}
		}

		if(event.keyCode == 35) {
			var obj = document.getElementById('toend');
			if(obj) {
				document.location = obj.href.toString();
				
				if(is_safari()) {
					return false;
				}
			}
		}
	}


});




