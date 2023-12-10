package com.example.spring.polling.publisher;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.String.format;

@RestController
@RequestMapping("/api")
public class BakeryController {

    private final ExecutorService bakers = Executors.newFixedThreadPool(5);

    @GetMapping("/bake/{bakedGood}")
    public DeferredResult<String> publish(@PathVariable String bakedGood, @RequestParam Integer bakeTime) {
        DeferredResult<String> output = new DeferredResult<>();
        bakers.execute(() -> {
            try {
                Thread.sleep(bakeTime);
                output.setResult(format("Bake for %s complete and order dispatched. Enjoy!", bakedGood));
            } catch (InterruptedException e) {
                output.setErrorResult("Something went wrong with your order!");
            }
        });
        return output;
    }
}
