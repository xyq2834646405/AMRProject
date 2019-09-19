<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <jsp:include page="/pages/plugins/include_javascript_head.jsp" />
    <script type="text/javascript" src="js/pages/purchase/purchase_show.js"></script>
    <script type="text/javascript">
        var pid=${purchase.pid};
    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/include_menu_item.jsp" />
		<div class="content-wrapper">
			<!-- 此处编写需要显示的页面 -->
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title"><strong>办公用品购买详单</strong></h3>
						</div>
						<div class="row">
							<div class="col-xs-4 col-xs-push-1"><label><strong>申请名称：</strong></label></div>
							<div class="col-xs-7">${purchase.title}</div>
						</div>
						<div class="row">
							<div class="col-xs-4 col-xs-push-1"><label><strong>申请人：</strong></label></div>
							<div class="col-xs-7">${purchase.emp.name}</div>
						</div>
						<div class="row">
							<div class="col-xs-4 col-xs-push-1"><label><strong>购买总额：</strong></label></div>
							<div class="col-xs-7"><span class="text-danger h3">￥${purchase.total}</span></div>
						</div>
						<div class="row">
							<div class="col-xs-4 col-xs-push-1"><label><strong>购入商品：</strong></label></div>
							<div class="col-xs-7">
								<div class="box-body table-responsive no-padding">
									<table class="table table-hover">
										<tr>
											<th>名称</th>
											<th>单价</th>
											<th>购买数量</th>
										</tr>
                                        <c:forEach items="${purchase.allDetails}" var="details">
                                            <tr>
                                                <td><img src="upload/res/${details.photo}" class="img" style="width:30px;"> ${details.title}</td>
                                                <td>${details.price}</td>
                                                <td>${details.amount}</td>
                                            </tr>
                                        </c:forEach>
									</table>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-4 col-xs-push-1"><label><strong>购买说明：</strong></label></div>
							<div class="col-xs-7">${purchase.note}</div>
						</div> 
						<div class="row">&nbsp;</div>
						<div class="row">
                            <c:if test="${purchase.status==0}">
                                <c:if test="${emp.dept.did==5 and emp.level.lid==4}">
                                    <div class="col-xs-4 col-xs-push-3" id="auditDiv">
                                        <button type="button" id="passBut" class="btn btn-primary btn-lg">审核通过</button>
                                        <button type="button" id="refBut" class="btn btn-danger btn-lg">审核拒绝</button>
                                    </div>
                                </c:if>
                            </c:if>
						</div>
						<!-- /.box-body -->
                        <jsp:include page="/pages/plugins/include_alert.jsp"/>
					</div>
					<!-- /.box -->
				</div>
			</div>
		</div>
		<!-- 导入公司尾部认证信息 -->
		<jsp:include page="/pages/plugins/include_title_foot.jsp" />
		<!-- 导入右边工具设置栏 -->
		<jsp:include page="/pages/plugins/include_menu_sidebar.jsp" />
		<div class="control-sidebar-bg"></div>
	</div>
	<jsp:include page="/pages/plugins/include_javascript_foot.jsp" />
</body>
</html>
