<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header class="marquee_content_header">
	<li>科室</li>
	<li>医生姓名</li>
	<li>原因</li>
	<li>时间</li>
</header>
<div class="txtMarquee-top">
	<div class="bd">
		<ul class="infoList">
			<c:choose>
				<c:when test="${datastore ne null}">
					<c:forEach var="d" items="${datastore}">
						<li>
						<div>${d.department}</div>
						<div>${d.userName}</div>
						<div>${d.reason}未及时回收</div>
						<div>${d.creatorTime}</div>
						</li>
					</c:forEach>	
				</c:when>
				<c:otherwise>
						<li style="margin-top:3%;font-size:2vw;">
							暂无信息！
						</li>
				<script type="text/javascript">
					$(".marquee_content_header").css("display","none");
				</script>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</div>
<script type="text/javascript">
$(function(){
	$('.infoList>li:odd').css('background-color', '#E1F3FF');
})

	jQuery(".txtMarquee-top").slide({
		mainCell : ".bd ul",
		autoPlay : true,
		effect : "topMarquee",
		vis : 14,
		scroll:8,
		interTime : 30,
		mouseOverStop : false
	});
$(".infoList>li").after("<div style='clear:both;'></div>");
</script>
