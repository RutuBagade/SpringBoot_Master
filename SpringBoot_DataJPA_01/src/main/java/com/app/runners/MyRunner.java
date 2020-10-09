package com.app.runners;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.app.model.Product;
import com.app.repository.ProductRepository;
@Component
public class MyRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);
	
	@Autowired
	private ProductRepository repo;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		logger.info("start application");
		/************** SAVE OR UPDATE ************************/
		repo.save(new Product("PEN", 32.36,"V1"));
		repo.save(new Product("TABLE", 305.86,"V2"));
		repo.save(new Product("REMOTE", 500.36,"V3"));
		repo.save(new Product("PENCIL", 8.12,"V2"));
		repo.save(new Product("NOTEBOOK", 18.12,"V1"));
		repo.save(new Product("PENCIL", 8.12,"V3"));
		repo.save(new Product("WRITTINGPAD", 18.12,"V2"));
		logger.info("***-save /update -- finish*****");
		
		/************** COUNT and EXIST ************************/
		logger.info("EXIST? :"+repo.existsById(2));
		logger.info("COUNT :"+repo.count());

		logger.info("------------------------");
		
		 logger.info("All products unsorted:");
		 List<Product> products=repo.findAll();
		 logger.info("{}", products);
		 products.forEach(System.out::println);
		 
		 logger.info("------------------------");
		 
		 logger.info("All products sorted:");
		 List<Product> prod= repo.findAll(Sort.by(Sort.Direction.DESC, "prodCode"));
		 logger.info("{}", prod);
		 prod.forEach(System.out::println);
		 
		 logger.info("------------------------");
		 /************** BASIC FIND METHODS ************************/
		 Optional<Product> p=repo.findById(3);
			if(p.isPresent()) {
				System.out.println(p.get());
			}else {
				logger.info("NO DATA FOUND");
			}
			 logger.info("------------------------");
			List<Product> p11=repo.findByProdCode("PENCIL");
			p11.forEach(prod1 -> System.out.println(prod1));
			System.out.println("Sorted in Ascending Order :");
			repo.findAll(Sort.by("prodCode")).forEach(System.out::println);;
		
			/************** Delete METHODS ************************/
			System.out.println("Delete based on id :");
			//delete row based on ID
			repo.deleteById(3);

			//is record exist
			System.out.println(repo.existsById(3)?"Yes data is available":"Seems No Data Found");
			System.out.println("delete All records :");
			repo.deleteAll();
			
			logger.info("COUNT :"+repo.count());
			
	}

}
