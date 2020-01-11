package frc.robot.util;

import edu.wpi.first.wpilibj.Timer;

public class AccelerationCurve {
    private Timer timer = new Timer();
    private double currentSpeed = 0;
    private double acceleration;

    public AccelerationCurve(double acceleration) {
        timer.start();
        this.acceleration = acceleration;
    }

    public void set(double input) {
        double seconds = timer.get();
        timer.reset();

        double velocitychange = acceleration * seconds;
        if (input == 0)
            currentSpeed = 0;
        else if (input > 0) {
            if (currentSpeed >= 0)
                currentSpeed = (input > currentSpeed) ?
                        currentSpeed + velocitychange :
                        input;
            else
                currentSpeed = 0;

        } else if (input < 0) {
            if (currentSpeed <= 0)
                currentSpeed = (input < currentSpeed) ?
                        currentSpeed - velocitychange :
                        input;
            else
                currentSpeed = 0;
        }
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }
}

