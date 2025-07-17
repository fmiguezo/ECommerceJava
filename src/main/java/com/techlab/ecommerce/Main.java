package com.techlab.ecommerce;

import com.techlab.ecommerce.infrastructure.adapters.in.cli.CLIMenu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        CLIMenu menu = context.getBean(CLIMenu.class);
        menu.mostrarMenu();
    }
}