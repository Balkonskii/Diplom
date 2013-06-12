jQuery(document).ready(function() {					
			
			jQuery('#featured_content').height(jQuery('#featured_content').height() + 15);						
			jQuery('img[class="attachment-441x269 wp-post-image"]').width(jQuery('img[class="attachment-441x269 wp-post-image"]').width() + 2);	
			jQuery('img[class="attachment-160x160 wp-post-image"]').height(jQuery('img[class="attachment-160x160 wp-post-image"]').height() + 1);	
			
			if(jQuery.browser.msie)
			{

			}
			else
			{
				jQuery('img[class="attachment-441x269 wp-post-image"], img[class="attachment-160x160 wp-post-image"]').insetBorder({
					borderType: "double"
				 });	
			}
			
			
		});