tinymce.init({
	relative_urls: false,
	convert_urls: false,
	remove_script_host : false,
	
	//valid_elements: "@[class],p[style],h3,h4,h5,h6,a[href|target],strong/b,div[align],br,table,tbody,thead,tr,td,ul,ol,li,img[src]",
	invalid_elements : "html,head,body",
	language:"zh_TW",
	selector: tinymce_selector,
	theme: "modern",
	convert_urls: false,	
	plugins: [
		"advlist autolink autosave link imageupload lists charmap print preview hr anchor pagebreak spellchecker",
		"searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
		"save table contextmenu directionality emoticons template textcolor paste textcolor colorpicker layer"//fullpage 	
	],
	external_plugins: {
		//"moxiemanager": "/moxiemanager-php/plugin.js"
	},
	
	content_css : "/MaZuStockAdmin/resources/admin/css/template.css",	//content css
	templates: [
	            { title: '表格樣板一', content: "<table class='td_type tC type2' border='0' width='100%' cellspacing='0' cellpadding='0'><tbody><tr><th scope='col'>欄位一</th><th scope='col'>欄位二</th><th scope='col'>欄位三</th><th scope='col'>欄位四</th><th scope='col'>欄位五<br />分類一</th></tr><tr><td>2014</td><td>4525</td><td>4.6%</td><td>1.6%</td><td>-0.1%</td></tr><tr><td>2014</td><td>4525</td><td>4.6%</td><td>1.6%</td><td>-0.1%</td></tr></tbody></table>" }
	],
	
	add_unload_trigger: false,
	autosave_ask_before_unload: false,
	// , preview
	toolbar1: "bold,italic,underline,strikethrough,|,bullist,numlist,outdent,indent,|,alignleft,aligncenter,alignright,alignjustify ,formatselect,fontselect,fontsizeselect,|,forecolor,backcolor",
	toolbar2 : "cut,copy,paste,|,undo,redo,|,link,unlink,imageupload,cleanup,code,|,table , textcolor, insertlayer ,mybutton ,mybutton1 ,template",
	menubar: false,
	setup: function(editor) {
	    editor.addButton('mybutton', {
	    	text: '插入K線',
			tooltip: '插入K線',
			cmd: 'plugin_command'
	    });
	    editor.addCommand( 'plugin_command', function() {
			// Calls the pop-up modal
			editor.windowManager.open({
				// Modal settings
				title: '選擇股票代號',
				width: jQuery( window ).width() * 0.3,
				// minus head and foot of dialog box
				height: (jQuery( window ).height() - 36 - 50) * 0.3,
				inline: 1,
				id: 'plugin-slug-insert-dialog',
				buttons: [{
					text: 'Insert',
					id: 'plugin-slug-button-insert',
					class: 'insert',
					onclick: function( e ) {
						insertShortcode(editor);
					},
				},
				{
					text: 'Cancel',
					id: 'plugin-slug-button-cancel',
					onclick: 'close'
				}],
			});
			jQuery( '#plugin-slug-insert-dialog-body' ).append( "<br><br>" );
			jQuery( '#plugin-slug-insert-dialog-body' ).append( "請輸入股票代號:" );
			jQuery( '#plugin-slug-insert-dialog-body' ).append( "<input type='text' name='stock_no' id='stock_no' style='width:100px' >" );
	    });
			
	  },
	toolbar_items_size: 'small',

	style_formats: [
		{title: 'Bold text', inline: 'b'},
		{title: 'Red text', inline: 'span', styles: {color: '#ff0000'}},
		{title: 'Red header', block: 'h1', styles: {color: '#ff0000'}},
		{title: 'Example 1', inline: 'span', classes: 'example1'},
		{title: 'Example 2', inline: 'span', classes: 'example2'},
		{title: 'Table styles'},
		{title: 'Table row 1', selector: 'tr', classes: 'tablerow1'}
	],

    spellchecker_callback: function(method, data, success) {
		if (method == "spellcheck") {
			var words = data.match(this.getWordCharPattern());
			var suggestions = {};

			for (var i = 0; i < words.length; i++) {
				suggestions[words[i]] = ["First", "second"];
			}

			success({words: suggestions, dictionary: true});
		}

		if (method == "addToDictionary") {
			success();
		}
	},
	
	
	imageupload_url: "../tinymce/uploadImg/upload.do"
	
//    file_browser_callback: function(field_name, url, type, win) {
//        if(type=='image') $('#my_form input').click();
//    }
});


function insertShortcode (editor) {
	var stock_no = $("#stock_no").val();
	if(stock_no==''){
		alert("請輸入股票代號");
		return false;
	}
	
	var data = "stock_no="+stock_no;
	$.getJSON('/MaZuStockAdmin/admin/tinymce/stock/query.do', data, function (data, status) {
		  if (status == 'success') {
		   	if(data.status == '0'){
		   		alert("查無此股票代號");
				return false;
		   	}else{
		   		editor.insertContent("<img src='"+data.url+"' >");
		   		editor.windowManager.close();
		   	}
		  }else{
			  alert("查無此股票代號");
				return false;
		  }
	});
}