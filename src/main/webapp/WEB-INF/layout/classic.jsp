<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    
<title><tiles:getAsString name="title" /></title>

	<!--Responsiveness-->
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    
	<!--FontAwesome-->
    <!--[if IE 7]>
    <link rel="stylesheet" href="font-awesome/css/font-awesome-ie7.min.css">
    <![endif]-->


  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>   
  <script src="http://malsup.github.io/jquery.form.js"></script>   
  
  
<script>


$(document).ready(function() {
	
	
	var options = {
			beforeSubmit : function(arr, $form, options){
				
				
				
			},
			success : function(res, status, xhr, form){
				var content = $(res).html();
				form.html(content);
				
				
				
			},
			error : function(xhr, textStatus, errorThrown){
			
			}
			
			
	};
	
	$('#add').ajaxForm(options); 
	
	
	
	$("#availablegroups").on("change",function(e){
		if($(this).val() == 0){
        	$("#addschedule").prop("disabled",true);
		}
		if($(this).val()>0){
		 	$("#availablegroupsjob").val($(this).val());
			
			
		}

		$.ajax({
            type: 'GET',
            url: "/home/selectgroups?group_id="+$(this).val(),
            
            success:function(data){
            	$('#result-table tbody > tr'). remove();
            	$("#addschedule").prop("disabled",false);
            	
            	var obj = $.parseJSON(JSON.stringify(data));
            	
            	$.each(obj, function (index, value) {
            		 

                     var button = ' <button type="button"  class="btn btn-info modify" data-toggle="modal" data-target="#myModal">Modify</button>                      <button type="button"  class="btn btn-info delete" data-toggle="modal" data-target="#myModal">Delete</button>';
					 var tr = "<tr><td>"+value.id+"</td><td>"+value.job_name+"</td><td>"+value.cron_expression+"</td><td>"+value.cron_job+"</td><td>"+value.job_label+"</td><td>"+button+"</td></tr>";
            		 $("#result-table tbody").append(tr);
					
            	 });
            	
            	
            	
            }
        });
	
	});
	
	
	
	
	
	
});

</script>
</head>


<body>

<div class="container">
  <h2>Application</h2>
  <!-- Trigger the modal with a button -->
  <select class="form-control" id="availablegroups">
    <option value="0" selected>Select Available Groups</option>
    <c:forEach items="${availableGroups}" var="gr">
        <option value="${gr.group_id}" ${selectedGroup == gr.group_id ? 'selected':''}>${gr.group_name}</option>
    </c:forEach>
  
  </select>
  <br/>
 
  <button type="button" id="addschedule" class="btn btn-info" data-toggle="modal" data-target="#addschedulermodal" ${selectedGroup == 0 ? 'disabled':''}>Add Scheduler</button>
<br/>
<br/>
<br/>



<div class="row">
   
   		   <div class="col-xs-12 col-sm-12">
	                        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Schedulers</h3>
            </div>
            <div class="panel-body">
               	<table id="result-table" class="table" style="overflow-x:auto;">
										<thead>
										  <tr>
											<th>Job Id</th>
                                            <th>Job Name</th>
                                            <th>Expression</th>
											<th>Type Cron Job</th>
											<th>Job</th>
											<th>Action</th>
											
										  </tr>
										</thead>
										<tbody>
										 
    <c:forEach items="${all}" var="j">
   		<tr>
        <td>
            <c:out value="${j.id}" />
        </td>

        <td>
            <c:out value="${j.job_name}" />
        </td>
        
        <td>
            <c:out value="${j.cron_expression}" />
        </td>
        <td>
            <c:out value="${j.cron_job}" />
        </td>
        <td>
            <c:out value="${j.job_label}" />
        </td>
        <td>
          
          <button type="button"  class="btn btn-info modify" data-toggle="modal" data-target="#myModal">Modify</button>
          
          <button type="button"  class="btn btn-info delete" data-toggle="modal" data-target="#myModal">Delete</button>
          
          </td>
        </tr>
   
    </c:forEach>


										</tbody>
									</table>
					
					
					
					    
         </div> 
         </div>
	      </div>
	        
   </div>	
 
 
 
 

  <!-- Modal -->
  <div class="modal fade" id="addschedulermodal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Scheduler</h4>
        </div>
        <div class="modal-body">
        <div class="row" >
<div class="col-xs-12 col-sm-12">

          <tiles:insertAttribute name="addscheduler" />
          
    </div>
       	</div>
        
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>




 
</body>
</html>