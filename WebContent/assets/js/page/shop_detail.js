/*
 * 手机详情页
 */
$(function() {
	//全局变量
	var nameCookie = $.cookie('username');
	var idCookie = $.cookie('userid');
	if (nameCookie != null){
		$("span[id=username]").text(nameCookie);
		$('a[id=logininfo]').attr('href', 'personpage.html');
	}
	//用户Name
	//ip
	//设备
	var allUrl = window.location.href;
	var imgUrl = allUrl.substring(allUrl.lastIndexOf('?') + 1);
	var phoneid = imgUrl.substring(0, imgUrl.length - 2);
	console.log(phoneid);
	function getPhoneDetail() {
		//ajax请求图片详情
		$.ajax({
			type:"post",
			url:"PhoneServlet",
			cache:false,
			async:false, 
			data:{
				phoneid:phoneid,
				op:"getPhone"
			},
			dataType:"json",
			success:function(data){
				console.log(data);
				//手机配置
				$(".product_weight").text(data.cpu);
				$(".product_dimensions").text(data.camera);
				$(".product_color").text(data.color);
				$(".product_size").text(data.size+"英寸");
				//手机价格
				$("h5.product-type").text(data.color);
				$("h2.product-name").text(data.phonename);
				var dataDescription;//商品描述
				if(!data.description){
					dataDescription=data.description;			
				}else{
					dataDescription="此商品暂无描述，敬请期待";
				}
				$("p.product-describe").text(dataDescription);
				var priceStr=`
					<del>¥${eval(data.price+500)}</del>${data.price}
				`;
				$("h3.product-price").html(priceStr);
			},
			error:function(error){
				console.log(error);
			}
		});
		
		
		var $shop_detail_img = $(".shop-detail_img");
		$shop_detail_img.html("");
		var str = "";
		str += `<button class="round-icon-btn" id="zoom-btn"> <i class="icon_zoom-in_alt"></i>
				</button>
			  <div class="big-img">
		`;
		for(let i = 0; i < 3; i++) {
			str += `
				<div class="big-img_block"><img src="assets/images/phone/${phoneid}-${i+1}.png" alt="product image"></div>
			`
		}
		str += `</div>
              <div class="slide-img">
              `;
		for(let j = 0; j < 3; j++) {
			str += `
				<div class="slide-img_block"><img src="assets/images/phone/${phoneid}-${j+1}.png" alt="product image"></div>
			`
		}
		str += `</div>`;
		$shop_detail_img.append(str);
		//手机描述图片
		var src="assets/images/phone/"+phoneid+"-"+"1.png";
		$(".img-fluid1").attr("src", src);
		var src="assets/images/phone/"+phoneid+"-"+"2.png";
		$(".img-fluid2").attr("src", src);	
		
	}

	//进入页面请求评论列表
	function getAllComment(){
		$.ajax({
			type:"post",
			url:"CommentServlet",
			cache:false,
			async:false, //同步
			data:{
				phoneId:phoneid,
				op:"commentList"
			},
			dataType:"json",
			success:function(data){
				//渲染评论列表
				console.log(data);
				var $customer_reviews_middle_block=$(".customer-reviews_middle_block");//评论列表
				$customer_reviews_middle_block.html("");//重置
				var str="";
				for(let i=0;i<data.length;i++){
					str+=`
						<div class="customer-review">
                          <div class="row">
                            <div class="col-12 col-sm-3 col-lg-2">
                              <div class="customer-review_left">
                                <div class="customer-review_img text-center"><img class="img-fluid" src="assets/images/shop/reviewer_02.png" alt="customer image"></div>
                                <div class="customer-rate"><i class="icon_star"></i><i class="icon_star"></i><i class="icon_star"></i><i class="icon_star"></i><i class="icon_star-half"></i></div>
                              </div>
                            </div>
                            <div class="col-12 col-sm-9 col-lg-10">
                              <div class="customer-comment"> 
                                <h5 class="comment-date">${data[i].contentTime}</h5>
                                <h3 class="customer-name">Jenney Kelley</h3>
                                <p class="customer-commented">${data[i].content}</p>
                              </div>
                            </div>
                          </div>
                    	</div>
					`;
				}
				$customer_reviews_middle_block.append(str);
			},
			error:function(error){
				console.log(error);
			}
		});
	}
	
	
	
	//发送评论(列表增加)
	function putComment(comment){
		console.log("评论："+comment);
		$.ajax({
			type:"post",
			url:"CommentServlet",
			cache:false,
			async:false, 
			data:{
				content:comment,
				uerId:2,
				phoneId:phoneid,
				ip:"127.0.0.1",
				equipment:"PC",
				op:"addComment"
			},
			dataType:"json",
			success:function(data){
				var $customer_reviews_middle_block=$(".customer-reviews_middle_block");//评论列表
				var str;
				str=`
                	<div class="customer-review">
                      <div class="row">
                        <div class="col-12 col-sm-3 col-lg-2">
                          <div class="customer-review_left">
                            <div class="customer-review_img text-center"><img class="img-fluid" src="assets/images/shop/reviewer_02.png" alt="customer image"></div>
                            <div class="customer-rate"><i class="icon_star"></i><i class="icon_star"></i><i class="icon_star"></i><i class="icon_star"></i><i class="icon_star-half"></i></div>
                          </div>
                        </div>
                        <div class="col-12 col-sm-9 col-lg-10">
                          <div class="customer-comment"> 
                            <h5 class="comment-date">${data.conentTime}</h5>
                            <h3 class="customer-name">${nameCookie}</h3>
                            <p class="customer-commented">${data.content}</p>
                          </div>
                        </div>
                      </div>
                	</div>
				`;
				//待续
				$customer_reviews_middle_block.append(str);
			},
			error:function(error){
				console.log(error);
			}
		});
	}
	
	//得到phone信息详情
	getPhoneDetail();
	getAllComment();
	
	//添加评论
	$("#commentBtn").bind("click",function(event){
		event.preventDefault();
		if(!idCookie || idCookie.length==0){
			alert("请先登录！");
			return false;
		}
		var comment=$("#review").val();//获取评论内容
		if(comment==null||comment.length==0){
			alert("请输入评论！再提交");
			return false;
		}
		console.log("继续执行");
		putComment(comment);
		$("#review").val("");//重置
		$("#commentBtn").text("评论成功");
		$("#commentBtn").css({
			color:'orange',
		});
		
		setTimeout(function(){
			$("#commentBtn").text("发表 评论");
			$("#commentBtn").css({
				color:'#fff',
			});
		},3000)
	});
	
	//添加购物车
	$(document).on("click",".addCart",function(){
		if(!idCookie || idCookie.length==0){
			alert("请先登录！");
			return false;
		}
		var num=$("#quantity").val();
		console.log(num);
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
});