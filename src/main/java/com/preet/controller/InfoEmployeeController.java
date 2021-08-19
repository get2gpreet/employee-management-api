package com.preet.controller;

import com.preet.dto.EmployeeTO;
import com.preet.service.InfoEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/employee-info")
public class InfoEmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(InfoEmployeeController.class);

    @Autowired
    private InfoEmployeeService infoEmployeeService;

    @GetMapping
    public ResponseEntity<?> getEmployeeInfo() {
        logger.info("getEmployeeInfo");
        List<EmployeeTO> employeeList ;
        try {
            employeeList = infoEmployeeService.getAllEmployee();
            if (CollectionUtils.isEmpty(employeeList)) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(employeeList);
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getEmployeeInfoById(@PathVariable String id) {
        logger.info("getEmployeeInfoById : {}",id);
        EmployeeTO employee ;
        try {
            employee = infoEmployeeService.getEmployeeById(id);
            if (employee != null) {
                return ResponseEntity.ok(employee);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
