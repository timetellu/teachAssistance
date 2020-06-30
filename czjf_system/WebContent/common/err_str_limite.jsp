<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <style type="text/css">
	#tip2 {
		position: absolute;
		right: 450px;
		bottom: 0px;
		height: 100px;
		width: 260px;
		border: 1px solid #CCCCCC;
		background-color: #eeeeee;
		padding: 1px;
		overflow: hidden;
		display: none;
		font-size: 12px;
		z-index: 10;
	}
	
	#tip2 p {
		padding: 6px;
	}
	
	#tip2 h1,#detail h1 {
		font-size: 14px;
		height: 25px;
		line-height: 25px;
		background-color: #0066CC;
		color: #FFFFFF;
		padding: 0px 3px 0px 3px;
		filter: Alpha(Opacity = 100);
	}
	
	#tip2 h1 a,#detail h1 a {
		float: right;
		text-decoration: none;
		color: #FFFFFF;
	}
</style>
<script type="text/javascript">
	window.onload = function() { 
		var divTip = document.createElement("div"); 
		divTip.id = "tip2"; 
		divTip.style.top = '0px';
		divTip.style.margin = '0,auto,0,0';
		divTip.style.height = '0px'; 
		divTip.style.bottom = '0px'; 
		divTip.style.position = 'fixed'; 
		document.body.appendChild(divTip); 
		start();
		setInterval("start()", 300000);
	} 
	
	var handle; 
	function start() { 
		var obj = document.getElementById("tip2"); 
		obj.innerHTML = "<h1><a href='javascript:void(0)' οnclick='start()'>×</a>提示</h1><p>输入包含非法数据,</br>例如，--,',\",\\,;,%,>,!,<,</br>iframe,frame,SYS.TAB,script,</br>alert,src,href等</p>"; 
		if (parseInt(obj.style.height) == 0) { 
			obj.style.display = "block"; 
			handle = setInterval("changeH('up')", 20); 
		}else { 
			handle = setInterval("changeH('down')", 20) 
		} 
	} 
	
	function changeH(str) { 
		var obj = document.all ? document.all["tip2"] : document.getElementById("tip2"); 
		if (str == "up") { 
			if (parseInt(obj.style.height) > 150) {
				clearInterval(handle); 
			} else {
				obj.style.height=(parseInt(obj.style.height) + 8).toString() + "px"; 
			}
		} 
		if (str == "down") { 
			if (parseInt(obj.style.height) < 8) { 
				clearInterval(handle); 
				obj.style.display = "none"; 
			} else {
				obj.style.height = (parseInt(obj.style.height) - 8).toString() + "px"; 
			}
		} 
	}
</script>
