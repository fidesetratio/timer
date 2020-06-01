<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib  prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
 <form:form action="/home/addscheduler" id="add"  method="post" modelAttribute="addschedulerform">
 
<div id="formcontent">
 
  <div class="form-group">
    <label for="jobname">Job Name:</label>
  	<form:input path="jobName" class="form-control"/>
  </div>
  <div class="form-group">
    <label for="pwd">Job Group:</label>
 <form:select name="group_id" path="group_id" class="form-control" id="availablegroupsjob">
    <option value="0" selected>Select Available Groups1</form>
    <c:forEach items="${availableGroups}" var="gr">
        <option value="${gr.group_id}" ${selectedGroup == gr.group_id ? 'selected':''}>${gr.group_name}</option>
    </c:forEach>
  </form:select>
 
  </div>
  <div class="form-group">
    <label for="typeOfjob">Type of cron Job:</label>
    <form:select path="typeOfJob" class="form-control"  id="typeofjob">
    
    	
    	 <c:forEach items="${typeOfJobs}" var="tj">
    	 
    	 
     <%--    	<option value="${tj.key}" ${addschedulerform.typeOfJob == tj.key? 'selected':''}>${tj.value}</option>
     --%>
     <option value="${tj.key}" <c:if test="${fn:contains(addschedulerform.typeOfJob,tj.key)}"> selected=selected </c:if>>${tj.value}</option>
     
     
     </c:forEach>
    
    
 	</form:select>
  </div>
 
  <div class="form-group">
    <label for="expression">Expression:</label>
  	<form:input path="expression" id="expression" class="form-control"/>
  
  </div>
 
  <div class="form-group">
    <label for="availablejobcategory">Job:</label>
 <select class="form-control" name="jobCategoryId" id="availablejobcategory">
    <option value="0" selected>Select Available Job</option>
    <c:forEach items="${jobCategories}" var="jc">
        <option value="${jc.cat_id}" <c:if test="${jc.cat_id eq addschedulerform.jobCategoryId}">selected=selected</c:if>>${jc.job_label}</option>
    </c:forEach>
  
  </select>


  </div>
 
 
  <div class="form-group">
    <label for="url">URL:</label>
    <form:input path="url" id="url" class="form-control"/>
  </div>
  
  
  <div class="form-group">
    <label for="param0">Param0:</label>
    <form:input path="param0" id="param0" class="form-control"/>
  </div>
  
  
  <div class="form-group">
    <label for="param1">Param1:</label>
    <form:input path="param1" id="param1" class="form-control"/>
 
  </div>
 
  <input type="submit" class="btn btn-default" value="submit"/>

   </form:form>
    
</div>
