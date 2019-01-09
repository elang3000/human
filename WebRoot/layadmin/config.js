layui.define(['laytpl', 'layer', 'element', 'util'], function(exports) {
	exports('setter', {
		container : 'smart_app', // 容器ID
		base : layui.cache.base, // 记录静态资源所在路径
		views : layui.cache.base + 'tpl/', // 动态模板所在目录
		entry : 'index', // 默认视图文件名
		engine : '.html', // 视图文件后缀名
		pageTabs : true, // 是否开启页面选项卡功能
		name : 'smartConsole',
		tableName : 'smartLocalCache', // 本地存储表名
		MOD_NAME : 'smartRoot', // 模块事件名
		debug : true, // 是否开启调试模式。如开启，接口异常时会抛出异常 URL 等信息
		request : {
			tokenName : false
		// 自动携带 token 的字段名（如：access_token）。可设置 false 不携带。
		},
		// 自定义响应字段
		response : {
			statusName : 'code', // 数据状态的字段名称
			statusCode : {
				ok : 0, // 数据状态一切正常的状态码
				logout : 1001
			// 登录状态失效的状态码
			},
			msgName : 'msg', // 状态信息的字段名称
			dataName : 'data' // 数据详情的字段名称
		},
		// 扩展的第三方模块
		extend : ['echarts', 'echartsTheme'],
		// 主题配置
		theme : {
			// 配色方案，如果用户未设置主题，第一个将作为默认
			color : [
			{
				main : '#28333E',
				logo : '#AA3130',
				selected : '#AA3130',
				alias : '时尚红'
			}, {
				main : '#20222A',
				logo : '#F78400',
				selected : '#F78400',
				alias : '橙色'
			}, {
				main : '#344058',
				logo : '#1E9FFF',
				selected : '#1E9FFF',
				alias : '海洋'
			}, {
				main : '#20222A',
				selected : '#009688',
				alias : '雅黑'
			}, {
				main : '#03152A',
				selected : '#3B91FF',
				alias : '藏蓝'
			}, {
				main : '#2E241B',
				selected : '#A48566',
				alias : '咖啡'
			}, {
				main : '#50314F',
				selected : '#7A4D7B',
				alias : '紫红'
			}, {
				main : '#3A3D49',
				logo : '#2F9688',
				selected : '#5FB878',
				alias : '墨绿'
			}, {
				main : '#24262F',
				logo : '#3A3D49',
				selected : '#009688',
				alias : '经典黑'
			}]
		}
	});
});
