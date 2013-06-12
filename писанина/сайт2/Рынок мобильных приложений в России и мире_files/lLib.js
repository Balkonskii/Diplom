﻿function lLib () {
	return this;
}

lLib.instance = null;

lLib.getInstance = function () {
	if(! lLib.instance) {
		lLib.instance = new lLib();
	}

	return lLib.instance;
};


lLib.md5 = function (s) {
	function Z(n,c){return(n<<c)|(n>>>(32-c))}
	function Y(q,a,b,x,s,t){return X(Z(X(X(a,q),X(x,t)),s),b)}
	function A(a,b,c,d,x,s,t){return Y((b&c)|((~b)&d),a,b,x,s,t)}
	function B(a,b,c,d,x,s,t){return Y((b&d)|(c&(~d)),a,b,x,s,t)}
	function C(a,b,c,d,x,s,t){return Y(b^c^d,a,b,x,s,t)}
	function D(a,b,c,d,x,s,t){return Y(c^(b|(~d)),a,b,x,s,t)}
	function X(x,y){var l=(x&0xFFFF)+(y&0xFFFF),m=(x>>16)+(y>>16)+(l>>16);return(m<<16)|(l&0xFFFF)}
	
	var w=s.length*8,i,hx="0123456789abcdef",L=(((w+64)>>>9)<<4)+15,x=Array(L+15);
	for(i=0;i<x.length;++i)x[i]=0;
	for(i=0;i<w;i+=8)x[i>>5]|=(s.charCodeAt(i/8)&255)<<(i%32);
	x[w>>5]|=0x80<<((w)%32);
	x[L-1]=w;
	
	var a=1732584193,b=-271733879,c=-1732584194,d=271733878;
	for(i=0;i<L;i+=16){
		var oa=a,ob=b,oc=c,od=d;
		a=A(a,b,c,d,x[i],7,-680876936);d=A(d,a,b,c,x[i+1],12,-389564586);c=A(c,d,a,b,x[i+2],17,606105819);b=A(b,c,d,a,x[i+3],22,-1044525330);
		a=A(a,b,c,d,x[i+4],7,-176418897);d=A(d,a,b,c,x[i+5],12,1200080426);c=A(c,d,a,b,x[i+6],17,-1473231341);b=A(b,c,d,a,x[i+7],22,-45705983);
		a=A(a,b,c,d,x[i+8],7,1770035416);d=A(d,a,b,c,x[i+9],12,-1958414417);c=A(c,d,a,b,x[i+10],17,-42063);b=A(b,c,d,a,x[i+11],22,-1990404162);
		a=A(a,b,c,d,x[i+12],7,1804603682);d=A(d,a,b,c,x[i+13],12,-40341101);c=A(c,d,a,b,x[i+14],17,-1502002290);b=A(b,c,d,a,x[i+15],22,1236535329);
		a=B(a,b,c,d,x[i+1],5,-165796510);d=B(d,a,b,c,x[i+6],9,-1069501632);c=B(c,d,a,b,x[i+11],14,643717713);b=B(b,c,d,a,x[i],20,-373897302);
		a=B(a,b,c,d,x[i+5],5,-701558691);d=B(d,a,b,c,x[i+10],9,38016083);c=B(c,d,a,b,x[i+15],14,-660478335);b=B(b,c,d,a,x[i+4],20,-405537848);
		a=B(a,b,c,d,x[i+9],5,568446438);d=B(d,a,b,c,x[i+14],9,-1019803690);c=B(c,d,a,b,x[i+3],14,-187363961);b=B(b,c,d,a,x[i+8],20,1163531501);
		a=B(a,b,c,d,x[i+13],5,-1444681467);d=B(d,a,b,c,x[i+2],9,-51403784);c=B(c,d,a,b,x[i+7],14,1735328473);b=B(b,c,d,a,x[i+12],20,-1926607734);
		a=C(a,b,c,d,x[i+5],4,-378558);d=C(d,a,b,c,x[i+8],11,-2022574463);c=C(c,d,a,b,x[i+11],16,1839030562);b=C(b,c,d,a,x[i+14],23,-35309556);
		a=C(a,b,c,d,x[i+1],4,-1530992060);d=C(d,a,b,c,x[i+4],11,1272893353);c=C(c,d,a,b,x[i+7],16,-155497632);b=C(b,c,d,a,x[i+10],23,-1094730640);
		a=C(a,b,c,d,x[i+13],4,681279174);d=C(d,a,b,c,x[i],11,-358537222);c=C(c,d,a,b,x[i+3],16,-722521979);b=C(b,c,d,a,x[i+6],23,76029189);
		a=C(a,b,c,d,x[i+9],4,-640364487);d=C(d,a,b,c,x[i+12],11,-421815835);c=C(c,d,a,b,x[i+15],16,530742520);b=C(b,c,d,a,x[i+2],23,-995338651);
		a=D(a,b,c,d,x[i],6,-198630844);d=D(d,a,b,c,x[i+7],10,1126891415);c=D(c,d,a,b,x[i+14],15,-1416354905);b=D(b,c,d,a,x[i+5],21,-57434055);
		a=D(a,b,c,d,x[i+12],6,1700485571);d=D(d,a,b,c,x[i+3],10,-1894986606);c=D(c,d,a,b,x[i+10],15,-1051523);b=D(b,c,d,a,x[i+1],21,-2054922799);
		a=D(a,b,c,d,x[i+8],6,1873313359);d=D(d,a,b,c,x[i+15],10,-30611744);c=D(c,d,a,b,x[i+6],15,-1560198380);b=D(b,c,d,a,x[i+13],21,1309151649);
		a=D(a,b,c,d,x[i+4],6,-145523070);d=D(d,a,b,c,x[i+11],10,-1120210379);c=D(c,d,a,b,x[i+2],15,718787259);b=D(b,c,d,a,x[i+9],21,-343485551);
		
		a=X(a,oa);
		b=X(b,ob);
		c=X(c,oc);
		d=X(d,od);
	}
	b=[a,b,c,d];
	s="";
	for(i=0;i<16;i++)s+=hx.charAt((b[i>>2]>>((i%4)*8+4))&0xF)+hx.charAt((b[i>>2]>>((i%4)*8))&0xF);
	return s
}


