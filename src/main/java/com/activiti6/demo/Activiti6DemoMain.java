package com.activiti6.demo;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Activiti6DemoMain {
	private static Logger logger = LoggerFactory.getLogger(Activiti6DemoMain.class);
	public static void main(String[] args) {
		

		// 创建流程引擎
 		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//		ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
		System.out.println(processEngine);
		// 得到流程存储服务组件
		RepositoryService repositoryService = processEngine.getRepositoryService();
		// 得到运行时服务组件
		RuntimeService runtimeService = processEngine.getRuntimeService();
		// 获取流程任务组件
		TaskService taskService = processEngine.getTaskService();
		// 部署流程定义文件
		repositoryService.createDeployment().addClasspathResource("processes/first.bpmn").deploy();
		// 启动流程
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("process1");
		String user = (String)runtimeService.getVariable(pi.getId(), "user");
		System.out.println("user=" + user);
		// 查询任务
		List<Task> tasks = taskService.createTaskQuery().list();//.singleResult();
		for(Task task:tasks) {
			System.out.println("任务完成前，当前任务名称:"+task.getName());
			taskService.complete(task.getId());
			
		}
		
		
		


		
	}

}
