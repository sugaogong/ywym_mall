<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
  	<head>
  		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    	<title>礼品添加</title>
    	<%@ include file="/WEB-INF/views/include/style.jsp"%>
		<script type="text/javascript" charset="utf-8" src="${ctxStatic }/ueditor/ueditor.config.js"></script>
		<script type="text/javascript" charset="utf-8" src="${ctxStatic }/ueditor/ueditor.all.min.js"> </script>
		<script type="text/javascript" charset="utf-8" src="${ctxStatic }/ueditor/lang/zh-cn/zh-cn.js"></script>
		<script type="text/javascript">
            //实例化编辑器
            //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
            var ue = UE.getEditor('details',{initialFrameWidth:1000,initialFrameHeight:500});
		</script>
  	</head>
  	<body>
  		<ul class="nav nav-tabs sys-font">
			<li><a href="/sys/ywymGoodsWebController/list">礼品列表</a></li>
			<li class="active"><a href="/sys/ywymGoodsWebController/form">礼品添加</a></li>
		</ul>
		<sys:message content="${message }"/>
  		<form:form id="inputForm" modelAttribute="entity" enctype="multipart/form-data" action="/sys/ywymGoodsWebController/save" method="post" class="form-horizontal sys-form-loading">
			<form:hidden path="id"/>
			<div style="overflow: hidden;">
				<div class="right" style="float:left; width: 30%">
					<div class="control-group">
						<label class="control-label">礼品分类：</label>
						<div class="controls">
							<form:select path="catlogId" class="input-xlarge required">
								<c:forEach items="${catlogList}" var="item">
									<form:option value="${item.id}" label="${item.name}"/>
								</c:forEach>title
							</form:select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">礼品类型：</label>
						<div class="controls">
							<form:select path="type" class="input-xlarge required">
								<form:options items="${fnc:getDictList('goods_type')}"   htmlEscape="false"/>
							</form:select>
						</div>
					</div>
						<div class="control-group">
							<label class="control-label">礼品序号：</label>
							<div class="controls">
								<form:input path="rank" htmlEscape="false" class="input-xlarge required" maxlength="5" onkeyup="value=value.replace(/[^\d+]/g,'')"/>
								<span style="text-align: center;font-size:22px; color:red;">*</span>
							</div>
						</div>
					<div class="control-group">
						<label class="control-label">礼品名称：</label>
						<div class="controls">
							<form:input path="name" htmlEscape="false" class="input-xlarge required"/>
							<span style="text-align: center;font-size:22px; color:red;">*</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">规格：</label>
						<div class="controls">
                            <form:textarea class="textBox" placeholder="选填，不填则为“默认”；  如有多个规格请用'逗号'隔开" path="spec" rows="3"></form:textarea>
						</div>
					</div>
				</div>
				<div class="right" style="float:right; width: 48%">
					<div class="control-group">
						<label class="control-label">库存：</label>
						<div class="controls">
							<form:input path="stock" htmlEscape="false" class="input-xlarge required" maxlength="5" onkeyup="value=value.replace(/[^\d+]/g,'')"/>
							<span style="text-align: center;font-size:22px; color:red;">*</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">库存预警：</label>
						<div class="controls">
							<form:input path="warnStock" htmlEscape="false" class="input-xlarge required" maxlength="5" onkeyup="value=value.replace(/[^\d+]/g,'')"/>
							<span style="text-align: center;font-size:22px; color:red;">*</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">原价（积分）：</label>
						<div class="controls">
							<form:input path="oldScore" htmlEscape="false" class="input-xlarge required" onkeyup="checkMoney(this)"/>
							<span style="text-align: center;font-size:22px; color:red;">*</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">现价（积分）：</label>
						<div class="controls">
							<form:input path="nowScore" htmlEscape="false" class="input-xlarge required" onkeyup="checkMoney(this)"/>
							<span style="text-align: center;font-size:22px; color:red;">*</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">价格(元)：</label>
						<div class="controls">
							<form:input path="price" htmlEscape="false" class="input-xlarge required" onkeyup="checkMoney(this)"/>
							<span style="text-align: center;font-size:22px; color:red;">*</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">参考价格(元)：</label>
						<div class="controls">
							<form:input path="refPrice" htmlEscape="false" class="input-xlarge required" onkeyup="checkMoney(this)"/>
							<span style="text-align: center;font-size:22px; color:red;">*</span>
						</div>
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">礼品缩略图：</label>
				<div class="controls">
					<%--<c:if test="${!empty entity.icon }">
						<img class="myImg" src="${entity.icon }" width="80px" height="80px"/><br>
					</c:if>--%>
					<c:if test="${!empty entity.icon}">
					<div class="zzf-box">
						<img class="myImg" src="${entity.icon }" width="80px" height="80px" />
						<input style="width: 174px;height: 30px" type="file" name="img_icon" class="input-xlarge upload_image" accept="image/*"/>
						<span  style="color:#F00;width: 210px;height: 16px;float: left">（建议尺寸比例：50px*50px）</span>
					</div>
					</c:if>
					<c:if test="${empty entity.icon}">
					<div class="zzf-box">
						<img class="myImg" src="${entity.icon }" width="80px" height="80px" style="display: none"/>
						<input style="width: 174px;height: 30px" type="file" name="img_icon" class="input-xlarge upload_image" accept="image/*"/>
						<span  style="color:#F00;width: 210px;height: 16px;float: left">（建议尺寸比例：50px*50px）</span>
					</div>
					</c:if>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">礼品主图：</label>
				<div class="controls">
					<div class="upload_image_main">
						<c:forEach items="${imgList}" var="item">
						<div class="zzf-box">
							<i class="delImg" id="${item.id}"></i>
							<img class="myImg" src="${item.img}" width="80px" height="80px"/>
						</div>
						</c:forEach>
					</div>
					<input style="width: 174px;height: 30px" type="file" name="img_img" multiple="multiple" class="input-xlarge upload_images" accept="image/*"/>
					<span  style="color:#F00;width: 210px;height: 16px">（建议尺寸比例：500px*500px）</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">礼品详情：</label>
				<div class="controls">
					<form:textarea path="details"></form:textarea>
				</div>
			</div>
			<div class="form-actions">
				<input id="btnSubmit" class="btn btn-info" type="submit" value="保 存"/>&nbsp;
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</form:form>
  	</body>

	<script type="text/javascript">
        $(document).ready(function() {
            $(".delImg").click(function (e) {
                var id = $(this).attr('id');
                console.log(id)
                var parent = $(this).parent();
                $.ajax( {
                    url : "/sys/ywymGoodsWebController/delImg",
                    data: {
                        id: id
                    },
                    type : "post",
                    dataType : "json",
                    success : function(data) {
                        console.log(data)
                        parent.remove();
                    },error: function() {
                        console.log("error : delImg");
                    }
                });
            });
        });

        //校验金额
        function checkMoney(obj){
            //如果输入非数字，则替换为''
            obj.value = obj.value.replace(/[^\d+\.]/g, '');
            //必须保证第一个为数字而不是.
            obj.value = obj.value.replace(/^\./g,'');
            //保证只有出现一个.而没有多个.
            obj.value = obj.value.replace(/\.{2,}/g,'.');
            //保证.只出现一次，而不能出现两次以上
            obj.value = obj.value.replace('.','$#$').replace(/\./g,'').replace('$#$','.');
            //只能输入两位小数
            obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
        }

        //图片显示
        $(".upload_image").live("change",function(event) {
            var obj = $(this);
            var files = event.target.files, file;
            if (files && files.length > 0) {
				file = files[0];
				var URL = window.URL || window.webkitURL;
				var imgURL = URL.createObjectURL(file);
                obj.siblings(".myImg").attr('src',imgURL).show();
            }

           /* var obj = $(this);
            var files = event.target.files, file;
            if (files && files.length > 0) {
                file = files[0];
                console.log(file);
                var URL = window.URL || window.webkitURL;
                var imgURL = URL.createObjectURL(file);
                //if(obj.siblings("img").length > 0){
                obj.siblings(".myImg").css("display","block");
                obj.siblings(".myImg").attr("src",imgURL);
                //}else{
                //	obj.parents(".upload_image_main").append('< img src="'+imgURL+'" />');
                //}
            }*/
        });
        $(".upload_images").live("change",function(event) {
            var obj = $(this);
            var files = event.target.files, file;
            var txt = new Array();
            if (files && files.length > 0) {
                console.log(files);
                for (var i  = 0;i < files.length;i++){
                    file = files[i];
                    var URL = window.URL || window.webkitURL;
                    var imgURL = URL.createObjectURL(file);
                    txt += '<div class="zzf-box">'+
								'<i class="delImg"  onclick="delImg(this)"></i>'+
								'<img class="myImg" src="'+ imgURL +'" width="80px" height="80px"/>'+
							'</div>';
                }
                obj.siblings(".upload_image_main").append(txt);
            }
        });
        function delImg(obj) {
			$(obj).parent(".zzf-box").remove();
        }
	</script>
</html>
