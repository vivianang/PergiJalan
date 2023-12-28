//package binus.skripsi.RatingWeb.controller;
//
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import binus.skripsi.RatingWeb.model.Category;
//import binus.skripsi.RatingWeb.model.MyServiceRequest;
//import binus.skripsi.RatingWeb.service.CategoryService;
//import binus.skripsi.RatingWeb.service.MyServiceService;
//
//@Controller
//@RequestMapping("")
//public class MyServiceController {
//	
//	@Autowired
//	MyServiceService myServiceService;
//	
//	@PostMapping("/cancel-change")
//	public String viewCategory(@RequestBody MyServiceRequest myServiceRequest) throws InterruptedException {
//		String result = myServiceService.exec(myServiceRequest);
//		return result;
//	}
//	
//	
//}
