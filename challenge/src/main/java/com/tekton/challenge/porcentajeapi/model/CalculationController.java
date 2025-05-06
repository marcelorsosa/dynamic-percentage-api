package com.tekton.challenge.porcentajeapi.model;


@RestController
@RequestMapping("/api/calculate")
@RequiredArgsConstructor
public class CalculationController {

    private final CalculationService calculationService;

    @GetMapping
    public ResponseEntity<BigDecimal> calculate(
            @RequestParam BigDecimal num1,
            @RequestParam BigDecimal num2
    ) {
        return ResponseEntity.ok(calculationService.calculate(num1, num2));
    }
}
