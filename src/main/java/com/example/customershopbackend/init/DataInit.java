package com.example.customershopbackend.init;

import com.example.customershopbackend.entities.category.Category;
import com.example.customershopbackend.entities.category.feture.CategoryRepository;
import com.example.customershopbackend.entities.customer.Customer;
import com.example.customershopbackend.entities.customer.feture.CustomerRepository;
import com.example.customershopbackend.entities.customer_type.CustomerType;
import com.example.customershopbackend.entities.customer_type.feture.CustomerTypeRepository;
import com.example.customershopbackend.entities.imports.Import;
import com.example.customershopbackend.entities.imports.feture.ImportRepository;
import com.example.customershopbackend.entities.invoice.Invoice;
import com.example.customershopbackend.entities.invoice.feture.InvoiceRepository;
import com.example.customershopbackend.entities.payment.Payment;
import com.example.customershopbackend.entities.payment.feture.PaymentRepository;
import com.example.customershopbackend.entities.product.Product;
import com.example.customershopbackend.entities.product.feture.ProductRepository;
import com.example.customershopbackend.entities.sale.Sale;
import com.example.customershopbackend.entities.sale.feture.SaleRepository;
import com.example.customershopbackend.entities.staff.Staff;
import com.example.customershopbackend.entities.staff.feture.StaffRepository;
import com.example.customershopbackend.entities.supplier.Supplier;
import com.example.customershopbackend.entities.supplier.feture.SupplierRepository;
import com.example.customershopbackend.util.RandomUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final CustomerRepository customerRepository;
    private final CustomerTypeRepository customerTypeRepository;
    private final StaffRepository staffRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;
    private final ImportRepository importRepository;
    private final SupplierRepository supplierRepository;
    private final SaleRepository saleRepository;
    private final InvoiceRepository invoiceRepository;

    @PostConstruct
    void init(){

        if (productRepository.count() == 0){

            Category laptop = new Category();
            laptop.setUuid(RandomUtil.random6Digits());
            laptop.setName("Laptop");
            laptop.setIsDeleted(false);

            Category desktop = new Category();
            desktop.setUuid(RandomUtil.random6Digits());
            desktop.setName("Desktop");
            desktop.setIsDeleted(false);

            Category keyboard = new Category();
            keyboard.setUuid(RandomUtil.random6Digits());
            keyboard.setName("Keyboard");
            keyboard.setIsDeleted(false);

            Category mouse = new Category();
            mouse.setUuid(RandomUtil.random6Digits());
            mouse.setName("Mouse");
            mouse.setIsDeleted(false);

            categoryRepository.saveAll(List.of(laptop, desktop, keyboard, mouse));

            Product product1 = new Product();
            product1.setUuid(RandomUtil.random6Digits());
            product1.setImage("image.png");
            product1.setName("ASUS ROG");
            product1.setDescription("CUP Core i7 12900K, RAM 8GB, SSD M.2 512GB, GPU NVIDIA GTR 3090.");
            product1.setStockQuantity(20);
            product1.setUnitPrice(BigDecimal.valueOf(1250));
            product1.setSaleUnitPrice(BigDecimal.valueOf(2499));
            product1.setDiscount(BigDecimal.valueOf(0));
            product1.setCategory(laptop);
            product1.setIsDeleted(false);

            Product product2 = new Product();
            product2.setUuid(RandomUtil.random6Digits());
            product2.setImage("image.png");
            product2.setName("ACER ");
            product2.setDescription("CUP Core i5 1135GM, RAM 4GB, SSD M.2 215GB");
            product2.setStockQuantity(10);
            product2.setUnitPrice(BigDecimal.valueOf(450));
            product2.setSaleUnitPrice(BigDecimal.valueOf(600));
            product2.setDiscount(BigDecimal.valueOf(0));
            product2.setCategory(laptop);
            product2.setIsDeleted(false);

            Product product3 = new Product();
            product3.setUuid(RandomUtil.random6Digits());
            product3.setImage("image.png");
            product3.setName("MAX BOOK");
            product3.setDescription("CUP M2, RAM 8GB, SSD M.2 512GB.");
            product3.setStockQuantity(20);
            product3.setUnitPrice(BigDecimal.valueOf(890));
            product3.setSaleUnitPrice(BigDecimal.valueOf(1459));
            product3.setDiscount(BigDecimal.valueOf(0));
            product3.setCategory(laptop);
            product3.setIsDeleted(false);

            productRepository.saveAll(List.of(product1, product2, product3));
        }

        if (customerRepository.count() == 0){

            CustomerType normal = new CustomerType();
            normal.setUuid(RandomUtil.random6Digits());
            normal.setType("NORMAL");

            CustomerType bronze = new CustomerType();
            bronze.setUuid(RandomUtil.random6Digits());
            bronze.setType("BRONZE");

            CustomerType silver = new CustomerType();
            silver.setUuid(RandomUtil.random6Digits());
            silver.setType("SILVER");

            CustomerType gold = new CustomerType();
            gold.setUuid(RandomUtil.random6Digits());
            gold.setType("GOLD");

            customerTypeRepository.saveAll(List.of(normal, bronze, silver, gold));

            Customer customer1 = new Customer();
            customer1.setUuid(RandomUtil.random6Digits());
            customer1.setImage("image.png");
            customer1.setName("An Srey Nich");
            customer1.setPhone("1099001122");
            customer1.setEmail("nich@gamil.com");
            customer1.setIsDeleted(false);
            customer1.setAddress("Phnom Penh");
            customer1.setCustomerType(normal);

            Customer customer2 = new Customer();
            customer2.setUuid(RandomUtil.random6Digits());
            customer2.setImage("image.png");
            customer2.setName("Na Rin");
            customer2.setPhone("1099001123");
            customer2.setEmail("rin@gamil.com");
            customer2.setIsDeleted(false);
            customer2.setAddress("Phnom Penh");
            customer2.setCustomerType(bronze);

            Customer customer3 = new Customer();
            customer3.setUuid(RandomUtil.random6Digits());
            customer3.setImage("image.png");
            customer3.setName("Han");
            customer3.setPhone("1099001124");
            customer3.setEmail("han@gamil.com");
            customer3.setIsDeleted(false);
            customer3.setAddress("Phnom Penh");
            customer3.setCustomerType(silver);

            Customer customer4 = new Customer();
            customer4.setUuid(RandomUtil.random6Digits());
            customer4.setImage("image.png");
            customer4.setName("Ma Ri Net");
            customer4.setPhone("1099001125");
            customer4.setEmail("net@gamil.com");
            customer4.setIsDeleted(false);
            customer4.setAddress("Phnom Penh");
            customer4.setCustomerType(gold);

            customerRepository.saveAll(List.of(customer1, customer2, customer3, customer4));

        }

        if (paymentRepository.count() == 0){
            Staff staff1 = new Staff();
            staff1.setUuid(RandomUtil.random6Digits());
            staff1.setImage("image.png");
            staff1.setName("Heng VeaSna");
            staff1.setGender("Male");
            staff1.setPhone("000-000-001");
            staff1.setBirthDate(LocalDate.parse("2002-07-12"));
            staff1.setAddress("Phnom Penh");
            staff1.setEmail("sna@gmail.com");
            staff1.setHiredDate(LocalDate.now());
            staff1.setSalary(BigDecimal.valueOf(500));
            staff1.setPosition("Admin");
            staff1.setIsDeleted(false);

            Staff staff2 = new Staff();
            staff2.setUuid(RandomUtil.random6Digits());
            staff2.setImage("image.png");
            staff2.setName("Him ChanNaRith");
            staff2.setGender("Male");
            staff2.setPhone("000-000-002");
            staff2.setBirthDate(LocalDate.parse("2003-01-31"));
            staff2.setAddress("Phnom Penh");
            staff2.setEmail("narith@gmail.com");
            staff2.setHiredDate(LocalDate.now());
            staff2.setSalary(BigDecimal.valueOf(360));
            staff2.setPosition("Cashier");
            staff2.setIsDeleted(false);

            Staff staff3 = new Staff();
            staff3.setUuid(RandomUtil.random6Digits());
            staff3.setImage("image.png");
            staff3.setName("Meng Leang");
            staff3.setGender("Male");
            staff3.setPhone("000-000-003");
            staff3.setBirthDate(LocalDate.parse("2001-07-12"));
            staff3.setAddress("Phnom Penh");
            staff3.setEmail("meng@gmail.com");
            staff3.setHiredDate(LocalDate.now());
            staff3.setSalary(BigDecimal.valueOf(1500));
            staff3.setPosition("Manager");
            staff3.setIsDeleted(false);

            staffRepository.saveAll(List.of(staff1, staff2, staff3));

            Customer customer1 = customerRepository.findByPhone("1099001122").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!")
            );

            Customer customer2 = customerRepository.findByPhone("1099001123").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!")
            );

            Customer customer3 = customerRepository.findByPhone("1099001124").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!")
            );

            Customer customer4 = customerRepository.findByPhone("1099001125").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!")
            );

            Payment payment1 = new Payment();
            payment1.setUuid(RandomUtil.random6Digits());
            payment1.setPayDate(LocalDateTime.now());
            payment1.setPaidAmount(BigDecimal.valueOf(600));
            payment1.setCustomer(customer1);
            payment1.setStaff(staff2);
            payment1.setIsDeleted(false);

            Payment payment2 = new Payment();
            payment2.setUuid(RandomUtil.random6Digits());
            payment2.setPayDate(LocalDateTime.now());
            payment2.setPaidAmount(BigDecimal.valueOf(600));
            payment2.setCustomer(customer2);
            payment2.setStaff(staff2);
            payment2.setIsDeleted(false);

            Payment payment3 = new Payment();
            payment3.setUuid(RandomUtil.random6Digits());
            payment3.setPayDate(LocalDateTime.now());
            payment3.setPaidAmount(BigDecimal.valueOf(600));
            payment3.setCustomer(customer3);
            payment3.setStaff(staff3);
            payment3.setIsDeleted(false);

            Payment payment4 = new Payment();
            payment4.setUuid(RandomUtil.random6Digits());
            payment4.setPayDate(LocalDateTime.now());
            payment4.setPaidAmount(BigDecimal.valueOf(600));
            payment4.setCustomer(customer4);
            payment4.setStaff(staff1);
            payment4.setIsDeleted(false);

            paymentRepository.saveAll(List.of(payment1, payment2, payment3, payment4));

        }

        if (importRepository.count() == 0){

            Staff staff1 = staffRepository.findByPhone("000-000-001").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found!")
            );

            Staff staff2 = staffRepository.findByPhone("000-000-002").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found!")
            );

            Staff staff3 = staffRepository.findByPhone("000-000-003").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found!")
            );

            Supplier supplier1 = new Supplier();
            supplier1.setUuid(RandomUtil.random6Digits());
            supplier1.setName("Amazon");
            supplier1.setPhone("111-111-111");
            supplier1.setAddress("Cambodia");
            supplier1.setIsDeleted(false);

            Supplier supplier2 = new Supplier();
            supplier2.setUuid(RandomUtil.random6Digits());
            supplier2.setName("Food Panda");
            supplier2.setPhone("111-111-112");
            supplier2.setAddress("Cambodia");
            supplier2.setIsDeleted(false);

            Supplier supplier3 = new Supplier();
            supplier3.setUuid(RandomUtil.random6Digits());
            supplier3.setName("Wow Now");
            supplier3.setPhone("111-111-113");
            supplier3.setAddress("Cambodia");
            supplier3.setIsDeleted(false);

            supplierRepository.saveAll(List.of(supplier1, supplier2, supplier3));

            Import import1 = new Import();
            import1.setUuid(RandomUtil.random6Digits());
            import1.setImportDate(LocalDateTime.now());
            import1.setSupplier(supplier1);
            import1.setStaff(staff3);
            import1.setTotalAmount(BigDecimal.valueOf(300));
            import1.setIsDeleted(false);

            Import import2 = new Import();
            import2.setUuid(RandomUtil.random6Digits());
            import2.setImportDate(LocalDateTime.now());
            import2.setSupplier(supplier1);
            import2.setStaff(staff3);
            import2.setTotalAmount(BigDecimal.valueOf(1300));
            import2.setIsDeleted(false);

            Import import3 = new Import();
            import3.setUuid(RandomUtil.random6Digits());
            import3.setImportDate(LocalDateTime.now());
            import3.setSupplier(supplier2);
            import3.setStaff(staff3);
            import3.setTotalAmount(BigDecimal.valueOf(750));
            import3.setIsDeleted(false);

            Import import4 = new Import();
            import4.setUuid(RandomUtil.random6Digits());
            import4.setImportDate(LocalDateTime.now());
            import4.setSupplier(supplier2);
            import4.setStaff(staff1);
            import4.setTotalAmount(BigDecimal.valueOf(1500));
            import4.setIsDeleted(false);

            importRepository.saveAll(List.of(import1, import2, import3, import4));

        }

        if (saleRepository.count() == 0){

            Customer customer1 = customerRepository.findByPhone("1099001122").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!")
            );

            Customer customer2 = customerRepository.findByPhone("1099001123").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!")
            );

            Customer customer3 = customerRepository.findByPhone("1099001124").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!")
            );

            Customer customer4 = customerRepository.findByPhone("1099001125").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!")
            );

            Staff staff1 = staffRepository.findByPhone("000-000-001").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found!")
            );

            Staff staff2 = staffRepository.findByPhone("000-000-002").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found!")
            );

            Staff staff3 = staffRepository.findByPhone("000-000-003").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found!")
            );

            Sale sale1 = new Sale();
            sale1.setUuid(RandomUtil.random6Digits());
            sale1.setSaleDate(LocalDateTime.now());
            sale1.setCustomer(customer1);
            sale1.setStaff(staff2);
            sale1.setTotalAmount(BigDecimal.valueOf(600));
            sale1.setIsDeleted(false);

            Sale sale2 = new Sale();
            sale2.setUuid(RandomUtil.random6Digits());
            sale2.setSaleDate(LocalDateTime.now());
            sale2.setCustomer(customer2);
            sale2.setStaff(staff2);
            sale2.setTotalAmount(BigDecimal.valueOf(1300));
            sale2.setIsDeleted(false);

            Sale sale3 = new Sale();
            sale3.setUuid(RandomUtil.random6Digits());
            sale3.setSaleDate(LocalDateTime.now());
            sale3.setCustomer(customer3);
            sale3.setStaff(staff1);
            sale3.setTotalAmount(BigDecimal.valueOf(2400));
            sale3.setIsDeleted(false);

            Sale sale4 = new Sale();
            sale4.setUuid(RandomUtil.random6Digits());
            sale4.setSaleDate(LocalDateTime.now());
            sale4.setCustomer(customer4);
            sale4.setStaff(staff3);
            sale4.setTotalAmount(BigDecimal.valueOf(1600));
            sale4.setIsDeleted(false);

            saleRepository.saveAll(List.of(sale1, sale2, sale3, sale4));
        }

        if (invoiceRepository.count() == 0){

            Customer customer1 = customerRepository.findByPhone("1099001122").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!")
            );

            Customer customer2 = customerRepository.findByPhone("1099001123").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!")
            );

            Customer customer3 = customerRepository.findByPhone("1099001124").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!")
            );

            Customer customer4 = customerRepository.findByPhone("1099001125").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!")
            );

            Invoice invoice1 = new Invoice();
            invoice1.setUuid(RandomUtil.random6Digits());
            invoice1.setInvoiceDate(LocalDateTime.now());
            invoice1.setTotalAmount(BigDecimal.valueOf(600));
            invoice1.setPaidAmount(BigDecimal.valueOf(400));
            invoice1.setOweAmount(BigDecimal.valueOf(200));
            invoice1.setCustomer(customer1);
            invoice1.setIsDeleted(false);

            Invoice invoice2 = new Invoice();
            invoice2.setUuid(RandomUtil.random6Digits());
            invoice2.setInvoiceDate(LocalDateTime.now());
            invoice2.setTotalAmount(BigDecimal.valueOf(1600));
            invoice2.setPaidAmount(BigDecimal.valueOf(400));
            invoice2.setOweAmount(BigDecimal.valueOf(1200));
            invoice2.setCustomer(customer2);
            invoice2.setIsDeleted(false);

            Invoice invoice3 = new Invoice();
            invoice3.setUuid(RandomUtil.random6Digits());
            invoice3.setInvoiceDate(LocalDateTime.now());
            invoice3.setTotalAmount(BigDecimal.valueOf(2300));
            invoice3.setPaidAmount(BigDecimal.valueOf(1400));
            invoice3.setOweAmount(BigDecimal.valueOf(900));
            invoice3.setCustomer(customer3);
            invoice3.setIsDeleted(false);

            Invoice invoice4 = new Invoice();
            invoice4.setUuid(RandomUtil.random6Digits());
            invoice4.setInvoiceDate(LocalDateTime.now());
            invoice4.setTotalAmount(BigDecimal.valueOf(600));
            invoice4.setPaidAmount(BigDecimal.valueOf(0));
            invoice4.setOweAmount(BigDecimal.valueOf(600));
            invoice4.setCustomer(customer1);
            invoice4.setIsDeleted(false);

            invoiceRepository.saveAll(List.of(invoice1, invoice2, invoice3, invoice4));

        }

    }

}
