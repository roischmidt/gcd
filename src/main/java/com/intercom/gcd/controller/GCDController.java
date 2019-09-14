package com.intercom.gcd.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercom.gcd.dto.GeoCustomer;
import com.intercom.gcd.service.GCDService;
import com.intercom.gcd.utils.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * main controller - reads customers from given input file and publishes the validated ones
 */
@Component
public class GCDController implements CommandLineRunner {

    private static final Logger logger = LogManager.getLogger(GCDController.class);

    @Autowired
    private GCDService gcdService;

    public void processCustomers(String path) {
        if (StringUtils.isEmpty(path))
            throw new IllegalArgumentException("Path is empty");
        try {
            FileUtils fileUtils = new FileUtils();
            ObjectMapper objectMapper = new ObjectMapper();
            for (String line : fileUtils.readLines(path)) {
                try {
                    GeoCustomer geoCustomer = objectMapper.readValue(line, GeoCustomer.class);
                    gcdService.addCustomerInRange(geoCustomer);
                } catch (JsonParseException e) {
                    logger.error(line + " has corrupted json format. Skipped");
                }
            }
            gcdService.publishCustomersInRange();
        } catch (IOException e) {
            logger.error("Couldn't read from in put file",e);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length == 0) {
            logger.error("Input file not provided. Shutting down");
            return;
        }
        processCustomers(args[0]);
    }
}
