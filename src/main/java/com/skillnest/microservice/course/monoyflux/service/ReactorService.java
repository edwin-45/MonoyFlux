package com.skillnest.microservice.course.monoyflux.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ReactorService {
    private static final Logger log = LoggerFactory.getLogger(ReactorService.class);

    /**
     * Crea un flujo de números del 1 al 10, filtra los números pares,
     * los multiplica por 2 y registra cada evento en el flujo.
     * @return Un {@link Flux} que emite los números pares procesados.
     */
    public Flux<Integer> filterAndMapExample() {
        return Flux.range(1, 10)
                .filter(i -> i % 2 == 0)
                .map(i -> i * 2)
                .log();
    }

    /**
     * Crea un flujo de números del 1 al 10 y los suma usando el operador reduce.
     * El valor inicial de la suma es 0.
     * @return Un {@link Mono} que emite la suma total de los números.
     */
    public Mono<Integer> reduceExample() {
        return Flux.range(1, 10)
                .reduce(0, Integer::sum)
                .log();
    }

    /**
     * Demuestra el manejo de errores. Intenta una operación que causa una excepción (división por cero)
     * y utiliza onErrorResume para capturar el error y devolver un valor de respaldo.
     * @return Un {@link Mono} que emite -1 como valor de respaldo en caso de error.
     */
    public Mono<Integer> onErrorResumeExample() {
        return Mono.<Integer>fromCallable(() -> 1 / 0)
                .onErrorResume(error -> {
                    log.error("Error capturado: {}", error.getMessage());
                    return Mono.just(-1);
                })
                .log();
    }

    /**
     * Demuestra una cadena de operadores reactivos más compleja.
     * 1. Emite números del 1 al 10.
     * 2. Lanza una excepción para números pares.
     * 3. Captura la excepción y emite un 0 en su lugar.
     * 4. Filtra los números para mantener solo aquellos mayores que 5.
     * 5. Para cada número restante, lo multiplica por 2 y emite el resultado con un retardo.
     * @return Un {@link Flux} que emite los resultados del procesamiento complejo.
     */
    public Flux<Integer> explainFlux() {
        return Flux.range(1, 10)
                .map(i -> {
                    if (i % 2 == 0) {
                        throw new RuntimeException("Número par no permitido");
                    }
                    return i;
                })
                .onErrorResume(RuntimeException.class, error -> {
                    log.error("Error: {}", error.getMessage());
                    return Mono.just(0);
                })
                .filter(number -> number > 5)
                .flatMap(number -> Mono.just(number * 2)
                        .delayElement(Duration.ofMillis(100)));
    }
}
