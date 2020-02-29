layui
		.use(
				[ 'jquery', 'table', 'layer', 'form', 'laydate' ],
				function() {
					// 加载layui模块，使用其推荐的【预先加载】方式，详见官网【模块规范】一节
					var $ = layui.$;
					var table = layui.table;
					var layer = layui.layer;
					var form = layui.form;
					var laydate = layui.laydate;

					/* 日期选择器 */
					laydate.render({
						elem : '#birthdateS' // 指定元素
					});
					// 显示所有用户
					table
							.render({
								elem : '#student-tbl',
								url : '/admin/findstudent',
								method : 'GET',
								page : true,// 开启分页
								first : '1',
								limit : 10,
								limits : [ 10, 20, 50, 100 ],
								theme : 'ali',
								cols : [ [
										{
											type : 'checkbox',
											fixed : 'left'
										},
										{
											templet : '#indexTpl',
											title : '序号',
											width : 60
										},
										{
											field : 'images',
											title : '头像图片位置',
											width : 100,
										},
										{
											field : 'username',
											title : '姓名',
											sort : true,
											width : 80,
										},
										{
											field : 'studentid',
											title : '学号',
											sort : true,
											width : 80,
										},
										{
											field : 'password',
											title : '密码',
											width : 80,
										},
										{
											field : 'sex',
											title : '性别',
											align : "center",
											templet : function(d) {
												if (d.sex == 1) {
													return '男'
												} else if (d.sex == 2) {
													return '女'
												} else {
													return '未知'
												}
											}
										},
										{
											field : 'birthdate',
											title : '生日',
											width : 80,
											templet : function(d) {
												if (d.birthdate + 1 != 1) {
													return d.birthdate
												} else {
													return ''
												}
											}
										},
										{
											field : 'phone',
											title : '电话',
											width : 80,
										},
										{
											field : 'address',
											title : '地址',
											width : 80,
										},
										{
											field : 'college',
											title : '所在学校',
											width : 80,
										},
										{
											field : 'inclass',
											title : '所在班级',
											width : 80,
										},
										{
											field : 'qq',
											title : 'QQ',
											width : 80,
										},
										{
											field : 'state',
											title : '状态',
											align : "center",
											templet : function(d) {
												if (d.state == 1) {
													return '<a>启用<a>'
												} else {
													return '<a style="color: red;">禁用<a>'
												}
											}
										},
										{
											field : 'updatetime',
											title : '最后更新时间',
											width : 100,
											templet : "<div>{{layui.util.toDateString(d.updatetime, 'yyyy-MM-dd HH:mm:ss')}}</div>"
										},
										{
											field : 'reporttotime',
											title : '报到时间',
											width : 100,
											templet : function(d) {
												if (d.reporttotime + 1 != 1) {
													return d.reporttotime
												} else {
													return '<a style="color: green;">未报到<a>'
												}
											}
										
										}, {
											toolbar : '#student-tbl-toolbar',
											title : '操作',
											width : 150,
											align : "center"
										} ] ],
								// 表格容器id，用于表格重载
								id : 'student-tbl',
							});

					// 重置输入框resetD
					$('#resetD').on('click', function() {
						// $('#reload')[0].reset();
						$('#usernameS').val('');
					});
					// 分页位置
					$('.layui-table-page>div').css("text-align", "center");
					// 点击获取数据进行查询
					$('#domew').on('click', function() {
						var username = $('#usernameS').val();
						var studentid = $('#studentidS').val();
						var inclass = $('#inclassS').val();
						var state = $('#stateS').val();
						layui.data('kvalue', {
							key : 'data',
							value : [ {
								key : 'username',
								value : username
							}, {
								key : 'studentid',
								value : studentid
							}, {
								key : 'inclass',
								value : inclass
							}, {
								key : 'state',
								value : state
							} ]
						});
						var localTest = layui.data('kvalue');
						console.log(localTest.data); // 获得“贤心”
						// console.log(state);
						table.reload('student-tbl', {
							where : {
								username : username,
								studentid : studentid,
								state : state,
								inclass : inclass
							}
						});
						$('.layui-table-page>div').css("text-align", "center");
					});

					// 监听行工具栏事件：删除用户与更新用户
					table.on('tool(student-tbl)', function(obj) {
						// 获取当前行数据和lay-event的值
						var data = obj.data;
						var event = obj.event;
					});

					// 显示添加用户弹出层
					$('#add-student-btn').click(function() {
						// 每次显示前重置表单
						$('#add-student-form')[0].reset();
						layer.open({
							type : 1,
							title : '添加用户',
							skin : 'layui-layer-molv',
							//skin: 'to-fix-select',
							area : [ '500px' ],
							content : $('#add-student-layer')
						});
						
					});
					
					// 班级搜索框弹出
					$.ajax({
						url : "/admin/tree",
						type : "GET",
						dataType : "json",
						success : function(data) {
							// console.log([data.inclass]);
							str = data.inclass; // 这是一字符串
							var strs = new Array(); // 定义一数组
							strs = str.split("+"); // 字符分割
							var inclass = [ {
								"name" : strs[0],
								"value" : 1
							} ]
							for (i = 1; i < strs.length - 1; i++) {
								inclass = inclass.concat([ {
									"name" : strs[i],
									"value" : i + 1
								} ])// 分割后的字符输出
							}
							// console.log(inclass)
							var demo2 = xmSelect.render({
								el : '#demo2',
								clickClose : true,// 选中关闭
								model : {// 隐藏单选图标
									icon : 'hidden',
									label : {
										type : 'text'
									}
								},
								layVerify : 'required',// 表单提交不可为空
								layVerType : 'msg',
								radio : true,// 开启单选
								filterable : true,
								paging : true,
								pageSize : 5,
								direction : 'down',// 方向向下
								data : inclass
							})
							var demo3 = xmSelect.render({
								el : '#demo3',
								clickClose : true,// 选中关闭
								model : {// 隐藏单选图标
									icon : 'hidden',
									label : {
										type : 'text'
									}
								},
								radio : true,// 开启单选
								filterable : true,
								paging : true,
								pageSize : 5,
								direction : 'down',// 方向向下
								data : inclass
							})

						},
						error : function() {
							console.log("ajax error");
							layer.open({
								title : '系统提示',
								content : '发生未知错误，请联系管理员！'
							});
						}
					});
					// console.log(demo3)
					// 添加用户表单提交
					form.on('submit(add-student-form-submit)', function(data) {
						data.field.className = $("#demo2 .label-content")
								.html();
						// console.log(data.field);
						// ajax方式添加用户
						$.ajax({
							url : "/admin/addstudent",
							type : "POST",
							data : JSON.stringify(data.field),
							contentType : 'application/json',
							dataType : 'json',
							success : function(data) {
								if (data.status == 1) {
									layer.close(layer.index);
									$('#resetAddStu').click();
									parent.layer.msg('添加成功', {
										icon : 1,
										shade : 0.4,
										time : 2000
									});
									table.reload('student-tbl');
									$('.layui-table-page>div').css(
											"text-align", "center");
								} else {
									parent.layer.msg('添加失败', {
										icon : 2,
										shade : 0.3,
										time : 2000
									});
								}
							},
							error : function() {
								console.log("ajax error");
								layer.open({
									title : '系统提示',
									content : '发生未知错误，请联系管理员！'
								});
							}
						});
						// 阻止表单跳转
						return false;
					});

					// 监听行工具栏事件：删除用户与更新用户
					table.on('tool(student-tbl)', function(obj) {
						// 获取当前行数据和lay-event的值
						var data = obj.data;
						var event = obj.event;

						// 删除用户事件
						if (event === 'deleteStudent') {
							layer.confirm('确定删除该用户吗？', function(index) {
								// ajax方式删除用户
								$.ajax({
									url : '/admin/delS/' + data.id,
									type : "DELETE",
									dataType : 'json',
									success : function(data) {
										if (data.status == 1) {
											parent.layer.msg('删除成功', {
												icon : 1,
												shade : 0.3,
												time : 2000
											});
											table.reload('student-tbl');
											$('.layui-table-page>div').css(
													"text-align", "center");
										} else {
											parent.layer.msg('删除失败', {
												icon : 2,
												shade : 0.3,
												time : 2000
											});
										}
									},
									error : function() {
										console.log("ajax error");
										layer.open({
											title : '系统提示',
											content : '发生未知错误，请联系管理员！'
										});
									}
								});
								layer.close(index);
							});
						}

						// 更新用户事件
						if (event === 'updateStudent') {
							// 每次显示更新用户的表单前自动为表单填写该行的数据
							$("#idS").val(data.id);
							form.val('update-student-form', {
								"username" : data.username,
								"studentid" : data.studentid,
								"password" : data.password,
								"sex" : data.sex,
								"images" : data.images,
								"birthdate" : data.birthdate,
								"phone" : data.phone,
								"address" : data.address,
								"inclass" : data.inclass,
								"qq" : data.qq,
								"state" : data.state
							});
							$("#demo3 .xm-tips").html(data.inclass);
							// console.log($("#demo3").html());
							// 显示更新用户表单的弹出层
							layer.open({
								type : 1,
								title : '更新用户',
								skin : 'layui-layer-molv',
								area : [ '750px' ],
								content : $('#update-student-layer')
							});
							// 更新用户表单提交
							form.on('submit(update-student-form-submit)',
									function(data) {
										// ajax方式更新用户
										data.field.id=$("#idS").val();
										data.field.inclass = $(
												"#demo3 .label-content").html();
										var text = $("#demo3 .label-content")
												.html();
										console.log(data.field);
										$.ajax({
											url : "/admin/updateStu",
											type : "PUT",
											data : JSON.stringify(data.field),
											contentType : 'application/json',
											dataType : 'json',
											success : function(data) {
												if (data.status == 1) {
													layer.close(layer.index);
													parent.layer.msg('更新成功', {
														icon : 1,
														shade : 0.3,
														time : 2000
													});
													table.reload('student-tbl');
													$('.layui-table-page>div').css("text-align","center")
												}else if (data.status == 2) {
													layer.close(layer.index);
													parent.layer.msg(data.msg, {
														icon : 6,
														shade : 0.3,
														time : 2000
													});
													table.reload('student-tbl');
													$('.layui-table-page>div').css("text-align","center")
												} else {
													parent.layer.msg(data.msg, {
														icon : 2,
														shade : 0.3,
														time : 2000
													});
												}
											},
											error : function() {
												console.log("ajax error");
												layer
												.open({
													title : '系统提示',
													content : '发生未知错误，请联系管理员！'
												});
											}
										});
										// 阻止表单跳转
										return false;
									});
						}

					});

					// 批量删除功能
					// 1.得到table选中行内容
					// 2.得到删除需要的唯一值，一般是id;
					// 3.将所要删除的项加入到数组中；
					// 4.判断是否选中；
					// 5.发送ajax请求，并附带参数id；
					$('#del-students-btn')
							.on(
									'click',
									function() {
										var checkStatus = table
												.checkStatus('student-tbl');
										if (checkStatus.data.length == 0) {
											parent.layer.msg('请先选择要删除的数据行！', {
												icon : 7
											});
											return;
										}
										layer
												.confirm(
														'确定删除该用户吗？',
														function(index) {
															var ids = "";
															for (var i = 0; i < checkStatus.data.length; i++) {
																ids += checkStatus.data[i].id
																		+ ",";
															}
															parent.layer
																	.msg(
																			'删除中...',
																			{
																				icon : 16,
																				shade : 0.3,
																				time : 3000
																			});
															// layer.msg(ids);
															$
																	.ajax({
																		url : '/admin/student/ids?ids='
																				+ ids,
																		type : "DELETE",
																		dataType : 'json',
																		success : function(
																				data) {
																			if (data.status == 1) {
																				parent.layer
																						.msg(
																								'删除成功！',
																								{
																									icon : 1,
																									time : 2000,
																									shade : 0.2
																								});
																				table
																						.reload('student-tbl');
																				$(
																						'.layui-table-page>div')
																						.css(
																								"text-align",
																								"center");
																			} else {
																				parent.layer
																						.msg(
																								'删除失败！',
																								{
																									icon : 2,
																									time : 2000,
																									shade : 0.2
																								});
																			}
																		},
																		error : function() {
																			console
																					.log("ajax error");
																			layer
																					.open({
																						title : '系统提示',
																						content : '发生未知错误，请联系管理员！'
																					});
																		}
																	});
															layer.close(index);
														});

									});
				});
