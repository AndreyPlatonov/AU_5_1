package ru.netology.web;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {
    // private DataGenerator() {
    //   }
    public static RegistrationByClientInfo generatedByInfo() {
        Faker faker = new Faker(new Locale(("ru")));
        return new RegistrationByClientInfo(
                faker.address().city(),
                faker.name().lastName() + " " + faker.name().firstName(),
                faker.phoneNumber().phoneNumber());
    }
}

