<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
  	<head>
  		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    	<title>轮播图添加</title>
    	<%@ include file="/WEB-INF/views/include/style.jsp"%>
  	</head>
  	<body>
  		<ul class="nav nav-tabs sys-font">
			<li><a href="/sys/ywymBannerWebController/list">轮播图列表</a></li>
			<li class="active"><a href="/sys/ywymBannerWebController/form">轮播图添加</a></li>
		</ul>
		<sys:message content="${message }"/>
  		<form:form id="inputForm" modelAttribute="entity" enctype="multipart/form-data" action="/sys/ywymBannerWebController/save" method="post" class="form-horizontal sys-form-loading">
			<form:hidden path="id"/>
			<div class="control-group">
				<label class="control-label">序号：</label>
				<div class="controls">
					<form:input path="rank" htmlEscape="false" class="input-xlarge required "/>
					<span style="text-align: center;font-size:22px; color:red;">*</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">对象类型：</label>
				<div class="controls">
					<form:select path="type" class="input-medium">
						<form:options items="${fnc:getDictList('banner_type')}" htmlEscape="false" />
					</form:select>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">轮播图名称：</label>
				<div class="controls">
					<form:input path="title" htmlEscape="false" class="input-xlarge required"/>
					<span style="text-align: center;font-size:22px; color:red;">*</span>
				</div>
			</div>
			<div class="control-group mall_goods">
				<label class="control-label">对象：</label>
				<div class="controls">
					<form:select path="objectId" class="input-xlarge required">
						<c:forEach items="${goodsList}" var="item">
							<form:option value="${item.id}" label="${item.name}"/>
						</c:forEach>
					</form:select>
				</div>
			</div>
			<div class="control-group mall_link">
				<label class="control-label">链接：</label>
				<div class="controls">
					<form:input path="link" htmlEscape="false" class="input-xlarge"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label ">轮播图素材(必填)：</label>
				<div class="controls">
					<c:if test="${!empty entity.img}">
						<img class="myImg" src="${entity.img }" width="80px" height="80px"/>
					</c:if>
					<c:if test="${empty entity.img}">
						<img class="myImg" src="${entity.img }" width="80px" height="80px" style="display: none"/>
					</c:if>
					<br>
					<input style="width: 174px;height: 30px" type="file" name="banner_img" class="input-xlarge upload_image"  accept="image/*"/>
                    <span  style="color:#F00">（建议尺寸比例：600px*300px）</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">是否上架：</label>
				<div class="controls">
					<form:select path="status" class="input-xlarge">
						<form:options items="${fnc:getDictList('banner_status')}"   htmlEscape="false"/>
					</form:select>
				</div>
			</div>

			<div class="form-actions">
				<input id="btnSubmit" class="btn btn-info" type="submit" value="保 存"/>&nbsp;
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</form:form>
  	</body>

	<script>
        $(document).ready(function() {
            var id = $("#id").val();
            if (notBlank(id)) { //编辑
                var ret = $("#type").val();
                if (ret == 1) { //链接
                    $(".mall_goods").hide();
                }else { //非链接
                    $(".mall_link").hide();
				}
            } else { //新增
                $(".mall_goods").hide();
            }


        });

        $("#type").change(function() {
            var ret = $("#type").val();
            if (ret == 1) {
                $(".mall_link").show();
                $(".mall_goods").hide();
            } else {
                $(".mall_link").hide();
                $(".mall_goods").show();
            }
        });

        $(".upload_image").live("change",function(event) {
            var obj = $(this);
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
            }
        });
	</script>
</html>
