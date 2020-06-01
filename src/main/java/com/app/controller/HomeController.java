package com.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.model.SchedulerJobGroup;
import com.app.model.SchedulerJobInfo;
import com.app.model.SchedulerJobInfoCategory;
import com.app.model.forms.AddScheduler;
import com.app.services.JobServices;

@Controller
public class HomeController {
	
	@Autowired
	private JobServices jobServices;
	
	@GetMapping({"/home"})
    public String home(Model model,HttpServletRequest request) {
		Map<String, String> availableGroups = new HashMap<>();
        for(SchedulerJobGroup g:jobServices.selectAvailableGroups()) {
        	availableGroups.put(Long.toString(g.getGroup_id()), g.getGroup_name());
        }
		
        Long groupId = ServletRequestUtils.getLongParameter(request,"group_id",new Long(0));
        List<SchedulerJobInfo> all = new ArrayList<SchedulerJobInfo>();
		
        all = jobServices.selectAllByGroupId(groupId);
		
        List<SchedulerJobInfoCategory> jobCategories = new ArrayList<SchedulerJobInfoCategory>();
        jobCategories= jobServices.selectJobInfoCategory();
		
		

        
		model.addAttribute("availableGroups",jobServices.selectAvailableGroups());
		model.addAttribute("all",all);
		model.addAttribute("jobCategories",jobCategories);
		model.addAttribute("selectedGroup",groupId);
		AddScheduler addScheduler =  new AddScheduler();
		model.addAttribute("addschedulerform",addScheduler);
			
		return "homePage";
    }

	@RequestMapping(value={"/home/selectgroups"})
	@ResponseBody
	public List<SchedulerJobInfo> homeSelectAll(HttpServletRequest request){
		Long groupId = ServletRequestUtils.getLongParameter(request,"group_id",new Long(0));
		List<SchedulerJobInfo> all = new ArrayList<SchedulerJobInfo>();
		all = jobServices.selectAllByGroupId(groupId);
		return all;
	
	}



	@PostMapping({"/home/addscheduler"})
    public String addschedulerr(Model model,HttpServletRequest request,@ModelAttribute("addschedulerform") AddScheduler addScheduler) {
		
		System.out.println("patar nih");
		addScheduler.setJobName("random"+addScheduler.getJobName());

        List<SchedulerJobInfoCategory> jobCategories = new ArrayList<SchedulerJobInfoCategory>();
        jobCategories= jobServices.selectJobInfoCategory();
        List<SchedulerJobInfo> all = new ArrayList<SchedulerJobInfo>();
        Long groupId = addScheduler.getGroup_id();
        all = jobServices.selectAllByGroupId(addScheduler.getGroup_id());
		model.addAttribute("addschedulerform",addScheduler);
		model.addAttribute("availableGroups",jobServices.selectAvailableGroups());
		model.addAttribute("all",all);
		model.addAttribute("jobCategories",jobCategories);
		model.addAttribute("selectedGroup",groupId);
		
		return "forms/addscheduler";
	}
	@ModelAttribute("typeOfJobs")
	   public Map<String, String> typeOfJobs() {
	      Map<String, String> typeOfjob = new HashMap<String, String>();
	      typeOfjob.put("N", "Once");
	      typeOfjob.put("Y", "Cron");
	      return typeOfjob;
	   }



}
