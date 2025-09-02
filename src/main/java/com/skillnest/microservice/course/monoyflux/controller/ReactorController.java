package com.skillnest.microservice.course.monoyflux.controller;

import com.skillnest.microservice.course.monoyflux.service.ReactorService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactor")
public class ReactorController {
    private final ReactorService reactorService;

    public ReactorController(ReactorService reactorService) {
        this.reactorService = reactorService;
    }

    /**
     * Endpoint que devuelve un flujo de números pares del 1 al 10, multiplicados por 2.
     * Produce un stream de eventos de texto (text/event-stream).
     * @return Un Flux<Integer> que emite los números procesados.
     */
    @GetMapping(value = "/filter-map", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> getFilteredAndMappedNumbers() {
        return reactorService.filterAndMapExample();
    }

    /**
     * Endpoint que calcula la suma de los números del 1 al 10 usando el operador reduce.
     * @return Un Mono<Integer> que emite la suma total.
     */
    @GetMapping("/reduce")
    public Mono<Integer> getSum() {
        return reactorService.reduceExample();
    }

    /**
     * Endpoint que demuestra el manejo de errores con onErrorResume.
     * Simula una operación fallida y devuelve un valor predeterminado.
     * @return Un Mono<Integer> que emite el valor de respaldo (-1).
     */
    @GetMapping("/on-error")
    public Mono<Integer> getWithErrorHandling() {
        return reactorService.onErrorResumeExample();
    }

    /**
     * Endpoint que demuestra un flujo complejo con map, onErrorResume, filter y flatMap.
     * Produce un stream de eventos de texto (text/event-stream).
     * @return Un Flux<Integer> con el resultado del procesamiento del flujo.
     */
    @GetMapping(value = "/explain", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> getExplainedFlux() {
        return reactorService.explainFlux();
    }
}
