<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %> <%-- header.jsp에 작성한 내용을 여기에 넣겠다! 즉 url 경로가 아님! --%>


<div class="card m-2">
   <div class="card-header">
      ModelAttribute를 이용한 데이터 전달
   </div>
   <div class="card-body">
   		<p>cloth kind : ${kind}</p>
   		<p>cloth sex : ${sex}</p>
   		
   		<hr/>
   		
   		<p>cloth kind : ${ch07Cloth.kind}</p>
   		<p>cloth sex : ${ch07Cloth.sex}</p>
   		
   		<hr/>
   		
   		<p>cloth kind : ${cloth.kind}</p>
   		<p>cloth sex : ${cloth.sex}</p>
   </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>