import com.example.random.service.RandomNumberService;
import com.example.random.service.business.StandardRandomNumberService;

module com.example.random {
	exports com.example.random.service;
	provides RandomNumberService with StandardRandomNumberService;
}