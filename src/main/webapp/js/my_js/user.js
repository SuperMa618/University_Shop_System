layui
		.use(
				[ 'jquery', 'table', 'layer', 'form' ],
				function() {
					// 加载layui模块，使用其推荐的【预先加载】方式，详见官网【模块规范】一节
					var $ = layui.$;
					var table = layui.table;
					var layer = layui.layer;
					var form = layui.form;

					// 显示所有用户
					table
							.render({
								elem : '#user-tbl',
								url : '/user/all',
								method : 'get',
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
											title : '序号'
										},
										{
											field : 'account',
											title : '账户',
											sort : true
										},
										{
											field : 'password',
											title : '密码'
										},
										{
											field : 'name',
											title : '姓名'
										},
										{
											field : 'info',
											title : '信息'
										},
										{
											field : 'create_Time',
											title : '更新时间',
											templet : "<div>{{layui.util.toDateString(d.create_Time, 'yyyy-MM-dd HH:mm:ss')}}</div>"
										}, {
											toolbar : '#user-tbl-toolbar',
											title : '操作'
										} ] ],
								// 表格容器id，用于表格重载
								id : 'user-tbl',
							});
					// 分页位置
					$('.layui-table-page>div').css("text-align", "center");

					// 点击获取数据进行查询
					$('#domew').on('click', function() {
						var demoReload = $('#demoReload').val();
						// layer.msg(demoReload.length);
						if (demoReload.length == '') {
							table.reload('user-tbl', {
								where : {
									'account' : ''
								}
							});
						}
						if (demoReload != '') {
							table.reload('user-tbl', {
								where : {
									'account' : demoReload
								}
							});
						}

					});
					// 重置输入框resetD
					$('#resetD').on('click', function() {
						// $('#demoReload')[0].reset();
						$('#demoReload').val('');
					});

					// 显示添加用户弹出层
					$('#add-user-btn').click(function() {
						// 每次显示前重置表单
						$('#add-user-form')[0].reset();
						layer.open({
							type : 1,
							title : '添加用户',
							skin : 'layui-layer-molv',
							area : [ '750px' ],
							content : $('#add-user-layer')
						});
					});

					// 添加用户表单提交
					form.on('submit(add-user-form-submit)', function(data) {
						// ajax方式添加用户
						$.ajax({
							url : "/user",
							type : "POST",
							data : JSON.stringify(data.field),
							contentType : 'application/json',
							dataType : 'json',
							success : function(data) {
								if (data.status == 1) {
									layer.close(layer.index);
									parent.layer.msg('添加成功', {
										icon : 1,
										shade : 0.4,
										time : 1000
									});
									table.reload('user-tbl');
								} else {
									parent.layer.msg('添加失败', {
										icon : 2,
										shade : 0.3,
										time : 1000
									});
								}
							},
							error : function() {
								console.log("ajax error");
							}
						});
						// 阻止表单跳转
						return false;
					});

					// 监听行工具栏事件：删除用户与更新用户
					table.on('tool(user-tbl)', function(obj) {
						// 获取当前行数据和lay-event的值
						var data = obj.data;
						var event = obj.event;

						// 删除用户事件
						if (event === 'deleteUser') {
							layer.confirm('确定删除该用户吗？', function(index) {
								// ajax方式删除用户
								$.ajax({
									url : '/user/' + data.id,
									type : "DELETE",
									dataType : 'json',
									success : function(data) {
										if (data.status == 1) {
											parent.layer.msg('删除成功', {
												icon : 1,
												shade : 0.3,
												time : 1000
											});
											table.reload('user-tbl');
										} else {
											parent.layer.msg('删除失败', {
												icon : 2,
												shade : 0.3,
												time : 1000
											});
										}
									},
									error : function() {
										console.log("ajax error");
									}
								});
								layer.close(index);
							});
						}

						// 更新用户事件
						if (event === 'updateUser') {
							// 每次显示更新用户的表单前自动为表单填写该行的数据
							form.val('update-user-form', {
								"id" : data.id,
								"account" : data.account,
								"password" : data.password,
								"name" : data.name,
								"info" : data.info
							});
							// 显示更新用户表单的弹出层
							layer.open({
								type : 1,
								title : '更新用户',
								skin : 'layui-layer-molv',
								area : [ '500px' ],
								content : $('#update-user-layer')
							});
							// 更新用户表单提交
							form.on('submit(update-user-form-submit)',
									function(data) {
										// ajax方式更新用户
										$.ajax({
											url : "/user",
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
														time : 1000
													});
													table.reload('user-tbl');
												} else {
													parent.layer.msg('更新失败', {
														icon : 2,
														shade : 0.3,
														time : 1000
													});
												}
											},
											error : function() {
												console.log("ajax error");
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
					$('#del-users-btn').on('click', function() {
						var checkStatus = table.checkStatus('user-tbl');
						if (checkStatus.data.length == 0) {
							parent.layer.msg('请先选择要删除的数据行！', {
								icon : 7
							});
							return;
						}
						layer.confirm('确定删除该用户吗？', function(index) {
							var ids = "";
							for (var i = 0; i < checkStatus.data.length; i++) {
								ids += checkStatus.data[i].id + ",";
							}
							parent.layer.msg('删除中...', {
								icon : 16,
								shade : 0.3,
								time : 3000
							});
							//layer.msg(ids);
							$.ajax({
								url : '/user/ids?ids=' + ids,
								type : "DELETE",
								dataType : 'json',
								success : function(data) {
									if (data.status == 1) {
										parent.layer.msg('删除成功！', {
											icon : 1,
											time : 2000,
											shade : 0.2
										});
										table.reload('user-tbl');
									} else {
										parent.layer.msg('删除失败！', {
											icon : 2,
											time : 2000,
											shade : 0.2
										});
									}
								},
								error : function() {
									console.log("ajax error");
								}
							});
							layer.close(index);
						});

					});

				});
