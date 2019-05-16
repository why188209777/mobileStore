/*
 * 评论管理
 */
$(function(){
	function getAllComments(){
		$.ajax({
			type:"post",
			url:"http://localhost:8080/手机商城/CommentServlet",
			async:true,
			cache:false,
			data:{
				op:"getAllComments"
			},
			dataType:"json",
			success:function(data){
				console.log(data)
				getDataComment(data);	
			},
			error:function(error){
				console.log(error);
			}	
		});
	}
	//填充评论DOM
	function getDataComment(data){
		if(!data.list || data.length==0){
			alert("暂无数据");
			return false;
		}
		currentPage=data.currentPage;//当前页码赋值
		totalPage=data.totalPage;//总共页码
		var $tablebody=$(".tablebody");
		$tablebody.html("");
		var str="";
		for(let i=0;i<data.list.length;i++){
			var contentTime=data.list[i].contentTime.substring(0,10);
			str+=`
				<div class="row commentbody">
					<div class="col-xs-1 commentid">
						<span>${data.list[i].id}</span>
					</div>
					<div class="col-xs-1 phoneid">
						<span>${data.list[i].phoneid}</span>
					</div>
					<div class="col-xs-1 userid">
						<span>${data.list[i].userid}</span>
					</div>
					<div class="col-xs-1 contentTime">
						<span>${contentTime}</span>
					</div>
					<div class="col-xs-4 content" style="text-align: center;">
						<span>${data.list[i].content}</span>
					</div>
					<div class="col-xs-1 commentip">
						<span>${data.list[i].ip}</span>
					</div>
					<div class="col-xs-1 commentequipment" style="text-align: center;">
						<span>${data.list[i].equipment}</span>
					</div>
					<div class="col-xs-2" style="text-align: center;">
						<button class="btn btn-success btn-xs" data-toggle="modal" data-target="#reviseComment" id="realModify">修改</button>
						<button class="btn btn-danger btn-xs" data-toggle="modal" data-target="#deleteComment" id="realRemove">删除</button>
					</div>
				</div>
			`;
		}
		$tablebody.append(str);
		
		str="";//重置
		var $pagination=$("ul.pagination");
		$pagination.html("");
		str+=`
			<li>
				<a class="realPrePage">
					<font color="black">&laquo;</font>
				</a>
			</li>
		`;
		for(let i=0;i<data.totalPage;i++){
			if((i+1)==data.currentPage){
				str+=`
					<li class="active">
						<a class="nextPage">
							<font color="black">${i+1}</font>
						</a>
					</li>
				`;
			}else{
				str+=`
					<li>
						<a class="nextPage">
							<font color="black">${i+1}</font>
						</a>
					</li>
				`;
			}
		}
		str+=`
			<li>
				<a class="realNextPage">
					<font color="black">&raquo;</font>
				</a>
			</li>
			<li class="gray">
				<font color="black">共${data.totalPage}页</font>
			</li>
		`;
		$pagination.append(str);
	}
	
	//获取所有评论
	getAllComments();
	
	
	//分页跳转
	
	//分页跳转
	$(document).on("click","ul.pagination li a.nextPage",function(){
		var pageIndex=$(this).children("font").text();
		currentPage=pageIndex;
		console.log("页码："+pageIndex);
		$.ajax({
			type:"post",
			url:"http://localhost:8080/手机商城/CommentServlet",
			cache:false,
			data:{
				pageIndex:pageIndex,
				op:"getAllComments"
			},
			dataType:"json",
			success:function(data){
				getDataComment(data);
				
			},
			error:function(error){
				console.log(error);
			}
		});
	});
	
	//上一页
	$(document).on("click","a.realPrePage",function(){	
		var pageIndex;
		if(currentPage==1){
			alert("已经是第一页！");
			return false;
		}else{
			pageIndex=currentPage-1;//上一页
		}
		currentPage--;
		console.log("页码："+currentPage);
		$.ajax({
			type:"post",
			url:"http://localhost:8080/手机商城/CommentServlet",
			cache:false,
			data:{
				pageIndex:pageIndex,
				op:"getAllComments"
			},
			dataType:"json",
			success:function(data){
				getDataComment(data);
				
			},
			error:function(error){
				console.log(error);
			}
		});
	});
	//下一页
	$(document).on("click",".realNextPage",function(){
		var pageIndex;
		if(currentPage==totalPage){
			alert("已经最后一页！");
			return false;
		}else{
			pageIndex=currentPage+1;
		}
		currentPage++;
		console.log("页码："+currentPage);
		$.ajax({
			type:"post",
			url:"http://localhost:8080/手机商城/CommentServlet",
			cache:false,
			data:{
				pageIndex:pageIndex,
				op:"getAllComments"
			},
			dataType:"json",
			success:function(data){
				getDataComment(data);
				
			},
			error:function(error){
				console.log(error);
			}
		});
	});
	
	
	
	
	//修改
	$(document).on("click","#realModify",function(){
		var commentid=$(this).parent().parent().children(".commentid").children("span").text();//评论id
		var phoneid=$(this).parent().parent().children(".phoneid").children("span").text();//评论的手机编号
		var userid=$(this).parent().parent().children(".userid").children("span").text();//评论的用户ID
		var contentTime=$(this).parent().parent().children(".contentTime").children("span").text();//评论时间
		var content=$(this).parent().parent().children(".content").children("span");//评论的内容
		var commentip=$(this).parent().parent().children(".commentip").children("span").text();//评论的IP
		var commentequipment=$(this).parent().parent().children(".commentequipment").children("span").text();//评论的设备
		//填充模态框数据
		$("#commentid2").val(commentid);
		$("#commentphoneid2").val(phoneid);
		$("#commentip2").val(commentip);
		$("#commentequipment2").val(commentequipment);
		//判断点击事件源
		$(document).on("click",function(){
			var obj = event.srcElement; 
			if(obj.type == "button"){ 
				if(obj.id=="updatecomment"){
					//评论内容
					var commentcontent=$("#commentcontent2").val();
					$.ajax({
						type:"post",
						url:"http://localhost:8080/手机商城/CommentServlet",
						async:true,
						cache:false,
						data:{
							op:"modifyMangeComments",
							commentid:commentid,
							commentcontent:commentcontent
						},
						dataType:"json",
						success:function(data){
							console.log(data)
							if(data>0){
								//更改
								content.text(commentcontent);
							}
						},
						error:function(error){
							console.log(error);
						}	
					});
				}else{
					console.log("修改失败");
				}
			}
		});
	});
	
	
	//删除(ok)
	$(document).on("click","#realRemove",function(){
		var $commentbody=$(this).parent().parent();
		var commentid=$(this).parent().parent().children(".commentid").children("span").text();//评论id
		console.log(commentid);
		//判断点击事件源
		$(document).on("click",function(){
			var obj = event.srcElement; 
			if(obj.type == "button"){ 
				if(obj.id=="btnRemove"){
					console.log("删除成功！");
					$commentbody.remove();//移除自己及其子元素
					//两种更新页面方法
					//1.这里直接前端页面改变dom结构
					//2.ajax返回函数里重新遍历
					$.ajax({
						type:"post",
						url:"http://localhost:8080/手机商城/CommentServlet",
						async:true,
						cache:false,
						data:{
							op:"removeMangeComments",
							commentid:commentid
						},
						dataType:"json",
						success:function(data){
							console.log(data)
							if(data>0){
								console.log("删除评论成功！");
							}
						},
						error:function(error){
							console.log(error);
						}	
					});
				}else{
					console.log("取消删除")
				}
			}
		})
	});
	
	//模糊查询
	$(document).on("click","#queryKeyWords",function(){
		var keyword=$(this).prev().val();
		console.log(keyword);
		$.ajax({
			type:"post",
			url:"http://localhost:8080/手机商城/CommentServlet",
			async:true,
			cache:false,
			data:{
				op:"getVagueComment",
				keyword:keyword
			},
			dataType:"json",
			success:function(data){
				console.log(data)
				getDataComment(data);
			},
			error:function(error){
				console.log(error);
			}	
		});
		
	});
});
