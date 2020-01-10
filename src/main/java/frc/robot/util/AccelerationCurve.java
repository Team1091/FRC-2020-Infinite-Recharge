package frc.robot.util;

public class AccelerationCurve {
    private long time;
    private double currentspeed;
    private double acceleration;

    public AccelerationCurve(double acceleration) {
        time = System.currentTimeMillis();
        currentspeed = 0;
        this.acceleration = acceleration;
    }

    public void set(double input) {
        long currenttime = System.currentTimeMillis();
        double seconds = ((currenttime - time) / 1000.0);
        time = currenttime;

        double velocitychange = acceleration * seconds;
        if (currentspeed > input) {
            currentspeed = currentspeed - velocitychange;

        } else if (currentspeed < input) {
            currentspeed = currentspeed + velocitychange;
        }

    }

    public double getCurrentSpeed() {
        return currentspeed;
    }
}
