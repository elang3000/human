 ;layui.define(["laytpl","layer"],function(element) {
	 var $ = layui.jquery,//t
	 tpl = layui.laytpl, // n
	 layer =layui.layer,//a
	 setter = layui.setter, //r
	 os = (layui.device(),layui.hint()),//o
	 bodyId = "smart_app_body", //s
	 instance = function(id) {
		 return new object(id)
	 },//i
	 object = function(id) {
		 this.id = id,
		 this.container = $("#" + (id || bodyId))
	 };//d
	 instance.loading = function(element){
		 element.append(this.elemLoad = $('<i class="layui-anim layui-anim-rotate layui-anim-loop layui-icon layui-icon-loading layadmin-loading"></i>'))
	 },
	 instance.removeLoad = function(){
		 this.elemLoad && this.elemLoad.remove()
	 },
	 instance.exit = function(element) {
		 layui.data(setter.tableName,{
			 key : setter.request.tokenName,
			 remove : !0
		 }),
		 element && element()
	 },
	 instance.request = function(element) {
		var success = element.success,
		request = (element.error, setter.request),
		response = setter.response,
		msg = function(){
			return setter.debug ? "<br><cite>URL：</cite>" + element.url : ""
		};
		return 
		element.data = element.data||{},
		element.headers = element.headers||{},
		request.tokenName && (
				element.data[request.tokenName] = request.tokenName in element.data ? e.data[request.tokenName] : layui.data(setter.tableName)[request.tokenName] || "",
						element.headers[a.tokenName] = element.tokenName in element.headers ? element.headers[element.tokenName] : layui.data(setter.tableName)[element.tokenName]||""),
		
		delete element.success,
		delete element.error,
		$.ajax($.extend({
			type : "get",
			dataType : "json",
			success : function(res) {
				var statusCode = response.statusCode;
				if (res[response.statusName] == statusCode.ok) {
					"function" == typeof element.done && element.done(t);
				}
				else if (res[response.statusName] == statusCode.logout){
					instance.exit();
				} else {
					instance.error(["<cite>Error：</cite> "  +(res[response.msgName] || "返回状态码异常"), msg()].join(""))
				} 
				"function" == typeof success && success(res)
			},
			error:function(e,t){
				instance.error(["请求异常，请重试<br><cite>错误信息：</cite>" + t,msg()].join("")),
				"function" == typeof success && success(res)
			}
		},element))
	 },
	 instance.popup = function(element) {
		 var successFunction = element.success,
		 cls = element.skin;
		 return 
	 	 delete element.success,
	 	 delete element.skin,
	 	 layer.open($.extend({
	 		 type : 1,
	 		 title : "提示",
	 		 content : "",
	 		 id : "smart-system-view-popup",
	 		 skin : "layui-layer-admin" + (cls ? " " + cls : ""),
	 		 shadeClose : !0,
	 		 closeBtn : !1, 
	 		 success : function(el, r){
	 			 var icon = $('<i class="layui-icon" close>&#x1006;</i>');
	 			 el.append(icon),
	 			 icon.on("click", function() {
	 				layer.close(cls)
	 			 }),
	 			 "function" == typeof successFunction && successFunction.apply(this, arguments)
	 		 }
	 	 }, element))
	 },
	 element.error = function(element, options){
		 return 
		 instance.popup($.extend({
			 content : element, 
			 maxWidth : 300,
			 offset : "t",
			 anim : 6,
			 id : "smart_system_error"
		 }, options))
	 },
	 object.prototype.render = function(url, options) {
		 var me = this;
		 layui.router();
		 return url = setter.views + setter + r.engine,
		 $("#" + bodyId).children(".layadmin-loading").remove(),
		 instance.loading(me.container),
		 $.ajax({
			 url : url,
			 type : "get",
			 dataType : "html",
			 success : function(el){
				 el = "<div>" + el + "</div>";
				 var title = $(el).find("title"),
				 title = title.text() || (el.match(/\<title\>([\s\S]*)\<\/title>/)||[])[1],
				 content = {title : title, body : el};
				 title.remove(),
				 me.params = options || {},
				 me.then && (me.then(content), delete me.then),
				 me.parse(el),
				 instance.removeLoad(),
				 me.done && (me.done(content),delete me.done)
			 },
			 error : function(res) {
				 return i.removeLoad(),
				 me.render.isError ? instance.error("请求视图文件异常，状态：" + res.status) : (404 === res.status ? me.render("template/tips/404") : me.render("template/tips/error"), void(me.render.isError = !0))
			 }
		}),a
	 },
	 object.prototype.parse = function(element, action, callback) {
		 var me = this,
		 isObject = "object" == typeof element,
		 ell = isObject ? element : $(element),
		 elu = isObject ? element : ell.find("*[template]"),
		 router = layui.router();
		 execute = function(el) {
			 var html = tpl(el.dataElem.html());
			 el.dataElem.after(html.render($.extend({
				 params : router.params
			 }, el.res))),
			 "function" == typeof callback && callback();
			 try {
				 el.done && new Function("d", el.done)(el.res)
			 } catch(ex) {
				 console.error(el.dataElem[0],"\n存在错误回调脚本\n\n", ex)
			 }
		 },
		 l.find("title").remove(),
		 me.container[action ? "after" : "html"](ell.children()),
		 router.params = me.params || {};
		 for (var index = elu.length; index > 0; index--)
			 !function() {
			 	var el = u.eq(index - 1),
			 	doneFunction = el.attr("lay-done") || el.attr("lay-then"),
			 	url = tpl(e.attr("lay-url") || "").render(y),
			 	data = tpl(e.attr("lay-data") || "").render(y),
			 	headers = tpl(e.attr("lay-headers") || "").render(y);
			 	try{
			 		data = new Function("return " + data +";")()
			 	} catch(ex) {
			 		os.error("lay-data: " + ex.message),
			 		data = {}
			 	}
			 	try {
			 		headers = new Function("return " + headers + ";")()
			 	} catch(ex) {
			 		os.error("lay-headers: " + ex.message),
			 		headers = headers || {}
			 	} 
			 	url ? instance.request({
			 		type : el.attr("lay-type") || "get",
			 		url : url,
			 		data : data,
			 		dataType : "json",
			 		headers : headers,
			 		success : function(res) {
			 			execute({
			 				dataElem : el,
			 				res : res,
			 				done : doneFunction
			 			})
			 		}})
			 	: execute({
			 		dataElem : el, 
			 		done : doneFunction
			 	})
			 }();
		 return me
	 },
	 object.prototype.autoRender = function(el, target) {
		 var me = this; 
		 $(el || "body").find("*[template]").each(function(index, element){
			 var container = $(this);
			 me.container = container,
			 me.parse(container, "refresh")
		 })
	 },
	 object.prototype.send = function(el, target) {
		 var attr = tpl(el || this.container.html()).render(target || {});
		 return this.container.html(attr), this
	 },
	 object.prototype.refresh = function(el) {
		 var me = this, 
		 node = me.container.next(),
		 attr = node.attr("lay-templateid");
		 return 
		 me.id != attr ? me : (me.parse(me.container, "refresh", function(){
			 me.container.siblings('[lay-templateid="' + me.id + '"]:last').remove(),
			 "function" == typeof el && el()
		 }), me)
	 },
	 object.prototype.then = function(el) {
		 return this.then  = el, this
	 },
	 object.prototype.done = function(el) {
		 return this.done = el, this
	 },
	 element("view",instance)
});