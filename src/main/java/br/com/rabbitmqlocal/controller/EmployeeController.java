package br.com.rabbitmqlocal.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rabbitmqlocal.model.Employee;

@RestController
@RequestMapping(value = "/v1/rabbitmq/producermodule/")
public class EmployeeController {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${local.rabbitmq.exchange}")
	String exchange;

	@Value("${local.rabbitmq.routingkey}")
	private String routingkey;

	
	@GetMapping(value = "/producer")
	public String producer(	@RequestParam("empName") String empName,
							@RequestParam("empId") String empId,
							@RequestParam("salary") int salary) {
		
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName(empName);
		emp.setSalary(salary);

		amqpTemplate.convertAndSend(exchange, routingkey, emp);
		
		System.out.println("Message "+emp.toString()+" sent to the RabbitMQ Successfully");
		
		return "Message "+emp.toString()+" sent to the RabbitMQ Successfully";
	}
}
