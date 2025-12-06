package calculator.controller;

import calculator.input.StringInput;

public class CalculateController {

    private final StringInput stringInput;

    public CalculateController() {
        this.stringInput = new StringInput();
    }

    public void Run() {
        try {
            stringInput.Start();
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}