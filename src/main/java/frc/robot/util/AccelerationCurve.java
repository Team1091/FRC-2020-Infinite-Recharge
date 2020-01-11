package frc.robot.util;

public class AccelerationCurve {
    private long time;
    private double currentSpeed = 0;
    private double acceleration;

    public AccelerationCurve(double acceleration) {
        time = System.currentTimeMillis();
        this.acceleration = acceleration;
    }

    public void set(double input) {
        long currenttime = System.currentTimeMillis();
        double seconds = ((currenttime - time) / 1000.0);
        time = currenttime;

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

