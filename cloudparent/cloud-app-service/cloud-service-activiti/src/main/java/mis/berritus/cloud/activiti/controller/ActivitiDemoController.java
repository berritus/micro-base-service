package mis.berritus.cloud.activiti.controller;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: Create in 2020/4/12
 */
@RestController
public class ActivitiDemoController {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProcessEngine processEngine;

    // http://localhost:8111/
    @RequestMapping("/")
    public String index(){
        return "hello activiti";
    }

    // http://localhost:8111/askLeave
    @RequestMapping("/askLeave")
    public String deployProcess(){
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().name("请假流程");
        Deployment deployment = null;
        try {
            // 根据bpmn文件部署流程
            deployment = deploymentBuilder.addClasspathResource("process/demoTest2.bpmn").deploy();

            // 获取流程定义
            ProcessDefinition processDefinition = repositoryService
                    .createProcessDefinitionQuery()
                    .deploymentId(deployment.getId()).singleResult();

            // 启动流程定义，返回流程实例
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("devManName", "qingh");
            ProcessInstance pi = runtimeService
                    .startProcessInstanceById(processDefinition.getId(), paramMap);
            String processId = pi.getId();
            System.out.println("流程创建成功，当前流程实例ID：" + processId);

//            Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
//            System.out.println("第一次执行前，任务名称：" + task.getName());
//            taskService.complete(task.getId());
//
//            task = taskService.createTaskQuery().processInstanceId(processId)
//                    .singleResult();
//            System.out.println("第二次执行前，任务名称：" + task.getName());
//            taskService.complete(task.getId());
//
//            task = taskService.createTaskQuery().processInstanceId(processId)
//                    .singleResult();
//            System.out.println("task为null，任务执行完毕：" + task);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "hello activiti";
    }



    // http://localhost:8111/doMyTask
    @RequestMapping("/doMyTask")
    public String doMyTask(){
        List<Task> list = processEngine.getTaskService()
                .createTaskQuery()
                .taskAssignee("qingh")//指定个人任务查询
                .list();
        if (list != null && list.size()>0) {
            for(Task task : list){
                System.out.println("task id="+task.getId());
                System.out.println("name="+task.getName());
                System.out.println("assinee="+task.getAssignee());
                System.out.println("executionId="+task.getExecutionId());
                System.out.println("=====================================");
            }
        }
        return "hello activiti";
    }

    // http://localhost:8111/findMyTaskList
    @RequestMapping("/findMyTaskList")
    public String findMyTaskList(){
        List<Task> list = processEngine.getTaskService()
                .createTaskQuery()
                .taskAssignee("qingh")//指定个人任务查询
                .list();
        if (list != null && list.size()>0) {
            for(Task task : list){
                System.out.println("task id="+task.getId());
                System.out.println("name="+task.getName());
                System.out.println("assinee="+task.getAssignee());
                System.out.println("executionId="+task.getExecutionId());
                System.out.println("=====================================");
            }
        }
        return "hello activiti";
    }
}
