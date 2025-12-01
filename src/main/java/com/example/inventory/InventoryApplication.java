package com.example.inventory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.inventory.model.Category;
import com.example.inventory.model.Product;
import com.example.inventory.model.SystemAccount;
import com.example.inventory.repository.CategoryRepository;
import com.example.inventory.repository.ProductRepository;
import com.example.inventory.repository.SystemAccountRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
public class InventoryApplication {
        public static void main(String[] args) {
                SpringApplication.run(InventoryApplication.class, args);
        }

        // Data mẫu
        @Bean
        CommandLineRunner initData(CategoryRepository catRepo, ProductRepository prodRepo,
                        SystemAccountRepository accRepo, PasswordEncoder passwordEncoder) {
                return args -> {
                        if (catRepo.count() == 0) {
                                catRepo.save(new Category(1, "Electronics", "Electronic devices and accessories"));
                                catRepo.save(new Category(2, "Wearables", "Smartwatches, fitness bands"));
                                catRepo.save(new Category(3, "Home Appliances", "Appliances for home use"));
                                catRepo.save(new Category(4, "Books", "Printed and digital books"));
                                catRepo.save(new Category(5, "Gaming", "Consoles, accessories and titles"));
                        }
                        if (prodRepo.count() == 0) {
                                Optional<Category> c1 = catRepo.findById(1);
                                Optional<Category> c2 = catRepo.findById(2);
                                Optional<Category> c3 = catRepo.findById(3);
                                Optional<Category> c4 = catRepo.findById(4);
                                Optional<Category> c5 = catRepo.findById(5);

                                prodRepo.save(new Product(1, c1.orElse(null), "Wireless Earbuds Pro", "Plastic",
                                                new BigDecimal("199.99"), 100, LocalDate.parse("2024-01-15")));
                                prodRepo.save(new Product(2, c1.orElse(null), "Smartphone X10", "Aluminum & Glass",
                                                new BigDecimal("999.00"), 50, LocalDate.parse("2024-02-10")));
                                prodRepo.save(new Product(3, c2.orElse(null), "Smartwatch Z3", "Metal",
                                                new BigDecimal("149.99"), 75,
                                                LocalDate.parse("2024-03-01")));
                                prodRepo.save(new Product(4, c3.orElse(null), "Air Purifier Pro", "Steel",
                                                new BigDecimal("259.00"), 40,
                                                LocalDate.parse("2024-01-05")));
                                prodRepo.save(new Product(5, c4.orElse(null), "Artificial Intelligence 101", "Paper",
                                                new BigDecimal("29.99"), 200, LocalDate.parse("2023-12-20")));
                                prodRepo.save(new Product(6, c5.orElse(null), "NextGen Console V", "Plastic",
                                                new BigDecimal("499.00"),
                                                30, LocalDate.parse("2024-02-20")));
                                prodRepo.save(new Product(7, c5.orElse(null), "Wireless Controller 2.0", "Plastic",
                                                new BigDecimal("69.99"), 150, LocalDate.parse("2024-01-25")));
                                prodRepo.save(new Product(8, c2.orElse(null), "Fitness Band Plus", "Rubber",
                                                new BigDecimal("89.99"),
                                                90, LocalDate.parse("2024-03-10")));
                                prodRepo.save(new Product(9, c3.orElse(null), "Robot Vacuum Cleaner", "Plastic",
                                                new BigDecimal("299.00"), 25, LocalDate.parse("2024-04-01")));
                                prodRepo.save(new Product(10, c4.orElse(null), "Data Structures Guidebook", "Paper",
                                                new BigDecimal("45.00"), 120, LocalDate.parse("2024-01-18")));
                        }

                        if (accRepo.count() == 0) {
                                // roles: 1-admin,2-manager,3-analyst,4-other
                                accRepo.save(new SystemAccount(1, "adminpro", "admin@system.com",
                                                passwordEncoder.encode("admin123"), 1, true));
                                accRepo.save(new SystemAccount(2, "manager1", "manager@system.com",
                                                passwordEncoder.encode("manager123"), 2,
                                                true));
                                accRepo.save(new SystemAccount(3, "analyst1", "analyst@system.com",
                                                passwordEncoder.encode("analyst123"), 3,
                                                true));
                                accRepo.save(new SystemAccount(4, "user1", "user1@system.com",
                                                passwordEncoder.encode("user123"), 4, true));
                                accRepo.save(new SystemAccount(5, "suspended", "blocked@system.com",
                                                passwordEncoder.encode("nopass"), 2,
                                                false));
                        }
                };
        }
}
