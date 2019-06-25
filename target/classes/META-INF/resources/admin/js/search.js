//鍖�
var ddsy = {};
//鍐呮牳
ddsy.core = {
	/**
	 * 璋冪敤鏈嶅姟鍣ㄧ鐨勫簲鐢ㄦ柟娉�
	 * @param act 鏂规硶鍚嶇О
	 * @param param 鍙傛暟
	 * @param callback 鍥炶皟鍑芥暟锛屾湁鍥炶皟鍑芥暟涓哄紓姝ヨ皟鐢�
	 * @param callbackParam 鍥炶皟鍑芥暟鍙傛暟
	 */
	invoke:function(act, param, callback, callbackParam) {
		var res = null;
    	ddsy.msg.layer.mask();
		$.ajax({
			type:"POST",
			async:(callback != undefined), //鏈夊洖璋冨嚱鏁帮紝浣跨敤寮傛鏂瑰紡
			dataType:"text",
			url:act,
			data:param,
			success:function(rtn) {
		    	ddsy.msg.layer.unmask();
				eval("res = " + rtn);
				if (res.Exception != undefined) {
	                ddsy.msg.err(res.Exception, res.Cause);
				} else if (callback != undefined) {
					callback.call(this, res, callbackParam);
				}
			},
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
		    	ddsy.msg.layer.unmask();
	        	//閿欒
	            if (XMLHttpRequest.status === 0) {
	                ddsy.msg.err('');
	            } else if (XMLHttpRequest.status == 404) {
	                ddsy.msg.err('鏃犳晥鐨勮闂湴鍧� [404]');
	            } else if (XMLHttpRequest.status == 500) {
	                ddsy.msg.err('鏈嶅姟鍣ㄥ唴閮ㄩ敊璇� [500]');
	            } else if (errorThrown === 'parsererror') {
	                ddsy.msg.err('JSON瑙ｆ瀽閿欒.');
	            } else if (errorThrown === 'timeout') {
	                ddsy.msg.err('璁块棶瓒呮椂.');
	            } else if (errorThrown === 'abort') {
	                ddsy.msg.err('Ajax璇锋眰涓柇.');
	            } else {
	                ddsy.msg.err('閿欒锛' + errorThrown + '<hr>' + XMLHttpRequest.responseText);
	            }
	        }
		});
		return res;
	},
	//涓嬭浇
	download:function(act, param, target) {
		var formId = "__downloadForm";
		var form = document.getElementById(formId);
		if (form == null) {
			form = document.createElement("form");
			form.id = formId;
			form.style.display = "none";
			document.body.appendChild(form);
			form.method = 'post';
		} else {
			form.innerHTML = "";
		}
		form.action = act + ".htm";
		if (target == undefined) {
			target = "_blank";
		}
		form.target = target;
		for(var a in param) {
			var el = document.createElement("input");
			el.setAttribute("id", formId + a);
			el.setAttribute("name", a);
			el.setAttribute("type", "hidden");
			form.appendChild(el);
			if (typeof(param[a]) == "object") {
				document.getElementById(formId + a).value = JSON.stringify(param[a]);
			} else {
				document.getElementById(formId + a).value = param[a];
			}
		}
		form.submit();
	},
	//涓婁紶
	upload:function(item, format, act, param, callback) {
		new AjaxUpload(item, {
			action: act,  
		    data: param,
		    responseType:"json",
		    onSubmit: function(file, ext) {
		    	if (format != "") {
		    		var b = false;
		    		eval("b = /^(" + format.replace(",", "|") + ")$/.test(ext)");
		    		if (!b) {  
		    			ddsy.msg.alert("璇烽�鎷" + format + "]鏂囦欢锛");  
		    			return false;  
		    		}
		    	}
		    	ddsy.msg.layer.mask();
		    },  
		    onComplete: function(file, res) {
		    	ddsy.msg.layer.unmask();
				if (callback != undefined) {
					callback.call(this, res);
				}
		    }  
		});
	}
};
//娑堟伅
ddsy.msg = {
	//鏄剧ず淇℃伅
	info:function(msg) {
		$.messager.show({  
            title:"淇℃伅",  
            msg:msg,  
            showType:"slide",  
            style:{  
                right:"",  
                top:document.body.scrollTop+document.documentElement.scrollTop,  
                bottom:""  
            }  
        });
	},
	//鏄剧ず鎻愮ず淇℃伅
	alert:function(msg) {
    	$.messager.alert("鎻愮ず", msg, "info");
	},
	//纭
	confirm:function(msg, onOk) {
    	$.messager.confirm("纭", msg, onOk);
	},
	//鏄剧ず閿欒淇℃伅
	err:function(msg, cause) {
		if (cause) {
			ddsy.msg.errCause = cause;
			msg += "<hr><a href='javascript:ddsy.msg.err(ddsy.msg.errCause)'>[璇︾粏淇℃伅]</a>";
		}
		$.messager.alert("閿欒", "<div style='width:220px;height:120px;overflow:auto;word-wrap:break-word'>" + msg + "</div>", "error");
	},
	layer:function(){  
	    var $mask,$maskMsg;  
	    function init(){  
	        if(!$mask){  
	            $mask = $("<div class=\"datagrid-mask mymask\"></div>").appendTo("body");  
	        }  
	        $mask.css({width:"100%",height:$(document).height(),zIndex:100000});  
	    }  
	    return {  
	        mask:function(msg){  
	            init();  
	            $mask.show();  
	        }  
	        ,unmask:function(){  
	            $mask.hide();  
	        }  
	    }  
	}()
};
//宸ュ叿
ddsy.tool = {
	//鍔犺浇HTML
	loadHtml:function(src) {
		var html = "";
		$.ajax({
			type:"GET",
			async:false,
			cache:false,
			dataType:"text",
			url:src,
			success:function(rtn) {
				html = rtn;
			}
		});
		return html;
	},
	//鍔犺浇JS
	loadJs:function(src) {
		$.ajax({
			type:"GET",
			async:false,
			cache:false,
			dataType:"text",
			url:src,
			success:function(rtn) {
				eval(rtn);
			}
		});
	},
	//鏇挎崲鍙橀噺
	replaceVar:function(str, obj) {
        var key = "";
        var res = "";
        for (var i = 0; i < str.length; i++) {
            if (str.charAt(i) == '$' && i + 1 < str.length && str.charAt(i + 1) == '{') {
                i += 2;
                key = "";
                while (i < str.length) {
                    if (str.charAt(i) == '}') {
                    	if (obj[key]) {
                    		res += obj[key];
                    	} else {
                    		res += "${" + key + "}"
                    	}
                        break;
                    } else {
                        key += str.charAt(i++);
                    }
                }
            } else {
                res += str.charAt(i);
            }
        }
        return res;
	}
}
//绂佺敤backspace瀵艰埅
$(document).keydown(function(event) {
    if (event.keyCode == 8 && !$("input").is(":focus") && !$("textarea").is(":focus")) {
        event.preventDefault();
    }
});