lLib.includeJS = includeJS;

lLib.getRandomPlacer = function () {
	var placerObj = document.body.firstChild;
	do {
		if(placerObj.nodeType == 1) {
			return placerObj;
			break;
		}
	} while(placerObj = placerObj.nextSibling);

	return false;
}

lLib.createVoidContainer = function () {
//	var placerObj = lLib.getRandomPlacer();
	var contObj = document.createElement("div");
	return document.body.appendChild(contObj);
};




lLib.prototype.is_loaded = false;
lLib.prototype.onLoadEvents = new Array();
lLib.prototype.requestStack = new Array();


lLib.prototype.base64_encode = function(sStr, bUrlMode) {
	if (typeof(bUrlMode) === 'undefined') var bUrlMode = false;
	if (!bUrlMode) bUrlMode = false;
	//
	var sWinChrs = 'АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдежзийклмнопрстуфхцчшщъыьэюя'
	if (bUrlMode) { // for base64url mode
	var sBase64Chrs = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_'
	} else {
	var sBase64Chrs = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'
	}
	
	var arrBase64 = sBase64Chrs.split('')
	
	var a = new Array();
	var i = 0;
	for(i=0; i<sStr.length; i++ ) {
		var cch=sStr.charCodeAt(i)
		if (cch>127) {
			cch=sWinChrs.indexOf(sStr.charAt(i))+163; if(cch<163) continue; 
		}
		a.push(cch)
	};
	var s=Array(), lPos = a.length - a.length % 3
	for (i=0; i<lPos; i+=3) {
		var t=(a[i]<<16)+(a[i+1]<<8)+a[i+2]
		s.push(arrBase64[(t>>18)&0x3f]+arrBase64[(t>>12)&0x3f]+arrBase64[(t>>6)&0x3f]+arrBase64[t&0x3f] )
	}

	switch (a.length-lPos) {
		case 1 : var t=a[lPos]<<4; s.push(arrBase64[(t>>6)&0x3f]+arrBase64[t&0x3f]+'=='); break
		case 2 : var t=(a[lPos]<<10)+(a[lPos+1]<<2); s.push(arrBase64[(t>>12)&0x3f]+arrBase64[(t>>6)&0x3f]+arrBase64[t&0x3f]+'='); break
	}
	return s.join('')
}



lLib.prototype.attachOnLoadEvent = function (handler) {
	if(typeof handler == "function") {
		this.onLoadEvents[this.onLoadEvents.length] = handler;
	} else {
		return false;
	}
};


lLib.prototype.makeRequest = function (url, handler) {
	var requestId = this.requestStack.length;
	this.requestStack[requestId] = handler;
	
	var s_delim = "?";
	if (url.indexOf('?') !== -1) s_delim = '&';

	url += s_delim + "requestId=" + requestId + "&rnd=" + Math.random();
	lLib.includeJS(url);

	return requestId;
};


lLib.prototype.makeResponse = function (response) {
	if(typeof response != "object") {
		return false;
	}

	var requestHandler;
	var requestId = response.id;

	if(typeof this.requestStack[requestId] == "undefined") {
		return false;
	} else {
		requestHandler = this.requestStack[requestId];
		requestHandler(response);
		this.requestStack[requestId] = undefined;
		return true;
	}
};


lLib.getAbsoluteLocation = function (obj, mode) {
	var pX = 0, pY = 0;

	if(!mode) {
		mode = false;
	}

	if(!obj) {
		return false;
	}

	var oParent = obj.offsetParent;

	while (oParent) {
		pX += oParent.offsetLeft;
		pY += oParent.offsetTop;

		if(mode) {
			if(!this.isIframeChild(oParent)) {
				break;
			}
		}

		oParent = oParent.offsetParent; 
	}

	return { x: pX + obj.offsetLeft, y: pY + obj.offsetTop };
}



function lLibResponse(requestId) {
	this.id = requestId;
}

lLibResponse.prototype.id = null;

function is_safari() {
	return (navigator.userAgent.indexOf("Safari") != -1);
}


function includeJS(src) {
	if(document.getElementsByTagName && document.createElement) {
		var head = document.getElementsByTagName('head')[0];

		var script = document.createElement('script');
		script.type = 'text/javascript';
		script.src = src;
		script.charset = "utf-8";

		head.appendChild(script);

		return true;
	} else {
		return false;
	}
}

function includeCSS(src) {
	if(document.getElementsByTagName && document.createElement) {
		var head = document.getElementsByTagName('head')[0];

		var script = document.createElement('link');
		script.setAttribute('rel', "stylesheet");
		script.setAttribute('href', src);

		head.appendChild(script);

		return true;
	} else {
		return false;
	}
}