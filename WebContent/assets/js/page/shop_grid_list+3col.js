/**
 * phoneStore
 */
$(function(){
	//全局变量
	//用户名 用户ID
	var nameCookie = $.cookie('username');
	var idCookie = $.cookie('userid');
	if (nameCookie != null){
		$("span[id=username]").text(nameCookie);
		$('a[id=logininfo]').attr('href', 'personpage.html');
	}

	var currentPage;//当前页码
	var totalPage;//总共页码
	//手机商城遍历
	function getPhone(){
		$.ajax({
			type:"post",
			url:"PhoneServlet",
			async:true,
			cache:false,
			data:{
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
	}
	function getDataPhone(data){
		console.log(data);
		currentPage=data.currentPage;//当前页码赋值
		totalPage=data.totalPage;//总共页码
		if(!data.list || data.length==0){
			alert("暂未查到相关手机信息，请重新输入查询！");
			return false;
		}
		var dom=$(".no-gutters-sm");
		dom.html("");
		var str = "";//通用字符串模板
		for(var i=0;i<data.list.length;i++){
			str+=`
				<div class="col-6 col-md-4">
	                <div class="product grid-view">
	                  <div class="product-img_block"><a class="product-img" href="shop_detail.html?${data.list[i].image}"><img src="assets/images/phone/${data.list[i].image}.png" alt=""></a>
	                    <button class="quickview no-round-btn smooth">Quick view</button>
	                    <span style="visibility:hidden">${data.list[i].phoneId}</span>
	                  </div>
	                  <div class="product-info_block">
	                    <h5 class="product-type">${data.list[i].color}</h5><a class="product-name" href="shop__detail.html">${data.list[i].phonename}</a>
	                    <h3 class="product-price">¥${data.list[i].price} 
	                      <del>¥${eval(data.list[i].price+500)}</del>
	                    </h3>
	                    <h5 class="product-rated"><i class="icon_star"></i><i class="icon_star"></i><i class="icon_star"></i><i class="icon_star"></i><i class="icon_star-half"></i><span>(5)</span></h5>
	                    <p class="product-describe">${data.list[i].description}</p>
	                    <h5 class="product-avaiable">Avability: <span>5 In stock</span></h5>
	                    <button class="add-to-wishlist button-borderless"><i class="icon_heart_alt"></i></button>
	                  </div>
	                  <div class="product-select">
	                    <button class="add-to-cart round-icon-btn addCart" style="margin-left:60px">  <i class="icon_bag_alt"></i></button>
	                    <button class="quickview round-icon-btn"> <i class="far fa-eye"></i></button>
	                    <span style="visibility:hidden">${data.list[i].phoneId}</span>
	                  </div>
	                  <div class="product-select_list">
	                    <p class="delivery-status">Free delivery</p>
	                    <h3 class="product-price"> 
	                      <del>¥${eval(data.list[i].price+500)}</del>¥${data.list[i].price} 
	                    </h3>
	                    <button class="add-to-cart normal-btn outline addCart">Add to Cart</button>
	                  </div>
	                </div>
	            </div>
			`;
		}
		dom.append(str);
		
		str = "";//重置str
		//分页栏
		var $shop_paginationUL=$(".shop-pagination ul");
		$shop_paginationUL.html("");
		str+=`
			<li>
            	<button class="no-round-btn smooth realPrePage"> <i class="arrow_carrot-2left"></i></button>
          	</li>
		`;
		if(data.totalPage<10){//显示最大十页
			/*var tempListPhoneNum=0;//初始化
			if(data.list.length<=data.pageSize){
				tempListPhoneNum=1;
			}else{
				if(data.list.length/data.pageSize==0){
					tempListPhoneNum=data.list.length/data.pageSize;
				}else{
					tempListPhoneNum=(data.list.length/data.pageSize)+1;
				}
			}
			console.log(tempListPhoneNum);*/
			for (var j=0;j<data.totalPage;j++) {
				if((j+1)==data.currentPage){
					str+=`
						<li>
		                	<button class="no-round-btn smooth active nextPage">${j+1}</button>
		              	</li>
					`;
				}else{
					str+=`
						<li>
		                	<button class="no-round-btn smooth nextPage">${j+1}</button>
		              	</li>
					`;
				}
			}
		}
		str+=`
			<li>
	            <button class="no-round-btn smooth realNextPage"> <i class="arrow_carrot-2right"></i></button>
	        </li>
		`
		$shop_paginationUL.append(str);
		
		//顶部Phone导航栏
		
		var $department_dropdown_menuUl=$(".department-dropdown-menu ul");
		$department_dropdown_menuUl.html("");
		str="";
		if(data.listBrand.length){
			for(let i=0;i<data.listBrand.length;i++){
				str+=`
					<li><a style="cursor: pointer;"> <i class="icon-2"></i><span>${data.listBrand[i].brand}</span></a></li>
				`;
			}
		}
		$department_dropdown_menuUl.append(str);
		
		
		
		//侧边Phone导航
		var $department_bottomUl=$(".department_bottom ul");
		$department_bottomUl.html("");
		str="";
		if(data.listBrand.length){
			for(let i=0;i<data.listBrand.length;i++){
				str+=`
					<li> <a class="department-link" style="cursor: pointer;">${data.listBrand[i].brand}</a></li>
				`;
			}
		}
		$department_bottomUl.append(str);
	}
	
	//遍历商城
	getPhone();
	
	
	//AllPhone
	$(document).on("click",".department-menu #allAphone",function(){
		$.ajax({
			type:"post",
			url:"PhoneServlet",
			cache:false,
			data:{
				op:"getAllPhone"
			},
			dataType:"json",
			success:function(arr){
				getDataPhone(arr);				
			},
			error:function(error){
				console.log(error);
			}
		});
	});
	
	
	//确定页码分页跳转
	$(document).on("click",".nextPage",function(){
		var pageIndex=$(this).text();
		currentPage=pageIndex;
		$.ajax({
			type:"post",
			url:"PhoneServlet",
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
	$(document).on("click",".realPrePage",function(){	
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
			url:"PhoneServlet",
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
			url:"PhoneServlet",
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
	
	//顶部导航栏
	$(document).on("click",".department-dropdown-menu ul li a span",function(){
		var brand=$(this).text();
		console.log("品牌:"+brand);
		currentPage=1;//重置页码
		$.ajax({
			type:"post",
			url:"PhoneServlet",
			cache:false,
			data:{
				brand:brand,
				op:"getBrandPhone"
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
	
	//侧边栏遍历某一特定品牌
	$(document).on("click",".department_bottom ul li a",function(){
		var brand=$(this).text();
		console.log("品牌："+brand);
		currentPage=1;//重置页码
		$.ajax({
			type:"post",
			url:"PhoneServlet",
			cache:false,
			data:{
				brand:brand,
				op:"getBrandPhone"
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
	
	//筛选价格区间的Phone
	$(document).on("click","#selectPrice",function(){
		currentPage=1;//重置页码
		var minPrice=$("#minPrice").val();
		var maxPrice=$("#maxPrice").val();
		console.log(minPrice);
		console.log(maxPrice);
		if(minPrice<0||maxPrice<0){
			alert("请输入正确的价格区间");
			return false;
		}else if(minPrice.length==0 || maxPrice.length==0){
			alert("请输入正确的价格区间");
			return false;
		}else if(minPrice>maxPrice){
			alert("请输入正确的价格区间");
			return false;
		}else if(isNaN(minPrice) || isNaN(maxPrice)){
			alert("请输入正确的价格区间");
			return false;
		}
		$.ajax({
			type:"post",
			url:"PhoneServlet",
			cache:false,
			data:{
				minPrice:minPrice,
				maxPrice:maxPrice,
				op:"getPricePhone"
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
	//价格聚焦事件清零
	$(document).on("click","#minPrice",function(){
		$(this).val("");
	});
	$(document).on("click","#maxPrice",function(){
		$(this).val("");
	});
	$(document).on("click",".search-input .no-round-btn",function(){
		$(this).val("");
	});
	
	
	//模糊搜索
	$(document).on("click",".website-search .no-round-btn",function(){
		var keyword=$(".no-round-input").val();
		console.log(keyword);
		$.ajax({
			type:"post",
			url:"PhoneServlet",
			cache:false,
			data:{
				keyword:keyword,
				op:"getVaguePhone"
			},
			dataType:"json",
			success:function(data){
				getDataPhone(data);	
			},
			error:function(error){
				console.log(error);
			}
		});
		$(".no-round-input").val("");
	});
	
	//商品缩略图下方按钮添加购物车
	$(document).on("click",".addCart",function(){
		if(!idCookie || idCookie.length==0){
				alert("请先登录！");
				return false;
			}
		var phoneid=$(this).parent().children("span").text();
		console.log(phoneid);
		//直接添加默认数量为1
		var num=1;
		$.ajax({
			type:"post",
			url:"PhoneServlet",
			cache:false,
			data:{
				op:"addCart",
				userid:idCookie,
				phoneid:phoneid,
				num:num
			},
			dataType:"json",
			success:function(data){
				if(data>0){
					alert("添加购物车成功")
				}else{
					alert("添加购物车失败");
				}
			},
			error:function(error){
				console.log(error);
			}
		});
	});
	
	
	//顶部导航栏Select
	$(document).on("click",".categories-select_box ul li",function(){
		var selectName=$(this).val();
		console.log(selectName);
		$("#selectText").val(selectName);
	});
});