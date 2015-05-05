/*
 * jQuery Form Tips 1.2.5
 * By Manuel Boy (http://www.manuelboy.de)
 * Copyright (c) 2011 Manuel Boy
 * Licensed under the MIT License: http://www.opensource.org/licenses/mit-license.php
*/
(function($){
	
	$.fn.formtips = function(options) {
	
		// handle options
		var settings = jQuery.extend({
			tippedClass: "tipped"
		}, options);
	
		return this.each(function() {
			
			// prepare input elements an textareas
			var e = $(this);
			
			// do not apply form tips to inputs of type file, radio or checkbox
			var type = $(e).attr('type');
			if(type != 'file' && type != 'checkbox' && type != 'radio') {
		
				// handle focus event
				$(e).bind('focus', function() {
					var lv = $(this).attr('title');
					if($(this).val() == lv) {
						$(this).val('').removeClass(settings.tippedClass);
					}
					return true;
				});
		
				// handle blur event
				$(e).bind('blur', function() {
					var lv = $(this).attr('title');
					if($(this).val() == '') {
						$(this).val(lv).addClass(settings.tippedClass);
					}
					return true;
				});
		
				// handle initial text
				var lv = $(e).attr('title');
				if($(e).val() == '' || $(e).val() == $(this).attr('title')) {
					$(e).val(lv).addClass(settings.tippedClass);
				} else {
					$(e).removeClass(settings.tippedClass);
				}
			
				// handle removal of default value
				$(e).parentsUntil('form').parent().submit(function() {
					var lv = $(e).attr('title');
					if($(e).val() == lv) {
						$(e).val('').removeClass(settings.tippedClass);
					}
				});
			
			}
		
		});
	};

})(jQuery);