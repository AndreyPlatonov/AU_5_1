package ru.netology.web;

import com.github.javafaker.Faker;

import java.util.Locale;

import static com.google.common.base.Ascii.toLowerCase;


public class DataGenerator {

    public static RegistrationByClientInfo generatedByInfo() {
        Faker faker = new Faker(new Locale(("ru")));

        while (toLowerCase(faker.name().lastName()+faker.name().firstName()).contains("ё") ){

            Faker fakerTmp = new Faker(new Locale(("ru")));

            faker=fakerTmp;
        }
        return new RegistrationByClientInfo(
                faker.address().city(),
                faker.name().lastName() + " " + faker.name().firstName(),
                faker.phoneNumber().phoneNumber());
    }
}

