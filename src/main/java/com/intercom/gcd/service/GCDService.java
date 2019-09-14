package com.intercom.gcd.service;

import com.intercom.gcd.dto.Customer;
import com.intercom.gcd.dto.GeoCustomer;
import com.intercom.gcd.repository.CustomerRepository;
import com.intercom.gcd.utils.FileUtils;
import com.intercom.gcd.utils.GreatCircle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles all the business logic - distances and storage
 */
@Service
public class GCDService {

    private static final Logger logger = LogManager.getLogger(GCDService.class);

    @Value("${base.latitude}")
    private double baseLat;

    @Value("${base.longitude}")
    private double baseLon;

    @Value("${base.name}")
    private String baseName;

    @Value("${base.max.distance}")
    private String baseMaxDistance;

    @Value("${output.file.name}")
    private String outputFileName;

    @Autowired
    CustomerRepository customerRepository;

    public void addCustomerInRange(GeoCustomer geoCustomer) {
        if(geoCustomer == null) return;
        try {
            if (isInRange(geoCustomer))
                customerRepository.addCustomer(geoCustomer);
        } catch (IllegalArgumentException e) {
            logger.error(geoCustomer.getName() + "  arrived with bad lat or lon. skipped");
        }
    }

    private boolean isInRange(GeoCustomer geoCustomer) {
        if (geoCustomer == null)
            throw new IllegalArgumentException("Empty customer object");
        return GreatCircle.getDistanceInKm(geoCustomer.getLatitude(), geoCustomer.getLongitude(), baseLat, baseLon) <= 100;
    }

    /**
     * publish customer list to output file and then clears the list to free memory
     * @throws IOException
     */
    public void publishCustomersInRange() throws IOException {
        logger.info("Publishing all customers withing range of " + baseMaxDistance + " km from " + baseName);
        List<Customer> customers = customerRepository.getCustomers();
        FileUtils fileUtils = new FileUtils();
        fileUtils.wrtieObjects(outputFileName,
                customers.stream()
                .sorted(Comparator.comparing(Customer::getUserId)) // sorted in ascending order
                .collect(Collectors.toList()));
        logger.info(customers.size() + " customers published to " + outputFileName);
        customerRepository.clear();
    }

}
