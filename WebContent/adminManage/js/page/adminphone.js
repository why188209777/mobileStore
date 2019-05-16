/*
 * 手机管理
 */
$(function(){
	//全局变量
	var currentPage;//当前页码
	var totalPage;//总共页码
	//初始化请求
	function getAllPhone(){
		$.ajax({
			type:"post",
			url:"http://localhost:8080/手机商城/PhoneServlet",
			async:true,
			cache:false,
			data:{
				op:"getAllPhone"
			},
			dataType:"json",
			success:function(data){
				console.log(data);
				currentPage=data.currentPage;//当前页码赋值
				totalPage=data.totalPage;//总共页码
				getDataPhone(data);	
			},
			error:function(error){
				console.log(error);
			}	
		});
	}
	
	
	//初始化渲染数据
	
	function getDataPhone(data){
		if(!data.list || data.length==0){
			console.log("暂无数据");
			return false
		}
		var $tablebody=$(".tablebody");
		$tablebody.html("");
		var str="";
		for(let i=0;i<data.list.length;i++){
			str+=`
				<div class="row phonebody" ">
					<div class="col-xs-1 phoneid">
						<span>${data.list[i].phoneId}</span>
					</div>
					<div class="col-xs-1 phonebrand">
						<span>${data.list[i].brand}</span>
					</div>
					<div class="col-xs-1 phonename">
						<span>${data.list[i].phonename}</span>
					</div>
					<div class="col-xs-1 phoneimage">
						<img src="images/1.png" width="70px" height="70px">
					</div>
					<div class="col-xs-1 phoneprice">
						<span>${data.list[i].price}</span>
					</div>
					<div class="col-xs-1 phonenum">
						<span>${data.list[i].num}</span>
					</div>
					<div class="col-xs-1 phonecpu">
						<span>${data.list[i].cpu}</span>
					</div>
					<div class="col-xs-1 phonesystem">
						<span>${data.list[i].operatingSystem}</span>
					</div>
					<div class="col-xs-2 phonedescription" style="text-align: center;">
						<span>${data.list[i].description}</span>
					</div>
					<div class="col-xs-2" style="text-align: center;">
						<button class="btn btn-success btn-xs" data-toggle="modal" data-target="#revisePhone"  data-phoneid="" id="realMoifyPhone">修改</button>
						<button class="btn btn-danger btn-xs" data-toggle="modal" data-target="#deletePhone"  data-phoneid="" id="realRemovePhone">删除</button>
					</div>
				</div>
			`;
		}
		$tablebody.append(str);
		
		//分页
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
	
	getAllPhone();
	
	//分页跳转
	$(document).on("click","ul.pagination li a.nextPage",function(){
		var pageIndex=$(this).children("font").text();
		currentPage=pageIndex;
		console.log("页码："+pageIndex);
		$.ajax({
			type:"post",
			url:"http://localhost:8080/手机商城/PhoneServlet",
			cache:false,
			data:{
				pageIndex:pageIndex,
				op:"getAllPhone"
			},
			dataType:"json",
			success:function(data){
				getDataPhone(data);
				
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
			url:"http://localhost:8080/手机商城/PhoneServlet",
			cache:false,
			data:{
				pageIndex:pageIndex,
				op:"getAllPhone"
			},
			dataType:"json",
			success:function(data){
				getDataPhone(data);
				
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
			url:"http://localhost:8080/手机商城/PhoneServlet",
			cache:false,
			data:{
				pageIndex:pageIndex,
				op:"getAllPhone"
			},
			dataType:"json",
			success:function(data){
				getDataPhone(data);
				
			},
			error:function(error){
				console.log(error);
			}
		});
	});
	
	//添加手机配置信息
	$(document).on("click","#addphone",function(){
		var phoneid1=$("#phoneid1").val();
		var phonebrand1=$("#phonebrand1").val();
		var phonename1=$("#phonename1").val();
		var phoneprice1=$("#phoneprice1").val();
		var phoneimage1=$("#phoneimage1").val();
		var phoneprice1=$("#phoneprice1").val();
		var phonesize1=$("#phonesize1").val();
		var phonecolcor1=$("#phonecolcor1").val();
		var phonenum1=$("#phonenum1").val();
		var phoneram1=$("#phoneram1").val();
		var phonerom1=$("#phonerom1").val();
		var phonenettype1=$("#phonenettype1").val();
		var phonecamera1=$("#phonecamera1").val();
		var phonecpu1=$("#phonecpu1").val();
		var phoneoperatingsystem1=$("#phoneoperatingsystem1").val();
		var phonecontent1=$("#phonecontent1").val();
		console.log("开始添加手机");
		//非空判断跳出
		if(!phoneid1){
//			alert("请输入！")
			return false;
		}
		$.ajax({
			type:"post",
			url:"http://localhost:8080/手机商城/PhoneServlet",
			cache:false,
			data:{
				op:"addPhone",
				phoneid1:phoneid1,
				phonebrand1:phonebrand1,
				phonename1:phonename1,
				phoneprice1:phoneprice1,
				phoneimage1:phoneimage1,
				phonesize1:phonesize1,
				phonecolcor1:phonecolcor1,
				phonenum1:phonenum1,
				phoneram1:phoneram1,
				phonerom1:phonerom1,
				phonenettype1:phonenettype1,
				phonecamera1:phonecamera1,
				phonecpu1:phonecpu1,
				phoneoperatingsystem1:phoneoperatingsystem1,
				phonecontent1:phonecontent1
			},
			dataType:"json",
			success:function(data){
				if(data>0){
					console.log("添加成功！")
				}
			},
			error:function(error){
				console.log(error);
			}
		});
	});
	
	//查看Phone详细信息
	$(document).on("click","#getPhoneDetail",function(){
		console.log("查看详细信息");
		var phoneId=$(this).parent().parent().children(".phoneid").children("span").text(); 
		console.log("phoneID:"+phoneId);
		
		
	});
	
	//修改手机配置
	$(document).on("click","#realMoifyPhone",function(){
		console.log("修改配置信息");
		var phoneId=$(this).parent().parent().children(".phoneid").children("span").text(); 
		var phoneBrand=$(this).parent().parent().children(".phonebrand").children("span").text();
		var phoneName=$(this).parent().parent().children(".phonename").children("span").text();
		var phoneCpu=$(this).parent().parent().children(".phonecpu").children("span").text();
		var phoneSystem=$(this).parent().parent().children(".phonesystem").children("span").text();
		var phoneNum=$(this).parent().parent().children(".phonenum").children("span");
		var phonePrice=$(this).parent().parent().children(".phoneprice").children("span");
		var phoneDescription=$(this).parent().parent().children(".phonedescription").children("span");
		//填充模态框
		//不可编辑数据
		$("#phoneid2").val(phoneId);
		$("#phonebrand2").val(phoneBrand);
		$("#phonename2").val(phoneName);
		$("#phonecpu2").val(phoneCpu);
		$("#phoneoperatingsystem2").val(phoneSystem);
		//可编辑数据
		$("#phoneprice2").attr("placeholder",phonePrice.text());
		$("#phonenum2").attr("placeholder",phoneNum.text());
		$("#phonecontent2").attr("placeholder",phoneDescription.text());
		//#updatephone
		//判断点击事件源
		$(document).on("click",function(){
			var obj = event.srcElement; 
			if(obj.type == "button"){ 
				if(obj.id=="updatephone"){
					var phonenum=$("#phonenum2").val();
					var phoneprice=$("#phoneprice2").val();
					var phonedescription=$("#phonecontent2").val();
					$.ajax({
						type:"post",
						url:"http://localhost:8080/手机商城/PhoneServlet",
						async:true,
						cache:false,
						data:{
							op:"modifyMangePhone",
							phoneId:phoneId,
							phoneNum:phonenum,
							phonePrice:phoneprice,
							phoneDescription:phonedescription
						},
						dataType:"json",
						success:function(data){
							console.log(data)
							if(data>0){
								//更改
								phoneNum.text(phonenum);
								phonePrice.text(phoneprice);
								phoneDescription.text(phonedescription);
							}
						},
						error:function(error){
							console.log(error);
						}	
					});
				}
			}	
		});
	});
	
	
	//删除手机realRemovePhone
	$(document).on("click","#realRemovePhone",function(){
		var $phonebody=$(this).parent().parent();
		var phoneId=$(this).parent().parent().children(".phoneid").children("span").text(); 
		$(document).on("click",function(){
			var obj = event.srcElement; 
			if(obj.type == "button"){ 
				if(obj.id=="btnRemove"){
					console.log("删除成功！");
					$phonebody.remove();//移除自己及其子元素
					//两种更新页面方法
					//1.这里直接前端页面改变dom结构
					//2.ajax返回函数里重新遍历
					$.ajax({
						type:"post",
						url:"http://localhost:8080/手机商城/PhoneServlet",
						async:true,
						cache:false,
						data:{
							op:"deleteMangePhone",
							phoneId:phoneId
						},
						dataType:"json",
						success:function(data){
							console.log(data)
							if(data>0){
								console.log("删除手机成功！");
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
			url:"http://localhost:8080/手机商城/PhoneServlet",
			async:true,
			cache:false,
			data:{
				op:"getVaguePhone",
				keyword:keyword
			},
			dataType:"json",
			success:function(data){
				console.log(data)
				getDataPhone(data);
			},
			error:function(error){
				console.log(error);
			}	
		});
		
	});
	
	//搜索框聚焦时清空
	$("#inputKeyWord").on("focus",function(){
		$(this).val("");
	})
})
