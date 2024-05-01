package com.alexosta.ostapjuks_pizza;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class Animations {
    public void setButtonHoverHandlers(Button button) {
        button.setOnMouseEntered(event -> animateUsingScaleTransition(button, 0.1, true));
        button.setOnMouseExited(event -> animateUsingScaleTransition(button, 0.2, false));
    }

    private void animateUsingScaleTransition(Button obj, double speed, boolean isEntered) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(speed), obj);
        scaleTransition.setToX(isEntered ? 1.05 : 1.0);
        scaleTransition.setToY(isEntered ? 1.05 : 1.0);
        scaleTransition.play();
    }
}
