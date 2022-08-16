function edit(id) {
	location = 'edit.do?id=' + id;
}

function add() {
	location = 'edit.do';
}

function selectAll() {
	var checked = document.getElementsByName("all")[0].checked;
	var sn = document.getElementsByName("ids");
	for ( var i = 0; i < sn.length; i++) {
		sn[i].checked = checked;
	}
}

function deleteMultiple() {
	var sn = document.getElementsByName("ids");
	var count = 0;
	for ( var i = 0; i < sn.length; i++) {
		if (sn[i].checked == true) {
			count++;
		}
	}
	if (count == 0) {
		alert("請勾選刪除項目!");
		return false;
	} else {
		if (confirm("你確定要刪除選取資料?")) {
			document.forms['uForm'].action = 'delete.do';
			document.forms['uForm'].submit();
		}
	}
}

function deleteIt() {
	var sn = document.getElementsByName("ids");
	var count = 0;
	for ( var i = 0; i < sn.length; i++) {
		if (sn[i].checked == true) {
			count++;
		}
	}
	if (count == 0) {
		alert("請勾選刪除項目!");
		return false;
	} else {
		if (confirm("你確定要刪除選取資料?")) {
			document.forms['uForm'].action = 'delete.do';
			document.forms['uForm'].submit();
		}
	}
}

function update(status) {
	var sn = document.getElementsByName("ids");
	var count = 0;
	for ( var i = 0; i < sn.length; i++) {
		if (sn[i].checked == true) {
			count++;
		}
	}
	if (count == 0) {
		alert("請勾選更新項目!");
		return false;
	} else {
		if (confirm("你確定要更新選取資料?")) {
			document.forms['uForm'].action = 'update.do?status=' + status;
			document.forms['uForm'].submit();
		}
	}
}

function update_homepage(homepage) {
	var sn = document.getElementsByName("ids");
	var count = 0;
	for ( var i = 0; i < sn.length; i++) {
		if (sn[i].checked == true) {
			count++;
		}
	}
	if (count == 0) {
		alert("請勾選更新項目!");
		return false;
	} else {
		if (confirm("你確定要更新選取資料?")) {
			document.forms['uForm'].action = 'update_homepage.do?homepage=' + homepage;
			document.forms['uForm'].submit();
		}
	}
}
function update_leftmenu(leftmenu) {
	var sn = document.getElementsByName("ids");
	var count = 0;
	for ( var i = 0; i < sn.length; i++) {
		if (sn[i].checked == true) {
			count++;
		}
	}
	if (count == 0) {
		alert("請勾選更新項目!");
		return false;
	} else {
		if (confirm("你確定要更新選取資料?")) {
			document.forms['uForm'].action = 'update_leftmenu.do?leftmenu=' + leftmenu;
			document.forms['uForm'].submit();
		}
	}
}

function updateSort(id, sort) {
	if (confirm("你確定要更新選取資料?")) {
		location.href = 'updateSort.do?id=' + id + '&sort=' + sort;
	}
}

function drawImage(ImgD, FitWidth, FitHeight) {
	var image = new Image();
	image.src = ImgD.src;
	if (image.width > 0 && image.height > 0) {
		if (image.width / image.height >= FitWidth / FitHeight) {
			if (image.width > FitWidth) {
				ImgD.width = FitWidth;
				ImgD.height = (image.height * FitWidth) / image.width;
			} else {
				ImgD.width = image.width;
				ImgD.height = image.height;
			}
		} else {
			if (image.height > FitHeight) {
				ImgD.height = FitHeight;
				ImgD.width = (image.width * FitHeight) / image.height;
			} else {
				ImgD.width = image.width;
				ImgD.height = image.height;
			}
		}
	}
	try {
		ImgD.style.display = "";
	} catch (e) {
	}
}

function getFileName(url) {
	var strobj = url;
	if (strobj == '') {
		return "";
	} else {
		var file_value = strobj.toUpperCase();
		var index = file_value.lastIndexOf('/', file_value.length);
		var file_attribute = file_value.substr(index + 1);
		return file_attribute;
	}
}

function hasChecked(name) {
	var chkObj = document.getElementsByName(name);
	if (chkObj[0]) {
		for ( var i = 0; i < chkObj.length; ++i) {
			if (chkObj[i].checked) {
				return true;
			}
		}
	} else if (chkObj) {
		return chkObj.checked;
	}
	return false;
}

function trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

function isAscii(pattern) {
	for ( var i = 0; i < pattern.length; i++) {
		if (pattern.charCodeAt(i) > 127) {
			return false;
		}
	}
	return true;
}
function getCookie(name) {
	var start = document.cookie.indexOf(name + "=");
	var len = start + name.length + 1;
	if ((!start) && (name != document.cookie.substring(0, name.length))) {
		return null;
	}
	if (start == -1)
		return null;
	var end = document.cookie.indexOf(";", len);
	if (end == -1)
		end = document.cookie.length;
	return unescape(document.cookie.substring(len, end));
}

function submitForm(url) {
	$("#sForm").attr("action", url);
	$("#sForm").submit();
}