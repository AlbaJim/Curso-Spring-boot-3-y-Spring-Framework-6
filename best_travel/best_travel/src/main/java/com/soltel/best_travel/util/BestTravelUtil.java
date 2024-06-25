package com.soltel.best_travel.util;

import java.time.LocalDateTime;
import java.util.Random;

public class BestTravelUtil {

    //usado para asignar fechas aleatorias.
    private static final Random random = new Random();

    //Creacion de método que saca fecha random cercana a la nuestra.
    public static LocalDateTime getRandomSoon(){
        //intervalo random será entre 2 y 5 y empezará en las 2. Ej: sale un random de 3 (está entre 2 y 5) y empieza desde las 2, por tanto la hora a añadir será las 5 (3+2).
        var randomHours = random.nextInt(5 - 2 ) + 2;
        var now = LocalDateTime.now();
        return now.plusHours(randomHours);
    }

    //Creacion de método que saca fecha random lejana a la nuestra.
    public static LocalDateTime getRandomLatter(){
        //intervalo random será entre 6 y 12 y empezará en las 6.
        var randomHours = random.nextInt(12 - 6 ) + 6;
        var now = LocalDateTime.now();
        return now.plusHours(randomHours);
    }

}
